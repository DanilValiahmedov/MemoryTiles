package com.valimade.memorytiles.game.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.valimade.memorytiles.game.ui.model.TilesState

@Composable
fun TileGrid(
    gridSize: Int,
    tiles: List<TilesState>,
    onTileClick: (TilesState) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: Dp = 16.dp,
    spacing: Dp = 8.dp,
) {

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = contentPadding)
    ) {
        val totalSpacing = spacing * (gridSize - 1)
        val tileSize = (maxWidth - totalSpacing) / gridSize

        LazyVerticalGrid(
            columns = GridCells.Fixed(gridSize),
            verticalArrangement = Arrangement.spacedBy(spacing),
            horizontalArrangement = Arrangement.spacedBy(spacing)
        ) {
            items(tiles) { tile ->
                Tile(
                    isActive = tile.isActive,
                    sideSize = tileSize,
                    colorTileActive = tile.colorTileActive,
                    colorTileInactive = tile.colorTileInactive,
                    colorBorderActive = tile.colorBorderActive,
                    colorBorderInactive = tile.colorBorderInactive,
                    onClick = { onTileClick(tile) }
                )
            }
        }
    }
}
