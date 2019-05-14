package com.tramalho.labs.data.infra.di

import com.tramalho.labs.view.TweeterContract
import com.tramalho.labs.view.FormTweetPresenter
import org.koin.dsl.module

val presenterModule = module {

    factory { (cv: TweeterContract.FormView) -> FormTweetPresenter(cv, get()) }
}