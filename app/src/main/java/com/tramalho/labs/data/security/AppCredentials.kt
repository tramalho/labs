package com.tramalho.labs.data.security

import android.util.Base64

class AppCredentials(private val consumerKey: String, private val consumerSecret: String) {

    fun authorizarion() =
        String(Base64.encode("$consumerKey:$consumerSecret".toByteArray(), Base64.DEFAULT))
}