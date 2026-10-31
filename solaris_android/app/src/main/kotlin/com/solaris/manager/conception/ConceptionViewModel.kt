package com.solaris.manager.conception

import androidx.lifecycle.ViewModel
import com.solaris.manager.conception.model.BomAuditModel
import com.solaris.manager.conception.model.GroundSurfaceAlbedo
import com.solaris.manager.conception.model.OrientationOption
import com.solaris.manager.conception.model.SimulationParameters
import com.solaris.manager.conception.model.SimulationYieldResult
import com.solaris.manager.data.SolarComponentsData
import com.solaris.manager.model.SolarComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.cos
import kotlin.math.roundToInt

data class ConceptionUiState(
    val components: List<SolarComponent> = SolarComponentsData.components,
    val selectedComponent: SolarComponent? = null,
    val simulationParams: SimulationParameters = SimulationParameters(),
    val yieldResult: SimulationYieldResult = calculateYield(SimulationParameters()),
    val bomAudit: BomAuditModel = calculateBom(SolarComponentsData.components),
    val selectedConceptionTab: ConceptionSubTab = ConceptionSubTab.SPECS
)

enum class ConceptionSubTab(val title: String) {
    SPECS("1. Composants & Specs"),
    SIMULATOR("2. Simulateur Rendement"),
    BOM_AUDIT("3. Coûts & Marges R&D")
}

private fun calculateYield(params: SimulationParameters): SimulationYieldResult {
    // Rendement de base France métropolitaine : ~1150 kWh / kWc par an pour 30° Sud
    val baseIrradianceKwhPerKwc = 1150.0
    // Facteur d'angle (optimum 30-35° en France)
    val angleRad = Math.toRadians(params.tiltAngleDegrees.toDouble())
    val optimumRad = Math.toRadians(32.0)
    val tiltEfficiency = 1.0 - 0.15 * (1.0 - cos(angleRad - optimumRad))
    
    // Gain bifacial
    val bifacialMultiplier = 1.0 + (params.groundSurface.bifacialGainPercent / 100.0)
    
    val totalKwh = (params.panelPeakWatts / 1000.0) * baseIrradianceKwhPerKwc * tiltEfficiency * params.orientation.factor * bifacialMultiplier
    val annualKwh = totalKwh.roundToInt()
    val annualSavings = annualKwh * params.electricityPricePerKwh
    val dailyAvg = annualKwh / 365.0
    val stationPrice = 349.0
    val payback = if (annualSavings > 0) stationPrice / annualSavings else 0.0
    val co2Saved = (annualKwh * 0.057).roundToInt() // Facteur réseau France ~57g CO2/kWh

    return SimulationYieldResult(
        annualKwh = annualKwh,
        annualSavingsEuros = annualSavings,
        dailyKwhAverage = dailyAvg,
        paybackPeriodYears = payback,
        co2KgAvoidedAnnual = co2Saved
    )
}

private fun calculateBom(components: List<SolarComponent>): BomAuditModel {
    val totalRetailTtc = components.sumOf { it.unitPriceRetailTtc }
    val totalWholesaleHt = components.sumOf { it.unitPriceWholesaleHt }
    val sellingTtc = 349.00
    val sellingHt = sellingTtc / 1.20 // 290.83 € HT
    val freightHt = 42.00 // Fret palette Geodis unitaire
    
    val grossMarginRetail = sellingTtc - totalRetailTtc
    val grossMarginWholesale = sellingHt - totalWholesaleHt
    val netMarginWholesale = sellingHt - totalWholesaleHt - freightHt

    return BomAuditModel(
        totalRetailCostTtc = totalRetailTtc,
        totalWholesaleCostHt = totalWholesaleHt,
        customerSellingPriceTtc = sellingTtc,
        customerSellingPriceHt = sellingHt,
        freightCostHt = freightHt,
        grossMarginRetail = grossMarginRetail,
        grossMarginWholesale = grossMarginWholesale,
        netMarginWholesale = netMarginWholesale
    )
}

class ConceptionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ConceptionUiState())
    val uiState: StateFlow<ConceptionUiState> = _uiState.asStateFlow()

    fun selectTab(tab: ConceptionSubTab) {
        _uiState.value = _uiState.value.copy(selectedConceptionTab = tab)
    }

    fun selectComponent(component: SolarComponent?) {
        _uiState.value = _uiState.value.copy(selectedComponent = component)
    }

    fun updateTiltAngle(angle: Float) {
        val updated = _uiState.value.simulationParams.copy(tiltAngleDegrees = angle)
        _uiState.value = _uiState.value.copy(
            simulationParams = updated,
            yieldResult = calculateYield(updated)
        )
    }

    fun updateOrientation(orientation: OrientationOption) {
        val updated = _uiState.value.simulationParams.copy(orientation = orientation)
        _uiState.value = _uiState.value.copy(
            simulationParams = updated,
            yieldResult = calculateYield(updated)
        )
    }

    fun updateGroundSurface(surface: GroundSurfaceAlbedo) {
        val updated = _uiState.value.simulationParams.copy(groundSurface = surface)
        _uiState.value = _uiState.value.copy(
            simulationParams = updated,
            yieldResult = calculateYield(updated)
        )
    }
}
