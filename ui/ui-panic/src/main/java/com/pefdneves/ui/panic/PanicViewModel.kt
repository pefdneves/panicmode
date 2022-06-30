package com.pefdneves.ui.panic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pefdneves.domain.FetchActionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PanicViewModel @Inject constructor(
    private val fetchActionsUseCase: FetchActionsUseCase
) : ViewModel() {

    private val _isFinished: MutableLiveData<Boolean> = MutableLiveData(false)
    val isFinished: LiveData<Boolean> = _isFinished

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            fetchActionsUseCase().onSuccess {

            }.onFailure {
                // Error
            }
        }
    }
}
