package com.valimade.memorytiles.game.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.valimade.memorytiles.settings.data.shape.ShapeTiles

@Composable
fun Tile(
    isActive: Boolean,
    sideSize: Dp,
    shapeTiles: ShapeTiles,
    colorTileActive: Color,
    colorTileInactive: Color,
    colorBorderActive: Color,
    colorBorderInactive: Color,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val roundedCornerShape = when(shapeTiles) {
        ShapeTiles.SQUARE -> RoundedCornerShape(20.dp)
        ShapeTiles.ROUND -> RoundedCornerShape(sideSize/2)
    }

    val scale by animateFloatAsState(
        targetValue = when {
            isPressed -> 0.92f
            isActive -> 1.05f
            else -> 1f
        },
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isActive) colorTileActive else colorTileInactive,
    )

    val borderColor by animateColorAsState(
        targetValue = if (isActive) colorBorderActive else colorBorderInactive,
    )

    val elevation by animateDpAsState(
        targetValue = if (isActive) 16.dp else 6.dp,
    )

    val borderWidth = 4.dp

    Box(
        modifier = Modifier
            .size(sideSize)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                shadowElevation = elevation.toPx()
                shape = roundedCornerShape
                clip = true
            }
            .padding(borderWidth / 2)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        backgroundColor,
                        backgroundColor.copy(alpha = 0.85f)
                    )
                ),
                shape = roundedCornerShape,
            )
            .border(
                width = borderWidth,
                color = borderColor,
                shape = roundedCornerShape,
            )
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                onClick = onClick
            )
    )
}