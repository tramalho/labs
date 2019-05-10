package com.tramalho.labs.data.repository

import com.tramalho.labs.data.infra.TwitterService
import com.tramalho.labs.data.infra.call

class TwitterRepository(private val service: TwitterService) {

    suspend fun retrieveToken(grantType: String, auth: String) =
        call { service.requestToken(auth, grantType) }
}