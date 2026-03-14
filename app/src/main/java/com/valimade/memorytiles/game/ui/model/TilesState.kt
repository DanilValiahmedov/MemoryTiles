package com.valimade.memorytiles.game.ui.model

import androidx.compose.ui.graphics.Color
import com.valimade.memorytiles.R
import com.valimade.memorytiles.settings.data.shape.ShapeTiles
import com.valimade.memorytiles.core.ui.theme.EmphasisGrayTheme

data class TilesState(
    val backgroundImage: Int = R.drawable.back_gray,
    val emphasisColor: Color = EmphasisGrayTheme,
    val score: Int = 0,
    val bestScore: Int = 0,
    var tiles: List<TileUi> = emptyList(),
    val shapeTiles: ShapeTiles = ShapeTiles.SQUARE,
    val gameSequence: List<Int> = emptyList(),
    val isEnabledTiles: Boolean = false,
    val informMessage: Int = R.string.empty,
    val showRepeatButton: Boolean = false,
    val showSteps: Boolean = false,
    val currentStepPerRound: Int = 0,
    val maxStepsPerRound: Int = 0,
)