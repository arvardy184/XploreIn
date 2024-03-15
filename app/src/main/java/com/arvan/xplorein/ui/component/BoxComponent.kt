package com.arvan.xplorein.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arvan.xplorein.ui.theme.gray
import com.arvan.xplorein.ui.theme.green

@Composable
fun PaymentOption(
    bank: Bank,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .clickable { onClick() }
) {
    Box(
        modifier = modifier
            .height(80.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(
//                if (isSelected) (2.dp Color.Green) else (1.dp, Color.Gray),
                width = if(isSelected)  2.dp else 1.dp,
                color = if (isSelected) green else gray,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = bank.imageResId),
                contentDescription = bank.name,
                modifier = Modifier.size(40.dp)
            )

            Text(
                text = bank.name,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )

            Checkbox(
                checked = isSelected,
                onCheckedChange = { onClick() },
                colors = CheckboxDefaults.colors(
                    checkedColor = green,
                    uncheckedColor = Color.Gray
                ),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


data class Bank(
    val name: String,
    val imageResId: Int
)