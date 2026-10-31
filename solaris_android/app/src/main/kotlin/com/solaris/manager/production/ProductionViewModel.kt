package com.solaris.manager.production

import androidx.lifecycle.ViewModel
import com.solaris.manager.data.SolarComponentsData
import com.solaris.manager.model.SolarComponent
import com.solaris.manager.production.model.AssemblyStep
import com.solaris.manager.production.model.ManufacturedStation
import com.solaris.manager.production.model.PackagingManifest
import com.solaris.manager.production.model.QualityCheckItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ProductionUiState(
    val selectedProductionTab: ProductionSubTab = ProductionSubTab.ASSEMBLY,
    val assemblySteps: List<AssemblyStep> = defaultAssemblySteps,
    val qualityChecks: List<QualityCheckItem> = defaultQualityChecks,
    val manufacturedStations: List<ManufacturedStation> = initialStations,
    val packagingManifest: PackagingManifest = PackagingManifest(),
    val workshopStock: List<SolarComponent> = SolarComponentsData.components,
    val inputPanelSerial: String = "",
    val inputInverterSerial: String = "",
    val inputTechnician: String = "Équipe Solaris Atelier",
    val registrationSuccessMessage: String? = null
)

enum class ProductionSubTab(val title: String) {
    ASSEMBLY("1. Gamme de Montage"),
    QUALITY_QC("2. Contrôle Qualité (QC)"),
    REGISTRY("3. Sérialisation & Enedis"),
    EXPEDITION("4. Fret & Colisage")
}

private val defaultAssemblySteps = listOf(
    AssemblyStep(
        stepNumber = 1,
        title = "Inspection Visuelle & Déballage Panneau",
        instruction = "Déballer le panneau Trina 440W Vertex S+ sur table capitonnée. Vérifier l'absence de micro-fissures sur les 2 faces de verre 2.0 mm et la planéité du cadre alu 30 mm.",
        toolRequired = "Ventouse double vitrier + Lampe d'inspection LED",
        torqueNm = null,
        safetyWarning = "Port obligatoire de gants anti-coupure niveau 5."
    ),
    AssemblyStep(
        stepNumber = 2,
        title = "Fixation Micro-Onduleur sur Châssis Ksolar",
        instruction = "Positionner le micro-onduleur Hoymiles HMS-800 sur la platine alu du chevalet. Insérer les boulons inox M8 avec rondelles éventails de contact de terre.",
        toolRequired = "Clé dynamométrique avec douille de 13 mm",
        torqueNm = 12.0,
        safetyWarning = "Respecter impérativement le couple de 12 Nm pour éviter toute déformation du boîtier IP67."
    ),
    AssemblyStep(
        stepNumber = 3,
        title = "Raccordement DC MC4 & Étanchéité",
        instruction = "Connecter les fiches positives (+) et négatives (-) du panneau solaire sur l'entrée MPPT 1 de l'onduleur. Pousser jusqu'à entendre le clic de verrouillage distinct.",
        toolRequired = "Outil de déverrouillage / serrage MC4",
        torqueNm = 2.5,
        safetyWarning = "Ne jamais raccorder ou débrancher sous charge ou en plein ensoleillement direct sans masquer le panneau."
    ),
    AssemblyStep(
        stepNumber = 4,
        title = "Câblage AC Schuko & Test Équipotentiel",
        instruction = "Encliqueter le câble secteur HMS 5m sur la prise femelle AC de l'onduleur. Fixer le serre-câble anti-traction sur le montant alu du châssis.",
        toolRequired = "Pince pour colliers Rilsan anti-UV",
        torqueNm = null,
        safetyWarning = "Vérifier le bon positionnement du joint torique d'étanchéité IP68 avant clipsage."
    )
)

