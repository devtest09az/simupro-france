package com.solaris.manager.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource
import com.solaris.manager.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.solaris.manager.model.ProjectPhase
import com.solaris.manager.model.ClientLeadItem
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
import androidx.compose.foundation.BorderStroke
import com.solaris.manager.model.RiskMitigationItem
import com.solaris.manager.model.PartnerPitchArgument
import com.solaris.manager.model.PartnerProfitProjection
import com.solaris.manager.model.ProductionAction
import com.solaris.manager.model.ProductionSimulation
import com.solaris.manager.model.RegulatoryRoadmapItem
import com.solaris.manager.model.ToolkitItem
import com.solaris.manager.model.UnitEconomicsDetail
import com.solaris.manager.model.VerifiedSupplier
import com.solaris.manager.viewmodel.EcomHubUiState
import com.solaris.manager.viewmodel.EcomHubViewModel

@Composable
fun EcomHubScreen(
    viewModel: EcomHubViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF0B132B),
                contentColor = Color.White,
                modifier = Modifier.border(1.dp, Color(0xFF1C2541))
            ) {
                NavigationBarItem(
                    selected = uiState.currentPhase == ProjectPhase.CONCEPTION,
                    onClick = { viewModel.selectPhase(ProjectPhase.CONCEPTION) },
                    icon = { Icon(Icons.Default.Lightbulb, contentDescription = "Conception") },
                    label = { Text("1. Conception & Sourcings", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF38BDF8),
                        selectedTextColor = Color(0xFF38BDF8),
                        unselectedIconColor = Color(0xFF64748B),
                        unselectedTextColor = Color(0xFF64748B),
                        indicatorColor = Color(0xFF0284C7).copy(alpha = 0.25f)
                    )
                )

                NavigationBarItem(
                    selected = uiState.currentPhase == ProjectPhase.PRODUCTION,
                    onClick = { viewModel.selectPhase(ProjectPhase.PRODUCTION) },
                    icon = { Icon(Icons.Default.RocketLaunch, contentDescription = "Production") },
                    label = { Text("2. Production & Ventes", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF34D399),
                        selectedTextColor = Color(0xFF34D399),
                        unselectedIconColor = Color(0xFF64748B),
                        unselectedTextColor = Color(0xFF64748B),
                        indicatorColor = Color(0xFF059669).copy(alpha = 0.25f)
                    )
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFF0B132B))
        ) {
            HubHeader(uiState = uiState)

            // Feedback toast sur enregistrement note
            uiState.noteSavedFeedback?.let { feedback ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF065F46))
                        .clickable { viewModel.clearFeedback() }
                        .padding(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CheckCircle, contentDescription = "OK", tint = Color(0xFF34D399), modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = feedback, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            when (uiState.currentPhase) {
                ProjectPhase.CONCEPTION -> ConceptionPhaseContent(
                    uiState = uiState,
                    onSwitcherClick = { viewModel.switchToDossier(it) },
                    onDossierClick = { viewModel.selectDossier(it) },
                    onVerificationToggle = { dId, vId -> viewModel.toggleVerification(dId, vId) },
                    onPhaseStepToggle = { dId, sNum -> viewModel.togglePhaseStep(dId, sNum) },
                    onHumanPrerequisiteToggle = { dId, pId -> viewModel.toggleHumanPrerequisite(dId, pId) },
                    onNoteChange = { dId, text -> viewModel.onNoteChange(dId, text) },
                    onSaveNote = { viewModel.saveNotes(it) }
                )
                ProjectPhase.PRODUCTION -> ProductionPhaseContent(
                    uiState = uiState,
                    onDossierClick = { viewModel.selectDossier(it) },
                    onActionToggle = { dId, aId -> viewModel.toggleAction(dId, aId) },
                    onNoteChange = { dId, text -> viewModel.onNoteChange(dId, text) },
                    onSaveNote = { viewModel.saveNotes(it) },
                    onAddLead = { name, phone, city, pack, notes -> viewModel.addClientLead(name, phone, city, pack, notes) },
                    onUpdateLeadStatus = { id, status -> viewModel.updateLeadStatus(id, status) },
                    onDeleteLead = { id -> viewModel.deleteClientLead(id) }
                )
            }
        }
    }
}

@Composable
private fun HubHeader(uiState: EcomHubUiState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_kopilot_logo),
                    contentDescription = "Logo KoPilot E-Com",
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(11.dp))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.People, contentDescription = "Associés", tint = Color(0xFFF59E0B), modifier = Modifier.size(15.dp))
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "ESPACE ASSOCIÉS • 2 CO-FONDATEURS",
                            color = Color(0xFFF59E0B),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.8.sp
                        )
                    }
                    Text(
                        text = "KoPilot E-Com",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (uiState.currentPhase == ProjectPhase.CONCEPTION) Color(0xFF0284C7) else Color(0xFF059669))
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(
                    text = if (uiState.currentPhase == ProjectPhase.CONCEPTION) "1. CONCEPTION" else "2. PRODUCTION",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = if (uiState.currentPhase == ProjectPhase.CONCEPTION)
                "SOLARIS & SECUREVO • SAS Multi-Marques (Solaire 440W & Alarme 4G) • Espace Co-fondateurs"
            else
                "Exécution terrain : checklist partagée à deux, répartition des tâches, sites en ligne & journal de bord.",
            color = Color(0xFF94A3B8),
            fontSize = 12.sp
        )
    }
}

