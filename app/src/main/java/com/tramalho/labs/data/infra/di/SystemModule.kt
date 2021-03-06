package com.tramalho.labs.data.infra.di

import android.content.Context
import com.tramalho.labs.BuildConfig
import com.tramalho.labs.data.entity.AppCredentials
import com.tramalho.labs.data.infra.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val systemModule = module {

    single { AppCredentials(BuildConfig.AUTHORIZARION, BuildConfig.GCP_API_KEY) }

    factory { androidContext().getSharedPreferences(Constants.PREF_KEY, Context.MODE_PRIVATE) }
}