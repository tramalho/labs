package com.tramalho.labs.data.entity

data class TweetRequest(val token: String,
                        val screenName: String,
                        val count: Int = 40,
                        var sinceId: String? = null)