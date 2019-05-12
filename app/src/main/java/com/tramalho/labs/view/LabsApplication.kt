package com.tramalho.labs.view

import android.app.Application
import com.tramalho.labs.data.infra.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LabsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LabsApplication)
            modules(presenterModule, usecaseModule, repositoryModule, systemModule, urlModule, networkModule)
        }
    }
}