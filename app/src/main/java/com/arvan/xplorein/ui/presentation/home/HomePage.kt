package com.arvan.xplorein.ui.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlarm
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arvan.xplorein.R
import com.arvan.xplorein.ui.component.SearchField
import com.arvan.xplorein.ui.presentation.onboarding.Dimensi
import com.arvan.xplorein.ui.theme.XploreInTheme

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier.fillMaxSize()
) {
XploreInTheme {


    // Content
    Column(
        modifier = modifier
    ) {
        Card(
            modifier = modifier
                .fillMaxHeight(fraction = 0.3f),
            shape = RoundedCornerShape(16.dp),
            ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.BottomCenter
            ) {
                // Gambar dengan konten scale Crop
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.onboarding1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                // Wadah untuk teks dan lingkaran (circle shape)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .padding(top = 20.dp,),

                    contentAlignment = Alignment.TopCenter
                ) {
                    // Teks
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                //spaceBetween
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Hai, Jane Cooper!",
                                style = MaterialTheme.typography.labelMedium
                                , color = Color.Black
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            IconButton(
                                onClick = { /* Tampilkan menu profile */ },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Person, contentDescription = "Profile")
                            }
                        }

                    }

                    // Spacer buat jaraknya
                    Spacer(modifier = Modifier.height(200.dp))


                }
                SearchField(
                    modifier = Modifier,
                    onValueChange = {},
                    placeholder = "Search",
                    value = "",
                    onSearchClick = {}

                )
            }
        }
        // Header




        // Content Area
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            // Tour Guide Section
            Text(
                text = "Tour Guide",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.onboarding1),
                    contentDescription = "Placeholder for Tour Guide 1",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(4.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.onboarding2),
                    contentDescription = "Placeholder for Tour Guide 2",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(4.dp))
                )

                Spacer(modifier = Modifier.width(8.dp))
            }

            // Partner Section
            Text(
                text = "Patner",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(top = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.onboarding2),
                    contentDescription = "Placeholder for Partner 1",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(4.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.onboarding2),
                    contentDescription = "Placeholder for Partner 2",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(4.dp))
                )

                Spacer(modifier = Modifier.width(8.dp))
            }

            // Food Section
            Text(
                text = "Food",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(top = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.onboarding2),
                    contentDescription = "Placeholder for Food 1",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(4.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.onboarding2),
                    contentDescription = "Placeholder for Food 2",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(4.dp))
                )

                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}}