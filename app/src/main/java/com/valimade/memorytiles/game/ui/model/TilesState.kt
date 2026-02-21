package com.valimade.memorytiles.game.ui.model

data class TilesState(
    val score: Int = 0,
    var tiles: List<TileUi> = emptyList(),
    val gameSequence: List<Int> = emptyList(),
    val isEnabledTiles: Boolean = true,
    val informMessage: String = "",
    val showRepeatButton: Boolean = false,
)