package com.arvan.xplorein.ui.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arvan.xplorein.R
import com.arvan.xplorein.ui.presentation.onboarding.Dimensi.MediumPadding2


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(modifier = modifier) {


        Card(
            modifier = modifier
                .fillMaxHeight(fraction = 0.7f),
            shape = RoundedCornerShape(16.dp),

            ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.BottomCenter
            ) {
                // Gambar dengan konten scale Crop
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = page.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                // Wadah untuk teks dan lingkaran (circle shape)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MediumPadding2),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    // Teks
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = page.title,
                            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                            color = colorResource(id = R.color.white)
                        )
                        Text(
                            text = page.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = colorResource(id = R.color.white)
                        )


                    }

                    // Spacer buat jaraknya
                    Spacer(modifier = Modifier.height(MediumPadding2))



                }
            }
        }
    }
}
