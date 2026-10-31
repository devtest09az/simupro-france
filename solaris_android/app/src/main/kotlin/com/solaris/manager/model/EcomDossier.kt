package com.solaris.manager.model

enum class ProjectPhase(val label: String) {
    CONCEPTION("1. Conception, Sourcing & R&D"),
    PRODUCTION("2. Production, Ventes & Opérations")
}

data class EcomDossier(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: String,
    val iconEmoji: String,
    var progressPercent: Int,
    val estimatedNetMargin: String,
    val targetMonthlyRevenue: String,
    // Partie Conception (R&D, Sourcing, Étude de marché 100% sourcée)
    val conceptionItems: List<ConceptionDetail>,
    // Partie Production (Mise en ligne, Actions, Opérations terrain, Ventes)
    val productionActions: List<ProductionAction>,
    // Boîte à Outils & Modèles prêts à l'emploi (Scripts, Statuts, Annonces)
    val toolkitItems: List<ToolkitItem>,
    // Notes partagées entre les deux associés
    var partnerNotes: String,
    val liveWebsiteUrl: String? = null
)

data class ConceptionDetail(
    val title: String,
    val description: String,
    val keyMetric: String,
    val sourceLabel: String,
    val verifiedSourceUrl: String? = null,
    val officialReference: String? = null
)

data class ProductionAction(
    val id: String,
    val task: String,
    val isDone: Boolean,
    val priority: String,
    val assignee: String // "Moi", "Associé", "Les Deux"
)

data class ToolkitItem(
    val id: String,
    val title: String,
    val category: String, // "Marketing", "Juridique", "Technique"
    val contentToCopy: String,
    val sourceOrNorm: String
)
