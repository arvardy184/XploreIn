package com.arvan.xplorein.ui.presentation.sign_in

import android.annotation.SuppressLint
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
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
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@SuppressLint("SuspiciousIndentation")
@Composable
fun SignInScreen(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    handleSignIn: () -> Unit,
    viewModel: SignInViewModel = SignInViewModel()// Add a callback for sign up click
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current

    if (state.isLoading) {
        Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
    }



    LaunchedEffect(key1 = state.isSignInSuccessful) {
        if (state.isSignInSuccessful) {
            val isUserExist = checkuserExist(email.value)
                if (isUserExist) {
                    onSignInClick()
                }
         else{
                Toast.makeText(context, "User doesn't exist", Toast.LENGTH_LONG).show()
                }
            viewModel.resetState()

        }
    }



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
//                viewModel.updateState(isLoading = true) // Update loading state
                viewModel.signInWithEmailAndPassword(email.value, password.value)
                handleSignIn()
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
