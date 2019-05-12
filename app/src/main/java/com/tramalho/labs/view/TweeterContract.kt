package com.tramalho.labs.view

import com.tramalho.labs.data.entity.Tweet

interface TweeterContract {

    interface View {
        fun showLoading()
        fun receiveData(tweets: List<Tweet>)
        fun showError()
        fun hideLoading()
        fun showValidationError()
        fun cleanValidationError()
    }
}