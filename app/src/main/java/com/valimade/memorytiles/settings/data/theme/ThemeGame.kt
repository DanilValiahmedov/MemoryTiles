package com.valimade.memorytiles.settings.data.theme

import androidx.compose.ui.graphics.Color
import com.valimade.memorytiles.R
import com.valimade.memorytiles.ui.theme.EmphasisBlueTheme
import com.valimade.memorytiles.ui.theme.EmphasisGrayTheme
import com.valimade.memorytiles.ui.theme.EmphasisGreenTheme
import com.valimade.memorytiles.ui.theme.EmphasisRedTheme

enum class ThemeGame(val title: String, val background: Int, val color: Color) {
    GRAY("Серебристый шёлк", R.drawable.back_gray, EmphasisGrayTheme),
    RED("Кровавый рубин", R.drawable.back_red, EmphasisRedTheme),
    GREEN("Малахитовый лес", R.drawable.back_green, EmphasisGreenTheme),
    BLUE("Сапфировая ночь", R.drawable.back_blue, EmphasisBlueTheme),
}