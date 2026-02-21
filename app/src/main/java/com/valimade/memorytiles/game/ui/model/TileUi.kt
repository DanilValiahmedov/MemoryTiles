package com.valimade.memorytiles.game.ui.model

import androidx.compose.ui.graphics.Color
import com.valimade.memorytiles.ui.theme.BorderActive
import com.valimade.memorytiles.ui.theme.BorderInactive

data class TileUi(
    val isActive: Boolean = false,
    val colorTileActive: Color,
    val colorTileInactive: Color,
    val colorBorderActive: Color = BorderActive,
    val colorBorderInactive: Color = BorderInactive,
)