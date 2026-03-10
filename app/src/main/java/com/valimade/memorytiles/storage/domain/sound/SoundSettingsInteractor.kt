package com.valimade.memorytiles.storage.domain.sound

import com.valimade.memorytiles.sound.domain.model.UsingSounds
import com.valimade.memorytiles.storage.data.sound.SoundStore
import kotlinx.coroutines.flow.Flow

class SoundSettingsInteractor(
    val soundStore: SoundStore
) {
    suspend fun saveUsageStatus(usingSounds: UsingSounds) {
        soundStore.saveUsageStatus(usingSounds)
    }

    fun getUsageStatus(): Flow<UsingSounds> {
        return soundStore.getUsageStatus()
    }
}