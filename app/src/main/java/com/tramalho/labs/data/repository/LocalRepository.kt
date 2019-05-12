package com.tramalho.labs.data.repository

import android.content.SharedPreferences
import com.tramalho.labs.data.infra.Constants.Companion.SHARED_PREF_TOKEN_KEY

class LocalRepository(private val sharedPreference: SharedPreferences) {

    fun getToken() = sharedPreference.getString(SHARED_PREF_TOKEN_KEY, "")

    fun saveToken(accessToken: String) {
        sharedPreference
            .edit()
            .putString(SHARED_PREF_TOKEN_KEY, accessToken)
            .apply()
    }

}