package com.valimade.memorytiles.core.di

import android.app.Application
import com.valimade.memorytiles.game.di.gameModule
import com.valimade.memorytiles.sound.di.soundModule
import com.valimade.memorytiles.storage.di.storageModule
import com.valimade.memorytiles.vibration.di.vibrationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@App)
            modules(
                coreModule,
                gameModule,
                storageModule,
                soundModule,
                vibrationModule,
            )
        }
    }
}