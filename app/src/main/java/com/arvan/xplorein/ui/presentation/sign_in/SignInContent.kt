package com.arvan.xplorein.ui.presentation.sign_in

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun SignInContent(navController: NavController, googleAuthUiClient: GoogleAuthUiClient, lifecycleScope: LifecycleCoroutineScope, applicationContext: Context) {
    val viewModel = viewModel<SignInViewModel>()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if(result.resultCode == ComponentActivity.RESULT_OK) {
                lifecycleScope.launch {
                    val signInResult = googleAuthUiClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    viewModel.OnSignInResult(signInResult)
                }
            }
        }
    )


    SignInScreen(
        onSignInClick = {
            lifecycleScope.launch {
                val signInIntentSender = googleAuthUiClient.signIn()
                launcher.launch(IntentSenderRequest.Builder(signInIntentSender ?: return@launch).build())
                navController.navigate("home")
            }

              },
        onSignUpClick = {
            navController.navigate("sign_up")
        },
        handleSignIn = {
                    Toast.makeText(
                        applicationContext,
                        "Sign in successful",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate("home")
                    viewModel.resetState()



        }






    )
}