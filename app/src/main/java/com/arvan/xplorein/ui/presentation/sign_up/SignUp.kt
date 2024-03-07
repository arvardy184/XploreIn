//package com.arvan.xplorein.ui.presentation.sign_up
//
//import android.annotation.SuppressLint
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.navigation.compose.rememberNavController
//import com.arvan.xplorein.ui.presentation.profile.ProfileContent
//import com.arvan.xplorein.ui.presentation.profile.ProfileScreen
//
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.auth.User
//
//@Composable
//fun AuthOrMainScreen(auth: FirebaseAuth){
//    var user by remember{ mutableStateOf(auth.currentUser) }
//    if (user == null){
//        AuthScreen(
//            onSignedIn = {
//                signedInUser ->
//                user = signedInUser
//            }
//        )
//    } else{
//       rememberNavController().navigate("profile")
//    }
//}
//
//@Composable
//fun AuthScreen (onSignedIn: (FirebaseAuth) -> Unit) {
//    var email by remember { mutableStateOf("") }
//    var fullName by remember { mutableStateOf("") }
//    var username by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var isLoading by remember { mutableStateOf(false) }
//    var isSignIn by remember { mutableStateOf(true) }
//    var myErrorMessage by remember { mutableStateOf<String?>(null) }
//    val navController = rememberNavController()
// if(!isSignIn){
//    signIn(
//        email = email,
//        password = password,
//        onSignIn = {
//            signedInUser ->
//            onSignedIn(signedInUser)
//        },
//        onSignInError = {
//            myErrorMessage = it
//        }
//    )
// } else {
//    SignUp(
//        auth = FirebaseAuth.getInstance(),
//        email = email,
//        fullName = fullName,
//        username = username,
//        password = password,
//        onSignUp = {
//            signedUpUser ->
//            onSignedIn(signedUpUser)
//        },
//        onSignUpError = {
//            myErrorMessage = it
//        }
//    )
// }
//}
//
//private fun onSignInError(errorMessage: String){
//    println("onSignInError: $errorMessage")
//}
//
//@SuppressLint("RestrictedApi")
//@Composable
//fun MainScreen(user: FirebaseUser, onSignOut: () -> Unit) {
//   val userProfile = remember { mutableStateOf<UserData?>(null) }
//
//    LaunchedEffect(user.uid){
//        val firestore = FirebaseFirestore.getInstance()
//        val userDocRef = firestore.collection("users").document(user.uid)
//
//        userDocRef.get()
//            .addOnSuccessListener { document ->
//                if (document.exists()){
//                    val email = document.get("email") as String
//                    val fullName = document.get("fullName") as String
//                    val username = document.get("username") as String
//                    val profilePic = document.get("profilePic") as String
////                    userProfile.value = User(
////                        email = email,
////                        fullName = fullName,
////                        username = username,
////                        profilePic = profilePic
////                    )
//                    userProfile.value = DataUser(
//                        email = email,
//                        fullName = fullName,
//                        userName = username,
//                        profilePic = profilePic
//                    )
//                } else {
//                    println("No such document")
//                }
//            }
//            .addOnFailureListener {
//                e ->
//                println("Error getting document: $e")
//            }
//    }
//
//}
//
//private fun signIn(
//    auth: FirebaseAuth,
//    email: String,
//    password: String,
//    onSignIn: (FirebaseUser) -> Unit,
//    onSignInError: (String) -> Unit
//){
//    auth.signInWithEmailAndPassword(email,password)
//        .addOnCompleteListener { task ->
//            if (task.isSuccessful){
//                val user = auth.currentUser
//                onSignIn(user!!)
//            } else {
//                onSignInError(task.exception?.message ?: "Unknown error")
//            }
//        }
//}
//
//private fun SignUp(
//    auth: FirebaseAuth,
//    email: String,
//    fullName: String,
//    username: String,
//    password: String,
//    onSignUp: (FirebaseUser) -> Unit,
//    onSignUpError: (String) -> Unit
//){
//    auth.createUserWithEmailAndPassword(email,password)
//        .addOnCompleteListener { task ->
//            if (task.isSuccessful){
//                val user = auth.currentUser
//                val userProfile = hashSetOf(
//                    "email" to email,
//                    "fullName" to fullName,
//                    "username" to username
//                )
//                val userDocRef = FirebaseFirestore.getInstance()
//                    .collection("users")
//                    .document(user!!.uid)
//                    .set(userProfile)
//                    .addOnSuccessListener {
//                        onSignedIn(user)
//                    }
//                    .addOnFailureListener {
//                        e ->
//                        println("Error getting document: $e")
//                    }
//
//                onSignUp(user)
//            } else {
//                onSignUpError(task.exception?.message ?: "Unknown error")
//            }
//        }
//}