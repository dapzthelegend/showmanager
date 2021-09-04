/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.core.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Authorization interceptor class for network requests.
 */
class AuthorizationInterceptor : Interceptor {

    companion object {
        const val CLIENT_KEY =
            "yiCk1DW6WHWG58wjj3C4pB/WyhpokCeDeSQEXA5HaicgGh4pTUd+3/rMOR5xu1Yi"
        const val APPLICATION_ID =
            "AaQjHwTIQtkCOhtjJaN/nDtMdiftbzMWW5N8uRZ+DNX9LI8AOziS10eHuryBEcCI"
    }

    /**
     * Intercept chain request.
     *
     * @param chain The request chain.
     * @see Interceptor.intercept
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Parse-Client-Key", CLIENT_KEY)
            .addHeader("X-Parse-Application-Id", APPLICATION_ID)
            .build()

        return chain.proceed(request)
    }
}
