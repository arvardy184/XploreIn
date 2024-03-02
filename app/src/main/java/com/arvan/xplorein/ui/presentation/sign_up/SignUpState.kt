package com.arvan.xplorein.ui.presentation.sign_up

data class SignUpState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val isLoading: Boolean = false
)