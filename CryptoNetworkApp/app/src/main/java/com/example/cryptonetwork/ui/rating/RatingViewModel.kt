package com.example.cryptonetwork.ui.rating

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RatingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is rating Fragment"
    }
    val text: LiveData<String> = _text
}