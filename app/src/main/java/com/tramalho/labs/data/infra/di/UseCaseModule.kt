package com.tramalho.labs.data.infra.di

import com.tramalho.labs.data.repository.TwitterRepository
import com.tramalho.labs.domain.TwitterUseCase
import org.koin.dsl.module

val usecaseModule = module {

    factory { TwitterUseCase(get(), get(), get()) }
}