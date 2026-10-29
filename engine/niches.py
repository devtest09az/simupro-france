"""
Catalogue des niches à haute valeur ajoutée et forte intention de recherche en France.
Chaque niche cible un besoin métier/fiscal précis avec forte monétisation d'affiliation (B2B/Fintech).
"""

NICHES = [
    {
        "slug": "simulateur-tjm-freelance",
        "title": "Simulateur TJM Freelance & Revenu Net Réel (2026)",
        "badge": "Freelance & Indépendants",
        "meta_description": "Calculez votre Taux Journalier Moyen (TJM) idéal ou découvrez votre salaire net réel après cotisations (Micro-entreprise, SASU, EURL).",
        "target_audience": "Développeurs, consultants, designers et freelances",
        "affiliate_category": "Banque Pro & Facturation",
        "affiliate_hook": "Optimisez vos frais bancaires et gagnez jusqu'à 300 € / an avec un compte pro adapté :",
        "affiliate_cta": "Comparer les Meilleurs Comptes Pro Freelance",
        "affiliate_url": "#comparatif-banques-pro",
        "calculator_type": "tjm_freelance",
        "faq": [
            {
                "q": "Comment calculer son TJM à partir d'un salaire net souhaité ?",
                "a": "En règle générale, pour obtenir un salaire net visé, comptez les cotisations sociales (22% à 45% selon le statut), les congés sans solde (au moins 5 semaines), et les frais de fonctionnement (10% à 15%). La formule standard est : TJM = (Revenu annuel brut souhaité + Frais) / Nombre de jours facturables (environ 210 jours/an)."
            },
            {
                "q": "Quel statut juridique est le plus avantageux pour démarrer ?",
                "a": "La micro-entreprise est idéale en dessous du plafond de chiffre d'affaires (77 700 € pour les services). Au-delà, la SASU (dividendes) ou l'EURL (rémunération gérant majoritaire) permettent de déduire les charges réelles."
            }
        ]
    },
    {
        "slug": "simulateur-brut-en-net",
        "title": "Convertisseur Salaire Brut en Net (Cadre & Non-Cadre 2026)",
        "badge": "Emploi & Salaires",
        "meta_description": "Calculez précisément votre salaire net mensuel et annuel à partir du brut pour salarié non-cadre, cadre ou fonction publique.",
        "target_audience": "Tous salariés, cadres, candidats à l'embauche",
        "affiliate_category": "Banque & Épargne",
        "affiliate_hook": "Votre salaire net sur un compte sans frais avec prime de bienvenue de 80 € à 160 € :",
        "affiliate_cta": "Voir les Meilleures Banques en Ligne",
        "affiliate_url": "#banques-en-ligne",
        "calculator_type": "brut_net",
        "faq": [
            {
                "q": "Quel est le pourcentage moyen de charges entre brut et net ?",
                "a": "Pour un salarié non-cadre du secteur privé, les cotisations salariales représentent environ 22% à 23% du brut (soit un salaire net égal à environ 77-78% du brut). Pour un cadre, les cotisations s'élèvent à environ 25% (net égal à environ 75% du brut)."
            },
            {
                "q": "Comment passer d'un salaire brut annuel à un net mensuel ?",
                "a": "Divisez le salaire brut annuel par 12 (ou par le nombre de mois prévu au contrat, ex. 13 mois), puis appliquez le coefficient de 0,78 (non-cadre) ou 0,75 (cadre) avant prélèvement à la source."
            }
        ]
    },
    {
        "slug": "simulateur-frais-de-notaire",
        "title": "Calculateur Frais de Notaire Réels (Ancien & Neuf 2026)",
        "badge": "Immobilier & Crédit",
        "meta_description": "Estimez au centime près les frais d'acquisition immobilière (frais de notaire) selon votre département et le type de bien.",
        "target_audience": "Acquéreurs immobiliers, investisseurs locatifs",
        "affiliate_category": "Courtage & Crédit Immobilier",
        "affiliate_hook": "Trouvez le meilleur taux de crédit immobilier et économisez jusqu'à 15 000 € sur votre emprunt :",
        "affiliate_cta": "Comparer les Meilleurs Taux de Prêt Immobilier",
        "affiliate_url": "#comparateur-pret-immo",
        "calculator_type": "frais_notaire",
        "faq": [
            {
                "q": "De quoi sont réellement composés les frais de notaire ?",
                "a": "Environ 80% des frais dits 'de notaire' sont en réalité des droits d'enregistrement et taxes perçus par l'État et les départements. La rémunération effective du notaire (les émoluments) ne représente qu'environ 10% à 15% du montant total facturé."
            },
            {
                "q": "Quelle est la différence de frais entre logement neuf et ancien ?",
                "a": "Dans l'ancien, les frais d'acquisition se situent généralement entre 7% et 8% du prix d'achat. Dans le neuf (VEFA), ils sont considérablement réduits, oscillant entre 2% et 3%."
            }
        ]
    },
    {
        "slug": "simulateur-rupture-conventionnelle",
        "title": "Simulateur Rupture Conventionnelle & Droits France Travail",
        "badge": "Droit du Travail & Cadres",
        "meta_description": "Estimez votre indemnité minimale légale de rupture conventionnelle et le montant prévisionnel de vos allocations chômage (ARE).",
        "target_audience": "Salariés et cadres en transition professionnelle",
        "affiliate_category": "Formation & Reconversion",
        "affiliate_hook": "Bénéficiez jusqu'à 100% de financement CPF pour votre reconversion ou votre bilan de compétences :",
        "affiliate_cta": "Tester mon éligibilité au Bilan de Compétences financé",
        "affiliate_url": "#bilan-competences-cpf",
        "calculator_type": "rupture_conventionnelle",
        "faq": [
            {
                "q": "Quel est le calcul de l'indemnité légale de rupture ?",
                "a": "L'indemnité légale ne peut être inférieure à : 1/4 de mois de salaire par année d'ancienneté pour les 10 premières années, et 1/3 de mois de salaire par année au-delà de 10 ans."
            },
            {
                "q": "L'indemnité de rupture conventionnelle est-elle imposable ?",
                "a": "L'indemnité légale ou conventionnelle est exonérée d'impôt sur le revenu dans la limite des plafonds fiscaux légaux (généralement 2 fois la rémunération annuelle brute de l'année précédente ou 50% de l'indemnité versée)."
            }
        ]
    },
    {
        "slug": "simulateur-dividendes-vs-salaire-sasu",
        "title": "Simulateur Dividendes vs Salaire SASU / EURL",
        "badge": "Dirigeants & PME",
        "meta_description": "Optimisez la fiscalité de votre rémunération de dirigeant : comparez l'arbitrage Flat Tax (30%) vs Rémunération avec charges sociales.",
        "target_audience": "Dirigeants de SASU, SAS, EURL et créateurs d'entreprise",
        "affiliate_category": "Expert-Comptable en Ligne",
        "affiliate_hook": "Déléguez votre bilan et vos fiches de paie à partir de 79 € / mois tout inclus :",
        "affiliate_cta": "Découvrir les Meilleurs Experts-Comptables en Ligne",
        "affiliate_url": "#experts-comptables",
        "calculator_type": "dividendes_sasu",
        "faq": [
            {
                "q": "Pourquoi verser un salaire minimum en SASU ?",
                "a": "Un salaire brut d'au moins 600 fois le SMIC horaire par an permet de valider 4 trimestres de retraite et d'assurer une couverture sécurité sociale complète (mutuelle, prévoyance)."
            },
            {
                "q": "Comment fonctionne la Flat Tax sur les dividendes ?",
                "a": "Le Prélèvement Forfaitaire Unique (PFU) ou Flat Tax est de 30% au total : 12,8% au titre de l'impôt sur le revenu et 17,2% au titre des prélèvements sociaux."
            }
        ]
    }
]
