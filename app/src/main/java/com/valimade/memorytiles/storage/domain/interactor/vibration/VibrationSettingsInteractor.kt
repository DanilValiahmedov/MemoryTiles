package com.valimade.memorytiles.storage.domain.interactor.vibration

import com.valimade.memorytiles.storage.data.vibration.VibrationStore
import com.valimade.memorytiles.vibration.domain.model.UsingVibration
import kotlinx.coroutines.flow.Flow

class VibrationSettingsInteractor(
    val vibrationStore: VibrationStore
) {
    suspend fun saveUsageStatusVibration(usingVibration: UsingVibration) {
        vibrationStore.saveUsageStatusVibration(usingVibration)
    }

    fun getUsageStatusVibration(): Flow<UsingVibration> {
        return vibrationStore.getUsageStatusVibration()
    }
}