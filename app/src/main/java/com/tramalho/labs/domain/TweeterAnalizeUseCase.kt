package com.tramalho.labs.domain

import com.tramalho.labs.data.entity.*
import com.tramalho.labs.data.infra.Result
import com.tramalho.labs.data.repository.GoogleRepository

class TweeterAnalizeUseCase(
    private val repository: GoogleRepository,
    private val appCredentials: AppCredentials
) {

    suspend fun getAnalizeFromTweet(content: String): Result<SentimentType> {

        val result = retrieveAnalize(content)

        return when (result) {
            is Result.Success -> configSentiment(result.data)
            is Result.Failure -> result
        }
    }

    private fun configSentiment(data: SentimentResponse): Result<SentimentType> {
        val score = data.documentSentiment.score
        return when {
            score < -0.5 -> Result.Success(SentimentType.UNHAPPY)
            score > 0.5 -> Result.Success(SentimentType.HAPPY)
            else -> Result.Success(SentimentType.NEUTRAL)
        }

    }

    private suspend fun retrieveAnalize(content: String): Result<SentimentResponse> {
        val request = AnalizeSentimentRequest(Document(content = content))
        return repository.retrieveSentimentAnalize(appCredentials.gcpAPIKey, request)
    }
}