package com.valimade.memorytiles.storage.data.sound

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.valimade.memorytiles.sound.domain.model.UsingSounds
import com.valimade.memorytiles.storage.data.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SoundStore(
    private val context: Context,
    private val keys: SoundKeys,
) {

    suspend fun saveUsageStatus(usingSounds: UsingSounds) {
        context.dataStore.edit { prefs ->
            prefs[keys.CURRENT_USAGE_STATUS] = usingSounds.name
        }
    }

    fun getUsageStatus(): Flow<UsingSounds> {
        return context.dataStore.data.map { prefs ->
            val result = prefs[keys.CURRENT_USAGE_STATUS] ?: UsingSounds.IS_USED.name
            UsingSounds.valueOf(result)
        }
    }

}