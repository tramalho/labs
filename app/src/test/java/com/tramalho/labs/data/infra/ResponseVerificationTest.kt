package com.tramalho.labs.data.infra

import com.tramalho.labs.data.networkentities.Result
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import retrofit2.Response
import java.lang.IllegalArgumentException

class ResponseVerificationTest {

    private val exceptionMessage = "mock exception"

    private enum class MockStates {
        SUCCESS, FAILURE, EXCEPTION
    }

    @Test
    fun shouldReturnSuccessResult() = runBlocking {
        val result = call { mockResult(MockStates.SUCCESS) }
        assertTrue(result is Result.Success<Any>)
    }

    @Test
    fun shouldReturnError() = runBlocking {
        val result = call { mockResult(MockStates.FAILURE) }
        assertTrue(result is Result.Failure)
    }

    @Test
    fun shouldReturnErrorByException() = runBlocking {
        val result = call { mockResult(MockStates.EXCEPTION) }
        assertEquals(exceptionMessage, (result as Result.Failure).error.message)
    }

    private fun mockResult(mockStates: MockStates): CompletableDeferred<Response<Any>> {
        val response: Response<Any> = Mockito.spy(Response.success<Any>(""))
        val state = mockStates.equals(MockStates.SUCCESS)
        when (mockStates) {
            MockStates.EXCEPTION -> doThrow(IllegalArgumentException(exceptionMessage)).`when`(response).isSuccessful
            else -> doReturn(state).`when`(response).isSuccessful
        }

        return CompletableDeferred(response)
    }
}