@Composable
private fun ConceptionPhaseContent(
    uiState: EcomHubUiState,
    onSwitcherClick: (String) -> Unit,
    onDossierClick: (String) -> Unit,
    onVerificationToggle: (String, String) -> Unit,
    onPhaseStepToggle: (String, String) -> Unit,
    onHumanPrerequisiteToggle: (String, String) -> Unit,
    onNoteChange: (String, String) -> Unit,
    onSaveNote: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            // SÉLECTEUR RAPIDE DES 3 DOSSIERS (SAS, SOLAIRE, ALARME)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                uiState.dossiers.forEach { d ->
                    val isSelected = uiState.selectedDossierId == d.id
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isSelected) Color(0xFF0284C7) else Color(0xFF1E293B))
                            .border(
                                1.dp,
                                if (isSelected) Color(0xFF38BDF8) else Color(0xFF334155),
                                RoundedCornerShape(10.dp)
                            )
                            .clickable { onSwitcherClick(d.id) }
                            .padding(vertical = 8.dp, horizontal = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = d.iconEmoji, fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = when (d.id) {
                                    "dossier_sas" -> "1. Société SAS"
                                    "dossier_solaire" -> "2. Solaire 440W"
                                    "dossier_alarme" -> "3. Alarme 4G"
                                    else -> d.title.take(12)
                                },
                                color = if (isSelected) Color.White else Color(0xFF94A3B8),
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        items(uiState.dossiers) { dossier ->
            val isExpanded = uiState.selectedDossierId == dossier.id
            val currentNote = uiState.activeNotesByDossier[dossier.id] ?: dossier.partnerNotes

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(14.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1C2541))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    DossierCardHeader(
                        dossier = dossier,
                        isExpanded = isExpanded,
                        onToggle = { onDossierClick(dossier.id) }
                    )

                    AnimatedVisibility(visible = isExpanded) {
                        Column(modifier = Modifier.padding(top = 14.dp)) {
                            HorizontalDivider(color = Color(0xFF334155))
                            Spacer(modifier = Modifier.height(10.dp))

                            // 1. ÉTUDE DE MARCHÉ DÉTAILLÉE & CHIFFRES CLÉS (100% SOURCÉE)
                            dossier.marketStudy?.let { study ->
                                MarketStudyCard(study = study)
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // 1.BIS BENCHMARK CONCURRENTIEL OFFICIEL (SUNOLOGY, BEEM, DIY VS SOLARIS)
                            if (dossier.id == "dossier_solaire") {
                                CompetitorBenchmarkCard()
                                Spacer(modifier = Modifier.height(14.dp))
                                SolarModelsRoiBenchmarkCard()
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // 1.TER AUDIT NOM DE MARQUE & DISPONIBILITÉ NDD .FR (AFNIC & INPI)
                            BrandNamingAuditCard()
                            Spacer(modifier = Modifier.height(14.dp))
                            HorizontalDivider(color = Color(0xFF334155))
                            Spacer(modifier = Modifier.height(10.dp))

                            // 2. MODÈLE ÉCONOMIQUE UNITAIRE (P&L RÉEL CHIFFRÉ)
                            dossier.unitEconomics?.let { economics ->
                                UnitEconomicsCard(economics = economics)
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // 3. BÉNÉFICES ESPÉRÉS PAR ASSOCIÉ (RÉPARTITION 50/50 & BOUCLIER ARE)
                            dossier.partnerProfit?.let { profit ->
                                PartnerProfitCard(profit = profit)
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // 4. GAMME COMMERCIALE & PACKS CLIENTS (BEST-SELLERS)
                            if (dossier.commercialPacks.isNotEmpty()) {
                                CommercialPacksCard(packs = dossier.commercialPacks)
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // 5. SIMULATEUR DE PRODUCTION RÉELLE & ÉCONOMIES LINKY (PVGIS UE)
                            if (dossier.productionSimulations.isNotEmpty()) {
                                ProductionSimulationsCard(simulations = dossier.productionSimulations)
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // 6. ANNUAIRE DES GROSSISTES B2B OFFICIELS
                            if (dossier.verifiedSuppliers.isNotEmpty()) {
                                VerifiedSuppliersCard(suppliers = dossier.verifiedSuppliers)
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // 7. PITCH ASSOCIÉ : LES 5 ARGUMENTS MASSUE EN 3 MINUTES
                            if (dossier.partnerPitchArguments.isNotEmpty()) {
                                PartnerPitchCard(pitchArgs = dossier.partnerPitchArguments)
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // 8. BOUCLIER ANTI-RISQUES (SOLUTIONS 100% VIABLES)
                            if (dossier.riskMitigations.isNotEmpty()) {
                                RiskMitigationsCard(risks = dossier.riskMitigations)
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // 9. CHECKLIST D'AUDIT TERRAIN (À VÉRIFIER PAR LES ASSOCIÉS)
                            if (dossier.associateVerifications.isNotEmpty()) {
                                AssociateVerificationsCard(
                                    verifications = dossier.associateVerifications,
                                    onToggle = { vId -> onVerificationToggle(dossier.id, vId) }
                                )
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // 9.bis CE QUE L'IA NE PEUT PAS FAIRE (ACTIONS HUMAINES OBLIGATOIRES DES ASSOCIÉS)
                            if (dossier.humanPrerequisites.isNotEmpty()) {
                                HumanPrerequisitesCard(
                                    items = dossier.humanPrerequisites,
                                    onToggle = { pId -> onHumanPrerequisiteToggle(dossier.id, pId) }
                                )
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }



                            // 10. ACCÉLÉRATION DU PROFIT & SCALE RAPIDE
                            if (dossier.growthLevers.isNotEmpty()) {
                                GrowthStrategyCard(levers = dossier.growthLevers)
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }


                            // 11. PLAN D'ACTION OPÉRATIONNEL CHRONOLOGIQUE (J-15 À J+90)
                            if (dossier.operationalPhases.isNotEmpty()) {
                                OperationalPhasesCard(
                                    steps = dossier.operationalPhases,
                                    onToggle = { sNum -> onPhaseStepToggle(dossier.id, sNum) }
                                )
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // 12. FAQ OBJECTIONS & PARADES COMMERCIALES
                            if (dossier.objections.isNotEmpty()) {
                                ObjectionsCard(objections = dossier.objections)
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // 9. CADRE LÉGAL, DÉMARCHES & SÉCURISATION
                            if (dossier.regulatoryRoadmap.isNotEmpty()) {
                                RegulatoryRoadmapCard(roadmap = dossier.regulatoryRoadmap)
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // 9.BIS PROTOCOLE DE CRÉATION DE LA SAS & GOUVERNANCE QONTO
                            SasCreationRoadmapCard()
                            Spacer(modifier = Modifier.height(14.dp))
                            HorizontalDivider(color = Color(0xFF334155))
                            Spacer(modifier = Modifier.height(10.dp))

                            // 10. COMPOSANTS & SOURCINGS B2B RÉELS (PRIX GROSSISTES VÉRIFIÉS)
                            Text(
                                text = "10. Composants & Sourcings B2B (Prix Grossistes Vérifiés) :",
                                color = Color(0xFF38BDF8),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            dossier.conceptionItems.forEach { item ->
                                ConceptionItemRow(item = item)
                                Spacer(modifier = Modifier.height(6.dp))
                            }

                            Spacer(modifier = Modifier.height(14.dp))
                            HorizontalDivider(color = Color(0xFF334155))
                            Spacer(modifier = Modifier.height(10.dp))

                            // 11. BOÎTE À OUTILS (SCRIPTS & MODÈLES PRÊTS À L'EMPLOI)
                            Text(
                                text = "11. Boîte à Outils & Modèles Prêts à Copier :",
                                color = Color(0xFFFBBF24),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            dossier.toolkitItems.forEach { tool ->
                                ToolkitItemRow(tool = tool)
                                Spacer(modifier = Modifier.height(6.dp))
                            }

                            Spacer(modifier = Modifier.height(14.dp))
                            HorizontalDivider(color = Color(0xFF334155))
                            Spacer(modifier = Modifier.height(10.dp))

                            // 12. JOURNAL DE BORD & PRISE DE NOTES COMMUNE
                            PartnerNotesSection(
                                dossierId = dossier.id,
                                noteText = currentNote,
                                onNoteChange = { onNoteChange(dossier.id, it) },
                                onSave = { onSaveNote(dossier.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductionPhaseContent(
    uiState: EcomHubUiState,
    onDossierClick: (String) -> Unit,
    onActionToggle: (String, String) -> Unit,
    onNoteChange: (String, String) -> Unit,
    onSaveNote: (String) -> Unit,
    onAddLead: (String, String, String, String, String) -> Unit,
    onUpdateLeadStatus: (String, String) -> Unit,
    onDeleteLead: (String) -> Unit
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(uiState.dossiers) { dossier ->
            val isExpanded = uiState.selectedDossierId == dossier.id
            val currentNote = uiState.activeNotesByDossier[dossier.id] ?: dossier.partnerNotes

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(
                        1.dp,
                        if (dossier.progressPercent >= 75) Color(0xFF059669) else Color(0xFF1E293B),
                        RoundedCornerShape(14.dp)
                    ),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1C2541))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onDossierClick(dossier.id) },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = dossier.iconEmoji, fontSize = 24.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = dossier.title,
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Avancement : ${dossier.progressPercent}%",
                                    color = if (dossier.progressPercent >= 75) Color(0xFF34D399) else Color(0xFFFBBF24),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Icon(
                            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = "Expand",
                            tint = Color(0xFF94A3B8)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    LinearProgressIndicator(
                        progress = { dossier.progressPercent / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = if (dossier.progressPercent >= 75) Color(0xFF10B981) else Color(0xFFF59E0B),
                        trackColor = Color(0xFF334155)
                    )

                    dossier.liveWebsiteUrl?.let { url ->
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0369A1))
                        ) {
                            Icon(Icons.Default.OpenInBrowser, contentDescription = "Site", modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Ouvrir le Site Live en Production", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    AnimatedVisibility(visible = isExpanded) {
                        Column(modifier = Modifier.padding(top = 12.dp)) {
                            HorizontalDivider(color = Color(0xFF334155))
                            Spacer(modifier = Modifier.height(8.dp))

                            if (dossier.id == "dossier_solaire") {
                                // 1. SIMULATEUR DE TRÉSORERIE & DIVIDENDES EN DIRECT (SOLAIRE)
                                LiveTreasuryTrackerCard()
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))

                                // 2. ANNONCE PILOTE LEBONCOIN HAUTE CONVERSION (PRÊTE À POSTER)
                                LeboncoinPilotAdCard()
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))

                                // 3. CARNET DE COMMANDES & PROSPECTS (LIVE CRM)
                                ClientLeadsManagerCard(
                                    leads = uiState.clientLeads,
                                    onAddLead = onAddLead,
                                    onUpdateStatus = onUpdateLeadStatus,
                                    onDeleteLead = onDeleteLead
                                )
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            } else {
                                // PROJET 2 : CARTE DE DÉPLOIEMENT ALARME 4G (SECUREVO FRANCE)
                                AlarmeProductionSummaryCard()
                                Spacer(modifier = Modifier.height(14.dp))
                                HorizontalDivider(color = Color(0xFF334155))
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            Text(
                                text = "Feuille de Route Opérationnelle (Attribution des Tâches) :",
                                color = Color(0xFF34D399),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))

                            dossier.productionActions.forEach { action ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onActionToggle(dossier.id, action.id) }
                                        .padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = action.isDone,
                                        onCheckedChange = { onActionToggle(dossier.id, action.id) },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = Color(0xFF10B981),
                                            uncheckedColor = Color(0xFF64748B)
                                        )
                                    )
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = action.task,
                                            color = if (action.isDone) Color(0xFF64748B) else Color.White,
                                            fontSize = 12.sp
                                        )
                                        Text(
                                            text = "Attribué à : ${action.assignee}",
                                            color = Color(0xFF94A3B8),
                                            fontSize = 10.sp
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(
                                                when (action.priority) {
                                                    "URGENT" -> Color(0xFF7F1D1D)
                                                    "HAUTE" -> Color(0xFF78350F)
                                                    else -> Color(0xFF0F172A)
                                                }
                                            )
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = action.priority,
                                            color = when (action.priority) {
                                                "URGENT" -> Color(0xFFFCA5A5)
                                                "HAUTE" -> Color(0xFFFDE68A)
                                                else -> Color(0xFF94A3B8)
                                            },
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))
                            HorizontalDivider(color = Color(0xFF334155))
                            Spacer(modifier = Modifier.height(10.dp))

                            PartnerNotesSection(
                                dossierId = dossier.id,
                                noteText = currentNote,
                                onNoteChange = { onNoteChange(dossier.id, it) },
                                onSave = { onSaveNote(dossier.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DossierCardHeader(
    dossier: EcomDossier,
    isExpanded: Boolean,
    onToggle: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            Text(text = dossier.iconEmoji, fontSize = 26.sp)
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = dossier.category.uppercase(),
                    color = Color(0xFF38BDF8),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = dossier.title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = dossier.subtitle,
                    color = Color(0xFF94A3B8),
                    fontSize = 12.sp
                )
            }
        }

        Icon(
            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = "Expand",
            tint = Color(0xFF94A3B8)
        )
    }

    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF064E3B))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = "Marge : ${dossier.estimatedNetMargin}",
                color = Color(0xFF34D399),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF451A03))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = "Cible : ${dossier.targetMonthlyRevenue}",
                color = Color(0xFFFBBF24),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun MarketStudyCard(study: MarketStudyDetail) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF6366F1).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "📊", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "1. ÉTUDE DE MARCHÉ OFFICIELLE (100% SOURCÉE)",
                    color = Color(0xFF818CF8),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF065F46))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "DONNÉES VÉRIFIÉES",
                    color = Color(0xFF34D399),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            KpiChip(label = "Taille du Marché", value = study.marketSize, modifier = Modifier.weight(1f), color = Color(0xFF38BDF8))
            KpiChip(label = "Dynamique / Vitesse", value = study.growthRate, modifier = Modifier.weight(1f), color = Color(0xFF34D399))
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            KpiChip(label = "Problème Client Résolu", value = study.targetPainPoint, modifier = Modifier.weight(1f), color = Color(0xFFFCA5A5))
            KpiChip(label = "Avantage Concurrentiel", value = study.competitiveEdge, modifier = Modifier.weight(1f), color = Color(0xFFFDE68A))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Analyse Stratégique & Rentabilité :",
            color = Color(0xFFCBD5E1),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = study.fullAnalysis,
            color = Color(0xFFE2E8F0),
            fontSize = 11.sp,
            lineHeight = 16.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF4338CA).copy(alpha = 0.25f))
                .border(1.dp, Color(0xFF6366F1), RoundedCornerShape(6.dp))
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(study.officialSourceUrl))
                    context.startActivity(intent)
                }
                .padding(horizontal = 10.dp, vertical = 7.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Icon(Icons.Default.OpenInBrowser, contentDescription = "Lien", tint = Color(0xFFA5B4FC), modifier = Modifier.size(15.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Source : ${study.officialSourceLabel}",
                    color = Color(0xFFA5B4FC),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "Consulter ↗",
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
private fun SolarModelsRoiBenchmarkCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0B132B))
            .border(1.dp, Color(0xFF10B981).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "📊", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "COMPARATIF RENTABILITÉ : 4 MODÈLES SOLAIRES (FRANCE 2026)",
                    color = Color(0xFF34D399),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF059669).copy(alpha = 0.2f))
                    .border(1.dp, Color(0xFF34D399), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "AUDIT ROI & AMORTISSEMENT",
                    color = Color(0xFFA7F3D0),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Analyse mathématique indépendante : pourquoi le Plug & Play sans batterie écrase tous les autres modèles en vitesse d'amortissement.",
            color = Color(0xFF94A3B8),
            fontSize = 11.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Modèle 1 : Plug & Play Direct
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF064E3B).copy(alpha = 0.35f))
                .border(1.dp, Color(0xFF10B981), RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "🏆 1. PLUG & PLAY DIRECT (1 À 2 PANNEAUX) - NOTRE CHOIX",
                    color = Color(0xFF34D399),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Amorti en 2,5 ans",
                    color = Color(0xFF6EE7B7),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "• Coût : 349 € à 649 € TTC • Zéro batterie • Autoconsommation : 85% à 96%\n• Gain : 142 € à 250 € / an • Démarche : CACSI Enedis gratuite en 5 min sans Consuel\n• Rendement net du placement : +38% à +41% / an (12x supérieur au Livret A)",
                color = Color.White,
                fontSize = 10.sp,
                lineHeight = 15.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Modèle 2 : Plug & Play avec Batterie
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF1E293B).copy(alpha = 0.6f))
                .border(1.dp, Color(0xFF334155), RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "🔋 2. PLUG & PLAY AVEC BATTERIE (1,6 kWh ECOFLOW/ANKER)",
                    color = Color(0xFF38BDF8),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Amorti en 5,4 ans",
                    color = Color(0xFF7DD3FC),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "• Coût : ~1 700 € TTC (la batterie coûte ~1 050 € à elle seule pour rapporter 67 €/an la nuit)\n• Autoconsommation : ~98% à 100% • Gain : ~317 € / an • Rendement net : +18,6% / an\n• Angle mort : Durée de vie batterie (8-10 ans), elle s'amortit juste avant son remplacement.",
                color = Color(0xFFCBD5E1),
                fontSize = 10.sp,
                lineHeight = 15.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Modèle 3 : Toiture 3 kWc RGE + EDF OA
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF1E293B).copy(alpha = 0.6f))
                .border(1.dp, Color(0xFF334155), RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "🏠 3. TOITURE 3 kWc ARTISAN RGE + REVENTE EDF OA",
                    color = Color(0xFFFBBF24),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Amorti en 12,2 ans",
                    color = Color(0xFFFCD34D),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "• Coût net : 8 450 € TTC (devis artisan ~9 500 € déduit de 1 050 € d'aide d'État)\n• Revente surplus : 0,13 € / kWh • Gain annuel global : ~694 € / an • Rendement net : ~8,2% / an\n• Piège légal : Revente EDF OA INTERDITE en Plug & Play (artisan RGE + Consuel obligatoires).",
                color = Color(0xFFCBD5E1),
                fontSize = 10.sp,
                lineHeight = 15.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Modèle 4 : Autonomie Totale Hors Réseau
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF450A0A).copy(alpha = 0.3f))
                .border(1.dp, Color(0xFFDC2626).copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "🔌 4. AUTONOMIE TOTALE (SITE ISOLÉ HORS RÉSEAU)",
                    color = Color(0xFFF87171),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Amortissement > 20 ans",
                    color = Color(0xFFFCA5A5),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "• Coût : > 25 000 € TTC (30 panneaux + 25 kWh batteries + groupe électrogène pour l'hiver)\n• Réalité météo : Une journée de décembre produit 8x moins qu'en juin. Non rentable.",
                color = Color(0xFFE2E8F0),
                fontSize = 10.sp,
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
private fun CompetitorBenchmarkCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF38BDF8).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "⚖️", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "BENCHMARK CONCURRENTIEL OFFICIEL (FRANCE 2024-2026)",
                    color = Color(0xFF38BDF8),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF0284C7).copy(alpha = 0.2f))
                    .border(1.dp, Color(0xFF38BDF8), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "AUDIT PRIX & RENTABILITÉ",
                    color = Color(0xFF7DD3FC),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Comparatif direct des 4 offres dominantes sur le marché de l'autoconsommation Plug & Play :",
            color = Color(0xFF94A3B8),
            fontSize = 9.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Competitor 1: SOLARIS France (Our kit)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF064E3B).copy(alpha = 0.4f))
                .border(1.5.dp, Color(0xFF10B981), RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "☀️", fontSize = 13.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "SOLARIS France SAS — Pack Solo 440W", color = Color(0xFF34D399), fontSize = 10.sp, fontWeight = FontWeight.Black)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFF10B981))
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                ) {
                    Text(text = "NOTRE POSITIONNEMENT : CHAMPION QUALITÉ/PRIX", color = Color.Black, fontSize = 7.sp, fontWeight = FontWeight.Black)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "• Prix Public TTC : 349,00 € TTC (Port inclus/retrait)", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                Text(text = "Amorti en 2,8 ans seulement", color = Color(0xFF6EE7B7), fontSize = 9.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "• Matériel : Panneau Trina Solar 440W Vertex S+ Bi-verre N-Type (Rendement 22%, garantie 25 ans) + Micro-onduleur Hoymiles HMS-400 VDE + Châssis alu réglable Ksolar 20°-45° + Câble Schuko 5m monté.",
                color = Color(0xFFCBD5E1),
                fontSize = 8.sp,
                lineHeight = 11.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "• Modèle : Vente directe fabricant / DTC & Local. Marge brute : +97 € à +132 € HT / kit.",
                color = Color(0xFFA7F3D0),
                fontSize = 8.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Competitor 2: Sunology PLAY 2
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF1E293B))
                .border(1.dp, Color(0xFF475569), RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "1. Sunology PLAY 2 (450W)", color = Color(0xFF93C5FD), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                Text(text = "649 € à 699 € TTC (+300 € vs SOLARIS)", color = Color(0xFFF87171), fontSize = 9.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = """• Matériel : Panneau 450W TopCon + Micro-onduleur Hoymiles (exactement la même base que SOLARIS) + Châssis alu.
• Amortissement Linky : 5,5 ans (2 fois plus long que SOLARIS pour rentabiliser l'achat).
• Pourquoi ce prix ? : Marge distributeur Leroy Merlin/Boulanger (25% à 30% = ~170 €) + lourds budgets de publicité TV/médias.""",
                color = Color(0xFFCBD5E1),
                fontSize = 8.sp,
                lineHeight = 11.sp
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Competitor 3: Beem Energy
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF1E293B))
                .border(1.dp, Color(0xFF475569), RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "2. Beem Energy (Beem On 460W)", color = Color(0xFFFCD34D), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                Text(text = "699 € à 749 € TTC (+350 € vs SOLARIS)", color = Color(0xFFF87171), fontSize = 9.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = """• Matériel : Panneau 460W + Micro-onduleur Hoymiles + Support mural/sol.
• Amortissement Linky : 6 ans.
• Pourquoi ce prix ? : Positionnement design lifestyle, application propriétaire, réseau physique Boulanger/Castorama.""",
                color = Color(0xFFCBD5E1),
                fontSize = 8.sp,
                lineHeight = 11.sp
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Competitor 4: Kits DIY pièces détachées (Oscaro Power / 123Elec)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF1E293B))
                .border(1.dp, Color(0xFF475569), RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "3. Kits DIY Pièces en vrac (Oscaro / 123Elec)", color = Color(0xFFCBD5E1), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                Text(text = "~450 € TTC réel livré (Faux prix bas)", color = Color(0xFFFBBF24), fontSize = 9.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = """• Coûts cachés : Prix d'appel de 320 € sans transport (+70 € à +90 € de port palette) + support inclinable en option (+50 €).
• Zéro prêt à brancher : Matériel livré en vrac non monté, connecteurs MC4 à sertir, câbles AC à assembler soi-même.
• Cible : Bricoleurs et électriciens uniquement. Inaccessible pour le grand public sans compétences.""",
                color = Color(0xFF94A3B8),
                fontSize = 8.sp,
                lineHeight = 11.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Strategy Verdict Box
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF0369A1).copy(alpha = 0.2f))
                .border(1.dp, Color(0xFF0284C7), RoundedCornerShape(6.dp))
                .padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🎯", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Verdict Compétitif Imbattable pour nos Clients & la SAS :",
                    color = Color(0xFF38BDF8),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "SOLARIS France vend STRICTEMENT les mêmes composants industriels haut de gamme que Sunology (panneau bi-verre Trina Solar et micro-onduleur Hoymiles numéro 1 mondial), mais à MOITIÉ PRIX (349 € vs 699 €). En supprimant les intermédiaires de la grande distribution physique et la pub TV, nous dégageons 97 € à 132 € de marge brute unitaire tout en offrant au consommateur l'amortissement le plus rapide du marché français (2,8 ans contre près de 6 ans pour les concurrents).",
                color = Color(0xFFE0F2FE),
                fontSize = 7.sp,
                lineHeight = 10.sp
            )
        }
    }
}


