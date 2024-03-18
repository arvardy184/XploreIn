package com.arvan.xplorein.ui.presentation.tour_guide

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arvan.xplorein.ui.component.TourGuideCard
import com.arvan.xplorein.ui.component.TouristDestinationCard
import com.arvan.xplorein.ui.theme.green
import com.arvan.xplorein.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TourGuideScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Your Location",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor  = white)
            )
        }, bottomBar = {}
    )  {
        Column (modifier = Modifier.padding(it)) {
            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Tour Guide", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            }
           Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(5.dp)
            ) {
                items(10) { index ->
                    TourGuideCard(
                      // Callback untuk memperbarui status favorit
                        onClick = { /*TODO*/
                            navController.navigate("detail_tg")
                            Log.d("TAG", "TourGuideScreen: $index")
                                  },

                    )
                }
            }
        }
    }
}
