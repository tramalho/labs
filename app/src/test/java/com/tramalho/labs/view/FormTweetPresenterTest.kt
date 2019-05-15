package com.tramalho.labs.view

import com.tramalho.labs.data.entity.Tweet
import com.tramalho.labs.data.infra.Constants
import com.tramalho.labs.data.infra.Result
import com.tramalho.labs.domain.TwitterUseCase
import com.tramalho.labs.utilities.TestUI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class FormTweetPresenterTest {

    private val tweets = arrayListOf(Tweet(Constants.GRANT_TYPE, 1))

    @Mock
    private lateinit var formView: TweeterContract.FormView

    @Mock
    private lateinit var useCase: TwitterUseCase

    private lateinit var presenter: FormTweetPresenter

    @Before
    @ExperimentalCoroutinesApi
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = FormTweetPresenter(formView, useCase, TestUI)
    }

    @Test
    fun shouldReturnSuccessToView() = runBlocking {

        `when`(useCase.getTweetersByNick(Constants.GRANT_TYPE)).thenReturn(
            Result.Success(tweets)
        )

        presenter.loadTweetsByNick(Constants.GRANT_TYPE)

        verify(formView).showLoading()
        verify(formView).receiveData(tweets)
    }


    @Test
    fun shouldReturnErrorToView() = runBlocking {

        `when`(useCase.getTweetersByNick(Constants.GRANT_TYPE)).thenReturn(Result.Failure(Error()))

        presenter.loadTweetsByNick(Constants.GRANT_TYPE)

        verify(formView).showLoading()
        verify(formView, never()).receiveData(tweets)
        verify(formView).showError()
    }

}