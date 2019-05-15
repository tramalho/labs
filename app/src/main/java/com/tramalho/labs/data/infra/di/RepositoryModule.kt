package com.tramalho.labs.data.infra.di

import com.tramalho.labs.data.repository.GoogleRepository
import com.tramalho.labs.data.repository.LocalRepository
import com.tramalho.labs.data.repository.TwitterRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory { TwitterRepository(get()) }
    factory { LocalRepository(get()) }
    factory { GoogleRepository(get()) }
}