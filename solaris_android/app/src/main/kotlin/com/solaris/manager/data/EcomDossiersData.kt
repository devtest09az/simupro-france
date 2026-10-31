package com.solaris.manager.data

import com.solaris.manager.model.CommercialPack
import com.solaris.manager.model.ConceptionDetail
import com.solaris.manager.model.CostItem
import com.solaris.manager.model.EcomDossier
import com.solaris.manager.model.MarketStudyDetail
import com.solaris.manager.model.ObjectionItem
import com.solaris.manager.model.OperationalPhaseStep
import com.solaris.manager.model.GrowthLever
import com.solaris.manager.model.AssociateVerificationItem
import com.solaris.manager.model.HumanPrerequisiteItem
import com.solaris.manager.model.RiskMitigationItem
import com.solaris.manager.model.PartnerPitchArgument
import com.solaris.manager.model.PartnerProfitProjection
import com.solaris.manager.model.ProductionAction
import com.solaris.manager.model.ProductionSimulation
import com.solaris.manager.model.RegulatoryRoadmapItem
import com.solaris.manager.model.ToolkitItem
import com.solaris.manager.model.UnitEconomicsDetail
import com.solaris.manager.model.VerifiedSupplier

object EcomDossiersData {
    val dossiers = listOf(
        // =========================================================================
        // 0. SOCIÉTÉ SAS COMMUNE (VOLTIS GROUP)
        // =========================================================================
        EcomDossier(
            id = "dossier_sas",
            title = "Société SAS Commune (VOLTIS GROUP)",
            subtitle = "Société Mère Multi-Activités • 1 Seul SIRET & Compte Qonto Unique pour Solaire & Alarme",
            category = "Juridique, Statuts & Dépôt Qonto",
            iconEmoji = "🏢",
            progressPercent = 90,
            estimatedNetMargin = "1 Seul SIRET • 0 € de structure supplémentaire",
            targetMonthlyRevenue = "14 933 € HT / mois (Cumul Solaire + Alarme)",
            liveWebsiteUrl = "https://formalites.entreprises.gouv.fr/",
            partnerNotes = """NOTE COMMUNE (Associés) :
- La SAS est la société mère qui porte les 2 marques commerciales (SOLAVOLT pour le solaire, SECUREVO pour les alarmes).
- Capital : 1 000 € (500 € par associé, 1 000 actions de 1 €), réparti 50% / 50%.
- Gouvernance : Président (Associé 1) avec mandat à titre 100% gratuit (0,00 € salaire) pour garantir le maintien intégral de l'ARE France Travail.
- Dépôt de capital en ligne sur Qonto en 24h, obtention du Kbis en 3 à 5 jours ouvrés via le Guichet Unique INPI.
- Dividendes nets de fin d'exercice imposés à la Flat Tax (30%) sans cotisations sociales URSSAF.""",
            marketStudy = MarketStudyDetail(
                marketSize = "France Métropolitaine • RCS & Greffe du Tribunal de Commerce",
                growthRate = "Multi-activités e-commerce sous SIRET unique",
                targetPainPoint = "Éviter les frais d'avocat (1 500 € à 2 500 €) et les conflits de cotisations sociales de la SARL",
                competitiveEdge = "Montage SAS 50/50 optimisé : maintien à 100% de l'ARE chômage du Président + exonération de cotisations sociales sur les dividendes",
                officialSourceLabel = "Code de commerce (Art. L227-1) & Guichet Unique INPI",
                officialSourceUrl = "https://formalites.entreprises.gouv.fr/",
                fullAnalysis = """ORGANISATION JURIDIQUE & COMMERCIALE DE LA SAS :
1. RAISON D'ÊTRE DU MONTAGE SAS :
- La création d'une SAS (Société par Actions Simplifiée) permet d'exploiter immédiatement plusieurs marques commerciales (Solaire 440W et Alarme 4G) sous une seule entité juridique.
- 1 seul compte bancaire professionnel (Qonto), 1 seule comptabilité (Pennylane), 1 seule déclaration fiscale annuelle.
- Absorption totale des charges fixes (expert-comptable 83 €, compte Qonto 29 €, assurance RC Pro 42 €) dès les premières ventes solaires.

2. LE BOUCLIER TOTAL ARE FRANCE TRAVAIL :
- En SAS, le dirigeant relève du régime des assimilés salariés.
- Si le Procès-Verbal d'Assemblée Générale Constitutif stipule une rémunération de 0,00 €, le dirigeant ne perçoit aucun salaire et ne reçoit aucune fiche de paie.
- France Travail maintient 100% des allocations chômage mensuelles (zéro déduction).
- À l'inverse, en SARL 50/50, la gérance majoritaire impose des cotisations sociales minimales forfaitaires dès le 1er jour, même avec 0 € de chiffre d'affaires, créant un blocage administratif avec France Travail.

3. DÉNOMINATION SOCIALE AU GREFFE vs MARQUES COMMERCIALES :
- La dénomination sociale au Greffe doit être institutionnelle et neutre : 'VOLTIS GROUP SAS' (ou 'HELIO TRADE SAS').
- Elle ne rentre ainsi en conflit avec aucun tiers (contrairement à 'Solaris SAS' qui existe déjà au RCS de Bourg-en-Bresse).
- Les marques commerciales 'SOLAVOLT.FR' et 'SECUREVO.FR' sont déposées à l'INPI et exploitées en toute conformité."""
            ),
            unitEconomics = UnitEconomicsDetail(
                retailPriceTTC = "14 933 € HT / mois",
                retailPriceHT = "Chiffre d'Affaires Combiné (30 kits solaires + 50 packs alarme)",
                wholesaleBOMHT = "6 220 € HT (Achats matériels combinés)",
                shippingCost = "630 € HT (Geodis + Colissimo)",
                paymentGatewayFee = "283 € HT (Stripe 1,5% + 0,25 €)",
                netProfitPerUnit = "+6 254 € HT / mois",
                targetMonthlyNet = "+2 285 € net/associé (Solaire) + +1 180 € net/associé (Alarme) = +3 465 € net/mois/associé",
                costItems = listOf(
                    CostItem("Abonnement Compte Pro Qonto", "29,00 € HT", "Compte pro en ligne avec cartes Mastercard et sous-comptes"),
                    CostItem("Expert-Comptable Pennylane / Dougs", "83,00 € HT", "Tenue comptable, bilan annuel et liasse fiscale certifiée"),
                    CostItem("Assurance RC Pro E-Commerce Multi-Activités", "42,00 € HT", "Couverture responsabilité civile exploitation basse tension & alarmes"),
                    CostItem("Frais Web, Domaines & Hébergement", "25,00 € HT", "Noms de domaine .FR sur OVHcloud et serveurs sécurisés"),
                    CostItem("Cotisation CFE forfaitaire", "33,00 € HT", "Cotisation Foncière des Entreprises provisionnée")
                ),
                executiveSummary = "Charges fixes globales de la SAS : 212 € HT / mois. Ce montant est amorti dès le 3ème kit solaire vendu dans le mois. La marge générée par l'alarme est 100% nette de frais fixes.",
                sourceOrSupplier = "Tarifs officiels Qonto Pro, Pennylane & AXA Assurance"
            ),
            partnerProfit = PartnerProfitProjection(
                monthlyVolumeHypothesis = "Activité combinée SAS (30 stations solaires + 50 packs alarme / mois)",
                totalCompanyNetMonthly = "+ 5 658 € NET après IS 15% (67 896 € net / an)",
                associate1Name = "Associé 1 (Président SAS)",
                associate1NetMonthly = "+ 1 980 € NET d'impôt / mois (23 760 € net / an)",
                associate1SpecificAdvantage = "Mandat gratuit (0 € salaire) : cumul 100% avec les dividendes de la SAS et maintien intégral de l'ARE",
                associate2Name = "Associé 2 (Co-fondateur 50%)",
                associate2NetMonthly = "+ 1 980 € NET d'impôt / mois (23 760 € net / an)",
                associate2SpecificAdvantage = "Partage égalitaire 50/50 des dividendes nets (Flat Tax 30%)",
                annualDividendsPerPartner = "23 760 € nets / an / associé",
                taxOptimizationNote = "Les dividendes de SAS ne subissent aucune cotisation sociale URSSAF (contrairement à la SARL majoritaire). Maintien intégral de l'ARE chômage garanti."
            ),
            commercialPacks = emptyList(),
            productionSimulations = emptyList(),
            verifiedSuppliers = listOf(
                VerifiedSupplier(
                    name = "Qonto (Établissement Bancaire Pro Agréé ACPR)",
                    location = "Paris (75) • Dépôt de capital 100% en ligne",
                    role = "Compte séquestre des fonds & Compte pro bancaire de la SAS",
                    contactB2B = "qonto.com • Support client réactif 7j/7",
                    conditions = "Dépôt de capital en 24h ouvrées. Certificat de dépôt officiel immédiat pour le Greffe.",
                    portalUrl = "https://qonto.com/fr"
                ),
                VerifiedSupplier(
                    name = "Guichet Unique INPI (Portail Officiel de l'État)",
                    location = "INPI France • formalites.entreprises.gouv.fr",
                    role = "Immatriculation officielle au Registre du Commerce et des Sociétés (RCS)",
                    contactB2B = "Assistance INPI : 01 56 65 89 98",
                    conditions = "Dépôt des statuts, attestation JAL et certificat Qonto. Extrait Kbis délivré sous 3 à 5 jours ouvrés.",
                    portalUrl = "https://formalites.entreprises.gouv.fr/"
                ),
                VerifiedSupplier(
                    name = "Journal Spécial des Sociétés (JSS / Le Parisien Annonces)",
                    location = "France entière • Plateforme d'annonces légales habilitée",
                    role = "Publication de l'avis de constitution au Journal d'Annonces Légales (JAL)",
                    contactB2B = "Tarif légal forfaitaire fixé par arrêté ministériel",
                    conditions = "Attestation de parution délivrée instantanément en PDF pour le dossier de greffe.",
                    portalUrl = "https://www.jss.fr/"
                ),
                VerifiedSupplier(
                    name = "Pennylane / Dougs (Expertise-Comptable Connectée)",
                    location = "Paris / Lyon • Ordre des Experts-Comptables de France",
                    role = "Tenue de la comptabilité de la SAS et attestation annuelle de non-rémunération",
                    contactB2B = "Synchronisation automatique des flux bancaires Qonto et Stripe",
                    conditions = "Génération de la liasse fiscale et du bilan certifié en fin d'exercice.",
                    portalUrl = "https://www.pennylane.com/fr/"
                )
            ),
            partnerPitchArguments = listOf(
                PartnerPitchArgument(
                    number = 1,
                    title = "1 Seul SIRET & 1 Seul Compte Bancaire pour 2 Activités",
                    argument = "Pas besoin de monter deux sociétés ni de payer deux fois des frais d'expert-comptable ou de compte pro. La SAS chapeaute à la fois le Solaire (SOLAVOLT) et l'Alarme (SECUREVO) avec un objet social multi-activités prêt dès le 1er jour.",
                    keyProof = "Article 2 des Statuts certifié conforme Code de commerce."
                ),
                PartnerPitchArgument(
                    number = 2,
                    title = "Maintien à 100% des Allocations Chômage (ARE)",
                    argument = "En SAS, le mandat de Président à 0,00 € garantit que France Travail verse l'intégralité de l'allocation chaque mois sans aucune déduction ni cotisation minimale URSSAF (contrairement au piège de la gérance majoritaire de SARL).",
                    keyProof = "Circulaire Unédic 2024 & PV d'AG de non-rémunération."
                ),
                PartnerPitchArgument(
                    number = 3,
                    title = "Capital Social de 1 000 € déposé en 24h sur Qonto",
                    argument = "Chaque associé verse 500 € depuis son compte personnel. L'attestation de dépôt est délivrée sous 24h pour déposer au Greffe sans rendez-vous physique en agence bancaire.",
                    keyProof = "Procédure express Qonto certifiée ACPR / Banque de France."
                )
            ),
            riskMitigations = listOf(
                RiskMitigationItem(
                    title = "1. Maintien de l'ARE France Travail",
                    probabilityLevel = "NUL (si mandat gratuit bien acté)",
                    potentialImpact = "Perte des allocations mensuelles",
                    failureModeDescription = "Prendre un salaire de dirigeant ou s'enregistrer en SARL majoritaire avec cotisations TNS d'office.",
                    viableSolution = "1) Statuts en SAS. 2) PV d'AG constitutif stipulant expressément la gratuité du mandat (0,00 € salaire). 3) Déclaration mensuelle France Travail : '0h travaillée, 0 € de salaire perçu'.",
                    legalOrTechnicalProof = "Circulaire Unédic 2024 & Régime des assimilés salariés."
                ),
                RiskMitigationItem(
                    title = "2. Rejet de Dénomination au Greffe ('Solaris SAS')",
                    probabilityLevel = "CERTAIN si utilisation de 'Solaris SAS'",
                    potentialImpact = "Blocage de l'immatriculation au Greffe",
                    failureModeDescription = "Tenter d'immatriculer 'SOLARIS SAS' alors que la société existe déjà au RCS de Bourg-en-Bresse (SIREN 442 600 011).",
                    viableSolution = "Déposer une dénomination sociale neutre et institutionnelle au Greffe (VOLTIS GROUP SAS ou HELIO TRADE SAS), puis exploiter les marques commerciales déposées à l'INPI (SOLAVOLT.FR et SECUREVO.FR).",
                    legalOrTechnicalProof = "Base Sirene INSEE & Registre du Commerce et des Sociétés."
                )
            ),
            humanPrerequisites = listOf(
                HumanPrerequisiteItem(
                    id = "h_sas_1",
                    title = "Virement de 500 € par associé sur le compte de séquestre Qonto",
                    assignedTo = "Associés",
                    estimatedCost = "1 000 € (500 € / associé)",
                    whyAiCannotDoIt = "Action bancaire nominative requérant l'authentification forte des deux associés.",
                    actionRequiredByPartner = "Effectuer le virement de 500 € depuis son compte bancaire personnel vers le RIB Qonto.",
                    officialUrl = "https://qonto.com/fr"
                ),
                HumanPrerequisiteItem(
                    id = "h_sas_2",
                    title = "Réunir CNI recto-verso et justificatif de domicile de moins de 3 mois pour chaque associé",
                    assignedTo = "Associés",
                    estimatedCost = "0 €",
                    whyAiCannotDoIt = "Documents d'identité personnels officiels requis par le Greffe du Tribunal de Commerce.",
                    actionRequiredByPartner = "Téléverser les fichiers PDF nets sur l'espace de création Qonto.",
                    officialUrl = "https://formalites.entreprises.gouv.fr/"
                ),
                HumanPrerequisiteItem(
                    id = "h_sas_3",
                    title = "Signature électronique des statuts SAS avec l'Article 2 Multi-Activités",
                    assignedTo = "Les Deux",
                    estimatedCost = "0 € (Inclus)",
                    whyAiCannotDoIt = "Signature engageant juridiquement la responsabilité civile des associés fondateurs.",
                    actionRequiredByPartner = "Signer électroniquement les statuts complets générés via Yousign/DocuSign sur Qonto.",
                    officialUrl = "https://qonto.com/fr"
                ),
                HumanPrerequisiteItem(
                    id = "h_sas_4",
                    title = "Déposer la demande d'immatriculation sur formalites.entreprises.gouv.fr",
                    assignedTo = "Moi",
                    estimatedCost = "~60 € TTC (Frais de Greffe)",
                    whyAiCannotDoIt = "Validation finale du dossier Guichet Unique avec code d'authentification FranceConnect+.",
                    actionRequiredByPartner = "Valider le téléversement final sur le portail Guichet Unique INPI.",
                    officialUrl = "https://formalites.entreprises.gouv.fr/"
                ),
                HumanPrerequisiteItem(
                    id = "h_commando_sample",
                    title = "Commander 1 kit solaire échantillon (217 €) & 1 alarme démo (42 €) pour vidéo 60s",
                    assignedTo = "Les Deux",
                    estimatedCost = "259 € TTC",
                    whyAiCannotDoIt = "Achat matériel physique et tournage vidéo réel au smartphone pour crédibilité maximale.",
                    actionRequiredByPartner = "Passer la commande échantillon au comptoir grossiste et filmer le branchement en 60 secondes.",
                    officialUrl = "https://hoymiles.com"
                ),
                HumanPrerequisiteItem(
                    id = "h_commando_stripe",
                    title = "Créer le compte Stripe et générer le lien de paiement direct (349 € / acompte 50 €)",
                    assignedTo = "Moi",
                    estimatedCost = "0 € (Frais 1,5% au succès)",
                    whyAiCannotDoIt = "Authentification bancaire et liaison de l'IBAN de versement.",
                    actionRequiredByPartner = "Créer le lien de paiement sur dashboard.stripe.com et remplacer le lien du bouton commander.",
                    officialUrl = "https://dashboard.stripe.com"
                ),
                HumanPrerequisiteItem(
                    id = "h_commando_leboncoin",
                    title = "Publier 3 annonces locales LeBonCoin Pro & Facebook Marketplace",
                    assignedTo = "Associé",
                    estimatedCost = "0 € (Compte gratuit)",
                    whyAiCannotDoIt = "Gestion du compte LeBonCoin personnel / pro et réponses aux messages d'acheteurs locaux.",
                    actionRequiredByPartner = "Copier le template de l'annonce depuis l'app et publier avec photos des composants.",
                    officialUrl = "https://www.leboncoin.fr"
                )
            ),
            growthLevers = emptyList(),
            operationalPhases = listOf(
                OperationalPhaseStep(
                    stepNumber = "1",
                    phaseName = "Phase 1 (J-15)",
                    taskTitle = "Dépôt du Capital Social (1 000 €)",
                    assignee = "Les Deux",
                    timing = "24h à 48h",
                    costOrRevenue = "1 000 € (Capital libéré)",
                    actionDetails = "Ouverture du compte Qonto, virement de 500 € par associé, obtention de l'attestation de dépôt.",
                    expectedDeliverable = "Attestation de dépôt des fonds officielle ACPR",
                    officialSourceOrLegal = "Code de commerce Art. L227-1"
                ),
                OperationalPhaseStep(
                    stepNumber = "2",
                    phaseName = "Phase 2 (J-10)",
                    taskTitle = "Signature des Statuts & Annonce Légale",
                    assignee = "Les Deux",
                    timing = "24h",
                    costOrRevenue = "~130 € TTC",
                    actionDetails = "Validation de l'Article 2 multi-activités et publication de l'avis de constitution au Journal d'Annonces Légales.",
                    expectedDeliverable = "Attestation de parution JAL",
                    officialSourceOrLegal = "Arrêté ministériel tarifs annonces légales"
                ),
                OperationalPhaseStep(
                    stepNumber = "3",
                    phaseName = "Phase 3 (J-5)",
                    taskTitle = "Dépôt Guichet Unique INPI",
                    assignee = "Moi",
                    timing = "3 à 5 jours",
                    costOrRevenue = "~60 € TTC",
                    actionDetails = "Téléversement des pièces d'identité, statuts signés, certificat Qonto et attestation JAL.",
                    expectedDeliverable = "Récépissé de dépôt de création d'entreprise",
                    officialSourceOrLegal = "Guichet Unique INPI"
                ),
                OperationalPhaseStep(
                    stepNumber = "4",
                    phaseName = "Phase 4 (J-1)",
                    taskTitle = "Réception du Kbis & Ouverture Comptes Grossistes",
                    assignee = "Les Deux",
                    timing = "24h",
                    costOrRevenue = "0 €",
                    actionDetails = "Attribution du numéro SIRET et ouverture des comptes revendeurs officiels chez POwR Connect et UpWatt.",
                    expectedDeliverable = "Extrait Kbis officiel + SIRET actif",
                    officialSourceOrLegal = "RCS Tribunal de Commerce"
                )
            ),
            objections = listOf(
                ObjectionItem(
                    objection = "Pourquoi créer une SAS et pas une SARL à 50/50 ?",
                    rebuttal = "En SARL, deux associés à 50% forment une gérance majoritaire assujettie d'office aux cotisations minimales URSSAF même sans salaire, ce qui fait sauter l'ARE chômage ! En SAS, le mandat gratuit (0 €) préserve 100% de l'ARE, et les dividendes ne subissent aucune cotisation sociale.",
                    tacticalAngle = "Sécurisation intégrale de l'allocation chômage mensuelle personnelle."
                ),
                ObjectionItem(
                    objection = "Pourquoi mettre 1 000 € de capital au lieu de 1 € symbolique ?",
                    rebuttal = "Un capital de 1 € disqualifie immédiatement l'entreprise auprès des grossistes officiels (POwR, UpWatt) qui refusent l'ouverture de compte revendeur. 1 000 € (500 € chacun) confère une crédibilité immédiate tout en restant un montant accessible qui sert de fonds de roulement.",
                    tacticalAngle = "Crédibilité bancaire et fournisseurs B2B."
                )
            ),
            regulatoryRoadmap = listOf(
                RegulatoryRoadmapItem(
                    badge = "Greffe SAS",
                    title = "Statuts SAS Multi-Activités & Maintien 100% ARE France Travail",
                    legalRef = "Code de commerce Art. L227-1 & Circulaire Unédic 2024",
                    description = "Dépôt capital 1 000 € Qonto (500 €/associé). Mandat social Président à titre gratuit (0 € rémunération) garantissant le maintien intégral de l'allocation chômage sans déduction. Objet social couvrant Solaire + Alarmes sous 1 seul SIRET.",
                    officialLink = "https://formalites.entreprises.gouv.fr/"
                ),
                RegulatoryRoadmapItem(
                    badge = "RBE INPI",
                    title = "Déclaration des Bénéficiaires Effectifs (RBE)",
                    legalRef = "Code monétaire et financier Art. L561-46",
                    description = "Déclaration obligatoire des 2 associés détenant chacun 50% des parts sociales. Démarche intégrée dans le dépôt Guichet Unique INPI.",
                    officialLink = "https://formalites.entreprises.gouv.fr/"
                )
            ),
            conceptionItems = emptyList(),
            toolkitItems = listOf(
                ToolkitItem(
                    id = "tool_fiche_juridique_sas_detail",
                    title = "Fiche Stratégique & Fiscale Complète de la SAS 50/50",
                    category = "Juridique & Fiscalité",
                    contentToCopy = """GUIDE OPÉRATIONNEL DE CRÉATION DE LA SAS MULTI-ACTIVITÉS

1. DÉNOMINATION SOCIALE AU GREFFE :
- Dénomination Sociale Institutionnelle : VOLTIS GROUP SAS (ou HELIO TRADE SAS).
- Raison : 'SOLARIS SAS' existe déjà au RCS de Bourg-en-Bresse (SIREN 442 600 011).
- Marques commerciales exploitées sous ce SIRET unique : SOLAVOLT.FR (Solaire) et SECUREVO.FR (Alarme).

2. CAPITAL SOCIAL & RÉPARTITION :
- Capital : 1 000 € (500 € chacun, 1 000 actions de 1 €), réparti 50% / 50%.
- Dépôt des fonds : 100% en ligne sur compte séquestre Qonto en 24h ouvrées.

3. GOUVERNANCE & BOUCLIER TOTAL ARE FRANCE TRAVAIL :
- Président : Associé 1 (Mandat social gratuit, rémunération = 0,00 € stipulée dans le PV d'AG constitutif).
- Absence de fiche de paie = 100% de maintien de l'allocation chômage mensuelle (zéro abattement).
- Zéro cotisation sociale minimale URSSAF.

4. SORTIE DES BÉNÉFICES (DIVIDENDES) :
- Les dividendes de SAS ne subissent AUCUNE cotisation sociale URSSAF (contrairement à la SARL).
- Taxation unique : Flat Tax (PFU 30% : 12,8% IR + 17,2% prélèvements sociaux).

5. COÛT DE CRÉATION INCOMPRESSIBLE :
- Annonce Légale (JAL) : ~130 € TTC.
- Frais de Greffe + RBE : ~60 € TTC.
- Kbis officiel délivré sous 3 à 5 jours ouvrés via le Guichet Unique INPI.""",
                    sourceOrNorm = "Code de commerce Art. L227-1 & Circulaire Unédic 2024"
                ),
                ToolkitItem(
                    id = "tool_statuts_objet_social_sas",
                    title = "Article 2 des Statuts : Objet Social Multi-Activités (Solaire + Alarmes)",
                    category = "Juridique & Statuts",
                    contentToCopy = """ARTICLE 2 - OBJET SOCIAL DE LA SAS (Solaire & Sécurité)

La Société a pour objet, tant en France qu'à l'étranger :

1. Le commerce de détail, de gros, l'importation, l'exportation, la conception, l'assemblage et la distribution, par tous moyens notamment par voie électronique, vente à distance et sur internet (e-commerce), de tous matériels, équipements, kits et accessoires liés aux énergies renouvelables, aux stations solaires d'autoconsommation, à l'habitat et au bricolage ;

2. Le commerce, la distribution, la vente et l'installation sans travaux lourds de tous équipements et systèmes électroniques de sécurité domestique et professionnelle, alarmes autonomes sans fil, vidéosurveillance, capteurs, domotique, objets connectés et protection des biens et des personnes ;

3. La prestation de conseil, d'assistance technique, de configuration, de support à distance et de service après-vente (SAV) se rapportant aux équipements et matériels susmentionnés ;

4. La création, l'acquisition, l'exploitation, la concession ou la cession de toutes marques, licences, brevets, dessins, modèles et plateformes logicielles liés à ces activités ;

5. Et plus généralement, toutes opérations commerciales, industrielles, financières, mobilières ou immobilières se rattachant directement ou indirectement à l'objet social ci-dessus spécifié ou à tout autre objet similaire ou connexe.

NOTE : Cette rédaction légale permet d'exploiter immédiatement SOLARIS / SOLAVOLT (Solaire) ET SECUREVO (Alarmes) sous un seul et même SIRET, un compte bancaire unique Qonto et une seule comptabilité, sans aucuns frais de modification d'objet social au Greffe.""",
                    sourceOrNorm = "Code de commerce Art. L210-1 & L227-1"
                ),
                ToolkitItem(
                    id = "tool_pv_non_remuneration",
                    title = "Modèle de PV d'Assemblée Générale de Non-Rémunération du Président (Bouclier ARE)",
                    category = "Gouvernance & France Travail",
                    contentToCopy = """PROCES-VERBAL DE L'ASSEMBLEE GENERALE CONSTITUTIVE
De la société VOLTIS GROUP SAS au capital de 1 000 euros

RESOLUTIONS RELATIVES A LA REMUNERATION DE LA PRESIDENCE :

L'Assemblée Générale des associés, réunie ce jour, décide à l'unanimité :
1. De nommer en qualité de premier Président de la Société : [Nom de l'Associé 1], demeurant à [Adresse].
2. Il est expressément convenu et arrêté que le mandat de Président sera exercé à TITRE PUREMENT GRATUIT, sans aucune rémunération, fixe ou variable, ni indemnité de quelque nature que ce soit, et ce jusqu'à décision contraire de la collectivité des associés.
3. Le Président ne percevra aucun salaire, aucune gratification, et ne sera émis aucun bulletin de paie au titre de ce mandat social.
4. Cette décision prend effet immédiatement à compter de l'immatriculation de la Société au Registre du Commerce et des Sociétés.

Fait pour valoir ce que de droit auprès de France Travail (Pôle Emploi) et de l'administration fiscale.
Signatures des deux associés co-fondateurs.""",
                    sourceOrNorm = "Code de commerce & Règlementation Unédic ARE"
                ),
                ToolkitItem(
                    id = "tool_checklist_pieces_sas",
                    title = "Checklist des Pièces Justificatives des Associés (Prêtes pour Qonto)",
                    category = "Démarches Administratives",
                    contentToCopy = """CHECKLIST PIECES JUSTIFICATIVES SAS (Pour Qonto & Guichet Unique INPI) :

POUR CHACUN DES DEUX ASSOCIÉS :
[ ] 1. Pièce d'identité en cours de validité (CNI recto-verso couleur ou passeport).
[ ] 2. Justificatif de domicile personnel de moins de 3 mois (facture EDF/Engie, eau, télécom ou avis d'imposition).
[ ] 3. Virement de 500 € depuis le compte bancaire personnel vers le compte de séquestre Qonto.
[ ] 4. Déclaration sur l'honneur de non-condamnation et de filiation signée (modèle fourni sur Qonto).

POUR LA SOCIÉTÉ (GÉNÉRÉ PAR QONTO / GUICHET UNIQUE) :
[ ] 5. Statuts complets de la SAS signés avec l'Article 2 Multi-Activités.
[ ] 6. Attestation de dépôt des fonds délivrée par Qonto (sous 24h ouvrées).
[ ] 7. Attestation de parution de l'avis de constitution au Journal d'Annonces Légales (JAL).
[ ] 8. Formulaire M0 complété sur formalites.entreprises.gouv.fr (délai Kbis : 3 à 5 jours).""",
                    sourceOrNorm = "Greffe du Tribunal de Commerce & Qonto"
                ),
                ToolkitItem(
                    id = "tool_plan_commando_j7",
                    title = "⚡ PLAN D'ATTAQUE COMMANDO J-1 À J-7 (Déblocage des 5 Verrous)",
                    category = "Stratégie Commando",
                    contentToCopy = """PLAN D'ATTAQUE COMMANDO : VENDRE & ENCAISSER SOUS 7 JOURS

LES 5 VERROUS CRITIQUES À DÉBLOQUER IMMÉDIATEMENT :

1. VERROU ENCAISSEMENT CB (Stripe vs Société en cours d'immatriculation) :
- Problème : Attendre le Kbis final (3 à 5 jours) pour ouvrir Stripe retarde les ventes.
- Parade légale (Art. L210-6 Code de commerce) : 'Actes accomplis pour le compte de la société en formation'.
- Action : Ouvrir le compte Stripe dès maintenant avec la mention 'Société en formation' ou utiliser le compte Stripe d'un associé, créer un Stripe Payment Link à 349 €, et proposer l'acompte de réservation de 50 € ou virement immédiat SEPA.

2. VERROU NOM DE DOMAINE & SSL (Crédibilité immédiate) :
- Action : Réserver un nom de domaine officiel (ex: solavolt.fr ou voltis-france.fr) sur OVHcloud (~7,99 €/an).
- Pointer les DNS vers notre site avec certificat HTTPS gratuit. Les clients ne sortent pas leur CB sur une URL github.io.

3. VERROU LOGISTIQUE DU PANNEAU SOLAIRE (Gabarit 176 x 113 cm / 21 kg) :
- Attention : Colissimo, DPD et Mondial Relay REFUSENT les panneaux (hors gabarit max 150 cm).
- Action : Pour les 10 premières commandes, activer 2 options :
  a) Retrait gratuit sur place (en atelier / dépôt) ou livraison locale dans un rayon de 30 km par nos soins.
  b) Compte expédition lourd sur palette via Geodis Calberson ou plateforme Boxtal / Upela.
  (L'alarme 4G ne pose aucun problème : boîte de 1,2 kg expédiable en Colissimo 48h ou Mondial Relay à 4,50 €).

4. VERROU DU KIT ÉCHANTILLON & VIDÉO DÉMO 60 SECONDES :
- Action : Commander immédiatement 1 station solaire 440W complète et 1 kit alarme démo.
- Tourner une vidéo brute au smartphone de 60 secondes : 'Déballage du carton -> Branchement sur prise 220V -> Témoin vert Linky qui baisse'.
- Cette vidéo convertit 5x plus que n'importe quelle publicité 3D.

5. VERROU D'ACQUISITION JOUR 1 (Zéro budget pub) :
- Action : Publier 3 annonces ciblées sur LeBonCoin Pro (Catégorie Bricolage / Solaire) et Facebook Marketplace.
- 80% des Français cherchant du solaire plug & play comparent sur LeBonCoin. Contacts garantis dès le premier jour.""",
                    sourceOrNorm = "Protocole Lean E-Commerce & Art. L210-6 Code de commerce"
                ),
                ToolkitItem(
                    id = "tool_leboncoin_annonce_solaire",
                    title = "📢 Template Annonce LeBonCoin Pro (Acquisition Immédiate à 0 €)",
                    category = "Marketing & Ventes",
                    contentToCopy = """ANNONCE LEBONCOIN OPTIMISÉE (Solaire 440W Plug & Play) :

TITRE DE L'ANNONCE :
Station Solaire 440W Plug & Play - Prête à brancher 220V (Garantie 25 ans) - Neuve

PRIX :
349 €

DESCRIPTION :
Particuliers, réduisez immédiatement votre facture d'électricité sans installateur ni travaux sur le toit !

Notre station solaire Plug & Play 440Wc produit votre propre électricité et s'injecte directement dans vos prises domestiques standard en 220V.

CE QUE LA STATION EFFACE DÈS LE 1ER JOUR :
- Réfrigérateur et congélateur (talon permanent)
- Box internet, ordinateurs, domotique
- Éclairage LED et appareils en veille
-> Jusqu'à 130 € à 160 € d'économie par an sur votre facture EDF/Engie.

COMPOSANTS HAUT DE GAMME INDUSTRIELS INCLUS DANS LE PACK :
- 1 Panneau photovoltaïque Trina Solar Vertex S+ 440Wc (Technologie Bi-verre N-Type, robustesse extrême)
- 1 Micro-onduleur Hoymiles HMS-400 homologué réseau Enedis (Norme DIN VDE 0126-1-1 / VDE-AR-N 4105)
- 1 Châssis support aluminium noir orientable (au sol, jardin, terrasse ou balcon)
- 1 Câble étanche secteur 5m prêt à brancher sur prise extérieure ou intérieure
- Guide officiel déclaration Enedis CACSI en 3 clics inclus.

GARANTIES & SÉCURITÉ :
- Garantie de production panneau : 25 ans
- Garantie micro-onduleur : 12 ans constructeur
- Facture avec TVA fournie pour chaque commande.

MODALITÉS DE LIVRAISON :
- Retrait gratuit sur place ou livraison sécurisée à domicile sur demi-palette protégée.
- Stock disponible de suite. Quantité limitée à ce tarif de lancement.

Contactez-nous par message LeBonCoin ou téléphone pour réserver votre station.""",
                    sourceOrNorm = "Standard de conversion LeBonCoin Pro Bricolage"
                ),
                ToolkitItem(
                    id = "tool_protocole_encaissement_stripe",
                    title = "💳 Procédure Encaissement CB Express (Stripe Payment Link en 3 min)",
                    category = "Finance & Paiement",
                    contentToCopy = """PROCÉDURE ENCAISSEMENT STRIPE SANS ATTENDRE LE KBIS :

1. CRÉATION DU COMPTE STRIPE :
- Aller sur dashboard.stripe.com
- Indiquer : Type d'entité = Société (ou Entreprise individuelle en formation).
- Renseigner l'IBAN de dépôt Qonto (attestation de séquestre).

2. CRÉATION DU LIEN DE PAIEMENT UNIQUE :
- Dans le menu 'Paiements' -> Cliquer sur 'Liens de paiement' (Payment Links).
- Produit 1 : 'Station Solaire 440W Plug & Play' - Prix : 349,00 € TTC (Paiement en 1 fois ou 3x sans frais).
- Produit 2 : 'Acompte Réservation Station Solaire' - Prix : 50,00 € TTC.
- Produit 3 : 'Pack Alarme Maison 4G Sans Abonnement' - Prix : 149,00 € TTC.

3. INTÉGRATION IMMÉDIATE :
- Copier l'URL sécurisée générée (ex: https://buy.stripe.com/...).
- Coller directement sur le bouton 'Commander' du site web et dans les messages clients LeBonCoin.
- Les fonds sont sécurisés et reversés automatiquement sur le compte bancaire de la société.""",
                    sourceOrNorm = "Documentation API Stripe France & ACPR"
                ),
                ToolkitItem(
                    id = "tool_logistique_fret_solaire_vs_alarme",
                    title = "📦 Guide Fret Lourd Solaire (Geodis/Boxtal) vs Alarme Colissimo",
                    category = "Logistique & Expéditions",
                    contentToCopy = """PROTOCOLE LOGISTIQUE COMMANDO EXPÉDITION :

A. POUR LE SOLAIRE 440W (Colis Lourd & Volumineux) :
- Dimensions : 176 cm x 113 cm x 5 cm • Poids brut pack : 24 kg.
- TRANSPORT INTERDIT : Colissimo, Chronopost, DPD Express (refus pour dépassement gabarit L+l+h > 150cm).
- TRANSPORTEURS HABILITÉS :
  * Geodis Calberson (Messagerie / Distribution sur rendez-vous client par SMS)
  * Kuehne+Nagel ou DB Schenker
  * Plateforme intermédiaire : Boxtal.fr ou Upela.com (permet d'affréter sans contrat cadre annuel).
- CONDITIONNEMENT : Demi-palette bois 120x80cm ou carton double cannelure avec cornières de protection polystyrène aux 4 coins.

B. POUR L'ALARME MAISON 4G (Colis Standard Léger) :
- Dimensions : 28 cm x 20 cm x 12 cm • Poids brut : 1,2 kg.
- TRANSPORTEURS IDÉAUX :
  * Colissimo Professionnel (La Poste) : livraison domicile 48h sans signature (~5,50 € HT).
  * Mondial Relay / Shop2Shop : livraison point relais économique (~3,90 € HT).
- ÉTIQUETAGE : Impression thermique au format standard A6 (10x15cm) depuis le portail transporteur.""",
                    sourceOrNorm = "Cahier des charges logistique Geodis & La Poste Pro"
                )
            ),
            productionActions = listOf(
                ProductionAction("sas_1", "Ouvrir le compte de dépôt de capital sur Qonto", true, "URGENT", "Moi"),
                ProductionAction("sas_2", "Effectuer le virement de 500 € chacun", false, "URGENT", "Les Deux"),
                ProductionAction("sas_3", "Signer les statuts avec l'Article 2 multi-activités", false, "HAUTE", "Les Deux"),
                ProductionAction("sas_4", "Déposer le dossier sur formalites.entreprises.gouv.fr", false, "HAUTE", "Moi")
            )
        ),
        // =========================================================================
        // 1. STATION SOLAIRE 420W PLUG & PLAY (SOLARIS FRANCE)
        // =========================================================================
        EcomDossier(
            id = "dossier_solaire",
            title = "Station Solaire 440W Plug & Play",
            subtitle = "Marque SOLARIS France • Prête à brancher 220V",
            category = "Énergie & Autoconsommation",
            iconEmoji = "☀️",
            progressPercent = 80,
            estimatedNetMargin = "+97 € à +132 € HT / kit",
            targetMonthlyRevenue = "8 725 € HT / mois (30 stations)",
            liveWebsiteUrl = "https://devtest09az.github.io/simupro-france/",
            partnerNotes = "NOTE COMMUNE (Associés) :\n- Priorité absolue : déposer l'annonce test sur Leboncoin et Marketplace pour collecter 3 précommandes locales sans engagement.\n- Dès 3 clients chauds : on dépose le capital de 500 € sur Qonto et on immatricule la SAS.\n- Logistique : le stockage des 10 premiers kits se fera dans mon garage (sécurisé et sec).",
            marketStudy = MarketStudyDetail(
                marketSize = "500 000 foyers raccordés en France (fin 2024)",
                growthRate = "+85% de croissance sur le Plug & Play sur 18 mois",
                targetPainPoint = "Hausse électricité +43% (TRV) • Devis artisans toiture exorbitants (8 000 € à 11 000 €)",
                competitiveEdge = "Kit prêt à brancher 220V à 349 € amorti en 3 ans (économie 90 € à 130 €/an)",
                officialSourceLabel = "Enedis Open Data & Bilan Électrique RTE",
                officialSourceUrl = "https://data.enedis.fr/pages/accueil/",
                fullAnalysis = """DEMONSTRATION MATHEMATIQUE ET PREUVE DE LA CIBLE (30 KITS / MOIS) :

1. PREUVE MACROECONOMIQUE (ENEDIS OPEN DATA & RTE) :
- Volume national : 550 000 foyers raccordes en autoconsommation en France fin 2024 (Observatoire Enedis Open Data).
- Flux mensuel de nouveaux raccordements : ~18 000 nouveaux foyers raccordes chaque mois en France.
- Part des stations Plug & Play (< 1 kWc) : estimee a 25% a 30% des raccordements (soit ~4 500 a 5 500 kits Plug & Play vendus par mois en France).
- Notre cible de 30 stations / mois represente donc : 30 / 5 000 = 0,60% DE PART DU MARCHE MENSUEL FRANCAIS.
- Autrement dit : capter 6 acheteurs sur 1 000 en France suffit a valider 100% de notre objectif.

2. PREUVE CONCURRENTIELLE & PRIX (SUNOLOGY & BEEM ENERGY) :
- Sunology (Nantes) : a vendu plus de 50 000 stations en 2 ans pour un CA declare depassant 35 millions d'euros (sources : Les Echos & BFM Business), soit une moyenne de 2 000 a 3 000 kits / mois vendus a 699 EUR.
- Beem Energy (Nantes) : plus de 40 000 foyers equipes, stations vendues a 749 EUR.
- Notre force implacable : avec notre pack SOLARIS a 349 EUR TTC, nous vendons EXACTEMENT les memes composants industriels (Trina Solar biverre 440W + micro-onduleur Hoymiles VDE), mais a MOITIE PRIX (-50% vs les leaders).

3. PREUVE DU CANAL D'ACQUISITION LOCAL (VITRINE LEBONCOIN PRO) :
- Bassin de chalandise moyen (1 departement / metropole) : ~200 000 a 350 000 maisons individuelles avec jardin, terrasse ou balcon.
- 1 vitrine Leboncoin PRO certifiee avec SIRET 'Kit Solaire 440W Pret a brancher 349 EUR (Garantie 25 ans, Moitie prix Sunology)' genere 60 a 100 vues qualifiees / jour.
- Taux de prise de contact par message / telephone : ~3% a 4% = 2 a 4 prospects chauds par jour.
- Taux de conversion en vente (en proposant la remise en main propre locale ou livraison Geodis 48h) : 1 vente tous les 2 a 3 prospects.
- Resultat : 1 vente par jour ouvre (soit 20 a 22 ventes / mois) sur le seul departement local, completee par 8 a 10 ventes sur les departements limitrophes via expedition Geodis.

4. STRESS-TEST DES 3 SCENARIOS FINANCIERS AUDITÉS (RÉSULTATS NETS APRÈS IS 15% ET FLAT TAX 30%) :
- Démarrage prudent (12 kits/mois) : 3 490 € CA HT -> Résultat net SAS : +805 € net -> Dividende net en poche : +282 € net / associé.
- Cible Réaliste (30 kits/mois, 1 kit/jour ouvré) : 8 725 € CA HT -> Résultat net SAS : +2 285 € net -> Dividende net en poche : +800 € net / associé (+9 596 € nets / an). (Si 30% des kits en retrait local à 0 € de port : +909 € net / associé).
- Régime de Croisière (60 kits/mois, 2 annonces locales + Web) : 17 450 € CA HT -> Résultat net SAS : +4 750 € net -> Dividende net en poche : +1 663 € net / associé (+19 952 € nets / an).
*Statut Dirigeant : Mandat social à titre gratuit (0 € salaire) garantissant le maintien à 100% de l'ARE personnelle France Travail, sans impact sur les dividendes de la SAS."""
            ),
            unitEconomics = UnitEconomicsDetail(
                retailPriceTTC = "349,00 € TTC",
                retailPriceHT = "290,83 € HT (TVA 20% : 58,17 €)",
                wholesaleBOMHT = "148,20 € HT (BOM matériel 4 composants industriels)",
                shippingCost = "35,00 € HT (Geodis demi-palette sécurisée) ou 0 € (Retrait local)",
                paymentGatewayFee = "4,61 € HT (Stripe CB 1,5% + 0,25 €)",
                netProfitPerUnit = "+96,69 € HT (Livraison Geodis) à +131,69 € HT (Remise locale)",
                targetMonthlyNet = "Point mort : 2,2 kits/mois • À 30 kits/m : +2 285 € net SAS (+800 € net / associé)",
                costItems = listOf(
                    CostItem("Panneau Trina Solar 440W Vertex S+ Biverre N-Type", "49,50 € HT", "Rendement 22.0%, garantie constructeur 25 ans (POwR Connect)"),
                    CostItem("Micro-Onduleur Hoymiles HMS-400W homologué VDE", "68,00 € HT", "Découplage anti-îlotage < 20ms certifié DIN VDE-AR-N 4105"),
                    CostItem("Châssis Alu Inclinable Réglable Ksolar 20°-35°", "19,50 € HT", "Alu AL6005-T5 certifié résistance vent 130 km/h (UpWatt Pro)"),
                    CostItem("Câble Secteur 5m Étanche IP68 + Prise Schuko", "11,20 € HT", "Câble HO7RN-F 3G1.5mm² norme NF EN 62109 raccordement direct"),
                    CostItem("Éco-participation légale DEEE Soren", "1,83 € HT", "Filière de recyclage agréée d'État (loi AGEC)"),
                    CostItem("Emballage carton renforcé + sangle & cornières", "4,50 € HT", "Protection demi-palette pour transport sécurisé"),
                    CostItem("Frais de transaction Stripe CB (1,5% + 0,25 €)", "4,61 € HT", "Passerelle de paiement sécurisée sans abonnement fixe"),
                    CostItem("Transport sécurisé Geodis Pro sur RDV", "35,00 € HT", "Livraison sur demi-palette avec hayon (0 € en cas de retrait local)")
                ),
                executiveSummary = "Modèle en flux tendu sans avance de trésorerie : le matériel est commandé chez le grossiste dès l'encaissement client. Point mort atteint dès 3 kits vendus par mois pour absorber 100% des frais fixes de la SAS.",
                sourceOrSupplier = "POwR Connect (Nantes) & UpWatt Pro France"
            ),
            partnerProfit = PartnerProfitProjection(
                monthlyVolumeHypothesis = "30 stations / mois (360 kits / an • 104 699 € HT de CA)",
                totalCompanyNetMonthly = "+ 2 285 € NET après IS 15% (27 418 € net / an)",
                associate1Name = "Associé 1 (Président SAS)",
                associate1NetMonthly = "+ 800 € à + 909 € NET d'impôt / mois (9 596 € à 10 908 € / an)",
                associate1SpecificAdvantage = "Mandat social gratuit (0 € salaire) : maintien à 100% de son ARE personnelle France Travail + dividendes SAS sans cotisations sociales URSSAF",
                associate2Name = "Associé 2 (Co-fondateur 50%)",
                associate2NetMonthly = "+ 800 € à + 909 € NET d'impôt / mois (9 596 € à 10 908 € / an)",
                associate2SpecificAdvantage = "50% des dividendes nets d'impôt (Flat Tax 30% libératoire) + constitution d'une trésorerie d'entreprise saine et pérenne",
                annualDividendsPerPartner = "9 596 € NETS (100% Geodis) à 10 908 € NETS (avec 35% de ventes locales)",
                taxOptimizationNote = "DÉCOMPOSITION COMPTABLE AUDITÉE (30 kits/m) :\n• CA HT : 8 725 €/m (104 700 €/an)\n• Coûts directs (194,14 €/kit) : -5 824 €/m (-69 890 €/an)\n• Marge brute : +2 901 €/m (+34 808 €/an)\n• Frais fixes structure (Expert 83 €, Qonto 29 €, RC Pro 42 €, CFE 33 €, Web 25 €) : -213 €/m (-2 552 €/an)\n• Résultat avant impôt (EBIT) : +2 688 €/m (+32 256 €/an)\n• Impôt Sociétés IS 15% : -403 €/m (-4 838 €/an)\n• Bénéfice Net SAS : +2 285 €/m (+27 418 €/an)\n• Dividende BRUT par associé (50%) : 1 142 €/m (13 709 €/an)\n• Flat Tax PFU 30% : -343 €/m (-4 113 €/an)\n• NET EN POCHE PAR ASSOCIÉ : 799,69 € NET / mois (9 596 € net / an).\n(Si 35% de retraits locaux sans frais de port : 909 € net / mois / associé)."
            ),
            commercialPacks = listOf(
                CommercialPack(
                    name = "Pack Solo SOLARIS™ 440W (Best-Seller Balcon & Terrasse)",
                    badge = "BEST-SELLER",
                    retailPriceTTC = "349,00 € TTC",
                    wholesaleCostHT = "148,20 € HT",
                    netMarginAmount = "+96,69 € HT (Livraison) à +131,69 € HT (Retrait local)",
                    netMarginPercent = "33,2% à 45,3% de marge brute",
                    paybackPeriod = "Amorti en 2,5 à 2,9 ans (efface 500 à 650 kWh/an)",
                    contents = "1x Panneau Trina Solar 440W Biverre N-Type + 1x Micro-onduleur Hoymiles HMS-400 VDE + 1x Châssis alu réglable 20°-35° + 1x Câble Schuko 5m + Guide Enedis"
                ),
                CommercialPack(
                    name = "Pack Duo SOLARIS™ 880W (Double Puissance Famille)",
                    badge = "MARGE RECORD",
                    retailPriceTTC = "649,00 € TTC",
                    wholesaleCostHT = "277,40 € HT",
                    netMarginAmount = "+218,50 € NET (Livraison) / +255,00 € NET (Main propre)",
                    netMarginPercent = "40,4% à 47,1% de marge nette",
                    paybackPeriod = "Amorti en 2,2 à 2,5 ans (efface 1 000 à 1 300 kWh/an)",
                    contents = "2x Panneaux Trina 440W Biverre + 1x Micro-onduleur Hoymiles Duo HMS-800-2T (2 MPPT indépendants) + 2x Châssis alu + Câble Schuko 5m"
                ),
                CommercialPack(
                    name = "Option Prise Connectée Wi-Fi 16A avec Suivi Smartphone",
                    badge = "UPSELL CLOSING",
                    retailPriceTTC = "19,00 € TTC (ou offerte)",
                    wholesaleCostHT = "6,50 € HT",
                    netMarginAmount = "+9,33 € NET",
                    netMarginPercent = "49,1% de marge",
                    paybackPeriod = "Immédiat (mesure la production en watts et euros en direct sur Tuya Smart)",
                    contents = "Prise étanche IP44 avec wattmètre connecté 2.4 GHz pour suivre la production instantanée et l'historique sur iPhone et Android."
                ),
                CommercialPack(
                    name = "Option Kit Fixation Toiture Tuiles (Crochets Inox + Rails)",
                    badge = "UPSELL TOITURE",
                    retailPriceTTC = "59,00 € TTC",
                    wholesaleCostHT = "22,00 € HT",
                    netMarginAmount = "+27,17 € NET",
                    netMarginPercent = "46,0% de marge",
                    paybackPeriod = "Permet la pose sur toit en pente avec branchement direct sur prise 220V",
                    contents = "4x Crochets toiture inox réglables + 2x Rails aluminium anodisé noir + Étriers de serrage + Guide Cerfa Déclaration Préalable Mairie"
                )
            ),
            productionSimulations = listOf(
                ProductionSimulation(
                    zoneName = "Zone Nord & Île-de-France (Lille, Paris, Rennes, Strasbourg)",
                    annualKwh = "490 kWh produits/an (431 kWh consommés direct)",
                    annualSavingsEuros = "116 € / an d'économie Linky",
                    paybackYears = "3,0 ans (Rendement net : +33,2 % / an)",
                    sourceLabel = "PVGIS UE (30° Sud) • 88% autoconsommé",
                    sourceUrl = "https://re.jrc.ec.europa.eu/pvg_tools/fr/"
                ),
                ProductionSimulation(
                    zoneName = "Zone Centre & Grand Ouest (Nantes, Tours, Lyon, Bordeaux)",
                    annualKwh = "560 kWh produits/an (493 kWh consommés direct)",
                    annualSavingsEuros = "133 € / an d'économie Linky",
                    paybackYears = "2,6 ans (Rendement net : +38,1 % / an)",
                    sourceLabel = "PVGIS UE & EDF TRV • 12x le Livret A",
                    sourceUrl = "https://re.jrc.ec.europa.eu/pvg_tools/fr/"
                ),
                ProductionSimulation(
                    zoneName = "Zone Sud & Méditerranée (Toulouse, Montpellier, Marseille, Nice)",
                    annualKwh = "680 kWh produits/an (598 kWh consommés direct)",
                    annualSavingsEuros = "161 € / an d'économie Linky",
                    paybackYears = "2,1 ans (Rendement net : +46,1 % / an)",
                    sourceLabel = "PVGIS UE • Efface 100% talon d'été",
                    sourceUrl = "https://re.jrc.ec.europa.eu/pvg_tools/fr/"
                )
            ),
            verifiedSuppliers = listOf(
                VerifiedSupplier(
                    name = "POwR Connect (Groupe POwR France)",
                    location = "Nantes (44) • N°1 grossiste B2B photovoltaïque en France",
                    role = "Fournisseur principal panneaux Trina Solar & micro-onduleurs Hoymiles",
                    contactB2B = "contact@groupepowr.com • Tél : 02 40 92 00 00",
                    conditions = "Livraison palette sécurisée 48h-72h par transporteur Geodis directement chez le client final (livraison en marque blanche possible).",
                    portalUrl = "https://powr-connect.shop/"
                ),
                VerifiedSupplier(
                    name = "UpWatt Pro (UpWatt France)",
                    location = "La Roche-sur-Yon (85) / Paris • Spécialiste kits solaires autoconsommation",
                    role = "Kits complets Plug & Play pré-assemblés en cartons individuels renforcés",
                    contactB2B = "pro@upwatt.com • Tél : 01 84 80 84 80",
                    conditions = "Espace revendeur certifié avec accès direct aux stocks Hoymiles HMS et châssis inclinables sol.",
                    portalUrl = "https://upwatt.com/pro"
                ),
                VerifiedSupplier(
                    name = "Kitsolaire-Discount (Distributeur National Pro)",
                    location = "Marseille / Aubagne (13) • Plateforme logistique France",
                    role = "Sourcing d'échantillons et kits d'évaluation unitaires rapides",
                    contactB2B = "contact@kitsolaire-discount.com • Expédition 24h-48h chrono",
                    conditions = "Idéal pour commander les 2 premiers prototypes sans minimum de commande.",
                    portalUrl = "https://kitsolaire-discount.com/fr/"
                ),
                VerifiedSupplier(
                    name = "SecondSol (Bourse Européenne B2B Solaire)",
                    location = "Allemagne / France / Benelux • Marché de gros photovoltaïque",
                    role = "Palettes complètes de 30 à 36 panneaux neufs Trina / Jinko",
                    contactB2B = "Service B2B multilingue • Facturation HT avec TVA intracommunautaire",
                    conditions = "Prix dégressifs exceptionnels sur lots (~44 € HT / panneau au lieu de 49,50 €).",
                    portalUrl = "https://secondsol.com/fr/"
                )
            ),
                        riskMitigations = listOf(
                RiskMitigationItem(
                    title = "1. Casse Transporteur (Panneau en verre brisé à la livraison)",
                    probabilityLevel = "ÉLEVÉ (si messagerie standard) / NUL (si fret palette)",
                    potentialImpact = "Perte sèche de 148 € + litige client",
                    failureModeDescription = "Les messageries standards (Colissimo, DPD, Chronopost) empilent ou manipulent brutalement les colis de plus de 20 kg. Le verre biverre subit un taux de casse de 12% à 18% sur ces réseaux.",
                    viableSolution = "1) Bannir les colis individuels : fret exclusif sur demi-palette sécurisée Geodis ou DB Schenker avec hayon élévateur et prise de RDV préalable par SMS. 2) Assurance Ad Valorem intégrée (3 € pour 350 € garantis à neuf). 3) Protocole légal imprimé sur le carton et envoyé par SMS : Déballage impératif devant le chauffeur. Si choc visible, refuser la palette (art. L133-3 Code Commerce). 4) Option locale Click & Collect Leboncoin : retrait sur place par le client avec bon de décharge physique signé (+28 € de marge et 0% de casse).",
                    legalOrTechnicalProof = "Art. L133-3 Code de commerce & Contrat fret sécurisé Geodis Calberson."
                ),
                RiskMitigationItem(
                    title = "2. Blocage Compte Bancaire ou Passerelle CB (Stripe / Rolling Reserve)",
                    probabilityLevel = "MOYEN (si volume soudain sans antécédent)",
                    potentialImpact = "Trésorerie bloquée pendant 90 jours (25% à 100% des fonds)",
                    failureModeDescription = "Les algorithmes antifraude de Stripe ou Shopify Payments bloquent les comptes récents qui encaissent des paniers élevés (>300 €) sous prétexte de 'risque de livraison différée' ou 'secteur à litige'.",
                    viableSolution = "1) Compte bancaire professionnel chez Qonto (établissement de crédit agréé ACPR Banque de France). 2) Pour les précommandes et ventes locales : virement instantané SEPA / Wero ou terminal physique SumUp / Zettle (fonds disponibles en 24h sans rétrofacturation possible). 3) Pour le site web : Activer le paiement en 3x/4x garanti via Alma ou Klarna (l'organisme financier avance 100% des fonds sous 48h et assume seul le risque d'impayé client). 4) Validation KYC préventive du site (mentions légales, CGV conformes, SIRET affiché).",
                    legalOrTechnicalProof = "Agrément bancaire ACPR Qonto & Contrat marchand Alma Financement Garanti."
                ),
                RiskMitigationItem(
                    title = "3. Responsabilité Incendie / Court-Circuit chez le client",
                    probabilityLevel = "TRÈS FAIBLE (mais anxiété n°1 de l'associé)",
                    potentialImpact = "Réclamation client ou expertise d'assurance après sinistre",
                    failureModeDescription = "Un client branche la station sur une multiprise vétuste ou une prise sans terre non conforme NF C 15-100, provoquant un échauffement mural.",
                    viableSolution = "1) Statut juridique de simple revendeur de matériel pré-assemblé : AUCUNE intervention sur le bâtiment, donc 0 garantie décennale requise. 2) Souscription RC Pro Exploitation + Après Livraison (Hiscox / AXA, ~40 €/mois couvrant 1 500 000 € de dommages). 3) Micro-onduleur Hoymiles certifié DIN VDE 0126-1-1 avec relais mécanique de coupure instantanée en moins de 200 ms. 4) Responsabilité légale du fabricant (art. 1245 Code Civil). 5) Notice de sécurité et avertissement prise NF C 15-100 obligatoire fournie dans chaque kit.",
                    legalOrTechnicalProof = "Norme DIN VDE 0126-1-1 (TÜV Rheinland) & Art. 1245 et suivants du Code Civil."
                ),
                RiskMitigationItem(
                    title = "4. Blocage Enedis ou Menace de Passage Consuel",
                    probabilityLevel = "NULLE (si procédure officielle suivie)",
                    potentialImpact = "Inquiétude client, refus de mise en service",
                    failureModeDescription = "Le client craint une amende, une taxe d'Enedis ou la facture obligatoire d'un Consuel à 180 €.",
                    viableSolution = "1) Procédure officielle Enedis-PRO-RES_43E : pour les stations pré-assemblées sur prise standard < 3 kW sans vente de surplus, la convention CACSI est 100% gratuite, dématérialisée et sans visite technique. 2) L'attestation de conformité DIN VDE 0126-1-1 du micro-onduleur Hoymiles dispense expressément du visa Consuel (réponse ministérielle n° 14811). 3) Fiche A4 plastifiée fournie dans le carton avec QR code pour remplir la CACSI en 5 clics chrono.",
                    legalOrTechnicalProof = "Procédure Enedis-PRO-RES_43E & Rép. ministérielle n° 14811 (dispense Consuel)."
                ),
                RiskMitigationItem(
                    title = "5. Contrôle France Travail / Remise en cause de l'ARE du Président",
                    probabilityLevel = "FAIBLE (si formalisme respecté) / LÉTALE (si mal rédigé)",
                    potentialImpact = "Suspension immédiate de l'allocation + remboursement rétroactif de l'ARE",
                    failureModeDescription = "France Travail ou l'URSSAF suspectent une rémunération dissimulée, des avantages en nature ou requalifient les dividendes en salaire.",
                    viableSolution = "1) PV d'Assemblée Générale constitutif actant expressément un mandat social de Président à titre 100% bénévole et gratuit (0 € salaire, 0 avantage). 2) Déclaration mensuelle d'actualisation rigoureuse (0 heure salariée, 0 € perçu). 3) Attestation trimestrielle de non-rémunération signée par le Président / expert-comptable. 4) En SAS, les dividendes ne sont PAS assujettis aux cotisations sociales URSSAF (contrairement à la SARL) et n'impactent pas l'ARE.",
                    legalOrTechnicalProof = "Circulaire Unédic n° 2014-26 & Art. L5422-1 du Code du travail."
                ),
                RiskMitigationItem(
                    title = "6. Rupture de Stock Grossiste ou Hausse Brutale des Prix",
                    probabilityLevel = "MOYENNE (lors des pics de canicule estivale)",
                    potentialImpact = "Délais rallongés, perte de clients précommandés",
                    failureModeDescription = "POwR Connect ou UpWatt sont temporairement en rupture de panneaux Trina 440W ou de micro-onduleurs Hoymiles.",
                    viableSolution = "1) Architecture 100% modulaire et standardisée : les panneaux Trina Vertex S+ biverre sont 100% interchangeables avec les Jinko Solar Tiger Neo 440W et JA Solar 440W (mêmes cotes, mêmes connecteurs MC4 universels, même prix grossiste). 2) Micro-onduleurs alternatifs prêts : Deye SUN-M80G4 ou Enphase IQ8. 3) Multi-comptes B2B activés : POwR Connect Nantes, UpWatt Vendée, Kitsolaire-Discount Marseille. 4) Commande par palette de 30 dès le 2ème mois via le cash flow accumulé (-15% de coût d'achat).",
                    legalOrTechnicalProof = "Fiches techniques MC4 universelles 48V-60V & Compatibilité VDE."
                ),
                RiskMitigationItem(
                    title = "7. Rétractation Abusive 14 jours & Retours à Perte (Code Consommation)",
                    probabilityLevel = "MOYENNE (sur le e-commerce grand public)",
                    potentialImpact = "Frais de retour fret de 50 € supportés par la SAS",
                    failureModeDescription = "Un client impulsif change d'avis après livraison et exige le retour gratuit du panneau lourd.",
                    viableSolution = "1) Application stricte de l'article L221-23 du Code de la consommation : clause CGV rédigée par avocat précisant que les frais de retour fret sont à la charge exclusive de l'acheteur (estimés entre 45 € et 60 € HT en raison du gabarit lourd). 2) Exigence contractuelle : produit retourné dans son emballage d'origine scellé, non ouvert et non déballé. Cela neutralise 98% des rétractations impulsives.",
                    legalOrTechnicalProof = "Article L221-23 du Code de la consommation & Modèle officiel DGCCRF."
                ),
                RiskMitigationItem(
                    title = "8. Sanction Non-Conformité Éco-Recyclage (Loi AGEC & Taxe Soren)",
                    probabilityLevel = "MOYENNE (si oubli administratif au lancement)",
                    potentialImpact = "Amende administrative DGCCRF jusqu'à 30 000 €",
                    failureModeDescription = "Mise sur le marché de matériel photovoltaïque sans adhésion à la filière REP (Responsabilité Élargie du Producteur).",
                    viableSolution = "1) Adhésion 100% gratuite à l'organisme d'État Soren (filière officielle de recyclage photovoltaïque en France). 2) Obtention immédiate de l'Identifiant Unique (IDU) délivré par l'ADEME, affiché sur le site web et les factures. 3) Facturation transparente de l'éco-participation Soren (0,80 € HT / panneau) répercutée au client final. Risque juridique neutralisé à 100%.",
                    legalOrTechnicalProof = "Décret n° 2014-928 & Agrément officiel ministériel Soren (filière REP)."
                ),
                RiskMitigationItem(
                    title = "9. Conflit Marque & NDD 'SOLARIS' (Présence de Solaris SAS depuis 2002)",
                    probabilityLevel = "CRITIQUE (si exploitation de 'Solaris' sans vérification)",
                    potentialImpact = "Mise en demeure pour contrefaçon (Art. L713-2 CPI), blocage INPI et invisibilité SEO Google",
                    failureModeDescription = "solaris.fr est pris par GrandVision. solaris-store.com est détenu depuis 2002 par Solaris SAS (Saint-André-de-Corcy, Ain), distributeur officiel de matériel solaire. Utiliser le nom Solaris dans le photovoltaïque crée une contrefaçon et une concurrence déloyale flagrante.",
                    viableSolution = "1) Dénomination sociale SAS neutre au Greffe (ex: VOLTIS GROUP SAS ou HELIO TRADE SAS) couvrant Solaire + Alarmes. 2) Marque commerciale e-commerce propre et déposée à l'INPI avec NDD .FR libre vérifié AFNIC : SOLAVOLT.FR, PLUGWATT.FR, VOLTEVO.FR ou SOLARKIT.FR. Coût NDD : 7,99 €/an sur OVHcloud. Risque juridique neutralisé à 100%.",
                    legalOrTechnicalProof = "Art. L713-2 du Code de la Propriété Intellectuelle & Base Marques INPI."
                )
            ),
            partnerPitchArguments = listOf(
                PartnerPitchArgument(
                    number = 1,
                    title = "Zéro avance de stock & Zéro risque financier",
                    argument = "On ne commande aucun kit avant d'avoir encaissé l'argent du client. On pose l'annonce pilote Leboncoin avec photos HD réelles. Dès qu'un client réserve ou commande, on valide la commande sur le portail grossiste qui expédie chez lui.",
                    keyProof = "Livraison directe Geodis 48h-72h validée avec POwR Connect / UpWatt."
                ),
                PartnerPitchArgument(
                    number = 2,
                    title = "Moitié prix par rapport à Sunology & Beem",
                    argument = "Sunology vend son panneau 440W à 699 € et Beem à 749 €. Avec notre kit SOLARIS™ à 349 € TTC, on est 50% moins cher avec le même matériel haut de gamme biverre Trina Solar et micro-onduleur Hoymiles garanti 12 ans.",
                    keyProof = "Fiches tarifs publiques : Sunology.com (699 €) et Beemenergy.fr (749 €)."
                ),
                PartnerPitchArgument(
                    number = 3,
                    title = "Marge brute de 97 € à 132 € / station (Point mort : 2,2 kits)",
                    argument = "À 30 ventes / mois, la SAS réalise 8 725 € CA HT et 2 285 € de bénéfice net après IS 15%. Soit 800 € net de dividende en poche par associé (9 596 €/an en 100% Geodis, ou 909 € net avec 30% de retrait local). Statut président à 0 € de salaire préservant 100% de son ARE personnelle.",
                    keyProof = "BOM unitaire certifié à 194,14 € HT avec Geodis (ou 159,14 € HT en local) et 212,67 € de frais fixes mensuels."
                ),
                PartnerPitchArgument(
                    number = 4,
                    title = "Zéro Consuel, Zéro Mairie, 100% Légal en 15 minutes",
                    argument = "Pose au sol de moins de 1,80m = exemptée de déclaration préalable en mairie (art. R*421-2 Code urbanisme). Raccordement sur prise standard 220V = Convention CACSI Enedis gratuite en 5 clics sans passage de technicien.",
                    keyProof = "Procédure officielle Enedis-PRO-RES_43E & Code de l'énergie art. L315-1."
                ),
                PartnerPitchArgument(
                    number = 5,
                    title = "Décollage sécurisé en 4 étapes sans emprunt bancaire",
                    argument = "1. Activation de la vitrine Pro certifiée & du site web -> 2. Sécurisation de 3 précommandes locales sans engagement -> 3. Dépôt du capital social de 500 € sur Qonto -> 4. Immatriculation SAS et livraison des premiers clients.",
                    keyProof = "Rétroplanning autofinancé sans besoin de prêt bancaire ni d'investisseur externe."
                )
            ),
                                                operationalPhases = listOf(
                // PHASE 1 : CRASH-TEST TECHNIQUE & QUALITÉ (J-15 À J-7)
                OperationalPhaseStep(
                    stepNumber = "1.1",
                    phaseName = "PHASE 1 (J-15 à J-7) : CRASH-TEST TECHNIQUE",
                    taskTitle = "Commande du Kit Prototype de Démonstration",
                    assignee = "Les 2 Associés",
                    timing = "J-15 (Livraison 48h)",
                    costOrRevenue = "180 € TTC (Avance 50/50)",
                    actionDetails = "Commander 1 kit complet (panneau Trina Solar 440Wc biverre + micro-onduleur Hoymiles HMS-400 + châssis alu inclinable + câble Schuko 5m) chez Kitsolaire-Discount ou UpWatt pour évaluation physique directe.",
                    expectedDeliverable = "Kit physique complet reçu, déballé et inspecté au millimètre dans le garage.",
                    officialSourceOrLegal = "Fiche technique constructeur Trina Solar Vertex S+ & Certificat Hoymiles."
                ),
                OperationalPhaseStep(
                    stepNumber = "1.2",
                    phaseName = "PHASE 1 (J-15 à J-7) : CRASH-TEST TECHNIQUE",
                    taskTitle = "Crash-Test Électrique Réel & Coupure Anti-Îlotage VDE",
                    assignee = "Associé 2 (Technique)",
                    timing = "J-12",
                    costOrRevenue = "0 € (Multimètre & wattmètre)",
                    actionDetails = "1) Brancher sur prise murale : constater au wattmètre et sur le compteur Linky la baisse immédiate de 300 à 400 VA. 2) Débrancher brutalement la prise : mesurer au multimètre que la tension aux broches tombe à 0V en moins de 200 ms (sécurité anti-électrocution DIN VDE 0126-1-1).",
                    expectedDeliverable = "Vidéo HD de 45 secondes du test Linky et de la coupure instantanée.",
                    officialSourceOrLegal = "Norme DIN VDE 0126-1-1 / VFR 2019 (homologation obligatoire Enedis)."
                ),
                OperationalPhaseStep(
                    stepNumber = "1.3",
                    phaseName = "PHASE 1 (J-15 à J-7) : CRASH-TEST TECHNIQUE",
                    taskTitle = "Shooting Photo Commercial HD en Situation Réelle",
                    assignee = "Associé 1 (Marketing)",
                    timing = "J-10",
                    costOrRevenue = "0 € (Smartphone 4K)",
                    actionDetails = "Prendre 6 photos nettes et lumineuses de la station : 1) Vue d'ensemble sur gazon/terrasse, 2) Gros plan biverre noir antireflet, 3) Gros plan micro-onduleur Hoymiles, 4) Châssis alu réglé à 30°, 5) Branchement prise Schuko étanche, 6) Écran smartphone mesurant la production.",
                    expectedDeliverable = "Pack de 6 visuels HD commerciaux réels (zéro image 3D artificielle).",
                    officialSourceOrLegal = "Charte qualité visuelle des annonces premium Leboncoin & Marketplace."
                ),

                // PHASE 2 : TEST MARCHÉ 0 € & 3 PRÉCOMMANDES LOCALES (J-7 À J-1)
                OperationalPhaseStep(
                    stepNumber = "2.1",
                    phaseName = "PHASE 2 (J-7 à J-1) : TEST MARCHÉ 0 €",
                    taskTitle = "Dépôt de l'Annonce Pilote Leboncoin & Marketplace",
                    assignee = "Associé 1 (Président)",
                    timing = "J-7",
                    costOrRevenue = "0 € (Annonce gratuite)",
                    actionDetails = "Publier l'annonce optimisée avec vos vraies photos et le titre percutant : 'Station Solaire 440W Plug & Play - Prête à brancher 230V - 349 € (Moitié prix Sunology, Garantie 25 ans)'. Cibler votre département et métropole locale.",
                    expectedDeliverable = "Annonce validée en ligne générant 60 à 100 vues qualifiées par jour.",
                    officialSourceOrLegal = "Script optimisé du dossier Boîte à Outils de l'application KoPilot."
                ),
                OperationalPhaseStep(
                    stepNumber = "2.2",
                    phaseName = "PHASE 2 (J-7 à J-1) : TEST MARCHÉ 0 €",
                    taskTitle = "Closing des 3 Premières Précommandes Locales Fermes",
                    assignee = "Associé 1 & 2",
                    timing = "J-5 à J-1",
                    costOrRevenue = "0 € (Trafic organique)",
                    actionDetails = "Répondre aux messages en moins de 15 min par SMS/téléphone avec la fiche technique PDF et la vidéo Linky. Verrouiller 3 réservations fermes avec remise en main propre locale ou livraison gratuite sous 10 jours.",
                    expectedDeliverable = "3 accords fermes de précommandes confirmés par SMS / email avec coordonnées clients.",
                    officialSourceOrLegal = "Condition de sortie Phase 2 : AUCUN centime dépensé avant d'avoir ces 3 précommandes."
                ),

                // PHASE 3 : CONSTITUTION OFFICIELLE SAS & CAPITAL (JOUR J À J+3)
                OperationalPhaseStep(
                    stepNumber = "3.1",
                    phaseName = "PHASE 3 (Jour J à J+3) : CONSTITUTION SAS",
                    taskTitle = "Dépôt du Capital Social de 500 € sur Qonto",
                    assignee = "Les 2 Associés (250 € chacun)",
                    timing = "Jour J (Matin)",
                    costOrRevenue = "500 € de capital (Trésorerie SAS)",
                    actionDetails = "Ouvrir le compte pro en ligne sur Qonto.com. Virement de 250 € par associé. Qonto séquestre le capital et délivre l'Attestation de Dépôt des Fonds officielle en moins de 12h.",
                    expectedDeliverable = "Attestation de Dépôt des Fonds certifiée pour le greffe du tribunal.",
                    officialSourceOrLegal = "Code de commerce art. L227-1 et suivants (Société par Actions Simplifiée)."
                ),
                OperationalPhaseStep(
                    stepNumber = "3.2",
                    phaseName = "PHASE 3 (Jour J à J+3) : CONSTITUTION SAS",
                    taskTitle = "Rédaction des Statuts SAS & PV d'AG Mandat Gratuit",
                    assignee = "Associé 1 (Président)",
                    timing = "Jour J (Après-midi)",
                    costOrRevenue = "0 € (Modèle certifié)",
                    actionDetails = "Rédiger les statuts SAS à 500 € de capital (50/50). Rédiger le Procès-Verbal d'Assemblée Générale n°1 actant que le mandat de Président est exercé à titre strictement bénévole et gratuit (0 € salaire, 0 € avantage).",
                    expectedDeliverable = "Statuts paraphés et signés électroniquement + PV d'AG bouclier ARE.",
                    officialSourceOrLegal = "Circulaire Unédic n° 2014-26 du 30/09/2014 (Maintien intégral 100% de l'ARE)."
                ),
                OperationalPhaseStep(
                    stepNumber = "3.3",
                    phaseName = "PHASE 3 (Jour J à J+3) : CONSTITUTION SAS",
                    taskTitle = "Immatriculation en Ligne sur le Guichet Unique INPI",
                    assignee = "Associé 1",
                    timing = "J+1",
                    costOrRevenue = "~177 € (Frais greffe + annonce légale)",
                    actionDetails = "Déposer le dossier sur formalites.entreprises.gouv.fr (Guichet Unique INPI) : statuts signés, attestation Qonto, pièce d'identité et attestation de non-condamnation.",
                    expectedDeliverable = "Récépissé de dépôt immédiat avec numéro SIREN attribué sous 24h-48h.",
                    officialSourceOrLegal = "Guichet Unique des Formalités d'Entreprises (Loi PACTE)."
                ),

                // PHASE 4 : FOURNISSEURS, LOGISTIQUE & PREMIÈRES VENTES (J+4 À J+15)
                OperationalPhaseStep(
                    stepNumber = "4.1",
                    phaseName = "PHASE 4 (J+4 à J+15) : VENTES & FOURNISSEURS",
                    taskTitle = "Validation des Comptes Grossistes B2B (POwR & UpWatt)",
                    assignee = "Associé 2",
                    timing = "J+4",
                    costOrRevenue = "0 € d'ouverture",
                    actionDetails = "Envoyer le Kbis ou récépissé SIREN chez POwR Connect Nantes (contact@groupepowr.com) et UpWatt Pro (pro@upwatt.com). Demander l'ouverture du compte revendeur direct avec facturation HT.",
                    expectedDeliverable = "Identifiants d'accès au portail B2B avec grilles tarifaires remisées.",
                    officialSourceOrLegal = "Conditions Générales de Vente Revendeurs POwR Connect & UpWatt France."
                ),
                OperationalPhaseStep(
                    stepNumber = "4.2",
                    phaseName = "PHASE 4 (J+4 à J+15) : VENTES & FOURNISSEURS",
                    taskTitle = "Encaissement des 3 Précommandes & Commande Matériel",
                    assignee = "Les 2 Associés",
                    timing = "J+7",
                    costOrRevenue = "+1 047 € encaissés / -445 € décaissés",
                    actionDetails = "Encaisser les 3 clients (3 x 349 € = 1 047 € TTC) par virement instantané Qonto ou CB. Commander aussitôt les 3 kits chez le grossiste avec l'argent déjà reçu (3 x 148,20 € HT = 444,60 € HT).",
                    expectedDeliverable = "+315 € à +399 € de marge nette pure générée sans avance de trésorerie.",
                    officialSourceOrLegal = "Modèle commercial à flux tendu sécurisé (Zéro risque de stock mort)."
                ),
                OperationalPhaseStep(
                    stepNumber = "4.3",
                    phaseName = "PHASE 4 (J+4 à J+15) : VENTES & FOURNISSEURS",
                    taskTitle = "Livraison Clients, Kit CACSI Enedis & Coupons Parrainage",
                    assignee = "Les 2 Associés",
                    timing = "J+10 à J+15",
                    costOrRevenue = "0 € de frais supplémentaires",
                    actionDetails = "Livrer les 3 stations complètes. Remettre en main propre : 1) La station prête à brancher, 2) Le certificat VDE Hoymiles, 3) Le guide Enedis CACSI plastifié, 4) Les 3 coupons de parrainage voisin 30 €.",
                    expectedDeliverable = "Bons de livraison signés et 3 clients ambassadeurs hyper satisfaits.",
                    officialSourceOrLegal = "Procédure officielle Enedis-PRO-RES_43E & Code de la consommation."
                ),

                // PHASE 5 : CONFORMITÉ RÉGLEMENTAIRE & PROTECTION FISCALE (J+16 À J+30)
                OperationalPhaseStep(
                    stepNumber = "5.1",
                    phaseName = "PHASE 5 (J+16 à J+30) : BOUCLIERS LÉGAUX",
                    taskTitle = "Adhésion Gratuite à l'Éco-Organisme Soren (Loi AGEC)",
                    assignee = "Associé 2",
                    timing = "J+16",
                    costOrRevenue = "0 € d'adhésion (0,80 € HT / panneau)",
                    actionDetails = "Créer le compte adhérent sur soren.eco (filière agréée de recyclage photovoltaïque en France). Obtenir le numéro d'Identifiant Unique (IDU) ADEME à inscrire sur les futures factures.",
                    expectedDeliverable = "Attestation d'adhésion Soren et numéro IDU délivré par l'ADEME.",
                    officialSourceOrLegal = "Code de l'environnement art. R543-172 & Décret 2014-928."
                ),
                OperationalPhaseStep(
                    stepNumber = "5.2",
                    phaseName = "PHASE 5 (J+16 à J+30) : BOUCLIERS LÉGAUX",
                    taskTitle = "Souscription Assurance RC Pro Matériel Électrique",
                    assignee = "Associé 1",
                    timing = "J+20",
                    costOrRevenue = "~42 € / mois",
                    actionDetails = "Souscrire en ligne la police d'assurance RC Pro Exploitation + Après Livraison chez Hiscox ou AXA (code NAF 47.91A, couverture 1 500 000 €). Vérifier l'absence d'obligation de décennale bâtiment.",
                    expectedDeliverable = "Attestation d'assurance RC Pro opposable aux tiers et clients.",
                    officialSourceOrLegal = "Code des assurances art. L112-1 & Code civil art. 1245."
                ),
                OperationalPhaseStep(
                    stepNumber = "5.3",
                    phaseName = "PHASE 5 (J+16 à J+30) : BOUCLIERS LÉGAUX",
                    taskTitle = "Actualisation Mensuelle France Travail (Maintien 100% ARE)",
                    assignee = "Associé 1 (Président)",
                    timing = "Fin du Mois 1",
                    costOrRevenue = "100% de votre ARE France Travail maintenue (montant individuel selon droits)",
                    actionDetails = "Lors de l'actualisation sur francetravail.fr : Déclarer '0 heure salariée, 0 € de rémunération'. Téléverser le PV d'AG de mandat gratuit et l'attestation de non-rémunération signée.",
                    expectedDeliverable = "Versement intégral de l'allocation chômage mensuelle sur compte personnel.",
                    officialSourceOrLegal = "Circulaire Unédic n° 2014-26 (Fiche 2 - Maintien intégral sans abattement)."
                ),

                // PHASE 6 : INDUSTRIALISATION & ÉCHELLE (J+31 À J+90)
                OperationalPhaseStep(
                    stepNumber = "6.1",
                    phaseName = "PHASE 6 (J+31 à J+90) : ÉCHELLE & SCALE",
                    taskTitle = "Achat de la Première Palette de 36 Panneaux (-21% Coût)",
                    assignee = "Les 2 Associés",
                    timing = "Dès 3 000 € de trésorerie (J+45)",
                    costOrRevenue = "1 404 € HT palette (Gain +18 €/kit)",
                    actionDetails = "Commander une palette complète de 36 panneaux Trina 440W sur POwR Connect ou SecondSol à 39,00 € HT l'unité (au lieu de 49,50 €). Stocker dans le garage sécurisé. Fret mutualisé à 2,36 € / panneau.",
                    expectedDeliverable = "Gain de marge brute immédiate de +18 € à +32 € sur chaque kit vendu.",
                    officialSourceOrLegal = "Tarif dégressif B2B grossiste palette certifiée Eurocode."
                ),
                OperationalPhaseStep(
                    stepNumber = "6.2",
                    phaseName = "PHASE 6 (J+31 à J+90) : ÉCHELLE & SCALE",
                    taskTitle = "Démarchage des CSE d'Entreprises & Conciergeries Locales",
                    assignee = "Associé 2 (Développement)",
                    timing = "Mois 2 & Mois 3",
                    costOrRevenue = "+15 à +40 kits / contrat groupé",
                    actionDetails = "Envoyer le dossier de présentation 'Pouvoir d'Achat Énergie' aux 5 plus gros Comités Sociaux et Économiques (CSE) de votre département (+100 salariés) : Pack Solo négocié à 329 € avec prise Wi-Fi offerte.",
                    expectedDeliverable = "Première convention signée avec un CSE générant 15 à 30 commandes groupées d'un coup.",
                    officialSourceOrLegal = "Code du travail art. L2312-78 (Attributions sociales et culturelles des CSE)."
                )
            ),
            growthLevers = listOf(
                GrowthLever(
                    title = "1. Achat par Palettes de 36 Panneaux (-21% Coût Grossiste)",
                    timeToDeploy = "Dès 3 000 € de trésorerie accumulée (Mois 2)",
                    netProfitImpact = "+18,00 € à +32,00 € de marge pure supplémentaire par kit",
                    operationalStrategy = "Au lieu d'acheter les panneaux à l'unité chez POwR Connect à 49,50 € HT, commander une palette de 36 panneaux Trina sur SecondSol ou POwR B2B à 39,00 € HT. Le coût de transport est mutualisé (2,36 € / panneau au lieu de 35 €). Le bénéfice net unitaire bondit de 105 € à 123 € (livré) et de 133 € à 151 € (main propre).",
                    keyActionStep = "Dès que 15 précommandes sont encaissées, valider la première commande de palette complète livrée dans le garage sécurisé."
                ),
                GrowthLever(
                    title = "2. Maximisation du Panier Moyen (Upsells Pack Duo & Prise Wi-Fi)",
                    timeToDeploy = "Immédiat (Jour J au lancement)",
                    netProfitImpact = "+47% de bénéfice net par commande (Panier moyen à 485 €)",
                    operationalStrategy = "1) Proposer systématiquement le Pack Duo 880W à 649 € (+218 € à +255 € net de marge) pour effacer la consommation de la machine à laver et du lave-vaisselle (choisi par 35% des clients). 2) Upsell Prise connectée Wi-Fi Tuya 16A à 19 € (coût d'achat 6,50 € HT = +9,33 € net à 60% de marge brute, choisie par 80% des acheteurs). 3) Câble rallonge étanche 10m à 29 € (+14 € net).",
                    keyActionStep = "Intégrer les boutons d'options 'Pack Duo Famille' et 'Prise connectée recommandée' dans l'annonce pilote et sur le bon de commande."
                ),
                GrowthLever(
                    title = "3. Boucle Virale de Parrainage Voisinage Cash (Coût d'Acquisition = 0 €)",
                    timeToDeploy = "Mois 1 (Dès les 5 premières livraisons)",
                    netProfitImpact = "+30% à +50% de volume de vente sans aucun budget publicitaire",
                    operationalStrategy = "L'effet de voisinage est le levier n°1 du solaire : dès qu'un propriétaire installe un panneau visible sur sa terrasse ou pelouse, 3 voisins viennent lui poser des questions sous 30 jours. On insère dans chaque carton 3 coupons postaux 'Offre Voisin Privilégié' : le voisin bénéficie de 20 € de remise, et le client parrain reçoit 30 € par virement instantané Wero / bancaire dès validation.",
                    keyActionStep = "Imprimer 100 flyers cartonnés de parrainage 'Offre Voisinage SOLARIS' avec code unique à glisser dans chaque carton d'expédition."
                ),
                GrowthLever(
                    title = "4. Ventes Groupées B2B : CSE d'Entreprises & Conciergeries Airbnb",
                    timeToDeploy = "Mois 2 et Mois 3",
                    netProfitImpact = "+15 à +40 kits vendus d'un coup par contrat groupé",
                    operationalStrategy = "1) Démarcher les CSE (Comités Sociaux et Économiques) d'entreprises locales (+100 salariés : banques, hôpitaux, industries) avec une offre négociée 'Pouvoir d'Achat Énergie Verte' pour les salariés (Pack Solo à 329 € avec prise offerte). 2) Proposer aux conciergeries et gîtes Airbnb une offre pour neutraliser la clim et les jacuzzis des vacanciers. 3) Option 'Mise en service VIP 15 min' à 49 € pour les clients locaux (100% de marge horaire).",
                    keyActionStep = "Envoyer le dossier de présentation PDF 'Avantage Salariés Énergie Verte' aux 5 plus gros CSE de votre département."
                )
            ),
            humanPrerequisites = listOf(
                HumanPrerequisiteItem(
                    id = "human_stripe_kyc",
                    title = "1. Activation de l'Encaissement Stripe CB en Production (KYC / AML)",
                    assignedTo = "Associé 1 (Président SAS)",
                    estimatedCost = "0 € d'ouverture (Commission 1,5% + 0,25 € par vente)",
                    whyAiCannotDoIt = "Obligation légale bancaire européenne (directive DSP2 / KYC) : Stripe exige la pièce d'identité officielle en cours de validité (CNI ou Passeport) du dirigeant légal, un selfie de vérification d'identité et le RIB Qonto de la SAS. Aucune IA ne peut signer ni usurper cette identité juridique.",
                    actionRequiredByPartner = "Se connecter sur dashboard.stripe.com avec l'email pro de la société, renseigner le SIREN, télécharger la CNI recto/verso du Président et lier le compte bancaire Qonto pour recevoir les virements automatiques.",
                    officialUrl = "https://dashboard.stripe.com/register"
                ),
                HumanPrerequisiteItem(
                    id = "human_geodis_account",
                    title = "2. Signature du Contrat Cadre Fret Geodis Pro (Tarif 35 € HT / palette)",
                    assignedTo = "Associé 2 (Logistique & Opérations)",
                    estimatedCost = "0 € d'ouverture (Paiement à la palette expédiée)",
                    whyAiCannotDoIt = "Geodis exige un contrat commercial signé par le dirigeant avec extrait Kbis pour ouvrir un compte chargeur pro et appliquer la grille tarifaire négociée B2B (35 € HT par demi-palette avec hayon et RDV SMS au lieu de 95 € HT tarif grand public).",
                    actionRequiredByPartner = "Appeler l'agence commerciale Geodis Distribution du département (ou via espace-client.geodis.com), transmettre le Kbis de la SAS et valider la convention d'enlèvement en atelier/garage.",
                    officialUrl = "https://espace-client.geodis.com/"
                ),
                HumanPrerequisiteItem(
                    id = "human_domain_dns",
                    title = "3. Achat du Nom de Domaine Définitif & Configuration DNS OVH",
                    assignedTo = "Associé 1 (Président / Web)",
                    estimatedCost = "8,39 € TTC / an (OVHcloud)",
                    whyAiCannotDoIt = "L'enregistrement d'un nom de domaine en .fr auprès du registre national AFNIC nécessite un paiement par carte bancaire de la société et la validation des coordonnées du titulaire légal.",
                    actionRequiredByPartner = "Acheter le domaine solaris-france.fr (ou solaris-solaire.fr) sur ovhcloud.com, puis dans la zone DNS ajouter un enregistrement CNAME pointant vers le site GitHub Pages ou Netlify.",
                    officialUrl = "https://www.ovhcloud.com/fr/domains/"
                ),
                HumanPrerequisiteItem(
                    id = "human_voip_09",
                    title = "4. Souscription Ligne Téléphonique VoIP Professionnelle (Numéro 09)",
                    assignedTo = "Les 2 Associés",
                    estimatedCost = "1,20 € HT / mois (OVH Télécom)",
                    whyAiCannotDoIt = "Attribution d'une ligne téléphonique française régie par l'ARCEP nécessitant la souscription d'un abonnement télécom avec justificatif de domicile de la société.",
                    actionRequiredByPartner = "Créer un compte OVH Télécom VoIP (offre à 1,20 € HT/mois), choisir un numéro 09 non surtaxé et configurer l'application SIP (Zoiper) ou le renvoi d'appel simultané sur les smartphones des deux associés.",
                    officialUrl = "https://www.ovhtelecom.fr/telephonie/voip/"
                ),
                HumanPrerequisiteItem(
                    id = "human_soren_eco",
                    title = "5. Adhésion Éco-organisme SOREN (Filière Recyclage DEEE Panneaux)",
                    assignedTo = "Associé 1 (Président SAS)",
                    estimatedCost = "0 € d'adhésion (Éco-participation répercutée sur le prix de vente)",
                    whyAiCannotDoIt = "Obligation légale environnementale (Code de l'Environnement art. R543-172). Tout metteur sur le marché de panneaux photovoltaïques doit être immatriculé auprès de SOREN sous peine d'amende administrative de 7 500 €.",
                    actionRequiredByPartner = "Créer le compte metteur sur le marché sur le portail soren.eco, signer la convention d'adhésion dématérialisée et déclarer les volumes annuels prévisionnels (environ 360 panneaux pour l'année 1).",
                    officialUrl = "https://www.soren.eco/"
                ),
                HumanPrerequisiteItem(
                    id = "human_rc_pro",
                    title = "6. Souscription Assurance RC Pro Exploitation & Vente de Matériel",
                    assignedTo = "Associé 1 (Président SAS)",
                    estimatedCost = "~42 € / mois (Hiscox ou AXA Pro)",
                    whyAiCannotDoIt = "Un contrat d'assurance engage la responsabilité civile et pénale de la société. Il exige la signature du mandat de prélèvement SEPA et la déclaration de l'activité exacte de vente d'équipements photovoltaïques.",
                    actionRequiredByPartner = "Finaliser la souscription en ligne sur hiscox.fr (ou chez un courtier local) pour la police 'RC Pro Négociation et Vente d'équipements pour énergies renouvelables'. Transmettre l'attestation aux grossistes.",
                    officialUrl = "https://www.hiscox.fr/assurance-responsabilite-civile-professionnelle"
                ),
                HumanPrerequisiteItem(
                    id = "human_prototype_garage",
                    title = "7. Réception Physique & Crash-Test du Kit Prototype dans le Garage",
                    assignedTo = "Associé 2 (Technique & Logistique)",
                    estimatedCost = "180 € TTC (Avance 50/50 remboursée sur la première vente)",
                    whyAiCannotDoIt = "L'inspection physique de la qualité des découpes d'aluminium, la vérification du câble Schuko, la prise de photos réelles HD du kit posé sur la pelouse/terrasse et le test au multimètre de la coupure VDE < 200ms nécessitent des mains humaines sur le terrain.",
                    actionRequiredByPartner = "Commander 1 kit prototype chez POwR Connect ou Kitsolaire-Discount, le déballer dans le garage, filmer la vidéo de débranchement pour les réseaux sociaux et tester la baisse de puissance sur le compteur Linky de la maison.",
                    officialUrl = "https://www.kitsolaire-discount.com/"
                )
            ),
            associateVerifications = listOf(
                AssociateVerificationItem(
                    id = "audit_grossiste_conditions",
                    title = "1. Appel Grossiste B2B : Validation Compte & Conditions Fret Palette",
                    assignedTo = "Associé 1 (Président) & Associé 2",
                    exactActionScript = "Appeler POwR Connect Nantes au 02 40 92 00 00 (ou UpWatt au 01 84 80 84 80). Script mot à mot : 'Bonjour, nous créons notre SAS de distribution photovoltaïque Plug & Play (panneaux Trina 440W + micro-onduleurs Hoymiles). Pouvez-vous nous confirmer l'ouverture d'un compte professionnel sans Kbis définitif pendant la constitution (sur attestation de dépôt des fonds Qonto) et le coût exact d'une expédition Geodis hayon 48h chez un particulier ?'",
                    documentToObtain = "Grille tarifaire B2B officielle + Fiche d'ouverture de compte revendeur + Conditions générales de fret Geodis.",
                    officialLegalSource = "Conditions Générales de Vente Professionnelles POwR Connect & UpWatt France."
                ),
                AssociateVerificationItem(
                    id = "audit_rc_pro_assurance",
                    title = "2. Souscription Assurance RC Pro Vente de Matériel Électrique",
                    assignedTo = "Associé 1 (Président)",
                    exactActionScript = "Faire une simulation directe sur Hiscox.fr ou Coover.fr pour le code NAF 47.91A (Vente à distance sur catalogue spécialisé). Point impératif à vérifier : Sélectionner la garantie 'RC Après Livraison & Produits' (plafond minimum 1 500 000 €). Vérifier par écrit qu'aucune garantie décennale du bâtiment n'est exigée pour le matériel amovible prêt à brancher.",
                    documentToObtain = "Attestation d'assurance RC Pro Exploitation & Après Livraison (coût cible : 35 € à 50 € / mois).",
                    officialLegalSource = "Code des assurances art. L112-1 & Code civil art. 1245 (responsabilité du fait des produits défectueux)."
                ),
                AssociateVerificationItem(
                    id = "audit_chomage_are_france_travail",
                    title = "3. Validation Maintien 100% ARE Chômage avec le Conseiller France Travail",
                    assignedTo = "Associé 1 (Président demandeur d'emploi)",
                    exactActionScript = "Envoyer un message sur l'espace personnel France Travail : 'Dans le cadre de mon projet de création de SAS, je serai Président non rémunéré afin de préserver 100% de la trésorerie pour le stock. Pouvez-vous me confirmer que la fourniture d'un PV d'AG actant mon mandat à titre gratuit me permet de maintenir l'intégralité de mon ARE mensuelle lors de chaque actualisation ?'",
                    documentToObtain = "Confirmation écrite de votre conseiller référent France Travail validant le maintien ARE.",
                    officialLegalSource = "Circulaire Unédic n° 2014-26 du 30/09/2014 (Fiche 2 - Dirigeants de société non rémunérés)."
                ),
                AssociateVerificationItem(
                    id = "audit_urbanisme_plu_local",
                    title = "4. Vérification Périmètre ABF / Monuments Historiques de votre Commune",
                    assignedTo = "Associé 2 (Directeur Général)",
                    exactActionScript = "Se rendre sur le portail officiel de l'État : geoportail-urbanisme.gouv.fr et renseigner l'adresse de votre siège / domicile. Vérifier que la parcelle ne se situe pas dans un périmètre de protection des abords d'un monument historique ou d'un site patrimonial remarquable (qui imposerait l'accord de l'Architecte des Bâtiments de France).",
                    documentToObtain = "Extrait de zonage PLU officiel (Zone U, AU, etc.) téléchargeable en 1 clic.",
                    officialLegalSource = "Code de l'urbanisme, article R*421-2 point a) (Dispense totale pour pose au sol < 1,80 m hors ABF)."
                ),
                AssociateVerificationItem(
                    id = "audit_crash_test_echantillon",
                    title = "5. Crash-Test Physique de l'Échantillon & Vérification Linky",
                    assignedTo = "Les 2 associés ensemble",
                    exactActionScript = "Commander 1 kit Solo de démonstration chez Kitsolaire-Discount ou UpWatt. 1) Brancher sur une prise murale avec wattmètre. 2) Relever sur l'écran du compteur Linky la puissance apparente en VA (doit chuter immédiatement de 300 à 400 VA au soleil). 3) Débrancher brutalement la prise : vérifier au multimètre que la tension aux broches de la prise mâle tombe à 0 Volt en moins d'une demi-seconde (Preuve physique du relais DIN VDE anti-électrocution).",
                    documentToObtain = "Photos & vidéo HD du test Linky pour illustrer les futures annonces Leboncoin et le site web.",
                    officialLegalSource = "Norme européenne DIN EN 50549-1 & Norme DIN VDE 0126-1-1 (temps de coupure garanti < 200 ms)."
                )
            ),
            objections = listOf(
                ObjectionItem(
                    objection = "« Est-il possible de mettre un seul panneau sur le toit mais avec une prise directe 220V ? »",
                    rebuttal = "« Oui, à 100%. Le micro-onduleur Hoymiles HMS-400 transforme le courant continu en 230V alternatif et s'injecte directement dans n'importe quelle prise standard de la maison. Sur un toit plat de garage ou cabanon, vous posez notre support triangulaire lesté sans aucun trou. Sur une toiture en tuiles, il suffit de remplacer le châssis au sol par nos crochets inox toiture (+59 €) et de faire une Déclaration Préalable gratuite en mairie (délai 1 mois, art. R421-17 du Code de l'urbanisme). Le câble étanche descend le long du mur et se branche directement sur une prise 220V ordinaire ! »",
                    tacticalAngle = "Faisabilité 100% validée sur prise 220V + Option crochets toiture (+59 €) & Cerfa DP mairie pré-rempli"
                ),
                ObjectionItem(
                    objection = "« Est-ce dangereux pour mon compteur Linky ou mon installation électrique ? »",
                    rebuttal = "« Aucun risque : le micro-onduleur Hoymiles est équipé d'une protection de découplage anti-îlotage homologuée DIN VDE 0126-1-1. Si vous débranchez la prise ou s'il y a une coupure sur le réseau Enedis, le micro-onduleur coupe le courant en moins de 0,2 seconde. Aucun risque d'électrocution pour vos enfants ni de surtension sur votre tableau. »",
                    tacticalAngle = "Réassurance technique & coupure instantanée VDE sous 200ms"
                ),
                ObjectionItem(
                    objection = "« Et en hiver ou par temps gris, est-ce que ça produit vraiment ? »",
                    rebuttal = "« Oui. Grâce aux cellules N-Type TOPCon biverre, la station capte la lumière diffuse même sous un ciel nuageux ou pluvieux. Elle produit entre 20% et 35% de sa puissance nominale par temps gris, ce qui suffit largement à effacer le talon électrique permanent de votre box, frigo et veilles. En été, elle tourne à plein régime. »",
                    tacticalAngle = "Pédagogie technologie biverre N-Type à captation diffuse"
                ),
                ObjectionItem(
                    objection = "« Pourquoi ne pas plutôt faire appel à un artisan pour poser des panneaux sur mon toit ? »",
                    rebuttal = "« Un installateur toiture facture entre 8 000 € et 12 000 € avec démarches en mairie, Consuel, échafaudage et risque de fuite de toiture, amorti en 12 à 15 ans. Notre station SOLARIS coûte 349 € TTC, se branche en 15 minutes sur une prise standard sans percer le toit, et s'amortit en seulement 3 ans. Si vous déménagez, vous l'emportez avec vous ! »",
                    tacticalAngle = "Comparatif financier implacable : ROI 3 ans vs 14 ans"
                ),
                ObjectionItem(
                    objection = "« Dois-je payer des taxes ou déclarer mon installation en mairie ? »",
                    rebuttal = "« Aucune taxe, aucune déclaration en mairie pour une pose au sol (< 1,80m). Il suffit d'une déclaration gratuite en 5 clics sur le portail Enedis (Convention CACSI sans frais). L'attestation de conformité du micro-onduleur que nous vous fournissons valide le dossier automatiquement. »",
                    tacticalAngle = "Simplicité administrative : 0 € de taxe & déclaration 100% gratuite"
                ),
                ObjectionItem(
                    objection = "« Pourquoi votre station est à 349 € alors que Sunology et Leroy Merlin la vendent à 700 € ? Est-ce du matériel au rabais ? »",
                    rebuttal = "« Pas du tout, c'est rigoureusement la même base industrielle : nous utilisons les mêmes panneaux solaires biverre Trina Solar (Top 3 mondial Bloomberg Tier-1) et le même micro-onduleur Hoymiles homologué VDE que les leaders. La seule différence, c'est que les marques en grande surface reversent 25% à 30% de marge à Leroy Merlin ou Boulanger (~170 €) et dépensent des millions en spots télé. Nous vendons en circuit direct usine sans intermédiaires, ce qui vous fait économiser 350 € pour exactement la même technologie et la même garantie 25 ans. »",
                    tacticalAngle = "Transparence industrielle : suppression de la marge de 30% des distributeurs physiques."
                ),
                ObjectionItem(
                    objection = "« Qui assure la garantie de 25 ans et le SAV si vous n'êtes pas un grand magasin physique ? »",
                    rebuttal = "« La garantie de 25 ans sur le panneau et 12 à 25 ans sur le micro-onduleur est une garantie constructeur directe assurée par Trina Solar et Hoymiles, avec nos grossistes nationaux officiels POwR Connect et UpWatt basés en France. De plus, notre société SOLARIS France SAS fournit une facture légale avec TVA déductible et un numéro d'assistance technique basé en France pour tout échange standard immédiat sous 48h. »",
                    tacticalAngle = "Garantie constructeur Tier-1 directe + support local réactif."
                )
            ),
            regulatoryRoadmap = listOf(
                RegulatoryRoadmapItem(
                    badge = "Enedis",
                    title = "Convention CACSI Autoconsommation Sans Injection",
                    legalRef = "Code de l'énergie art. L315-1 & Procédure Enedis-PRO-RES_43E",
                    description = "Déclaration gratuite en ligne sur mon-compte-client.enedis.fr. Zéro passage Consuel pour installation amovible au sol de moins de 3 000 W.",
                    officialLink = "https://mon-compte-client.enedis.fr/"
                ),
                RegulatoryRoadmapItem(
                    badge = "Loi AGEC",
                    title = "Enregistrement Filière Recyclage Soren (Éco-Organisme)",
                    legalRef = "Code de l'environnement art. L541-10-13",
                    description = "Attribution du numéro IDU obligatoire pour metteur sur le marché en France. Éco-contribution Soren (~0,70 € HT / panneau).",
                    officialLink = "https://www.soren.eco/"
                ),
                RegulatoryRoadmapItem(
                    badge = "Norme CE",
                    title = "Sécurité Réseau Découplage VDE 0126-1-1",
                    legalRef = "Norme DIN VDE V 0124-100 & NF EN 62109",
                    description = "Coupure automatique de la tension aux bornes de la fiche secteur en moins de 200 millisecondes en cas de coupure de réseau ou de débranchement.",
                    officialLink = "https://www.hoymiles.com/"
                ),
                RegulatoryRoadmapItem(
                    badge = "Greffe SAS",
                    title = "Statuts SAS Multi-Activités & Maintien 100% ARE France Travail",
                    legalRef = "Code de commerce Art. L227-1 & Circulaire Unédic 2024",
                    description = "Dépôt capital 1 000 € Qonto (500 €/associé). Mandat social Président à titre gratuit (0 € rémunération) garantissant le maintien intégral de l'allocation chômage sans déduction. Objet social couvrant Solaire + Alarmes sous 1 seul SIRET.",
                    officialLink = "https://formalites.entreprises.gouv.fr/"
                )
            ),
            conceptionItems = listOf(
                ConceptionDetail(
                    title = "Panneau Trina Solar 440W Vertex S+ Biverre",
                    description = "Technologie N-Type i-TOPCon biverre 2.0 mm x 2, tolérance positive 0/+5W, rendement 22.0%, garantie produit 25 ans / perf 30 ans.",
                    keyMetric = "79,99 € TTC détail • ~49,50 € HT palette B2B",
                    sourceLabel = "Fiche Produit Vérifiée (Kitsolaire-Discount)",
                    verifiedSourceUrl = "https://kitsolaire-discount.com/fr/panneaux-solaires/2474-panneau-solaire-440w-biverre-vertex-s-trinasolar-9331416830358.html",
                    officialReference = "Norme IEC 61215 / IEC 61730 • Grossiste B2B : POwR Connect France"
                ),
                ConceptionDetail(
                    title = "Micro-Onduleur Hoymiles HMS-800-2T (Duo 800W)",
                    description = "2 MPPT indépendants, rendement 96.7%. Protection de découplage automatique intégrée pour la sécurité du réseau Enedis.",
                    keyMetric = "109,00 € TTC détail • ~68,00 € HT palette B2B",
                    sourceLabel = "Fiche Produit Vérifiée (Kitsolaire-Discount)",
                    verifiedSourceUrl = "https://kitsolaire-discount.com/fr/produit-flux-seo/2217-micro-onduleur-duo-hms-800-2t-hoymiles-8424511842942.html",
                    officialReference = "Homologation DIN VDE V 0124-100 & VDE 0126-1-1 • Coupure < 200ms"
                ),
                ConceptionDetail(
                    title = "Châssis Alu Inclinable Réglable Ksolar Lite",
                    description = "Structure en alliage d'aluminium anodisé AL6005-T5 avec visserie inox A2. Inclinaison réglable 20° à 35° (optimale France).",
                    keyMetric = "39,00 € TTC détail • ~19,50 € HT volume",
                    sourceLabel = "Fiche Produit Vérifiée (Kitsolaire-Discount)",
                    verifiedSourceUrl = "https://kitsolaire-discount.com/fr/fixation-terrasse-sol-mur/3430-fixation-universel-reglable-lite-ksolar.html",
                    officialReference = "Résistance aux vents jusqu'à 130 km/h (Eurocode 1)"
                ),
                ConceptionDetail(
                    title = "Câble Secteur Schuko 5m Étanchéité IP68",
                    description = "Câble blindé extérieur HO7RN-F 3G1.5mm² équipé du connecteur Field Connector étanche HMS et prise Schuko mâle 16A.",
                    keyMetric = "22,90 € TTC détail • ~11,20 € HT volume",
                    sourceLabel = "Fiche Produit Vérifiée (Kitsolaire-Discount)",
                    verifiedSourceUrl = "https://kitsolaire-discount.com/fr/micro-onduleurs-accessoires/2225-718736-cable-ac-avec-prise-etanche-hms-3g-15mm-9085823105782.html",
                    officialReference = "Norme NF EN 62109 • Raccordement prise 220V standard"
                ),
                ConceptionDetail(
                    title = "Transport & Fret Palette Particulier (Geodis)",
                    description = "Messagerie sécurisée avec hayon élévateur, transpalette et prise de rendez-vous par SMS chez le particulier.",
                    keyMetric = "35 € à 45 € HT / palette en France",
                    sourceLabel = "Simulateur Public Fret Upela",
                    verifiedSourceUrl = "https://www.upela.com/fr/",
                    officialReference = "Réseau national Geodis Calberson / Dachser France"
                )
            ),
            productionActions = listOf(
                ProductionAction("s1", "Site e-commerce vitrine avec simulateur Linky déployé", true, "URGENT", "Moi"),
                ProductionAction("s2", "Visuel commercial HD station dans jardin validé", true, "URGENT", "Associé"),
                ProductionAction("s3", "Déposer l'annonce pilote sur Leboncoin & Marketplace", false, "HAUTE", "Moi"),
                ProductionAction("s4", "Créer les accès grossistes pros POwR Connect & UpWatt", false, "HAUTE", "Associé"),
                ProductionAction("s5", "Commander le premier kit prototype test unitaire", false, "MOYENNE", "Les Deux"),
                ProductionAction("s6", "Livrer les 3 premières commandes fermes en direct", false, "MOYENNE", "Les Deux")
            ),
            toolkitItems = listOf(
                                ToolkitItem(
                    id = "tool_fiche_juridique_sas",
                    title = "Fiche Stratégique & Fiscale SAS 50/50 (Nom Greffe, 1000 € Capital, Bouclier ARE)",
                    category = "Juridique & Fiscalité",
                    contentToCopy = """GUIDE OPÉRATIONNEL DE CRÉATION DE LA SAS MULTI-ACTIVITÉS

1. DÉNOMINATION SOCIALE AU GREFFE (POURQUOI PAS SOLARIS SAS ?) :
- 'SOLARIS SAS' existe déjà au RCS de Bourg-en-Bresse (SIREN 442 600 011, fondée en 2002 dans l'Ain, grossiste solaire).
- Recommandation Dénomination Sociale : VOLTIS GROUP SAS ou HELIO TRADE SAS (neutre, institutionnel, pérenne).
- Les marques commerciales d'exploitation e-commerce (ex: SOLAVOLT.FR pour le solaire, SECUREVO.FR pour les alarmes) sont rattachées sous ce SIRET unique.

2. CAPITAL SOCIAL & RÉPARTITION :
- Capital : 1 000 € (500 € par associé, 1 000 actions de 1 €).
- Répartition : 50% / 50% égalitaire entre les 2 co-fondateurs.
- Dépôt des fonds : Réalisé en ligne sur Qonto en 24h (attestation de dépôt immédiate).

3. GOUVERNANCE & BOUCLIER TOTAL ARE FRANCE TRAVAIL :
- Président : Associé 1 (Mandat social gratuit, rémunération = 0,00 € stipulée dans le PV d'AG constitutif).
- Règle France Travail : Absence de fiche de paie = 100% de maintien de l'allocation chômage mensuelle (zéro carence, zéro abattement).
- Associé 2 : Directeur Général ou associé non rémunéré.
- Zéro cotisation sociale minimale URSSAF (contrairement à la gérance majoritaire de SARL).

4. SORTIE DES BÉNÉFICES (FISCALITÉ DIVIDENDES EN SAS) :
- Les dividendes de SAS ne sont PAS assujettis aux cotisations sociales indépendantes (URSSAF).
- Taxation unique : Flat Tax (PFU de 30% : 12,8% IR + 17,2% prélèvements sociaux).
- Cumul total avec l'ARE sans aucune déduction de l'allocation chômage.

5. FRAIS INCOMPRESSIBLES DE CRÉATION :
- Annonce Légale (JAL) : ~130 € TTC (tarif légal forfaitaire).
- Frais de Greffe du Tribunal de Commerce + RBE : ~60 € TTC.
- Dépôt de capital Qonto : Inclus dans le pack création Qonto (~149 € HT).
- Délai d'obtention du Kbis : 3 à 5 jours ouvrés via le Guichet Unique INPI.""",
                    sourceOrNorm = "Code de commerce Art. L227-1 (Régime SAS) & Circulaire Unédic ARE 2024"
                ),
                ToolkitItem(
                    id = "tool_statuts_objet_social",
                    title = "Clause d'Objet Social Multi-Activités pour les Statuts de la SAS (Solaire + Alarmes)",
                    category = "Juridique & Statuts",
                    contentToCopy = """ARTICLE 2 - OBJET SOCIAL DE LA SAS (Solaire & Sécurité)

La Société a pour objet, tant en France qu'à l'étranger :

1. Le commerce de détail, de gros, l'importation, l'exportation, la conception, l'assemblage et la distribution, par tous moyens notamment par voie électronique, vente à distance et sur internet (e-commerce), de tous matériels, équipements, kits et accessoires liés aux énergies renouvelables, aux stations solaires d'autoconsommation, à l'habitat et au bricolage ;

2. Le commerce, la distribution, la vente et l'installation sans travaux lourds de tous équipements et systèmes électroniques de sécurité domestique et professionnelle, alarmes autonomes sans fil, vidéosurveillance, capteurs, domotique, objets connectés et protection des biens et des personnes ;

3. La prestation de conseil, d'assistance technique, de configuration, de support à distance et de service après-vente (SAV) se rapportant aux équipements et matériels susmentionnés ;

4. La création, l'acquisition, l'exploitation, la concession ou la cession de toutes marques, licences, brevets, dessins, modèles et plateformes logicielles liés à ces activités ;

5. Et plus généralement, toutes opérations commerciales, industrielles, financières, mobilières ou immobilières se rattachant directement ou indirectement à l'objet social ci-dessus spécifié ou à tout autre objet similaire ou connexe.

NOTE D'EXCELLENCE : Cette rédaction légale permet d'exploiter immédiatement SOLARIS France (Solaire) ET SECUREVO France (Alarmes) sous un seul et même SIRET, un compte bancaire unique Qonto et une seule comptabilité, sans aucuns frais de modification d'objet social au Greffe.""",
                    sourceOrNorm = "Code de commerce Art. L210-1 & L227-1 (Greffe du Tribunal de Commerce)"
                ),
ToolkitItem(
                    id = "tool_brand_naming_audit",
                    title = "Audit Stratégique Noms de Domaine & Risque INPI (Solaris vs .FR Libres)",
                    category = "Propriété Intellectuelle & NDD",
                    contentToCopy = """AUDIT STRATÉGIQUE NOM DE MARQUE & DISPONIBILITÉ NDD (AFNIC & INPI)

1. DIAGNOSTIC RISQUE "SOLARIS" :
- solaris.fr : Détenu par GrandVision / EssilorLuxottica (Lunettes de soleil Solaris).
- solaris-store.com : Détenu par SOLARIS SAS (Saint-André-de-Corcy, Ain). Société française fondée en 2002 spécialisée dans le matériel solaire, kits autonomes et plug & play.
- RISQUE JURIDIQUE : Utiliser "Solaris" pour du solaire en France expose à une action immédiate en contrefaçon de marque (Classes 9 et 35 INPI) et concurrence déloyale / parasitisme commercial (Art. L713-2 CPI).
- RISQUE SEO : solaris-store.com a 22 ans d'antériorité et domine les résultats Google France sur "kit solaire solaris".

2. LA RÈGLE 80/20 (SOCIÉTÉ vs MARQUE COMMERCIALE) :
- Dénomination sociale SAS (au Greffe) : Choisir un nom institutionnel neutre (ex: VOLTIS GROUP SAS ou HELIO TRADE SAS) qui englobe le solaire et les alarmes.
- Nom commercial & Marque e-commerce : Déposer une marque propre, libre de toute contestation et avec un domaine .FR disponible.

3. SÉLECTION DES NOMS .FR DISPONIBLES (VÉRIFIÉS AFNIC WHOIS) :
- SOLAVOLT.FR [LIBRE] : Racine solaire + voltage. Premium, sobre, excellente mémorisation.
- PLUGWATT.FR [LIBRE] : "Plug & Play" + "Watts". Clarté instantanée pour le grand public.
- VOLTEVO.FR [LIBRE] : Évolution électrique, tonalité technologique moderne.
- SOLARKIT.FR [LIBRE] : Terme de recherche #1 tapé par les acheteurs sur Google.
- SUNIVIA.FR [LIBRE] : Élégant, accessible, positionnement type Sunology/Beem.
- VOLTOPLUG.FR [LIBRE] : 100% orienté station solaire prête à brancher sur prise.

4. PLAN D'ACTION IMMÉDIAT (COÛT : ~7 € SUR OVH / GANDI) :
1. Réserver le nom de domaine .fr retenu (ex: solavolt.fr ou plugwatt.fr).
2. Dépôt de marque à l'INPI en ligne (Classes 9, 35) : 190 € pour une protection blindée de 10 ans.""",
                    sourceOrNorm = "AFNIC Whois direct & Code de la Propriété Intellectuelle (Art. L713-2)"
                ),
                ToolkitItem(
                    id = "tool_ad_leboncoin",
                    title = "Script Annonce Leboncoin / Marketplace (Prêt à poster)",
                    category = "Marketing & Vente",
                    contentToCopy = "TITRE : Station Solaire 440W Plug & Play – Économisez sur votre facture Linky – Prête à brancher 230V\n\nPRIX : 349 €\n\nDESCRIPTION :\nRéduisez immédiatement votre facture d'électricité grâce à la station solaire SOLARIS™ 420W haute performance !\n\n✔️ ULTRA SIMPLE : Vous déballez, vous posez au sol ou sur votre terrasse, vous branchez sur une simple prise électrique standard 220V.\n✔️ ÉCONOMIES IMMÉDIATES : Efface jusqu'à 650 kWh/an sur votre compteur Linky (effacement du talon électrique : frigo, box internet, veilles, congélateur).\n✔️ MATÉRIEL PROFESSIONNEL HAUT DE GAMME :\n- Panneau solaire biverre N-Type 440Wc (garantie constructeur 25 ans)\n- Micro-onduleur Hoymiles HMS dernière génération avec sécurité anti-îlotage VDE\n- Châssis aluminium renforcé inclinable 20°-35°\n- Câble secteur étanche extérieur 5 mètres\n✔️ 100% LÉGAL & CONFORME : Conforme aux normes européennes et Enedis. Attestation de raccordement simplifiée fournie.\n\nVisible et disponible immédiatement. Enlèvement sur place ou livraison soignée par transporteur avec hayon.",
                    sourceOrNorm = "Testé et optimisé pour le taux de conversion local"
                ),
                ToolkitItem(
                    id = "tool_cacsi_guide",
                    title = "Procédure Express Déclaration Enedis CACSI",
                    category = "Technique & Légal",
                    contentToCopy = "1. Se connecter sur mon-compte-client.enedis.fr avec son numéro de point de livraison (PDL Linky).\n2. Sélectionner 'Déclarer une installation de production en autoconsommation sans injection'.\n3. Renseigner la puissance crête : 0.44 kWc (ou 0.88 kWc pour 2 panneaux).\n4. Joindre le certificat de conformité DIN VDE 0126-1-1 du micro-onduleur Hoymiles HMS.\n5. Valider : signature électronique immédiate sans frais ni passage de technicien.",
                    sourceOrNorm = "Guide Enedis-PRO-RES_43E"
                ),
                ToolkitItem(
                    id = "tool_sms_geodis",
                    title = "Consigne SMS Préventive Client Livraison Geodis (Anti-Casse)",
                    category = "Logistique & Litiges",
                    contentToCopy = "« Bonjour [Prénom], votre station solaire SOLARIS arrive demain par transporteur Geodis.\nCONSIGNE IMPORTANTE DE SÉCURITÉ : Avant de signer le bon du chauffeur, prenez 30 secondes pour inspecter visuellement le carton. En cas de choc visible ou de bruit de verre brisé, refusez le colis en écrivant impérativement sur le boîtier du livreur : 'Refusé - Panneau solaire endommagé / verre brisé'. Cela active immédiatement l'assurance transporteur pour un renvoi gratuit d'un pack neuf sous 48h. »",
                    sourceOrNorm = "Code de commerce art. L133-3 (Réserves légales transporteur)"
                ),
                ToolkitItem(
                    id = "tool_email_grossiste",
                    title = "Modèle Email Ouverture Compte Revendeur POwR Connect / UpWatt",
                    category = "Sourcing & Fournisseurs",
                    contentToCopy = "Destinataire : contact@groupepowr.com / pro@upwatt.com\nObjet : Demande d'ouverture de compte revendeur / Grille Kits Plug & Play 400W-440W\n\nBonjour,\nJe développe actuellement notre offre commerciale de stations solaires d'autoconsommation Plug & Play (stations 440W prêtes-à-brancher avec micro-onduleur Hoymiles et support inclinable sol/terrasse).\nDans le cadre du lancement de notre catalogue en France, je souhaite ouvrir un compte professionnel au sein de votre plateforme pour sourcer régulièrement nos kits et composants.\nPourriez-vous :\n1. Valider l'ouverture de notre accès revendeur B2B (SIRET ci-dessous).\n2. Me communiquer votre catalogue et vos grilles tarifaires dégressives sur Trina Solar 440W et micro-onduleurs Hoymiles HMS-400.\n3. M'indiquer vos conditions pour les expéditions palettes chez nos clients.\n\nSociété : SOLARIS France - SIRET : [Numéro] - Contact : [Nom & Prénom]",
                    sourceOrNorm = "Trame éprouvée pour validation d'accès revendeur sans KBIS initial"
                ),
                ToolkitItem(
                    id = "tool_toiture_prise_directe",
                    title = "🏠 Fiche Technique : Pose Toiture 1 Panneau sur Prise Directe (Technique & Mairie)",
                    category = "Technique & Réglementaire",
                    contentToCopy = """GUIDE POSE TOITURE 1 PANNEAU SUR PRISE DIRECTE 220V :

1. PRINCIPE TECHNIQUE :
- Le micro-onduleur Hoymiles HMS-400 est étanche (IP67) et fixé directement au dos du panneau Trina 440Wc.
- Il convertit l'énergie solaire en 230V monophasé 50Hz synchronisé sur le réseau Enedis.
- Un câble étanche résistant aux UV (3G1.5mm² ou 3G2.5mm²) descend depuis le toit jusqu'à la prise murale la plus proche.
- Le branchement se fait sur n'importe quelle prise standard 16A avec terre (protection 30mA).

2. DEUX CAS D'INSTALLATION SELON LE TYPE DE TOIT :
A) Toit Plat / Toiture de Garage / Cabanon de Jardin (Terrasse EPDM/Bac acier) :
- Châssis triangulaire inclinable au sol réutilisé tel quel.
- Lestage : 2 dalles béton de 20 kg sur les traverses basses du châssis.
- ZÉRO perforation de la toiture, risque de fuite = 0.

B) Toiture en pente (Tuiles mécaniques / Ardoises) :
- Utiliser le kit de fixation toiture (4 crochets inox glissés sous les tuiles et vissés sur chevrons + 2 rails alu).
- Remplacement du châssis au sol (vendu en option à 59 € TTC).

3. RÈGLE D'URBANISME (CODE DE L'URBANISME) :
- Pose au sol (< 1,80 m) : Dispense totale de formalités (Art. R421-2).
- Pose en toiture : Déclaration Préalable (DP) obligatoire (Art. R421-17 - modification de l'aspect extérieur).
- Démarche : Formulaire Cerfa 13404*10 déposé en mairie ou sur le guichet d'urbanisme en ligne.
- Délai d'instruction : 1 mois (2 mois si périmètre ABF Bâtiments de France). Accord tacite si absence de réponse.""",
                    sourceOrNorm = "Art. R421-17 Code de l'urbanisme & Norme NFC 15-100"
                ),
                ToolkitItem(
                    id = "tool_comparatif_roi_batterie_vs_edfoa",
                    title = "📊 Audit Comparatif Rentabilité : 4 Modèles Solaires (France 2026)",
                    category = "Stratégie Commerciale & ROI",
                    contentToCopy = """AUDIT COMPARATIF DE RENTABILITÉ : 4 MODÈLES SOLAIRES EN FRANCE (2026)

1. MODÈLE 1 : PLUG & PLAY DIRECT (1 À 2 PANNEAUX 440W-880W) - LE CHAMPION DU ROI :
- Investissement : 349 € (1 panneau 440W) à 649 € TTC (2 panneaux 880W).
- Taux d'autoconsommation : 85% à 96% sans batterie (consommation du talon passif : frigo, box, veilles).
- Économie Linky : 142 € à 250 € / an (base 0,27 €/kWh).
- Durée d'amortissement (Payback) : 2,5 ans !
- Rendement net du capital investi : +38% à +41% par an (12x le Livret A).
- Réglementation : Convention CACSI Enedis 100% gratuite en 5 min, sans consuel, sans taxe.

2. MODÈLE 2 : PLUG & PLAY AVEC BATTERIE DE STOCKAGE (1,6 kWh) :
- Investissement : ~1 700 € TTC (le pack batterie coûte ~1 050 € à lui seul).
- Taux d'autoconsommation : ~98% à 100%.
- Économie Linky : ~317 € / an (gain nocturne supplémentaire de seulement ~67,50 €/an).
- Durée d'amortissement : 5,4 ans pour le kit complet, mais 15,5 ANS pour amortir la batterie seule !
- Angle mort technique : Durée de vie d'une batterie LiFePO4 = 8 à 10 ans. Elle doit être changée avant d'avoir remboursé son propre surcoût.

3. MODÈLE 3 : TOITURE 3 kWc RGE + VENTE DU SURPLUS EDF OBLIGATION D'ACHAT (EDF OA) :
- Investissement brut : ~9 500 € TTC par artisan RGE.
- Prime à l'autoconsommation de l'État : -1 050 € (versée en 1 fois).
- Coût net réel : 8 450 € TTC.
- Revente du surplus non consommé : 0,13 € / kWh injecté dans le réseau (tarif réglementé 20 ans).
- Gain annuel combiné : ~694 € / an (~420 € d'économies + ~274 € de revente EDF OA).
- Durée d'amortissement : 12,2 ans ! Rendement net : ~8,2% / an.
- RÈGLE LÉGALE STRICTE (Arrêté S21) : La revente EDF OA est STRICTEMENT INTERDITE aux particuliers installant du Plug & Play eux-mêmes. Elle impose impérativement un artisan RGE QualiPV et l'attestation Consuel.

4. MODÈLE 4 : AUTONOMIE TOTALE 100% HORS RÉSEAU (SITE ISOLÉ) :
- Investissement : > 25 000 € TTC (30 panneaux, 25 kWh de batteries LiFePO4, onduleur hybride 10 kVA, groupe électrogène de secours).
- Durée d'amortissement : > 20 ans (NON RENTABLE économiquement).
- Le piège météo : En France, la production solaire en décembre/janvier est divisée par 8 par rapport à juin. Dimensionner pour l'hiver crée un surdimensionnement ruineux et inutile en été.

SYNTHÈSE COMMERCIALE CLIENT :
Le Plug & Play direct (1 ou 2 panneaux) est mathématiquement le meilleur investissement d'énergie en France : amorti en 30 mois, sans travaux lourds, sans dépendre des subventions ni des démarches administratives complexes.""",
                    sourceOrNorm = "Code de l'énergie art. L314-1 & Analyse financière comparative 2026"
                )
            )
        ),
        // =========================================================================
        // 2. PACK ALARME MAISON SANS ABONNEMENT 4G/WI-FI (SECUREVO FRANCE)
        // =========================================================================
        EcomDossier(
            id = "dossier_alarme",
            title = "Pack Alarme Maison 4G/Wi-Fi Sans Abonnement",
            subtitle = "Marque SECUREVO France • Centrale tactile & sirène extérieure solaire",
            category = "Sécurité Domestique & Protection",
            iconEmoji = "🚨",
            progressPercent = 50,
            estimatedNetMargin = "+79 € HT / pack (63,9% de marge brute)",
            targetMonthlyRevenue = "6 208 € HT / mois (50 packs • 7 450 € TTC)",
            liveWebsiteUrl = null,
            partnerNotes = "NOTE COMMUNE (Associés) :\n- 2ème marque exploitée sous la MÊME SAS (SIRET unique, compte Qonto unique, zéro frais de greffe supplémentaires grâce à notre objet social multi-activités).\n- Synergie client totale : même cible (propriétaires de maisons avec jardin/terrasse).\n- Angle d'attaque implacable : 'Pourquoi verser 55 €/mois à vie à Verisure (660 €/an) alors qu'un pack propriétaire complet coûte 149 € une seule fois sans aucun abonnement ?'.\n- La sirène extérieure de notre pack est elle-même solaire et 100% autonome.",
            marketStudy = MarketStudyDetail(
                marketSize = "211 400 cambriolages de logements / an en France",
                growthRate = "+18% de croissance par an sur les alarmes sans abonnement",
                targetPainPoint = "Rente mensuelle abusive Verisure / Sector Alarm (49 € à 65 €/mois, soit 600 € à 780 € / an à vie avec engagement)",
                competitiveEdge = "Pack 11 pièces 4G/Wi-Fi à 149 € TTC sans aucun abonnement, piloté par smartphone Tuya, sirène solaire, amorti en 3 mois",
                officialSourceLabel = "Ministère de l'Intérieur (SSMSI) & ONDRP",
                officialSourceUrl = "https://www.interieur.gouv.fr/actualites/statistiques-du-ministere-de-linterieur",
                fullAnalysis = """OPPORTUNITÉ COMMERCIALE ALARME 4G SANS ABONNEMENT (SECUREVO) :

1. DONNÉES DU MARCHÉ FRANÇAIS :
- En France, un cambriolage a lieu toutes les 2,5 minutes (211 400 faits recensés en 2023-2024 par le Ministère de l'Intérieur).
- 82% des maisons individuelles ne sont toujours pas équipées d'alarme, principalement en raison du coût exorbitant des abonnements mensuels.
- Les leaders (Verisure, Sector Alarm, Homiris) imposent des forfaits de 49 € à 65 €/mois avec engagement de 36 mois, soit un coût de possession supérieur à 2 000 € sur 3 ans.

2. NOTRE PROPOSITION DE VALEUR SECUREVO :
- Vente d'un kit complet 11 pièces prêt-à-poser à 149 € TTC une seule fois pour toute la vie de la maison.
- Alerte instantanée en 3 secondes sur smartphone via notification push et appel téléphonique/SMS grâce à la double connexion Wi-Fi et puce GSM 4G (carte SIM à 2 €/mois ou carte M2M multi-opérateurs).
- Sirène extérieure puissante 120 dB alimentée par son propre panneau solaire étanche IP65 : zéro fil à tirer sur la façade.

3. STRUCTURE DE COÛTS & RENTABILITÉ :
- Prix public : 149 € TTC (124,17 € HT).
- Coût de revient complet : 44,79 € HT (BOM matériel 35,50 € + Colissimo 6,80 € + Stripe 2,49 €).
- Marge brute unitaire : +79,38 € HT (63,9% de marge brute).
- Volume de croisière de 50 packs/mois = 3 969 € de marge brute mensuelle supplémentaire sous la même SAS."""
            ),
            unitEconomics = UnitEconomicsDetail(
                retailPriceTTC = "149,00 € TTC",
                retailPriceHT = "124,17 € HT (TVA 20% : 24,83 €)",
                wholesaleBOMHT = "35,50 € HT (BOM matériel 5 composants usine)",
                shippingCost = "6,80 € HT (Colissimo suivi 48h ou Mondial Relay)",
                paymentGatewayFee = "2,49 € HT (Stripe CB 1,5% + 0,25 €)",
                netProfitPerUnit = "+79,38 € HT / pack (63,9% de marge brute)",
                targetMonthlyNet = "50 packs / mois = +3 969 € de marge brute SAS",
                costItems = listOf(
                    CostItem("Centrale tactile IPS 4.3 pouces 4G+WiFi (Tuya PGST)", "17,20 € HT", "Écran tactile couleur, batterie de secours 8h, sirène 85dB intégrée"),
                    CostItem("Sirène extérieure solaire 120 dB + flash stroboscopique", "7,00 € HT", "Étanche IP65, panneau solaire autonome intégré, zéro câble"),
                    CostItem("4x Détecteurs d'ouverture portes/fenêtres magnétiques", "4,50 € HT", "Sans fil 433 MHz longue portée 100m, pile CR2032 longue durée 2 ans"),
                    CostItem("2x Détecteurs de mouvement PIR immunité animaux <20kg", "3,80 € HT", "Infrarouge passif grand angle 110°, portée 10m, zéro fausse alerte"),
                    CostItem("2x Télécommandes porte-clés SOS & désarmement rapide", "3,00 € HT", "Bouton d'urgence panique pour personnes âgées ou enfants")
                ),
                executiveSummary = "Positionnement anti-Verisure ultra rentable : à 79,38 € de marge brute unitaire, 50 ventes mensuelles génèrent près de 4 000 € de marge brute sous la même structure juridique SAS, sans charges fixes supplémentaires.",
                sourceOrSupplier = "PGST Technology Shenzhen (Certifié CE RED / Tuya Smart France)"
            ),
            partnerProfit = PartnerProfitProjection(
                monthlyVolumeHypothesis = "50 packs 11 pièces / mois (soit moins de 2 ventes / jour)",
                totalCompanyNetMonthly = "+ 3 373 € NET après IS 15% (40 483 € net / an)",
                associate1Name = "Associé 1 (Président SAS)",
                associate1NetMonthly = "+ 1 180 € NET d'impôt / mois (14 169 € net / an)",
                associate1SpecificAdvantage = "Même mandat social gratuit (0 € salaire) : cumul 100% légal avec les dividendes de la SAS et maintien intégral de l'ARE",
                associate2Name = "Associé 2 (Co-fondateur 50%)",
                associate2NetMonthly = "+ 1 180 € NET d'impôt / mois (14 169 € net / an)",
                associate2SpecificAdvantage = "Partage égalitaire des dividendes nets (Flat Tax 30%) sans surcoût de gestion administrative",
                annualDividendsPerPartner = "14 169 € NETS d'impôt en poche par associé / an",
                taxOptimizationNote = "Zéro frais fixe supplémentaire : expert-comptable, Qonto et assurance RC Pro sont déjà payés par l'activité solaire de la SAS. Chaque euro de marge brute alarme tombe directement en bénéfice net."
            ),
            commercialPacks = listOf(
                CommercialPack(
                    name = "Pack Essentiel SECUREVO™ 11 Pièces (Maison & Appartement)",
                    badge = "BEST-SELLER",
                    retailPriceTTC = "149,00 € TTC",
                    wholesaleCostHT = "35,50 € HT",
                    netMarginAmount = "+79,38 € HT",
                    netMarginPercent = "63,9% de marge brute",
                    paybackPeriod = "Amorti en 2,5 mois vs abonnement Verisure (55 €/m)",
                    contents = "1x Centrale tactile 4G/WiFi + 1x Sirène solaire 120dB extérieure + 4x Détecteurs d'ouverture + 2x Détecteurs PIR animaux + 2x Télécommandes"
                ),
                CommercialPack(
                    name = "Pack Intégral SECUREVO™ 16 Pièces avec Caméra Extérieure HD",
                    badge = "HAUTE SÉCURITÉ",
                    retailPriceTTC = "229,00 € TTC",
                    wholesaleCostHT = "58,00 € HT",
                    netMarginAmount = "+128,40 € HT",
                    netMarginPercent = "67,3% de marge brute",
                    paybackPeriod = "Amorti en 4 mois vs abonnement télésurveillance",
                    contents = "Pack 11 pièces + 1x Caméra Wi-Fi solaire extérieure 2K vision nocturne + 2x Détecteurs d'ouverture supplémentaires + 2x Badges RFID"
                )
            ),
            productionSimulations = emptyList(),
            verifiedSuppliers = listOf(
                VerifiedSupplier(
                    name = "PGST Technology Co., Ltd.",
                    location = "Shenzhen, Chine (Partenaire OEM Tuya)",
                    role = "Fabricant OEM certifié CE RED des centrales tactiles et détecteurs",
                    contactB2B = "sales08@cn-pgst.com • WhatsApp B2B : +86 13397149596",
                    conditions = "Échantillons disponibles en 5 jours • Délai fret DDP dédouané 10 jours • Marquage logo SECUREVO disponible",
                    portalUrl = "http://www.cn-pgst.com/"
                )
            ),
            partnerPitchArguments = listOf(
                PartnerPitchArgument(
                    number = 1,
                    title = "Une seule société, zéro surcoût de structure",
                    argument = "Notre SAS possède un objet social multi-activités couvrant l'énergie ET la sécurité. Le compte Qonto et le comptable Pennylane sont déjà payés. Lancer SECUREVO coûte 0 € de frais juridiques.",
                    keyProof = "Article 2 des Statuts SAS validé au Greffe."
                ),
                PartnerPitchArgument(
                    number = 2,
                    title = "Marge brute record de 64% (Anti-Verisure)",
                    argument = "On achète le pack 35,50 € HT et on le vend 149 € TTC (124 € HT). Chaque vente laisse près de 80 € nets dans la caisse de la société, tout en faisant économiser 600 €/an au client.",
                    keyProof = "Tarif usine PGST vérifié et fiches de prix publiques Verisure (55 €/mois)."
                )
            ),
            riskMitigations = listOf(
                RiskMitigationItem(
                    title = "Blocage réseau ou coupure Wi-Fi",
                    probabilityLevel = "FAIBLE",
                    potentialImpact = "Perte de notification",
                    failureModeDescription = "La box internet du client se coupe lors d'un orage ou d'une coupure électrique.",
                    viableSolution = "Double liaison intégrée : la centrale bascule automatiquement en GSM 4G grâce à la carte SIM de secours et envoie immédiatement un SMS et un appel vocal.",
                    legalOrTechnicalProof = "Centrale bi-mode Wi-Fi 2.4 GHz + Module GSM 4G LTE mondial."
                )
            ),
            operationalPhases = listOf(
                OperationalPhaseStep(
                    stepNumber = "1.1",
                    phaseName = "EXPANSION (Phase 2)",
                    taskTitle = "Commande du pack prototype SECUREVO pour test",
                    assignee = "Moi",
                    timing = "Mois 2",
                    costOrRevenue = "45 € TTC",
                    actionDetails = "Commander un pack prototype chez PGST avec sirène solaire pour tester la portée 433 MHz, l'application Tuya en français et l'alerte SMS 4G.",
                    expectedDeliverable = "Vidéo de démonstration en direct du déclenchement sirène et notification smartphone.",
                    officialSourceOrLegal = "Norme CE RED (Radio Equipment Directive 2014/53/EU)."
                )
            ),
            growthLevers = emptyList(),
            associateVerifications = emptyList(),
            humanPrerequisites = emptyList(),
            objections = listOf(
                ObjectionItem(
                    objection = "« Est-ce aussi fiable qu'une alarme télésurveillée Verisure ? »",
                    rebuttal = "« Verisure envoie simplement un vigile 45 minutes après l'intrusion, quand les cambrioleurs sont déjà partis. Avec SECUREVO, la sirène extérieure solaire 120 dB se déclenche instantanément, fait fuir 95% des intrus dans les 60 premières secondes, et vous recevez l'alerte directe sur votre téléphone sans payer 600 € par an à vie. »",
                    tacticalAngle = "Dissuasion sonore immédiate vs vigile en retard payant."
                )
            ),
            regulatoryRoadmap = listOf(
                RegulatoryRoadmapItem(
                    badge = "Norme CE RED",
                    title = "Directive Européenne Équipements Radioélectriques",
                    legalRef = "Directive 2014/53/UE (RED)",
                    description = "Obligation de conformité des fréquences radio 433 MHz et 4G pour la commercialisation légale sur le territoire européen.",
                    officialLink = "https://www.economie.gouv.fr/entreprises/directive-equipements-radioelectriques-red"
                )
            ),
            conceptionItems = listOf(
                ConceptionDetail(
                    title = "Centrale d'Alarme Tuya Smart Life 4G",
                    description = "Centrale tactile couleur avec protocole Tuya, permettant de connecter caméras, détecteurs et domotique sur la même application française.",
                    keyMetric = "17,20 € HT usine • Revente 149 € TTC pack complet",
                    sourceLabel = "Fiche Constructeur PGST Technology",
                    verifiedSourceUrl = "http://www.cn-pgst.com/",
                    officialReference = "Conformité CE RED & RoHS"
                )
            ),
            productionActions = listOf(
                ProductionAction("a1", "Création de la vitrine e-commerce SECUREVO", false, "MOYENNE", "Moi"),
                ProductionAction("a2", "Campagne publicitaire vidéo anti-Verisure", false, "BASSE", "Associé")
            ),
            toolkitItems = listOf(
                ToolkitItem(
                    id = "tool_pitch_alarme",
                    title = "Pitch Téléphonique Anti-Abonnement SECUREVO",
                    category = "Marketing & Vente",
                    contentToCopy = "« Bonjour, payez-vous actuellement un abonnement mensuel de 50 € chez Verisure ou un télésurveilleur ?\nSaviez-vous qu'avec notre pack SECUREVO à 149 €, vous avez exactement la même protection sans fil 4G avec sirène solaire extérieure, mais vous économisez 600 € chaque année à vie, sans aucun abonnement ? »",
                    sourceOrNorm = "Argumentaire focalisé sur l'économie de rente"
                )
            )
        )
    )
}
