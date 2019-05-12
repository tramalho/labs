package com.tramalho.labs.domain

import com.tramalho.labs.data.entity.*
import com.tramalho.labs.data.infra.Constants.Companion.GRANT_TYPE
import com.tramalho.labs.data.infra.Result
import com.tramalho.labs.data.repository.LocalRepository
import com.tramalho.labs.data.repository.TwitterRepository

class TwitterUseCase(
    private var twitterRepository: TwitterRepository,
    private val localRepository: LocalRepository,
    private val appCredential: AppCredentials
) {

    suspend fun getTweetersByNick(nick: String): Result<List<Tweet>> {

        val resultToken = resolveToken()

        return when (resultToken) {
            is Result.Success -> retrieveTweets(resultToken.data, nick)
            is Result.Failure -> resultToken
        }
    }

    private suspend fun resolveToken(): Result<String> {

        val accessToken = localRepository.getToken()

        val result: Result<String>

        if (accessToken.isNullOrEmpty()) {

            val tokenResult =
                twitterRepository.retrieveToken(appCredential.authorization, GRANT_TYPE)

            result = when (tokenResult) {
                is Result.Success -> configOAuthToken(tokenResult.data)
                is Result.Failure -> tokenResult
            }
        } else {
            result = Result.Success(accessToken)
        }

        return result
    }

    private fun configOAuthToken(data: OAuthToken): Result.Success<String> {

        localRepository.saveToken(data.accessToken)

        return Result.Success(data.accessToken)
    }

    private suspend fun retrieveTweets(bearerToken: String, nick: String): Result<List<Tweet>> {

        val request = TweetRequest(bearerToken, nick)
        val result = twitterRepository.retrieveTweetsByNick(request)

        return when (result) {
            is Result.Success -> Result.Success(result.data)
            is Result.Failure -> result
        }
    }
}