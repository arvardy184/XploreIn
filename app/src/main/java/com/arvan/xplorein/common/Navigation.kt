package com.arvan.xplorein.common


import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arvan.xplorein.ui.presentation.onboarding.OnboardingScreen
import com.arvan.xplorein.ui.presentation.profile.ProfileContent
import com.arvan.xplorein.ui.presentation.sign_in.GoogleAuthUiClient
import com.arvan.xplorein.ui.presentation.sign_in.SignInContent
import com.arvan.xplorein.ui.presentation.sign_up.SignUpScreen

@Composable
fun AppNavigation(navController: NavHostController,
                  lifecycleScope: LifecycleCoroutineScope,
                  googleAuthUiClient: GoogleAuthUiClient,
                  applicationContext: Context) {
    NavHost(navController = navController, startDestination = "onboarding") {

        composable("onboarding") {
            OnboardingScreen(
                onSignInClick = {
                    navController.navigate("sign_up")
                }
            )
        }

        composable("sign_in") {
            SignInContent(navController = navController,googleAuthUiClient = googleAuthUiClient,lifecycleScope = lifecycleScope,applicationContext = applicationContext)
        }

        composable("profile") {
            ProfileContent(navController = navController,lifecycleScope = lifecycleScope,googleAuthUiClient = googleAuthUiClient,applicationContext = applicationContext)
        }

        composable("sign_up") {
            SignUpScreen(
                onClick = {
                    navController.navigate("sign_in")
                }
            )
        }
    }
}