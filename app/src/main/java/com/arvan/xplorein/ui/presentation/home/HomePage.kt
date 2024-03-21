package com.arvan.xplorein.ui.presentation.home

import android.content.ContentValues.TAG
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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlarm
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arvan.xplorein.R
import com.arvan.xplorein.data.ViewModel.HomeViewModel
import com.arvan.xplorein.ui.component.HomeButton
import com.arvan.xplorein.ui.component.RoundedImageWithText
import com.arvan.xplorein.ui.component.SearchField
import com.arvan.xplorein.ui.presentation.onboarding.Dimensi
import com.arvan.xplorein.ui.theme.XploreInTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier.fillMaxSize(),
    viewModel: HomeViewModel = viewModel()
) {
XploreInTheme {

    val touristDestinations = viewModel.touristDestinations.observeAsState(emptyList())
    val isLoading = viewModel.isLoading.observeAsState(initial = true)

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
                    .clip(RoundedCornerShape(16.dp))
                    ,

                contentAlignment = Alignment.BottomCenter
            ) {

                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        ,
                    painter = painterResource(id = R.drawable.home_walpaper),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

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
                                , color = Color.White
                                , fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily.SansSerif
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            IconButton(
                                onClick = {
                                          navController.navigate("notification")},
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Notifications, contentDescription = "Profile", tint = Color.Black)
                            }
                        }

                    }

                    // Spacer buat jaraknya
                    Spacer(modifier = Modifier.height(200.dp))


                }

            }
        }
        LazyRow(
            modifier = Modifier.padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                HomeButton(icon = Icons.Default.Map, text = "Tour Guide", onClick = { /*TODO*/
navController.navigate("tour_guide")
                })
            }
            item {
                HomeButton(icon = Icons.Default.Sms, text = "Partner", onClick = {
                    navController.navigate("partner")
                })
            }
            item {
                HomeButton(icon = Icons.Default.Restaurant, text = "Food", onClick = { /*TODO*/ })
            }
            item {
                HomeButton(icon = Icons.Default.Favorite, text = "Wishlist", onClick = {

                    navController.navigate("wishlist")
                })
            }
        }

        if (!isLoading.value) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(14.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(touristDestinations.value) { index, destination ->
                    Log.d("TAG", "Index: $index, Destination: $destination")
                    RoundedImageWithText(
                        text = destination.name,
                        imageResId =
                        R.drawable.kota1,
                        onClick = {
                            navController.navigate("wisata/${destination.id}")
                        }
                    )
                }
            }

        } else {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}


    }




