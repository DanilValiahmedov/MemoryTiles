package com.valimade.memorytiles.game.ui.mapper

import com.valimade.memorytiles.game.domain.model.Tile
import com.valimade.memorytiles.game.ui.model.TileUi

object TileMapper {
    fun domainToUi(domainTile: Tile): TileUi {
        return TileUi(
            colorTileActive = domainTile.colorActive,
            colorTileInactive = domainTile.colorInactive,
        )
    }
}