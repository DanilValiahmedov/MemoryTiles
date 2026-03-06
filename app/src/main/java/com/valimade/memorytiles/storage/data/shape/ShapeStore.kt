package com.valimade.memorytiles.storage.data.shape

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.valimade.memorytiles.settings.data.shape.ShapeTiles
import com.valimade.memorytiles.storage.data.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShapeStore(
    private val context: Context,
    private val keys: ShapeKeys,
) {

    suspend fun saveShape(shape: ShapeTiles) {
        context.dataStore.edit { prefs ->
            prefs[keys.CURRENT_SHAPE] = shape.name
        }
    }

    fun getShape(): Flow<ShapeTiles> {
        return context.dataStore.data.map { prefs ->
            val result = prefs[keys.CURRENT_SHAPE] ?: ShapeTiles.SQUARE.name
            ShapeTiles.valueOf(result)
        }
    }

}