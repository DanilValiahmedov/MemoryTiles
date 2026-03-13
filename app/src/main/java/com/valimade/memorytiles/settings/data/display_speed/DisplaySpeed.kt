package com.valimade.memorytiles.settings.data.display_speed

enum class DisplaySpeed(
    val title: String,
    val activeSpeed: Long,
    val inactiveSpeed: Long,
) {
    SLOWLY("Медленно", 400, 400),
    MEASURED("Размеренно", 300, 300),
    QUICKLY("Быстро", 200, 200),
    TURBO("Турбо", 100, 100),
}
