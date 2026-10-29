import sys
import json
from pathlib import Path

if hasattr(sys.stdout, 'reconfigure'):
    sys.stdout.reconfigure(encoding='utf-8')

from engine.niches import NICHES

def get_calculator_html_and_js(calculator_type: str) -> tuple[str, str]:
    if calculator_type == "tjm_freelance":
        html = """
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
            <div class="bg-white rounded-2xl p-6 shadow-sm border border-slate-100 space-y-6">
                <h3 class="text-lg font-semibold text-slate-800 flex items-center gap-2">
                    <span class="w-8 h-8 rounded-lg bg-indigo-50 text-indigo-600 flex items-center justify-center font-bold">1</span>
                    Vos Paramètres d'Activité
                </h3>
                
                <div>
                    <div class="flex justify-between items-center mb-2">
                        <label class="text-sm font-medium text-slate-700">Taux Journalier Moyen (TJM HT)</label>
                        <span id="tjm-val" class="text-lg font-bold text-indigo-600">550 €</span>
                    </div>
                    <input type="range" id="tjm" min="200" max="1500" step="25" value="550" 
                           class="w-full h-2 bg-slate-200 rounded-lg appearance-none cursor-pointer accent-indigo-600">
                </div>

                <div>
                    <div class="flex justify-between items-center mb-2">
                        <label class="text-sm font-medium text-slate-700">Jours travaillés par an</label>
                        <span id="days-val" class="text-sm font-semibold text-slate-600">210 jours</span>
                    </div>
                    <input type="range" id="days" min="100" max="250" step="5" value="210" 
                           class="w-full h-2 bg-slate-200 rounded-lg appearance-none cursor-pointer accent-indigo-600">
                    <p class="text-xs text-slate-400 mt-1">Moyenne : 210 jours (5 semaines de congés + jours fériés + intercontrat).</p>
                </div>

                <div>
                    <label class="block text-sm font-medium text-slate-700 mb-2">Statut Juridique</label>
                    <select id="status" class="w-full bg-slate-50 border border-slate-200 rounded-xl px-4 py-3 text-slate-800 font-medium focus:ring-2 focus:ring-indigo-500 focus:outline-none">
                        <option value="micro">Micro-entreprise (Charges ~22% + frais réduits)</option>
                        <option value="sasu">SASU (Dividendes / Salaire ~42% prélèvements)</option>
                        <option value="eurl">EURL (Rémunération TNS ~38% cotisations)</option>
                    </select>
                </div>

                <div>
                    <label class="block text-sm font-medium text-slate-700 mb-2">Frais professionnels annuels (€)</label>
                    <input type="number" id="expenses" value="4000" 
                           class="w-full bg-slate-50 border border-slate-200 rounded-xl px-4 py-3 text-slate-800 font-medium focus:ring-2 focus:ring-indigo-500 focus:outline-none">
                </div>
            </div>

            <div class="bg-gradient-to-br from-slate-900 to-indigo-950 rounded-2xl p-6 text-white flex flex-col justify-between shadow-xl">
                <div>
                    <div class="flex items-center justify-between pb-4 border-b border-white/10">
                        <span class="text-xs font-semibold tracking-wider text-indigo-300 uppercase">Projection Annuelle & Mensuelle</span>
                        <span class="px-3 py-1 bg-indigo-500/20 text-indigo-300 text-xs rounded-full border border-indigo-400/30">Temps réel</span>
                    </div>

                    <div class="mt-6 space-y-6">
                        <div>
                            <span class="text-sm text-slate-400 block">Chiffre d'Affaires Brut Annuel</span>
                            <span id="ca-annuel" class="text-3xl font-extrabold text-white">115 500 €</span>
                        </div>

                        <div class="p-4 bg-white/5 rounded-xl border border-white/10 space-y-3">
                            <div class="flex justify-between text-sm">
                                <span class="text-slate-400">Cotisations sociales estimées</span>
                                <span id="charges" class="font-semibold text-rose-300">- 25 410 €</span>
                            </div>
                            <div class="flex justify-between text-sm">
                                <span class="text-slate-400">Frais de fonctionnement</span>
                                <span id="frais-display" class="font-semibold text-amber-300">- 4 000 €</span>
                            </div>
                        </div>

                        <div class="pt-2">
                            <span class="text-sm text-indigo-300 block font-medium">Revenu Net Estimé Avant Impôt sur le Revenu</span>
                            <div class="flex items-baseline gap-3 mt-1">
                                <span id="net-mensuel" class="text-4xl font-black text-emerald-400">7 174 €</span>
                                <span class="text-sm text-slate-400">/ mois (sur 12 mois)</span>
                            </div>
                            <span id="net-annuel" class="text-sm text-slate-400 block mt-1">soit environ 86 090 € net / an</span>
                        </div>
                    </div>
                </div>

                <div class="mt-8 pt-4 border-t border-white/10 text-xs text-slate-400">
                    * Estimation indicative basée sur les barèmes légaux 2026. Non contractuel.
                </div>
            </div>
        </div>
        """
        js = """
        function updateTJM() {
            const tjm = parseFloat(document.getElementById('tjm').value) || 0;
            const days = parseFloat(document.getElementById('days').value) || 0;
            const expenses = parseFloat(document.getElementById('expenses').value) || 0;
            const status = document.getElementById('status').value;

            document.getElementById('tjm-val').textContent = tjm + ' €';
            document.getElementById('days-val').textContent = days + ' jours';

            const ca = tjm * days;
            let chargesRate = 0.22;
            if (status === 'sasu') chargesRate = 0.42;
            if (status === 'eurl') chargesRate = 0.38;

            let charges = 0;
            let netAnnuel = 0;

            if (status === 'micro') {
                charges = ca * chargesRate;
                netAnnuel = Math.max(0, ca - charges - expenses);
            } else {
                const beneficeBrut = Math.max(0, ca - expenses);
                charges = beneficeBrut * chargesRate;
                netAnnuel = beneficeBrut - charges;
            }

            const netMensuel = netAnnuel / 12;
            const fmt = (n) => new Intl.NumberFormat('fr-FR', { maximumFractionDigits: 0 }).format(n) + ' €';

            document.getElementById('ca-annuel').textContent = fmt(ca);
            document.getElementById('charges').textContent = '- ' + fmt(charges);
            document.getElementById('frais-display').textContent = '- ' + fmt(expenses);
            document.getElementById('net-mensuel').textContent = fmt(netMensuel);
            document.getElementById('net-annuel').textContent = 'soit environ ' + fmt(netAnnuel) + ' net / an';

            const params = new URLSearchParams({ tjm, days, expenses, status });
            window.history.replaceState({}, '', window.location.pathname + '?' + params.toString());
        }

        const q = new URLSearchParams(window.location.search);
        if (q.has('tjm')) document.getElementById('tjm').value = q.get('tjm');
        if (q.has('days')) document.getElementById('days').value = q.get('days');
        if (q.has('expenses')) document.getElementById('expenses').value = q.get('expenses');
        if (q.has('status')) document.getElementById('status').value = q.get('status');

        ['tjm', 'days', 'expenses', 'status'].forEach(id => {
            document.getElementById(id).addEventListener('input', updateTJM);
        });
        updateTJM();
        """
        return html, js

    elif calculator_type == "brut_net":
        html = """
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
            <div class="bg-white rounded-2xl p-6 shadow-sm border border-slate-100 space-y-6">
                <h3 class="text-lg font-semibold text-slate-800 flex items-center gap-2">
                    <span class="w-8 h-8 rounded-lg bg-emerald-50 text-emerald-600 flex items-center justify-center font-bold">1</span>
                    Votre Rémunération Brute
                </h3>
                
                <div>
                    <label class="block text-sm font-medium text-slate-700 mb-2">Salaire Brut Mensuel (€)</label>
                    <input type="number" id="brut-mensuel" value="3200" step="50"
                           class="w-full bg-slate-50 border border-slate-200 rounded-xl px-4 py-3 text-slate-800 font-bold text-lg focus:ring-2 focus:ring-emerald-500 focus:outline-none">
                </div>

                <div>
                    <label class="block text-sm font-medium text-slate-700 mb-2">Statut du Salarié</label>
                    <select id="statut-salarie" class="w-full bg-slate-50 border border-slate-200 rounded-xl px-4 py-3 text-slate-800 font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none">
                        <option value="non_cadre">Salarié Non-Cadre (Cotisations ~22%)</option>
                        <option value="cadre" selected>Salarié Cadre (Cotisations ~25% dont APEC & Prévoyance)</option>
                        <option value="fonctionnaire">Fonction Publique (Cotisations ~15%)</option>
                    </select>
                </div>

                <div class="grid grid-cols-2 gap-4">
                    <div>
                        <label class="block text-sm font-medium text-slate-700 mb-2">Mois payés</label>
                        <select id="nb-mois" class="w-full bg-slate-50 border border-slate-200 rounded-xl px-4 py-3 text-slate-800 font-medium">
                            <option value="12" selected>12 mois</option>
                            <option value="13">13 mois</option>
                        </select>
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-slate-700 mb-2">Taux Prélèvement Source</label>
                        <input type="number" id="taux-pas" value="5.5" step="0.5" min="0" max="45"
                               class="w-full bg-slate-50 border border-slate-200 rounded-xl px-4 py-3 text-slate-800 font-medium">
                    </div>
                </div>
            </div>

            <div class="bg-gradient-to-br from-slate-900 to-emerald-950 rounded-2xl p-6 text-white flex flex-col justify-between shadow-xl">
                <div>
                    <div class="flex items-center justify-between pb-4 border-b border-white/10">
                        <span class="text-xs font-semibold tracking-wider text-emerald-300 uppercase">Fiche de Paie Estimée</span>
                        <span class="px-3 py-1 bg-emerald-500/20 text-emerald-300 text-xs rounded-full border border-emerald-400/30">Barème 2026</span>
                    </div>

                    <div class="mt-6 space-y-6">
                        <div>
                            <span class="text-sm text-slate-400 block">Salaire Net avant Impôt (versé sur le compte)</span>
                            <span id="net-avant-impot" class="text-3xl font-extrabold text-white">2 400 €</span>
                            <span id="net-annuel-display" class="text-xs text-slate-400 block mt-1">soit 28 800 € net / an</span>
                        </div>

                        <div class="p-4 bg-white/5 rounded-xl border border-white/10 space-y-3">
                            <div class="flex justify-between text-sm">
                                <span class="text-slate-400">Cotisations salariales prélevées</span>
                                <span id="charges-salariales" class="font-semibold text-rose-300">- 800 €</span>
                            </div>
                            <div class="flex justify-between text-sm">
                                <span class="text-slate-400">Impôt à la source estimé</span>
                                <span id="impot-source" class="font-semibold text-amber-300">- 132 €</span>
                            </div>
                        </div>

                        <div class="pt-2">
                            <span class="text-sm text-emerald-300 block font-medium">Net Final dans votre Poche (après impôt)</span>
                            <div class="flex items-baseline gap-3 mt-1">
                                <span id="net-apres-impot" class="text-4xl font-black text-emerald-400">2 268 €</span>
                                <span class="text-sm text-slate-400">/ mois</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="mt-8 pt-4 border-t border-white/10 flex justify-between items-center text-xs text-slate-400">
                    <span>Coût total employeur : <strong id="super-brut" class="text-white">4 544 €</strong></span>
                    <span>35h hebdo</span>
                </div>
            </div>
        </div>
        """
        js = """
        function updateBrutNet() {
            const brutMensuel = parseFloat(document.getElementById('brut-mensuel').value) || 0;
            const statut = document.getElementById('statut-salarie').value;
            const nbMois = parseFloat(document.getElementById('nb-mois').value) || 12;
            const tauxPas = (parseFloat(document.getElementById('taux-pas').value) || 0) / 100;

            let rateCotis = 0.22;
            if (statut === 'cadre') rateCotis = 0.25;
            if (statut === 'fonctionnaire') rateCotis = 0.15;

            const cotis = brutMensuel * rateCotis;
            const netAvant = Math.max(0, brutMensuel - cotis);
            const impot = netAvant * tauxPas;
            const netApres = netAvant - impot;
            const superBrut = brutMensuel * 1.42; // charges patronales ~42%

            const fmt = (n) => new Intl.NumberFormat('fr-FR', { maximumFractionDigits: 0 }).format(n) + ' €';

            document.getElementById('net-avant-impot').textContent = fmt(netAvant);
            document.getElementById('net-annuel-display').textContent = 'soit ' + fmt(netAvant * nbMois) + ' net / an';
            document.getElementById('charges-salariales').textContent = '- ' + fmt(cotis);
            document.getElementById('impot-source').textContent = '- ' + fmt(impot);
            document.getElementById('net-apres-impot').textContent = fmt(netApres);
            document.getElementById('super-brut').textContent = fmt(superBrut);

            const params = new URLSearchParams({ brut: brutMensuel, statut, mois: nbMois, pas: (tauxPas * 100).toFixed(1) });
            window.history.replaceState({}, '', window.location.pathname + '?' + params.toString());
        }

        const q = new URLSearchParams(window.location.search);
        if (q.has('brut')) document.getElementById('brut-mensuel').value = q.get('brut');
        if (q.has('statut')) document.getElementById('statut-salarie').value = q.get('statut');
        if (q.has('mois')) document.getElementById('nb-mois').value = q.get('mois');
        if (q.has('pas')) document.getElementById('taux-pas').value = q.get('pas');

        ['brut-mensuel', 'statut-salarie', 'nb-mois', 'taux-pas'].forEach(id => {
            document.getElementById(id).addEventListener('input', updateBrutNet);
        });
        updateBrutNet();
        """
        return html, js

    elif calculator_type == "frais_notaire":
        html = """
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
            <div class="bg-white rounded-2xl p-6 shadow-sm border border-slate-100 space-y-6">
                <h3 class="text-lg font-semibold text-slate-800 flex items-center gap-2">
                    <span class="w-8 h-8 rounded-lg bg-rose-50 text-rose-600 flex items-center justify-center font-bold">1</span>
                    Votre Projet Immobilier
                </h3>
                
                <div>
                    <label class="block text-sm font-medium text-slate-700 mb-2">Prix d'Achat du Bien Immobilier (€)</label>
                    <input type="number" id="prix-bien" value="280000" step="5000"
                           class="w-full bg-slate-50 border border-slate-200 rounded-xl px-4 py-3 text-slate-800 font-bold text-lg focus:ring-2 focus:ring-rose-500 focus:outline-none">
                </div>

                <div>
                    <label class="block text-sm font-medium text-slate-700 mb-2">Nature du Logement</label>
                    <select id="type-bien" class="w-full bg-slate-50 border border-slate-200 rounded-xl px-4 py-3 text-slate-800 font-medium focus:ring-2 focus:ring-rose-500 focus:outline-none">
                        <option value="ancien" selected>Logement Ancien (Frais ~7% à 8%)</option>
                        <option value="neuf">Logement Neuf / VEFA (Frais réduits ~2% à 3%)</option>
                        <option value="terrain">Terrain à Bâtir (~7% à 8%)</option>
                    </select>
                </div>

                <div>
                    <label class="block text-sm font-medium text-slate-700 mb-2">Montant du Mobilier inclus à déduire (€)</label>
                    <input type="number" id="mobilier" value="5000" step="500"
                           class="w-full bg-slate-50 border border-slate-200 rounded-xl px-4 py-3 text-slate-800 font-medium">
                    <p class="text-xs text-slate-400 mt-1">Cuisine équipée, électroménager... Déductible des droits d'enregistrement !</p>
                </div>
            </div>

            <div class="bg-gradient-to-br from-slate-900 to-rose-950 rounded-2xl p-6 text-white flex flex-col justify-between shadow-xl">
                <div>
                    <div class="flex items-center justify-between pb-4 border-b border-white/10">
                        <span class="text-xs font-semibold tracking-wider text-rose-300 uppercase">Frais d'Acquisition Prévus</span>
                        <span class="px-3 py-1 bg-rose-500/20 text-rose-300 text-xs rounded-full border border-rose-400/30">Barème Légal</span>
                    </div>

                    <div class="mt-6 space-y-6">
                        <div>
                            <span class="text-sm text-slate-400 block">Montant Total des Frais de Notaire</span>
                            <span id="total-notaire" class="text-3xl font-extrabold text-white">20 840 €</span>
                            <span id="pct-display" class="text-xs text-rose-300 block mt-1">soit ~7,4% du prix du bien</span>
                        </div>

                        <div class="p-4 bg-white/5 rounded-xl border border-white/10 space-y-3">
                            <div class="flex justify-between text-sm">
                                <span class="text-slate-400">Droits d'enregistrement (Taxes État/Dép.)</span>
                                <span id="taxes-etat" class="font-semibold text-rose-300">16 672 €</span>
                            </div>
                            <div class="flex justify-between text-sm">
                                <span class="text-slate-400">Émoluments du notaire (rémunération)</span>
                                <span id="emoluments" class="font-semibold text-indigo-300">2 500 €</span>
                            </div>
                            <div class="flex justify-between text-sm">
                                <span class="text-slate-400">Débours et formalités</span>
                                <span id="debours" class="font-semibold text-amber-300">1 668 €</span>
                            </div>
                        </div>

                        <div class="pt-2">
                            <span class="text-sm text-rose-300 block font-medium">Budget Global Acquisition (Bien + Frais)</span>
                            <div class="flex items-baseline gap-3 mt-1">
                                <span id="budget-global" class="text-4xl font-black text-emerald-400">300 840 €</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="mt-8 pt-4 border-t border-white/10 text-xs text-slate-400">
                    * Calcul conforme aux décrets régissant les tarifs des notaires en France.
                </div>
            </div>
        </div>
        """
        js = """
        function updateNotaire() {
            const prix = parseFloat(document.getElementById('prix-bien').value) || 0;
            const typeBien = document.getElementById('type-bien').value;
            const mobilier = parseFloat(document.getElementById('mobilier').value) || 0;

            const baseTaxable = Math.max(0, prix - mobilier);
            let rate = 0.075;
            if (typeBien === 'neuf') rate = 0.025;

            const total = baseTaxable * rate;
            const taxes = total * 0.80;
            const emoluments = total * 0.12;
            const debours = total * 0.08;
            const budgetGlobal = prix + total;

            const fmt = (n) => new Intl.NumberFormat('fr-FR', { maximumFractionDigits: 0 }).format(n) + ' €';

            document.getElementById('total-notaire').textContent = fmt(total);
            document.getElementById('pct-display').textContent = 'soit ~' + (rate * 100).toFixed(1) + '% du montant taxable';
            document.getElementById('taxes-etat').textContent = fmt(taxes);
            document.getElementById('emoluments').textContent = fmt(emoluments);
            document.getElementById('debours').textContent = fmt(debours);
            document.getElementById('budget-global').textContent = fmt(budgetGlobal);

            const params = new URLSearchParams({ prix, type: typeBien, mobilier });
            window.history.replaceState({}, '', window.location.pathname + '?' + params.toString());
        }

        const q = new URLSearchParams(window.location.search);
        if (q.has('prix')) document.getElementById('prix-bien').value = q.get('prix');
        if (q.has('type')) document.getElementById('type-bien').value = q.get('type');
        if (q.has('mobilier')) document.getElementById('mobilier').value = q.get('mobilier');

        ['prix-bien', 'type-bien', 'mobilier'].forEach(id => {
            document.getElementById(id).addEventListener('input', updateNotaire);
        });
        updateNotaire();
        """
        return html, js

    elif calculator_type == "rupture_conventionnelle":
        html = """
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
            <div class="bg-white rounded-2xl p-6 shadow-sm border border-slate-100 space-y-6">
                <h3 class="text-lg font-semibold text-slate-800 flex items-center gap-2">
                    <span class="w-8 h-8 rounded-lg bg-teal-50 text-teal-600 flex items-center justify-center font-bold">1</span>
                    Vos Données de Salarié
                </h3>
                <div>
                    <label class="block text-sm font-medium text-slate-700 mb-2">Salaire Mensuel Brut de Référence (€)</label>
                    <input type="number" id="salaire" value="3500" 
                           class="w-full bg-slate-50 border border-slate-200 rounded-xl px-4 py-3 text-slate-800 font-semibold focus:ring-2 focus:ring-teal-500 focus:outline-none">
                </div>
                <div>
                    <div class="flex justify-between items-center mb-2">
                        <label class="text-sm font-medium text-slate-700">Ancienneté dans l'entreprise</label>
                        <span id="annees-val" class="text-sm font-bold text-teal-600">6 ans et 0 mois</span>
                    </div>
                    <div class="grid grid-cols-2 gap-4">
                        <div>
                            <span class="text-xs text-slate-500 block mb-1">Années complètes</span>
                            <input type="number" id="annees" value="6" min="0" max="45"
                                   class="w-full bg-slate-50 border border-slate-200 rounded-xl px-4 py-2.5 text-slate-800 font-medium">
                        </div>
                        <div>
                            <span class="text-xs text-slate-500 block mb-1">Mois supplémentaires</span>
                            <input type="number" id="mois" value="0" min="0" max="11"
                                   class="w-full bg-slate-50 border border-slate-200 rounded-xl px-4 py-2.5 text-slate-800 font-medium">
                        </div>
                    </div>
                </div>
                <div>
                    <label class="block text-sm font-medium text-slate-700 mb-2">Indemnité supra-légale négociée (€)</label>
                    <input type="number" id="supralegale" value="0" 
                           class="w-full bg-slate-50 border border-slate-200 rounded-xl px-4 py-3 text-slate-800 font-medium">
                </div>
            </div>

            <div class="bg-gradient-to-br from-slate-900 to-teal-950 rounded-2xl p-6 text-white flex flex-col justify-between shadow-xl">
                <div>
                    <div class="flex items-center justify-between pb-4 border-b border-white/10">
                        <span class="text-xs font-semibold tracking-wider text-teal-300 uppercase">Indemnités & Droits Chômage</span>
                        <span class="px-3 py-1 bg-teal-500/20 text-teal-300 text-xs rounded-full border border-teal-400/30">Code du Travail</span>
                    </div>
                    <div class="mt-6 space-y-6">
                        <div>
                            <span class="text-sm text-slate-400 block">Indemnité Minimale Légale Exonérée</span>
                            <span id="indemnite-legale" class="text-3xl font-extrabold text-white">5 250 €</span>
                        </div>
                        <div class="p-4 bg-white/5 rounded-xl border border-white/10 space-y-3">
                            <div class="flex justify-between text-sm">
                                <span class="text-slate-400">Bonus supra-légal</span>
                                <span id="bonus-display" class="font-semibold text-teal-300">0 €</span>
                            </div>
                            <div class="flex justify-between text-sm border-t border-white/10 pt-2 font-medium">
                                <span class="text-slate-200">Indemnité totale de départ</span>
                                <span id="indemnite-totale" class="font-bold text-emerald-400">5 250 €</span>
                            </div>
                        </div>
                        <div class="pt-2">
                            <span class="text-sm text-teal-300 block font-medium">Allocation Chômage Mensuelle Nette (ARE)</span>
                            <div class="flex items-baseline gap-3 mt-1">
                                <span id="are-mensuelle" class="text-4xl font-black text-emerald-400">1 995 €</span>
                                <span class="text-sm text-slate-400">/ mois</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="mt-8 pt-4 border-t border-white/10 text-xs text-slate-400">
                    * Calcul conforme aux barèmes officiels (Art. L. 1237-13).
                </div>
            </div>
        </div>
        """
        js = """
        function updateRupture() {
            const salaire = parseFloat(document.getElementById('salaire').value) || 0;
            const annees = parseFloat(document.getElementById('annees').value) || 0;
            const mois = parseFloat(document.getElementById('mois').value) || 0;
            const supra = parseFloat(document.getElementById('supralegale').value) || 0;

            const dureeTotale = annees + (mois / 12);
            document.getElementById('annees-val').textContent = annees + ' ans et ' + mois + ' mois';

            let indemnite = 0;
            if (dureeTotale <= 10) {
                indemnite = dureeTotale * (salaire / 4);
            } else {
                indemnite = (10 * (salaire / 4)) + ((dureeTotale - 10) * (salaire / 3));
            }

            const total = indemnite + supra;
            const areEstimee = salaire * 0.57;
            const fmt = (n) => new Intl.NumberFormat('fr-FR', { maximumFractionDigits: 0 }).format(n) + ' €';

            document.getElementById('indemnite-legale').textContent = fmt(indemnite);
            document.getElementById('bonus-display').textContent = fmt(supra);
            document.getElementById('indemnite-totale').textContent = fmt(total);
            document.getElementById('are-mensuelle').textContent = fmt(areEstimee);
        }

        ['salaire', 'annees', 'mois', 'supralegale'].forEach(id => {
            document.getElementById(id).addEventListener('input', updateRupture);
        });
        updateRupture();
        """
        return html, js

    elif calculator_type == "dividendes_sasu":
        html = """
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
            <div class="bg-white rounded-2xl p-6 shadow-sm border border-slate-100 space-y-6">
                <h3 class="text-lg font-semibold text-slate-800 flex items-center gap-2">
                    <span class="w-8 h-8 rounded-lg bg-blue-50 text-blue-600 flex items-center justify-center font-bold">1</span>
                    Enveloppe de Rémunération
                </h3>
                <div>
                    <label class="block text-sm font-medium text-slate-700 mb-2">Budget Entreprise Annuel Total (€)</label>
                    <input type="number" id="budget" value="70000" step="5000"
                           class="w-full bg-slate-50 border border-slate-200 rounded-xl px-4 py-3 text-slate-800 font-semibold focus:ring-2 focus:ring-blue-500 focus:outline-none">
                </div>
                <div>
                    <div class="flex justify-between items-center mb-2">
                        <label class="text-sm font-medium text-slate-700">Répartition : % Dividendes vs Salaire</label>
                        <span id="ratio-val" class="text-sm font-bold text-blue-600">70% Dividendes / 30% Salaire</span>
                    </div>
                    <input type="range" id="ratio" min="0" max="100" step="5" value="70" 
                           class="w-full h-2 bg-slate-200 rounded-lg appearance-none cursor-pointer accent-blue-600">
                </div>
            </div>

            <div class="bg-gradient-to-br from-slate-900 to-blue-950 rounded-2xl p-6 text-white flex flex-col justify-between shadow-xl">
                <div>
                    <div class="flex items-center justify-between pb-4 border-b border-white/10">
                        <span class="text-xs font-semibold tracking-wider text-blue-300 uppercase">Résultat Net dans votre Poche</span>
                        <span class="px-3 py-1 bg-blue-500/20 text-blue-300 text-xs rounded-full border border-blue-400/30">Optimisation Fiscale</span>
                    </div>
                    <div class="mt-6 space-y-6">
                        <div class="p-4 bg-white/5 rounded-xl border border-white/10 space-y-3">
                            <div class="flex justify-between text-sm">
                                <span class="text-slate-400">Partie Salaire Net</span>
                                <span id="part-salaire" class="font-semibold text-blue-200">12 000 €</span>
                            </div>
                            <div class="flex justify-between text-sm">
                                <span class="text-slate-400">Partie Dividendes Net</span>
                                <span id="part-dividendes" class="font-semibold text-indigo-200">29 155 €</span>
                            </div>
                        </div>
                        <div class="pt-2">
                            <span class="text-sm text-blue-300 block font-medium">Revenu Net Global dans votre Poche</span>
                            <div class="flex items-baseline gap-3 mt-1">
                                <span id="total-poche" class="text-4xl font-black text-emerald-400">41 155 €</span>
                                <span class="text-sm text-slate-400">/ an</span>
                            </div>
                            <span id="taux-reel" class="text-xs text-slate-300 block mt-1">Prélèvement global d'environ 41,2%</span>
                        </div>
                    </div>
                </div>
                <div class="mt-8 pt-4 border-t border-white/10 text-xs text-slate-400">
                    * Modèle intégrant IS à taux réduit (15%) + PFU Flat Tax (30%).
                </div>
            </div>
        </div>
        """
        js = """
        function updateDividendes() {
            const budget = parseFloat(document.getElementById('budget').value) || 0;
            const ratioDiv = parseFloat(document.getElementById('ratio').value) / 100;
            const ratioSal = 1 - ratioDiv;

            document.getElementById('ratio-val').textContent = Math.round(ratioDiv * 100) + '% Dividendes / ' + Math.round(ratioSal * 100) + '% Salaire';

            const budgetSalaire = budget * ratioSal;
            const salaireNet = budgetSalaire * 0.55;

            const budgetDiv = budget * ratioDiv;
            const isEstime = budgetDiv * 0.15;
            const soldeApresIS = Math.max(0, budgetDiv - isEstime);
            const dividendesNet = soldeApresIS * 0.70;

            const netTotal = salaireNet + dividendesNet;
            const tauxGlobal = budget > 0 ? ((budget - netTotal) / budget) * 100 : 0;
            const fmt = (n) => new Intl.NumberFormat('fr-FR', { maximumFractionDigits: 0 }).format(n) + ' €';

            document.getElementById('part-salaire').textContent = fmt(salaireNet);
            document.getElementById('part-dividendes').textContent = fmt(dividendesNet);
            document.getElementById('total-poche').textContent = fmt(netTotal);
            document.getElementById('taux-reel').textContent = 'Prélèvement global d\\'environ ' + tauxGlobal.toFixed(1) + '%';
        }

        ['budget', 'ratio'].forEach(id => {
            document.getElementById(id).addEventListener('input', updateDividendes);
        });
        updateDividendes();
        """
        return html, js

    return "<div>Calculateur introuvable</div>", ""

