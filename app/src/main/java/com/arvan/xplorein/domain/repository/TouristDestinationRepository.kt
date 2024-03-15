package com.arvan.xplorein.domain.repository

import com.arvan.xplorein.ui.component.TouristDestination

interface TouristDestinationRepository {
    suspend fun fetchTouristDestinations(): List<TouristDestination>

    suspend fun updateFavoriteStatus(destination: TouristDestination)

}