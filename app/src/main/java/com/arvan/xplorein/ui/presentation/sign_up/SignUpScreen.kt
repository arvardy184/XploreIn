package com.arvan.xplorein.ui.presentation.sign_up

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arvan.xplorein.R
import com.arvan.xplorein.ui.component.AuthButtonComponent
import com.arvan.xplorein.ui.component.MyTextField
import com.arvan.xplorein.ui.component.ClickableLoginTextComponent

import com.arvan.xplorein.ui.component.TitleTextComponent
import com.arvan.xplorein.ui.theme.green
import com.arvan.xplorein.ui.theme.red
import com.arvan.xplorein.ui.theme.yellow

@Composable
fun SignUpScreen(
onClick : () -> Unit
){
    Surface (color = yellow,
        modifier = Modifier
            .fillMaxSize()

    ){
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            TitleTextComponent(value = stringResource(id = R.string.app_name))
            Spacer(modifier = Modifier.height(10.dp))
            MyTextField(labelValue = "Email/Phone Number")
            Spacer(modifier = Modifier.height(10.dp))
            MyTextField(labelValue = "Full Name")
            Spacer(modifier = Modifier.height(10.dp))
            MyTextField(labelValue = "Username")
            Spacer(modifier = Modifier.height(10.dp))
            MyTextField(labelValue = "Password")
            Spacer(modifier = Modifier.height(10.dp))
            AuthButtonComponent(value = "Sign Up")
            Spacer(modifier = Modifier.height(16.dp))

            ClickableLoginTextComponent(onTextSelected = {
                "Login"
                Log.d("Test", "Login clicked!")
                onClick()
            })

        }


    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreviewOfSignUpScreen() {
//    Surface(
//        color = yellow,
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        Column(
//            verticalArrangement = Arrangement.Top,
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(28.dp)
//        ) {
//            Spacer(modifier = Modifier.height(30.dp))
//            NormalTextComponent(value = stringResource(id = R.string.app_name))
//            Spacer(modifier = Modifier.height(10.dp))
//            MyTextField(labelValue = "Email/Phone Number")
//            Spacer(modifier = Modifier.height(10.dp))
//            MyTextField(labelValue = "Full Name")
//            Spacer(modifier = Modifier.height(10.dp))
//            MyTextField(labelValue = "Username")
//            Spacer(modifier = Modifier.height(10.dp))
//            MyTextField(labelValue = "Password")
//            Spacer(modifier = Modifier.height(10.dp))
//            ButtonComponent(value = "Sign Up")
//            Spacer(modifier = Modifier.height(16.dp))
//
//               ClickableLoginTextComponent(onTextSelected = {
//
//                   Log.d("Test", "Login clicked!")
//               })
//
//        }
//    }
//}
//

