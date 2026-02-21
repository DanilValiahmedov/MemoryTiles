package com.valimade.memorytiles.game.domain.entity

import com.valimade.memorytiles.game.domain.model.DifficultyLevel
import com.valimade.memorytiles.game.domain.model.GameResult
import com.valimade.memorytiles.game.domain.model.Tile
import com.valimade.memorytiles.game.domain.model.TileColors

class GameEngine(
    val fieldBuilder: FieldBuilder,
) {
    var difficultyLevel = DifficultyLevel.EASY
    var playingField: List<Tile> = emptyList()
    val gameSequence: MutableList<Int> = mutableListOf()
    val playerSequence: MutableList<Int> = mutableListOf()

    fun startGame(difficulty: DifficultyLevel, colorSelection: TileColors): List<Tile> {
        difficultyLevel = difficulty
        playingField = fieldBuilder.fieldConstruction(difficultyLevel, colorSelection)
        return playingField
    }

    fun selectGameTileSection(): List<Int> {
        val newSection = (1..difficultyLevel.quantities).random() - 1
        gameSequence.add(newSection)
        return gameSequence
    }

    fun checkPlayerSequence(selectedTile: Int): GameResult {
        playerSequence.add(selectedTile)
        val lastIndex = playerSequence.lastIndex
        return if(gameSequence[lastIndex] != selectedTile) GameResult.Wrong
        else  {
            if(playerSequence.size != gameSequence.size) GameResult.Correct
            else {
                playerSequence.clear()
                GameResult.LevelCompleted
            }
        }
    }

    fun refreshGame() {
        gameSequence.clear()
        playerSequence.clear()
    }

}