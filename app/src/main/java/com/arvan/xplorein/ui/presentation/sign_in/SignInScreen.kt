package com.arvan.xplorein.ui.presentation.sign_in

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arvan.xplorein.R
import com.arvan.xplorein.ui.component.AuthButtonComponent
import com.arvan.xplorein.ui.component.ClickableAuthTextComponent
import com.arvan.xplorein.ui.component.DividerTextCompoent
import com.arvan.xplorein.ui.component.MyTextField
import com.arvan.xplorein.ui.component.SocialMediaRow
import com.arvan.xplorein.ui.component.TitleTextComponent
import com.arvan.xplorein.ui.theme.yellow
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit // Add a callback for sign up click
) {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }

    Surface(
        color = yellow,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            TitleTextComponent(value = stringResource(id = R.string.app_name))
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier
                .height(200.dp)
                .width(200.dp)) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.logo_signin),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            MyTextField(labelValue = "Email/Phone Number", textValue = email.value, onValueChanged = {newValue ->
                email.value = newValue}  )
            Spacer(modifier = Modifier.height(10.dp))
            MyTextField(
                labelValue = "Password",
                textValue = password.value,
                onValueChanged = { newValue ->
                    password.value = newValue
                },
                isPassword = true
            )

            Spacer(modifier = Modifier.height(10.dp))
            AuthButtonComponent(value = "Sign In") {
                signInWithEmailAndPassword(
                    email = email.value,
                    password = password.value,
                    onSuccess = {
                        // Handle sign-in success (if needed)
                        Log.d("SignInScreen", "Sign In successful!")
                        onSignInClick()
                    },
                    onError = { errorMessage ->
                        // Handle sign-in error (show error message to the user, log, etc.)
                        // For example, you can update a state variable to display the error
                        // myErrorMessage = errorMessage

                        // Log the error message
                        Log.e("SignInScreen", errorMessage)
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            ClickableAuthTextComponent(
                onLoginSelected = {
                    // Handle login click
                    onSignInClick()
                },
                onSignUpSelected = {
                    // Handle sign up click
                    onSignUpClick()
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            DividerTextCompoent()
            SocialMediaRow(
                {}, {
                    onSignInClick()
                }
            )
        }
    }
}

private fun signInWithEmailAndPassword(
    email: String,
    password: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val auth = Firebase.auth
    if (email.isNotEmpty() && password.isNotEmpty()) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Handle sign-in success
                    Log.d("SignInScreen", "Sign In successful!")
                    onSuccess()
                } else {
                    // Handle sign-in failure
                    onError("Failed to sign in: ${task.exception?.message}")
                }
            }
    } else {
        onError("Please fill in all fields")
    }
}
