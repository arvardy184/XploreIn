package com.arvan.xplorein.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arvan.xplorein.R
import com.arvan.xplorein.ui.theme.InterFontFamily
import com.arvan.xplorein.ui.theme.green

@Composable
fun AuthButtonComponent(value:String,onClickAuth: () -> Unit) {
    Button(
        onClick = {
                  onClickAuth()
        },
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
fun ClickableAuthTextComponent(
    onLoginSelected: () -> Unit,
    onSignUpSelected: () -> Unit

) {
    val initText = "Already have an account? "
    val loginText = "Login"
    val signupText = "Sign Up"

    val annotatedString = buildAnnotatedString {
        append(initText)
        withStyle(style = SpanStyle(color = Color.Black)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
            pop()
        }
        append(" or ")
        withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)) {
            pushStringAnnotation(tag = signupText, annotation = signupText)
            append(signupText)
            pop()
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),

        style = TextStyle(
            fontFamily = InterFontFamily,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
        ),
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    when (span.item) {
                        loginText -> onLoginSelected()
                        signupText -> onSignUpSelected()
                    }
                }
        }
    )
}



@Composable
fun OnboardingButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(56.dp)
            .padding(horizontal = 5.dp)
            .fillMaxWidth(), // Memastikan tombol memenuhi lebar maksimum
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold), fontSize = 14.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,


            )
    }
}

@Composable
fun DividerTextCompoent(){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ){
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(horizontal = 8.dp)
                .height(1.dp),
            color = Color.Gray,
            thickness = 1.dp
        )

        Text(
            text = "OR",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(horizontal = 8.dp)
                .height(1.dp),
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}

@Composable
fun ClickableTextComponent(onTextSelected: (String) -> Unit) {
    val initText = "You don't have an account? "
    val loginText = "Sign Up"

    val annotatedString = buildAnnotatedString {
        append(initText)
        withStyle(style = SpanStyle(color = Color.Black)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
            pop()
        }
    }

    ClickableText(
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
        ),
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    if (span.item == loginText) {
                        onTextSelected(loginText)
                    }
                }
        }
    )
}
@Composable
fun SocialMediaRow(onFacebookClick: () -> Unit, onGoogleClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Fb Icon
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .clickable { onFacebookClick() }
        )

        // Google Icon
        Image(
            painter = painterResource(id = R.drawable.google),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clickable { onGoogleClick() }
        )
    }
}

@Composable
fun FloatingButtonWithNavigation(
    navController: NavController,
    destinationRoute: String,
    icon: ImageVector
) {
    FloatingActionButton(
        onClick = {
            navController.navigate(destinationRoute)
        },
        modifier = Modifier
            .padding(16.dp)

     // Menempatkan tombol di pojok kanan
    // bawah
    ) {
Icon(imageVector = icon, contentDescription = destinationRoute )
    }
}