@Composable
private fun BrandNamingAuditCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFFF59E0B).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "⚖️", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "AUDIT NOM DE MARQUE : 'SOLARIS' vs DOMAINES .FR LIBRES",
                    color = Color(0xFFFBBF24),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFFEF4444).copy(alpha = 0.2f))
                    .border(1.dp, Color(0xFFEF4444), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "ALERTE INPI & NDD",
                    color = Color(0xFFFCA5A5),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Diagnostic de risque légal sur la marque 'SOLARIS' et benchmark des 6 domaines .FR vérifiés libres à l'AFNIC :",
            color = Color(0xFF94A3B8),
            fontSize = 9.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        // ALERTE ROUGE SOLARIS
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF7F1D1D).copy(alpha = 0.25f))
                .border(1.dp, Color(0xFFEF4444).copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                .padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🚨", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "LE NOM 'SOLARIS' EST DÉJÀ PRÉEMPTÉ DANS LE SOLAIRE EN FRANCE",
                    color = Color(0xFFFCA5A5),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = """• solaris.fr : Détenu par GrandVision (lunettes de soleil Solaris).
• solaris-store.com : Détenu par SOLARIS SAS (Saint-André-de-Corcy, Ain), distributeur majeur de matériel solaire et kits autonomes depuis 2002 !
• RISQUE JURIDIQUE : Utiliser 'Solaris' pour des kits solaires en France expose à une action directe en contrefaçon de marque (Classes 9 & 35 INPI) et concurrence déloyale (Art. L713-2 CPI).
• RISQUE SEO : solaris-store.com bénéficie de 22 ans d'antériorité et truste la première page Google sur le solaire.""",
                color = Color(0xFFFECACA),
                fontSize = 8.sp,
                lineHeight = 11.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // SOLUTION 80/20
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF0284C7).copy(alpha = 0.15f))
                .border(1.dp, Color(0xFF38BDF8).copy(alpha = 0.4f), RoundedCornerShape(6.dp))
                .padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "💡", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "LA RÈGLE D'OR 80/20 (SOCIÉTÉ vs MARQUE COMMERCIALE)",
                    color = Color(0xFF7DD3FC),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = """1) Dénomination Sociale SAS (au Greffe) : Nom institutionnel neutre (ex: VOLTIS GROUP SAS ou HELIO TRADE SAS). Elle englobe Solaire + Alarmes sous un seul SIRET sans risque.
2) Marque Commerciale E-commerce : Marque commerciale propre, déposée à l'INPI et associée à un domaine .FR disponible immédiatement.""",
                color = Color(0xFFE0F2FE),
                fontSize = 8.sp,
                lineHeight = 11.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "6 NOMS DE DOMAINE .FR VÉRIFIÉS LIBRES À L'AFNIC :",
            color = Color(0xFF34D399),
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        // GRILLE DES 6 NOMS CANDIDATS
        val candidates = listOf(
            Triple("SOLAVOLT.FR", "Racine Solaire + Voltage", "Sobre, haut de gamme, excellente mémorisation. Protégeable INPI."),
            Triple("PLUGWATT.FR", "Plug & Play + Puissance Watt", "Positionnement ultra direct : compréhensible en 1 seconde par le client."),
            Triple("VOLTEVO.FR", "Évolution Électrique / Tech", "Sonorité technologique moderne type Anker/Tesla. Idéal pour scaler."),
            Triple("SOLARKIT.FR", "Mot-clé #1 Recherché sur Google", "Bonus SEO immédiat sur les requêtes d'achat 'kit solaire autoconsommation'."),
            Triple("SUNIVIA.FR", "Élégant, Lumineux, Grand Public", "Positionnement grand public premium en concurrence frontale avec Sunology."),
            Triple("VOLTOPLUG.FR", "100% Station Solaire sur Prise", "Descriptif fort sans ambiguïté technique pour les bricoleurs.")
        )

        candidates.forEach { (domain, sub, desc) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF1E293B))
                    .border(1.dp, Color(0xFF334155), RoundedCornerShape(6.dp))
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = domain,
                            color = Color(0xFFF8FAFC),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(3.dp))
                                .background(Color(0xFF059669).copy(alpha = 0.2f))
                                .border(1.dp, Color(0xFF10B981), RoundedCornerShape(3.dp))
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = "LIBRE AFNIC",
                                color = Color(0xFF6EE7B7),
                                fontSize = 7.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Text(
                        text = sub,
                        color = Color(0xFFFBBF24),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = desc,
                        color = Color(0xFF94A3B8),
                        fontSize = 7.sp,
                        lineHeight = 9.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // FOOTER ACTION
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF059669).copy(alpha = 0.15f))
                .border(1.dp, Color(0xFF10B981).copy(alpha = 0.3f), RoundedCornerShape(6.dp))
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "✅", fontSize = 11.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "ACTION IMMÉDIATE : Réservation du .FR sur OVHcloud (~7,99 €/an) + Dépôt INPI Classes 9 & 35 (190 € pour 10 ans de protection blindée).",
                color = Color(0xFFA7F3D0),
                fontSize = 8.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun UnitEconomicsCard(economics: UnitEconomicsDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF10B981).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "💰", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "2. MODÈLE ÉCONOMIQUE UNITAIRE (P&L RÉEL)",
                    color = Color(0xFF34D399),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF064E3B))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "P&L SOURCÉ",
                    color = Color(0xFF34D399),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Row 1: Prix Vente & Sourcing
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            KpiChip(label = "Prix Vente Public (TTC / HT)", value = "${economics.retailPriceTTC} • ${economics.retailPriceHT}", modifier = Modifier.weight(1f), color = Color.White)
            KpiChip(label = "Achat Grossiste HT (BOM)", value = economics.wholesaleBOMHT, modifier = Modifier.weight(1f), color = Color(0xFF38BDF8))
        }
        Spacer(modifier = Modifier.height(6.dp))
        // Row 2: Fret & Stripe
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            KpiChip(label = "Fret / Transporteur", value = economics.shippingCost, modifier = Modifier.weight(1f), color = Color(0xFFFDE68A))
            KpiChip(label = "Passerelle / Frais", value = economics.paymentGatewayFee, modifier = Modifier.weight(1f), color = Color(0xFFFCA5A5))
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Big Highlighted Profit Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF064E3B).copy(alpha = 0.6f))
                .border(1.dp, Color(0xFF10B981), RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "BÉNÉFICE NET PAR VENTE :", color = Color(0xFFA7F3D0), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Text(text = economics.netProfitPerUnit, color = Color(0xFF34D399), fontSize = 13.sp, fontWeight = FontWeight.Black)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Objectif Mensuel Associés :", color = Color(0xFFFDE68A), fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = economics.targetMonthlyNet, color = Color(0xFFFBBF24), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Breakdown of Cost Items
        Text(
            text = "Décomposition des Postes de Coûts Usine & Matériel :",
            color = Color(0xFFCBD5E1),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(6.dp))
        economics.costItems.forEach { cost ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = cost.name, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = cost.note, color = Color(0xFF94A3B8), fontSize = 9.sp)
                }
                Text(text = cost.amountHT, color = Color(0xFF38BDF8), fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = economics.executiveSummary,
            color = Color(0xFF94A3B8),
            fontSize = 10.sp,
            lineHeight = 14.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Fournisseurs vérifiés : ${economics.sourceOrSupplier}",
            color = Color(0xFFF59E0B),
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun PartnerProfitCard(profit: PartnerProfitProjection) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFFF59E0B).copy(alpha = 0.6f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "👥", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "3. BÉNÉFICES ESPÉRÉS PAR ASSOCIÉ (50% / 50%)",
                    color = Color(0xFFFBBF24),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF78350F))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "SCÉNARIO RÉALISTE",
                    color = Color(0xFFFDE68A),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF1E293B))
                .padding(8.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Hypothèse de vente prudente :", color = Color(0xFF94A3B8), fontSize = 10.sp)
                    Text(text = profit.monthlyVolumeHypothesis, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Résultat d'exploitation brut SAS :", color = Color(0xFF94A3B8), fontSize = 10.sp)
                    Text(text = profit.totalCompanyNetMonthly, color = Color(0xFF34D399), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF0369A1).copy(alpha = 0.15f))
                    .border(1.dp, Color(0xFF0284C7), RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "👑", fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "ASSOCIÉ 1 (MOI)",
                        color = Color(0xFF38BDF8),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(text = "Président SAS • Mandat 0 €", color = Color(0xFF94A3B8), fontSize = 9.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = profit.associate1NetMonthly,
                    color = Color(0xFF38BDF8),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = profit.associate1SpecificAdvantage,
                    color = Color(0xFFE2E8F0),
                    fontSize = 9.sp,
                    lineHeight = 13.sp
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF059669).copy(alpha = 0.15f))
                    .border(1.dp, Color(0xFF10B981), RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "🤝", fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "ASSOCIÉ 2 (ASSOCIÉ)",
                        color = Color(0xFF34D399),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(text = "Co-fondateur • 50% du capital", color = Color(0xFF94A3B8), fontSize = 9.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = profit.associate2NetMonthly,
                    color = Color(0xFF34D399),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = profit.associate2SpecificAdvantage,
                    color = Color(0xFFE2E8F0),
                    fontSize = 9.sp,
                    lineHeight = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF312E81).copy(alpha = 0.4f))
                .border(1.dp, Color(0xFF6366F1), RoundedCornerShape(6.dp))
                .padding(8.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Dividende net réel en poche / associé :", color = Color(0xFFA5B4FC), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Text(text = profit.annualDividendsPerPartner, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = profit.taxOptimizationNote, color = Color(0xFF94A3B8), fontSize = 9.sp, lineHeight = 12.sp)
            }
        }
    }
}

