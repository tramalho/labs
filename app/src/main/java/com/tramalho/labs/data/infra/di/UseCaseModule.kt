package com.tramalho.labs.data.infra.di

import com.tramalho.labs.domain.TweeterAnalizeUseCase
import com.tramalho.labs.domain.TwitterUseCase
import org.koin.dsl.module.module

val usecaseModule = module {

    factory { TwitterUseCase(get(), get(), get()) }

    factory { TweeterAnalizeUseCase(get(), get()) }
}