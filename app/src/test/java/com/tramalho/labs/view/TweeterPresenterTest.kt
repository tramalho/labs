package com.tramalho.labs.view

import com.tramalho.labs.data.entity.Tweet
import com.tramalho.labs.data.infra.Constants
import com.tramalho.labs.data.infra.Result
import com.tramalho.labs.domain.TwitterUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class TweeterPresenterTest {

    private val tweets = listOf(Tweet(Constants.GRANT_TYPE, 1))

    @Mock
    private lateinit var view: TweeterContract.View

    @Mock
    private lateinit var useCase: TwitterUseCase

    private lateinit var presenter: TweeterPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TweeterPresenter(view, useCase, CoroutineScope(Dispatchers.Unconfined))
    }

    @Test
    fun shouldReturnSuccessToView() = runBlocking {

        `when`(useCase.getTweetersByNick(Constants.GRANT_TYPE)).thenReturn(
            Result.Success(tweets)
        )

        presenter.loadTweetsByNick(Constants.GRANT_TYPE)

        verify(view).showLoading()
        verify(view).receiveData(tweets)
    }


    @Test
    fun shouldReturnErrorToView() = runBlocking {

        `when`(useCase.getTweetersByNick(Constants.GRANT_TYPE)).thenReturn(Result.Failure(Error()))

        presenter.loadTweetsByNick(Constants.GRANT_TYPE)

        verify(view).showLoading()
        verify(view, never()).receiveData(tweets)
        verify(view).showError()
    }

}