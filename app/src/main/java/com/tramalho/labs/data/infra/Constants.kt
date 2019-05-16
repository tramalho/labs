package com.tramalho.labs.data.infra

class Constants {

    companion object {
        const val SHARED_PREF_TOKEN_KEY: String = "TOKEN_KEY"
        const val GRANT_TYPE = "client_credentials"
        const val PREF_KEY = "PREF_KEY"
        val AUTH_SUFFIX = "Basic"
        val TOKEN_SUFFIX = "Bearer"
        val DEFAULT_LANGUAGE = "pt"
        val PLAIN_TEXT = "PLAIN_TEXT"
        val TWEETER_ENDPOINT = "https://api.twitter.com/"
        val GOOGLE_ENDPOINT = "https://language.googleapis.com/"
        val TWEETER_LIST_EXTRA = "TWEETER_LIST_EXTRA"

        val TWEETER_NT = "TWEETER_NT"
        val GOOGLE_NT = "GOOGLE_NT"
        val MOCK_URL = "http://localhost:51384"
    }
}