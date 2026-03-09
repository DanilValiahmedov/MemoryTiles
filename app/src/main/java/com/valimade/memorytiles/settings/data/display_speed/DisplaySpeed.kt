package com.valimade.memorytiles.settings.data.display_speed

enum class DisplaySpeed(
    val title: String,
    val activeSpeed: Long,
    val inactiveSpeed: Long,
) {
    SLOWLY("Медленно", 400, 500),
    MEASURED("Размеренно", 300, 400),
    QUICKLY("Быстро", 200, 300),
    TURBO("Турбо", 150, 250),
}
