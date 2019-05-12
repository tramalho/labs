package com.tramalho.labs.data.infra

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Response

val UI = CoroutineScope(Dispatchers.Default + Job())

suspend fun <T : Any> call(block: () -> Deferred<Response<T>>): Result<T> {
    try {

        val response = block().await()

        return when {
            response.isSuccessful -> Result.Success(response.body()!!)
            else -> Result.Failure(Error(response.errorBody().toString()))
        }
    } catch (e: Exception) {
        return Result.Failure(Error(e.message))
    }
}