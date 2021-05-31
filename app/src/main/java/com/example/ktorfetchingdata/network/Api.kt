package com.example.ktorfetchingdata.network

import com.example.ktorfetchingdata.model.PostItem
import io.ktor.client.request.*
import javax.inject.Inject

class Api @Inject constructor() {
    suspend fun getPost(): List<PostItem> {
        return ServiceGenerator.client.use {
            it.get {
                url("https://jsonplaceholder.typicode.com/posts")
            }
        }
    }
}