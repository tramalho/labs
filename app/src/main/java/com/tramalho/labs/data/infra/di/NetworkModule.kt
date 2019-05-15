package com.tramalho.labs.data.infra.di

import com.tramalho.labs.data.infra.*
import com.tramalho.labs.data.infra.Constants.Companion.GOOGLE_ENDPOINT
import com.tramalho.labs.data.infra.Constants.Companion.GOOGLE_NT
import com.tramalho.labs.data.infra.Constants.Companion.TWEETER_ENDPOINT
import com.tramalho.labs.data.infra.Constants.Companion.TWEETER_NT
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module(override = true) {

    factory(named(TWEETER_NT)) { RetrofitFactory().build(get(named(TWEETER_ENDPOINT))) }

    factory(named(GOOGLE_NT)) { RetrofitFactory().build(get(named(GOOGLE_ENDPOINT))) }

    single { ServiceFactory(get(named(TWEETER_NT))).create<TwitterService>() }

    single { ServiceFactory(get(named(GOOGLE_NT))).create<GoogleService>() }
}
