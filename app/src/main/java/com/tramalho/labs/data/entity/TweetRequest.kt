package com.tramalho.labs.data.entity

import retrofit2.http.Query

data class TweetRequest(val token: String,
                        val screenName: String,
                        val count: Int = 20,
                        var sinceId: String? = null)