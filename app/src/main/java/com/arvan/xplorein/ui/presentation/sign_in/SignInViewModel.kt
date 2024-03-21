package com.arvan.xplorein.ui.presentation.sign_in

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class SignInViewModel: ViewModel() {
    private val auth = Firebase.auth

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()
    val userData: MutableState<UserData?> = mutableStateOf(null)
    val signInError = MutableStateFlow<String?>(null)

    fun OnSignInResult(result: SignInResult){
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }




    fun resetState(){
        _state.update { SignInState() }
    }
    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                Log.d("SignInViewModel", "Sign in attempt with email: $email") // Add log at the beginning
                if(checkuserExist(email = email)){
                    Log.d("SignInViewModel", "Sign in successful with email: $email")
                    authWithEmailAndPassword(email = email, password = password)

                }else{
                    signInError.value = "Login gagal, informasi user tidak ditemukan"
                }
            } catch (e: Exception) {
                Log.e("SignInViewModel", "Sign in error", e) // Log exception
                signInError.value = e.message
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    private suspend fun authWithEmailAndPassword(email:String,password:String){
        try {
            Log.d("SignInViewModel", "Authenticating with email: $email") // Add log
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
            Log.d("Data User", user?.uid.toString())
            if (user != null) {
//                createUserProfileIfNotExists(user)
//                userData.value = getUserData(user)
                _state.update { it.copy(isSignInSuccessful = true) }
                Log.d("state di sign", "isSignInSuccessful: ${_state.value}")
                Log.d("SignInViewModel", "Authentication successful with email: $email")
            } else {
                signInError.value = "Login gagal, informasi user tidak ditemukan"
            }
        } catch (e: Exception) {
            Log.e("SignInViewModel", "Authentication error", e) // Log exception
            if (e is CancellationException) throw e
            signInError.value = e.message
        }
    }

}

suspend fun checkuserExist(email: String?): Boolean {
    if(email == null) return false

    val result = FirebaseFirestore.getInstance()
        .collection("users")
        .whereEqualTo("email", email)
        .get()
        .await()
    return result.documents.isNotEmpty()
}

//private suspend fun createUserProfileIfNotExists(user: FirebaseUser) {
//    val userDocRef = FirebaseFirestore.getInstance().collection("users").document(user.uid)
//    userDocRef.get().addOnCompleteListener { task ->
//        if (task.isSuccessful && !task.result.exists()) {
//            val userProfile = hashMapOf(
//                "userId" to user.uid,
//                "username" to user.displayName,
//                "email" to user.email,
//                "profilePic" to user.photoUrl?.toString()
//            )
//            userDocRef.set(userProfile).addOnCompleteListener {
//                if (it.isSuccessful) {
//                    Log.d("TAG", "User profile created successfully")
//                } else {
//                    Log.e("TAG", "Failed to create user profile: ${it.exception}")
//                }
//            }
//        }
//    }
//}
//
//private fun getUserData(user: FirebaseUser): UserData {
//    val docRef = FirebaseFirestore.getInstance().collection("users").document(user.uid)
//    docRef.get().addOnSuccessListener { document ->
//        val userId = document.getString("userId")
//        val username = document.getString("username")
//        val email = document.getString("email")
//        val profilePic = document.getString("profilePic")
//       UserData(
//            userId = userId ?: "",
//            username = username ?: "",
//            email = email ?: "",
//            profilePic = profilePic ?: ""
//        )
//    }
//
//
//   return UserData(
//        userId = user.uid,
//        username = user.displayName ?: "",
//        email = user.email ?: "",
//        profilePic = user.photoUrl?.toString() ?: ""
//    )
//}



