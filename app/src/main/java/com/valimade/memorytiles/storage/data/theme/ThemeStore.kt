package com.valimade.memorytiles.storage.data.theme

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.valimade.memorytiles.settings.data.theme.ThemeGame
import com.valimade.memorytiles.storage.data.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeStore(
    private val context: Context,
    private val keys: ThemeKeys,
) {

    suspend fun saveTheme(theme: ThemeGame) {
        context.dataStore.edit { prefs ->
            prefs[keys.CURRENT_THEME] = theme.name
        }
    }

    fun getTheme(): Flow<ThemeGame> {
        return context.dataStore.data.map { prefs ->
            val result = prefs[keys.CURRENT_THEME] ?: ThemeGame.GRAY.name
            ThemeGame.valueOf(result)
        }
    }

}