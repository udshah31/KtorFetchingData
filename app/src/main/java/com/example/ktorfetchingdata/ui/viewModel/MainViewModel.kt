package com.example.ktorfetchingdata.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorfetchingdata.repository.Repository
import com.example.ktorfetchingdata.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state: MutableStateFlow<Resource> = MutableStateFlow(Resource.Empty)
    val state: StateFlow<Resource> = _state

    fun getPost() = viewModelScope.launch {
        repository.getPost()
            .onStart {
                _state.value = Resource.Loading
            }.catch { e ->
                _state.value = Resource.Error(e)
            }.collect { response ->
                _state.value = Resource.Success(response)
            }
    }


}