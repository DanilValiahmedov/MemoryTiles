package com.valimade.memorytiles.game.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valimade.memorytiles.R
import com.valimade.memorytiles.game.domain.model.DifficultyLevel
import com.valimade.memorytiles.game.domain.model.GameResult
import com.valimade.memorytiles.game.domain.interactor.GameInteractor
import com.valimade.memorytiles.game.ui.mapper.TileMapper
import com.valimade.memorytiles.game.ui.model.TilesState
import com.valimade.memorytiles.settings.data.display_speed.DisplaySpeed
import com.valimade.memorytiles.sound.domain.interactor.SoundInteractor
import com.valimade.memorytiles.storage.domain.interactor.SettingsGameInteractor
import com.valimade.memorytiles.vibration.domain.usecase.VibrateClickUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val TAP_PLAYER_TIME = 200L
const val LEVEL_COMPLETED_TIME = 1000L
const val QUANTITY_REPEAT_AN_ERROR = 3
const val BLINKING_WRONG_TIME = 200L
const val REFRESH_TIME_GAME = 1000L

class GameViewModel(
    private val tileMapper: TileMapper,
    private val gameInteractor: GameInteractor,
    private val soundInteractor: SoundInteractor,
    private val vibrateClickUseCase: VibrateClickUseCase,
    private val settingsInteractor: SettingsGameInteractor,
): ViewModel() {
    private val _tileState = MutableStateFlow(TilesState())
    private var difficultyLevel = DifficultyLevel.EASY
    private var displaySpeed = DisplaySpeed.MEASURED
    val tileState = _tileState.asStateFlow()

    fun startGame(difficulty: DifficultyLevel) {
        viewModelScope.launch {
            difficultyLevel = difficulty
            val colorSelection = settingsInteractor.colorTile.getColorTile().first()
            val listTilesDomain = gameInteractor.startGame(difficulty, colorSelection)
            val listTilesUi = listTilesDomain.map { domainTile ->
                tileMapper.domainToUi(domainTile)
            }

            soundInteractor.prepareRates(difficulty)
            displaySpeed = settingsInteractor.displaySpeed.getDisplaySpeed().first()

            val bestScore = settingsInteractor.score.getScore(difficulty).first()
            val theme = settingsInteractor.theme.getTheme()
            val shapeTiles = settingsInteractor.shape.getShape().first()
            val gameSequence = gameInteractor.creatureGameTileSection()

            _tileState.update {
                it.copy(
                    tiles = listTilesUi,
                    shapeTiles = shapeTiles,
                    gameSequence = gameSequence,
                    bestScore = bestScore,
                    backgroundImage = theme.first().background,
                    emphasisColor = theme.first().color,
                )
            }

            showGameTiles()
        }
    }

    fun showGameTiles() {
         viewModelScope.launch {
             _tileState.update {
                 it.copy(
                     isEnabledTiles = false,
                     informMessage = R.string.remember,
                 )
             }

             val sequence = _tileState.value.gameSequence
             sequence.forEachIndexed { index, tileIndex ->
                 mutableListTiles(tileIndex, true)
                 soundInteractor.playClick(tileIndex)
                 vibrateClickUseCase()

                 delay(displaySpeed.activeSpeed)

                 mutableListTiles(tileIndex, false)

                 if (index != sequence.lastIndex) {
                     delay(displaySpeed.inactiveSpeed)
                 }
             }

             _tileState.update {
                 it.copy(
                     isEnabledTiles = true,
                     informMessage = R.string.repeat,
                     maxStepsPerRound = it.gameSequence.size,
                     showSteps = true,
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
            soundInteractor.playClick(selectedTile)
            vibrateClickUseCase()

            delay(TAP_PLAYER_TIME)

            mutableListTiles(selectedTile,false)

            when(gameInteractor.checkPlayerSequence(selectedTile)) {
                GameResult.Correct -> {
                    _tileState.update {
                        it.copy(
                            isEnabledTiles = true,
                            currentStepPerRound = it.currentStepPerRound + 1
                        )
                    }
                }
                GameResult.Wrong -> {
                    showBlinkingWrong()

                    _tileState.update {
                        it.copy(
                            informMessage = selectMessageWrong(),
                            showRepeatButton = true,
                            isEnabledTiles = false,
                            currentStepPerRound = 0,
                            showSteps = false,
                        )
                    }
                }
                GameResult.LevelCompleted -> {
                    val score = _tileState.value.score + 1
                    settingsInteractor.score.saveScoreIfBest(difficultyLevel, score)
                    val bestScore = settingsInteractor.score.getScore(difficultyLevel).first()

                    _tileState.update {
                        it.copy(
                            score = score,
                            bestScore = bestScore,
                            informMessage = selectMessageCompleteLevel(),
                            isEnabledTiles = false,
                            currentStepPerRound = 0,
                            showSteps = false,
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
            val tilesSize = _tileState.value.tiles.size
            for (i in 0 until tilesSize) {
                mutableListTiles(i,true)
            }
            delay(BLINKING_WRONG_TIME)

            for (i in 0 until tilesSize) {
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
            gameInteractor.refreshGame()

            _tileState.update {
                it.copy(
                    informMessage = R.string.restart,
                    showRepeatButton = false,
                    isEnabledTiles = false,
                    showSteps = false,
                )
            }

            delay(REFRESH_TIME_GAME)

            val gameSequence = gameInteractor.creatureGameTileSection()

            _tileState.update {
                it.copy(
                    score = 0,
                    gameSequence = gameSequence,
                    isEnabledTiles = true,
                    showSteps = true,
                )
            }

            showGameTiles()
        }

    }

    override fun onCleared() {
        super.onCleared()
        soundInteractor.release()
    }

}