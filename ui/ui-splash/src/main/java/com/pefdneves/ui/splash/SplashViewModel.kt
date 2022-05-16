package com.pefdneves.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pefdneves.ui.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SplashViewModel @Inject constructor() : ViewModel() {

    private val _isFirstRun = MutableLiveData<Event<Boolean>>()
    val isFirstRun: LiveData<Event<Boolean>> = _isFirstRun

    init {
        //TODO: get from shared preferences
        _isFirstRun.value = Event(false)
    }

}