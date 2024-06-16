package com.example.hackathon.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackathon.api.ApiResponse

class AppViewModel : ViewModel() {
    private val _result = MutableLiveData<ApiResponse>()
    private val _isLoading = mutableStateOf(false)

    val result: LiveData<ApiResponse> get() = _result
    val isLoading: State<Boolean> get() = _isLoading

    fun updateResult(result: ApiResponse) {
        Log.i("ResultViewModel", "Result updated")
        _result.value = result
    }

    fun clearResult() {
        _result.value = null
    }

    fun showLoader() {
        _isLoading.value = true
    }
    fun hideLoader() {
        _isLoading.value = false
    }
}