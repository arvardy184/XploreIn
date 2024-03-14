package com.arvan.xplorein.ui.presentation.booking

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arvan.xplorein.common.BottomNavBar
import com.arvan.xplorein.ui.component.ElevatedCardExample
import com.arvan.xplorein.ui.theme.booking
import com.arvan.xplorein.ui.theme.orange
import com.arvan.xplorein.ui.theme.yellow
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Booking",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = booking)
        )

    },
      ) { innerPadding -> // Use padding from the Scaffold
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Apply Scaffold's padding
        ) {
            val pagerState = rememberPagerState(pageCount = {2})
            val tabTitles = listOf("Places", "Guides")
            val coroutineScope = rememberCoroutineScope()

            // Add height to TabRow
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = Color.White,
                contentColor = Color.Black,
                divider = ({  }),
                indicator = { tabPositions ->
                    val tabWidth = (tabPositions[pagerState.currentPage].right - tabPositions[pagerState.currentPage].left) / 1f // Adjust calculation as needed
                    TabRowDefaults.Indicator(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                            .padding(horizontal = 48.dp)
                            .clip(CircleShape),
                        height = 10.dp,
                        color = orange
                    )
                },
                modifier = Modifier.height(48.dp) // Add height to the TabRow
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
               // Allow pager to occupy remaining space
            ) { page ->
                when (page) {
                    0 -> PlacesPage()
                    1 -> GuidesPage()
                }
            }
        }
    }
}

@Composable
fun GuidesPage() {
    Column( modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        //list view
        LazyColumn(modifier = Modifier){
            items(10){
                ElevatedCardExample(date = "17 September 2024", price = "Rp. 28.590", onSeeDetailsClick = {})
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
//        ElevatedCardExample(date = "17 September 2024", price = "Rp. 28.590", onSeeDetailsClick = {})
    }
}

@Composable
fun PlacesPage() {
    Column( modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
        ) {
      ElevatedCardExample(date = "17 September 2024", price = "Rp. 28.590", onSeeDetailsClick = {})
    }
}
