package com.tramalho.labs.data.infra.di

import com.tramalho.labs.data.repository.TwitterRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory { TwitterRepository(get()) }
}