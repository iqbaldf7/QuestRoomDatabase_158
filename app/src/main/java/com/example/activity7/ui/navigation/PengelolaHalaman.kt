package com.example.activity7.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Insert
import com.example.week9.ui.view.mahasiswa.DestinasiInsert
import com.example.week9.ui.view.mahasiswa.InsertMhsView
import kotlin.math.round

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(navController = NavController, startDestination = DestinasiInsert.route){
        composable(
            route = DestinasiInsert.route

        ){
            InsertMhsView(
                onBack = {}, onNavigate = {})

        }
    }
}