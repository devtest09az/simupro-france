package com.solaris.manager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.SolarPower
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.solaris.manager.conception.ConceptionViewModel
import com.solaris.manager.conception.ui.ConceptionScreen
import com.solaris.manager.production.ProductionViewModel
import com.solaris.manager.production.ui.ProductionScreen

enum class AppPillar(val title: String, val subtitle: String) {
    CONCEPTION("Conception R&D", "Bureau d'Études & Sourcing"),
    PRODUCTION("Production", "Atelier & Contrôle Qualité")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolarisApp(
    conceptionViewModel: ConceptionViewModel = viewModel(),
    productionViewModel: ProductionViewModel = viewModel()
) {
    var selectedPillar by remember { mutableStateOf(AppPillar.CONCEPTION) }
    val conceptionUiState by conceptionViewModel.uiState.collectAsState()
    val productionUiState by productionViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFF59E0B)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.SolarPower,
                                contentDescription = "Logo",
                                tint = Color(0xFF0F172A),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(horizontalAlignment = Alignment.Start) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "SOLARIS",
                                    color = Color.White,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Black,
                                    letterSpacing = 1.sp
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "FRANCE",
                                    color = Color(0xFFF59E0B),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = "Station Solaire 440W Plug & Play",
                                color = Color(0xFF94A3B8),
                                fontSize = 10.sp
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF0F172A)
                ),
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                if (selectedPillar == AppPillar.CONCEPTION) Color(0xFF0369A1) else Color(0xFF065F46)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = if (selectedPillar == AppPillar.CONCEPTION) "R&D" else "ATELIER",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF0F172A),
                contentColor = Color.White,
                modifier = Modifier.border(1.dp, Color(0xFF1E293B))
            ) {
                NavigationBarItem(
                    selected = selectedPillar == AppPillar.CONCEPTION,
                    onClick = { selectedPillar = AppPillar.CONCEPTION },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Build,
                            contentDescription = "Conception"
                        )
                    },
                    label = {
                        Text(
                            text = AppPillar.CONCEPTION.title,
                            fontWeight = if (selectedPillar == AppPillar.CONCEPTION) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 11.sp
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF38BDF8),
                        selectedTextColor = Color(0xFF38BDF8),
                        unselectedIconColor = Color(0xFF64748B),
                        unselectedTextColor = Color(0xFF64748B),
                        indicatorColor = Color(0xFF0369A1).copy(alpha = 0.3f)
                    )
                )

                NavigationBarItem(
                    selected = selectedPillar == AppPillar.PRODUCTION,
                    onClick = { selectedPillar = AppPillar.PRODUCTION },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Handyman,
                            contentDescription = "Production"
                        )
                    },
                    label = {
                        Text(
                            text = AppPillar.PRODUCTION.title,
                            fontWeight = if (selectedPillar == AppPillar.PRODUCTION) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 11.sp
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF34D399),
                        selectedTextColor = Color(0xFF34D399),
                        unselectedIconColor = Color(0xFF64748B),
                        unselectedTextColor = Color(0xFF64748B),
                        indicatorColor = Color(0xFF047857).copy(alpha = 0.3f)
                    )
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedPillar) {
                AppPillar.CONCEPTION -> ConceptionScreen(
                    viewModel = conceptionViewModel,
                    uiState = conceptionUiState
                )
                AppPillar.PRODUCTION -> ProductionScreen(
                    viewModel = productionViewModel,
                    uiState = productionUiState
                )
            }
        }
    }
}
