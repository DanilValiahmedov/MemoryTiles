package com.valimade.memorytiles.game.domain.entity

import androidx.compose.ui.graphics.Color
import com.valimade.memorytiles.game.domain.model.DifficultyLevel
import com.valimade.memorytiles.game.domain.model.Tile
import com.valimade.memorytiles.game.domain.model.TileColors
import com.valimade.memorytiles.game.domain.utils.ListPaletteColors
import com.valimade.memorytiles.ui.theme.Tile11Active
import com.valimade.memorytiles.ui.theme.Tile11Inactive
import com.valimade.memorytiles.ui.theme.Tile1Active
import com.valimade.memorytiles.ui.theme.Tile1Inactive
import com.valimade.memorytiles.ui.theme.Tile3Active
import com.valimade.memorytiles.ui.theme.Tile3Inactive
import com.valimade.memorytiles.ui.theme.Tile5Active
import com.valimade.memorytiles.ui.theme.Tile5Inactive
import com.valimade.memorytiles.ui.theme.Tile8Active
import com.valimade.memorytiles.ui.theme.Tile8Inactive
import com.valimade.memorytiles.ui.theme.Tile9Active
import com.valimade.memorytiles.ui.theme.Tile9Inactive

object FieldBuilder {

    fun fieldConstruction(difficultyLevel: DifficultyLevel, colorSelection: TileColors): List<Tile> {
        val fieldSize = difficultyLevel.quantities

        return when(colorSelection) {
            TileColors.MULTICOLORED -> {
                ListPaletteColors
                    .shuffled()
                    .take(fieldSize)
            }
            TileColors.RED -> {
                List(fieldSize) {
                    Tile(
                        colorActive = Tile1Active,
                        colorInactive = Tile1Inactive,
                    )
                }
            }
            TileColors.GREEN -> {
                List(fieldSize) {
                    Tile(
                        colorActive = Tile5Active,
                        colorInactive = Tile5Inactive,
                    )
                }
            }
            TileColors.BLUE -> {
                List(fieldSize) {
                    Tile(
                        colorActive = Tile8Active,
                        colorInactive = Tile8Inactive,
                    )
                }
            }
            TileColors.YELLOW -> {
                List(fieldSize) {
                    Tile(
                        colorActive = Tile3Active,
                        colorInactive = Tile3Inactive,
                    )
                }
            }
            TileColors.PURPLE -> {
                List(fieldSize) {
                    Tile(
                        colorActive = Tile9Active,
                        colorInactive = Tile9Inactive,
                    )
                }
            }
            TileColors.PINK -> {
                List(fieldSize) {
                    Tile(
                        colorActive = Tile11Active,
                        colorInactive = Tile11Inactive,
                    )
                }
            }
            TileColors.BLACK_AND_WHITE -> {
                List(fieldSize) {
                    Tile(
                        colorActive = Color.White,
                        colorInactive = Color.Black,
                    )
                }
            }
            TileColors.WHITE_AND_BLACK -> {
                List(fieldSize) {
                    Tile(
                        colorActive = Color.Black,
                        colorInactive = Color.White,
                    )
                }
            }
        }

    }

}