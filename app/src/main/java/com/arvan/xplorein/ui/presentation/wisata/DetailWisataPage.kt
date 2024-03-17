package com.arvan.xplorein.ui.presentation.wisata

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arvan.xplorein.R
import com.arvan.xplorein.ui.component.SubmitButton
import com.arvan.xplorein.ui.theme.XploreInTheme
import com.arvan.xplorein.ui.theme.orange

@Composable
fun DetailWisataScreen(navController: NavController) {
    var date = remember { mutableStateOf("") }
    val month = remember { mutableStateOf("") }
    val year = remember { mutableStateOf("") }
    val isChecked = remember { mutableStateOf(false) }
    XploreInTheme {

        Column(
            modifier = Modifier
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                shape = RoundedCornerShape(16.dp),
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.detail_wisata),
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
                                        contentDescription = "Back"
                                    )
                                }
                            }

                        }


                        Spacer(modifier = Modifier.height(200.dp))


                    }

                }
            }
            Column(
                modifier = Modifier

                    .fillMaxWidth()


                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Jatim Park 3",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp) // Adjust padding for the stars
                ) {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            tint = orange,
                            contentDescription = "Star",
                            modifier = Modifier.size(16.dp) // Adjust size of the stars
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Column (modifier = Modifier.fillMaxWidth()){
                Text(
                    text = "Deskripsi",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding( horizontal = 16.dp)
                )

                // Deskripsi
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce non ligula vitae lectus tempus egestas. Maecenas maximus, nisi sit amet egestas hendrerit, ex nibh efficitur ex, a ultrices arcu purus eu justo.",
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
                    text = "Rp. 100.000 / Person",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )

                Text(
                    text = "Date",
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
                            .padding(start = 4.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }


                Text(
                    text = "Do you need a partner?",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding( horizontal = 16.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked.value,
                        onCheckedChange = { isChecked.value = it },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = "Yes")

                    Checkbox(
                        checked = !isChecked.value,
                        onCheckedChange = { isChecked.value = !it },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = "No")
                }

                SubmitButton(isEnabled = isChecked.value && date.value.isNotEmpty() && month.value.isNotEmpty() && year.value.isNotEmpty(), onClick = { navController.navigate("payment") }, text = "Book")


            }


        }

    }
}