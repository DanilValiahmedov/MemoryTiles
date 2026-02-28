package com.valimade.memorytiles.storage.di

import com.valimade.memorytiles.storage.data.score.ScoreDataStore
import com.valimade.memorytiles.storage.data.score.ScoreKeys
import com.valimade.memorytiles.storage.domain.score.ScoreInteractor
import org.koin.dsl.module

val storageModule = module {

    //Score
    single { ScoreKeys }
    single {
        ScoreDataStore(
            context = get(),
            keys = get(),
        )
    }
    single { ScoreInteractor(scoreDataStore = get()) }

}