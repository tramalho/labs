package com.tramalho.labs.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tramalho.labs.R
import com.tramalho.labs.data.entity.Tweet
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), TweeterContract.View{

    override fun showLoading() {

    }

    override fun receiveData(tweets: List<Tweet>) {

    }

    override fun showError() {

    }

    val presenter: TweeterPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