@Composable
private fun CommercialPacksCard(packs: List<CommercialPack>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF38BDF8).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "📦", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "4. GAMME COMMERCIALE & PACKS CLIENTS",
                    color = Color(0xFF38BDF8),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF0C4A6E))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "OFFRE CATALOGUE",
                    color = Color(0xFF38BDF8),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        packs.forEach { pack ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1E293B))
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = pack.name,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                when (pack.badge) {
                                    "BEST-SELLER" -> Color(0xFF065F46)
                                    "MARGE RECORD" -> Color(0xFF78350F)
                                    else -> Color(0xFF312E81)
                                }
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = pack.badge,
                            color = when (pack.badge) {
                                "BEST-SELLER" -> Color(0xFF34D399)
                                "MARGE RECORD" -> Color(0xFFFDE68A)
                                else -> Color(0xFFA5B4FC)
                            },
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Prix de Vente Client :", color = Color(0xFF94A3B8), fontSize = 10.sp)
                        Text(text = pack.retailPriceTTC, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(text = "Marge Nette Vendeur :", color = Color(0xFFA7F3D0), fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
                        Text(text = pack.netMarginAmount, color = Color(0xFF34D399), fontSize = 12.sp, fontWeight = FontWeight.Black)
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Coût matériel grossiste : ${pack.wholesaleCostHT} • ${pack.netMarginPercent}",
                    color = Color(0xFFFBBF24),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Retour sur investissement client : ${pack.paybackPeriod}",
                    color = Color(0xFF38BDF8),
                    fontSize = 10.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Contenu : ${pack.contents}",
                    color = Color(0xFFCBD5E1),
                    fontSize = 10.sp,
                    lineHeight = 13.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun ProductionSimulationsCard(simulations: List<ProductionSimulation>) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFFF59E0B).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "☀️", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "5. SIMULATEUR DE PRODUCTION & ÉCONOMIES (PVGIS)",
                    color = Color(0xFFFBBF24),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF78350F))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "DONNÉES OFFICIELLES UE",
                    color = Color(0xFFFDE68A),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Production annuelle réelle calculée avec le logiciel satellite PVGIS de la Commission Européenne (inclinaison 30° Sud) :",
            color = Color(0xFF94A3B8),
            fontSize = 10.sp,
            lineHeight = 14.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF064E3B).copy(alpha = 0.25f))
                .border(1.dp, Color(0xFF10B981).copy(alpha = 0.4f), RoundedCornerShape(6.dp))
                .padding(8.dp)
        ) {
            Text(
                text = "☀️ Pourquoi ces écarts ? Le soleil ne brille pas pareil partout en France : un même panneau 440W produit +40% d'électricité en plus dans le Sud qu'à Paris. Les kWh/an mesurent l'électricité gratuite fabriquée qui efface vos factures Linky.",
                color = Color(0xFFA7F3D0),
                fontSize = 10.sp,
                lineHeight = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        simulations.forEach { sim ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1E293B))
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = sim.zoneName,
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = sim.annualSavingsEuros,
                        color = Color(0xFF34D399),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Production : ${sim.annualKwh}", color = Color(0xFF38BDF8), fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = "Amortissement : ${sim.paybackYears}", color = Color(0xFFFBBF24), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(3.dp))
                Row(
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(sim.sourceUrl))
                            context.startActivity(intent)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.OpenInBrowser, contentDescription = "Lien", tint = Color(0xFFA5B4FC), modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Source : ${sim.sourceLabel} ↗", color = Color(0xFFA5B4FC), fontSize = 9.sp)
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Composable
private fun VerifiedSuppliersCard(suppliers: List<VerifiedSupplier>) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF10B981).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🏭", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "6. ANNUAIRE DES GROSSISTES B2B OFFICIELS",
                    color = Color(0xFF34D399),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF064E3B))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "AUDIT FOURNISSEURS",
                    color = Color(0xFF34D399),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Accès directs professionnels vérifiés pour commandes palettes et expéditions directes clients :",
            color = Color(0xFF94A3B8),
            fontSize = 10.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        suppliers.forEach { supplier ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1E293B))
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = supplier.name,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = supplier.location,
                            color = Color(0xFF38BDF8),
                            fontSize = 9.sp
                        )
                    }

                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(supplier.portalUrl))
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0284C7)),
                        modifier = Modifier.height(28.dp)
                    ) {
                        Icon(Icons.Default.OpenInBrowser, contentDescription = "Lien", tint = Color.White, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Portail B2B", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Rôle : ${supplier.role}",
                    color = Color(0xFFCBD5E1),
                    fontSize = 10.sp
                )
                Text(
                    text = "Contact pro : ${supplier.contactB2B}",
                    color = Color(0xFFFBBF24),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "Conditions : ${supplier.conditions}",
                    color = Color(0xFF94A3B8),
                    fontSize = 9.sp,
                    lineHeight = 12.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun PartnerPitchCard(pitchArgs: List<PartnerPitchArgument>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF6366F1).copy(alpha = 0.6f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🗣️", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "7. PITCH ASSOCIÉ : 5 ARGUMENTS MASSUE EN 3 MIN",
                    color = Color(0xFFA5B4FC),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF312E81))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "VALIDATION IMMÉDIATE",
                    color = Color(0xFFA5B4FC),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Ce qu'il faut expliquer à votre associé pour décider d'ouvrir la SAS sans aucune peur :",
            color = Color(0xFF94A3B8),
            fontSize = 10.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        pitchArgs.forEach { pitch ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1E293B))
                    .padding(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFF4F46E5)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "${pitch.number}", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = pitch.title,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = pitch.argument,
                    color = Color(0xFFCBD5E1),
                    fontSize = 10.sp,
                    lineHeight = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Preuve vérifiable : ${pitch.keyProof}",
                    color = Color(0xFF34D399),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Composable
