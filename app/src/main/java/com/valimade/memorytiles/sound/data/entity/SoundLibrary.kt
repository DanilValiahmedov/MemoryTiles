package com.valimade.memorytiles.sound.data.entity

import android.content.Context
import android.media.SoundPool
import com.valimade.memorytiles.R

class SoundLibrary(
    private val context: Context
) {
    fun loadSound(soundPool: SoundPool): Int {
        return soundPool.load(context, R.raw.tiles, 1)
    }
}