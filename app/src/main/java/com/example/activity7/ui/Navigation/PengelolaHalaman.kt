package com.example.activity7.ui.navigation
import DestinationInsert
import InsertMhsView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.activity7.ui.Navigation.DestinasiDetail
import com.example.activity7.ui.Navigation.DestinasiHome
import com.example.activity7.ui.Navigation.DestinasiUpdate
import com.example.activity7.ui.view.mahasiswa.DetailMhsView
import com.example.activity7.ui.view.mahasiswa.HomeMhsView
import com.example.activity7.ui.view.mahasiswa.UpdateMhsView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    NavHost(navController = navController, startDestination = DestinasiHome.route) {

        // Home Screen
        composable(route = DestinasiHome.route) {
            HomeMhsView(
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                },
                onAddMhs = {
                    navController.navigate(DestinationInsert.route)
                },
                modifier = modifier
            )
        }


        // Insert Screen
        composable(route = DestinationInsert.route) {
            InsertMhsView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        // Detail Screen
        composable(
            route = DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.NIM) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val nim = backStackEntry.arguments?.getString(DestinasiDetail.NIM)
            nim?.let {
                DetailMhsView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdate.route}/$nim")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        // Update Screen
        composable(
            route = DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.NIM) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val nim = backStackEntry.arguments?.getString(DestinasiUpdate.NIM)
            nim?.let {
                UpdateMhsView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onNavigate = {
                        navController.popBackStack()
                    },
                    modifier = modifier
                )
            }
        }
    }
}
