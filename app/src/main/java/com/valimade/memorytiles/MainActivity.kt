package com.valimade.memorytiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.valimade.memorytiles.game.domain.model.DifficultyLevel
import com.valimade.memorytiles.game.domain.model.TileColors
import com.valimade.memorytiles.game.ui.screen.GameScreen
import com.valimade.memorytiles.ui.theme.MemoryTilesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoryTilesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        difficulty = DifficultyLevel.EASY,
                        colorSelection = TileColors.BLUE,
                    )
                }
            }
        }
    }
}
