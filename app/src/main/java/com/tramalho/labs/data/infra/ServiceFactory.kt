package com.tramalho.labs.data.infra

import retrofit2.Retrofit

class ServiceFactory(val retrofit: Retrofit){

    inline fun <reified T> create() : T = retrofit.create(T::class.java)
}