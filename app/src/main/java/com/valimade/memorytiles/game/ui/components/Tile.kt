package com.valimade.memorytiles.game.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Tile(
    isActive: Boolean,
    sideSize: Dp,
    colorTileActive: Color,
    colorTileInactive: Color,
    colorBorderActive: Color,
    colorBorderInactive: Color,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .width(sideSize)
            .height(sideSize)
            .background(
                color = if(isActive) colorTileActive
                    else colorTileInactive,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 5.dp,
                color = if(isActive) colorBorderActive
                    else colorBorderInactive,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) {
                onClick()
            }
    )
}