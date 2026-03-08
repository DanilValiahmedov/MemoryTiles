package com.valimade.memorytiles.sound.di

import com.valimade.memorytiles.sound.data.entity.SoundLibrary
import com.valimade.memorytiles.sound.data.utils.CalculationRate
import com.valimade.memorytiles.sound.domain.entity.SoundManager
import com.valimade.memorytiles.sound.domain.interactor.SoundInteractor
import org.koin.dsl.module

val soundModule = module {

    //Utils
    single { CalculationRate }

    //Entity
    single { SoundLibrary(context = get()) }
    single { SoundManager(calculationRate = get(), soundLibrary = get()) }

    //Interactor
    single { SoundInteractor(soundManager = get()) }

}