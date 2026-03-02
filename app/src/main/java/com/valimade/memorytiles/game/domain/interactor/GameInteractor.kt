package com.valimade.memorytiles.game.domain.interactor

import com.valimade.memorytiles.game.domain.entity.GameEngine
import com.valimade.memorytiles.game.domain.model.DifficultyLevel
import com.valimade.memorytiles.game.domain.model.GameResult
import com.valimade.memorytiles.game.domain.model.Tile
import com.valimade.memorytiles.game.domain.model.TileColors

class GameInteractor(private val gameEngine: GameEngine) {

    fun startGame(difficulty: DifficultyLevel, colorSelection: TileColors): List<Tile> {
        return gameEngine.startGame(difficulty, colorSelection)
    }

    fun creatureGameTileSection(): List<Int> {
        return gameEngine.creatureGameTileSection()
    }

    fun checkPlayerSequence(selectedTile: Int): GameResult {
        return gameEngine.checkPlayerSequence(selectedTile)
    }

    fun refreshGame() {
        return gameEngine.refreshGame()
    }

}
