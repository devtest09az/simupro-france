"""
Module d'Acquisition Client B2B Automatisé — RecouvrExpress
Génère des séquences de prospection commerciale ultra-ciblées pour les secteurs les plus touchés par les impayés en France (BTP, Agences, Transport, Consultants).
Conforme RGPD B2B (intérêt légitime professionnel).
"""

from pathlib import Path
import json

SECTORS = [
    {
        "sector": "BTP & Artisans du Bâtiment",
        "pain_point": "Retenues de garantie bloquées et retards de paiement des promoteurs/particuliers",
        "average_debt": "8 500 €",
        "subject": "Question sur vos factures en attente — {{entreprise}}",
        "email_body": """Bonjour {{prenom}},

En échangeant avec plusieurs artisans et gérants du BTP dans votre région, le même constat revient constamment : des milliers d'euros de trésorerie restent bloqués pendant des mois sur des chantiers déjà livrés.

Nous avons développé RecouvrExpress (https://moonlit-baklava-2495ca.netlify.app/recouvrement.html) : une solution qui calcule automatiquement vos pénalités légales (art. L. 441-10 du Code de commerce + 40 € par facture) et génère des mises en demeure formelles qui débloquent les paiements sous 8 jours sans passer par un avocat.

Seriez-vous ouvert à tester l'outil gratuitement sur un de vos impayés en cours ?

Bien cordialement,
L'équipe RecouvrExpress
Désinscription en 1 clic : répondre STOP"""
    },
    {
        "sector": "Agences Web, Marketing & Freelances Tech",
        "pain_point": "Clients qui ne paient pas le solde à la livraison du site ou de la prestation",
        "average_debt": "3 200 €",
        "subject": "{{entreprise}} : relance de vos soldes de projets impayés",
        "email_body": """Bonjour {{prenom}},

Combien de projets ou de soldes de factures avez-vous en souffrance depuis plus de 30 jours ?

Les agences et studios perdent en moyenne 12 % de leur marge nette simplement parce qu'ils n'osent pas relancer fermement leurs clients de peur de briser la relation.

RecouvrExpress (https://moonlit-baklava-2495ca.netlify.app/recouvrement.html) formalise juridiquement vos relances en 3 clics avec les pénalités légales de plein droit. Vos clients comprennent immédiatement que vous maîtrisez le cadre légal et régularisent en priorité.

Le test est 100% gratuit sur votre premier dossier : https://moonlit-baklava-2495ca.netlify.app/recouvrement-express.html

Bien à vous,
RecouvrExpress"""
    }
]

def generate_outreach_kit(output_dir: str = "outreach"):
    out = Path(output_dir)
    out.mkdir(parents=True, exist_ok=True)
    
    report = []
    report.append("# Kit de Prospection Commerciale B2B — RecouvrExpress\n")
    report.append("Ce kit contient les modèles d'emails à haute conversion pour acquérir vos 10 premiers clients payants à 49 €/mois.\n")
    
    for s in SECTORS:
        report.append(f"## Cible : {s['sector']}")
        report.append(f"- **Problème douloureux** : {s['pain_point']}")
        report.append(f"- **Créance moyenne** : {s['average_debt']}")
        report.append(f"- **Objet de l'email** : `{s['subject']}`\n")
        report.append("```text")
        report.append(s['email_body'].strip())
        report.append("```\n---\n")

    kit_path = out / "SEQUENCE_PROSPECTION.md"
    kit_path.write_text("\n".join(report), encoding="utf-8")
    print(f"[+] Kit de prospection généré dans {kit_path}")

if __name__ == "__main__":
    generate_outreach_kit()
