package com.arvan.xplorein.data.Model

data class BookingModel(
    val wisataId: String,
    val wisata: DetailWisataModel, // Add WisataModel property
    val bookingDate: String,
    val partnerNeeded: Boolean,
    val paymentMethod: String,
    val userId: String
)