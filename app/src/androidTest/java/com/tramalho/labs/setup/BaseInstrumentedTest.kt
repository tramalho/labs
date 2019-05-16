package com.tramalho.labs.setup

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.tramalho.labs.data.infra.Constants
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext
import org.koin.test.KoinTest
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
@LargeTest
abstract class BaseInstrumentedTest : KoinTest {

    private lateinit var mockWebServer: MockWebServer

    private val endpoint = "http://localhost:51384"

    private val urlModule = module(override = true) {
        single(Constants.TWEETER_ENDPOINT) { StringBuilder(endpoint) }
        single(Constants.GOOGLE_ENDPOINT) { StringBuilder(endpoint) }
    }


    @Before
    fun setUp() {

        mockWebServer = MockWebServer()

        mockWebServer.start(TestConstants.PORT)

        StandAloneContext.loadKoinModules(listOf(urlModule))
    }

    @After
    fun tearDown() {
        if (::mockWebServer.isInitialized) {
            mockWebServer.shutdown()
        }
    }

    fun setupMockWebServer(pathMock: String, delay: Long = 1, statusCode: Int = 200) {
        val json = ResourceUtils().openFile(pathMock)
        val mockResponse = MockResponse()

        mockResponse
            .setResponseCode(statusCode)
            .setBody(json)
            .throttleBody(Long.MAX_VALUE, delay, TimeUnit.MILLISECONDS)

        mockWebServer.enqueue(mockResponse)
    }

    fun clickInText(text: String) =
        onView(withText(containsString(text))).perform(ViewActions.click())

    fun checkIfIsDisplayed(rId: Int) = onView(withId(rId)).check(matches(isDisplayed()))

    fun checkImage(imageRId: Int, rDrawableId: Int) = onView(withId(imageRId))
        .check(matches(DrawableMatcher(rDrawableId)))

    fun waitLoading() {
        Thread.sleep(2000)
    }

    fun checkTextInToast(text: String, activity: Activity) =
        onView(withText(text)).inRoot(withDecorView(not(`is`(activity.getWindow().getDecorView())))).check(
            matches(isDisplayed())
        )

}