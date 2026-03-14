package com.valimade.memorytiles.storage.domain.interactor.score

import com.valimade.memorytiles.game.domain.model.DifficultyLevel
import com.valimade.memorytiles.storage.data.score.ScoreStore
import kotlinx.coroutines.flow.Flow

class ScoreInteractor(
    val scoreStore: ScoreStore
) {
    suspend fun saveScoreIfBest(difficultyLevel: DifficultyLevel, score: Int) {
        scoreStore.saveScoreIfBest(difficultyLevel, score)
    }

    fun getScore(difficultyLevel: DifficultyLevel): Flow<Int> {
        return scoreStore.getScore(difficultyLevel)
    }
}