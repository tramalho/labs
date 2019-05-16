package com.tramalho.labs.view

import android.app.Application
import com.tramalho.labs.data.infra.di.*
import org.koin.android.ext.android.startKoin

class LabsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this, listOf(
                presenterModule,
                usecaseModule,
                repositoryModule,
                systemModule,
                urlModule,
                networkModule
            )
        )
    }
}