private fun RiskMitigationsCard(risks: List<RiskMitigationItem>) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFFEF4444).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🛡️", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "8. BOUCLIER ANTI-RISQUES (SOLUTIONS 100% VIABLES)",
                    color = Color(0xFFF87171),
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFFEF4444).copy(alpha = 0.2f))
                    .border(1.dp, Color(0xFFEF4444), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "SÉCURITÉ BLINDÉE",
                    color = Color(0xFFFCA5A5),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Anticipation rigoureuse des 8 risques majeurs et parades juridiques, logistiques et financières testées :",
            color = Color(0xFF94A3B8),
            fontSize = 9.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        risks.forEach { risk ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1E293B))
                    .border(1.dp, Color(0xFF475569), RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = risk.title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFF59E0B).copy(alpha = 0.2f))
                            .border(1.dp, Color(0xFFF59E0B), RoundedCornerShape(4.dp))
                            .padding(horizontal = 5.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = risk.probabilityLevel,
                            color = Color(0xFFFCD34D),
                            fontSize = 7.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "⚠️ Impact potentiel : ", color = Color(0xFFF87171), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    Text(text = risk.potentialImpact, color = Color(0xFFFCA5A5), fontSize = 9.sp)
                }

                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "Mode de défaillance : ${risk.failureModeDescription}",
                    color = Color(0xFF94A3B8),
                    fontSize = 9.sp,
                    lineHeight = 13.sp
                )

                Spacer(modifier = Modifier.height(6.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF064E3B).copy(alpha = 0.35f))
                        .border(1.dp, Color(0xFF10B981).copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "✅ PARADE VIABLE À 100% :",
                            color = Color(0xFF34D399),
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("Parade Risque", risk.viableSolution)
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(context, "Parade copiée !", Toast.LENGTH_SHORT).show()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copier",
                                tint = Color(0xFF34D399),
                                modifier = Modifier.size(11.dp)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "Copier",
                                color = Color(0xFF34D399),
                                fontSize = 8.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = risk.viableSolution,
                        color = Color(0xFFE2E8F0),
                        fontSize = 9.sp,
                        lineHeight = 13.sp
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Preuve vérifiable : ${risk.legalOrTechnicalProof}",
                    color = Color(0xFF38BDF8),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}




@Composable
private fun OperationalPhasesCard(
    steps: List<OperationalPhaseStep>,
    onToggle: (String) -> Unit
) {
    val completedCount = steps.count { it.isCompleted }
    val totalCount = steps.size
    val progress = if (totalCount > 0) completedCount.toFloat() / totalCount.toFloat() else 0f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF8B5CF6).copy(alpha = 0.6f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "📋", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "11. PLAN D'ACTION OPÉRATIONNEL (J-15 À J+90)",
                    color = Color(0xFFA78BFA),
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(if (completedCount == totalCount) Color(0xFF10B981).copy(alpha = 0.2f) else Color(0xFF6D28D9).copy(alpha = 0.2f))
                    .border(1.dp, if (completedCount == totalCount) Color(0xFF10B981) else Color(0xFF8B5CF6), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "$completedCount / $totalCount ÉTAPES",
                    color = if (completedCount == totalCount) Color(0xFF34D399) else Color(0xFFDDD6FE),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Rétroplanning en 6 phases chronologiques 100% sourcées pour réussir le premier projet :",
            color = Color(0xFF94A3B8),
            fontSize = 9.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = Color(0xFF10B981),
            trackColor = Color(0xFF334155)
        )

        Spacer(modifier = Modifier.height(10.dp))

        steps.forEach { step ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1E293B))
                    .border(
                        1.dp,
                        if (step.isCompleted) Color(0xFF10B981).copy(alpha = 0.7f) else Color(0xFF334155),
                        RoundedCornerShape(8.dp)
                    )
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onToggle(step.stepNumber) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = step.isCompleted,
                        onCheckedChange = { onToggle(step.stepNumber) },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF10B981),
                            uncheckedColor = Color(0xFF64748B)
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(3.dp))
                                .background(Color(0xFF4C1D95).copy(alpha = 0.5f))
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = step.phaseName,
                                color = Color(0xFFDDD6FE),
                                fontSize = 7.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Étape ${step.stepNumber} : ${step.taskTitle}",
                            color = if (step.isCompleted) Color(0xFF94A3B8) else Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF0369A1).copy(alpha = 0.2f))
                            .border(1.dp, Color(0xFF0284C7), RoundedCornerShape(4.dp))
                            .padding(horizontal = 5.dp, vertical = 2.dp)
                    ) {
                        Text(text = "📅 ${step.timing}", color = Color(0xFF7DD3FC), fontSize = 8.sp, fontWeight = FontWeight.SemiBold)
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF334155))
                            .padding(horizontal = 5.dp, vertical = 2.dp)
                    ) {
                        Text(text = "👤 ${step.assignee}", color = Color(0xFFFDE68A), fontSize = 8.sp, fontWeight = FontWeight.SemiBold)
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF064E3B).copy(alpha = 0.3f))
                            .border(1.dp, Color(0xFF10B981), RoundedCornerShape(4.dp))
                            .padding(horizontal = 5.dp, vertical = 2.dp)
                    ) {
                        Text(text = "💶 ${step.costOrRevenue}", color = Color(0xFF34D399), fontSize = 8.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = step.actionDetails,
                    color = Color(0xFFCBD5E1),
                    fontSize = 9.sp,
                    lineHeight = 13.sp
                )

                Spacer(modifier = Modifier.height(6.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFF0F172A))
                        .padding(6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "📦 Livrable clé : ", color = Color(0xFF34D399), fontSize = 8.sp, fontWeight = FontWeight.Bold)
                        Text(text = step.expectedDeliverable, color = Color(0xFFA7F3D0), fontSize = 8.sp)
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "⚖️ Source / Légal : ", color = Color(0xFF94A3B8), fontSize = 8.sp, fontWeight = FontWeight.Bold)
                        Text(text = step.officialSourceOrLegal, color = Color(0xFFCBD5E1), fontSize = 8.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun GrowthStrategyCard(levers: List<GrowthLever>) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF10B981).copy(alpha = 0.6f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🚀", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "10. ACCÉLÉRATION DU PROFIT & SCALE RAPIDE",
                    color = Color(0xFF34D399),
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF065F46))
                    .border(1.dp, Color(0xFF10B981), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "+47% À +100% BÉNÉFICE",
                    color = Color(0xFFA7F3D0),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Les 4 leviers à fort effet de levier (80/20) pour faire exploser les marges et les parts de marché :",
            color = Color(0xFF94A3B8),
            fontSize = 9.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        levers.forEach { lever ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1E293B))
                    .border(1.dp, Color(0xFF334155), RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = lever.title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF0369A1).copy(alpha = 0.25f))
                            .border(1.dp, Color(0xFF38BDF8), RoundedCornerShape(4.dp))
                            .padding(horizontal = 5.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = lever.timeToDeploy,
                            color = Color(0xFF7DD3FC),
                            fontSize = 7.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFF064E3B).copy(alpha = 0.35f))
                        .border(1.dp, Color(0xFF10B981).copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "💰 IMPACT BÉNÉFICE : ${lever.netProfitImpact}",
                        color = Color(0xFF34D399),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = lever.operationalStrategy,
                    color = Color(0xFFCBD5E1),
                    fontSize = 9.sp,
                    lineHeight = 13.sp
                )

                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF0F172A))
                        .border(1.dp, Color(0xFF475569), RoundedCornerShape(6.dp))
                        .padding(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.RocketLaunch,
                        contentDescription = "Action",
                        tint = Color(0xFFFBBF24),
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Étape clé : ${lever.keyActionStep}",
                        color = Color(0xFFFDE68A),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun AssociateVerificationsCard(
    verifications: List<AssociateVerificationItem>,
    onToggle: (String) -> Unit
) {
    val context = LocalContext.current
    val completedCount = verifications.count { it.isVerified }
    val totalCount = verifications.size

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF38BDF8).copy(alpha = 0.6f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🔍", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "9. CHECKLIST D'AUDIT TERRAIN (À VÉRIFIER PAR VOUS)",
                    color = Color(0xFF38BDF8),
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(if (completedCount == totalCount) Color(0xFF10B981).copy(alpha = 0.2f) else Color(0xFF0284C7).copy(alpha = 0.2f))
                    .border(1.dp, if (completedCount == totalCount) Color(0xFF10B981) else Color(0xFF38BDF8), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "$completedCount / $totalCount VÉRIFIÉS",
                    color = if (completedCount == totalCount) Color(0xFF34D399) else Color(0xFF7DD3FC),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Pour 0 approximation et 0 litige : les 5 vérifications concrètes à mener vous-mêmes avec scripts et pièces à exiger :",
            color = Color(0xFF94A3B8),
            fontSize = 9.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        verifications.forEach { verif ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1E293B))
                    .border(
                        1.dp,
                        if (verif.isVerified) Color(0xFF10B981).copy(alpha = 0.7f) else Color(0xFF475569),
                        RoundedCornerShape(8.dp)
                    )
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onToggle(verif.id) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = verif.isVerified,
                        onCheckedChange = { onToggle(verif.id) },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF10B981),
                            uncheckedColor = Color(0xFF64748B)
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = verif.title,
                            color = if (verif.isVerified) Color(0xFF94A3B8) else Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFF334155))
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = "Responsable : ${verif.assignedTo}",
                                color = Color(0xFFFCD34D),
                                fontSize = 8.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF0F172A))
                        .border(1.dp, Color(0xFF334155), RoundedCornerShape(6.dp))
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "📞 ACTION OU SCRIPT EXACT :",
                            color = Color(0xFF38BDF8),
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("Script Action", verif.exactActionScript)
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(context, "Script copié !", Toast.LENGTH_SHORT).show()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copier",
                                tint = Color(0xFF38BDF8),
                                modifier = Modifier.size(11.dp)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "Copier",
                                color = Color(0xFF38BDF8),
                                fontSize = 8.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = verif.exactActionScript,
                        color = Color(0xFFCBD5E1),
                        fontSize = 9.sp,
                        lineHeight = 13.sp
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "📄 Pièce à exiger : ", color = Color(0xFF34D399), fontSize = 8.sp, fontWeight = FontWeight.Bold)
                    Text(text = verif.documentToObtain, color = Color(0xFFA7F3D0), fontSize = 8.sp)
                }

                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "⚖️ Source officielle : ", color = Color(0xFF94A3B8), fontSize = 8.sp, fontWeight = FontWeight.Bold)
                    Text(text = verif.officialLegalSource, color = Color(0xFFCBD5E1), fontSize = 8.sp)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun HumanPrerequisitesCard(
    items: List<HumanPrerequisiteItem>,
    onToggle: (String) -> Unit
) {
    val context = LocalContext.current
    val completedCount = items.count { it.isCompleted }
    val totalCount = items.size

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFFF43F5E).copy(alpha = 0.7f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🛑", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "ACTIONS HUMAINES OBLIGATOIRES (HORS IA)",
                    color = Color(0xFFFB7185),
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(if (completedCount == totalCount) Color(0xFF10B981).copy(alpha = 0.2f) else Color(0xFFF43F5E).copy(alpha = 0.2f))
                    .border(1.dp, if (completedCount == totalCount) Color(0xFF10B981) else Color(0xFFFB7185), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "$completedCount / $totalCount RÉALISÉES",
                    color = if (completedCount == totalCount) Color(0xFF34D399) else Color(0xFFFDA4AF),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Pour un lancement 100% prêt : les démarches légales, bancaires et physiques strictes que seuls les associés humains peuvent accomplir en droit français :",
            color = Color(0xFF94A3B8),
            fontSize = 9.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        items.forEach { item ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1E293B))
                    .border(
                        1.dp,
                        if (item.isCompleted) Color(0xFF10B981).copy(alpha = 0.7f) else Color(0xFF475569),
                        RoundedCornerShape(8.dp)
                    )
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onToggle(item.id) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = item.isCompleted,
                        onCheckedChange = { onToggle(item.id) },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF10B981),
                            uncheckedColor = Color(0xFFFB7185)
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = item.title,
                            color = if (item.isCompleted) Color(0xFF94A3B8) else Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0xFF334155))
                                    .padding(horizontal = 4.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = "👤 ${item.assignedTo}",
                                    color = Color(0xFFFCD34D),
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0xFF0F172A))
                                    .border(1.dp, Color(0xFF38BDF8).copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 4.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = "💰 ${item.estimatedCost}",
                                    color = Color(0xFF38BDF8),
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Pourquoi l'IA ne peut pas le faire
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF881337).copy(alpha = 0.25f))
                        .border(1.dp, Color(0xFFF43F5E).copy(alpha = 0.35f), RoundedCornerShape(6.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "❌ POURQUOI L'IA NE PEUT PAS LE FAIRE :",
                        color = Color(0xFFFDA4AF),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = item.whyAiCannotDoIt,
                        color = Color(0xFFFECDD3),
                        fontSize = 9.sp,
                        lineHeight = 13.sp
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Action humaine requise
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF064E3B).copy(alpha = 0.25f))
                        .border(1.dp, Color(0xFF10B981).copy(alpha = 0.35f), RoundedCornerShape(6.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "✅ ACTION HUMAINE CONCRÈTE REQUISE :",
                        color = Color(0xFF6EE7B7),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = item.actionRequiredByPartner,
                        color = Color(0xFFA7F3D0),
                        fontSize = 9.sp,
                        lineHeight = 13.sp
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.officialUrl))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E293B)),
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, Color(0xFF38BDF8).copy(alpha = 0.5f))
                ) {
                    Icon(Icons.Default.OpenInBrowser, contentDescription = "Ouvrir", modifier = Modifier.size(12.dp), tint = Color(0xFF38BDF8))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Ouvrir le portail officiel pour cette démarche",
                        color = Color(0xFF38BDF8),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Composable
private fun ObjectionsCard(objections: List<ObjectionItem>) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFFF59E0B).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🎯", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "12. FAQ OBJECTIONS & PARADES COMMERCIALES",
                    color = Color(0xFFFBBF24),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF78350F))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "CLOSING DIRECT",
                    color = Color(0xFFFDE68A),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Arguments et répliques mot-à-mot à dégainer par téléphone, SMS, Leboncoin ou en face-à-face :",
            color = Color(0xFF94A3B8),
            fontSize = 10.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        objections.forEach { item ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1E293B))
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = item.objection,
                            color = Color(0xFFFCA5A5),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Angle tactique : ${item.tacticalAngle}",
                            color = Color(0xFF38BDF8),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Button(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("Réplique commerciale", item.rebuttal)
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "Réplique copiée !", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF334155)),
                        modifier = Modifier.height(28.dp)
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = "Copier", tint = Color.White, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Copier", fontSize = 10.sp)
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF0F172A))
                        .padding(8.dp)
                ) {
                    Text(
                        text = item.rebuttal,
                        color = Color(0xFFE2E8F0),
                        fontSize = 11.sp,
                        lineHeight = 15.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun SasCreationRoadmapCard() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF38BDF8).copy(alpha = 0.6f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🏢", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "PROTOCOLE DE CRÉATION DE LA SAS & STATUTS QONTO",
                    color = Color(0xFF38BDF8),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF0284C7).copy(alpha = 0.2f))
                    .border(1.dp, Color(0xFF38BDF8), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "JURIDIQUE & STATUTS",
                    color = Color(0xFF7DD3FC),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Guide opérationnel pour créer la SAS multi-activités (Solaire + Alarmes) sous 1 seul SIRET sans perdre l'ARE :",
            color = Color(0xFF94A3B8),
            fontSize = 9.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        // LES 4 ÉTAPES CLÉS
        val steps = listOf(
            Triple("1. Dépôt Capital Qonto (1 000 €)", "Virement 500 € / associé sur compte de séquestre en ligne.", "Certificat officiel de dépôt des fonds délivré sous 24h ouvrées."),
            Triple("2. Statuts SAS & Objet Social Article 2", "Dénomination sociale neutre (ex: VOLTIS GROUP SAS ou HELIO TRADE SAS).", "Objet social rédigé pour couvrir Solaire 440W + Alarmes 4G sous un seul SIRET."),
            Triple("3. Mandat Gratuit & Bouclier ARE 100%", "Nomination du Président à titre 100% gratuit (0,00 € salaire).", "Zéro cotisation minimale URSSAF. Maintien intégral de l'allocation chômage chaque mois."),
            Triple("4. Guichet Unique INPI & Extrait Kbis", "Annonce légale JAL (~130 €) + Frais de Greffe et RBE (~60 €).", "Obtention de l'extrait Kbis officiel sous 3 à 5 jours ouvrés.")
        )

        steps.forEach { (title, subtitle, detail) ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF1E293B))
                    .border(1.dp, Color(0xFF334155), RoundedCornerShape(6.dp))
                    .padding(8.dp)
            ) {
                Text(
                    text = title,
                    color = Color(0xFFFBBF24),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    color = Color(0xFFE2E8F0),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = detail,
                    color = Color(0xFF94A3B8),
                    fontSize = 7.sp,
                    lineHeight = 9.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // BANDEAU FISCALITÉ DIVIDENDES
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF059669).copy(alpha = 0.15f))
                .border(1.dp, Color(0xFF10B981).copy(alpha = 0.4f), RoundedCornerShape(6.dp))
                .padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "💰", fontSize = 11.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "FISCALITÉ OPTIMISÉE DES BÉNÉFICES (DIVIDENDES)",
                    color = Color(0xFF6EE7B7),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Les dividendes de SAS ne subissent AUCUNE cotisation sociale URSSAF (contrairement à la SARL). Taxation unique à la Flat Tax (PFU 30%), totalement cumulable avec l'ARE sans diminution d'allocation.",
                color = Color(0xFFA7F3D0),
                fontSize = 7.sp,
                lineHeight = 9.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // BOUTON ACTION RAPIDE COPIER
        Button(
            onClick = {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val text = """ARTICLE 2 - OBJET SOCIAL DE LA SAS (Solaire & Sécurité)
1. Le commerce, vente à distance et e-commerce d'équipements liés aux énergies renouvelables et stations solaires d'autoconsommation ;
2. Le commerce et la distribution d'équipements de sécurité électronique, alarmes autonomes sans fil, vidéosurveillance et domotique ;
3. Le conseil, l'assistance technique à distance et le SAV associé.
(Couvre Solaire + Alarmes sous 1 seul SIRET et 1 compte Qonto)"""
                val clip = ClipData.newPlainText("Article 2 Objet Social", text)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(context, "Article 2 copié dans le presse-papier !", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0284C7)),
            modifier = Modifier.fillMaxWidth().height(36.dp)
        ) {
            Icon(Icons.Default.ContentCopy, contentDescription = "Copier", tint = Color.White, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Copier la Clause Article 2 des Statuts pour Qonto", fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun RegulatoryRoadmapCard(roadmap: List<RegulatoryRoadmapItem>) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF38BDF8).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "⚖️", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "13. CADRE LÉGAL, DÉMARCHES & SÉCURISATION",
                    color = Color(0xFF38BDF8),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF0C4A6E))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "100% LÉGAL",
                    color = Color(0xFF38BDF8),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Boucliers juridiques, conformité Enedis, Code du travail et Unédic :",
            color = Color(0xFF94A3B8),
            fontSize = 10.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        roadmap.forEach { step ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1E293B))
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF0284C7))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = step.badge.uppercase(),
                            color = Color.White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    step.officialLink?.let { url ->
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .clickable {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                    context.startActivity(intent)
                                }
                                .padding(horizontal = 4.dp, vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.OpenInBrowser, contentDescription = "Lien", tint = Color(0xFF38BDF8), modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(text = "Texte Officiel ↗", color = Color(0xFF38BDF8), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = step.title,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Base juridique : ${step.legalRef}",
                    color = Color(0xFFFBBF24),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = step.description,
                    color = Color(0xFFCBD5E1),
                    fontSize = 11.sp,
                    lineHeight = 15.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun KpiChip(label: String, value: String, modifier: Modifier = Modifier, color: Color) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xFF1E293B))
            .padding(8.dp)
    ) {
        Text(text = label.uppercase(), color = Color(0xFF94A3B8), fontSize = 9.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = value, color = color, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, lineHeight = 14.sp)
    }
}

