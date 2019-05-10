package com.tramalho.labs.data.infra

import com.tramalho.labs.data.networkentities.OAuthToken
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST


interface TwitterService {

    @FormUrlEncoded
    @POST("/oauth2/token")
    fun requestToken(@Header("Authorization") auth: String, @Field("grant_type") grantType: String): Deferred<Response<OAuthToken>>
}