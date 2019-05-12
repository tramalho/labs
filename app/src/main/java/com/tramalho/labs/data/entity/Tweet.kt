package com.tramalho.labs.data.entity

import com.squareup.moshi.Json

data class Tweet(
    val text: String, val id: Long,
    @Json(name = "created_at") val createdAt: String = ""
)