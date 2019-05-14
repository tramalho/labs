package com.tramalho.labs.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tramalho.labs.R
import com.tramalho.labs.data.entity.Tweet
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FormTweetActivity : AppCompatActivity(), TweeterContract.FormView {

    private val presenter: FormTweetPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findTweets.setOnClickListener {
            presenter.loadTweetsByNick(editText?.text.toString())
        }
    }

    override fun hideLoading() {
        loadingVisibility(View.GONE)
    }

    override fun showValidationError() {
        textInputLayout.error = getString(R.string.invalid_value)
    }

    override fun cleanValidationError() {
    }

    override fun showLoading() {
        loadingVisibility(View.VISIBLE)
    }

    override fun receiveData(tweets: List<Tweet>) {
    }

    override fun showError() {
    }

    private fun loadingVisibility(view: Int) {
        progressBar1.visibility = view
        findTweets.isEnabled = view == View.GONE
        textInputLayout.isEnabled = view == View.GONE
        textInputLayout.error = ""
    }
}
