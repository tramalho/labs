package com.tramalho.labs.data.infra.di

import com.tramalho.labs.data.infra.Constants
import org.koin.core.qualifier.named
import org.koin.dsl.module

val urlModule = module {
    single(named(Constants.TWEETER_ENDPOINT)) { StringBuilder(Constants.TWEETER_ENDPOINT) }
    single(named(Constants.GOOGLE_ENDPOINT)) { StringBuilder(Constants.GOOGLE_ENDPOINT) }
}