private val defaultQualityChecks = listOf(
    QualityCheckItem(
        id = "qc_ground_continuity",
        title = "Continuité de la liaison équipotentielle (Terre)",
        testProcedure = "Mesure de résistance entre le cadre alu du panneau, le châssis et la borne de terre Schuko.",
        expectedValue = "Résistance R < 0.10 Ω",
        isPassed = true
    ),
    QualityCheckItem(
        id = "qc_voc_voltage",
        title = "Tension à vide DC (Voc)",
        testProcedure = "Mesure au multimètre calibré aux bornes MC4 sous lampe étalon.",
        expectedValue = "Voc = 43.2 V (± 5% soit 41.0V - 45.4V)",
        isPassed = true
    ),
    QualityCheckItem(
        id = "qc_vde_disconnect",
        title = "Protection de découplage réseau (VDE 0126-1-1)",
        testProcedure = "Simulation de coupure secteur 230V et mesure du temps de coupure automatique de l'onduleur.",
        expectedValue = "Coupure immédiate < 200 ms (Homologation Enedis)",
        isPassed = true
    ),
    QualityCheckItem(
        id = "qc_mc4_lock",
        title = "Verrouillage mécanique & Étanchéité IP68",
        testProcedure = "Test de traction mécanique à 50N sur les connecteurs DC et vérification du joint torique.",
        expectedValue = "Aucun jeu mécanique, verrouillage indémontable sans outil",
        isPassed = true
    )
)

private val initialStations = listOf(
    ManufacturedStation(
        stationSerial = "SOL-2026-0041",
        panelSerial = "TRN-9331-440-8812",
        inverterSerial = "HYM-8424-HMS-2931",
        assemblyDate = "31/10/2026",
        technicianName = "Solaris Atelier 1",
        qcPassed = true,
        enedisCertificateReady = true,
        shippingStatus = "Prêt à expédier (Palette Geodis)"
    ),
    ManufacturedStation(
        stationSerial = "SOL-2026-0040",
        panelSerial = "TRN-9331-440-8811",
        inverterSerial = "HYM-8424-HMS-2930",
        assemblyDate = "30/10/2026",
        technicianName = "Solaris Atelier 1",
        qcPassed = true,
        enedisCertificateReady = true,
        shippingStatus = "Expédié - Suivi Geodis #GEO-7739120"
    )
)

class ProductionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProductionUiState())
    val uiState: StateFlow<ProductionUiState> = _uiState.asStateFlow()

    fun selectTab(tab: ProductionSubTab) {
        _uiState.value = _uiState.value.copy(selectedProductionTab = tab)
    }

    fun toggleStep(stepNumber: Int) {
        val updated = _uiState.value.assemblySteps.map {
            if (it.stepNumber == stepNumber) it.copy(isCompleted = !it.isCompleted) else it
        }
        _uiState.value = _uiState.value.copy(assemblySteps = updated)
    }

    fun toggleQc(checkId: String) {
        val updated = _uiState.value.qualityChecks.map {
            if (it.id == checkId) it.copy(isPassed = !it.isPassed) else it
        }
        _uiState.value = _uiState.value.copy(qualityChecks = updated)
    }

    fun setPanelSerial(serial: String) {
        _uiState.value = _uiState.value.copy(inputPanelSerial = serial)
    }

    fun setInverterSerial(serial: String) {
        _uiState.value = _uiState.value.copy(inputInverterSerial = serial)
    }

    fun setTechnician(name: String) {
        _uiState.value = _uiState.value.copy(inputTechnician = name)
    }

    fun registerNewStation() {
        val panel = _uiState.value.inputPanelSerial.ifBlank { "TRN-9331-${(1000..9999).random()}" }
        val inverter = _uiState.value.inputInverterSerial.ifBlank { "HYM-8424-${(1000..9999).random()}" }
        val nextIdNumber = _uiState.value.manufacturedStations.size + 42
        val newSerial = "SOL-2026-00$nextIdNumber"
        val dateStr = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(Date())

        val newStation = ManufacturedStation(
            stationSerial = newSerial,
            panelSerial = panel,
            inverterSerial = inverter,
            assemblyDate = dateStr,
            technicianName = _uiState.value.inputTechnician,
            qcPassed = true,
            enedisCertificateReady = true,
            shippingStatus = "Prêt à expédier (Palette Geodis)"
        )

        // Décrémentation du stock atelier
        val updatedStock = _uiState.value.workshopStock.map {
            it.copy(stockQuantity = (it.stockQuantity - 1).coerceAtLeast(0))
        }

        _uiState.value = _uiState.value.copy(
            manufacturedStations = listOf(newStation) + _uiState.value.manufacturedStations,
            workshopStock = updatedStock,
            inputPanelSerial = "",
            inputInverterSerial = "",
            registrationSuccessMessage = "Station $newSerial assemblée, contrôlée QC et prête à l'expédition !"
        )
    }

    fun clearSuccessMessage() {
        _uiState.value = _uiState.value.copy(registrationSuccessMessage = null)
    }
}
