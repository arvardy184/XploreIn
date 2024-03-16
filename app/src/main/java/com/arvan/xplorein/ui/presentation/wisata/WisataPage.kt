package com.arvan.xplorein.ui.presentation.wisata

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arvan.xplorein.R
import com.arvan.xplorein.ui.component.HomeButton
import com.arvan.xplorein.ui.component.RoundedImageWithText
import com.arvan.xplorein.ui.component.TouristDestination
import com.arvan.xplorein.ui.component.TouristDestinationCard
import com.arvan.xplorein.ui.theme.XploreInTheme

@Composable
fun WisataScreen(
    navController: NavController,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val touristDestinations = remember { // Gunakan remember untuk mempertahankan data selama komposisi
        mutableListOf(
            TouristDestination(
                name = "Pantai Kuta",
                imageResId = R.drawable.kota1,
                rating = 4,
                price = "200.000",
                isFav = false // Atur status favorit awal
            ),
            TouristDestination(
                name = "Ubud Monkey Forest",
                imageResId = R.drawable.kota1,
                rating = 5,
                price = "150.000",
                isFav = true // Atur status favorit awal
            ),
            TouristDestination(
                name = "Pantai Kuta",
                imageResId = R.drawable.kota1,
                rating = 4,
                price = "200.000",
                isFav = false // Atur status favorit awal
            )
        )
    }

    val updateFavoriteStatus: (Int) -> Unit = { index ->
        touristDestinations[index].isFav = !touristDestinations[index].isFav
    }
    XploreInTheme {
        // Content
        Column(
            modifier = modifier
        ) {
            // Card dengan gambar
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                // Isi dalam Card
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.home_walpaper),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                }
            }

            // Spacer untuk jarak antara Card dan LazyVerticalGrid
            Spacer(modifier = Modifier.height(16.dp))

            // LazyVerticalGrid dengan item-item TouristDestinationCard
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(5.dp)
            ) {
                items(touristDestinations.size) { index ->
                    TouristDestinationCard(
                        touristDestination = touristDestinations[index],
                        isFavorite = touristDestinations[index].isFav,
                        onFavClick = { updateFavoriteStatus(index) }, // Callback untuk memperbarui status favorit
                        onClick = { /*TODO*/
                                  navController.navigate("detail_wisata")},
                        key = touristDestinations[index].name
                    )
                }
            }
        }
    }
}
//                    val touristDestinations = listOf(
//                        TouristDestination(
//                            name = "Pantai Kuta",
//                            imageResId = R.drawable.kota1,
//                            rating = 4,
//                            price = "200.000",
//                            isFav = false
//                        ),
//                        TouristDestination(
//                            name = "Ubud Monkey Forest",
//                            imageResId = R.drawable.kota1,
//                            rating = 5,
//                            price = "150.000",
//                            isFav = true
//                        ),
//                        TouristDestination(
//                            name = "Pantai Kuta",
//                            imageResId = R.drawable.kota1,
//                            rating = 4,
//                            price = "200.000",
//                            isFav = false
//                        ),
//                    )
//
//                    TouristDestinationCard(
//                        touristDestination = touristDestinations[it],
//                        onClick = { /*TODO*/ }
//                    )
//
//                }
//
//            }

