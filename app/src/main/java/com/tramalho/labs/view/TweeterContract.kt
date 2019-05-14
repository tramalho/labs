package com.tramalho.labs.view

import com.tramalho.labs.data.entity.SentimentType
import com.tramalho.labs.data.entity.Tweet

interface TweeterContract {

    interface BaseView {
        fun showLoading()
        fun showError()
        fun hideLoading()
    }

    interface FormView : BaseView {
        fun receiveData(tweets: List<Tweet>)
        fun showValidationError()
        fun cleanValidationError()
    }

    interface ListView : BaseView {
        fun receiveData(sentiment: SentimentType)
    }
}