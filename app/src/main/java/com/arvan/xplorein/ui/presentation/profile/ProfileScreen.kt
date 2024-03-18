package com.arvan.xplorein.ui.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.arvan.xplorein.R
import com.arvan.xplorein.ui.component.ProfileButton
import com.arvan.xplorein.ui.component.TitleTextComponent
import com.arvan.xplorein.ui.presentation.sign_in.UserData
import com.arvan.xplorein.ui.theme.InterFontFamily
import com.arvan.xplorein.ui.theme.red
import com.arvan.xplorein.ui.theme.yellow


@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOutClick: () -> Unit
){

    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

            ){
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Delete", tint = yellow)
            Text(
                text = "XploreInd",
                modifier= Modifier

                    .heightIn(min = 40.dp),


                style = TextStyle(
                    fontFamily = InterFontFamily,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center

            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Delete", tint = Color.Black)
            }
        }
            if(userData?.profilePic != null){
              AsyncImage(model = userData.profilePic, contentDescription = "Profile picture",
                  modifier = Modifier
                      .size(150.dp)
                      .clip(CircleShape),
                  contentScale = ContentScale.Crop)
            }else {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

        if(userData?.username != null){
            Text(text = userData.username,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(16.dp))
        } else {
            Text(text = "Arvan",
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold)
        }
        ProfileButton(icon = Icons.Filled.Person, text = "View and Edit Profile", onClick = { /*TODO*/ })
        ProfileButton(icon = Icons.Filled.ConfirmationNumber, text = "Bookings", onClick = { /*TODO*/ })
        ProfileButton(icon = Icons.Filled.Favorite, text = "Wishlist", onClick = { /*TODO*/ })
        ProfileButton(icon = Icons.AutoMirrored.Filled.Help, text = "Help and Center", onClick = { /*TODO*/ })
        ProfileButton(icon = Icons.Default.Settings, text = "Setting", onClick = { /*TODO*/ })

        Button(onClick = onSignOutClick, colors = ButtonDefaults.buttonColors(containerColor = red)) {
            Text(text = "Logout")
        }
    }
}