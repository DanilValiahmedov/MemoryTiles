package com.valimade.memorytiles.sound.domain.interactor

import com.valimade.memorytiles.game.domain.model.DifficultyLevel
import com.valimade.memorytiles.sound.domain.entity.SoundManager

class SoundInteractor(private val soundManager: SoundManager) {

    fun prepareRates(difficulty: DifficultyLevel) {
        return soundManager.prepareRates(difficulty)
    }

    fun playClick(rateIndex: Int) {
        return soundManager.playClick(rateIndex)
    }

    fun release() {
        return soundManager.release()
    }

}