@Composable
private fun ConceptionItemRow(item: ConceptionDetail) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF0F172A))
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.title,
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            item.verifiedSourceUrl?.let { url ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF0284C7))
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Link, contentDescription = "Lien", tint = Color.White, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Lien Direct", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = item.description,
            color = Color(0xFFCBD5E1),
            fontSize = 11.sp
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Donnée vérifiée : ${item.keyMetric}",
            color = Color(0xFF34D399),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )

        item.officialReference?.let { ref ->
            Text(
                text = "Source légale / Norme : $ref",
                color = Color(0xFFFBBF24),
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun ToolkitItemRow(tool: ToolkitItem) {
    val context = LocalContext.current
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, if (isExpanded) Color(0xFFFBBF24) else Color(0xFF334155), RoundedCornerShape(8.dp))
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { isExpanded = !isExpanded }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = tool.title,
                        color = Color(0xFFFBBF24),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Déplier",
                        tint = Color(0xFFFBBF24),
                        modifier = Modifier.size(16.dp)
                    )
                }
                Text(
                    text = "${tool.category} • ${tool.sourceOrNorm}",
                    color = Color(0xFF94A3B8),
                    fontSize = 10.sp
                )
            }

            Button(
                onClick = {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText(tool.title, tool.contentToCopy)
                    clipboard.setPrimaryClip(clip)
                    Toast.makeText(context, "Texte copié dans le presse-papier !", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF334155)),
                modifier = Modifier.height(32.dp)
            ) {
                Icon(Icons.Default.ContentCopy, contentDescription = "Copier", tint = Color.White, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Copier", fontSize = 11.sp)
            }
        }

        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF030712))
                .clickable { isExpanded = !isExpanded }
                .padding(8.dp)
        ) {
            Text(
                text = if (isExpanded) tool.contentToCopy else (tool.contentToCopy.take(160) + (if (tool.contentToCopy.length > 160) "\n\n[👉 Appuyez ici pour lire l'intégralité du document]" else "")),
                color = if (isExpanded) Color(0xFFE2E8F0) else Color(0xFF94A3B8),
                fontSize = 10.sp,
                lineHeight = 14.sp
            )
        }
    }
}

