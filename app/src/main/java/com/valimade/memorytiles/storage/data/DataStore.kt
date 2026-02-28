package com.valimade.memorytiles.storage.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(
    name = "game_prefs"
)
