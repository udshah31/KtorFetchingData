package com.example.ktorfetchingdata.utils

import com.example.ktorfetchingdata.model.PostItem

sealed class Resource {
    object Loading : Resource()
    data class Success(val data: List<PostItem>) : Resource()
    data class Error(val message: Throwable) : Resource()
    object Empty : Resource()
}
