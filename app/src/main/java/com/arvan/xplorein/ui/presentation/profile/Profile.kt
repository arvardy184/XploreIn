package com.arvan.xplorein.ui.presentation.profile

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.arvan.xplorein.ui.presentation.sign_in.GoogleAuthUiClient
import kotlinx.coroutines.launch

@Composable
fun ProfileContent(navController: NavController, lifecycleScope: LifecycleCoroutineScope,
                   googleAuthUiClient: GoogleAuthUiClient, applicationContext: Context) {ProfileScreen(
    userData = googleAuthUiClient.getSignedInUser(),
    onSignOutClick = {
        lifecycleScope.launch {
            googleAuthUiClient.signOut()
            Toast.makeText(
                applicationContext,
                "Signed out",
                Toast.LENGTH_LONG
            ).show()
            navController.popBackStack()
        }
    }
)
}