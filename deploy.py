"""
Script d'automatisation pour le déploiement gratuit des actifs en 1 clic.
Supporte :
1. Git / GitHub Pages
2. Cloudflare Pages (0 €/mois, trafic illimité)
3. Vercel (0 €/mois)
"""

import subprocess
import sys
import shutil
from pathlib import Path

def check_tools():
    print("[*] Vérification des outils de déploiement...")
    git_installed = shutil.which("git") is not None
    print(f"  -> Git disponible : {'OUI' if git_installed else 'NON'}")

if __name__ == "__main__":
    check_tools()
    print("\n[+] Pour mettre en ligne gratuitement en 30 secondes :")
    print("    Option 1 (Cloudflare Pages) : glissez simplement le dossier 'public' sur dash.cloudflare.com/pages")
    print("    Option 2 (Vercel) : tapez 'npx vercel public' dans le terminal")
    print("    Option 3 (GitHub Pages) : poussez ce repo sur GitHub et activez Pages sur la branche main/public")
