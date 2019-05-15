package com.tramalho.labs.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tramalho.labs.R
import com.tramalho.labs.data.entity.Tweet
import com.tramalho.labs.data.infra.Constants
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

    override fun showLoading() {
        loadingVisibility(View.VISIBLE)
    }

    override fun receiveData(tweets: ArrayList<Tweet>) {
        val intent = Intent(this, TweeterListActivity::class.java)
        intent.putExtra(Constants.TWEETER_LIST_EXTRA, tweets)
        startActivity(intent)
    }

    override fun showError() {
        showToastError(R.string.something_wrong)
    }

    private fun loadingVisibility(view: Int) {
        progressBar1.visibility = view
        findTweets.isEnabled = view == View.GONE
        textInputLayout.isEnabled = view == View.GONE
        textInputLayout.error = ""
    }
}
