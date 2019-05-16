package com.tramalho.labs

import android.content.Intent
import android.os.Bundle
import androidx.test.rule.ActivityTestRule
import com.tramalho.labs.data.entity.Tweet
import com.tramalho.labs.data.infra.Constants
import com.tramalho.labs.setup.BaseInstrumentedTest
import com.tramalho.labs.view.TweeterListActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TweeterListActivityTest : BaseInstrumentedTest() {

    private val tweet = Tweet("Text", 1, "01/01/2018")


    @get:Rule
    var activityRule: ActivityTestRule<TweeterListActivity> =
        ActivityTestRule(TweeterListActivity::class.java, false, false)


    @Before
    fun setup() {
        val result = createIntentData()
        activityRule.launchActivity(result)
    }

    private val happy = "assets/google/success.json"
    private val error = "assets/google/error.json"


    @Test
    fun shouldBeShowPositiveSentiment() {

        setupMockWebServer(happy)

        clickInText(tweet.text)
        waitLoading()
        checkImage(R.id.imageView, R.drawable.ic_happy)
    }

    @Test
    fun shouldBeShowErrorMessage() {

        setupMockWebServer(error)

        clickInText(tweet.text)
        waitLoading()
        checkTextInToast(activityRule.activity.getString(R.string.something_wrong), activityRule.activity)
    }

    private fun createIntentData(): Intent {

        val bundle = Bundle()
        bundle.putParcelableArrayList(Constants.TWEETER_LIST_EXTRA, arrayListOf(tweet))

        val resultData = Intent()
        resultData.putExtras(bundle)

        return resultData
    }
}