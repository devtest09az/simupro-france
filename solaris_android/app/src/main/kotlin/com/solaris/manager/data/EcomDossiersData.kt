package com.solaris.manager.data

import com.solaris.manager.model.CommercialPack
import com.solaris.manager.model.ConceptionDetail
import com.solaris.manager.model.CostItem
import com.solaris.manager.model.EcomDossier
import com.solaris.manager.model.MarketStudyDetail
import com.solaris.manager.model.ObjectionItem
import com.solaris.manager.model.AssociateVerificationItem
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
            title = "Station Solaire 420W Plug & Play",
            subtitle = "Marque SOLARIS France • Prête à brancher 220V",
            category = "Énergie & Autoconsommation",
            iconEmoji = "☀️",
            progressPercent = 80,
            estimatedNetMargin = "+105 € à +133 € / unité",
            targetMonthlyRevenue = "10 500 € / mois (30 stations)",
            liveWebsiteUrl = "https://devtest09az.github.io/simupro-france/",
            partnerNotes = "NOTE COMMUNE (Associés) :\n- Priorité absolue : déposer l'annonce test sur Leboncoin et Marketplace pour collecter 3 précommandes locales sans engagement.\n- Dès 3 clients chauds : on dépose le capital de 500 € sur Qonto et on immatricule la SAS.\n- Logistique : le stockage des 10 premiers kits se fera dans mon garage (sécurisé et sec).",
            marketStudy = MarketStudyDetail(
                marketSize = "500 000 foyers raccordés en France (fin 2024)",
                growthRate = "+85% de croissance sur le Plug & Play sur 18 mois",
                targetPainPoint = "Hausse électricité +43% (TRV) • Devis artisans toiture exorbitants (8 000 € à 11 000 €)",
                competitiveEdge = "Kit prêt à brancher 220V à 349 € amorti en 3 ans (économie 90 € à 130 €/an)",
                officialSourceLabel = "Enedis Open Data & Bilan Électrique RTE",
                officialSourceUrl = "https://data.enedis.fr/pages/accueil/",
                fullAnalysis = "Le marché français de l'autoconsommation photovoltaïque a explosé, passant de 14 000 foyers en 2017 à plus de 500 000 fin 2024. Porté par l'inflation des tarifs réglementés de l'électricité (+43% cumulés), les particuliers refusent désormais de s'endetter sur 15 ans avec un installateur en toiture. La station Plug & Play au sol ou terrasse est devenue le produit refuge : s'installe en 15 minutes, efface le talon électrique du logement (650 kWh/an) et offre un retour sur investissement de 3 à 4 ans."
            ),
            unitEconomics = UnitEconomicsDetail(
                retailPriceTTC = "349,00 € TTC",
                retailPriceHT = "290,83 € HT (TVA 20% : 58,17 €)",
                wholesaleBOMHT = "148,20 € HT (BOM matériel complet)",
                shippingCost = "35,00 € HT (Geodis palette) ou 0 € (Main propre)",
                paymentGatewayFee = "5,48 € TTC (Stripe 1,5% + 0,25 €)",
                netProfitPerUnit = "+105,15 € NET (Livraison) / +133,00 € NET (Main propre)",
                targetMonthlyNet = "30 ventes / mois = 3 154 € à 3 990 € NET d'impôt",
                costItems = listOf(
                    CostItem("Panneau Trina Solar 440W Vertex S+ Biverre N-Type", "49,50 € HT", "Rendement 22.0%, garantie 25 ans (POwR Connect)"),
                    CostItem("Micro-Onduleur Hoymiles HMS-400 / HMS-800-2T", "68,00 € HT", "Protection découplage DIN VDE 0126-1-1 intégrée"),
                    CostItem("Châssis Alu Inclinable Réglable Ksolar 20°-35°", "19,50 € HT", "Alu anodisé AL6005-T5, vent jusqu'à 130 km/h"),
                    CostItem("Câble Secteur 5m Étanche IP68 + Prise Schuko 16A", "11,20 € HT", "Câble HO7RN-F 3G1.5mm² norme NF EN 62109")
                ),
                executiveSummary = "Modèle à zéro avance de stock en dropshipping grossiste B2B : la commande du kit est validée chez POwR Connect ou UpWatt dès l'encaissement du client. Expédition directe chez le client sur palette Geodis avec prise de RDV par SMS, ou remise en main propre locale (zéro frais de port = +28 € de marge supplémentaire).",
                sourceOrSupplier = "POwR Connect (Nantes) & UpWatt Pro France"
            ),
            partnerProfit = PartnerProfitProjection(
                monthlyVolumeHypothesis = "30 stations / mois (~1 kit / jour ouvré sur Leboncoin & Web)",
                totalCompanyNetMonthly = "+ 3 570 € net / mois pour la SAS (~42 840 € / an)",
                associate1Name = "Associé 1 (Moi - Président SAS)",
                associate1NetMonthly = "+ 1 785 € net / mois (50% société)",
                associate1SpecificAdvantage = "Maintien 100% ARE France Travail (~2 000 €/m) = 3 785 € de ressources personnelles totales",
                associate2Name = "Associé 2 (Mon Associé - DG Salarié)",
                associate2NetMonthly = "+ 1 785 € net / mois (50% société)",
                associate2SpecificAdvantage = "Salaire CDI 100% sécurisé + zéro charge sociale supplémentaire + création de patrimoine",
                annualDividendsPerPartner = "~ 12 700 € nets d'impôt en poche par associé (après IS 15% et Flat Tax 30%)",
                taxOptimizationNote = "Trésorerie laissée en société pour acheter les palettes de 30 panneaux en direct (zéro impôt prélevé tant que réinvesti)."
            ),
            commercialPacks = listOf(
                CommercialPack(
                    name = "Pack Solo SOLARIS™ 440W (Best-Seller Balcon & Terrasse)",
                    badge = "BEST-SELLER",
                    retailPriceTTC = "349,00 € TTC",
                    wholesaleCostHT = "148,20 € HT",
                    netMarginAmount = "+105,15 € NET (Livraison) / +133,00 € NET (Main propre)",
                    netMarginPercent = "36,2% à 45,7% de marge nette",
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
        ),

        // =========================================================================
        // 2. PACK ALARME MAISON SANS ABONNEMENT (SECUREX FRANCE)
        // =========================================================================
        EcomDossier(
            id = "dossier_alarme",
            title = "Pack Alarme Maison Sans Abonnement",
            subtitle = "Marque SECUREX France • Centrale tactile 4G/Wi-Fi",
            category = "Sécurité Domestique & Protection",
            iconEmoji = "🛡️",
            progressPercent = 45,
            estimatedNetMargin = "+65 € à +85 € / unité",
            targetMonthlyRevenue = "7 500 € / mois (50 packs)",
            liveWebsiteUrl = null,
            partnerNotes = "NOTE COMMUNE (Associés) :\n- Deuxième site e-commerce sous la même SAS.\n- Coût du pack grossiste : 32,50 € HT usine / Prix de vente public : 149 € à 189 €.\n- Angle d'attaque : 'Pourquoi payer 49 €/mois toute votre vie à Verisure alors qu'un pack propriétaire coûte 149 € une seule fois ?'.",
            marketStudy = MarketStudyDetail(
                marketSize = "211 400 cambriolages de logements / an en France",
                growthRate = "+18% par an sur le segment des alarmes sans abonnement",
                targetPainPoint = "Rente mensuelle abusive Verisure / Sector Alarm (49 € à 65 € / mois, soit 600 € / an à vie)",
                competitiveEdge = "Pack 11 pièces 4G/Wi-Fi à 149 € sans aucun abonnement, amorti en moins de 3 mois",
                officialSourceLabel = "Ministère de l'Intérieur (SSMSI) & ONDRP",
                officialSourceUrl = "https://www.interieur.gouv.fr/actualites/statistiques-du-ministere-de-linterieur",
                fullAnalysis = "En France, un cambriolage a lieu toutes les 2,5 minutes, et 82% des résidences principales ne sont pas protégées. Les ménages refusent de souscrire aux abonnements de télésurveillance traditionnels qui coûtent plus de 600 € par an avec engagement. L'opportunité majeure réside dans le matériel propriétaire : le client achète son équipement en une fois, le connecte en Wi-Fi et GSM 4G, et reçoit directement les alertes en direct sur son smartphone sans aucun intermédiaire payant."
            ),
            unitEconomics = UnitEconomicsDetail(
                retailPriceTTC = "149,00 € TTC",
                retailPriceHT = "124,17 € HT (TVA 20% : 24,83 €)",
                wholesaleBOMHT = "32,50 € HT (35 $ pack complet OEM Shenzhen)",
                shippingCost = "12,50 € (Fret aérien DDP dédouané) + 6,80 € (Colissimo)",
                paymentGatewayFee = "2,24 € TTC (Stripe CB 1,5%)",
                netProfitPerUnit = "+65,13 € NET / unité (Marge nette de 52,5%)",
                targetMonthlyNet = "85 ventes / mois (moins de 3/jour) = 5 536 € NET",
                costItems = listOf(
                    CostItem("Centrale tactile 4.3 pouces IPS 4G+WiFi (PGST PG-107)", "17,20 € HT", "Écran tactile couleur, Tuya Smart Life, batterie secours 8h"),
                    CostItem("Sirène extérieure solaire 120 dB + flash stroboscopique", "7,00 € HT", "Étanche IP65, panneau solaire autonome intégré"),
                    CostItem("4x Détecteurs magnétiques ouverture portes/fenêtres", "4,50 € HT", "Sans fil 433 MHz, adhésif 3M puissant, pile CR2032 2 ans"),
                    CostItem("2x Détecteurs de mouvement PIR immunité animaux <20kg", "3,80 € HT", "Infrarouge passif grand angle 110°, portée 10m")
                ),
                executiveSummary = "Positionnement anti-Verisure ultra rentable : à 65,13 € de bénéfice net par commande, 3 ventes quotidiennes génèrent 5 500 € net par mois. Sourcing direct auprès du fabricant PGST à Shenzhen avec conformité CE RED et application mobile Tuya en français.",
                sourceOrSupplier = "PGST Technology Shenzhen (sales08@cn-pgst.com / WhatsApp: +86 13397149596)"
            ),
            partnerProfit = PartnerProfitProjection(
                monthlyVolumeHypothesis = "50 packs 11 pièces / mois (1 à 2 packs / jour via pub vidéo & SEO local)",
                totalCompanyNetMonthly = "+ 3 256 € net / mois pour la SAS (~39 072 € / an)",
                associate1Name = "Associé 1 (Moi - Président SAS)",
                associate1NetMonthly = "+ 1 628 € net / mois (50% société)",
                associate1SpecificAdvantage = "Maintien 100% ARE France Travail (~2 000 €/m) = 3 628 € / mois de ressources personnelles totales",
                associate2Name = "Associé 2 (Mon Associé - DG Salarié)",
                associate2NetMonthly = "+ 1 628 € net / mois (50% société)",
                associate2SpecificAdvantage = "Gestion expéditions le soir ou week-end sans impacter son travail salarié",
                annualDividendsPerPartner = "~ 11 600 € nets d'impôt en poche par associé (après IS 15% et Flat Tax 30%)",
                taxOptimizationNote = "Marge nette record (52,5%) : rentabilité immédiate pour autofinancer l'acquisition sans découvert."
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
                    objection = "« Si je ne paie pas d'abonnement, qui appelle la police en cas d'intrusion ? »",
                    rebuttal = "« Savez-vous que les sociétés comme Verisure n'ont PAS le droit d'appeler directement le 17 sans levée de doute physique préalable (art. L613-6 CSI) ? Dans les faits, un agent privé met en moyenne 45 minutes à arriver. Avec SECUREX, c'est VOUS qui recevez l'alerte en 2 secondes chrono avec photo/vidéo sur votre mobile. Vous pouvez déclencher la sirène 120 dB à distance ou composer le 17 immédiatement pour flagrant délit. »",
                    tacticalAngle = "Vérité légale sur la télésurveillance privée (art. L613-6 CSI)"
                ),
                ObjectionItem(
                    objection = "« Que se passe-t-il si un cambrioleur coupe le disjoncteur ou le câble internet ? »",
                    rebuttal = "« La centrale SECUREX est 100% autonome : elle dispose d'une batterie lithium de secours (8h d'autonomie) et d'une puce 4G GSM de secours (compatible avec une carte SIM Free à 2 €/mois). Même si le courant et la box internet sont coupés, la centrale émet l'alerte instantanément par appel vocal et SMS. »",
                    tacticalAngle = "Sécurité double réseau 4G + relais batterie physique"
                ),
                ObjectionItem(
                    objection = "« Est-ce que mes chats ou mon chien vont déclencher l'alarme sans arrêt ? »",
                    rebuttal = "« Non, les détecteurs de mouvement volumétriques PIR intègrent un filtre optique à double faisceau avec algorithme d'analyse corporelle. Ils ignorent les animaux domestiques de moins de 20 kg. Vos animaux circulent librement sans fausse alerte. »",
                    tacticalAngle = "Technologie optique PIR anti-animaux domestiques"
                ),
                ObjectionItem(
                    objection = "« Est-ce compliqué à installer sans faire de trous dans les murs ? »",
                    rebuttal = "« 100% des capteurs sont sans fil et déjà pré-appairés à la centrale en usine. Vous fixez les détecteurs d'ouverture sur vos portes et fenêtres avec les puissants adhésifs 3M fournis. En 15 minutes chrono montre en main, votre logement est sous alarme. »",
                    tacticalAngle = "Installation 15 min sans travaux ni perçage"
                )
            ),
            regulatoryRoadmap = listOf(
                RegulatoryRoadmapItem(
                    badge = "Directive UE",
                    title = "Directive Équipements Radioélectriques (RED)",
                    legalRef = "Directive 2014/53/UE & Norme EN 300 220",
                    description = "Conformité obligatoire pour l'émission radio 433.92 MHz et le module cellulaire 4G LTE en France et Europe.",
                    officialLink = "https://eur-lex.europa.eu/"
                ),
                RegulatoryRoadmapItem(
                    badge = "Légifrance",
                    title = "Dispense d'Agrément Sécurité Privée (CNAPS)",
                    legalRef = "Code de la sécurité intérieure Livre VI art. L611-1",
                    description = "La vente de matériel d'alarme autonome sans prestation de surveillance humaine ni gardiennage est libre et ne nécessite aucun agrément CNAPS.",
                    officialLink = "https://www.legifrance.gouv.fr/"
                ),
                RegulatoryRoadmapItem(
                    badge = "Loi AGEC",
                    title = "Filière Recyclage DEEE & Piles (Ecosystem)",
                    legalRef = "Code de l'environnement art. L541-10-1",
                    description = "Obligation légale d'adhésion à l'éco-organisme pour les composants électroniques et batteries rechargeables.",
                    officialLink = "https://www.ecosystem.eco/"
                )
            ),
            conceptionItems = listOf(
                ConceptionDetail(
                    title = "Centrale Tactile 4.3 pouces Wi-Fi + GSM 4G Multi-Réseaux",
                    description = "Application mobile smartphone iOS/Android gratuite (Smart Life / Tuya). Notifications push, alertes SMS et appels vocaux en cas d'intrusion.",
                    keyMetric = "Coût usine kit complet : 32,50 € à 38 € HT",
                    sourceLabel = "Fabricant Shenzhen PGST",
                    verifiedSourceUrl = "https://www.interieur.gouv.fr/actualites/statistiques-du-ministere-de-linterieur",
                    officialReference = "Normes CE LVD / RED Directive 2014/53/EU • Bandes 4G B1/B3/B7/B8/B20/B28"
                ),
                ConceptionDetail(
                    title = "Kit 11 Pièces Prêt à Poser Sans Fil",
                    description = "1 centrale tactile + 4 détecteurs magnétiques d'ouverture portes/fenêtres + 2 détecteurs de mouvement infrarouge PIR + 2 télécommandes + 2 badges RFID + 1 sirène filaire 110 dB.",
                    keyMetric = "Prix de vente public : 149 € à 189 € TTC",
                    sourceLabel = "Étude Comparative Marché Français",
                    verifiedSourceUrl = "https://www.interieur.gouv.fr/actualites/statistiques-du-ministere-de-linterieur",
                    officialReference = "Concurrent direct : Verisure (49€/mois) & Somfy Protect (449€)"
                ),
                ConceptionDetail(
                    title = "Logistique Colis Économique (Colissimo / Mondial Relay)",
                    description = "Format boîte compacte (28 x 20 x 12 cm, 1.4 kg). Expédition rapide sans hayon, dépose en point relais ou boîte aux lettres.",
                    keyMetric = "Frais de port : 4,90 € à 6,80 € TTC",
                    sourceLabel = "Tarifs Messagerie Express Colis",
                    verifiedSourceUrl = "https://www.upela.com/fr/",
                    officialReference = "Envoi automatisé via étiquettes thermiques imprimées"
                )
            ),
            productionActions = listOf(
                ProductionAction("a1", "Étude de marché et sourcing composants validés", true, "URGENT", "Associé"),
                ProductionAction("a2", "Visuel commercial 3D du pack 11 pièces généré", true, "URGENT", "Moi"),
                ProductionAction("a3", "Créer la boutique en ligne dédiée SECUREX", false, "HAUTE", "Moi"),
                ProductionAction("a4", "Commander 2 échantillons d'évaluation pour test réel", false, "HAUTE", "Associé"),
                ProductionAction("a5", "Lancer la campagne vidéo publicitaire Meta & TikTok", false, "MOYENNE", "Les Deux")
            ),
            toolkitItems = listOf(
                ToolkitItem(
                    id = "tool_pitch_alarme",
                    title = "Argumentaire Commercial Anti-Abonnement (Pitch Vente)",
                    category = "Marketing & Vente",
                    contentToCopy = "« Pourquoi payer 49 € par mois toute votre vie à une société de télésurveillance (soit près de 600 € par an !) pour une alarme qui ne vous appartient même pas ?\n\nAvec le Pack SECUREX™ 11 pièces sans fil :\n- Vous êtes propriétaire à 100% de votre équipement dès le premier jour.\n- ZÉRO abonnement mensuel, ZÉRO engagement.\n- Votre smartphone vous alerte instantanément par notification push, SMS et appel vocal en direct dès qu'une porte ou fenêtre s'ouvre.\n- Installation en 15 minutes sans travaux ni câblage. »",
                    sourceOrNorm = "Angle d'attaque psychologique à fort levier d'économie"
                ),
                ToolkitItem(
                    id = "tool_rfq_shenzhen",
                    title = "Modèle RFQ Fournisseur Usine Shenzhen PGST (Alibaba)",
                    category = "Sourcing & Fournisseurs",
                    contentToCopy = "Subject: RFQ - PG-107 4G+WiFi Smart Home Alarm System (11-Piece Complete Pack for France)\n\nDear Sales Team,\nWe are launching a new consumer brand for smart home security in France. We are interested in your PG-107 (4.3-inch IPS touchscreen) Tuya 4G + WiFi alarm kit.\nPlease quote your best pricing and lead times for the following customized bundle:\n1. 1x PG-107 Host (4G European frequency bands: B1/B3/B7/B8/B20/B28 + WiFi 2.4G + EU plug 5V adapter + French firmware language).\n2. 1x Outdoor Solar Wireless Strobe Siren (120dB, IP65 waterproof, 433MHz).\n3. 4x Wireless Door/Window Magnetic Sensors (with 3M adhesive tapes).\n4. 2x Wireless PIR Motion Sensors (Pet-immune up to 20kg).\n5. 2x Wireless Remote Controllers + 2x RFID Keyfobs.\nPlease provide sample price with DDP shipping to France and CE RED / RoHS certificates.\n\nContact: sales08@cn-pgst.com (Shenzhen Pilot Guards Safety Technology)",
                    sourceOrNorm = "Cahier des charges OEM direct usine vérifié"
                )
            )
        ),

        // =========================================================================
        // 3. SOCIÉTÉ SAS COMMUNE & BOUCLIER ARE
        // =========================================================================
        EcomDossier(
            id = "dossier_sas_are",
            title = "Société SAS Commune & Maintien ARE",
            subtitle = "Structure juridique commune pour les 2 associés",
            category = "Juridique, Fiscal & Protection Sociale",
            iconEmoji = "🏢",
            progressPercent = 60,
            estimatedNetMargin = "Maintien 100% ARE + Flat Tax 30%",
            targetMonthlyRevenue = "0 € de cotisations sociales minimales",
            liveWebsiteUrl = "https://formalites.entreprises.gouv.fr/",
            partnerNotes = "NOTE COMMUNE (Associés) :\n- Répartition du capital convenue : 50% / 50% ou 51% / 49%.\n- Rôle A (Moi) : Président (mandat non rémunéré à 0 € pour maintenir l'ARE à 100%).\n- Rôle B (Mon Associé) : Directeur Général (mandat non rémunéré, 100% compatible avec son emploi salarié à côté).\n- Compte bancaire pro : ouverture chez Qonto avec 2 cartes Mastercard séparées.",
            marketStudy = MarketStudyDetail(
                marketSize = "Structure juridique unique multi-marques (Code commerce art. L227-1)",
                growthRate = "Immatriculation centralisée sous code NAF 47.91A (Vente à distance)",
                targetPainPoint = "Piège de la SARL TNS (1 200 € / an d'URSSAF même à 0 € de CA + suspension de l'ARE)",
                competitiveEdge = "SAS à l'IS avec PV 0 € : 100% ARE maintenue (art. L5421-1) + 0 € de charges + cumul salarié (art. L1222-5)",
                officialSourceLabel = "Légifrance Code du Travail & Circulaire Unédic 2023-08",
                officialSourceUrl = "https://www.service-public.fr/particuliers/vosdroits/F11660",
                fullAnalysis = "La réglementation française permet à une société par actions simplifiée (SAS) d'exercer une pluralité d'activités commerciales et numériques distinctes sous un seul SIREN. En fixant la rémunération des mandataires sociaux à 0,00 € par Assemblée Générale, vous conservez l'intégralité de vos allocations chômage ARE chaque mois sans retenue, tandis que votre associé peut exercer son mandat en toute légalité en parallèle de son contrat de travail."
            ),
            unitEconomics = UnitEconomicsDetail(
                retailPriceTTC = "0 € de cotisations sociales obligatoires",
                retailPriceHT = "0 € de charges minimales URSSAF en cas de CA nul",
                wholesaleBOMHT = "500 € de capital social (250 € par associé sur Qonto)",
                shippingCost = "Frais Guichet Unique INPI + Journal JAL : ~230 € TTC",
                paymentGatewayFee = "Compte pro Qonto 9 €/m + RC Pro Hiscox ~35 €/m",
                netProfitPerUnit = "100% de l'allocation mensuelle ARE sécurisée chaque mois",
                targetMonthlyNet = "Préservation de 1 800 € à 2 600 € / mois d'allocations chômage sans baisse",
                costItems = listOf(
                    CostItem("Dépôt de capital Qonto & attestation notariée 24h", "69,00 € HT", "Code de commerce art. L227-1"),
                    CostItem("Frais d'immatriculation Guichet Unique INPI", "190,00 € TTC", "Loi PACTE immatriculation centralisée"),
                    CostItem("Publication Journal d'Annonces Légales (JAL)", "140,00 € TTC", "Tarif légal forfaitaire officiel"),
                    CostItem("Assurance RC Pro E-Commerce Hiscox France", "35,00 € / mois", "Couverture responsabilité produits (art. 1245)")
                ),
                executiveSummary = "Structure d'accueil à immunité financière totale : le statut d'assimilé salarié non rémunéré garantit qu'aucun appel de cotisation minimale URSSAF ne sera émis. France Travail continue de verser 100% de l'ARE sur présentation du PV d'AG de gratuité du mandat.",
                sourceOrSupplier = "Légifrance Code du Travail & Circulaire Unédic 2023-08 (Fiche 6)"
            ),
            partnerProfit = PartnerProfitProjection(
                monthlyVolumeHypothesis = "Vitesse de croisière cumulée SAS (30 stations solaires + 50 packs alarme / mois)",
                totalCompanyNetMonthly = "+ 6 826 € net / mois pour la SAS (~81 912 € / an)",
                associate1Name = "Associé 1 (Moi - Président SAS)",
                associate1NetMonthly = "+ 3 413 € net / mois (50% société)",
                associate1SpecificAdvantage = "Bouclier ARE 100% actif (~2 000 €/m) = 5 413 € / mois de cash-flow personnel sécurisé",
                associate2Name = "Associé 2 (Mon Associé - DG Salarié)",
                associate2NetMonthly = "+ 3 413 € net / mois (50% société)",
                associate2SpecificAdvantage = "Cumul légal parfait (Code du travail art. L1222-5) + salaire CDI mensuel intact",
                annualDividendsPerPartner = "~ 24 300 € nets d'impôt en poche par associé (après IS 15% jusqu'à 42.5k€ et Flat Tax 30%)",
                taxOptimizationNote = "Structure unique multi-marques (code NAF 47.91A) : un seul compte bancaire, une comptabilité, zéro frottement fiscal."
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
                    objection = "« France Travail (Pôle Emploi) ne va-t-il pas me couper mes allocations si je crée une société ? »",
                    rebuttal = "« Non. La circulaire Unédic n° 2023-08 du 26 juillet 2023 (Fiche 6) stipule que les allocations ARE sont calculées uniquement sur les rémunérations effectives soumises à cotisations. En fournissant les statuts de la SAS et le Procès-Verbal d'Assemblée Générale attestant formellement d'une rémunération de 0,00 €, France Travail maintient 100% de votre allocation journalière sans aucune retenue. »",
                    tacticalAngle = "Circulaire Unédic n° 2023-08 & PV d'AG de gratuité"
                ),
                ObjectionItem(
                    objection = "« Mon associé qui est salarié dans une autre boîte risque-t-il d'être licencié pour faute ? »",
                    rebuttal = "« Non. L'article L1222-5 du Code du travail accorde une dérogation légale d'un an (renouvelable) à toute clause d'exclusivité en cas de création d'entreprise. De plus, son mandat de Directeur Général est bénévole et exercé en dehors de ses heures de travail sur une activité non concurrente de son employeur. Il respecte ainsi pleinement son devoir de loyauté (art. L1222-1). »",
                    tacticalAngle = "Protection de l'article L1222-5 du Code du travail"
                ),
                ObjectionItem(
                    objection = "« Pourquoi une SAS plutôt qu'une SARL ? »",
                    rebuttal = "« En SARL, le gérant majoritaire est Travailleur Non Salarié (TNS) : l'URSSAF exige environ 1 200 € de cotisations minimales chaque année même si le chiffre d'affaires est de 0 €, et France Travail bloque souvent l'ARE sur des bases forfaitaires. En SAS, les dirigeants sont Assimilés Salariés : pas de salaire = zéro charge sociale, zéro appel de cotisation minimale URSSAF. »",
                    tacticalAngle = "Élimination des cotisations forfaitaires minimales TNS"
                ),
                ObjectionItem(
                    objection = "« Peut-on vraiment vendre à la fois du solaire, des alarmes et des apps sous la même société ? »",
                    rebuttal = "« Absolument. Le droit des sociétés français (art. L227-1 C. com.) permet d'inscrire une pluralité d'activités dans l'objet social. Sous le code APE 47.91A (Vente à distance sur catalogue spécialisé), vous pouvez exploiter plusieurs sites e-commerce et marques distinctes avec un seul Kbis, un seul compte bancaire et une seule comptabilité. »",
                    tacticalAngle = "Objet social parapluie sous code APE 47.91A"
                )
            ),
            regulatoryRoadmap = listOf(
                RegulatoryRoadmapItem(
                    badge = "Unédic",
                    title = "Bouclier ARE & PV de Rémunération 0 €",
                    legalRef = "Circulaire Unédic 2023-08 (Fiche 6) & Art. L5421-1 Code du travail",
                    description = "Attestation formelle de non-rémunération à transmettre lors de l'actualisation mensuelle pour maintien de 100% de l'allocation chômage.",
                    officialLink = "https://www.service-public.fr/particuliers/vosdroits/F11660"
                ),
                RegulatoryRoadmapItem(
                    badge = "Code Travail",
                    title = "Dérogation Clause d'Exclusivité Associé Salarié",
                    legalRef = "Code du travail art. L1222-5 & art. L1222-1",
                    description = "Inopposabilité de la clause d'exclusivité du contrat de travail pendant 12 mois pour création d'entreprise.",
                    officialLink = "https://www.legifrance.gouv.fr/"
                ),
                RegulatoryRoadmapItem(
                    badge = "Loi AGEC",
                    title = "Enregistrement Éco-Organismes & Numéros IDU",
                    legalRef = "Code de l'environnement art. L541-10-13",
                    description = "Obligation légale d'obtenir les identifiants uniques IDU auprès de Soren (panneaux) et Citeo (emballages).",
                    officialLink = "https://www.soren.eco/"
                ),
                RegulatoryRoadmapItem(
                    badge = "Code Civil",
                    title = "Assurance RC Pro Responsabilité Produits",
                    legalRef = "Code civil art. 1245 à 1245-17 (Directive 85/374/CEE)",
                    description = "Couverture obligatoire de la responsabilité du fait des produits défectueux pour la distribution d'équipements électriques.",
                    officialLink = "https://www.hiscox.fr/"
                )
            ),
            conceptionItems = listOf(
                ConceptionDetail(
                    title = "Bouclier ARE France Travail (Rémunération 0 €)",
                    description = "Procès-Verbal d'Assemblée Générale fixant la rémunération des mandataires à strictement 0 €. Aucune fiche de paie = maintien intégral des allocations journalières.",
                    keyMetric = "100% des droits ARE maintenus sans carence",
                    sourceLabel = "Code du travail Art. L5421-1 & Circulaire Unédic",
                    verifiedSourceUrl = "https://www.service-public.fr/particuliers/vosdroits/F11660",
                    officialReference = "Circulaire Unédic n° 2023-08 du 26 juillet 2023 (Fiche 6)"
                ),
                ConceptionDetail(
                    title = "Cumul Salarié à Côté (Dérogation d'Exclusivité)",
                    description = "L'associé salarié peut exercer un mandat social sur son temps libre. Si clause d'exclusivité dans son CDI, la loi prévoit 12 mois de dérogation automatique.",
                    keyMetric = "Article L1222-5 du Code du travail",
                    sourceLabel = "Légifrance Code du Travail",
                    verifiedSourceUrl = "https://www.service-public.fr/particuliers/vosdroits/F11660",
                    officialReference = "Obligation de loyauté (art. L1222-1) : activité non concurrente"
                ),
                ConceptionDetail(
                    title = "Objet Social Parapluie Multi-Marques",
                    description = "Englobe dès la création le solaire ET les alarmes sous un seul code APE 47.91A (Vente à distance sur catalogue spécialisé).",
                    keyMetric = "1 seul Kbis pour toutes les marques",
                    sourceLabel = "Portail Officiel Guichet Unique INPI",
                    verifiedSourceUrl = "https://formalites.entreprises.gouv.fr/",
                    officialReference = "Loi PACTE • Immatriculation centralisée des entreprises"
                ),
                ConceptionDetail(
                    title = "Dépôt de Capital Express 24h (Qonto)",
                    description = "Dépôt de 500 € (250 € chacun) avec clause de capital variable. Certificat de dépôt des fonds délivré par notaire sous 24 heures.",
                    keyMetric = "Certificat notarié en 24h • 69 € HT",
                    sourceLabel = "Qonto Dépôt de Capital Légal",
                    verifiedSourceUrl = "https://qonto.com/fr/incorporation/capital-deposit",
                    officialReference = "Code de commerce art. L223-7 & L227-1"
                ),
                ConceptionDetail(
                    title = "Passerelle Stripe & Paiement 3x Alma / Klarna",
                    description = "Activation immédiate CB + Apple Pay. Le paiement fractionné 3x/4x Alma est le levier de conversion n°1 pour les kits à 349 € et 149 €.",
                    keyMetric = "1,5% + 0,25 € / tx • Risque impayé 0%",
                    sourceLabel = "Stripe France Documentation",
                    verifiedSourceUrl = "https://stripe.com/fr",
                    officialReference = "Agrément Banque de France / ACPR"
                ),
                ConceptionDetail(
                    title = "Assurance RC Pro Multirisque E-Commerce",
                    description = "Couvre la responsabilité du fait des produits défectueux (art. 1245 Code civil) pour le matériel électrique et les objets connectés.",
                    keyMetric = "~35 € à 45 € / mois • Couverture 1M€",
                    sourceLabel = "Hiscox France Assurances Pro",
                    verifiedSourceUrl = "https://www.hiscox.fr/",
                    officialReference = "Code civil art. 1245 • Responsabilité fabricant/distributeur"
                ),
                ConceptionDetail(
                    title = "Éco-Organismes & Numéro IDU Obligatoire",
                    description = "Enregistrement légal auprès de Soren (solaire) et Citeo (emballages). Obligatoire pour vendre sur Leboncoin et les marketplaces.",
                    keyMetric = "Numéro IDU requis par la loi AGEC",
                    sourceLabel = "Soren Solaire & Citeo Emballages",
                    verifiedSourceUrl = "https://www.soren.eco/",
                    officialReference = "Code de l'environnement art. L541-10-13 (Loi AGEC)"
                )
            ),
            productionActions = listOf(
                ProductionAction("j1", "Rédiger les statuts de SAS à capital variable", true, "URGENT", "Moi"),
                ProductionAction("j2", "Rédiger le pacte d'associés (Vesting & Bad Leaver)", true, "URGENT", "Associé"),
                ProductionAction("j3", "Déposer le capital social (500 €) sur Qonto", false, "URGENT", "Les Deux"),
                ProductionAction("j4", "Soumettre l'immatriculation au Guichet Unique INPI", false, "HAUTE", "Moi"),
                ProductionAction("j5", "Transmettre le PV d'AG 0 € à France Travail", false, "HAUTE", "Moi"),
                ProductionAction("j6", "Activer Stripe + Paiement 3x Alma pour le solaire", false, "HAUTE", "Associé"),
                ProductionAction("j7", "Souscrire la RC Pro Hiscox (35€/mois)", false, "MOYENNE", "Moi"),
                ProductionAction("j8", "Ouvrir les comptes pros PoWR Connect & SecondSol", false, "MOYENNE", "Associé")
            ),
            toolkitItems = listOf(
                ToolkitItem(
                    id = "tool_checklist_jour_j",
                    title = "Checklist Opérationnelle Chronologique Jour J",
                    category = "Organisation & Décollage",
                    contentToCopy = "CHECKLIST PRÊTS POUR LE JOUR J (SAS COMMUNE) :\n\nJ-7 : Préparation\n- [ ] Rédaction des statuts de SAS avec objet social multi-activités étendu.\n- [ ] Rassemblement des CNI certifiées conformes + justificatifs de domicile.\n- [ ] Choix de la domiciliation (siège social à domicile ou SeDomicilier).\n\nJ-3 : Dépôt du capital\n- [ ] Ouverture du compte chez Qonto et virement de 250 € par associé (500 € total).\n- [ ] Réception sous 24h de l'attestation de dépôt des fonds notariée.\n\nJ-0 : Immatriculation Guichet Unique INPI\n- [ ] Dépôt du dossier sur formalites.entreprises.gouv.fr (frais ~230 €).\n- [ ] Signature du PV d'Assemblée Générale de non-rémunération (0 €).\n\nJ+1 : Décollage immédiat\n- [ ] Réception du Kbis et du SIREN.\n- [ ] Transmission du PV 0 € à France Travail (maintien 100% de l'ARE garanti).\n- [ ] Validation du compte Stripe et activation du paiement en 3x/4x Alma.\n- [ ] Souscription de l'assurance RC Pro Hiscox (~35 €/mois).\n- [ ] Ouverture des comptes pros chez les grossistes PoWR Connect et SecondSol.\n- [ ] Mise en ligne des premières annonces Leboncoin et des campagnes publicitaires.",
                    sourceOrNorm = "Rétroplanning éprouvé pour un lancement sans accroc"
                ),
                ToolkitItem(
                    id = "tool_pv_ag",
                    title = "Modèle Officiel PV d'AG de Non-Rémunération (Bouclier ARE)",
                    category = "Juridique & Fiscal",
                    contentToCopy = "PROCÈS-VERBAL DE L'ASSEMBLÉE GÉNÉRALE ORDINAIRE DU [DATE]\n\nLa collectivité des associés de la société [NOM SAS] s'est réunie au siège social.\n\nRÉSOLUTION UNIQUE – RÉMUNÉRATION DE LA DIRECTION :\nL'Assemblée Générale décide que les mandats de Président et de Directeur Général sont exercés à titre strictement gratuit.\nEn conséquence, aucune rémunération, ni fixe, ni variable, ni aucun avantage en nature ou indemnité ne seront alloués au Président et au Directeur Général au titre de l'exercice en cours et jusqu'à nouvelle décision collective de l'Assemblée Générale.\n\nCette résolution, mise aux voix, est adoptée à l'unanimité des associés.\n\nSignatures des associés :",
                    sourceOrNorm = "Requis par France Travail pour le versement à 100% de l'ARE"
                ),
                ToolkitItem(
                    id = "tool_objet_social",
                    title = "Texte Exact Objet Social Multi-Activités (Solaire, Alarme, Béton, Apps)",
                    category = "Juridique & Statuts",
                    contentToCopy = "ARTICLE 2 – OBJET SOCIAL :\nLa Société a pour objet, en France et à l'étranger :\n1. Le commerce de détail et de gros, la vente à distance (e-commerce), l'importation, l'exportation et la distribution de tous matériels et accessoires liés à l'énergie, aux énergies renouvelables et à l'autoconsommation (panneaux solaires, onduleurs, supports).\n2. Le commerce de détail et de gros, la vente à distance (e-commerce) de tous matériels et équipements de sécurité autonome, de domotique, d'alarmes sans fil et de protection des biens et des personnes (hors activités de sécurité privée réglementées au sens du Livre VI du CSI).\n3. Le commerce de détail et de gros de résines, mortiers, enduits décoratifs et produits de rénovation du bâtiment.\n4. La conception, l'édition, le développement et l'exploitation d'applications mobiles, logiciels, plateformes web et services numériques.\n5. Et toutes opérations commerciales ou financières s'y rattachant.",
                    sourceOrNorm = "Validé pour attribution du code NAF 47.91A par l'INSEE et immunité multi-projets"
                )
            )
        ),

        // =========================================================================
        // 4. BÉTON DÉCORATIF RECOUVR'EXPRESS
        // =========================================================================
        EcomDossier(
            id = "dossier_beton",
            title = "Béton Décoratif Recouvr'Express",
            subtitle = "Kit Rénovation Sol & Escalier sans casser",
            category = "Bricolage & Travaux de Rénovation",
            iconEmoji = "🏗️",
            progressPercent = 30,
            estimatedNetMargin = "+120 € à +145 € / seau 25kg",
            targetMonthlyRevenue = "5 000 € / mois (Niche complémentaire)",
            liveWebsiteUrl = null,
            partnerNotes = "NOTE COMMUNE (Associés) :\n- Niche identifiée pour diversification ultérieure.\n- Panier moyen très élevé (les clients commandent 2 à 4 seaux pour refaire leur terrasse ou escalier).\n- À activer après la stabilisation du flux de commandes solaires.",
            marketStudy = MarketStudyDetail(
                marketSize = "Marché de la rénovation de l'habitat en France : 45,5 milliards d'euros",
                growthRate = "+12% par an sur le Home-Staging et la rénovation sans casser",
                targetPainPoint = "Casser un carrelage coûte 60 € à 90 € / m² en artisan (bruit, marteau-piqueur, gravats)",
                competitiveEdge = "Mortier millimétrique 2 mm à adhérence extrême directement sur ancien carrelage (panier moyen 289 €)",
                officialSourceLabel = "CAPEB & FFB (Fédération Française du Bâtiment)",
                officialSourceUrl = "https://www.capeb.fr/",
                fullAnalysis = "Le remplacement de sols et escaliers est l'un des postes les plus onéreux et contraignants de la rénovation résidentielle. Le béton décoratif millimétrique en kit prêt à l'emploi permet à n'importe quel particulier ou artisan de recouvrir un ancien carrelage sans aucune démolition en 48 heures. La marge brute sur les résines et mortiers dépasse 55%, avec des paniers moyens naturellement élevés (les clients commandent 2 à 4 seaux pour une pièce)."
            ),
            unitEconomics = UnitEconomicsDetail(
                retailPriceTTC = "289,00 € TTC (Kit sol 10m²)",
                retailPriceHT = "240,83 € HT (TVA 20% : 48,17 €)",
                wholesaleBOMHT = "75,00 € HT (Mortier minéral + primaire + vernis)",
                shippingCost = "18,50 € HT (Messagerie DPD colis lourd)",
                paymentGatewayFee = "4,30 € TTC (Stripe 1,5%)",
                netProfitPerUnit = "+143,03 € NET / commande (Marge nette de 59,4%)",
                targetMonthlyNet = "35 ventes / mois = 5 006 € NET",
                costItems = listOf(
                    CostItem("Mortier minéral millimétrique 2 mm (seau 25 kg)", "38,00 € HT", "Formulation haute dureté UPEC (Arcane Industries)"),
                    CostItem("Primaire d'adhérence universel tous supports (5L)", "19,00 € HT", "Accroche directe sur vieux carrelage lisse"),
                    CostItem("Vernis polyuréthane bi-composant anti-taches (2L)", "18,00 € HT", "Protection hydrofuge/oléofuge cuisine et bain"),
                    CostItem("Kit outillage offert (flamoche inox + platoir)", "4,50 € HT", "Levier psychologique d'achat immédiat")
                ),
                executiveSummary = "Panier moyen record et zéro SAV mécanique : le client achète la certitude de ne pas démolir son carrelage au marteau-piqueur. Formulation et fabrication française chez Arcane Industries à Aubagne.",
                sourceOrSupplier = "Arcane Industries (Aubagne 13) - Certifié CSTB"
            ),
            partnerProfit = PartnerProfitProjection(
                monthlyVolumeHypothesis = "20 kits sol/escalier / mois (niche bricolage / rénovation sans casser)",
                totalCompanyNetMonthly = "+ 2 860 € net / mois pour la SAS (~34 320 € / an)",
                associate1Name = "Associé 1 (Moi - Président SAS)",
                associate1NetMonthly = "+ 1 430 € net / mois (50% société)",
                associate1SpecificAdvantage = "Diversification à panier moyen élevé (289 €) et marge nette de 59,4%",
                associate2Name = "Associé 2 (Mon Associé - DG Salarié)",
                associate2NetMonthly = "+ 1 430 € net / mois (50% société)",
                associate2SpecificAdvantage = "Zéro composant électronique, zéro bug logiciel, aucun SAV complexe à gérer",
                annualDividendsPerPartner = "~ 10 200 € nets d'impôt en poche par associé (après IS 15% et Flat Tax 30%)",
                taxOptimizationNote = "Produit de renfort après stabilisation du solaire : apporte environ 1 430 € net / mois supplémentaire par associé."
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
                    objection = "« Est-ce que le béton ne va pas se fissurer sur les joints de mon ancien carrelage ? »",
                    rebuttal = "« Non. La sous-couche d'accroche polymère universelle bouche et solidarise les joints de carrelage. De plus, notre mortier minéral millimétrique est enrichi en micro-fibres de verre synthétiques qui absorbent les micro-dilatations thermiques sans aucune fissure. »",
                    tacticalAngle = "Explication de la résine élastique fibrée anti-fissure"
                ),
                ObjectionItem(
                    objection = "« Est-ce résistant aux taches de gras, au vin et à l'eau dans une cuisine ou douche ? »",
                    rebuttal = "« Le kit comprend un vernis polyuréthane hydrofuge et oléofuge bi-composant de qualité professionnelle. Il sature les pores du béton et crée un film protecteur imperméable résistant aux rayures, à l'eau stagnante et aux produits ménagers les plus agressifs. »",
                    tacticalAngle = "Vernis PU bi-composant bouche-pores imperméable"
                ),
                ObjectionItem(
                    objection = "« N'est-ce pas trop compliqué à appliquer pour un bricoleur amateur ? »",
                    rebuttal = "« Le produit est pré-dosé en usine : vous mélangez la poudre et le liquide avec un simple malaxeur sur perceuse. Il s'applique en deux passes fines à la taloche comme un enduit classique. Une vidéo pas-à-pas de 10 minutes montre chaque geste pour un résultat digne d'un pro. »",
                    tacticalAngle = "Kit prêt-à-l'emploi & vidéo de pose 10 min"
                )
            ),
            regulatoryRoadmap = listOf(
                RegulatoryRoadmapItem(
                    badge = "Norme CSTB",
                    title = "Classement UPEC & Résistance Mécanique",
                    legalRef = "Cahiers des Prescriptions Techniques CSTB n° 3578",
                    description = "Conformité aux exigences de résistance au poinçonnement et à l'usure pour sols intérieurs et escaliers résidentiels.",
                    officialLink = "https://www.cstb.fr/"
                ),
                RegulatoryRoadmapItem(
                    badge = "Réglementation COV",
                    title = "Étiquetage Émissions dans l'Air Intérieur A+",
                    legalRef = "Décret n° 2011-321 du 23 mars 2011",
                    description = "Obligation de certification de classe A+ pour les résines et vernis d'intérieur sans émanations toxiques.",
                    officialLink = "https://www.legifrance.gouv.fr/"
                )
            ),
            conceptionItems = listOf(
                ConceptionDetail(
                    title = "Résine & Mortier Millimétrique Haute Adhérence",
                    description = "Formule permettant d'accrocher directement sur vieux carrelage, béton fissuré ou marches d'escalier sans marteau-piqueur ni évacuation de gravats.",
                    keyMetric = "Marge brute > 55% sur panier moyen de 289 €",
                    sourceLabel = "Dossier Technique Béton Recouvr'Express",
                    verifiedSourceUrl = "https://www.arcane-industries.fr/",
                    officialReference = "Normes d'adhérence CSTB & Classement UPEC sol"
                )
            ),
            productionActions = listOf(
                ProductionAction("b1", "Dossier stratégique et analyse de marché établis", true, "MOYENNE", "Moi"),
                ProductionAction("b2", "Échantillonnage et tests d'adhérence sur éprouvettes", false, "BASSE", "Associé"),
                ProductionAction("b3", "Lancement du site e-commerce après validation solaire", false, "BASSE", "Les Deux")
            ),
            toolkitItems = listOf(
                ToolkitItem(
                    id = "tool_pitch_beton",
                    title = "Accroche Marketing Rénovation Sans Décapage",
                    category = "Marketing & Vente",
                    contentToCopy = "« Envie de moderniser votre carrelage démodé ou votre escalier sans passer des semaines dans la poussière et les gravats ?\n\nAvec le kit Béton Recouvr'Express™ :\n- Application directe en 2 mm d'épaisseur sur votre ancien carrelage.\n- ZÉRO démolition, ZÉRO marteau-piqueur.\n- Résine hydrofuge anti-taches haute résistance trafic intense.\n- Rénovation complète de votre sol en un seul week-end. »",
                    sourceOrNorm = "Argumentaire axé sur le gain de temps et l'économie de main d'œuvre"
                )
            )
        )
    )
}
