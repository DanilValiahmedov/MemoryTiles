package com.valimade.memorytiles.storage.domain.interactor.display_speed

import com.valimade.memorytiles.settings.data.display_speed.DisplaySpeed
import com.valimade.memorytiles.storage.data.display_speed.DisplaySpeedStore
import kotlinx.coroutines.flow.Flow

class DisplaySpeedInteractor(
    val displaySpeedStore: DisplaySpeedStore,
) {
    suspend fun saveDisplaySpeed(displaySpeed: DisplaySpeed) {
        displaySpeedStore.saveDisplaySpeed(displaySpeed)
    }

    fun getDisplaySpeed(): Flow<DisplaySpeed> {
        return displaySpeedStore.getDisplaySpeed()
    }
}