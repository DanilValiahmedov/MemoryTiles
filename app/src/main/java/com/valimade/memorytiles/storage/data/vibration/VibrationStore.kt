package com.valimade.memorytiles.storage.data.vibration

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.valimade.memorytiles.storage.data.dataStore
import com.valimade.memorytiles.vibration.domain.model.UsingVibration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VibrationStore(
    private val context: Context,
    private val keys: VibrationKeys,
) {

    suspend fun saveUsageStatusVibration(usingVibration: UsingVibration) {
        context.dataStore.edit { prefs ->
            prefs[keys.CURRENT_USAGE_STATUS_VIBRATION] = usingVibration.name
        }
    }

    fun getUsageStatusVibration(): Flow<UsingVibration> {
        return context.dataStore.data.map { prefs ->
            val result = prefs[keys.CURRENT_USAGE_STATUS_VIBRATION] ?: UsingVibration.IS_USED.name
            UsingVibration.valueOf(result)
        }
    }

}