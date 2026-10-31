package com.solaris.manager.production.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solaris.manager.production.ProductionSubTab
import com.solaris.manager.production.ProductionUiState
import com.solaris.manager.production.ProductionViewModel
import com.solaris.manager.production.model.AssemblyStep
import com.solaris.manager.production.model.ManufacturedStation
import com.solaris.manager.production.model.QualityCheckItem

@Composable
fun ProductionScreen(
    viewModel: ProductionViewModel,
    uiState: ProductionUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0B132B)) // Dark Industrial Navy
    ) {
        // En-tête Production / Atelier
        ProductionHeader()

        // Sous-onglets de navigation Production
        ScrollableTabRow(
            selectedTabIndex = uiState.selectedProductionTab.ordinal,
            containerColor = Color(0xFF1C2541),
            contentColor = Color(0xFF10B981),
            edgePadding = 12.dp
        ) {
            ProductionSubTab.values().forEach { tab ->
                Tab(
                    selected = uiState.selectedProductionTab == tab,
                    onClick = { viewModel.selectTab(tab) },
                    text = {
                        Text(
                            text = tab.title,
                            fontWeight = if (uiState.selectedProductionTab == tab) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp
                        )
                    }
                )
            }
        }

        // Message de succès temporaire
        uiState.registrationSuccessMessage?.let { msg ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF065F46))
                    .clickable { viewModel.clearSuccessMessage() }
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Success", tint = Color(0xFF34D399))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = msg, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Contenu dynamique selon l'onglet
        when (uiState.selectedProductionTab) {
            ProductionSubTab.ASSEMBLY -> AssemblyTabContent(
                steps = uiState.assemblySteps,
                onStepToggle = { viewModel.toggleStep(it) }
            )
            ProductionSubTab.QUALITY_QC -> QualityTabContent(
                checks = uiState.qualityChecks,
                onQcToggle = { viewModel.toggleQc(it) }
            )
            ProductionSubTab.REGISTRY -> RegistryTabContent(
                uiState = uiState,
                onPanelSerialChange = { viewModel.setPanelSerial(it) },
                onInverterSerialChange = { viewModel.setInverterSerial(it) },
                onTechnicianChange = { viewModel.setTechnician(it) },
                onSubmit = { viewModel.registerNewStation() }
            )
            ProductionSubTab.EXPEDITION -> ExpeditionTabContent(
                uiState = uiState
            )
        }
    }
}

@Composable
private fun ProductionHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF059669)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Handyman,
                        contentDescription = "Production",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "PRODUCTION & ATELIER",
                        color = Color(0xFF34D399),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Ligne d'Assemblage & Contrôle",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF047857))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "LIGNE ACTIVE",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Gestion des gammes opératoires (SOP), contrôles de découplage, appairage des numéros de série et colisage fret.",
            color = Color(0xFF94A3B8),
            fontSize = 12.sp
        )
    }
}

@Composable
private fun AssemblyTabContent(
    steps: List<AssemblyStep>,
    onStepToggle: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(steps) { step ->
            AssemblyStepCard(step = step, onToggle = { onStepToggle(step.stepNumber) })
        }
    }
}

@Composable
private fun AssemblyStepCard(
    step: AssemblyStep,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = if (step.isCompleted) Color(0xFF10B981) else Color(0xFF334155),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onToggle() },
        colors = CardDefaults.cardColors(
            containerColor = if (step.isCompleted) Color(0xFF064E3B).copy(alpha = 0.5f) else Color(0xFF1E293B)
        )
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(if (step.isCompleted) Color(0xFF10B981) else Color(0xFF334155)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${step.stepNumber}",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = step.title,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Checkbox(
                    checked = step.isCompleted,
                    onCheckedChange = { onToggle() },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF10B981),
                        uncheckedColor = Color(0xFF64748B)
                    )
                )
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = step.instruction,
                color = Color(0xFFCBD5E1),
                fontSize = 12.sp,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Handyman,
                        contentDescription = "Outil",
                        tint = Color(0xFF38BDF8),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = step.toolRequired,
                        color = Color(0xFF7DD3FC),
                        fontSize = 11.sp
                    )
                }

                step.torqueNm?.let { torque ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Speed,
                            contentDescription = "Couple",
                            tint = Color(0xFFFBBF24),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Couple : $torque Nm",
                            color = Color(0xFFFDE68A),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            step.safetyWarning?.let { warning ->
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF451A03))
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Alerte Sécurité",
                        tint = Color(0xFFFBBF24),
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = warning,
                        color = Color(0xFFFDE68A),
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun QualityTabContent(
    checks: List<QualityCheckItem>,
    onQcToggle: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF064E3B))
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.VerifiedUser,
                        contentDescription = "Certifié",
                        tint = Color(0xFF34D399),
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "PROTOCOLE QUALITÉ USINE (QC)",
                            color = Color(0xFF6EE7B7),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Tous les tests sont indispensables pour délivrer l'attestation Enedis CACSI.",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        items(checks) { check ->
            QualityCheckCard(check = check, onToggle = { onQcToggle(check.id) })
        }
    }
}