@Composable
private fun PartnerNotesSection(
    dossierId: String,
    noteText: String,
    onNoteChange: (String) -> Unit,
    onSave: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF0284C7), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.EditNote, contentDescription = "Notes", tint = Color(0xFF38BDF8))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "JOURNAL DE BORD & NOTES ASSOCIÉS",
                    color = Color(0xFF38BDF8),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = onSave,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0284C7)),
                modifier = Modifier.height(32.dp)
            ) {
                Icon(Icons.Default.Save, contentDescription = "Save", tint = Color.White, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Enregistrer", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = noteText,
            onValueChange = onNoteChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            placeholder = { Text("Écrivez vos notes, idées de réunions, comptes-rendus fournisseurs et actions à mener ici...") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF38BDF8),
                unfocusedBorderColor = Color(0xFF334155),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color(0xFFCBD5E1)
            )
        )
    }
}

@Composable
private fun LiveTreasuryTrackerCard() {
    var salesCount by remember { mutableStateOf(30) }
    var isMixLocal by remember { mutableStateOf(false) }

    val unitCostHT = if (isMixLocal) 183.64 else 194.14
    val unitPriceHT = 290.83
    val caHT = salesCount * unitPriceHT
    val directCostsHT = salesCount * unitCostHT
    val grossMargin = caHT - directCostsHT
    val monthlyFixedCosts = 212.67
    val ebit = maxOf(0.0, grossMargin - monthlyFixedCosts)
    val is15 = ebit * 0.15
    val netSas = ebit - is15
    val partnerGrossDividend = netSas / 2.0
    val flatTax30 = partnerGrossDividend * 0.30
    val netInPocketPerPartner = partnerGrossDividend - flatTax30
    val annualNetPerPartner = netInPocketPerPartner * 12.0

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF10B981).copy(alpha = 0.7f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "📊", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "SIMULATEUR DE TRÉSORERIE & DIVIDENDES SAS",
                    color = Color(0xFF34D399),
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF10B981).copy(alpha = 0.2f))
                    .border(1.dp, Color(0xFF10B981), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "$salesCount VENTES / MOIS",
                    color = Color(0xFF34D399),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Modèle financier audité de la SAS (chiffres 100% cohérents, certifiés hors chômage/ARE) :",
            color = Color(0xFF94A3B8),
            fontSize = 9.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Slider Ventes
        Text(
            text = "Volume de Ventes Mensuelles : $salesCount stations solaires / mois",
            color = Color.White,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
        Slider(
            value = salesCount.toFloat(),
            onValueChange = { salesCount = it.toInt() },
            valueRange = 5f..60f,
            steps = 54,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFF10B981),
                activeTrackColor = Color(0xFF10B981),
                inactiveTrackColor = Color(0xFF334155)
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Démarrage : 5/m", color = Color(0xFF64748B), fontSize = 8.sp)
            Text(text = "Cible Prudente : 30/m (1/jour)", color = Color(0xFFFBBF24), fontSize = 8.sp, fontWeight = FontWeight.Bold)
            Text(text = "Régime Croisière : 60/m", color = Color(0xFF34D399), fontSize = 8.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Mode Logistique Toggle
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (!isMixLocal) Color(0xFF065F46) else Color(0xFF1E293B))
                    .border(1.dp, if (!isMixLocal) Color(0xFF34D399) else Color(0xFF334155), RoundedCornerShape(6.dp))
                    .clickable { isMixLocal = false }
                    .padding(vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "📦 100% Expédition Geodis (35 €)",
                    color = if (!isMixLocal) Color.White else Color(0xFF94A3B8),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (isMixLocal) Color(0xFF065F46) else Color(0xFF1E293B))
                    .border(1.dp, if (isMixLocal) Color(0xFF34D399) else Color(0xFF334155), RoundedCornerShape(6.dp))
                    .clickable { isMixLocal = true }
                    .padding(vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🤝 Mixte (30% Retrait Local 0 €)",
                    color = if (isMixLocal) Color.White else Color(0xFF94A3B8),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 4 KPI Grid
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF1E293B))
                    .padding(8.dp)
            ) {
                Column {
                    Text(text = "Chiffre d'Affaires HT", color = Color(0xFF94A3B8), fontSize = 8.sp)
                    Text(
                        text = "${caHT.toInt()} € HT",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF1E293B))
                    .padding(8.dp)
            ) {
                Column {
                    Text(text = "Marge Brute HT", color = Color(0xFF94A3B8), fontSize = 8.sp)
                    Text(
                        text = "+${grossMargin.toInt()} € HT",
                        color = Color(0xFF38BDF8),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF1E293B))
                    .padding(8.dp)
            ) {
                Column {
                    Text(text = "Frais Fixes & IS", color = Color(0xFF94A3B8), fontSize = 8.sp)
                    Text(
                        text = "-${(monthlyFixedCosts + is15).toInt()} €",
                        color = Color(0xFFF87171),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF1E293B))
                    .padding(8.dp)
            ) {
                Column {
                    Text(text = "Bénéfice Net SAS", color = Color(0xFF94A3B8), fontSize = 8.sp)
                    Text(
                        text = "+${netSas.toInt()} €",
                        color = Color(0xFF34D399),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Master Result Card (Dividende Net en Poche / Associé)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF064E3B).copy(alpha = 0.45f))
                .border(1.dp, Color(0xFF10B981), RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "DIVIDENDE NET EN POCHE / ASSOCIÉ (50 / 50) :",
                        color = Color(0xFF6EE7B7),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Après TOUS les coûts directs, charges fixes (212 €), IS 15% et Flat Tax 30%",
                        color = Color(0xFFA7F3D0),
                        fontSize = 7.sp
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "+${netInPocketPerPartner.toInt()} € NETS / mois",
                        color = Color(0xFF34D399),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = "(soit ${(annualNetPerPartner).toInt()} € nets virés / an)",
                        color = Color(0xFF6EE7B7),
                        fontSize = 8.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Legal & Social Status Callout Box
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF1E293B))
                .border(1.dp, Color(0xFF334155), RoundedCornerShape(6.dp))
                .padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🛡️", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Statut Juridique & Protection Sociale du Président :",
                    color = Color(0xFFFBBF24),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Le mandat social de Président est exercé à titre gratuit (0 € de salaire). Ce choix 100% légal (Art. L5421-1 Code du travail) permet au dirigeant de conserver l'intégralité de son allocation chômage personnelle France Travail (ARE), quel qu'en soit le montant individuel. Les dividendes de SAS (soumis au PFU 30%) ne sont pas assujettis aux cotisations sociales URSSAF et ne réduisent pas les droits chômage.",
                color = Color(0xFFCBD5E1),
                fontSize = 7.sp,
                lineHeight = 10.sp
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "🎯 Point Mort : 2,2 stations / mois. Dès la 3ème vente du mois, 100% des frais fixes de la société sont absorbés.",
            color = Color(0xFF94A3B8),
            fontSize = 7.sp
        )
    }
}

@Composable
private fun LeboncoinPilotAdCard() {
    val context = LocalContext.current

    val adTitle = "Station Solaire 440W Plug & Play - SOLARIS France SAS (Garantie 25 ans)"
    val adBody = """☀️ STATION SOLAIRE PLUG & PLAY 440W — VENTE DIRECTE FABRICANT PAR SOLARIS FRANCE SAS
Matériel industriel neuf certifié sous emballage d'origine scellé.
Facture officielle avec TVA récupérable (garantie constructeur 25 ans pièces et main d'œuvre).

Économisez 120 € à 180 € par an sur votre facture d'électricité dès le premier mois.
Amorti en moins de 3 ans (face aux offres concurrentes vendues plus de 700 € pour la même technologie).

✅ COMPOSITION DU PACK COMPLET :
- 1x Panneau solaire Trina Solar Vertex S+ 440Wc Bi-verre N-Type (Rendement 22%, garantie 25 ans)
- 1x Micro-onduleur Hoymiles HMS-400 homologué DIN VDE-AR-N 4105 (Coupure instantanée < 0,02s si débranché)
- 1x Châssis alu Ksolar inclinable et renforcé (20° à 45° - résiste au vent 130 km/h)
- 1x Câble d'alimentation extérieur 5m avec prise Schuko 16A étanche IP68
- Guide de raccordement et attestation Enedis CACSI pré-remplie

⚖️ 100% CONFORME & SANS TRAVAUX :
- Pose au sol / balcon / terrasse sans déclaration de travaux (< 1,80m de hauteur)
- Zéro électricien requis : branchement direct sur n'importe quelle prise murale 220V standard.

📦 RETRAIT & LIVRAISON :
- Retrait direct à notre dépôt local (0 € de frais de port) sur prise de rendez-vous.
- Livraison sur toute la France par transporteur sécurisé Geodis (demi-palette avec hayon sur RDV).
- Commandes et fiches techniques téléchargeables sur notre site officiel : https://devtest09az.github.io/simupro-france/

📞 Service client & réservations : 09 72 10 44 20 ou par messagerie Leboncoin Pro."""

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF38BDF8).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🎯", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "STRATÉGIE D'ACQUISITION MULTI-CANAL HAUTE CRÉDIBILITÉ",
                    color = Color(0xFF38BDF8),
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF38BDF8).copy(alpha = 0.2f))
                    .border(1.dp, Color(0xFF38BDF8), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "MIX MARKETING B2C & LOCAL",
                    color = Color(0xFF7DD3FC),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Pour maximiser la rentabilité tout en conservant une image 100% professionnelle, SOLARIS France combine 4 canaux complémentaires :",
            color = Color(0xFF94A3B8),
            fontSize = 9.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        // 4 Acquisition Pillars Grid
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF1E293B))
                    .border(1.dp, Color(0xFF334155), RoundedCornerShape(6.dp))
                    .padding(6.dp)
            ) {
                Text(text = "🌐 1. Site Web Officiel", color = Color(0xFF34D399), fontSize = 8.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "Paiement CB sécurisé Stripe, réassurance légale CGV, fiches techniques PDF et SAV.", color = Color(0xFFCBD5E1), fontSize = 7.sp, lineHeight = 9.sp)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF1E293B))
                    .border(1.dp, Color(0xFF334155), RoundedCornerShape(6.dp))
                    .padding(6.dp)
            ) {
                Text(text = "🔍 2. Google Search & Local", color = Color(0xFF60A5FA), fontSize = 8.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "Fiche Google Établissement Pro, avis vérifiés et capture de requêtes intentionnistes.", color = Color(0xFFCBD5E1), fontSize = 7.sp, lineHeight = 9.sp)
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF1E293B))
                    .border(1.dp, Color(0xFF334155), RoundedCornerShape(6.dp))
                    .padding(6.dp)
            ) {
                Text(text = "🏢 3. Vitrine Leboncoin PRO", color = Color(0xFFFBBF24), fontSize = 8.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "Compte Pro certifié avec SIRET & TVA. 1er comparateur d'équipement en France (CAC = 0 €).", color = Color(0xFFCBD5E1), fontSize = 7.sp, lineHeight = 9.sp)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF1E293B))
                    .border(1.dp, Color(0xFF334155), RoundedCornerShape(6.dp))
                    .padding(6.dp)
            ) {
                Text(text = "🤝 4. Réseau & Prescripteurs", color = Color(0xFFA78BFA), fontSize = 8.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "Électriciens locaux, artisans de l'habitat et comités d'entreprise (CSE) pour ventes groupées.", color = Color(0xFFCBD5E1), fontSize = 7.sp, lineHeight = 9.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Ad preview box
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF1E293B))
                .border(1.dp, Color(0xFF475569), RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Format d'Annonce Certifiée Vitrine PRO :",
                    color = Color(0xFF94A3B8),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Compte Entreprise avec SIRET & TVA",
                    color = Color(0xFF34D399),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = adTitle,
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "🏷️ Prix : 349 € TTC (Facture pro)", color = Color(0xFFFBBF24), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                Text(text = "📍 Localisation : Retrait local ou Fret 48h", color = Color(0xFF38BDF8), fontSize = 9.sp)
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = adBody,
                color = Color(0xFFCBD5E1),
                fontSize = 8.sp,
                lineHeight = 11.sp,
                maxLines = 8
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Annonce Vitrine Pro", "$adTitle\n\n$adBody")
                clipboard.setPrimaryClip(clip)
                Toast.makeText(context, "Texte de la vitrine Pro copié dans le presse-papier !", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0284C7)),
            shape = RoundedCornerShape(6.dp)
        ) {
            Icon(Icons.Default.ContentCopy, contentDescription = "Copier", modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Copier l'annonce Vitrine Pro dans le presse-papier",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
private fun AlarmeProductionSummaryCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFFF43F5E).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🚨", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "SECUREVO FRANCE — ALARME 4G SANS ABONNEMENT",
                    color = Color(0xFFFB7185),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFFF43F5E).copy(alpha = 0.2f))
                    .border(1.dp, Color(0xFFF43F5E), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "EXPANSION PHASE 2",
                    color = Color(0xFFFDA4AF),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Deuxième marque de notre SAS : synergie totale avec la cible maison individuelle, couverte par le même SIRET et le même compte bancaire sans aucuns frais supplémentaires.",
            color = Color(0xFFCBD5E1),
            fontSize = 9.sp,
            lineHeight = 12.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            KpiChip(label = "Prix Vente TTC", value = "149,00 € TTC", modifier = Modifier.weight(1f), color = Color(0xFF38BDF8))
            KpiChip(label = "Coût Revient Direct", value = "44,79 € HT", modifier = Modifier.weight(1f), color = Color(0xFFFCA5A5))
            KpiChip(label = "Marge Brute / Pack", value = "+79,38 € HT (64%)", modifier = Modifier.weight(1f), color = Color(0xFF34D399))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF1E293B))
                .border(1.dp, Color(0xFF334155), RoundedCornerShape(6.dp))
                .padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🏛️", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Bénéfice Stratégique de la SAS Multi-Activités :",
                    color = Color(0xFFFBBF24),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "L'Article 2 des Statuts de notre SAS (Objet Social) a été expressément rédigé pour inclure la vente de matériel de sécurité électronique en plus des kits solaires. Nos frais fixes (expert-comptable 83 €, Qonto 29 €, RC Pro 42 €) étant déjà payés par SOLARIS, chaque vente d'alarme (+79 €) tombe directement en dividende net distribué à 50/50 entre les associés.",
                color = Color(0xFFE2E8F0),
                fontSize = 7.sp,
                lineHeight = 10.sp
            )
        }
    }
}

