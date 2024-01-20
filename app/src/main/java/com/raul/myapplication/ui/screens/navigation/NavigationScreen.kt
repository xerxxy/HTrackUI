package com.raul.myapplication.ui.screens.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raul.myapplication.R
import com.raul.myapplication.data.local.NavigationDataSource
import com.raul.myapplication.data.remote.model.User
import com.raul.myapplication.ui.screens.assesments.AssessmentsScreen
import com.raul.myapplication.ui.screens.biometrics.ChartScreen
import com.raul.myapplication.ui.screens.emergency_contact.EmergencyContactScreen
import com.raul.myapplication.ui.screens.medical_record.MedicalRecordScreen
import com.raul.myapplication.ui.screens.medication.MedicationScreen
import com.raul.myapplication.ui.screens.settings.SettingsScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen(
    navController: NavHostController = rememberNavController(),
    user: User
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        //MAIN NAVIGATION COMPONENT
        // HERE WE NAVIGATE THROUGH SCREENS

        //LogInScreen()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        //coroutine for opening and closing drawer state
        val scope = rememberCoroutineScope()
        //this is to check which items from the ModalDrawerSheet are clicked and highlight them
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }

        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Box(
                        Modifier
                            .padding(start = 16.dp)
                            .height(158.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 24.dp)
                                    .size(116.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.Black, CircleShape)
                            ) {

                                Image(
                                    painter = painterResource(id = R.drawable.no_profile),
                                    contentDescription = "No profile pciture"
                                )

                            }

                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .padding(start = 20.dp)
                            ) {
                                Text(
                                    text = "Hello, ",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                )
                                Text(
                                    text = "${user.firstName}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 32.sp
                                )
                                Text(
                                    text = "${user.lastName}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 32.sp
                                )
                            }

                        }
                    }

                    NavigationDataSource.navigationItems.forEachIndexed { index, item ->
                        if (index != NavigationDataSource.navigationItems.size - 1) {
                            Spacer(modifier = Modifier.height(16.dp))
                            NavigationDrawerItem(
                                label = {
                                    Text(text = item.title)
                                },
                                selected = index == selectedItemIndex,
                                onClick = {
                                    selectedItemIndex = index
                                    navController.navigate(item.route)
                                    scope.launch {
                                        drawerState.close()
                                    }
                                },
                                icon = {

                                    Icon(
                                        painter = painterResource(id = item.selectedIcon),
                                        contentDescription = item.title
                                    )
                                },
                                badge = {
                                    if (item.badgeCount != null)
                                        Text(text = item.badgeCount.toString())
                                },
                                modifier = Modifier
                                    .padding(NavigationDrawerItemDefaults.ItemPadding)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(216.dp))
                    NavigationDrawerItem(
                        label = {
                            Text(text = NavigationDataSource.navigationItems[5].title)
                        },
                        selected = 5 == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = 5
                            navController.navigate(NavigationDataSource.navigationItems[5].route)
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = NavigationDataSource.navigationItems[5].selectedIcon),
                                contentDescription = NavigationDataSource.navigationItems[5].title,
                                tint = Color.Red
                            )
                        },
                        badge = {
                            if (NavigationDataSource.navigationItems[5].badgeCount != null)
                                Text(text = NavigationDataSource.navigationItems[5].badgeCount.toString())
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            },
            drawerState = drawerState
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.icon_htrack),
                                    contentDescription = "app icon"
                                )
                                Text(text = "HTrack")
                            }

                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                        }
                    )
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = NavigationDataSource.navigationItems[0].route,
                    modifier = Modifier
                        .padding(innerPadding)
                ) {
                    composable(route = NavigationDataSource.navigationItems[0].route) {
                        ChartScreen(user = user)
                    }

                    composable(route = NavigationDataSource.navigationItems[1].route) {
                        MedicalRecordScreen(user = user)
                    }

                    composable(route = NavigationDataSource.navigationItems[2].route) {
                        MedicationScreen(user = user)
                    }

                    composable(route = NavigationDataSource.navigationItems[3].route) {
                        AssessmentsScreen(user = user)
                    }
                    composable(route = NavigationDataSource.navigationItems[4].route) {
                        SettingsScreen(user = user)
                    }
                    composable(route = NavigationDataSource.navigationItems[5].route) {
                        EmergencyContactScreen(user = user)
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewNavigationScreen() {
    NavigationScreen(
        user = User(
            232,
            "faf",
            "fs",
            "fsf",
            "Raul",
            "male",
            "Spatariu",
            "ffdsf",
            "fdsfsdf",
            "fdsfsd"
        )
    )
}