package com.valimade.memorytiles.vibration.di

import com.valimade.memorytiles.vibration.data.entity.VibrationManager
import com.valimade.memorytiles.vibration.domain.usecase.VibrateClickUseCase
import org.koin.dsl.module

val vibrationModule = module {

    //Entity
    single { VibrationManager(context = get()) }

    //UseCase
    single { VibrateClickUseCase(vibrationManager = get()) }

}