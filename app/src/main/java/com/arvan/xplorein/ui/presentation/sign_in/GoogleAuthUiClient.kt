package com.arvan.xplorein.ui.presentation.sign_in

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import com.arvan.xplorein.R
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {
    private val auth = Firebase.auth

    suspend fun signIn(): IntentSender? {
        val result = try{
            oneTapClient.beginSignIn(buildSignInRequest()).await()
        } catch (e: Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredential).await().user
            println("user: $user")
            if (user != null){
                val userProfile = hashMapOf(
                    "userId" to user.uid,
                    "username" to user.displayName,
                    "email" to user.email,
                    "profilePic" to user.photoUrl?.toString()
                )
                FirebaseFirestore.getInstance().collection("users").document(user.uid).set(userProfile)
                    .addOnCompleteListener {
                        Log.d("GoogleAuthUiClient", "User profile created successfully")
                    }
                    .addOnFailureListener {
                        Log.e(  "GoogleAuthUiClient", "Failed to create user profile: $it")
                    }
               return  SignInResult(
                    data = user.run {   UserData(
                        userId = uid,
                        username = displayName,
                        email = email,
                        profilePic = photoUrl?.toString()
                    )

                    },
                    errorMessage = null
                )
            } else{
                return SignInResult(
                    data = null,
                    errorMessage = "Failed to sign in, User data not found"
                )
            }

        } catch (e: Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
           SignInResult(
               data = null,
               errorMessage = e.message
           )

        }
    }

    suspend fun signOut(){
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
        }
    }




    fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            email = email,
            profilePic = photoUrl?.toString()
        )
    }


    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
            .setSupported(true)
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.getString(R.string.web_client_id))
            .build()
        )
        .setAutoSelectEnabled(true)
            .build()
    }
}