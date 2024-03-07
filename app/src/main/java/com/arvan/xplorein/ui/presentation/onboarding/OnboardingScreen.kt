package com.arvan.xplorein.ui.presentation.onboarding

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arvan.xplorein.ui.component.OnboardingButton
import com.arvan.xplorein.ui.component.OnboardingTextButton
import com.arvan.xplorein.ui.presentation.onboarding.Dimensi.MediumPadding2
import com.arvan.xplorein.ui.theme.green
import com.arvan.xplorein.ui.theme.orange
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onSignInClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember {
            derivedStateOf {
                when(pagerState.currentPage){
                    0 -> listOf("","NEXT")
                    1 -> listOf("PREVIOUS","NEXT")
                    2 -> listOf("PREVIOUS","GET STARTED")
                    else -> listOf("","")
                }
            }
        }

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }


        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2, vertical = 10.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            IndicatorPage(modifier = Modifier.width(Dimensi.PageIndicatorWidth),
                pageSize = pages.size, selectedPage = pagerState.currentPage)
        }

        Spacer(modifier = Modifier.weight(1f))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val scope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    OnboardingButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        },
                        containerColor = green,
                        contentColor = Color.White,
                        modifier = Modifier
                            .weight(1f)// Tambahkan padding jika diperlukan

                    )
                }

                OnboardingButton(
                    text = buttonState.value[1],
                    onClick = {
                        Log.d("Test", "Sign up clicked! ${pagerState.currentPage}")
                        scope.launch {
                            if (pagerState.currentPage == 2) {
                                //navigate to sign up
                                onSignInClick()
                            } else {
                                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                            }
                        }
                    },
                    containerColor = orange,
                    contentColor = Color.White,
                    modifier = Modifier
                        .weight(1f) // Tambahkan padding jika diperlukan
                )

            }
    }
        Spacer(modifier = Modifier.weight(0.5f))
}}