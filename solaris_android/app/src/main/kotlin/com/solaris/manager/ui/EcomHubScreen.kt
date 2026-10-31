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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Shield
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
import com.solaris.manager.model.ConceptionDetail
import com.solaris.manager.model.EcomDossier
import com.solaris.manager.model.MarketStudyDetail
import com.solaris.manager.model.ProjectPhase
import com.solaris.manager.model.ToolkitItem
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
                    onDossierClick = { viewModel.selectDossier(it) },
                    onNoteChange = { dId, text -> viewModel.onNoteChange(dId, text) },
                    onSaveNote = { viewModel.saveNotes(it) }
                )
                ProjectPhase.PRODUCTION -> ProductionPhaseContent(
                    uiState = uiState,
                    onDossierClick = { viewModel.selectDossier(it) },
                    onActionToggle = { dId, aId -> viewModel.toggleAction(dId, aId) },
                    onNoteChange = { dId, text -> viewModel.onNoteChange(dId, text) },
                    onSaveNote = { viewModel.saveNotes(it) }
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
                "Pilotage stratégique sourcé : Solaire 420W, Alarmes 4G, SAS multi-marques & Bouclier ARE."
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
    onDossierClick: (String) -> Unit,
    onNoteChange: (String, String) -> Unit,
    onSaveNote: (String) -> Unit
) {
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

                            // 2. COMPOSANTS & SOURCINGS B2B RÉELS (100% VÉRIFIÉS)
                            Text(
                                text = "2. Composants & Sourcings B2B (Prix Grossistes) :",
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

                            // 3. BOÎTE À OUTILS (SCRIPTS & MODÈLES)
                            Text(
                                text = "3. Boîte à Outils & Modèles Prêts à Copier :",
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

                            // 4. JOURNAL DE BORD & PRISE DE NOTES COMMUNE
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
    onSaveNote: (String) -> Unit
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
                    )
                    .clickable { onDossierClick(dossier.id) },
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1C2541))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, Color(0xFF334155), RoundedCornerShape(8.dp))
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = tool.title,
                    color = Color(0xFFFBBF24),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
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
                .padding(8.dp)
        ) {
            Text(
                text = tool.contentToCopy.take(160) + if (tool.contentToCopy.length > 160) "..." else "",
                color = Color(0xFF94A3B8),
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
