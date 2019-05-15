package com.tramalho.labs.data.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tweet(
    val text: String, val id: Long,
    @field:Json(name = "created_at") var createdAt: String = ""
) : Parcelable