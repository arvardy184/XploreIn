package com.arvan.xplorein.ui.presentation.partner

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arvan.xplorein.R
import com.arvan.xplorein.ui.theme.black
import com.arvan.xplorein.ui.theme.green
import com.arvan.xplorein.ui.theme.grey
import com.arvan.xplorein.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPartnerScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.weight(1f)) // Spacer di sebelah kiri
                        Card(
                            modifier = Modifier
                                .height(30.dp)
                                .width(200.dp),
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = green
                            ),
                        ) {
                            Text(
                                text = "Connected",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f)) // Spacer di sebelah kanan
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = white)
            )


        },
    ){
        Column(modifier = Modifier.padding(it)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
                    .padding(horizontal = 20.dp),
//                colors = CardDefaults.cardColors(containerColor = Color.Green)
            ) {
                Box(modifier = Modifier) {
                    // Gambar
                    Image(
                        painter = painterResource(id = R.drawable.detail_partner),
                        contentDescription = "Partner",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop // Scale gambar agar memenuhi seluruh area
                    )


                    // Teks
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                ,
                           colors = CardDefaults.cardColors(containerColor = white), // Warna latar belakang card teks
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(8.dp),
                            border = BorderStroke(2.dp, grey.copy())
                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(
                                    text = "John Mayer",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    color = black // Warna teks
                                )
                                Text(
                                    text = "Malang",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    color = black// Warna teks
                                )
                            }
                        }
                    }
                }
            }
        }

    }

}