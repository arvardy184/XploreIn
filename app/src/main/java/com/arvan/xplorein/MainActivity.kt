package com.arvan.xplorein

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arvan.xplorein.common.AppNavigation
import com.arvan.xplorein.common.BottomNavBar
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

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
                    val isLoggedIn = remember { mutableStateOf(false) }

                    // Observe login status
                    LaunchedEffect(Unit) {
                        isLoggedIn.value = googleAuthUiClient.getSignedInUser() != null
                    }
                    // Scaffold content goes here
                    Scaffold(
                        bottomBar = {
                            BottomNavBar(
                                navController = navController,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        },
                    ) {
                        AppNavigation(
                            navController = navController,
                            lifecycleScope = lifecycleScope,
                            googleAuthUiClient = googleAuthUiClient,
                            applicationContext = applicationContext
                        )

                    }
                }
            }
        }
    }
}
