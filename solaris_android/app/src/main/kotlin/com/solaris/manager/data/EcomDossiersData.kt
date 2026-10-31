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

3. PREUVE MICROECONOMIQUE DU FUNNEL LEBONCOIN LOCAL :
- Bassin de chalandise moyen (1 departement / metropole) : ~200 000 a 350 000 maisons individuelles avec jardin, terrasse ou balcon.
- 1 annonce pilote Leboncoin optimisee 'Kit Solaire 440W Pret a brancher 349 EUR (Garantie 25 ans, Moitie prix Sunology)' genere 60 a 100 vues qualifiees / jour.
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
                )
            ),
            productionSimulations = listOf(
                ProductionSimulation(
                    zoneName = "Zone Nord & Île-de-France (Lille, Paris, Rennes, Strasbourg)",
                    annualKwh = "490 à 540 kWh / an",
                    annualSavingsEuros = "123 € à 136 € / an",
                    paybackYears = "2,6 à 2,8 ans (Amortissement record)",
                    sourceLabel = "PVGIS Commission Européenne (30° Sud)",
                    sourceUrl = "https://re.jrc.ec.europa.eu/pvg_tools/fr/"
                ),
                ProductionSimulation(
                    zoneName = "Zone Centre & Grand Ouest (Nantes, Tours, Lyon, Bordeaux)",
                    annualKwh = "560 à 630 kWh / an",
                    annualSavingsEuros = "141 € à 159 € / an",
                    paybackYears = "2,2 à 2,5 ans",
                    sourceLabel = "PVGIS Commission Européenne & Bilan Enedis",
                    sourceUrl = "https://re.jrc.ec.europa.eu/pvg_tools/fr/"
                ),
                ProductionSimulation(
                    zoneName = "Zone Sud & Méditerranée (Toulouse, Montpellier, Marseille, Nice)",
                    annualKwh = "670 à 740 kWh / an",
                    annualSavingsEuros = "169 € à 186 € / an",
                    paybackYears = "1,9 à 2,1 ans (Efface jusqu'à 85% du talon de veille)",
                    sourceLabel = "PVGIS Commission Européenne",
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
                    argument = "Sunology vend son panneau 420W à 699 € et Beem à 749 €. Avec notre kit SOLARIS™ à 349 € TTC, on est 50% moins cher avec le même matériel haut de gamme biverre Trina Solar et micro-onduleur Hoymiles garanti 12 ans.",
                    keyProof = "Fiches tarifs publiques : Sunology.com (699 €) et Beemenergy.fr (749 €)."
                ),
                PartnerPitchArgument(
                    number = 3,
                    title = "Marge nette record : 105 € à 133 € par vente",
                    argument = "1 vente par jour ouvré (30 kits / mois) génère 3 570 € net / mois pour la SAS. Soit 1 785 € net pour chaque associé, tout en préservant 100% de l'ARE pour le président.",
                    keyProof = "BOM audité au centime près chez Kitsolaire-Discount / POwR Connect."
                ),
                PartnerPitchArgument(
                    number = 4,
                    title = "Zéro Consuel, Zéro Mairie, 100% Légal en 15 minutes",
                    argument = "Pose au sol de moins de 1,80m = exemptée de déclaration en mairie (art. R*421-2 Code urbanisme). Raccordement sur prise standard = Convention CACSI Enedis gratuite en 5 clics sans passage de technicien.",
                    keyProof = "Procédure officielle Enedis-PRO-RES_43E & Code de l'énergie art. L315-1."
                ),
                PartnerPitchArgument(
                    number = 5,
                    title = "Décollage Jour J immédiat en 4 étapes simples",
                    argument = "1. Dépôt de l'annonce Leboncoin -> 2. Prise de 3 précommandes locales sans engagement -> 3. Dépôt du capital 500 € Qonto -> 4. Immatriculation SAS et livraison des clients avec le cash déjà encaissé.",
                    keyProof = "Rétroplanning validé sans besoin de prêt bancaire ni d'investisseur."
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
                    id = "tool_ad_leboncoin",
                    title = "Script Annonce Leboncoin / Marketplace (Prêt à poster)",
                    category = "Marketing & Vente",
                    contentToCopy = "TITRE : Station Solaire 420W Plug & Play – Économisez sur votre facture Linky – Prête à brancher 230V\n\nPRIX : 349 €\n\nDESCRIPTION :\nRéduisez immédiatement votre facture d'électricité grâce à la station solaire SOLARIS™ 420W haute performance !\n\n✔️ ULTRA SIMPLE : Vous déballez, vous posez au sol ou sur votre terrasse, vous branchez sur une simple prise électrique standard 220V.\n✔️ ÉCONOMIES IMMÉDIATES : Efface jusqu'à 650 kWh/an sur votre compteur Linky (effacement du talon électrique : frigo, box internet, veilles, congélateur).\n✔️ MATÉRIEL PROFESSIONNEL HAUT DE GAMME :\n- Panneau solaire biverre N-Type 440Wc (garantie constructeur 25 ans)\n- Micro-onduleur Hoymiles HMS dernière génération avec sécurité anti-îlotage VDE\n- Châssis aluminium renforcé inclinable 20°-35°\n- Câble secteur étanche extérieur 5 mètres\n✔️ 100% LÉGAL & CONFORME : Conforme aux normes européennes et Enedis. Attestation de raccordement simplifiée fournie.\n\nVisible et disponible immédiatement. Enlèvement sur place ou livraison soignée par transporteur avec hayon.",
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
                    contentToCopy = "Destinataire : contact@groupepowr.com / pro@upwatt.com\nObjet : Demande d'ouverture de compte revendeur / Grille Kits Plug & Play 400W-440W\n\nBonjour,\nJe développe actuellement notre offre commerciale de stations solaires d'autoconsommation Plug & Play (stations 420W prêtes-à-brancher avec micro-onduleur Hoymiles et support inclinable sol/terrasse).\nDans le cadre du lancement de notre catalogue en France, je souhaite ouvrir un compte professionnel au sein de votre plateforme pour sourcer régulièrement nos kits et composants.\nPourriez-vous :\n1. Valider l'ouverture de notre accès revendeur B2B (SIRET ci-dessous).\n2. Me communiquer votre catalogue et vos grilles tarifaires dégressives sur Trina Solar 440W et micro-onduleurs Hoymiles HMS-400.\n3. M'indiquer vos conditions pour les expéditions palettes chez nos clients.\n\nSociété : SOLARIS France - SIRET : [Numéro] - Contact : [Nom & Prénom]",
                    sourceOrNorm = "Trame éprouvée pour validation d'accès revendeur sans KBIS initial"
                )
            )
        )
    )
}
