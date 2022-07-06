package com.pefdneves.ui.panic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pefdneves.core.DefaultDispatcher
import com.pefdneves.domain.FetchActionsUseCase
import com.pefdneves.domain.RunActionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PanicViewModel @Inject constructor(
    private val fetchActionsUseCase: FetchActionsUseCase,
    private val runActionUseCase: RunActionUseCase,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _isFinished: MutableLiveData<Boolean> = MutableLiveData(false)
    val isFinished: LiveData<Boolean> = _isFinished

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            fetchActionsUseCase().onSuccess {
                for (action in it) {
                    withContext(defaultDispatcher) {
                        runActionUseCase(action).onFailure { exception ->
                            Timber.d(exception)
                            // TODO: Log to file
                        }
                    }
                }
            }.onFailure {
                Timber.d("Error fetching actions")
            }
        }
    }
}
