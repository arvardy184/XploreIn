package com.arvan.xplorein.common


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arvan.xplorein.ui.presentation.NotificationScreen
import com.arvan.xplorein.ui.presentation.PaymentScreen
import com.arvan.xplorein.ui.presentation.wisata.WisataScreen
import com.arvan.xplorein.ui.presentation.booking.BookingScreen
import com.arvan.xplorein.ui.presentation.home.HomeScreen
import com.arvan.xplorein.ui.presentation.onboarding.OnboardingScreen
import com.arvan.xplorein.ui.presentation.payment.DetailBookingCard
import com.arvan.xplorein.ui.presentation.payment.PaymentSuccessScreen
import com.arvan.xplorein.ui.presentation.profile.ProfileContent
import com.arvan.xplorein.ui.presentation.sign_in.GoogleAuthUiClient
import com.arvan.xplorein.ui.presentation.sign_in.SignInContent
import com.arvan.xplorein.ui.presentation.sign_up.SignUpScreen
import com.arvan.xplorein.ui.presentation.wisata.DetailWisataScreen

@Composable
fun AppNavigation(navController: NavHostController,
                  lifecycleScope: LifecycleCoroutineScope,
                  googleAuthUiClient: GoogleAuthUiClient,
                  applicationContext: Context,
                  isBottomBar: MutableState<Boolean>) {

    NavHost(navController = navController, startDestination = "home") {

        composable("payment_success"){
            isBottomBar.value = false
            PaymentSuccessScreen(navController = navController)
        }
        composable("notification"){
            isBottomBar.value = false
            NotificationScreen(navController = navController)
        }

        composable("onboarding") {
            OnboardingScreen(
                onSignInClick = {
                    navController.navigate("sign_up")
                }
            )
        }

        composable("sign_in") {
            SignInContent(navController = navController,googleAuthUiClient = googleAuthUiClient,lifecycleScope = lifecycleScope,applicationContext = applicationContext)
        }


        composable(BottomNavItem.Profile.route) {
            isBottomBar.value = true
            ProfileContent(navController = navController,lifecycleScope = lifecycleScope,googleAuthUiClient = googleAuthUiClient,applicationContext = applicationContext)
        }

        composable("sign_up") {
            SignUpScreen(
                onSignInClick = {
                    navController.navigate("sign_in")
                },
                onClick = {
                    navController.navigate("sign_in")
                }
            )
        }

        composable(BottomNavItem.Home.route) {
            isBottomBar.value = true // Tampilkan bottom bar di layar home
            HomeScreen(navController = navController, modifier = Modifier)
        }

        composable (BottomNavItem.Booking.route){
            // TODO
            isBottomBar.value = true
            BookingScreen(navController = navController)
        }
        
        composable("wisata"){
            isBottomBar.value = false
            WisataScreen(navController = navController)
        }

        composable("payment"){
            PaymentScreen(navController = navController)
        }

        composable("detail_wisata"){
            isBottomBar.value = false
            DetailWisataScreen(navController = navController)
        }
        
        composable("detail_booking"){
            DetailBookingCard(navController = navController)
        }

    }

}