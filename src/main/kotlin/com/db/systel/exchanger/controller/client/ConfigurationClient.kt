package com.db.systel.exchanger.controller.client

import okhttp3.OkHttpClient
import okhttp3.Request

class ConfigurationClient {

    fun welcomeClient(): String {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://www.thunderclient.com/welcome")
            .build()
        val response = client.newCall(request).execute()
        return response.message
    }

}
