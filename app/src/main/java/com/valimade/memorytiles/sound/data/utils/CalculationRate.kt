package com.valimade.memorytiles.sound.data.utils

import com.valimade.memorytiles.game.domain.model.DifficultyLevel

const val GAP_BETWEEN_RATE_EASY = 0.4f
const val GAP_BETWEEN_RATE_AVERAGE = 0.17f
const val GAP_BETWEEN_RATE_DIFFICULT = 0.1f

object CalculationRate {

    fun getListRate(difficulty: DifficultyLevel): List<Float> {
        val initialRate = 0.6f
        val quantity = difficulty.quantities

        return when(difficulty){
            DifficultyLevel.EASY -> {
                List(quantity) { initialRate + it * GAP_BETWEEN_RATE_EASY }
            }
            DifficultyLevel.AVERAGE -> {
                List(quantity) { initialRate + it * GAP_BETWEEN_RATE_AVERAGE}
            }
            DifficultyLevel.DIFFICULT -> {
                List(quantity) { initialRate + it * GAP_BETWEEN_RATE_DIFFICULT }
            }
        }
    }

}