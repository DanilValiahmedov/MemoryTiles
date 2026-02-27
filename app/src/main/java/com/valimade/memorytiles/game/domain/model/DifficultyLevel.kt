package com.valimade.memorytiles.game.domain.model

enum class DifficultyLevel(val sideLength: Int, val quantities: Int,) {
    EASY(2, 4),
    AVERAGE(3, 9),
    DIFFICULT(4, 16),
}