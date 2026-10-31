package com.solaris.manager.conception.ui

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
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.SolarPower
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solaris.manager.conception.ConceptionSubTab
import com.solaris.manager.conception.ConceptionUiState
import com.solaris.manager.conception.ConceptionViewModel
import com.solaris.manager.conception.model.GroundSurfaceAlbedo
import com.solaris.manager.conception.model.OrientationOption
import com.solaris.manager.model.SolarComponent
import java.util.Locale

@Composable
fun ConceptionScreen(
    viewModel: ConceptionViewModel,
    uiState: ConceptionUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A)) // Slate Navy background
    ) {
        // En-tête R&D / Bureau d'Études
        ConceptionHeader()

        // Sous-onglets de navigation Conception
        ScrollableTabRow(
            selectedTabIndex = uiState.selectedConceptionTab.ordinal,
            containerColor = Color(0xFF1E293B),
            contentColor = Color(0xFF38BDF8),
            edgePadding = 12.dp
        ) {
            ConceptionSubTab.values().forEach { tab ->
                Tab(
                    selected = uiState.selectedConceptionTab == tab,
                    onClick = { viewModel.selectTab(tab) },
                    text = {
                        Text(
                            text = tab.title,
                            fontWeight = if (uiState.selectedConceptionTab == tab) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp
                        )
                    }
                )
            }
        }

        // Contenu dynamique selon l'onglet
        when (uiState.selectedConceptionTab) {
            ConceptionSubTab.SPECS -> SpecsTabContent(
                components = uiState.components,
                onComponentClick = { viewModel.selectComponent(it) }
            )
            ConceptionSubTab.SIMULATOR -> SimulatorTabContent(
                uiState = uiState,
                onAngleChange = { viewModel.updateTiltAngle(it) },
                onOrientationChange = { viewModel.updateOrientation(it) },
                onGroundSurfaceChange = { viewModel.updateGroundSurface(it) }
            )
            ConceptionSubTab.BOM_AUDIT -> BomAuditTabContent(
                uiState = uiState
            )
        }
    }
}

@Composable
private fun ConceptionHeader() {
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
                        .background(Color(0xFF0284C7)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = "R&D",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "CONCEPTION & R&D",
                        color = Color(0xFF38BDF8),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Architecture Technique 420W",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF0369A1))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "BUREAU D'ÉTUDES",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Validation des normes DIN VDE V 0124-100, rendements biverre N-Type et nomenclature matière (BOM).",
            color = Color(0xFF94A3B8),
            fontSize = 12.sp
        )
    }
}

@Composable
private fun SpecsTabContent(
    components: List<SolarComponent>,
    onComponentClick: (SolarComponent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(components) { component ->
            ComponentItemCard(component = component, onClick = { onComponentClick(component) })
        }
    }
}

