package com.kriya.biosys.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kriya.biosys.ui.screens.*
import com.kriya.biosys.viewmodel.*

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object About : Screen("about", "About", Icons.Default.Info)
    object Products : Screen("products", "Products", Icons.Default.ShoppingCart)
    object Manufacturing : Screen("manufacturing", "Factory", Icons.Default.Factory)
    object Technology : Screen("technology", "Technology", Icons.Default.Memory)
    object Team : Screen("team", "Team", Icons.Default.Groups)
    object CSR : Screen("csr", "CSR", Icons.Default.Event)
    object Gallery : Screen("gallery", "Gallery", Icons.Default.PhotoAlbum)
    object Documents : Screen("documents", "Documents", Icons.Default.Description)
    object Contact : Screen("contact", "Contact", Icons.Default.Call)
}

object Icons {
    object Default {
        val Home = androidx.compose.material.icons.Icons.Filled.Home
        val Info = androidx.compose.material.icons.Icons.Filled.Info
        val ShoppingCart = androidx.compose.material.icons.Icons.Filled.ShoppingCart
        val Factory = androidx.compose.material.icons.Icons.Filled.Factory
        val Memory = androidx.compose.material.icons.Icons.Filled.Memory
        val Groups = androidx.compose.material.icons.Icons.Filled.Groups
        val Event = androidx.compose.material.icons.Icons.Filled.Event
        val PhotoAlbum = androidx.compose.material.icons.Icons.Filled.PhotoAlbum
        val Description = androidx.compose.material.icons.Icons.Filled.Description
        val Call = androidx.compose.material.icons.Icons.Filled.Call
    }
}

@Composable
fun KriyaNavHost(navController: NavHostController = rememberNavController()) {
    val items = listOf(
        Screen.Home,
        Screen.About,
        Screen.Products,
        Screen.Manufacturing,
        Screen.Technology,
        Screen.Team,
        Screen.CSR,
        Screen.Gallery,
        Screen.Documents,
        Screen.Contact
    )

    androidx.compose.material3.Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(imageVector = screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(navController = navController, startDestination = Screen.Home.route, modifier = androidx.compose.ui.Modifier.padding(padding)) {
            composable(Screen.Home.route) {
                val vm: HomeViewModel = viewModel()
                HomeScreen(vm)
            }
            composable(Screen.About.route) { AboutScreen() }
            composable(Screen.Products.route) {
                val vm: ProductsViewModel = viewModel()
                ProductsScreen(vm)
            }
            composable(Screen.Manufacturing.route) { ManufacturingScreen() }
            composable(Screen.Technology.route) {
                val vm: TechnologyViewModel = viewModel()
                TechnologyScreen(vm)
            }
            composable(Screen.Team.route) {
                val vm: TeamViewModel = viewModel()
                TeamScreen(vm)
            }
            composable(Screen.CSR.route) {
                val vm: CSRViewModel = viewModel()
                CSRScreen(vm)
            }
            composable(Screen.Gallery.route) {
                val vm: GalleryViewModel = viewModel()
                GalleryScreen(vm)
            }
            composable(Screen.Documents.route) {
                val vm: DocumentsViewModel = viewModel()
                DocumentsScreen(vm)
            }
            composable(Screen.Contact.route) {
                val vm: ContactViewModel = viewModel()
                ContactScreen(vm)
            }
        }
    }
}
