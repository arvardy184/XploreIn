package com.arvan.xplorein

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arvan.xplorein.common.AppNavigation
import com.arvan.xplorein.ui.presentation.onboarding.OnboardingScreen
import com.arvan.xplorein.ui.presentation.profile.ProfileScreen
import com.google.android.gms.auth.api.identity.Identity
import com.arvan.xplorein.ui.presentation.sign_in.GoogleAuthUiClient
import com.arvan.xplorein.ui.presentation.sign_in.SignInScreen
import com.arvan.xplorein.ui.presentation.sign_in.SignInViewModel
import com.arvan.xplorein.ui.presentation.sign_up.SignUpScreen
import com.arvan.xplorein.ui.theme.XploreInTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        setContent {
            XploreInTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    AppNavigation(navController = navController,lifecycleScope = lifecycleScope,googleAuthUiClient = googleAuthUiClient,applicationContext = applicationContext)
                                 }
            }
        }
    }
}