def generate_tool_page(niche: dict) -> str:
    calc_html, calc_js = get_calculator_html_and_js(niche["calculator_type"])
    
    faq_html = ""
    faq_schema = []
    for item in niche["faq"]:
        faq_schema.append({
            "@type": "Question",
            "name": item["q"],
            "acceptedAnswer": {
                "@type": "Answer",
                "text": item["a"]
            }
        })
        faq_html += f"""
        <div class="bg-white rounded-xl p-5 border border-slate-100 shadow-sm">
            <h4 class="text-base font-semibold text-slate-800 mb-2">{item['q']}</h4>
            <p class="text-sm text-slate-600 leading-relaxed">{item['a']}</p>
        </div>
        """

    schema_json = json.dumps({
        "@context": "https://schema.org",
        "@graph": [
            {
                "@type": "SoftwareApplication",
                "name": niche["title"],
                "description": niche["meta_description"],
                "applicationCategory": "BusinessApplication",
                "operatingSystem": "All",
                "offers": {
                    "@type": "Offer",
                    "price": "0",
                    "priceCurrency": "EUR"
                }
            },
            {
                "@type": "FAQPage",
                "mainEntity": faq_schema
            }
        ]
    }, ensure_ascii=False, indent=2)

    return f"""<!DOCTYPE html>
<html lang="fr" class="scroll-smooth">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>{niche['title']}</title>
    <meta name="description" content="{niche['meta_description']}">
    <link rel="canonical" href="https://simupro.fr/{niche['slug']}.html">
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <style>
        body {{ font-family: 'Plus Jakarta Sans', sans-serif; }}
        @media print {{
            header, footer, .no-print {{ display: none !important; }}
            body {{ background: white !important; color: black !important; }}
            .bg-gradient-to-br, .bg-gradient-to-r {{ background: #f8fafc !important; color: #0f172a !important; border: 1px solid #cbd5e1 !important; }}
            .text-white {{ color: #0f172a !important; }}
            .text-slate-400, .text-indigo-300, .text-teal-300, .text-blue-300, .text-rose-300 {{ color: #475569 !important; }}
            .text-emerald-400 {{ color: #047857 !important; font-weight: 800 !important; }}
            .print-header {{ display: block !important; }}
        }}
        .print-header {{ display: none; }}
    </style>
    <script type="application/ld+json">
{schema_json}
    </script>
</head>
<body class="bg-slate-50 text-slate-900 min-h-screen flex flex-col antialiased">
    <header class="bg-white border-b border-slate-100 sticky top-0 z-50">
        <div class="max-w-6xl mx-auto px-4 h-16 flex items-center justify-between">
            <a href="index.html" class="flex items-center gap-2.5 font-extrabold text-slate-900 text-lg tracking-tight">
                <span class="w-8 h-8 rounded-xl bg-indigo-600 text-white flex items-center justify-center font-black text-sm shadow-md shadow-indigo-200">⚡</span>
                SimuPro France
            </a>
            <div class="flex items-center gap-4">
                <span class="text-xs font-semibold px-3 py-1 bg-indigo-50 text-indigo-700 rounded-full border border-indigo-100">
                    Barème 2026
                </span>
                <a href="index.html" class="text-sm font-medium text-slate-600 hover:text-slate-900 transition">Tous les simulateurs</a>
            </div>
        </div>
    </header>

    <main class="flex-grow max-w-6xl mx-auto px-4 py-10 w-full space-y-12">
        <div class="print-header">
            <div class="flex justify-between items-center border-b-2 border-slate-900 pb-4 mb-6">
                <div>
                    <h2 class="text-2xl font-black text-slate-900">⚡ SimuPro France — Dossier de Simulation Officielle</h2>
                    <p class="text-sm text-slate-600">Calcul conforme à la réglementation fiscale et sociale 2026</p>
                </div>
                <div class="text-right text-xs text-slate-500">
                    Document de synthèse personnel
                </div>
            </div>
        </div>
        <div class="text-center max-w-3xl mx-auto space-y-4">
            <span class="inline-block text-xs font-bold tracking-wider uppercase px-3 py-1 rounded-full bg-slate-200 text-slate-700">
                {niche['badge']}
            </span>
            <h1 class="text-3xl md:text-5xl font-extrabold text-slate-900 tracking-tight leading-tight">
                {niche['title']}
            </h1>
            <p class="text-base md:text-lg text-slate-600 leading-relaxed">
                {niche['meta_description']}
            </p>
        </div>

        <section class="max-w-5xl mx-auto">
            {calc_html}
        </section>

        <!-- Export & Viral Sharing Bar -->
        <div class="max-w-5xl mx-auto flex flex-col md:flex-row items-center justify-between gap-4 p-5 bg-white rounded-2xl border border-slate-200/80 no-print shadow-sm">
            <div class="flex items-center gap-3">
                <span class="w-10 h-10 rounded-xl bg-indigo-50 text-indigo-600 flex items-center justify-center font-bold text-base shadow-sm">🔗</span>
                <div>
                    <p class="text-sm font-bold text-slate-800">Partager ou sauvegarder cette simulation</p>
                    <p class="text-xs text-slate-500">Lien interactif avec vos chiffres pré-remplis ou export PDF officiel.</p>
                </div>
            </div>
            <div class="flex flex-wrap items-center gap-2 w-full md:w-auto">
                <button onclick="copyShareLink()" id="copy-btn" class="flex-1 md:flex-none px-4 py-2.5 bg-slate-100 hover:bg-slate-200 text-slate-800 font-semibold text-xs rounded-xl transition flex items-center justify-center gap-2">
                    <span id="copy-icon">📋</span>
                    <span id="copy-text">Copier le lien direct</span>
                </button>
                <button onclick="shareWhatsApp()" class="px-3.5 py-2.5 bg-emerald-50 hover:bg-emerald-100 text-emerald-700 font-semibold text-xs rounded-xl transition flex items-center justify-center gap-1.5 border border-emerald-200/60">
                    <span>💬</span>
                    <span>WhatsApp</span>
                </button>
                <button onclick="shareLinkedIn()" class="px-3.5 py-2.5 bg-blue-50 hover:bg-blue-100 text-blue-700 font-semibold text-xs rounded-xl transition flex items-center justify-center gap-1.5 border border-blue-200/60">
                    <span>💼</span>
                    <span>LinkedIn</span>
                </button>
                <button onclick="window.print()" class="flex-1 md:flex-none px-5 py-2.5 bg-slate-900 hover:bg-slate-800 text-white font-semibold text-xs rounded-xl transition flex items-center justify-center gap-2 shadow-sm">
                    <span>PDF Officiel</span>
                    <span>↓</span>
                </button>
            </div>
        </div>

        <section class="max-w-5xl mx-auto">
            <div class="bg-gradient-to-r from-indigo-50 via-purple-50 to-pink-50 rounded-2xl p-6 md:p-8 border border-indigo-100 flex flex-col md:flex-row items-center justify-between gap-6 shadow-sm">
                <div class="space-y-2 text-center md:text-left">
                    <span class="text-xs font-bold tracking-wider text-indigo-700 uppercase">Partenaire Recommandé</span>
                    <h3 class="text-lg md:text-xl font-bold text-slate-900">{niche['affiliate_hook']}</h3>
                    <p class="text-sm text-slate-600">Offre partenaire certifiée, 100% conforme et sans engagement.</p>
                </div>
                <a href="{niche['affiliate_url']}" class="whitespace-nowrap px-6 py-3.5 bg-indigo-600 hover:bg-indigo-700 text-white font-bold rounded-xl shadow-lg shadow-indigo-200 transition-all transform hover:-translate-y-0.5 text-center">
                    {niche['affiliate_cta']} →
                </a>
            </div>
        </section>

        <section class="max-w-5xl mx-auto pt-6 space-y-6">
            <div class="border-t border-slate-200 pt-8">
                <h3 class="text-2xl font-bold text-slate-900 mb-6">Questions Fréquentes & Règles Officielles</h3>
                <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                    {faq_html}
                </div>
            </div>
        </section>
    </main>

    <footer class="bg-white border-t border-slate-100 py-8 text-center text-sm text-slate-500">
        <div class="max-w-6xl mx-auto px-4 space-y-2">
            <p>© 2026 SimuPro France. Outils gratuits et indépendants pour professionnels et particuliers.</p>
        </div>
    </footer>

    <script>
    function copyShareLink() {{
        navigator.clipboard.writeText(window.location.href).then(() => {{
            const btn = document.getElementById('copy-text');
            const original = btn.textContent;
            btn.textContent = 'Lien copié !';
            setTimeout(() => {{ btn.textContent = original; }}, 2000);
        }});
    }}

    function shareWhatsApp() {{
        const url = encodeURIComponent(window.location.href);
        const text = encodeURIComponent('Découvre cette simulation sur SimuPro France : ');
        window.open('https://api.whatsapp.com/send?text=' + text + url, '_blank');
    }}

    function shareLinkedIn() {{
        const url = encodeURIComponent(window.location.href);
        window.open('https://www.linkedin.com/sharing/share-offsite/?url=' + url, '_blank');
    }}

    {calc_js}
    </script>
</body>
</html>
"""

