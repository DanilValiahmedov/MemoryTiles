package com.valimade.memorytiles

import android.app.Application
import com.valimade.memorytiles.game.di.gameModule
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(gameModule)
        }
    }
}