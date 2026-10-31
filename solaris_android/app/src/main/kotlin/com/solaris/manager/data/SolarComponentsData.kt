package com.solaris.manager.data

import com.solaris.manager.model.ComponentCategory
import com.solaris.manager.model.SolarComponent

object SolarComponentsData {
    val components = listOf(
        SolarComponent(
            id = "comp_panel_trina_440",
            name = "Trina Solar Vertex S+ 440W Biverre",
            category = ComponentCategory.PANEL,
            reference = "TSM-NEG9R.28-440",
            brand = "Trina Solar",
            description = "Panneau photovoltaïque bifacial monocristallin N-Type i-TOPCon à double vitrage haute résistance (2.0 mm x 2). Rendement surfacique jusqu'à 22.0% avec gain bifacial jusqu'à +25%.",
            specifications = mapOf(
                "Puissance nominale" to "440 Wc (tolérance 0/+5W)",
                "Rendement du module" to "22.0 %",
                "Technologie cellules" to "N-Type Monocristallin 144 demi-cellules",
                "Tension MPP (Vmpp)" to "43.2 V",
                "Courant MPP (Impp)" to "10.19 A",
                "Dimensions" to "1762 x 1134 x 30 mm",
                "Poids" to "21.0 kg",
                "Garantie produit" to "25 ans",
                "Garantie performance" to "30 ans (87.4% min)"
            ),
            unitPriceRetailTtc = 79.99,
            unitPriceWholesaleHt = 49.50,
            supplierUrl = "https://kitsolaire-discount.com/fr/panneaux-solaires/2474-panneau-solaire-440w-biverre-vertex-s-trinasolar-9331416830358.html",
            certifications = listOf("IEC 61215", "IEC 61730", "Classe au feu A", "Résistance grêle 35mm"),
            stockQuantity = 48,
            assemblyRole = "Générateur photovoltaïque principal à fixer sur le châssis alu.",
            safetyNotes = "Manipuler avec des ventouses ou à deux opérateurs. Ne pas poser le verre directement sur sol abrasif."
        ),
        SolarComponent(
            id = "comp_inverter_hoymiles_800",
            name = "Hoymiles Duo HMS-800-2T (800W)",
            category = ComponentCategory.INVERTER,
            reference = "HMS-800-2T-EU",
            brand = "Hoymiles",
            description = "Micro-onduleur double entrée avec 2 MPPT indépendants. Rendement CEC 96.7%. Déconnexion réseau intégrée conforme VDE 0126-1-1 et VFR 2019 (homologué Enedis).",
            specifications = mapOf(
                "Puissance max sortie AC" to "800 VA (bridable 600 VA)",
                "Nombre de MPPT" to "2 trackers indépendants",
                "Plage tension MPPT" to "16 V - 60 V",
                "Courant d'entrée max" to "2 x 14 A",
                "Facteur de puissance" to "> 0.99",
                "Rendement maximal" to "96.7 %",
                "Indice de protection" to "IP67 étanche immersion",
                "Refroidissement" to "Convection naturelle (zéro bruit)",
                "Garantie constructeur" to "12 ans (extensible 25 ans)"
            ),
            unitPriceRetailTtc = 109.00,
            unitPriceWholesaleHt = 68.00,
            supplierUrl = "https://kitsolaire-discount.com/fr/produit-flux-seo/2217-micro-onduleur-duo-hms-800-2t-hoymiles-8424511842942.html",
            certifications = listOf("DIN VDE V 0124-100", "VDE 0126-1-1", "VFR 2019", "EN 50549-1", "CE LVD/EMC"),
            stockQuantity = 32,
            assemblyRole = "Boulonné sous le châssis aluminium pour convertir le courant continu en 230V alternatif.",
            safetyNotes = "Serrage obligatoire au couple de 12 Nm. Ne jamais déconnecter les fiches DC sous charge."
        ),
        SolarComponent(
            id = "comp_mount_ksolar_lite",
            name = "Ksolar Lite Châssis Inclinable 20°-35°",
            category = ComponentCategory.MOUNTING,
            reference = "KSOLAR-LITE-ALU",
            brand = "Ksolar",
            description = "Chevalet au sol / terrasse / mur en alliage d'aluminium anodisé AL6005-T5 avec visserie complète en acier inoxydable A2. Angle réglable manuellement selon la saison.",
            specifications = mapOf(
                "Matériau structure" to "Aluminium anodisé AL6005-T5",
                "Visserie & fixations" to "Inox haute résistance A2-70",
                "Plage d'inclinaison" to "20° à 35° réglable",
                "Résistance au vent" to "Jusqu'à 130 km/h (avec lestage)",
                "Compatibilité panneaux" to "Largeur 900 mm à 1180 mm",
                "Poids du châssis" to "3.4 kg",
                "Emplacement onduleur" to "Support percé dédié avec écrous M8 fournis"
            ),
            unitPriceRetailTtc = 39.00,
            unitPriceWholesaleHt = 19.50,
            supplierUrl = "https://kitsolaire-discount.com/fr/fixation-terrasse-sol-mur/3430-fixation-universel-reglable-lite-ksolar.html",
            certifications = listOf("Eurocode 1 (Vent & Neige)", "ISO 9001", "Alu anodisé 15 µm"),
            stockQuantity = 55,
            assemblyRole = "Support mécanique rigide maintenant le panneau face au soleil.",
            safetyNotes = "Si pose au sol sans chevillage, lester la base avec 2 dalles béton de 15 kg minimum."
        ),
        SolarComponent(
            id = "comp_cable_schuko_5m",
            name = "Câble AC Hoymiles HMS vers Schuko 5m",
            category = ComponentCategory.CABLING,
            reference = "CAB-HMS-SCHUKO-5M",
            brand = "Hoymiles / Betteri",
            description = "Câble blindé extérieur HO7RN-F 3G1.5mm² équipé d'un connecteur étanche HMS Field Connector verrouillable d'un côté et d'une fiche Schuko 2P+T surmoulée 16A de l'autre.",
            specifications = mapOf(
                "Type de câble" to "HO7RN-F 3G 1.5 mm² résistant UV & intempéries",
                "Longueur" to "5 mètres",
                "Connecteur côté onduleur" to "Field Connector HMS étanche IP68 verrouillable",
                "Prise côté réseau" to "Fiche Schuko 2P+T étanche IP44 16A 230V",
                "Tension assignée" to "450 / 750 V",
                "Section conducteurs" to "3 x 1.5 mm² cuivre étamé"
            ),
            unitPriceRetailTtc = 22.90,
            unitPriceWholesaleHt = 11.20,
            supplierUrl = "https://kitsolaire-discount.com/fr/micro-onduleurs-accessoires/2225-718736-cable-ac-avec-prise-etanche-hms-3g-15mm-9085823105782.html",
            certifications = listOf("NF EN 62109", "CE", "RoHS", "IP68 côté micro-onduleur"),
            stockQuantity = 70,
            assemblyRole = "Liaison électrique directe entre l'onduleur et la prise murale de l'habitation.",
            safetyNotes = "Ne pas couper ou rallonger le câble avec des dominos non étanches."
        ),
        SolarComponent(
            id = "comp_monitor_smart_plug",
            name = "Prise Connectée Wi-Fi Mesure Linky & Wattmètre",
            category = ComponentCategory.MONITORING,
            reference = "SOL-SMART-PLUG-16A",
            brand = "Solaris Tech",
            description = "Prise intelligente Wi-Fi 2.4 GHz pour le suivi en temps réel de la production solaire sur smartphone (Watts instantanés, kWh cumulés, historique journalier et économies en €).",
            specifications = mapOf(
                "Connectivité" to "Wi-Fi 802.11 b/g/n 2.4 GHz",
                "Charge maximale" to "16 A / 3680 W",
                "Précision mesure" to "± 1 %",
                "Application mobile" to "Smart Life / Tuya / Home Assistant compatible",
                "Protection surcharge" to "Coupure automatique si surtension"
            ),
            unitPriceRetailTtc = 14.90,
            unitPriceWholesaleHt = 6.80,
            supplierUrl = "https://powr-connect.shop/",
            certifications = listOf("CE", "RED 2014/53/EU", "RoHS"),
            stockQuantity = 40,
            assemblyRole = "Accessoire fourni dans le pack pour monitorer la production depuis le salon.",
            safetyNotes = "Brancher directement sur une prise murale reliée à la terre (éviter les multiprises cascader)."
        )
    )
}
