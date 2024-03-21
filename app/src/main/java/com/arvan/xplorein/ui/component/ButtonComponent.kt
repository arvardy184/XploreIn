package com.arvan.xplorein.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arvan.xplorein.R
import com.arvan.xplorein.ui.theme.InterFontFamily
import com.arvan.xplorein.ui.theme.gray
import com.arvan.xplorein.ui.theme.green
import com.arvan.xplorein.ui.theme.yellow

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
    onClickSelected: () -> Unit,
    teks: String
) {
    val initText = "Already have an account? "

    val annotatedString = buildAnnotatedString {
        append(initText)
        withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold )) {
            pushStringAnnotation(tag = teks, annotation = teks)
            append(teks)
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
                       teks -> onClickSelected()
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
            .fillMaxWidth(), 
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
      
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .clickable { onFacebookClick() }
        )


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

    ) {
Icon(imageVector = icon, contentDescription = destinationRoute )
    }
}
@Composable
fun HomeButton(    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = Color.Green,
    contentColor: Color = Color.White,
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(yellow)
                .border(
                    brush = Brush.horizontalGradient(colors = listOf(yellow, green)),
                    width = 1.dp,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = green,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RoundedImageWithText(
    imageResId: Int,
    text: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    imageModifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(modifier = modifier.padding(10.dp).clickable(onClick = onClick)) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = contentDescription,
            modifier = Modifier
                .width(160.dp)
                .height(160.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .then(imageModifier)
        )
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
                .padding(vertical = 14.dp)
                .then(textModifier),
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ProfileButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    cornerRadius: Dp = 20.dp,
    height: Dp = 40.dp,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 20.dp,vertical = 10.dp)
            .border(shape = RoundedCornerShape(cornerRadius), width = 1.dp, color = green)
            .clip(RoundedCornerShape(cornerRadius))
            .height(height)
            .fillMaxWidth()
            .shadow(
                ambientColor = green,
                elevation = 10.dp,
                spotColor = green,

            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(imageVector = icon, contentDescription = text, tint = green, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(40.dp))
            Text(
                text = text,
                color = contentColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
            )
        }
    }
}

@Composable
fun TabContent(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}

@Composable
fun SubmitButton(
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) green else gray,
            contentColor = Color.White
        ),
        enabled = isEnabled
    ) {
        Text(text = text)
    }
}

@Composable
fun PartnerSelection(
    selected: Boolean,
    onSelectPartner: (Boolean) -> Unit
) {
    val options = listOf(true, false)

    Row(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            ) {
                RadioButton(
                    selected = selected == option,
                    onClick = { onSelectPartner(option) },
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (option) "Ya" else "Tidak",
                    modifier = Modifier.padding(start = 4.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}
