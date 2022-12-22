package nikhil.bhople.skillshowcase.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import nikhil.bhople.skillshowcase.common.Constants
import nikhil.bhople.skillshowcase.presentation.coin_details.components.CoinDetailsScreen
import nikhil.bhople.skillshowcase.presentation.coin_list.components.CoinListScreen
import nikhil.bhople.skillshowcase.presentation.theme.MVVMWithComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMWithComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HandleNavigation()
                }
            }
        }
    }

    @Composable
    private fun HandleNavigation() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.CoinListScreen.route
        ) {
            composable(
                route = Screen.CoinListScreen.route
            ) {
                CoinListScreen(navController = navController)
            }
            composable(
                route = Screen.CoinDetailScreen.route + "/{${Constants.PARAMS_COIN_NAME}}"
            ) {
                CoinDetailsScreen()
            }
        }
    }
}