@Composable
private fun ClientLeadsManagerCard(
    leads: List<ClientLeadItem>,
    onAddLead: (String, String, String, String, String) -> Unit,
    onUpdateStatus: (String, String) -> Unit,
    onDeleteLead: (String) -> Unit
) {
    val context = LocalContext.current
    var isAddingLead by remember { mutableStateOf(false) }

    var newName by remember { mutableStateOf("") }
    var newPhone by remember { mutableStateOf("") }
    var newCity by remember { mutableStateOf("") }
    var newPack by remember { mutableStateOf("Pack Solo 440W (349 €)") }
    var newNotes by remember { mutableStateOf("") }

    val totalPotentialCash = leads.sumOf { lead ->
        if (lead.packType.contains("880") || lead.packType.contains("Duo")) 649 else 349
    }
    val securedOrdersCount = leads.count { it.status == "Acompte reçu" || it.status == "Livré / Payé" }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF38BDF8).copy(alpha = 0.7f), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "📋", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "CARNET DE COMMANDES & PROSPECTS (LIVE CRM)",
                    color = Color(0xFF38BDF8),
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF38BDF8).copy(alpha = 0.2f))
                    .border(1.dp, Color(0xFF38BDF8), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "${leads.size} CONTACTS • $totalPotentialCash € POTENTIEL",
                    color = Color(0xFF38BDF8),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Gestion centralisée sur tablette des prospects chauds (Leboncoin, Marketplace & Site Web) pour les 2 associés :",
            color = Color(0xFF94A3B8),
            fontSize = 9.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Summary KPI Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF1E293B))
                    .padding(8.dp)
            ) {
                Column {
                    Text(text = "Commandes Sécurisées", color = Color(0xFF94A3B8), fontSize = 8.sp)
                    Text(
                        text = "$securedOrdersCount / 3 de l'objectif pilote",
                        color = if (securedOrdersCount >= 3) Color(0xFF34D399) else Color(0xFFFBBF24),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF1E293B))
                    .padding(8.dp)
            ) {
                Column {
                    Text(text = "Potentiel Chiffre d'Affaires", color = Color(0xFF94A3B8), fontSize = 8.sp)
                    Text(
                        text = "$totalPotentialCash € TTC",
                        color = Color(0xFF38BDF8),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Toggle add lead form button
        Button(
            onClick = { isAddingLead = !isAddingLead },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isAddingLead) Color(0xFF475569) else Color(0xFF0284C7)
            ),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(
                text = if (isAddingLead) "▲ Masquer le formulaire de saisie" else "+ Enregistrer un Nouveau Prospect / Commande",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (isAddingLead) {
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1E293B))
                    .border(1.dp, Color(0xFF475569), RoundedCornerShape(8.dp))
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Ajout Rapide d'un Contact Client :",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = newName,
                    onValueChange = { newName = it },
                    label = { Text("Nom et Prénom du client", fontSize = 9.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF38BDF8),
                        unfocusedBorderColor = Color(0xFF64748B)
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    OutlinedTextField(
                        value = newPhone,
                        onValueChange = { newPhone = it },
                        label = { Text("Téléphone (ex: 06 12 34 56 78)", fontSize = 9.sp) },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = Color(0xFF38BDF8),
                            unfocusedBorderColor = Color(0xFF64748B)
                        )
                    )
                    OutlinedTextField(
                        value = newCity,
                        onValueChange = { newCity = it },
                        label = { Text("Ville & Dépt (ex: Lyon 69)", fontSize = 9.sp) },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = Color(0xFF38BDF8),
                            unfocusedBorderColor = Color(0xFF64748B)
                        )
                    )
                }

                // Pack Choice
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val isSolo = newPack.contains("Solo")
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isSolo) Color(0xFF0284C7) else Color(0xFF334155))
                            .clickable { newPack = "Pack Solo 440W (349 €)" }
                            .padding(vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Pack Solo 440W (349 €)",
                            color = Color.White,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (!isSolo) Color(0xFF0284C7) else Color(0xFF334155))
                            .clickable { newPack = "Pack Duo 880W (649 €)" }
                            .padding(vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Pack Duo 880W (649 €)",
                            color = Color.White,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                OutlinedTextField(
                    value = newNotes,
                    onValueChange = { newNotes = it },
                    label = { Text("Détails / Besoins client (ex: pose sur abri, questions Enedis)", fontSize = 9.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF38BDF8),
                        unfocusedBorderColor = Color(0xFF64748B)
                    )
                )

                Button(
                    onClick = {
                        if (newName.isNotBlank() && newPhone.isNotBlank()) {
                            onAddLead(newName, newPhone, newCity, newPack, newNotes)
                            newName = ""
                            newPhone = ""
                            newCity = ""
                            newNotes = ""
                            isAddingLead = false
                            Toast.makeText(context, "Prospect enregistré avec succès !", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Veuillez renseigner au moins le nom et le téléphone", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "💾 Enregistrer le Prospect dans la Tablette",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Leads List
        if (leads.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Aucun prospect enregistré. Cliquez sur le bouton ci-dessus pour ajouter votre premier contact.",
                    color = Color(0xFF64748B),
                    fontSize = 9.sp
                )
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                leads.forEach { lead ->
                    val statusColor = when (lead.status) {
                        "Nouveau contact" -> Color(0xFF38BDF8)
                        "Devis / Proforma envoyé" -> Color(0xFFF59E0B)
                        "Acompte reçu" -> Color(0xFFA855F7)
                        "Livré / Payé" -> Color(0xFF10B981)
                        else -> Color(0xFF94A3B8)
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF1E293B))
                            .border(1.dp, statusColor.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = lead.clientName,
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "📍 ${if (lead.city.isBlank()) "Ville non précisée" else lead.city} • 📅 ${lead.dateCreated}",
                                    color = Color(0xFF94A3B8),
                                    fontSize = 8.sp
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(statusColor.copy(alpha = 0.2f))
                                    .border(1.dp, statusColor, RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = lead.status,
                                    color = statusColor,
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "📦 ${lead.packType}",
                                color = Color(0xFFFCD34D),
                                fontSize = 9.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "📞 ${lead.phone}",
                                color = Color(0xFFCBD5E1),
                                fontSize = 9.sp
                            )
                        }

                        if (lead.notes.isNotBlank()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "💬 Note : ${lead.notes}",
                                color = Color(0xFF94A3B8),
                                fontSize = 8.sp,
                                lineHeight = 11.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Action buttons row: Call, SMS, Cycle Status, Delete
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            // Call button
                            Button(
                                onClick = {
                                    val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${lead.phone}"))
                                    context.startActivity(dialIntent)
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0284C7)),
                                shape = RoundedCornerShape(4.dp),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 4.dp, horizontal = 4.dp)
                            ) {
                                Text(text = "📞 Appeler", fontSize = 8.sp, fontWeight = FontWeight.Bold)
                            }

                            // SMS button
                            Button(
                                onClick = {
                                    val smsIntent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:${lead.phone}")).apply {
                                        putExtra("sms_body", "Bonjour ${lead.clientName}, c'est SOLARIS France au sujet de votre projet de kit solaire plug & play. Avez-vous un moment pour échanger ?")
                                    }
                                    context.startActivity(smsIntent)
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF334155)),
                                shape = RoundedCornerShape(4.dp),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 4.dp, horizontal = 4.dp)
                            ) {
                                Text(text = "💬 SMS", fontSize = 8.sp, fontWeight = FontWeight.Bold)
                            }

                            // Cycle Status button
                            Button(
                                onClick = {
                                    val nextStatus = when (lead.status) {
                                        "Nouveau contact" -> "Devis / Proforma envoyé"
                                        "Devis / Proforma envoyé" -> "Acompte reçu"
                                        "Acompte reçu" -> "Livré / Payé"
                                        else -> "Nouveau contact"
                                    }
                                    onUpdateStatus(lead.id, nextStatus)
                                },
                                modifier = Modifier.weight(1.2f),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF475569)),
                                shape = RoundedCornerShape(4.dp),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 4.dp, horizontal = 4.dp)
                            ) {
                                Text(text = "🔄 Statut +", fontSize = 8.sp, fontWeight = FontWeight.Bold)
                            }

                            // Delete button
                            Button(
                                onClick = {
                                    onDeleteLead(lead.id)
                                    Toast.makeText(context, "Contact supprimé", Toast.LENGTH_SHORT).show()
                                },
                                modifier = Modifier.weight(0.7f),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC2626).copy(alpha = 0.8f)),
                                shape = RoundedCornerShape(4.dp),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 4.dp, horizontal = 4.dp)
                            ) {
                                Text(text = "🗑️", fontSize = 8.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}