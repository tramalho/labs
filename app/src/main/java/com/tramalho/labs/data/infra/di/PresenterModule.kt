package com.tramalho.labs.data.infra.di

import com.tramalho.labs.view.FormTweetPresenter
import com.tramalho.labs.view.ListTweetsPresenter
import com.tramalho.labs.view.TweeterContract
import org.koin.dsl.module.module

val presenterModule = module {

    factory { (cv: TweeterContract.FormView) -> FormTweetPresenter(cv, get()) }

    factory { (cv: TweeterContract.ListView) -> ListTweetsPresenter(cv, get()) }
}