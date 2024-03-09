package com.arvan.xplorein.ui.presentation.sign_up

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arvan.xplorein.R
import com.arvan.xplorein.ui.component.AuthButtonComponent
import com.arvan.xplorein.ui.component.ClickableAuthTextComponent
import com.arvan.xplorein.ui.component.MyTextField

import com.arvan.xplorein.ui.component.TitleTextComponent
import com.arvan.xplorein.ui.theme.green
import com.arvan.xplorein.ui.theme.red
import com.arvan.xplorein.ui.theme.yellow
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun SignUpScreen(
    onSignInClick: () -> Unit,
    onClick: () -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val fullName = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }

    Surface(
        color = yellow,
        modifier = Modifier.fillMaxSize()
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
            MyTextField(
                labelValue = "Email/Phone Number",
                textValue = email.value,
                onValueChanged = {
                    newValue ->
                    email.value = newValue

                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            MyTextField(labelValue = "Full Name", textValue = fullName.value, onValueChanged = {
                newValue ->
                fullName.value = newValue
            })
            Spacer(modifier = Modifier.height(10.dp))
            MyTextField(labelValue = "Username", textValue = username.value, onValueChanged = {
                newValue ->
                username.value = newValue
            }, )
            Spacer(modifier = Modifier.height(10.dp))
            MyTextField(labelValue = "Password", textValue = password.value, onValueChanged = {
                newValue ->
                password.value = newValue
            }, isPassword = true)
            Spacer(modifier = Modifier.height(10.dp))
            AuthButtonComponent(value = "Sign Up") {

                signUpWithEmailPassword(
                    email = email.value,
                    password = password.value,
                    fullName = fullName.value,
                    username = username.value,
                    onSuccess = {
                        // Handle sign-up success (if needed)
                        // For example, you can navigate to the next screen
                        onClick()
//                        Log.d("SignUpScreen", "User ID: ${user.uid}")
                        Log.d("SignUpScreen", "Email: ${email}")
                        Log.d("SignUpScreen", "Full Name: $fullName")
                        Log.d("SignUpScreen", "Username: $username")

                        Log.d("Test", "Sign Up clicked!")
                    },
                    onError = { errorMessage ->
                        // Handle sign-up error (show error message to the user, log, etc.)
                        // For example, you can display an error message
                        // myErrorMessage = errorMessage
                       Log.d("Test", errorMessage)
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            ClickableAuthTextComponent(
                onLoginSelected = {
                    // Null or navigate to the login screen
                               onSignInClick()
                },
                onSignUpSelected = {
                    onClick()
                }
            )
        }
    }
}

//private fun showToast(message: String) {
//    val context = LocalContext.current
//    val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
//    toast.show()
//}

private fun signUpWithEmailPassword(
    email: String,
    password: String,
    fullName: String,
    username: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val auth = Firebase.auth
    if (email.isNotEmpty() && password.isNotEmpty() && fullName.isNotEmpty() && username.isNotEmpty()) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        Log.d("SignUpScreen", "User ID: ${user.uid}")
                        Log.d("SignUpScreen", "Email: ${user.email}")
                        Log.d("SignUpScreen", "Full Name: $fullName")
                        Log.d("SignUpScreen", "Username: $username")
                    }
                    // Create a user profile in Firestore
                    val userProfile = hashMapOf(
                        "fullName" to fullName,
                        "username" to username,
                        "email" to email
                    )

                    val firestore = FirebaseFirestore.getInstance()
                    firestore.collection("users")
                        .document(user!!.uid)
                        .set(userProfile)
                        .addOnSuccessListener {
                            // Handle sign-up success
                            Log.d("SignUpScreen", "User profile created successfully")


                            // Additional log to check if the data is stored in Firestore
                            Log.d("SignUpScreen", "Data stored in Firestore: $userProfile")

                            onSuccess()
                        }
                        .addOnFailureListener { e ->
                            // Handle sign-up failure
                            onError("Failed to create user profile: ${e.message}")
                        }
                } else {
                    // Handle sign-up failure
                    onError("Failed to sign up: ${task.exception?.message}")
                }
            }
    } else {
        // Handle sign-up failure
        onError("Please fill in all fields ")
        onError("$username $email $password $fullName")
    }
}