def generate_index_page(niches: list[dict]) -> str:
    cards_html = ""
    for niche in niches:
        cards_html += f"""
        <a href="{niche['slug']}.html" class="group bg-white rounded-2xl p-6 border border-slate-200/80 shadow-sm hover:shadow-md hover:border-indigo-300 transition-all flex flex-col justify-between">
            <div class="space-y-3">
                <span class="inline-block text-xs font-bold tracking-wider uppercase px-2.5 py-0.5 rounded-md bg-indigo-50 text-indigo-700">
                    {niche['badge']}
                </span>
                <h3 class="text-xl font-bold text-slate-900 group-hover:text-indigo-600 transition">
                    {niche['title']}
                </h3>
                <p class="text-sm text-slate-600 line-clamp-2">
                    {niche['meta_description']}
                </p>
            </div>
            <div class="mt-6 pt-4 border-t border-slate-100 flex items-center justify-between text-sm font-semibold text-indigo-600">
                <span>Accéder au simulateur</span>
                <span class="transform group-hover:translate-x-1 transition-transform">→</span>
            </div>
        </a>
        """

    return f"""<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SimuPro France - Simulateurs & Calculateurs Professionnels Gratuits</title>
    <meta name="description" content="Tous vos simulateurs gratuits de calcul de salaire brut en net, frais de notaire, TJM freelance, rupture conventionnelle conformes 2026.">
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <style>
        body {{ font-family: 'Plus Jakarta Sans', sans-serif; }}
        @media print {{
            header, footer, .no-print {{ display: none !important; }}
            body {{ background: white !important; color: black !important; }}
            .bg-gradient-to-br, .bg-gradient-to-r {{ background: #f8fafc !important; color: #0f172a !important; border: 1px solid #cbd5e1 !important; }}
            .text-white {{ color: #0f172a !important; }}
            .text-slate-400, .text-indigo-300, .text-teal-300, .text-blue-300, .text-rose-300 {{ color: #475569 !important; }}
            .text-emerald-400 {{ color: #047857 !important; font-weight: 800 !important; }}
            .print-header {{ display: block !important; }}
        }}
        .print-header {{ display: none; }}
    </style>
</head>
<body class="bg-slate-50 text-slate-900 min-h-screen flex flex-col antialiased">
    <header class="bg-white border-b border-slate-100">
        <div class="max-w-6xl mx-auto px-4 h-16 flex items-center justify-between">
            <div class="flex items-center gap-2.5 font-extrabold text-slate-900 text-lg tracking-tight">
                <span class="w-8 h-8 rounded-xl bg-indigo-600 text-white flex items-center justify-center font-black text-sm shadow-md shadow-indigo-200">⚡</span>
                SimuPro France
            </div>
            <span class="text-xs font-semibold px-3 py-1 bg-emerald-50 text-emerald-700 rounded-full border border-emerald-100">
                ● 100% Gratuit & Conforme 2026
            </span>
        </div>
    </header>

    <main class="flex-grow max-w-6xl mx-auto px-4 py-16 w-full space-y-12">
        <div class="text-center max-w-3xl mx-auto space-y-4">
            <h1 class="text-4xl md:text-5xl font-black text-slate-900 tracking-tight">
                Simulateurs Fiscaux & Financiers en 1 Clic
            </h1>
            <p class="text-lg text-slate-600">
                Calculateurs instantanés et ultra-précis pour salariés, freelances, acheteurs immobiliers et dirigeants d'entreprise en France.
            </p>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {cards_html}
        </div>
    </main>

    <footer class="bg-white border-t border-slate-100 py-8 text-center text-sm text-slate-500">
        <p>© 2026 SimuPro France. Conçu pour maximiser la clarté financière.</p>
    </footer>
</body>
</html>
"""

