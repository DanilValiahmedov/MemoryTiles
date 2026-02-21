package com.valimade.memorytiles.game.ui.model

import androidx.compose.ui.graphics.Color
import com.valimade.memorytiles.ui.theme.BorderActive
import com.valimade.memorytiles.ui.theme.BorderInactive

data class TilesState(
    val record: Int = 0,
    val tiles: List<TileUi> = emptyList(),
    val gameSequence: List<Int> = emptyList(),
    val enabledTiles: Boolean = true,
    val informMessage: String = "",
    val showRepeatButton: Boolean = false,
)