package com.solaris.manager.model

enum class ProjectPhase(val label: String) {
    CONCEPTION("1. Conception, Sourcing & R&D"),
    PRODUCTION("2. Production, Ventes & Opérations")
}

data class CostItem(
    val name: String,
    val amountHT: String,
    val note: String
)

data class UnitEconomicsDetail(
    val retailPriceTTC: String,
    val retailPriceHT: String,
    val wholesaleBOMHT: String,
    val shippingCost: String,
    val paymentGatewayFee: String,
    val netProfitPerUnit: String,
    val targetMonthlyNet: String,
    val costItems: List<CostItem>,
    val executiveSummary: String,
    val sourceOrSupplier: String
)

data class PartnerProfitProjection(
    val monthlyVolumeHypothesis: String,
    val totalCompanyNetMonthly: String,
    val associate1Name: String,
    val associate1NetMonthly: String,
    val associate1SpecificAdvantage: String,
    val associate2Name: String,
    val associate2NetMonthly: String,
    val associate2SpecificAdvantage: String,
    val annualDividendsPerPartner: String,
    val taxOptimizationNote: String
)

data class CommercialPack(
    val name: String,
    val badge: String,
    val retailPriceTTC: String,
    val wholesaleCostHT: String,
    val netMarginAmount: String,
    val netMarginPercent: String,
    val paybackPeriod: String,
    val contents: String
)

data class ProductionSimulation(
    val zoneName: String,
    val annualKwh: String,
    val annualSavingsEuros: String,
    val paybackYears: String,
    val sourceLabel: String,
    val sourceUrl: String
)

data class VerifiedSupplier(
    val name: String,
    val location: String,
    val role: String,
    val contactB2B: String,
    val conditions: String,
    val portalUrl: String
)

data class RiskMitigationItem(
    val title: String,
    val probabilityLevel: String, // 'FAIBLE', 'MOYEN', 'ÉLEVÉ'
    val potentialImpact: String,
    val failureModeDescription: String,
    val viableSolution: String,
    val legalOrTechnicalProof: String
)

data class PartnerPitchArgument(
    val number: Int,
    val title: String,
    val argument: String,
    val keyProof: String
)

data class ObjectionItem(
    val objection: String,
    val rebuttal: String,
    val tacticalAngle: String
)

data class RegulatoryRoadmapItem(
    val badge: String,
    val title: String,
    val legalRef: String,
    val description: String,
    val officialLink: String? = null
)

data class MarketStudyDetail(
    val marketSize: String,
    val growthRate: String,
    val targetPainPoint: String,
    val competitiveEdge: String,
    val officialSourceLabel: String,
    val officialSourceUrl: String,
    val fullAnalysis: String
)

data class EcomDossier(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: String,
    val iconEmoji: String,
    var progressPercent: Int,
    val estimatedNetMargin: String,
    val targetMonthlyRevenue: String,
    // Étude de marché macro 100% sourcée
    val marketStudy: MarketStudyDetail? = null,
    // Modèle économique unitaire détaillé (P&L réel)
    val unitEconomics: UnitEconomicsDetail? = null,
    // Projection des bénéfices raisonnables répartis par associé (50/50)
    val partnerProfit: PartnerProfitProjection? = null,
    // Gamme commerciale de packs (Solo, Duo, Accessoires)
    val commercialPacks: List<CommercialPack> = emptyList(),
    // Simulation de production réelle & économies Linky (PVGIS UE)
    val productionSimulations: List<ProductionSimulation> = emptyList(),
    // Annuaire des grossistes B2B vérifiés
    val verifiedSuppliers: List<VerifiedSupplier> = emptyList(),
    // Pitch de présentation pour l'associé (Les arguments massue en 3 min)
    val partnerPitchArguments: List<PartnerPitchArgument> = emptyList(),
    // Objections clients réelles & parades mot à mot
    val riskMitigations: List<RiskMitigationItem> = emptyList(),
    val objections: List<ObjectionItem> = emptyList(),
    // Réglementation, démarches administratives & boucliers
    val regulatoryRoadmap: List<RegulatoryRoadmapItem> = emptyList(),
    // Partie Conception (R&D, Sourcing 100% sourcé)
    val conceptionItems: List<ConceptionDetail>,
    // Partie Production (Mise en ligne, Actions, Opérations terrain, Ventes)
    val productionActions: List<ProductionAction>,
    // Boîte à Outils & Modèles prêts à l'emploi (Scripts, Statuts, Annonces, CGV)
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
    val category: String, // "Marketing", "Juridique", "Technique", "Logistique"
    val contentToCopy: String,
    val sourceOrNorm: String
)
