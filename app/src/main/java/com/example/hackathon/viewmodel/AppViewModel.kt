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
    private val _isScanEnabled = mutableStateOf(true)

    val result: LiveData<ApiResponse> get() = _result
    val isLoading: State<Boolean> get() = _isLoading
    val isScanEnabled: State<Boolean> get() = _isScanEnabled

    fun updateResult(result: ApiResponse) {
        Log.i("ResultViewModel", "Result updated")
        _result.value = result
    }

    fun enableScan(flag: Boolean) {
        _isScanEnabled.value = flag
    }

    fun clearResult() {
        _result.value = null
        enableScan(true)
    }

    fun showLoader() {
        _isLoading.value = true
    }
    fun hideLoader() {
        _isLoading.value = false
    }
}