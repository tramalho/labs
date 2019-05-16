package com.tramalho.labs.data.infra.di

import com.tramalho.labs.data.infra.Constants.Companion.GOOGLE_ENDPOINT
import com.tramalho.labs.data.infra.Constants.Companion.GOOGLE_NT
import com.tramalho.labs.data.infra.Constants.Companion.TWEETER_ENDPOINT
import com.tramalho.labs.data.infra.Constants.Companion.TWEETER_NT
import com.tramalho.labs.data.infra.GoogleService
import com.tramalho.labs.data.infra.RetrofitFactory
import com.tramalho.labs.data.infra.ServiceFactory
import com.tramalho.labs.data.infra.TwitterService
import org.koin.dsl.module.module

val networkModule = module {

    factory(TWEETER_NT) { RetrofitFactory().build(get(TWEETER_ENDPOINT)) }

    factory(GOOGLE_NT) { RetrofitFactory().build(get(GOOGLE_ENDPOINT)) }

    single { ServiceFactory(get(TWEETER_NT)).create<TwitterService>() }

    single { ServiceFactory(get(GOOGLE_NT)).create<GoogleService>() }
}
