package com.arvan.xplorein.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arvan.xplorein.ui.theme.InterFontFamily
import com.arvan.xplorein.ui.theme.green


@Composable
fun NormalTextComponent(value: String){
    Text(
        text = value,
        modifier= Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .padding(40.dp),

        style = TextStyle(
            //i want to use Inter
            fontFamily = InterFontFamily,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        ),
        textAlign = TextAlign.Center

        )
}

@Composable
//buat text untuk tulisan email,password atau lainnya
fun SmallTextComponent(value: String){
    Text(
        text = value,
        modifier= Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Normal
        ),
        textAlign = TextAlign.Start
    )
}

@Composable
fun TextButtonComponent(value: String, onClick: () -> Unit) { // Tambahkan onClick
    Button(
      onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(Color.Transparent), // Tombol transparan
        contentPadding = PaddingValues() // Hapus padding default
    ) {
        Text(
            text = value,
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
    }
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

@Composable
fun ButtonComponent(value:String) {
    Button(
        onClick = {  },
        colors = ButtonDefaults.buttonColors(
            containerColor = green,
        ),

        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 16.dp
        ),
               shape = ButtonDefaults.elevatedShape,
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(24.dp)
            .background(
                color = green,
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center

        ){
            Text (text = value,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = InterFontFamily
                )
            )
        }
    }
}

@Composable
fun ClickableLoginTextComponent(onTextSelected: (String)
 -> Unit){
    val initText = "Already have an account"
    val loginText = "Login"

    val annotatedString = buildAnnotatedString{
        append(initText)
        withStyle(style = SpanStyle(color = green)){
            pushStringAnnotation(tag = loginText, annotation = loginText)
        }
    }
    
    ClickableText(
        modifier= Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
        ),




        text = annotatedString, onClick = {
        offset ->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also{
                span ->
                Log.d("Test click text ", span.item)
                if (span.item == loginText){
                    onTextSelected(loginText)
                }

            }
    })
}
//@Composable
//fun ButtonComponent(value: String){
//    Button(
//        onClick = {},
//        modifier = Modifier
//            .fillMaxWidth()
//            .heightIn(48.dp),
//        contentPadding = PaddingValues(),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = MaterialTheme.colorScheme.primary
//        ),
//
//        elevation = ButtonDefaults.buttonElevation(
//            defaultElevation = 8.dp,
//            pressedElevation = 16.dp
//        ),
//
//    )
//}


