package com.tramalho.labs.data.infra.di

import com.tramalho.labs.view.TweeterContract
import com.tramalho.labs.view.FormTweetPresenter
import com.tramalho.labs.view.ListTweetsPresenter
import org.koin.dsl.module

val presenterModule = module {

    factory { (cv: TweeterContract.FormView) -> FormTweetPresenter(cv, get()) }

    factory { (cv: TweeterContract.ListView) -> ListTweetsPresenter(cv, get()) }
}