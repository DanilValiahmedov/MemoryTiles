package com.valimade.memorytiles.game.domain.model

sealed class GameResult {
    object Correct : GameResult()
    object Wrong : GameResult()
    object LevelCompleted : GameResult()
}