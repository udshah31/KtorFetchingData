package com.example.ktorfetchingdata.repository

import com.example.ktorfetchingdata.model.PostItem
import com.example.ktorfetchingdata.network.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: Api
) {
    fun getPost(): Flow<List<PostItem>> = flow {
        emit(api.getPost())
    }.flowOn(Dispatchers.IO)
}