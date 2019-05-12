package com.tramalho.labs.data.entity

import com.squareup.moshi.Json

data class OAuthToken(
    @field:Json(name = "access_token")
    val accessToken: String,
    @field:Json(name = "token_type")
    val tokenType: String
)