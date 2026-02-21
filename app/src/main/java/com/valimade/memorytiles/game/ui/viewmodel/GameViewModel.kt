package com.valimade.memorytiles.game.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valimade.memorytiles.R
import com.valimade.memorytiles.game.domain.model.DifficultyLevel
import com.valimade.memorytiles.game.domain.model.GameResult
import com.valimade.memorytiles.game.domain.model.TileColors
import com.valimade.memorytiles.game.domain.usecase.CheckPlayerSequenceUseCase
import com.valimade.memorytiles.game.domain.usecase.CreatureTileSectionUseCase
import com.valimade.memorytiles.game.domain.usecase.RefreshGameUseCase
import com.valimade.memorytiles.game.domain.usecase.StartGameUseCase
import com.valimade.memorytiles.game.ui.mapper.TileMapper
import com.valimade.memorytiles.game.ui.model.TilesState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(
    private val tileMapper: TileMapper,
    private val startGameUseCase: StartGameUseCase,
    private val creatureTileSectionUseCase: CreatureTileSectionUseCase,
    private val checkPlayerSequenceUseCase: CheckPlayerSequenceUseCase,
    private val refreshGameUseCase: RefreshGameUseCase,
): ViewModel() {
    private val _tileState = MutableStateFlow(TilesState())
    val tileState = _tileState.asStateFlow()

    init {
        val listTilesDomain = startGameUseCase(DifficultyLevel.AVERAGE, TileColors.RED)
        val listTilesUi = listTilesDomain.map { domainTile ->
            tileMapper.domainToUi(domainTile)
        }
        _tileState.update {
            it.copy(
                tiles = listTilesUi,
                gameSequence = creatureTileSectionUseCase()
            )
        }

        showGameTiles()
    }

    fun showGameTiles() {
         viewModelScope.launch {
             _tileState.update {
                 it.copy(
                     enabledTiles = false,
                     informMessage = R.string.remember.toString(),
                 )
             }

             for (i in _tileState.value.gameSequence) {

                 val activeTile = _tileState.value.tiles[i].copy(
                     isActive = true
                 )
                 val newList = _tileState.value.tiles.toMutableList()
                 newList[i] = activeTile

                 _tileState.update {
                     it.copy(
                         tiles = newList
                     )
                 }

                 delay(1000)

                 newList[i] = activeTile.copy(isActive = false)

                 _tileState.update {
                     it.copy(
                         tiles = newList
                     )
                 }

                 delay(500)

             }

             _tileState.update {
                 it.copy(
                     enabledTiles = true,
                     informMessage = R.string.repeat.toString(),
                 )
             }
         }
    }

    fun playerTileSelection(selectedTile: Int) {
        viewModelScope.launch {
            val activeTile = _tileState.value.tiles[selectedTile].copy(
                isActive = true
            )
            val newList = _tileState.value.tiles.toMutableList()
            newList[selectedTile] = activeTile

            _tileState.update {
                it.copy(
                    tiles = newList
                )
            }

            delay(300)

            newList[selectedTile] = activeTile.copy(isActive = false)

            _tileState.update {
                it.copy(
                    tiles = newList
                )
            }

            when(checkPlayerSequenceUseCase(selectedTile)) {
                GameResult.Correct -> {}
                GameResult.Wrong -> {
                    showBlinkingWrong()

                    _tileState.update {
                        it.copy(
                            informMessage = selectMessageWrong(),
                            showRepeatButton = true,
                        )
                    }
                }
                GameResult.LevelCompleted -> {
                    _tileState.update {
                        it.copy(
                            record = it.record + 1,
                            informMessage = selectMessageCompleteLevel(),
                        )
                    }
                }
            }
        }
    }

    private fun selectMessageCompleteLevel(): String {
        return when((1..5).random()) {
            1 -> R.string.successfully.toString()
            2 -> R.string.wonderfully.toString()
            3 -> R.string.great.toString()
            4 -> R.string.excellently.toString()
            5 -> R.string.perfectly.toString()
            else -> R.string.successfully.toString()
        }
    }

    private fun selectMessageWrong(): String {
        return when((1..4).random()) {
            1 -> R.string.mistake.toString()
            2 -> R.string.incorrect.toString()
            3 -> R.string.wrong.toString()
            4 -> R.string.sorry.toString()
            else -> R.string.mistake.toString()
        }
    }

    private suspend fun showBlinkingWrong() {
        repeat(3) {
            for (i in 0 until _tileState.value.tiles.size) {

                val activeTile = _tileState.value.tiles[i].copy(
                    isActive = true
                )
                val newList = _tileState.value.tiles.toMutableList()
                newList[i] = activeTile

                _tileState.update {
                    it.copy(
                        tiles = newList
                    )
                }

                delay(300)
            }

            for (i in 0 until _tileState.value.tiles.size) {

                val activeTile = _tileState.value.tiles[i].copy(
                    isActive = false
                )
                val newList = _tileState.value.tiles.toMutableList()
                newList[i] = activeTile

                _tileState.update {
                    it.copy(
                        tiles = newList
                    )
                }

                delay(300)
            }
        }

    }

    fun refreshGame() {
        viewModelScope.launch {
            refreshGameUseCase()

            _tileState.update {
                it.copy(
                    informMessage = R.string.restart.toString(),
                    showRepeatButton = false,
                )
            }

            delay(1000)

            _tileState.update {
                it.copy(
                    record = 0,
                    gameSequence = creatureTileSectionUseCase()
                )
            }

            showGameTiles()
        }

    }

}