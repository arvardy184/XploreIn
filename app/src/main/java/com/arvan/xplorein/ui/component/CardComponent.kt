package com.arvan.xplorein.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
        elevation = CardDefaults.cardElevation(
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

//            .size(height = 100.dp, width = 300.dp)
            .size(100.dp)
            .clickable {
                isSelected.value = true
                onClick()
            }
            .padding( vertical = 8.dp),

        colors = CardDefaults.cardColors(
            containerColor = if (isSelected.value) Color.White else grey
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
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
