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

const val TILES_ACTIVE_GAME_TIME = 200L
const val TILES_INACTIVE_GAME_TIME = 300L
const val TAP_PLAYER_TIME = 200L
const val LEVEL_COMPLETED_TIME = 1000L
const val QUANTITY_REPEAT_AN_ERROR = 3
const val BLINKING_WRONG_TIME = 200L
const val REFRESH_TIME_GAME = 1000L

class GameViewModel(
    private val tileMapper: TileMapper,
    private val startGameUseCase: StartGameUseCase,
    private val creatureTileSectionUseCase: CreatureTileSectionUseCase,
    private val checkPlayerSequenceUseCase: CheckPlayerSequenceUseCase,
    private val refreshGameUseCase: RefreshGameUseCase,
): ViewModel() {
    private val _tileState = MutableStateFlow(TilesState())
    val tileState = _tileState.asStateFlow()

    fun startGame(difficulty: DifficultyLevel, colorSelection: TileColors) {
        val listTilesDomain = startGameUseCase(difficulty, colorSelection)
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
                     isEnabledTiles = false,
                     informMessage = R.string.remember,
                 )
             }

             for (i in _tileState.value.gameSequence) {
                 mutableListTiles(i,true)

                 delay(TILES_ACTIVE_GAME_TIME)

                 mutableListTiles(i,false)

                 if(i != _tileState.value.gameSequence.last()) delay(TILES_INACTIVE_GAME_TIME)
             }

             _tileState.update {
                 it.copy(
                     isEnabledTiles = true,
                     informMessage = R.string.repeat,
                 )
             }
         }
    }

    fun mutableListTiles(index: Int, isActive: Boolean) {
        _tileState.update { state ->
            val newList = state.tiles.toMutableList()
            newList[index] = state.tiles[index].copy(isActive = isActive)
            state.copy(tiles = newList)
        }
    }

    fun playerTileSelection(selectedTile: Int) {
        viewModelScope.launch {
            _tileState.update { it.copy(isEnabledTiles = false) }

            mutableListTiles(selectedTile,true)

            delay(TAP_PLAYER_TIME)

            mutableListTiles(selectedTile,false)

            when(checkPlayerSequenceUseCase(selectedTile)) {
                GameResult.Correct -> {
                    _tileState.update { it.copy(isEnabledTiles = true) }
                }
                GameResult.Wrong -> {
                    showBlinkingWrong()

                    _tileState.update {
                        it.copy(
                            informMessage = selectMessageWrong(),
                            showRepeatButton = true,
                            isEnabledTiles = false,
                        )
                    }
                }
                GameResult.LevelCompleted -> {
                    _tileState.update {
                        it.copy(
                            score = it.score + 1,
                            informMessage = selectMessageCompleteLevel(),
                            isEnabledTiles = false,
                        )
                    }

                    delay(LEVEL_COMPLETED_TIME)

                    showGameTiles()
                }
            }
        }
    }

    private fun selectMessageCompleteLevel(): Int {
        return when((1..5).random()) {
            1 -> R.string.successfully
            2 -> R.string.wonderfully
            3 -> R.string.great
            4 -> R.string.excellently
            5 -> R.string.perfectly
            else -> R.string.successfully
        }
    }

    private fun selectMessageWrong(): Int {
        return when((1..4).random()) {
            1 -> R.string.mistake
            2 -> R.string.incorrect
            3 -> R.string.wrong
            4 -> R.string.sorry
            else -> R.string.mistake
        }
    }

    private suspend fun showBlinkingWrong() {
        _tileState.update {
            it.copy(
                isEnabledTiles = false,
            )
        }

        repeat(QUANTITY_REPEAT_AN_ERROR) {
            for (i in 0 until _tileState.value.tiles.size) {
                mutableListTiles(i,true)
            }

            delay(BLINKING_WRONG_TIME)

            for (i in 0 until _tileState.value.tiles.size) {
                mutableListTiles(i,false)
            }

            delay(BLINKING_WRONG_TIME)
        }

        _tileState.update {
            it.copy(
                isEnabledTiles = true,
            )
        }
    }

    fun refreshGame() {
        viewModelScope.launch {
            refreshGameUseCase()

            _tileState.update {
                it.copy(
                    informMessage = R.string.restart,
                    showRepeatButton = false,
                    isEnabledTiles = false,
                )
            }

            delay(REFRESH_TIME_GAME)

            _tileState.update {
                it.copy(
                    score = 0,
                    gameSequence = creatureTileSectionUseCase(),
                    isEnabledTiles = true,
                )
            }

            showGameTiles()
        }

    }

}