@Composable
private fun ComponentItemCard(
    component: SolarComponent,
    onClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFF334155), RoundedCornerShape(12.dp))
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = component.category.label.uppercase(Locale.FRANCE),
                        color = Color(0xFF38BDF8),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = component.name,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Réf : ${component.reference} • Marque : ${component.brand}",
                        color = Color(0xFF94A3B8),
                        fontSize = 12.sp
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "${String.format(Locale.FRANCE, "%.2f", component.unitPriceRetailTtc)} € TTC",
                        color = Color(0xFFFBBF24), // Amber gold
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${String.format(Locale.FRANCE, "%.2f", component.unitPriceWholesaleHt)} € HT B2B",
                        color = Color(0xFF34D399), // Emerald
                        fontSize = 11.sp
                    )
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Expand",
                        tint = Color(0xFF94A3B8),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = component.description,
                color = Color(0xFFCBD5E1),
                fontSize = 12.sp,
                lineHeight = 16.sp
            )

            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Divider(color = Color(0xFF334155))
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Spécifications Techniques R&D :",
                    color = Color(0xFF38BDF8),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))
                component.specifications.forEach { (key, value) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = key, color = Color(0xFF94A3B8), fontSize = 11.sp)
                        Text(text = value, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Certifications & Normes de sécurité :",
                    color = Color(0xFF38BDF8),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    component.certifications.forEach { cert ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFF0F172A))
                                .border(1.dp, Color(0xFF0284C7), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(text = cert, color = Color(0xFF7DD3FC), fontSize = 10.sp)
                        }
                    }
                }

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
                        contentDescription = "Safety",
                        tint = Color(0xFFFBBF24),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = component.safetyNotes,
                        color = Color(0xFFFDE68A),
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SimulatorTabContent(
    uiState: ConceptionUiState,
    onAngleChange: (Float) -> Unit,
    onOrientationChange: (OrientationOption) -> Unit,
    onGroundSurfaceChange: (GroundSurfaceAlbedo) -> Unit
) {
    var orientationExpanded by remember { mutableStateOf(false) }
    var albedoExpanded by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            // Résultats phares de la simulation
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, Color(0xFF0284C7), RoundedCornerShape(14.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF032644))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "RÉSULTATS DE SIMULATION SOLAIRE",
                            color = Color(0xFF38BDF8),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.Default.SolarPower,
                            contentDescription = "Solar",
                            tint = Color(0xFFFBBF24)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "${uiState.yieldResult.annualKwh} kWh",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Production / an",
                                color = Color(0xFF94A3B8),
                                fontSize = 11.sp
                            )
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "${String.format(Locale.FRANCE, "%.1f", uiState.yieldResult.annualSavingsEuros)} €",
                                color = Color(0xFF34D399),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Économies Linky / an",
                                color = Color(0xFF94A3B8),
                                fontSize = 11.sp
                            )
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "${String.format(Locale.FRANCE, "%.1f", uiState.yieldResult.paybackPeriodYears)} ans",
                                color = Color(0xFFFBBF24),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Amortissement ROI",
                                color = Color(0xFF94A3B8),
                                fontSize = 11.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(color = Color(0xFF0C4A6E))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Équivaut à ~${String.format(Locale.FRANCE, "%.2f", uiState.yieldResult.dailyKwhAverage)} kWh/jour effacés sur le talon de consommation (frigo, box internet, veille) et ${uiState.yieldResult.co2KgAvoidedAnnual} kg CO2 évités.",
                        color = Color(0xFFBAE6FD),
                        fontSize = 11.sp
                    )
                }
            }
        }

        item {
            // Curseur d'angle d'inclinaison
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Inclinaison du Châssis Ksolar :",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "${uiState.simulationParams.tiltAngleDegrees.toInt()}°",
                            color = Color(0xFF38BDF8),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Slider(
                        value = uiState.simulationParams.tiltAngleDegrees,
                        onValueChange = onAngleChange,
                        valueRange = 15f..45f,
                        steps = 5,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "15° (Toit plat / Été)", color = Color(0xFF64748B), fontSize = 10.sp)
                        Text(text = "30°-35° (Optimal annuel France)", color = Color(0xFF38BDF8), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        Text(text = "45° (Hiver)", color = Color(0xFF64748B), fontSize = 10.sp)
                    }
                }
            }
        }

        item {
            // Orientation Dropdown
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "Orientation du panneau :",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    ExposedDropdownMenuBox(
                        expanded = orientationExpanded,
                        onExpandedChange = { orientationExpanded = it }
                    ) {
                        OutlinedTextField(
                            value = uiState.simulationParams.orientation.label,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = orientationExpanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = orientationExpanded,
                            onDismissRequest = { orientationExpanded = false }
                        ) {
                            OrientationOption.values().forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option.label) },
                                    onClick = {
                                        onOrientationChange(option)
                                        orientationExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            // Type de sol (gain bifacial Trina Vertex S+ Biverre)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = "Bifacial",
                            tint = Color(0xFFFBBF24),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Effet d'Albédo Sol (Gain Bifacial Biverre) :",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))

                    ExposedDropdownMenuBox(
                        expanded = albedoExpanded,
                        onExpandedChange = { albedoExpanded = it }
                    ) {
                        OutlinedTextField(
                            value = uiState.simulationParams.groundSurface.label,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = albedoExpanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = albedoExpanded,
                            onDismissRequest = { albedoExpanded = false }
                        ) {
                            GroundSurfaceAlbedo.values().forEach { surface ->
                                DropdownMenuItem(
                                    text = { Text(surface.label) },
                                    onClick = {
                                        onGroundSurfaceChange(surface)
                                        albedoExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BomAuditTabContent(
    uiState: ConceptionUiState
) {
    val bom = uiState.bomAudit

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            // Synthèse R&D Marges
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, Color(0xFF10B981), RoundedCornerShape(14.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF064E3B))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "MARGE NETTE OPÉRATIONNELLE R&D",
                            color = Color(0xFF6EE7B7),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.Default.Savings,
                            contentDescription = "Marge",
                            tint = Color(0xFF34D399)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "+${String.format(Locale.FRANCE, "%.2f", bom.netMarginWholesale)} € NET",
                                color = Color.White,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "par station SOLARIS 420W vendue (en palette grossiste)",
                                color = Color(0xFFA7F3D0),
                                fontSize = 11.sp
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF047857))
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            val marginPercent = (bom.netMarginWholesale / bom.customerSellingPriceHt) * 100
                            Text(
                                text = "${String.format(Locale.FRANCE, "%.1f", marginPercent)} %",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        item {
            // Tableau Comparatif BOM : Détail vs Grossiste
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "Décomposition des Coûts R&D (BOM) :",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    uiState.components.forEach { comp ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = comp.name,
                                    color = Color(0xFFE2E8F0),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = comp.category.label,
                                    color = Color(0xFF64748B),
                                    fontSize = 10.sp
                                )
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "${String.format(Locale.FRANCE, "%.2f", comp.unitPriceWholesaleHt)} € HT (B2B)",
                                    color = Color(0xFF34D399),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${String.format(Locale.FRANCE, "%.2f", comp.unitPriceRetailTtc)} € TTC (Détail)",
                                    color = Color(0xFF94A3B8),
                                    fontSize = 10.sp
                                )
                            }
                        }
                        Divider(color = Color(0xFF334155), modifier = Modifier.padding(vertical = 2.dp))
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Fret palette Geodis unitaire :", color = Color(0xFFFBBF24), fontSize = 12.sp)
                        Text(text = "42.00 € HT", color = Color(0xFFFBBF24), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Prix de Vente Client TTC :", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        Text(text = "349.00 € TTC", color = Color(0xFF38BDF8), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
