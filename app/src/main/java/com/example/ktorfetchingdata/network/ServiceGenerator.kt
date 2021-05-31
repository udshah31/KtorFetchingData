package com.example.ktorfetchingdata.network

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*

object ServiceGenerator {

    val client = HttpClient(Android) {
        install(DefaultRequest) {
            headers.append("Content-Type", "application/json")
        }

        install(JsonFeature) {
            serializer = GsonSerializer()
        }


        engine {
            connectTimeout = 100_000
            socketTimeout = 100_000
        }
    }

}