package com.valimade.memorytiles.sound.domain.interactor

import com.valimade.memorytiles.game.domain.model.DifficultyLevel
import com.valimade.memorytiles.sound.domain.entity.SoundManager
import com.valimade.memorytiles.sound.domain.model.UsingSounds
import com.valimade.memorytiles.storage.domain.sound.SoundSettingsInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SoundInteractor(
    private val soundManager: SoundManager,
    private val soundSettings: SoundSettingsInteractor,
    private val scope: CoroutineScope,
) {

    private var isSoundEnabled: Boolean = true

    init {
        scope.launch {
            soundSettings.getUsageStatusSound().collect { status ->
                isSoundEnabled = (status == UsingSounds.IS_USED)
            }
        }
    }

    fun prepareRates(difficulty: DifficultyLevel) {
        if (isSoundEnabled) {
            soundManager.prepareRates(difficulty)
        }
    }

    fun playClick(rateIndex: Int) {
        if (isSoundEnabled) {
            soundManager.playClick(rateIndex)
        }
    }

    fun release() {
        if (isSoundEnabled) {
            soundManager.release()
        }
    }

}