package com.arvan.xplorein.domain.repository

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.arvan.xplorein.data.Model.WisataModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
class WisataRepository {
    suspend fun getWisataByCity(cityId: String): List<WisataModel> {
        val db = FirebaseFirestore.getInstance() // Get Firestore instance

        return try {
            val querySnapshot = db.collection("kota").document(cityId).collection("wisata").get().await()

            val destinations = mutableListOf<WisataModel>()
            for (document in querySnapshot.documents) {
                val id = document.getString("id") ?: ""
                val name = document.getString("name") ?: ""
                val imageUrl = document.getString("imageUrl") ?: ""
                val rating = document.getString("rating") ?: ""
                val price = document.getString("price") ?: ""
                val isFav = document.getBoolean("isFav") ?: false
                destinations.add(
                    WisataModel(
                        id = id,
                        name = name,
                        imageUrl = imageUrl,
                        rating = rating,
                        price = price,
                        isFav = isFav
                    )
                )
            }
            Log.d("WisataRepository", "Wisata data retrieved successfully: $destinations")
            destinations
        } catch (e: Exception) {
            Log.e("WisataRepository", "Error getting wisata data", e)
            emptyList() // Return an empty list in case of failure
        }
    }
}
