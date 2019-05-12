package com.tramalho.labs.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tramalho.labs.R
import com.tramalho.labs.data.entity.Tweet
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), TweeterContract.View {

    private val presenter: TweeterPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findTweets.setOnClickListener {
            presenter.loadTweetsByNick(StringBuilder(findTweets.text).toString())
        }
    }

    override fun hideLoading() {
    }

    override fun showValidationError() {
    }

    override fun cleanValidationError() {
    }

    override fun showLoading() {
    }

    override fun receiveData(tweets: List<Tweet>) {
    }

    override fun showError() {
    }

}
