package com.solaris.manager.model

enum class ProjectPhase(val label: String) {
    CONCEPTION("Phase Conception & Étude"),
    PRODUCTION("Phase Production & Ventes")
}

data class EcomDossier(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: String,
    val iconEmoji: String,
    val progressPercent: Int,
    val estimatedNetMargin: String,
    val targetMonthlyRevenue: String,
    // Partie Conception (R&D, Sourcing, Étude de marché)
    val conceptionItems: List<ConceptionDetail>,
    // Partie Production (Mise en ligne, Actions, Opérations, Ventes)
    val productionActions: List<ProductionAction>,
    val liveWebsiteUrl: String? = null
)

data class ConceptionDetail(
    val title: String,
    val description: String,
    val keyMetric: String,
    val verifiedSourceUrl: String? = null
)

data class ProductionAction(
    val id: String,
    val task: String,
    val isDone: Boolean,
    val priority: String
)
