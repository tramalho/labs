package com.tramalho.labs.view

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.tramalho.labs.R
import com.tramalho.labs.data.entity.SentimentType
import com.tramalho.labs.data.entity.Tweet
import com.tramalho.labs.data.infra.Constants
import kotlinx.android.synthetic.main.activity_tweeter_list.*
import kotlinx.android.synthetic.main.sentiment_layout.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.*


class TweeterListActivity : AppCompatActivity(), TweeterContract.ListView {

    private lateinit var tweets: ArrayList<Tweet>
    private val presenter: ListTweetsPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweeter_list)

        setupRV()
    }

    private fun setupRV() {

        tweets = intent.getParcelableArrayListExtra<Tweet>(Constants.TWEETER_LIST_EXTRA)


        val clickAction: (String) -> Unit = {
            presenter.analizeSentimentByContent(it)
        }

        tweetsRV.layoutManager = LinearLayoutManager(this)
        tweetsRV.adapter = TweetAdapter(tweets, clickAction)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun showError() {
        showToastError(R.string.something_wrong)
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun receiveData(sentiment: SentimentType) {
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.sentiment_layout, null)
        view.imageView.setImageResource(checkImageByType(sentiment))
        builder.setView(view)
        builder.setPositiveButton(android.R.string.ok, { dialog, _ -> dialog.dismiss() })
        val dialog = builder.create()
        dialog.show()
    }

    private fun checkImageByType(sentiment: SentimentType): Int {
        return when (sentiment) {
            SentimentType.HAPPY -> R.drawable.ic_happy
            SentimentType.UNHAPPY -> R.drawable.ic_unhappy
            else -> R.drawable.ic_confused
        }
    }
}
