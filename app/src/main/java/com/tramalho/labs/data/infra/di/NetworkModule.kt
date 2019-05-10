package com.tramalho.labs.data.infra.di

import com.tramalho.labs.data.infra.RetrofitFactory
import com.tramalho.labs.data.infra.ServiceFactory
import com.tramalho.labs.data.infra.TwitterService
import org.koin.dsl.module

val networkModule = module(override = true) {

    single { RetrofitFactory().build(get()) }

    single { ServiceFactory<TwitterService>(get()).create(TwitterService::class.java) }
}