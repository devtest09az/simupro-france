package com.solaris.manager.data

import com.solaris.manager.model.ConceptionDetail
import com.solaris.manager.model.EcomDossier
import com.solaris.manager.model.ProductionAction

object EcomDossiersData {
    val dossiers = listOf(
        EcomDossier(
            id = "dossier_solaire",
            title = "Station Solaire 420W Plug & Play",
            subtitle = "Marque SOLARIS France • Prête à brancher 220V",
            category = "Énergie & Autoconsommation",
            iconEmoji = "☀️",
            progressPercent = 80,
            estimatedNetMargin = "+105 € à +115 € / unité",
            targetMonthlyRevenue = "10 500 € / mois (30 stations)",
            liveWebsiteUrl = "https://devtest09az.github.io/simupro-france/",
            conceptionItems = listOf(
                ConceptionDetail(
                    title = "Panneau Trina Solar 440W Vertex S+ Biverre",
                    description = "Technologie N-Type i-TOPCon biverre 2.0 mm, tolérance positive, rendement 22.0%, garantie 25/30 ans.",
                    keyMetric = "79,99 € TTC détail / 49,50 € HT palette",
                    verifiedSourceUrl = "https://kitsolaire-discount.com/fr/panneaux-solaires/2474-panneau-solaire-440w-biverre-vertex-s-trinasolar-9331416830358.html"
                ),
                ConceptionDetail(
                    title = "Micro-Onduleur Hoymiles HMS-800-2T (800W)",
                    description = "2 MPPT indépendants, rendement 96.7%, découplage réseau DIN VDE V 0124-100 et VDE 0126-1-1 homologué Enedis.",
                    keyMetric = "109,00 € TTC détail / 68,00 € HT B2B",
                    verifiedSourceUrl = "https://kitsolaire-discount.com/fr/produit-flux-seo/2217-micro-onduleur-duo-hms-800-2t-hoymiles-8424511842942.html"
                ),
                ConceptionDetail(
                    title = "Châssis Alu Ksolar Lite Inclinable 20°-35°",
                    description = "Alliage alu AL6005-T5 anodisé avec visserie inox A2 complète. Résistance vent jusqu'à 130 km/h.",
                    keyMetric = "39,00 € TTC détail / 19,50 € HT B2B",
                    verifiedSourceUrl = "https://kitsolaire-discount.com/fr/fixation-terrasse-sol-mur/3430-fixation-universel-reglable-lite-ksolar.html"
                ),
                ConceptionDetail(
                    title = "Câblage AC Schuko 5m Étanchéité IP68",
                    description = "Câble extérieur HO7RN-F 3G1.5mm² avec connecteur Field Connector étanche HMS côté onduleur et prise Schuko 16A.",
                    keyMetric = "22,90 € TTC détail / 11,20 € HT B2B",
                    verifiedSourceUrl = "https://kitsolaire-discount.com/fr/micro-onduleurs-accessoires/2225-718736-cable-ac-avec-prise-etanche-hms-3g-15mm-9085823105782.html"
                ),
                ConceptionDetail(
                    title = "Logistique Fret Palette Geodis Calberson",
                    description = "Fret palette messagerie sécurisée (1,85 x 1,20 m, 28,5 kg) avec hayon et prise de RDV par SMS chez le client particulier.",
                    keyMetric = "38 € à 45 € HT / palette",
                    verifiedSourceUrl = "https://www.upela.com/fr/"
                )
            ),
            productionActions = listOf(
                ProductionAction("s1", "Site e-commerce vitrine avec simulateur Linky déployé", true, "URGENT"),
                ProductionAction("s2", "Visuel commercial HD station dans jardin validé", true, "URGENT"),
                ProductionAction("s3", "Tester l'annonce pilote sur Leboncoin & Facebook Marketplace", false, "HAUTE"),
                ProductionAction("s4", "Ouvrir les accès grossistes pros POwR Connect & UpWatt", false, "HAUTE"),
                ProductionAction("s5", "Commander le kit prototype test unitaire", false, "MOYENNE"),
                ProductionAction("s6", "Livrer les 3 premières commandes fermes", false, "MOYENNE")
            )
        ),
        EcomDossier(
            id = "dossier_alarme",
            title = "Pack Alarme Maison Sans Abonnement",
            subtitle = "Marque SECUREX France • Centrale tactile 4G/Wi-Fi",
            category = "Sécurité Domestique & Protection",
            iconEmoji = "🛡️",
            progressPercent = 45,
            estimatedNetMargin = "+85 € à +105 € / unité",
            targetMonthlyRevenue = "7 500 € / mois (50 packs)",
            liveWebsiteUrl = null,
            conceptionItems = listOf(
                ConceptionDetail(
                    title = "Centrale Tactile 4.3\" Wi-Fi & Carte SIM 4G",
                    description = "Application mobile Tuya / Smart Life sans abonnement mensuel, notifications push instantanées et appels vocaux GSM.",
                    keyMetric = "Coût usine complet : 34 € à 42 € HT",
                    verifiedSourceUrl = null
                ),
                ConceptionDetail(
                    title = "Kit 11 Pièces Prêt à Poser",
                    description = "Centrale tactile + 4 détecteurs ouverture portes/fenêtres + 2 détecteurs présence infrarouge PIR + 2 télécommandes + 2 badges RFID + sirène 110 dB.",
                    keyMetric = "Prix de vente public : 149 € à 179 € TTC (vs Verisure à 49€/mois)",
                    verifiedSourceUrl = null
                ),
                ConceptionDetail(
                    title = "Format Colis Compact Colissimo / Mondial Relay",
                    description = "Dimensions colis : 28 x 20 x 12 cm, poids : 1.4 kg. Expédition économique et facile.",
                    keyMetric = "Frais de port : 6,50 € à 8,90 € TTC",
                    verifiedSourceUrl = null
                )
            ),
            productionActions = listOf(
                ProductionAction("a1", "Étude de marché et sourcing composants validés", true, "URGENT"),
                ProductionAction("a2", "Visuel 3D photoréaliste du pack 11 pièces généré", true, "URGENT"),
                ProductionAction("a3", "Créer la boutique en ligne dédiée (Shopify ou Netlify)", false, "HAUTE"),
                ProductionAction("a4", "Commander 2 échantillons de test en direct usine", false, "HAUTE"),
                ProductionAction("a5", "Tester la campagne publicitaire vidéo Meta / TikTok", false, "MOYENNE")
            )
        ),
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
            conceptionItems = listOf(
                ConceptionDetail(
                    title = "Objet Social Parapluie Multi-Marques",
                    description = "Englobe à la fois la vente de matériel photovoltaïque ET la sécurité/alarmes dans les statuts sans frais greffe ultérieurs.",
                    keyMetric = "Code NAF 47.91A (Vente à distance)",
                    verifiedSourceUrl = "https://formalites.entreprises.gouv.fr/"
                ),
                ConceptionDetail(
                    title = "Bouclier ARE France Travail (0 € de Salaire)",
                    description = "Procès-Verbal d'Assemblée Générale fixant la rémunération du Président à 0 €. Maintien intégral des allocations chômage journalières.",
                    keyMetric = "Art. L5421-1 Code du travail",
                    verifiedSourceUrl = "https://www.service-public.fr/particuliers/vosdroits/F11660"
                ),
                ConceptionDetail(
                    title = "Cumul Salarié à Côté (Associé DG)",
                    description = "Compatibilité légale de l'associé salarié (art. L1222-1 et dérogation d'exclusivité art. L1222-5 pour création d'entreprise).",
                    keyMetric = "100% légal sur temps libre",
                    verifiedSourceUrl = "https://www.service-public.fr/particuliers/vosdroits/F11660"
                )
            ),
            productionActions = listOf(
                ProductionAction("j1", "Statuts de SAS à capital variable rédigés", true, "URGENT"),
                ProductionAction("j2", "Pacte d'associés (Vesting & Bad Leaver) cadré", true, "URGENT"),
                ProductionAction("j3", "Ouvrir le compte pro en ligne pour dépôt du capital (Qonto)", false, "HAUTE"),
                ProductionAction("j4", "Déposer l'immatriculation au Guichet Unique INPI", false, "HAUTE"),
                ProductionAction("j5", "Transmettre le PV d'AG de 0 € à France Travail", false, "HAUTE")
            )
        ),
        EcomDossier(
            id = "dossier_beton",
            title = "Béton Décoratif Recouvr'Express",
            subtitle = "Kit Rénovation Sol & Escalier sans casser",
            category = "Bricolage & Travaux de Rénovation",
            iconEmoji = "🏗️",
            progressPercent = 30,
            estimatedNetMargin = "+120 € à +180 € / seau 25kg",
            targetMonthlyRevenue = "5 000 € / mois (Niche complémentaire)",
            liveWebsiteUrl = null,
            conceptionItems = listOf(
                ConceptionDetail(
                    title = "Résine Époxy & Mortier Millimétrique",
                    description = "Produit d'accroche directe sur carrelage ancien, dallage ou béton fissuré sans démolition.",
                    keyMetric = "Sourcing fabricant direct France/Italie",
                    verifiedSourceUrl = null
                ),
                ConceptionDetail(
                    title = "Panier Moyen Élevé (250 € - 450 €)",
                    description = "Rénovation de terrasse ou escalier extérieur : les clients achètent 2 à 4 seaux d'un coup.",
                    keyMetric = "Marge brute > 55%",
                    verifiedSourceUrl = null
                )
            ),
            productionActions = listOf(
                ProductionAction("b1", "Dossier stratégique et analyse de marché établis", true, "MOYENNE"),
                ProductionAction("b2", "Échantillonnage et tests d'adhérence", false, "BASSE"),
                ProductionAction("b3", "Lancement ultérieur après stabilisation du Solaire", false, "BASSE")
            )
        )
    )
}
