package com.valimade.memorytiles.storage.domain.theme

import com.valimade.memorytiles.settings.data.theme.ThemeGame
import com.valimade.memorytiles.storage.data.theme.ThemeStore
import kotlinx.coroutines.flow.Flow

class ThemeInteractor(
    val themeStore: ThemeStore
) {
    suspend fun saveTheme(theme: ThemeGame) {
        themeStore.saveTheme(theme)
    }

    fun getTheme(): Flow<ThemeGame> {
        return themeStore.getTheme()
    }
}