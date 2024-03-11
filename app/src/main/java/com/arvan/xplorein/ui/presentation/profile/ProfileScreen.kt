package com.arvan.xplorein.ui.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.arvan.xplorein.R
import com.arvan.xplorein.ui.component.ProfileButton
import com.arvan.xplorein.ui.component.TitleTextComponent
import com.arvan.xplorein.ui.presentation.sign_in.UserData


@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOutClick: () -> Unit
){
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        
        Row(modifier = Modifier,
                Arrangement.SpaceBetween,

            ){

            TitleTextComponent(value = stringResource(id = R.string.app_name))
            Icon(imageVector =  Icons.Filled.Menu, contentDescription = "Menu",)
        }   
            if(userData?.profilePic != null){
              AsyncImage(model = userData.profilePic, contentDescription = "Profile picture",
                  modifier = Modifier
                      .size(150.dp)
                      .clip(CircleShape),
                  contentScale = ContentScale.Crop)
            }

        if(userData?.username != null){
            Text(text = userData.username,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(16.dp))
        }
        ProfileButton(icon = Icons.Default.Menu, text = "View and Edit Profile", onClick = { /*TODO*/ })
        Button(onClick = onSignOutClick) {
            Text(text = "Sign Out")
        }
    }
}