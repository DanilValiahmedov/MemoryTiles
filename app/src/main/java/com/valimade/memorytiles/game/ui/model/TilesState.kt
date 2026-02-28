package com.valimade.memorytiles.game.ui.model

import com.valimade.memorytiles.R

data class TilesState(
    val score: Int = 0,
    var tiles: List<TileUi> = emptyList(),
    val gameSequence: List<Int> = emptyList(),
    val isEnabledTiles: Boolean = false,
    val informMessage: Int = R.string.empty,
    val showRepeatButton: Boolean = false,
    val showSteps: Boolean = false,
    val currentStepPerRound: Int = 0,
    val maxStepsPerRound: Int = 0,
)