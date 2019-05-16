package com.tramalho.labs.data.infra.di

import com.tramalho.labs.data.infra.Constants
import org.koin.dsl.module.module

val urlModule = module(override = true) {
    single(Constants.TWEETER_ENDPOINT) { StringBuilder(Constants.TWEETER_ENDPOINT) }
    single(Constants.GOOGLE_ENDPOINT) { StringBuilder(Constants.GOOGLE_ENDPOINT) }
}
