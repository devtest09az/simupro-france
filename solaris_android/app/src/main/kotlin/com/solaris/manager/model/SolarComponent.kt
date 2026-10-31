package com.solaris.manager.model

enum class ComponentCategory(val label: String) {
    PANEL("Panneau Photovoltaïque"),
    INVERTER("Micro-Onduleur"),
    MOUNTING("Support & Châssis"),
    CABLING("Câblage & Sécurité AC"),
    MONITORING("Mesure & Supervision")
}

data class SolarComponent(
    val id: String,
    val name: String,
    val category: ComponentCategory,
    val reference: String,
    val brand: String,
    val description: String,
    val specifications: Map<String, String>,
    val unitPriceRetailTtc: Double,
    val unitPriceWholesaleHt: Double,
    val supplierUrl: String,
    val certifications: List<String>,
    var stockQuantity: Int,
    val assemblyRole: String,
    val safetyNotes: String
)
