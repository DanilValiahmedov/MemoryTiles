package com.valimade.memorytiles.storage.domain.score

import com.valimade.memorytiles.game.domain.model.DifficultyLevel
import com.valimade.memorytiles.storage.data.score.ScoreDataStore
import kotlinx.coroutines.flow.Flow

class ScoreInteractor(
    val scoreDataStore: ScoreDataStore
) {
    suspend fun saveScoreIfBest(difficultyLevel: DifficultyLevel, score: Int) {
        scoreDataStore.saveScoreIfBest(difficultyLevel, score)
    }

    fun getScore(difficultyLevel: DifficultyLevel): Flow<Int> {
        return scoreDataStore.getScore(difficultyLevel)
    }

}