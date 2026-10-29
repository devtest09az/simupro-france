"""
Générateur de dossiers guides SEO à forte intention de recherche pour capter le trafic Google organique.
Chaque guide inclut des données structurées Article (Schema.org) et des liens internes vers les calculateurs.
"""

from pathlib import Path
import json

GUIDES = [
    {
        "slug": "guide-reduire-frais-de-notaire",
        "title": "Comment Réduire Légalement ses Frais de Notaire en 2026 : Les 4 Astuces Clés",
        "badge": "Guide Immobilier",
        "read_time": "5 min de lecture",
        "meta_description": "Économisez jusqu'à 5 000 € sur vos frais d'acquisition immobilière grâce à la déduction du mobilier, la dissociation des frais d'agence et les logements neufs.",
        "target_calc": "simulateur-frais-de-notaire.html",
        "target_calc_name": "Accéder au Calculateur de Frais de Notaire Réels",
        "content_html": """
        <p class="text-lg text-slate-700 leading-relaxed mb-6 font-medium">
            Lors d'un achat immobilier, les "frais de notaire" représentent entre 7 % et 8 % du prix du bien dans l'ancien. Sur un appartement à 300 000 €, cela représente près de 24 000 € à débourser en apport personnel. Heureusement, il existe des leviers 100 % légaux pour réduire cette facture fiscale.
        </p>

        <h2 class="text-2xl font-bold text-slate-900 mt-8 mb-4">1. Déduire la valeur des meubles et équipements (Cuisine, Électroménager)</h2>
        <p class="text-slate-600 leading-relaxed mb-4">
            Les droits de mutation (qui représentent 80 % des frais de notaire) ne s'appliquent qu'à la valeur <strong>immobilière</strong> du bien. Si le logement comprend des biens mobiliers (cuisine équipée, électroménager, placards sur-mesure, luminaires, radiateurs mobiles), vous pouvez déduire leur valeur vénale du prix d'achat.
        </p>
        <div class="bg-indigo-50 border-l-4 border-indigo-600 p-4 rounded-r-xl my-4 text-sm text-indigo-900">
            <strong>Exemple concret :</strong> Pour un bien à 280 000 € comprenant 10 000 € de meubles inventoriés, les droits de mutation ne seront calculés que sur 270 000 €. Soit une économie immédiate d'environ <strong>800 €</strong> sur vos frais d'enregistrement.
        </div>

        <h2 class="text-2xl font-bold text-slate-900 mt-8 mb-4">2. Sortir les frais d'agence immobilière du prix de vente</h2>
        <p class="text-slate-600 leading-relaxed mb-4">
            Si le mandat de l'agence immobilière mentionne des honoraires à la charge de l'acquéreur, demandez à ce que le compromis sépare clairement le prix net vendeur et la commission d'agence. Vous ne paierez ainsi des frais de notaire que sur le net vendeur, et non sur la commission de l'intermédiaire.
        </p>

        <h2 class="text-2xl font-bold text-slate-900 mt-8 mb-4">3. Privilégier le logement neuf (VEFA) pour des frais à 2 %</h2>
        <p class="text-slate-600 leading-relaxed mb-4">
            Dans un achat sur plan (VEFA) ou un logement neuf tout juste achevé, les droits de mutation sont très largement réduits : ils oscillent généralement entre <strong>2 % et 3 %</strong> contre 7 % à 8 % dans l'ancien.
        </p>
        """
    },
    {
        "slug": "guide-negocier-rupture-conventionnelle",
        "title": "Négocier sa Rupture Conventionnelle en 2026 : Indemnités, Chômage et Stratégie",
        "badge": "Guide Salariés & Cadres",
        "read_time": "6 min de lecture",
        "meta_description": "Découvrez comment calculer et négocier votre indemnité supra-légale de rupture conventionnelle, préserver vos droits chômage ARE et sécuriser votre transition.",
        "target_calc": "simulateur-rupture-conventionnelle.html",
        "target_calc_name": "Calculer vos Indemnités et Droits Chômage",
        "content_html": """
        <p class="text-lg text-slate-700 leading-relaxed mb-6 font-medium">
            La rupture conventionnelle individuelle est le seul mode de séparation à l'amiable qui garantit l'accès aux allocations chômage (ARE de France Travail). Mais entre l'indemnité minimale légale et ce que vous pouvez réellement obtenir, la marge de négociation est souvent significative.
        </p>

        <h2 class="text-2xl font-bold text-slate-900 mt-8 mb-4">1. La base non négociable : L'indemnité minimale légale</h2>
        <p class="text-slate-600 leading-relaxed mb-4">
            L'employeur ne peut en aucun cas vous verser moins que l'indemnité légale de licenciement prévue par le Code du travail :
        </p>
        <ul class="list-disc list-inside space-y-2 text-slate-600 mb-4">
            <li><strong>1/4 de mois de salaire</strong> par année d'ancienneté pour les 10 premières années.</li>
            <li><strong>1/3 de mois de salaire</strong> par année pour les années au-delà de 10 ans d'ancienneté.</li>
        </ul>

        <h2 class="text-2xl font-bold text-slate-900 mt-8 mb-4">2. Comment négocier l'indemnité supra-légale</h2>
        <p class="text-slate-600 leading-relaxed mb-4">
            L'indemnité supra-légale correspond au montant négocié en plus du minimum légal. Les arguments les plus solides pour valoriser cette indemnité :
        </p>
        <ul class="list-disc list-inside space-y-2 text-slate-600 mb-4">
            <li>Vos résultats commerciaux ou objectifs dépassés au cours des dernières années.</li>
            <li>L'abandon d'une clause de non-concurrence ou le transfert soigné de vos dossiers sans conflit.</li>
            <li>Le gain de temps et l'absence de risque juridique (prud'hommes) pour l'entreprise.</li>
        </ul>

        <h2 class="text-2xl font-bold text-slate-900 mt-8 mb-4">3. Attention au délai de carence France Travail</h2>
        <div class="bg-amber-50 border-l-4 border-amber-600 p-4 rounded-r-xl my-4 text-sm text-amber-900">
            <strong>Règle clé :</strong> Toute indemnité supra-légale perçue au-delà du minimum légal repousse la date de premier versement de votre chômage (différé spécifique d'indemnisation plafonné à 150 jours).
        </div>
        """
    },
    {
        "slug": "guide-comprendre-salaire-brut-en-net",
        "title": "Salaire Brut en Net : Où Va la Différence sur Votre Fiche de Paie en 2026 ?",
        "badge": "Guide Rémunération",
        "read_time": "4 min de lecture",
        "meta_description": "Comprendre les 22% à 25% de cotisations sociales prélevées entre le brut et le net : retraite, santé, CSG, CRDS et prélèvement à la source.",
        "target_calc": "simulateur-brut-en-net.html",
        "target_calc_name": "Convertir votre Salaire Brut en Net en Direct",
        "content_html": """
        <p class="text-lg text-slate-700 leading-relaxed mb-6 font-medium">
            Lorsque vous négociez un salaire d'embauche de 40 000 € brut annuel, combien arrive réellement sur votre compte en banque chaque mois ? Entre le salaire brut, le net avant impôt et le net après prélèvement à la source, près d'un quart de votre rémunération finance le modèle social français.
        </p>

        <h2 class="text-2xl font-bold text-slate-900 mt-8 mb-4">1. Salarié Cadre vs Non-Cadre : Pourquoi cette différence de 3 % ?</h2>
        <p class="text-slate-600 leading-relaxed mb-4">
            Pour un salarié <strong>non-cadre</strong>, le taux de retenue globale est d'environ <strong>22 %</strong> (un brut de 3 000 € donne ~2 340 € net).<br>
            Pour un salarié <strong>cadre</strong>, le taux monte à environ <strong>25 %</strong> (un brut de 3 000 € donne ~2 250 € net). Cette différence s'explique par la cotisation APEC (emploi des cadres) et une prévoyance décès obligatoire renforcée.
        </p>

        <h2 class="text-2xl font-bold text-slate-900 mt-8 mb-4">2. Les 4 grands postes de cotisations salariales</h2>
        <ul class="list-disc list-inside space-y-2 text-slate-600 mb-4">
            <li><strong>Assurance Retraite</strong> : Régime général de base + retraite complémentaire Agirc-Arrco.</li>
            <li><strong>CSG & CRDS</strong> : Contribution Sociale Généralisée (9,2 %) et remboursement de la dette sociale (0,5 %).</li>
            <li><strong>Santé & Prévoyance</strong> : Maintien de salaire en cas d'arrêt maladie et mutuelle d'entreprise d'au moins 50 %.</li>
            <li><strong>Assurance Chômage</strong> : Financée exclusivement par les cotisations patronales depuis les réformes récentes.</li>
        </ul>
        """
    }
]

