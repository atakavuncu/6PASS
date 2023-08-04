package com.example.altipass.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()

    val text: LiveData<String> = _index.map { "$it" }


    fun setIndex(index: Int) {
        _index.value = index
    }
}