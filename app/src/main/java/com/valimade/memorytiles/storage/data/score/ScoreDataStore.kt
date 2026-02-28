package com.valimade.memorytiles.storage.data.score

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.valimade.memorytiles.game.domain.model.DifficultyLevel
import com.valimade.memorytiles.storage.data.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ScoreDataStore(
    private val context: Context,
    private val keys: ScoreKeys,

) {

    private fun keyForDifficulty(difficulty: DifficultyLevel) =
        when (difficulty) {
            DifficultyLevel.EASY -> keys.BEST_SCORE_EASY
            DifficultyLevel.AVERAGE -> keys.BEST_SCORE_AVERAGE
            DifficultyLevel.DIFFICULT -> keys.BEST_SCORE_DIFFICULT
        }

    suspend fun saveScoreIfBest(difficultyLevel: DifficultyLevel, score: Int) {
        context.dataStore.edit { prefs ->
            val key = keyForDifficulty(difficultyLevel)
            val currentBest = prefs[key] ?: 0

            if (score > currentBest) {
                prefs[key] = score
            }
            prefs[keyForDifficulty(difficultyLevel)] = score
        }
    }

    fun getScore(difficultyLevel: DifficultyLevel): Flow<Int> {
        return context.dataStore.data.map { prefs ->
            val key = keyForDifficulty(difficultyLevel)
            prefs[key] ?: 0
        }
    }

}