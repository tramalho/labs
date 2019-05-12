package com.tramalho.labs

import com.tramalho.labs.data.entity.Tweet
import com.tramalho.labs.data.entity.TweetRequest
import com.tramalho.labs.data.infra.Constants.Companion.GRANT_TYPE
import com.tramalho.labs.data.infra.Result
import com.tramalho.labs.data.repository.LocalRepository
import com.tramalho.labs.data.repository.TwitterRepository
import com.tramalho.labs.data.entity.AppCredentials
import com.tramalho.labs.data.entity.OAuthToken
import com.tramalho.labs.domain.TwitterUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.lang.Error

class TwitterUseCaseTest {

    private val nickname = "nick"
    private val oAuthToken = OAuthToken("tokenType", "access_token")
    private val listTweet = listOf(Tweet(nickname, 1))

    @Mock
    private lateinit var localRepository: LocalRepository

    @Mock
    private lateinit var twitterRepository: TwitterRepository

    @Mock
    private lateinit var appCredentials: AppCredentials

    private lateinit var twitterUseCase: TwitterUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        `when`(appCredentials.authorization).thenReturn(nickname)
        twitterUseCase = TwitterUseCase(twitterRepository, localRepository, appCredentials)
    }

    @Test
    fun shouldBeResultSuccessWithoutLocalToken() = runBlocking {

        Mockito.`when`(twitterRepository.retrieveToken(nickname, GRANT_TYPE))
            .thenReturn(Result.Success(oAuthToken))

        val tweetRequest = TweetRequest(oAuthToken.accessToken, nickname)

        `when`(twitterRepository.retrieveTweetsByNick(tweetRequest)).thenReturn(
            Result.Success(
                listTweet
            )
        )

        val result = twitterUseCase.getTweetersByNick(nickname)

        assertTrue(result is Result.Success)
    }

    @Test
    fun shouldBeResultSuccessWithtLocalToken() = runBlocking {

        Mockito.`when`(localRepository.getToken()).thenReturn(oAuthToken.accessToken)

        val tweetRequest = TweetRequest(oAuthToken.accessToken, nickname)

        `when`(twitterRepository.retrieveTweetsByNick(tweetRequest)).thenReturn(
            Result.Success(
                listTweet
            )
        )

        val result = twitterUseCase.getTweetersByNick(nickname)

        verify(twitterRepository, never()).retrieveToken(nickname, GRANT_TYPE)

        assertTrue(result is Result.Success)
    }

    @Test
    fun shouldBeResultFailure() = runBlocking {

        Mockito.`when`(localRepository.getToken()).thenReturn(oAuthToken.accessToken)

        val tweetRequest = TweetRequest(oAuthToken.accessToken, nickname)

        `when`(twitterRepository.retrieveTweetsByNick(tweetRequest)).thenReturn(
            Result.Failure(Error()))

        val result = twitterUseCase.getTweetersByNick(nickname)

        assertTrue(result is Result.Failure)
    }
}