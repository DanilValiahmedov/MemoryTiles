package com.valimade.memorytiles.game.domain.usecase

import com.valimade.memorytiles.game.domain.entity.GameEngine
import com.valimade.memorytiles.game.domain.model.DifficultyLevel
import com.valimade.memorytiles.game.domain.model.Tile
import com.valimade.memorytiles.game.domain.model.TileColors

class StartGameUseCase(private val gameEngine: GameEngine) {
    operator fun invoke(difficulty: DifficultyLevel, colorSelection: TileColors): List<Tile> {
        return gameEngine.startGame(difficulty, colorSelection)
    }
}