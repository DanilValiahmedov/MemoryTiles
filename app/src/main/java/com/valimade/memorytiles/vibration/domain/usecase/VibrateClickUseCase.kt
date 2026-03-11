package com.valimade.memorytiles.vibration.domain.usecase

import com.valimade.memorytiles.vibration.data.entity.VibrationManager

class VibrateClickUseCase(
    private val vibrationManager: VibrationManager
) {
    fun invoke() = vibrationManager.vibrateClick()
}