package com.tramalho.labs.data.repository

import com.tramalho.labs.data.entity.AnalizeSentimentRequest
import com.tramalho.labs.data.infra.GoogleService
import com.tramalho.labs.data.infra.call

class GoogleRepository(private val service: GoogleService) {

    suspend fun retrieveSentimentAnalize(key: String, asr: AnalizeSentimentRequest) =
        call { service.requestSentimentAnalyze(key, asr) }
}