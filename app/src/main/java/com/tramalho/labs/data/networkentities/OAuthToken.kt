package com.tramalho.labs.data.networkentities

import com.squareup.moshi.Json

data class OAuthToken(@Json(name= "token_type") val tokenType: String = "",
                      @Json(name= "access_token") val accessToken: String = "") {
}