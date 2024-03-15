package com.arvan.xplorein.data.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvan.xplorein.domain.repository.TouristDestinationRepository
import com.arvan.xplorein.ui.component.TouristDestination
import kotlinx.coroutines.launch

class WisataViewModel(private val repository: TouristDestinationRepository): ViewModel() {
    private  val _touristDestinations = MutableLiveData<List<TouristDestination>>()
    val touristDestinations: LiveData<List<TouristDestination>>
        get() = _touristDestinations

    fun getTouristDestinations() {
        viewModelScope.launch {
            val destinations = repository.fetchTouristDestinations()
            _touristDestinations.value = destinations
        }
    }

    fun updateFavoriteStatus(destination: TouristDestination) {
      val updatedList = touristDestinations.value!!.toMutableList()
      updatedList[updatedList.indexOf(destination)] = destination
      _touristDestinations.value = updatedList
    }
}