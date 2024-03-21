package com.arvan.xplorein.domain.repository

import android.annotation.SuppressLint
import android.util.Log
import com.arvan.xplorein.data.Model.BookingModel
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BookingRepository @Inject constructor() {

    private val firestore = FirebaseFirestore.getInstance()

    suspend fun createBooking(booking: BookingModel): Result<Unit> {
        return try {
            val bookingRef = firestore.collection("booking").add(booking).await()
            Log.d("BookingRepository", "Booking created successfully: $booking $bookingRef")
            Result.success(Unit) // Indicate successful booking creation
        } catch (e: Exception) {
            Result.failure(e) // Return failure with the exception
        }
    }
}
