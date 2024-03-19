package com.arvan.xplorein.data.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvan.xplorein.common.ViewState
import com.arvan.xplorein.data.Model.WisataModel
import com.arvan.xplorein.domain.repository.TouristDestinationRepository
import com.arvan.xplorein.domain.repository.WisataRepository
import com.arvan.xplorein.ui.component.TouristDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WisataViewModel @Inject constructor(
    private val wisataRepository: WisataRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState

    private val _wisataList = MutableStateFlow<List<WisataModel>>(emptyList())
    val wisataList: StateFlow<List<WisataModel>> = _wisataList
    fun getWisataByCity(cityId: String) {
        viewModelScope.launch {
            try {
                val wisata = wisataRepository.getWisataByCity(cityId)
                _wisataList.value = wisata
                _viewState.value = ViewState.Success
                Log.d("WisataRepository", "Wisata data retrieved successfully: $wisata")
            } catch (e: Exception) {
                Log.e("WisataRepository", "Error getting wisata data", e)
                _viewState.value = ViewState.Error
            }
        }
    }
}

