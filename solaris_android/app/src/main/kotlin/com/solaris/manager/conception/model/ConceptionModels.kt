package com.solaris.manager.conception.model

enum class OrientationOption(val label: String, val factor: Double) {
    SUD("Plein Sud (Optimal 100%)", 1.00),
    SUD_EST("Sud-Est (-5%)", 0.95),
    SUD_OUEST("Sud-Ouest (-5%)", 0.95),
    EST("Plein Est (-20%)", 0.80),
    OUEST("Plein Ouest (-20%)", 0.80)
}

enum class GroundSurfaceAlbedo(val label: String, val bifacialGainPercent: Int) {
    GRAVIER_BLANC("Gravier Blanc / Calcaire (+24%)", 24),
    DALLE_BETON_CLAIR("Terrasse Béton / Carrelage clair (+18%)", 18),
    PELOUSE_JARDIN("Gazon / Pelouse verte (+12%)", 12),
    BITUME_FONCE("Enrobé bitume / Sol sombre (+5%)", 5)
}

data class SimulationParameters(
    val panelPeakWatts: Int = 440,
    val tiltAngleDegrees: Float = 30f,
    val orientation: OrientationOption = OrientationOption.SUD,
    val groundSurface: GroundSurfaceAlbedo = GroundSurfaceAlbedo.GRAVIER_BLANC,
    val electricityPricePerKwh: Double = 0.2516
)

data class SimulationYieldResult(
    val annualKwh: Int,
    val annualSavingsEuros: Double,
    val dailyKwhAverage: Double,
    val paybackPeriodYears: Double,
    val co2KgAvoidedAnnual: Int
)

data class BomAuditModel(
    val totalRetailCostTtc: Double,
    val totalWholesaleCostHt: Double,
    val customerSellingPriceTtc: Double,
    val customerSellingPriceHt: Double,
    val freightCostHt: Double,
    val grossMarginRetail: Double,
    val grossMarginWholesale: Double,
    val netMarginWholesale: Double
)
