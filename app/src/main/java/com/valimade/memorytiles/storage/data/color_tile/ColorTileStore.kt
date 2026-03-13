package com.valimade.memorytiles.storage.data.color_tile

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.valimade.memorytiles.game.domain.model.TileColors
import com.valimade.memorytiles.storage.data.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ColorTileStore(
    private val context: Context,
    private val keys: ColorTileKeys,
) {

    suspend fun saveColorTile(tileColors: TileColors) {
        context.dataStore.edit { prefs ->
            prefs[keys.CURRENT_COLOR_TILE] = tileColors.name
        }
    }

    fun getColorTile(): Flow<TileColors> {
        return context.dataStore.data.map { prefs ->
            val result = prefs[keys.CURRENT_COLOR_TILE] ?: TileColors.MULTICOLORED.name
            TileColors.valueOf(result)
        }
    }

}