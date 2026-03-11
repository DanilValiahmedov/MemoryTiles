package com.valimade.memorytiles.storage.di

import com.valimade.memorytiles.storage.data.display_speed.DisplaySpeedKeys
import com.valimade.memorytiles.storage.data.display_speed.DisplaySpeedStore
import com.valimade.memorytiles.storage.data.score.ScoreStore
import com.valimade.memorytiles.storage.data.score.ScoreKeys
import com.valimade.memorytiles.storage.data.shape.ShapeKeys
import com.valimade.memorytiles.storage.data.shape.ShapeStore
import com.valimade.memorytiles.storage.data.sound.SoundKeys
import com.valimade.memorytiles.storage.data.sound.SoundStore
import com.valimade.memorytiles.storage.data.theme.ThemeKeys
import com.valimade.memorytiles.storage.data.theme.ThemeStore
import com.valimade.memorytiles.storage.data.vibration.VibrationKeys
import com.valimade.memorytiles.storage.data.vibration.VibrationStore
import com.valimade.memorytiles.storage.domain.display_speed.DisplaySpeedInteractor
import com.valimade.memorytiles.storage.domain.score.ScoreInteractor
import com.valimade.memorytiles.storage.domain.shape.ShapeInteractor
import com.valimade.memorytiles.storage.domain.sound.SoundSettingsInteractor
import com.valimade.memorytiles.storage.domain.theme.ThemeInteractor
import com.valimade.memorytiles.storage.domain.vibration.VibrationSettingsInteractor
import org.koin.dsl.module

val storageModule = module {

    //Score
    single { ScoreKeys }
    single { ScoreStore(context = get(), keys = get()) }
    single { ScoreInteractor(scoreStore = get()) }

    //Theme
    single { ThemeKeys }
    single { ThemeStore(context = get(), keys = get()) }
    single { ThemeInteractor(themeStore = get()) }

    //Shape
    single { ShapeKeys }
    single { ShapeStore(context = get(), keys = get()) }
    single { ShapeInteractor(shapeStore = get()) }

    //Sound
    single { SoundKeys }
    single { SoundStore(context = get(), keys = get()) }
    single { SoundSettingsInteractor(soundStore = get()) }

    //Display Speed
    single { DisplaySpeedKeys }
    single { DisplaySpeedStore(context = get(), keys = get()) }
    single { DisplaySpeedInteractor(displaySpeedStore = get()) }

    //Vibration
    single { VibrationKeys }
    single { VibrationStore(context = get(), keys = get()) }
    single { VibrationSettingsInteractor(vibrationStore = get()) }

}