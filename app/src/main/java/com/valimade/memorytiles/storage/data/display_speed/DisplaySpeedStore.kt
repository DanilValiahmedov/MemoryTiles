package com.valimade.memorytiles.storage.data.display_speed

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.valimade.memorytiles.settings.data.display_speed.DisplaySpeed
import com.valimade.memorytiles.storage.data.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DisplaySpeedStore(
    private val context: Context,
    private val keys: DisplaySpeedKeys,
) {

    suspend fun saveDisplaySpeed(displaySpeed: DisplaySpeed) {
        context.dataStore.edit { prefs ->
            prefs[keys.CURRENT_DISPLAY_SPEED] = displaySpeed.name
        }
    }

    fun getDisplaySpeed(): Flow<DisplaySpeed> {
        return context.dataStore.data.map { prefs ->
            val result = prefs[keys.CURRENT_DISPLAY_SPEED] ?: DisplaySpeed.MEASURED.name
            DisplaySpeed.valueOf(result)
        }
    }

}