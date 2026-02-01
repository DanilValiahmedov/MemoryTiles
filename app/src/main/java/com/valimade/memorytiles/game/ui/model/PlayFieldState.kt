package com.valimade.memorytiles.game.ui.model

data class PlayFieldState(
    val fieldSize: Int,
    val tileSize: Int,
    val listTilesState: List<TilesState>
)