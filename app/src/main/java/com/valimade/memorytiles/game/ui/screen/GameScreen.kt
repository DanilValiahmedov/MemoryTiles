package com.valimade.memorytiles.game.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valimade.memorytiles.R
import com.valimade.memorytiles.game.domain.model.DifficultyLevel
import com.valimade.memorytiles.game.domain.model.TileColors
import com.valimade.memorytiles.game.ui.components.TileGrid
import com.valimade.memorytiles.game.ui.viewmodel.GameViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameScreen(
    modifier: Modifier,
    difficulty: DifficultyLevel,
    colorSelection: TileColors
) {
    val viewModel: GameViewModel = koinViewModel()
    val tileState by viewModel.tileState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startGame(difficulty, colorSelection)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "Задний фон",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Счет: ${tileState.score}",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = "Лучший: ${tileState.bestScore}",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )


                IconButton(onClick = {
                    viewModel.refreshGame()
                }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Обновить",
                        tint = Color.White,
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = tileState.informMessage),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.height(56.dp)
            ) {
                if(tileState.showRepeatButton) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier
                            .height(48.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 2.dp,
                            pressedElevation = 0.dp
                        ),
                        onClick = {
                            viewModel.refreshGame()
                        },
                    ) {
                        Text(
                            text = "Заново",
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                else if (tileState.showSteps) {
                    Text(
                        text = "Шаг: ${tileState.currentStepPerRound} из ${tileState.maxStepsPerRound}",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            TileGrid(
                gridSize = difficulty.sideLength,
                tiles = tileState.tiles,
                onTileClick = {
                    if(tileState.isEnabledTiles) {
                        viewModel.playerTileSelection(it)
                    }
                },
            )

            Spacer(modifier = Modifier.weight(1f))

        }
    }


}