@Composable
private fun QualityCheckCard(
    check: QualityCheckItem,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = if (check.isPassed) Color(0xFF10B981) else Color(0xFFEF4444),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onToggle() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = check.title,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                Checkbox(
                    checked = check.isPassed,
                    onCheckedChange = { onToggle() },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF10B981),
                        uncheckedColor = Color(0xFFEF4444)
                    )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Procédure : ${check.testProcedure}",
                color = Color(0xFF94A3B8),
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF0F172A))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Valeur requise : ${check.expectedValue}",
                    color = if (check.isPassed) Color(0xFF34D399) else Color(0xFFF87171),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun RegistryTabContent(
    uiState: ProductionUiState,
    onPanelSerialChange: (String) -> Unit,
    onInverterSerialChange: (String) -> Unit,
    onTechnicianChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            // Formulaire d'enregistrement
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFF0284C7), RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.QrCode, contentDescription = "Scan", tint = Color(0xFF38BDF8))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "APPAIRAGE & SÉRIALISATION NOUVELLE STATION",
                            color = Color(0xFF38BDF8),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = uiState.inputPanelSerial,
                        onValueChange = onPanelSerialChange,
                        label = { Text("N° Série Panneau (ex: TRN-9331-440-XXXX)") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF38BDF8),
                            unfocusedBorderColor = Color(0xFF475569)
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = uiState.inputInverterSerial,
                        onValueChange = onInverterSerialChange,
                        label = { Text("N° Série Micro-Onduleur (ex: HYM-8424-HMS-XXXX)") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF38BDF8),
                            unfocusedBorderColor = Color(0xFF475569)
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onSubmit,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981))
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Valider l'Appairage & Générer Attestation Enedis",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = "Stations Assemblées & Prêtes (${uiState.manufacturedStations.size}) :",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        items(uiState.manufacturedStations) { station ->
            StationRegistryCard(station = station)
        }
    }
}

@Composable
private fun StationRegistryCard(station: ManufacturedStation) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = station.stationSerial,
                    color = Color(0xFFFBBF24),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFF047857))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "QC VALIDÉ",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Panneau : ${station.panelSerial}", color = Color(0xFF94A3B8), fontSize = 11.sp)
            Text(text = "Onduleur : ${station.inverterSerial}", color = Color(0xFF94A3B8), fontSize = 11.sp)
            Text(text = "Date : ${station.assemblyDate} • Opérateur : ${station.technicianName}", color = Color(0xFF64748B), fontSize = 11.sp)

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Description, contentDescription = "Enedis", tint = Color(0xFF38BDF8), modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Attestation CACSI prête", color = Color(0xFF38BDF8), fontSize = 11.sp)
                }
                Text(text = station.shippingStatus, color = Color(0xFF34D399), fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun ExpeditionTabContent(
    uiState: ProductionUiState
) {
    val manifest = uiState.packagingManifest

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            // Spécifications de Colisage
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFF10B981), RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.LocalShipping, contentDescription = "Fret", tint = Color(0xFF34D399))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "SPÉCIFICATIONS D'EXPÉDITION PALETTE GEODIS",
                            color = Color(0xFF34D399),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "Dimensions Colis :", color = Color(0xFF94A3B8), fontSize = 11.sp)
                            Text(text = manifest.dimensionsCm, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(text = "Poids Total Brut :", color = Color(0xFF94A3B8), fontSize = 11.sp)
                            Text(text = "${manifest.grossWeightKg} kg", color = Color(0xFFFBBF24), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(color = Color(0xFF334155))
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Éléments de calage & protection antichoc :", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    manifest.protectionItems.forEach { item ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 2.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = "OK", tint = Color(0xFF10B981), modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = item, color = Color(0xFFCBD5E1), fontSize = 11.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Transporteur agréé : ${manifest.approvedCarrier}",
                        color = Color(0xFF38BDF8),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        item {
            // Suivi des stocks atelier
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Inventory, contentDescription = "Stock", tint = Color(0xFFFBBF24))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "STOCKS COMPOSANTS ATELIER",
                            color = Color(0xFFFBBF24),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    uiState.workshopStock.forEach { comp ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = comp.name, color = Color.White, fontSize = 12.sp, modifier = Modifier.weight(1f))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(if (comp.stockQuantity < 10) Color(0xFF7F1D1D) else Color(0xFF064E3B))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "${comp.stockQuantity} en stock",
                                    color = if (comp.stockQuantity < 10) Color(0xFFFCA5A5) else Color(0xFF6EE7B7),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Divider(color = Color(0xFF334155), modifier = Modifier.padding(vertical = 2.dp))
                    }
                }
            }
        }
    }
}
