package com.valimade.memorytiles.storage.domain.color_tile

import com.valimade.memorytiles.game.domain.model.TileColors
import com.valimade.memorytiles.storage.data.color_tile.ColorTileStore
import kotlinx.coroutines.flow.Flow

class ColorTileInteractor(
    val colorTileStore: ColorTileStore,
) {
    suspend fun saveColorTile(tileColors: TileColors) {
        colorTileStore.saveColorTile(tileColors)
    }

    fun getColorTile(): Flow<TileColors> {
        return colorTileStore.getColorTile()
    }
}