def generate_guide_page(guide: dict, base_url: str = "https://moonlit-baklava-2495ca.netlify.app") -> str:
    schema_json = json.dumps({
        "@context": "https://schema.org",
        "@type": "Article",
        "headline": guide["title"],
        "description": guide["meta_description"],
        "author": {
            "@type": "Organization",
            "name": "SimuPro France"
        },
        "publisher": {
            "@type": "Organization",
            "name": "SimuPro France",
            "logo": {
                "@type": "ImageObject",
                "url": f"{base_url}/favicon.ico"
            }
        },
        "mainEntityOfPage": f"{base_url}/{guide['slug']}.html"
    }, ensure_ascii=False, indent=2)

    return f"""<!DOCTYPE html>
<html lang="fr" class="scroll-smooth">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>{guide['title']}</title>
    <meta name="description" content="{guide['meta_description']}">
    <link rel="canonical" href="{base_url}/{guide['slug']}.html">
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <style> body {{ font-family: 'Plus Jakarta Sans', sans-serif; }} </style>
    <script type="application/ld+json">
{schema_json}
    </script>
</head>
<body class="bg-slate-50 text-slate-900 min-h-screen flex flex-col antialiased">
    <header class="bg-white border-b border-slate-100 sticky top-0 z-50">
        <div class="max-w-4xl mx-auto px-4 h-16 flex items-center justify-between">
            <a href="index.html" class="flex items-center gap-2.5 font-extrabold text-slate-900 text-lg tracking-tight">
                <span class="w-8 h-8 rounded-xl bg-indigo-600 text-white flex items-center justify-center font-black text-sm shadow-md shadow-indigo-200">⚡</span>
                SimuPro France
            </a>
            <div class="flex items-center gap-4">
                <a href="index.html" class="text-sm font-medium text-slate-600 hover:text-slate-900 transition">Tous les simulateurs</a>
            </div>
        </div>
    </header>

    <main class="flex-grow max-w-4xl mx-auto px-4 py-12 w-full space-y-8">
        <div class="space-y-4">
            <div class="flex items-center gap-3">
                <span class="text-xs font-bold tracking-wider uppercase px-3 py-1 rounded-full bg-indigo-50 text-indigo-700">
                    {guide['badge']}
                </span>
                <span class="text-xs text-slate-400 font-medium">⏱️ {guide['read_time']}</span>
                <span class="text-xs text-emerald-600 font-semibold">Mis à jour 2026</span>
            </div>
            <h1 class="text-3xl md:text-5xl font-black text-slate-900 tracking-tight leading-tight">
                {guide['title']}
            </h1>
        </div>

        <!-- Callout to Interactive Simulator -->
        <div class="bg-gradient-to-r from-indigo-900 to-slate-900 rounded-2xl p-6 text-white flex flex-col sm:flex-row items-center justify-between gap-4 shadow-xl">
            <div>
                <p class="text-xs uppercase font-bold text-indigo-300 tracking-wider">Outil Interactif Recommandé</p>
                <h3 class="text-lg font-bold text-white mt-0.5">Testez vos chiffres immédiatement</h3>
            </div>
            <a href="{guide['target_calc']}" class="whitespace-nowrap px-5 py-3 bg-indigo-600 hover:bg-indigo-500 text-white font-bold text-sm rounded-xl transition shadow-lg shadow-indigo-500/30">
                {guide['target_calc_name']} →
            </a>
        </div>

        <!-- Article Body -->
        <article class="bg-white rounded-2xl p-6 md:p-10 border border-slate-200/80 shadow-sm leading-relaxed prose max-w-none text-slate-800">
            {guide['content_html']}
        </article>

        <!-- Bottom Action CTA -->
        <div class="bg-white rounded-2xl p-6 border border-slate-200/80 text-center space-y-3">
            <h3 class="text-xl font-bold text-slate-900">Vous avez un calcul précis à effectuer ?</h3>
            <p class="text-sm text-slate-600 max-w-xl mx-auto">Nos calculateurs intègrent les dernières réformes fiscales et sociales 2026 en temps réel.</p>
            <div class="pt-2">
                <a href="{guide['target_calc']}" class="inline-block px-6 py-3.5 bg-slate-900 hover:bg-slate-800 text-white font-bold rounded-xl transition">
                    Ouvrir le simulateur dédié →
                </a>
            </div>
        </div>
    </main>

    <footer class="bg-white border-t border-slate-100 py-8 text-center text-sm text-slate-500">
        <p>© 2026 SimuPro France. Guides informatifs indépendants.</p>
    </footer>
</body>
</html>
"""

def build_guides(output_dir: str = "public"):
    out = Path(output_dir)
    out.mkdir(parents=True, exist_ok=True)
    
    print("[+] Construction des dossiers guides SEO...")
    for g in GUIDES:
        html = generate_guide_page(g)
        dest = out / f"{g['slug']}.html"
        dest.write_text(html, encoding="utf-8")
        print(f"  -> Guide généré : {dest.name}")

if __name__ == "__main__":
    build_guides()
