package com.valimade.memorytiles.sound.domain.entity

import android.media.AudioAttributes
import android.media.SoundPool
import com.valimade.memorytiles.game.domain.model.DifficultyLevel
import com.valimade.memorytiles.sound.data.entity.SoundLibrary
import com.valimade.memorytiles.sound.data.utils.CalculationRate

class SoundManager(
    private val calculationRate: CalculationRate,
    private val soundLibrary: SoundLibrary,
) {

    private val soundPool: SoundPool
    private val soundId: Int
    private var rateList: List<Float> = emptyList()

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()

        soundId = soundLibrary.loadSound(soundPool)
    }

    fun startSound(difficultyLevel: DifficultyLevel) {
        rateList = calculationRate.getListRate(difficultyLevel)
    }

    fun playClick(rateIndex: Int) {
        try {
            val rate = rateList[rateIndex]
            soundPool.play(soundId, 1f, 1f, 1, 0, rate)
        } catch (e: Exception) {
            soundPool.play(soundId, 1f, 1f, 1, 0, 1f)
        }

    }

    fun release() {
        soundPool.release()
    }
}