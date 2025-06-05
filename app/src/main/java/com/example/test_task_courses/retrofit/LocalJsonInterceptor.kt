package com.example.test_task_courses.retrofit

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody


class LocalJsonInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        return if (url.endsWith("courses.json")) {
            val json = context.assets.open("courses.json").bufferedReader().use { it.readText() }

            Response.Builder()
                .code(200)
                .message("OK")
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .body(json.toResponseBody("application/json".toMediaTypeOrNull()))
                .addHeader("content-type", "application/json")
                .build()
        } else {
            chain.proceed(request)
        }
    }
}

