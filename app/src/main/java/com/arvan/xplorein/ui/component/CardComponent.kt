package com.arvan.xplorein.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arvan.xplorein.R
import com.arvan.xplorein.ui.theme.grey
import com.arvan.xplorein.ui.theme.orange
import com.arvan.xplorein.ui.theme.yellow

@Composable
fun BookingCard(
    date: String,
    price: String,
    onSeeDetailsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(yellow, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Penida Island",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = date,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Harga: $price",
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { onSeeDetailsClick() }
            ) {
                Text(text = "See Details")
            }
        }
    }
}

@Composable
fun ElevatedCardExample( date: String,
                         price: String,
                         onSeeDetailsClick: () -> Unit

) {
    ElevatedCard(
        elevation = cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .size(width = 550.dp, height = 170.dp)
            .fillMaxWidth()
        , colors = CardDefaults.elevatedCardColors(containerColor = yellow)

    ) {
        Column(
            modifier = Modifier
                .background(yellow, RoundedCornerShape(16.dp))
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Penida Island",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = date,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Divider(color = orange, thickness = 2.dp, modifier = Modifier.padding(vertical = 8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = " $price",
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = { onSeeDetailsClick() },
                    colors = ButtonDefaults.buttonColors(containerColor = orange)
                ) {

                    Text(text = "See Details")
                }
            }

        }
    }
}

@Composable
fun NotificationItem(
    notification: Notification,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    val isSelected = remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()

            .size(100.dp)
            .clickable {
                isSelected.value = true
                onClick()
            }
            .padding(vertical = 8.dp),

        colors = CardDefaults.cardColors(
            containerColor = if (isSelected.value) Color.White else grey
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(
                modifier = Modifier.size(56.dp),
                painter = painterResource(id = notification.profilePictureResId),
                contentDescription = notification.profileName
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = notification.text,
                style = MaterialTheme.typography.bodyMedium,
                color =  Color.Black
            )
        }
    }
}


data class Notification(
    val profilePictureResId: Int,
    val profileName: String,
    val text: String
)


@Composable
fun TouristDestinationCard(
    touristDestination: TouristDestination,
    modifier: Modifier = Modifier.fillMaxWidth(),
    isFavorite: Boolean,
    onFavClick: () -> Unit,
    onClick: () -> Unit,
    key: Any
) {
    Card(
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 4.dp) 
            ,
        elevation = cardElevation(
            defaultElevation = 8.dp
        ),

    ) {
        Column(
            modifier = Modifier.clickable(onClick = onClick)
        ) {
            Box(modifier = Modifier.aspectRatio(1.5f)) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    painter = painterResource(id = touristDestination.imageResId),
                    contentDescription = touristDestination.name
                )

             
                IconButton(
                    onClick = onFavClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp)) 

            Text(
                text = touristDestination.name,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Row( 
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp) 
            ) {
                repeat(touristDestination.rating) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        tint = orange,
                        contentDescription = "Star",
                        modifier = Modifier.size(16.dp) 
                    )
                }
            }
            Row(
                modifier = Modifier
                    , 
                horizontalArrangement = Arrangement.SpaceBetween,

            ) {
                Text(
                    text = "Rp ${touristDestination.price}",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Button(
                    contentPadding = PaddingValues(10.dp),

                    onClick = { 
                              onClick()},
                    colors = ButtonDefaults.buttonColors(containerColor = orange)
                )
                {
                    Text(text = "See Details", color = Color.White,  fontSize = 10.sp)
                }
            }
        }
    }
}

@Composable
fun PaymentSuccessCard(
    modifier: Modifier = Modifier,
    title: String,
    amount: String,
    paymentDate: String,
) {
    val checkIconColor = Color(0xFF2ECC40)
    val borderColor = Color(0xFFD9D9D9)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
        , // Menyesuaikan tinggi sesuai kebutuhan
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(width = 1.dp, color = borderColor)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Divider(color = borderColor, thickness = 1.dp)

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Jumlah:",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = amount,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tanggal Pembayaran:",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = paymentDate,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
            }
        }
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "Check Icon",
            tint = checkIconColor,
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.TopCenter)
                .offset(0.dp, -20.dp)
        )
    }


}



data class TouristDestination(
    val name: String,
    val imageResId: Int,
    val rating: Int,
    val price: String,
    var isFav: Boolean
)

