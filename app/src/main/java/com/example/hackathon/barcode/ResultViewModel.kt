package com.example.hackathon.barcode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackathon.api.AlternativeItem

class ResultViewModel : ViewModel() {
    private val _result = MutableLiveData<AlternativeItem>()
    val result: LiveData<AlternativeItem> get() = _result

    fun updateResult(result: AlternativeItem) {
        Log.i("ResultViewModel", "Result updated")
        _result.value = result
    }

    fun clearResult() {
        _result.value = null
    }
}