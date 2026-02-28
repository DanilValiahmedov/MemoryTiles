package com.valimade.memorytiles

import android.app.Application
import com.valimade.memorytiles.game.di.gameModule
import com.valimade.memorytiles.storage.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                gameModule,
                storageModule,
            )
        }
    }
}