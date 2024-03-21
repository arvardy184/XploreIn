package com.arvan.xplorein.ui.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arvan.xplorein.R
import com.arvan.xplorein.data.Model.Bank
import com.arvan.xplorein.data.Model.DetailWisataModel
import com.arvan.xplorein.data.ViewModel.WisataViewModel
import com.arvan.xplorein.ui.component.PaymentOption
import com.arvan.xplorein.ui.component.SubmitButton
import com.arvan.xplorein.ui.theme.XploreInTheme
import com.arvan.xplorein.ui.theme.green
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    navController: NavController,
    viewModel: WisataViewModel = hiltViewModel()
) {
    val selectedBankIndex = remember { mutableStateOf(-1) } // Menggunakan state untuk mengelola bank yang dipilih
    val paymentMethod by remember {viewModel.paymentMethod}.collectAsState(initial = "")
    val wisataId = navController.currentBackStackEntry?.arguments?.getString("wisataId") ?: ""
    val bookingDate = navController.currentBackStackEntry?.arguments?.getString("bookingDate") ?: ""
    val partnerNeeded = navController.currentBackStackEntry?.arguments?.getBoolean("partnerNeeded") ?: false
    val detailWisata by remember {viewModel.detailWisata}.collectAsState(initial = DetailWisataModel())


    LaunchedEffect(detailWisata){
        Log.d("Cek data di payment","${detailWisata} ${wisataId} ${bookingDate} ${partnerNeeded} ${paymentMethod}")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pembayaran",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()

                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = green
                )
            )
        }
    ) {
            intentPadding ->
        Column (modifier = Modifier){
                    LazyColumn(
            modifier = Modifier
                .padding(intentPadding)
                .padding(top = 10.dp)
//                .fillMaxSize()
        ) {
            items(banks.size) { index ->
                PaymentOption(
                    bank = banks[index],
                    isSelected = index == selectedBankIndex.value, // Menentukan apakah bank dipilih berdasarkan indeksnya
                    onClick = { selectedBankIndex.value = index } // Mengupdate indeks bank yang dipilih
                )
            }
        }
//        Spacer(modifier = Modifier.fillMaxSize())

                SubmitButton(isEnabled = selectedBankIndex.value != -1, onClick = {
                viewModel.updatePaymentMethod(banks[selectedBankIndex.value].name)
                    Log.d("Cek data", "$wisataId ${bookingDate} ${partnerNeeded}")
                    Log.d("Payment Now", banks[selectedBankIndex.value].name)
                    viewModel.book()
                                                                                  navController.navigate("detail_booking")}, text = "Submit")
        }



    }
}

val banks = listOf(
    Bank("Bank Mandiri", R.drawable.mandiri),
    Bank("Bank BCA",R.drawable.bca),
    Bank("Bank BNI", R.drawable.bni),
    Bank("VISA", R.drawable.visa),
    // Tambahkan bank lainnya sesuai kebutuhan Anda
)