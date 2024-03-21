package com.arvan.xplorein.domain.repository

import android.annotation.SuppressLint
import android.util.Log
import com.arvan.xplorein.data.Model.BookingModel
import com.arvan.xplorein.data.Model.DetailWisataModel
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

    private val bookingCollection = firestore.collection("booking")
    suspend fun getAllBookings(): Result<List<BookingModel>> {
        return try {
            val snapshot = bookingCollection.get().await().documents
            val bookings = snapshot.map { document ->
                val id = document.getString("id") ?: ""
                val name = document.getString("name") ?: ""
                val email = document.getString("email") ?: ""
                val phone = document.getString("phone") ?: ""
                val destination = document.getString("destination") ?: ""
                val date = document.getString("date") ?: ""
                val time = document.getString("time") ?: ""

                BookingModel(
                    wisataId = id,
                    wisata = DetailWisataModel(
                        id = id,
                        name = name,
                        imageUrl = "",
                        rating = 0,
                        price = "",
                        description = "",
                        address = "",
                        facilities = emptyList(),
                        isFav = false
                    ),
                    bookingDate = time,
                    partnerNeeded = false,
                    paymentMethod = "",
                    userId = "",

                )
            }
            Result.success(bookings)
        } catch (e: Exception) {
            Log.e("BookingRepository", "Error getting all bookings", e)
            Result.failure(e)
        }
    }

}
