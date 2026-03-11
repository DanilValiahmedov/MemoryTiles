package com.valimade.memorytiles.vibration.domain.usecase

import com.valimade.memorytiles.storage.domain.vibration.VibrationSettingsInteractor
import com.valimade.memorytiles.vibration.data.entity.VibrationManager
import com.valimade.memorytiles.vibration.domain.model.UsingVibration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class VibrateClickUseCase(
    private val vibrationManager: VibrationManager,
    private val vibrationSettings: VibrationSettingsInteractor,
    private val scope: CoroutineScope,
) {

    private var isVibrationEnabled: Boolean = true

    init {
        scope.launch {
            vibrationSettings.getUsageStatusVibration().collect { status ->
                isVibrationEnabled = (status == UsingVibration.IS_USED)
            }
        }
    }

    operator fun invoke() {
        if (isVibrationEnabled) {
            vibrationManager.vibrateClick()
        }
    }

}