package com.arvan.xplorein.common


import android.content.Context
import android.util.Log
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
import com.arvan.xplorein.ui.presentation.partner.DetailPartnerScreen
import com.arvan.xplorein.ui.presentation.partner.PartnerScreen
import com.arvan.xplorein.ui.presentation.payment.DetailBookingCard
import com.arvan.xplorein.ui.presentation.payment.PaymentSuccessScreen
import com.arvan.xplorein.ui.presentation.profile.ProfileContent
import com.arvan.xplorein.ui.presentation.sign_in.GoogleAuthUiClient
import com.arvan.xplorein.ui.presentation.sign_in.SignInContent
import com.arvan.xplorein.ui.presentation.sign_up.SignUpScreen
import com.arvan.xplorein.ui.presentation.tour_guide.DetailTourGuideScreen
import com.arvan.xplorein.ui.presentation.tour_guide.TourGuideScreen
import com.arvan.xplorein.ui.presentation.wisata.DetailWisataScreen
import com.arvan.xplorein.ui.presentation.wishlist.WishlistScreen

@Composable
fun AppNavigation(navController: NavHostController,
                  lifecycleScope: LifecycleCoroutineScope,
                  googleAuthUiClient: GoogleAuthUiClient,
                  applicationContext: Context,
                  isBottomBar: MutableState<Boolean>) {

    NavHost(navController = navController, startDestination = "onboarding") {

        composable("payment_success"){
            isBottomBar.value = false
            PaymentSuccessScreen(navController = navController)
        }
        composable("notification"){
            isBottomBar.value = false
            NotificationScreen(navController = navController)
        }

        composable("onboarding") {
            isBottomBar.value = false
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

            isBottomBar.value = true
            BookingScreen(navController = navController)
        }

        composable("wisata/{cityId}") { backStackEntry ->
            val cityId = backStackEntry.arguments?.getString("cityId")
            if (cityId != null) {
                isBottomBar.value = false
                WisataScreen(navController = navController, cityId = cityId)
                Log.d("TAG", "AppNavigation: $cityId")
            } else {
                // Handle case when cityId is null
                Log.d("TAG", "AppNavigation: cityId is null")
            }
        }

        composable("payment"){
            PaymentScreen(navController = navController)
        }

        composable("{cityId}/detail_wisata/{wisataId}"){
            val wisataId = it.arguments?.getString("wisataId")
            val cityId = it.arguments?.getString("cityId")
            if (wisataId != null  && cityId != null)  {
                Log.d("TAG", "AppNavigation Detail: $wisataId $cityId")
                isBottomBar.value = false
                DetailWisataScreen(navController = navController, cityId =cityId ,wisataId = wisataId)
            }
             }
        
        composable("detail_booking"){
            DetailBookingCard(navController = navController)
        }

        composable("tour_guide"){
            isBottomBar.value = false
            TourGuideScreen(navController = navController)
        }

        composable("detail_tg"){
            isBottomBar.value = false
            DetailTourGuideScreen(navController = navController)
        }
        
        composable("wishlist"){
            isBottomBar.value = false
            WishlistScreen(navController = navController)
        }
        
        composable("partner"){
            isBottomBar.value = false
            PartnerScreen(navController = navController)
        }

        composable("partner_detail"){
            isBottomBar.value = false
            DetailPartnerScreen(navController = navController)
        }

    }

}