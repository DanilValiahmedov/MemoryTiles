package com.valimade.memorytiles.storage.domain.interactor

import com.valimade.memorytiles.storage.domain.interactor.color_tile.ColorTileInteractor
import com.valimade.memorytiles.storage.domain.interactor.display_speed.DisplaySpeedInteractor
import com.valimade.memorytiles.storage.domain.interactor.score.ScoreInteractor
import com.valimade.memorytiles.storage.domain.interactor.shape.ShapeInteractor
import com.valimade.memorytiles.storage.domain.interactor.theme.ThemeInteractor

class SettingsGameInteractor(
    val score: ScoreInteractor,
    val theme: ThemeInteractor,
    val shape: ShapeInteractor,
    val displaySpeed: DisplaySpeedInteractor,
    val colorTile: ColorTileInteractor,
)
