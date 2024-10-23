package com.aditya.color_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aditya.color_app.MVVM.ColorViewModel
import com.aditya.color_app.Screens.AddColorScreen
import com.aditya.color_app.Screens.Home
import com.aditya.color_app.ui.theme.ColorAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel:ColorViewModel by viewModels()

        setContent {

            val navController=rememberNavController()


            ColorAppTheme {

                    NavHost(navController = navController, startDestination ="Home" ) {
                        composable("Home"){
                            Home(viewModel,navController)
                        }
                        composable("addColors"){
                            AddColorScreen(viewModel,navController)
                        }
                    }


                }

            }
        }
    }










