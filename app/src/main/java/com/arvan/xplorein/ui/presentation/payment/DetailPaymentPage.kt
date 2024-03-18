package com.arvan.xplorein.ui.presentation.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arvan.xplorein.common.DetailPayment
import com.arvan.xplorein.ui.component.SubmitButton
import com.arvan.xplorein.ui.theme.orange
import com.arvan.xplorein.ui.theme.yellow
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBookingCard(navController: NavController) {
    val isCopied = remember { mutableStateOf(false) }

    val clipboardManager = LocalClipboardManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail Booking",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),

            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = yellow),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Title and Subtitle
                    Text(
                        text = "Detail Booking",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = "Date")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "17 September 2024",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Black
                        )
                    }

                    // Divider
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Jatim Park 3",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Black
                    )


                }
                Column {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = orange),

                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(
                                text = "Total Price",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Rp 650.000",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )


                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Account Number",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ){
                                Text(
                                    text = "4216578890",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                IconButton(
                                    onClick = { clipboardManager.setText(AnnotatedString("4216578890")) },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ContentCopy,
                                        contentDescription = "Copy",
                                        tint = MaterialTheme.colorScheme.onPrimary // Sesuaikan warna ikon dengan tema
                                    )
                                }
                            }




                            Spacer(modifier = Modifier.height(8.dp))
                            SubmitButton(isEnabled = true, onClick = {  navController.navigate("payment_success")}, text = "I Already Paid")


                        }
                    }
                }


            }

         DetailPayment()
            Spacer(modifier = Modifier.height(8.dp))
            SubmitButton(isEnabled = true, onClick = {  navController.navigate("payment_success")}, text = "I Already Paid")
        }
        if (isCopied.value) {
            Text(
                text = "Copied!",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
        }

    }
}


