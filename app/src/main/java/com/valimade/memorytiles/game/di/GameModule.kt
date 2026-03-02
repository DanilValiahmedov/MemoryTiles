package com.valimade.memorytiles.game.di

import com.valimade.memorytiles.game.data.entity.FieldBuilder
import com.valimade.memorytiles.game.data.utils.Palette
import com.valimade.memorytiles.game.domain.entity.GameEngine
import com.valimade.memorytiles.game.domain.interactor.GameInteractor
import com.valimade.memorytiles.game.ui.mapper.TileMapper
import com.valimade.memorytiles.game.ui.viewmodel.GameViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val gameModule = module {

    //Utils
    single { Palette }

    //Entity
    single { FieldBuilder(palette = get()) }
    single { GameEngine(fieldBuilder = get()) }

    //Interactor
    single { GameInteractor(gameEngine = get()) }

    //Mapper
    single { TileMapper }

    //viewModel
    viewModel {
        GameViewModel(
            tileMapper = get(),
            gameInteractor = get(),
            scoreInteractor = get(),
            themeInteractor = get(),
        )
    }

}