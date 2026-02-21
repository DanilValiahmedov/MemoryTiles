package com.valimade.memorytiles.game.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valimade.memorytiles.game.domain.model.DifficultyLevel
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
                     enabledTiles = false
                 )
             }

             for (i in _tileState.value.gameSequence) {

                 val activeValue = _tileState.value.tiles[i].copy(
                     isActive = true
                 )
                 val newList = _tileState.value.tiles.toMutableList()
                 newList[i] = activeValue

                 _tileState.update {
                     it.copy(
                         tiles = newList
                     )
                 }

                 delay(1000)

                 newList[i] = activeValue.copy(isActive = false)

                 _tileState.update {
                     it.copy(
                         tiles = newList
                     )
                 }

                 delay(500)

             }

             _tileState.update {
                 it.copy(
                     enabledTiles = true
                 )
             }
         }
    }

}