package com.tramalho.labs.data.infra.di

import org.koin.dsl.module

val urlModule = module {
    single {"https://api.twitter.com/1.1/" }
}