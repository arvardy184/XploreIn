package com.arvan.xplorein.ui.presentation.partner

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arvan.xplorein.ui.component.ElevatedCardExample
import com.arvan.xplorein.ui.component.PermintaanPertemananCard
import com.arvan.xplorein.ui.theme.booking
import com.arvan.xplorein.ui.theme.orange
import com.arvan.xplorein.ui.theme.white
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PartnerScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Partner",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = white)
        )

    },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val pagerState = rememberPagerState(pageCount = {3})
            val tabTitles = listOf("Request", "Add","Friend")
            val coroutineScope = rememberCoroutineScope()


            TabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = Color.White,
                contentColor = Color.Black,
                divider = ({  }),
                indicator = { tabPositions ->
                    val tabWidth = (tabPositions[pagerState.currentPage].right - tabPositions[pagerState.currentPage].left) / 1f
                    TabRowDefaults.Indicator(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                            .padding(horizontal = 48.dp)
                            .clip(CircleShape),
                        height = 10.dp,
                        color = orange
                    )
                },
                modifier = Modifier.height(48.dp)
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

                ) { page ->
                when (page) {
                    0 -> RequestPage()
                    1 -> AddPage()
                    2 -> FriendPage()
                }
            }
        }
    }
}

@Composable
fun RequestPage() {
    Column( modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        //list view
        LazyColumn(modifier = Modifier){
            items(10){
               PermintaanPertemananCard(
                   typePartner = 1,
                   nama = "Arvan Ardana",
                   daerah = "Malang",
                   umur = 19,
                   onClickTerima = { /*TODO*/ }) {

               }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
//        ElevatedCardExample(date = "17 September 2024", price = "Rp. 28.590", onSeeDetailsClick = {})
    }
}

@Composable
fun AddPage() {
    Column( modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        LazyColumn(modifier = Modifier){
            items(10){
                PermintaanPertemananCard(
                    typePartner = 2,
                    nama = "Arvan Ardana",
                    daerah = "Malang",
                    umur = 19,
                    onClickTerima = { /*TODO*/ }) {

                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun FriendPage() {
    Column( modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        LazyColumn(modifier = Modifier){
            items(10){
                PermintaanPertemananCard(
                    typePartner = 3,
                    nama = "Arvan Ardana",
                    daerah = "Malang",
                    umur = 19,
                    onClickTerima = { /*TODO*/ }) {

                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
