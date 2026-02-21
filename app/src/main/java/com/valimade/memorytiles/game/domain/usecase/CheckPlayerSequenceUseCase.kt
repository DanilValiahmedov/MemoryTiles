package com.valimade.memorytiles.game.domain.usecase

import com.valimade.memorytiles.game.domain.entity.GameEngine
import com.valimade.memorytiles.game.domain.model.GameResult

class CheckPlayerSequenceUseCase(private val gameEngine: GameEngine) {
    operator fun invoke(selectedTile: Int): GameResult {
        return gameEngine.checkPlayerSequence(selectedTile)
    }
}