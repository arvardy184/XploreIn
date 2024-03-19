package com.arvan.xplorein.ui.presentation.wisata

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.arvan.xplorein.R
import com.arvan.xplorein.data.Model.WisataModel
import com.arvan.xplorein.data.ViewModel.WisataViewModel
import com.arvan.xplorein.domain.repository.WisataRepository
import com.arvan.xplorein.ui.component.HomeButton
import com.arvan.xplorein.ui.component.RoundedImageWithText
import com.arvan.xplorein.ui.component.TouristDestination
import com.arvan.xplorein.ui.component.TouristDestinationCard
import com.arvan.xplorein.ui.theme.XploreInTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
 fun WisataScreen(
    navController: NavController,
    cityId: String,
    modifier: Modifier = Modifier.fillMaxSize(),
    viewModel: WisataViewModel = hiltViewModel()
) {

    val wisataList = remember { viewModel.wisataList.collectAsState().value }
    val viewState = remember {viewModel.viewState}
    LaunchedEffect(Unit) {
        viewModel.getWisataByCity(cityId)
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
                                Arrangement.Start
                            ) {
                                IconButton(
                                    onClick = { navController.popBackStack() },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back",
                                        tint = Color.Black,

                                    )
                                }
                            }

                        }

                        // Spacer buat jaraknya
                        Spacer(modifier = Modifier.height(200.dp))


                    }

                }
            }

            // Spacer untuk jarak antara Card dan LazyVerticalGrid
            Spacer(modifier = Modifier.height(16.dp))
        Log.d("wisata list", wisataList.toString() + " " + wisataList.size)
            // LazyVerticalGrid dengan item-item TouristDestinationCard
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(5.dp)
            ) {
                items(wisataList.size) { index ->
                    val wisata = wisataList[index]
                    Log.d("wisata list", wisata.name)
                    TouristDestinationCard(
                        touristDestination = wisata,
                        isFavorite = wisata.isFav,
                        onFavClick = {},
//                        onFavClick = { updateFavoriteStatus(index) }, // Callback untuk memperbarui status favorit
                        onClick = { /*TODO*/
                                  navController.navigate("detail_wisata")},

                    )
                }
            }
        }
    }
}


