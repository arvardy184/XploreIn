package com.arvan.xplorein.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DetailPayment() {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "How to Pay",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        StepItem(stepNumber = 1, stepDescription = "Go to your bank account")
        StepItem(stepNumber = 2, stepDescription = "Click \"Transfer\"")
        StepItem(stepNumber = 3, stepDescription = "Choose VISA")
        StepItem(stepNumber = 4, stepDescription = "Input the account number 4216578890 or copy the account number")
        StepItem(stepNumber = 5, stepDescription = "Enter the amount of the transfer")
        StepItem(stepNumber = 6, stepDescription = "Make sure the information is correct")
        StepItem(stepNumber = 7, stepDescription = "Confirm by entering your password")
        StepItem(stepNumber = 8, stepDescription = "Payment is successful")
    }
}

@Composable
fun StepItem(stepNumber: Int, stepDescription: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Text(
            text = "$stepNumber.",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.width(20.dp)
        )
        Text(
            text = stepDescription,
            style = MaterialTheme.typography.labelMedium
        )
    }
}
