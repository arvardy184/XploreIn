package com.arvan.xplorein.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arvan.xplorein.ui.theme.InterFontFamily

@Composable
fun TitleTextComponent(value: String){
    Text(
        text = value,
        modifier= Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .padding(40.dp),

        style = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        ),
        textAlign = TextAlign.Center

    )
}
@Composable
fun MyTextField(labelValue: String) {
    val textValue = remember { mutableStateOf("") }

    Column {
        Text(
            text = labelValue,
            style =  TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.SemiBold,
            ),
            //style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(bottom = 4.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start

        )
        OutlinedTextField(
            value = textValue.value,
            onValueChange = { textValue.value = it },
            keyboardOptions = KeyboardOptions.Default,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}