package com.valimade.memorytiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.valimade.memorytiles.game.ui.components.TileGrid
import com.valimade.memorytiles.game.ui.model.TilesState
import com.valimade.memorytiles.ui.theme.BorderActive
import com.valimade.memorytiles.ui.theme.BorderInactive
import com.valimade.memorytiles.ui.theme.MemoryTilesTheme
import com.valimade.memorytiles.ui.theme.Tile1Active
import com.valimade.memorytiles.ui.theme.Tile1Inactive
import com.valimade.memorytiles.ui.theme.Tile2Active
import com.valimade.memorytiles.ui.theme.Tile2Inactive
import com.valimade.memorytiles.ui.theme.Tile3Active
import com.valimade.memorytiles.ui.theme.Tile3Inactive
import com.valimade.memorytiles.ui.theme.Tile4Active
import com.valimade.memorytiles.ui.theme.Tile4Inactive
import com.valimade.memorytiles.ui.theme.Tile5Active
import com.valimade.memorytiles.ui.theme.Tile5Inactive
import com.valimade.memorytiles.ui.theme.Tile6Active
import com.valimade.memorytiles.ui.theme.Tile6Inactive
import com.valimade.memorytiles.ui.theme.Tile7Active
import com.valimade.memorytiles.ui.theme.Tile7Inactive
import com.valimade.memorytiles.ui.theme.Tile8Active
import com.valimade.memorytiles.ui.theme.Tile8Inactive
import com.valimade.memorytiles.ui.theme.Tile9Active
import com.valimade.memorytiles.ui.theme.Tile9Inactive
import com.valimade.memorytiles.ui.theme.Tile10Active
import com.valimade.memorytiles.ui.theme.Tile10Inactive
import com.valimade.memorytiles.ui.theme.Tile11Active
import com.valimade.memorytiles.ui.theme.Tile11Inactive
import com.valimade.memorytiles.ui.theme.Tile12Active
import com.valimade.memorytiles.ui.theme.Tile12Inactive
import com.valimade.memorytiles.ui.theme.Tile13Active
import com.valimade.memorytiles.ui.theme.Tile13Inactive
import com.valimade.memorytiles.ui.theme.Tile14Active
import com.valimade.memorytiles.ui.theme.Tile14Inactive
import com.valimade.memorytiles.ui.theme.Tile15Active
import com.valimade.memorytiles.ui.theme.Tile15Inactive
import com.valimade.memorytiles.ui.theme.Tile16Active
import com.valimade.memorytiles.ui.theme.Tile16Inactive

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoryTilesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val gridSize = 4
                    val tiles = remember {
                        mutableStateListOf(
                            TilesState(false, Tile1Active, Tile1Inactive),
                            TilesState(false, Tile2Active, Tile2Inactive),
                            TilesState(false, Tile3Active, Tile3Inactive),
                            TilesState(false, Tile4Active, Tile4Inactive),
                            TilesState(false, Tile5Active, Tile5Inactive),
                            TilesState(false, Tile6Active, Tile6Inactive),
                            TilesState(false, Tile7Active, Tile7Inactive),
                            TilesState(false, Tile8Active, Tile8Inactive),
                            TilesState(false, Tile9Active, Tile9Inactive),
                            TilesState(false, Tile10Active, Tile10Inactive),
                            TilesState(false, Tile11Active, Tile11Inactive),
                            TilesState(false, Tile12Active, Tile12Inactive),
                            TilesState(false, Tile13Active, Tile13Inactive),
                            TilesState(false, Tile14Active, Tile14Inactive),
                            TilesState(false, Tile15Active, Tile15Inactive),
                            TilesState(false, Tile16Active, Tile16Inactive),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        TileGrid(
                            gridSize = gridSize,
                            tiles = tiles,
                            onTileClick = { tile ->
                                val index = tiles.indexOf(tile)
                                if (index != -1) {
                                    tiles[index] = tile.copy(isActive = !tile.isActive)
                                }
                            },
                        )
                    }



                }
            }
        }
    }
}
