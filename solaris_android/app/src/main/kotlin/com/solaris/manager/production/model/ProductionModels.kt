package com.solaris.manager.production.model

data class AssemblyStep(
    val stepNumber: Int,
    val title: String,
    val instruction: String,
    val toolRequired: String,
    val torqueNm: Double? = null,
    val safetyWarning: String? = null,
    val isCompleted: Boolean = false
)

data class QualityCheckItem(
    val id: String,
    val title: String,
    val testProcedure: String,
    val expectedValue: String,
    val isPassed: Boolean = false,
    val notes: String = ""
)

data class ManufacturedStation(
    val stationSerial: String,
    val panelSerial: String,
    val inverterSerial: String,
    val assemblyDate: String,
    val technicianName: String,
    val qcPassed: Boolean,
    val enedisCertificateReady: Boolean,
    val shippingStatus: String
)

data class PackagingManifest(
    val packageType: String = "Carton renforcé double cannelure sur mesure",
    val dimensionsCm: String = "185 x 120 x 15 cm",
    val grossWeightKg: Double = 28.5,
    val protectionItems: List<String> = listOf(
        "Coins de protection d'angle en polyéthylène haute densité (x4)",
        "Film étirable opaque anti-UV",
        "Calage mousse pour micro-onduleur Hoymiles HMS",
        "Pochette étanche porte-documents (Notice + Attestation Enedis)"
    ),
    val approvedCarrier: String = "Geodis Calberson / Dachser France (Livraison avec hayon & RDV SMS)"
)
