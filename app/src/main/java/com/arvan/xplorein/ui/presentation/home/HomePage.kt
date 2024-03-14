package com.arvan.xplorein.ui.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlarm
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.arvan.xplorein.common.BottomNavBar
import com.arvan.xplorein.ui.component.HomeButton
import com.arvan.xplorein.ui.component.RoundedImageWithText
import com.arvan.xplorein.ui.component.SearchField
import com.arvan.xplorein.ui.presentation.onboarding.Dimensi
import com.arvan.xplorein.ui.theme.XploreInTheme
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    XploreInTheme {
        Scaffold(
//            bottomBar = {
////                BottomNavBar(
////                    navController = navController,
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(horizontal = 16.dp, vertical = 8.dp)
////                )
//                        BottomNavBar(navController = navController, modifier = Modifier )
//            },
            content = { innerPadding ->
                Log.d("TAG", "HomeScreen: $innerPadding")
                Column(
                    modifier = modifier
                        .padding(innerPadding) // Apply Scaffold's padding
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxHeight(fraction = 0.3f)
                            .padding(16.dp), // Add padding to Card
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
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(id = R.drawable.onboarding1),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                            // Wadah untuk teks dan lingkaran (circle shape)
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp),
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
                                            .padding(horizontal = 16.dp, vertical = 8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Hai, Jane Cooper!",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = Color.Black
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
                        }
                    }

                    LazyRow(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp), // Add vertical padding
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            HomeButton(icon = coil.base.R.drawable.abc_vector_test, text = "Wishlist", onClick = { /*TODO*/ })
                        }
                        item {
                            HomeButton(icon = coil.base.R.drawable.abc_vector_test, text = "Wishlist", onClick = { /*TODO*/ })
                        }
                        item {
                            HomeButton(icon = coil.base.R.drawable.abc_vector_test, text = "Wishlist", onClick = { /*TODO*/ })
                        }
                        item {
                            HomeButton(icon = coil.base.R.drawable.abc_vector_test, text = "Wishlist", onClick = { /*TODO*/ })
                        }
                    }

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // Create a grid with 2 columns
                        modifier = Modifier.padding(16.dp),
                        contentPadding = PaddingValues(16.dp) // Optional: spacing between items
                    ) {
                        items(20) {
                            RoundedImageWithText(
                                text = "Bali",
                                imageResId = R.drawable.kota1
                            )
                        }
                    }
                }
            }
        )
    }
}
