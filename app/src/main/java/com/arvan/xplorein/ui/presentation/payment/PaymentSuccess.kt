package com.arvan.xplorein.ui.presentation.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arvan.xplorein.R
import com.arvan.xplorein.ui.component.PaymentSuccessCard

@Composable
fun PaymentSuccessScreen(navController: NavController){
    Column(modifier = Modifier
        .padding(20.dp)
        .padding(bottom = 20.dp,top = 60.dp),verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,) {
        Box(modifier = Modifier
            .height(200.dp)
            .width(200.dp)) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        val title = "Pembayaran Berhasil"
        val amount = "Rp 100.000"
        val paymentDate = "2023-03-18"

        Box(modifier = Modifier.fillMaxSize()) {
            PaymentSuccessCard(
                title = title,
                amount = amount,
                paymentDate = paymentDate
            )
        }
    }

}
