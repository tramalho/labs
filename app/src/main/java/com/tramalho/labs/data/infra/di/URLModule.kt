package com.tramalho.labs.data.infra.di

import org.koin.dsl.module

val urlModule = module {
    single {"localhost:8080" }
}