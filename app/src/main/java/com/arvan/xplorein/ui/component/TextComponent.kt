package com.arvan.xplorein.ui.component

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arvan.xplorein.R
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
fun MyTextField(
    labelValue: String,
    textValue: String,
    onValueChanged: (String) -> Unit,
    isPassword: Boolean = false) {


    val passwordVisibility = remember { mutableStateOf(false) }
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
            value = textValue,
            onValueChange = { onValueChanged(it) },
            keyboardOptions = KeyboardOptions.Default,
            visualTransformation = if (isPassword && !passwordVisibility.value) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            trailingIcon = {
                if (isPassword) {
                    val icon = if (passwordVisibility.value){
                        Icons.Filled.Visibility
                    } else{
                        Icons.Filled.VisibilityOff
                    }

//                    val icon = if (passwordVisibility.value) Icons.Filled. else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                        Icon(icon, contentDescription = "Toggle password visibility")
                    }
                }
            },
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
//
//@Composable
//fun SearchBar(
//    hint: String,
//    modifier: Modifier = Modifier,
//    isEnabled: (Boolean) = true,
//    height: Dp = 40.dp,
//    elevation: Dp = 0.dp,
//    cornerShape: Shape
//    backgroundColor: Color = Color.White,
//    onSearchClicked: () -> Unit = {},
//    onTextChange: (String) -> Unit = {},
//) {
//    var text by remember { mutableStateOf(TextFieldValue()) }
//    Row(
//        modifier = Modifier
//            .height(height)
//            .fillMaxWidth()
//            .shadow(elevation = elevation, shape = cornerShape)
//            .background(color = backgroundColor, shape = cornerShape)
//            .clickable { onSearchClicked() },
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        BasicTextField(
//            modifier = modifier
//                .weight(5f)
//                .fillMaxWidth()
//                .padding(horizontal = Dimens.dp24),
//            value = text,
//            onValueChange = {
//                text = it
//                onTextChange(it.text)
//            },
//            enabled = isEnabled,
//            textStyle = TextStyle(
//                color = MaterialTheme.colorScheme.primary,
//                fontSize = Dimens.sp16,
//                fontWeight = FontWeight.Bold
//            ),
//            decorationBox = { innerTextField ->
//                if (text.text.isEmpty()) {
//                    Text(
//                        text = hint,
//                        color = Color.Gray.copy(alpha = 0.5f),
//                        fontSize = Dimens.sp16,
//                        fontWeight = FontWeight.Bold,
//                    )
//                }
//                innerTextField()
//            },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Text,
//                imeAction = ImeAction.Search
//            ),
//            keyboardActions = KeyboardActions(onSearch = { onSearchClicked() }),
//            singleLine = true
//        )
//        Box(
//            modifier = modifier
//                .weight(1f)
//                .size(Dimens.dp40)
//                .background(color = Color.Transparent, shape = CircleShape)
//                .clickable {
//                    if (text.text.isNotEmpty()) {
//                        text = TextFieldValue(text = "")
//                        onTextChange("")
//                    }
//                },
//        ) {
//            if (text.text.isNotEmpty()) {
//                Icon(
//                    modifier = modifier
//                        .fillMaxSize()
//                        .padding(Dimens.dp10),
//                    painter = painterResource(id = R.drawable.baseline_clear_24),
//                    contentDescription = stringResource(R.string.search),
//                    tint = MaterialTheme.colorScheme.primary,
//                )
//            } else {
//                Icon(
//                    modifier = modifier
//                        .fillMaxSize()
//                        .padding(Dimens.dp10),
//                    painter = painterResource(id = R.drawable.ic_search),
//                    contentDescription = stringResource(R.string.search),
//                    tint = MaterialTheme.colorScheme.primary,
//                )
//            }
//        }
//    }

// Search Bar
@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Search places, foods, guide, partners, etc",
    backgroundColor: Color = Color.White,
    contentColor: Color = LocalContentColor.current,
    onSearchClick: () -> Unit = {} // Optional callback for explicit search action
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp) // Consistent height for a standard search field
            .background(Color.White, shape = RoundedCornerShape(8.dp)) // Rounded corners for a modern look
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween // Distribute evenly
        ) {
            IconButton(
                onClick = onSearchClick, // Handle search action if provided
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = contentColor // Use content color for consistency
                )
            }
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { Text(placeholder, color =Color.White) }, // Use content color for consistency
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp).background(Color.White), // Padding for spacing from trailing icon
                textStyle = MaterialTheme.typography.labelMedium, // Consistent style
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search // Suggest search action on keyboard
                ),
                keyboardActions = KeyboardActions(onSearch = { onSearchClick() }),
            )
        }
    }
}