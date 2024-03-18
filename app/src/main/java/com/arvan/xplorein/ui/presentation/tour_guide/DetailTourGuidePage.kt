package com.arvan.xplorein.ui.presentation.tour_guide

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arvan.xplorein.R
import com.arvan.xplorein.common.CheckboxStatus
import com.arvan.xplorein.ui.component.SubmitButton
import com.arvan.xplorein.ui.theme.black
import com.arvan.xplorein.ui.theme.green
import com.arvan.xplorein.ui.theme.orange
import com.arvan.xplorein.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTourGuideScreen(navController : NavController) {
    val date = remember { mutableStateOf("") }
    val month = remember { mutableStateOf("") }
    val year = remember { mutableStateOf("") }
    val endDate = remember { mutableStateOf("") }
    val endMonth = remember { mutableStateOf("") }
    val endYear = remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail Guide",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
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
        }, bottomBar = {},
        // Enable nested scrolling
    ){

        Column(
            modifier = Modifier.padding(it).verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = white),
                border = BorderStroke(1.dp, Color(0xFFD9D9D9))
            ) {

//                    Image(
//                        modifier = Modifier.fillMaxSize(),
//                        painter = painterResource(id = R.drawable.dummy_detailguide),
//                        contentDescription = null,
//                        contentScale = ContentScale.Crop
//                    )
                Box(modifier = Modifier

                   ) {
                    Image(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                        painter = painterResource(id = R.drawable.dummy_detailguide),
                        contentDescription = "Profile"
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                        ,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = orange,
                            modifier = Modifier

                                .size(24.dp)
                                .padding(4.dp)
                        )
                        Text(text = "4.5", color = black, fontSize = 12.sp)
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Arvan Yudhistia",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp) // Adjust padding for the stars
                ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            tint = green,
                            contentDescription = "Location",
                            modifier = Modifier.size(16.dp) // Adjust size of the stars
                        )
                    Text(text = "Malang, Indonesia", color = Color.Gray, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Column (modifier = Modifier.fillMaxWidth()){
                Text(
                    text = "Language",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding( horizontal = 16.dp)
                )
                val itemsList = listOf("Indonesia", "English", "Mandarin")

                LazyRow(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    items(itemsList) { item ->
                        Card(
                            modifier = Modifier.padding(8.dp),
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            border = BorderStroke(1.dp, Color(0xFFD9D9D9))
                        ) {
                            Text(
                                text = item,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }

                Text(
                    text = "Description",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding( horizontal = 16.dp)
                )

                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal

                )
                Text(
                    text = "Price",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding( horizontal = 16.dp)
                )
                Text(
                    text = "Rp. 100.000 / Day",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "Start Date",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding( horizontal = 16.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    OutlinedTextField(
                        label = { Text("dd") },
                        value = date.value,
                        onValueChange = { newDate -> date.value = newDate },
                        singleLine = true,
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(end = 4.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(20.dp)
                    ) {
                        Text("/", fontSize = 20.sp, color = Color.Black)
                    }

                    OutlinedTextField(
                        label = { Text("mm") },
                        value = month.value,
                        onValueChange = { newMonth -> month.value = newMonth },
                        singleLine = true,
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(start = 4.dp, end = 4.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(20.dp)
                    ) {
                        Text("/", fontSize = 20.sp, color = Color.Black)
                    }
                    OutlinedTextField(
                        label = { Text("yyyy") },
                        value = year.value,
                        onValueChange = { newYear -> year.value = newYear },
                        singleLine = true,
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(start = 4.dp)
                           ,
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                Text(
                    text = "End Date",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding( horizontal = 16.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    OutlinedTextField(
                        label = { Text("dd") },
                        value = endDate.value,
                        onValueChange = { newDate -> endDate.value = newDate },
                        singleLine = true,
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(end = 4.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(20.dp)
                    ) {
                        Text("/", fontSize = 20.sp, color = Color.Black)
                    }


                    OutlinedTextField(
                        label = { Text("mm") },
                        value = endMonth.value,
                        onValueChange = { newMonth -> endMonth.value = newMonth },
                        singleLine = true,
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(start = 4.dp, end = 4.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(20.dp)
                    ) {
                        Text("/", fontSize = 20.sp, color = Color.Black)
                    }
                    OutlinedTextField(
                        label = { Text("yyyy") },
                        value = endYear.value,
                        onValueChange = { newYear -> endYear.value = newYear },
                        singleLine = true,
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(start = 4.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }


                SubmitButton(isEnabled =  date.value.isNotEmpty() && month.value.isNotEmpty() && year.value.isNotEmpty() && endDate.value.isNotEmpty() && endMonth.value.isNotEmpty() && endYear.value.isNotEmpty(), onClick = { navController.navigate("payment") }, text = "Book")
            }
        }
    }
}