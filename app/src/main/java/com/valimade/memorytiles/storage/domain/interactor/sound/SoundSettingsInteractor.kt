package com.valimade.memorytiles.storage.domain.interactor.sound

import com.valimade.memorytiles.sound.domain.model.UsingSounds
import com.valimade.memorytiles.storage.data.sound.SoundStore
import kotlinx.coroutines.flow.Flow

class SoundSettingsInteractor(
    val soundStore: SoundStore
) {
    suspend fun saveUsageStatusSound(usingSounds: UsingSounds) {
        soundStore.saveUsageStatusSound(usingSounds)
    }

    fun getUsageStatusSound(): Flow<UsingSounds> {
        return soundStore.getUsageStatusSound()
    }
}