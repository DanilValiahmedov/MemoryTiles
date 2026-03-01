package com.valimade.memorytiles.game.di

import com.valimade.memorytiles.game.data.entity.FieldBuilder
import com.valimade.memorytiles.game.data.utils.Palette
import com.valimade.memorytiles.game.domain.entity.GameEngine
import com.valimade.memorytiles.game.domain.usecase.CheckPlayerSequenceUseCase
import com.valimade.memorytiles.game.domain.usecase.CreatureTileSectionUseCase
import com.valimade.memorytiles.game.domain.usecase.RefreshGameUseCase
import com.valimade.memorytiles.game.domain.usecase.StartGameUseCase
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

    //UseCase
    single { StartGameUseCase(gameEngine = get()) }
    single { CreatureTileSectionUseCase(gameEngine = get()) }
    single { CheckPlayerSequenceUseCase(gameEngine = get()) }
    single { RefreshGameUseCase(gameEngine = get()) }

    //Mapper
    single { TileMapper }

    //viewModel
    viewModel {
        GameViewModel(
            tileMapper = get(),
            startGameUseCase = get(),
            creatureTileSectionUseCase = get(),
            checkPlayerSequenceUseCase = get(),
            refreshGameUseCase = get(),
            scoreInteractor = get(),
        )
    }

}