package com.example.kotlinmvvm_7.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val reponse = request.newBuilder().url(request.url()).build()

        Timber.d(reponse.toString())
        return chain.proceed(reponse)
    }

}