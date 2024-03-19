package com.arvan.xplorein.data.Model

data class DetailWisataModel(
    val id: String = "",
    val name: String = "",
    val rating: Int = 0,
    val description: String = "",
    val price: String = "",
    val imageUrl: String = "",
    val address: String = "",
    val facilities: List<String> = emptyList(),
    val isFav: Boolean = false
)
