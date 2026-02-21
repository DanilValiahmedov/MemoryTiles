package com.valimade.memorytiles.game.domain.usecase

import com.valimade.memorytiles.game.domain.entity.GameEngine

class RefreshGameUseCase(private val gameEngine: GameEngine) {
    operator fun invoke() {
        return gameEngine.refreshGame()
    }
}