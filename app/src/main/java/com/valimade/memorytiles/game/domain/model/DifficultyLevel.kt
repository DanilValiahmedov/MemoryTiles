package com.valimade.memorytiles.game.domain.model

enum class DifficultyLevel(
    val title: String,
    val sideLength: Int,
    val quantities: Int,
) {
    EASY("Легкий", 2, 4),
    AVERAGE("Средний", 3, 9),
    DIFFICULT("Сложный", 4, 16),
}