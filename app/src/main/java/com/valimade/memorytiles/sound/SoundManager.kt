package com.valimade.memorytiles.sound

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.util.Log
import com.valimade.memorytiles.R

class SoundManager(context: Context) {

    private val soundPool: SoundPool
    private var soundId: Int = 0
    private var isLoaded = false

    var rate = 0.6f

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()

        soundId = soundPool.load(context, R.raw.tiles, 1)

        soundPool.setOnLoadCompleteListener { _, _, status ->
            if (status == 0) {
                isLoaded = true
            }
        }
    }

    fun playClick() {
        if (isLoaded) {
            soundPool.play(soundId, 1f, 1f, 1, 0, rate)
            rate = rate + 0.1f
        }
    }

    fun release() {
        soundPool.release()
    }
}