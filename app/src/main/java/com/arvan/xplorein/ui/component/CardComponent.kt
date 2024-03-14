package com.arvan.xplorein.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
