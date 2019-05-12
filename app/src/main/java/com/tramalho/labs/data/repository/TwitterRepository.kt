package com.tramalho.labs.data.repository

import com.tramalho.labs.data.entity.TweetRequest
import com.tramalho.labs.data.infra.Constants
import com.tramalho.labs.data.infra.TwitterService
import com.tramalho.labs.data.infra.call

class TwitterRepository(private val service: TwitterService) {

    suspend fun retrieveToken(auth: String, grantType: String) =
        call { service.requestToken("${Constants.AUTH_SUFFIX} ${auth}", grantType) }

    suspend fun retrieveTweetsByNick(request: TweetRequest) = call {
        service.requestTweetByNick(
            "${Constants.TOKEN_SUFFIX} ${request.token}",
            request.screenName,
            request.count,
            request.sinceId
        )
    }
}