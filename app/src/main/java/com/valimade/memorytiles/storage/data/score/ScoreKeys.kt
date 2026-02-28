package com.valimade.memorytiles.storage.data.score

import androidx.datastore.preferences.core.intPreferencesKey

object ScoreKeys {
    val BEST_SCORE_EASY = intPreferencesKey("best_score_easy")
    val BEST_SCORE_AVERAGE = intPreferencesKey("best_score_average")
    val BEST_SCORE_DIFFICULT = intPreferencesKey("best_score_difficult")
}

