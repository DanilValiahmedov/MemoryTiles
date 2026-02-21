package com.valimade.memorytiles.game.domain.usecase

import com.valimade.memorytiles.game.domain.entity.GameEngine

class CreatureTileSectionUseCase(private val gameEngine: GameEngine) {
    operator fun invoke(): List<Int> {
        return gameEngine.creatureGameTileSection()
    }
}