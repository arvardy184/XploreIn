package com.arvan.xplorein.data.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvan.xplorein.common.ViewState
import com.arvan.xplorein.data.Model.DetailWisataModel
import com.arvan.xplorein.data.Model.WisataModel
import com.arvan.xplorein.domain.repository.TouristDestinationRepository
import com.arvan.xplorein.domain.repository.WisataRepository
import com.arvan.xplorein.ui.component.TouristDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WisataViewModel @Inject constructor(
    private val wisataRepository: WisataRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading) // Initial state
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow() // Expose read-only StateFlow

    private val _wisataList = MutableStateFlow<List<WisataModel>>(emptyList())
    val wisataList: StateFlow<List<WisataModel>> = _wisataList.asStateFlow()

    private val _viewStateDetail = MutableStateFlow<ViewState>(ViewState.Loading) // Initial state
    val viewStateDetail: StateFlow<ViewState> = _viewStateDetail.asStateFlow() // Expose read-only StateFlow

    private val _detailWisata = MutableStateFlow<DetailWisataModel?>(null)

    val detailWisata: StateFlow<DetailWisataModel?> = _detailWisata.asStateFlow()

    fun getWisataByCity(cityId: String) {
        viewModelScope.launch {
            try {
                _viewState.value = ViewState.Loading // Update state to loading
                val wisata = wisataRepository.getWisataByCity(cityId)
                _wisataList.value = wisata
                _viewState.value = ViewState.Success // Update state to success after data retrieval
                Log.d("WisataRepository", "Wisata data retrieved successfully: $wisata")
            } catch (e: Exception) {
                Log.e("WisataRepository", "Error getting wisata data", e)
                _viewState.value = ViewState.Error // Update state to error on exception
            }
        }
    }

    fun getDetailWisata(cityId: String,wisataId: String) {
        viewModelScope.launch {
            try {
                _viewStateDetail.value = ViewState.Loading // Update state to loading
                val wisata = wisataRepository.getDetailWisata(cityId,wisataId)
                _detailWisata.value = wisata
                _viewStateDetail.value = ViewState.Success // Update state to success after data retrieval
                Log.d("Wisata View Model", "Wisata data retrieved successfully: $wisata")
            } catch (e: Exception) {
                Log.e("Wisata View Model", "Error getting wisata data", e)
                _viewStateDetail.value = ViewState.Error // Update state to error on exception
            }
        }
    }
}

