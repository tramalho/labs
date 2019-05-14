package com.tramalho.labs.data.entity

import com.tramalho.labs.data.infra.Constants

data class AnalizeSentimentRequest(val document: Document)

data class Document(
    val type: String = Constants.PLAN_TEXT,
    val language: String = Constants.DEFAULT_LANGUAGE,
    val content: String
)

data class SentimentResponse(val documentSentiment: DocumentSentiment)

data class DocumentSentiment(val magninute: Double, val score: Double)

 enum class SentimentType {UNHAPPY, NEUTRAL, HAPPY}