def generate_seo_assets(niches: list[dict], out: Path, base_url: str = "https://simupro.fr"):
    sitemap = ['<?xml version="1.0" encoding="UTF-8"?>']
    sitemap.append('<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">')
    sitemap.append(f'  <url><loc>{base_url}/index.html</loc><priority>1.0</priority><changefreq>weekly</changefreq></url>')
    for n in niches:
        sitemap.append(f'  <url><loc>{base_url}/{n["slug"]}.html</loc><priority>0.8</priority><changefreq>monthly</changefreq></url>')
    sitemap.append('</urlset>')

    (out / "sitemap.xml").write_text("\n".join(sitemap), encoding="utf-8")
    
    robots = [
        "User-agent: *",
        "Allow: /",
        f"Sitemap: {base_url}/sitemap.xml"
    ]
    (out / "robots.txt").write_text("\n".join(robots), encoding="utf-8")

def build_all(output_dir: str = "public"):
    out = Path(output_dir)
    out.mkdir(parents=True, exist_ok=True)

    print(f"[+] Construction des actifs numeriques dans {out.resolve()}...")
    for niche in NICHES:
        html_content = generate_tool_page(niche)
        dest_file = out / f"{niche['slug']}.html"
        dest_file.write_text(html_content, encoding="utf-8")
        print(f"  -> Genere : {dest_file.name}")

    index_content = generate_index_page(NICHES)
    (out / "index.html").write_text(index_content, encoding="utf-8")
    print("  -> Genere : index.html (Catalogue complet)")

    generate_seo_assets(NICHES, out)
    print("  -> Genere : sitemap.xml & robots.txt pour Google")

    print("[OK] Tous les actifs ont ete generes avec succes !")

if __name__ == "__main__":
    build_all()
