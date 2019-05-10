package com.tramalho.labs.data.infra

import retrofit2.Retrofit

class ServiceFactory<T>(private val retrofit: Retrofit){

    fun create(clazz: Class<T>) = retrofit.create(clazz::class.java)
}