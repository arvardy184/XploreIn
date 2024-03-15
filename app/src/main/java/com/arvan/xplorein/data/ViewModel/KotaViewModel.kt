package com.arvan.xplorein.data.ViewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvan.xplorein.data.Model.KotaModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeViewModel : ViewModel() {
    private val _touristDestinations = MutableLiveData<List<KotaModel>>()
    val touristDestinations: LiveData<List<KotaModel>> = _touristDestinations

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadTouristDestinations()
    }

    private fun loadTouristDestinations() {
        viewModelScope.launch {
            try {
                val querySnapshot = Firebase.firestore.collection("kota").get()
                val destinations = mutableListOf<KotaModel>()

                querySnapshot.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!.documents) {
                            val id = document.getString("id")
                            val name = document.getString("name")

                            if (id != null && name != null) {
                                destinations.add(KotaModel(id, name))
                            } else {
                                Log.w(TAG, "Incomplete data for tourist destination: $document")
                            }
                        }
                        _touristDestinations.value = destinations
                    } else {
                        Log.e(TAG, "Error getting tourist destinations: ", task.exception)
                    }
                    _isLoading.value = false // Set loading to false only on success/failure
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error getting tourist destinations: ", e)
                _isLoading.value = false
            }
        }
    }
}
