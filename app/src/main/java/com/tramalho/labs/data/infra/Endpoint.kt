package com.tramalho.labs.data.infra

import com.tramalho.labs.data.entity.OAuthToken
import com.tramalho.labs.data.entity.Tweet
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*


interface TwitterService {

    @FormUrlEncoded
    @POST("/oauth2/token")
    fun requestToken(@Header("Authorization") auth: String,
                     @Field("grant_type") grantType: String): Deferred<Response<OAuthToken>>

    @GET("statuses/user_timeline.json")
    fun requestTweetByNick(@Header("Authorization") auth: String,
                           @Query("screen_name") screenName: String,
                           @Query("count") count: Int,
                           @Query("since_id") sinceId: String?): Deferred<Response<List<Tweet>>>
}