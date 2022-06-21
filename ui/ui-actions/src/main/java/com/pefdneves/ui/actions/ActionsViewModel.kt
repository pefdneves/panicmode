package com.pefdneves.ui.actions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pefdneves.data.entity.Action
import com.pefdneves.data.entity.ActionSmsData
import com.pefdneves.data.entity.ActionType
import com.pefdneves.domain.DeleteActionUseCase
import com.pefdneves.domain.FetchActionsUseCase
import com.pefdneves.domain.SaveActionUseCase
import com.pefdneves.ui.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActionsViewModel @Inject constructor(
    private val fetchActionsUseCase: FetchActionsUseCase,
    private val deleteActionUseCase: DeleteActionUseCase,
    private val saveActionUseCase: SaveActionUseCase
) : ViewModel() {

    private val _deleteActionPopup = MutableLiveData<Event<() -> Unit>>()
    val deleteActionPopup: LiveData<Event<() -> Unit>> = _deleteActionPopup

    private val _actions: MutableLiveData<List<Action>> = MutableLiveData(listOf())
    val actions: LiveData<List<Action>> = _actions

    val empty: LiveData<Boolean> = Transformations.map(_actions) {
        it.isEmpty()
    }

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            fetchActionsUseCase().onSuccess {
                _actions.value = it
            }.onFailure {
                // Error
            }
        }
    }

    fun onActionClicked(action: Action) {
        _deleteActionPopup.value = Event { deleteAction(action) }
    }

    private fun deleteAction(action: Action) {
        viewModelScope.launch {
            deleteActionUseCase(action)
            fetchData()
        }
    }

    fun option1Clicked() {
        viewModelScope.launch {
            saveActionUseCase(
                Action(
                    type = ActionType.SEND_SMS,
                    actionData = ActionSmsData(
                        "teste",
                        "00351934335287"
                    )
                )
            )
            fetchActionsUseCase().onSuccess {
                Log.e("teste", it.size.toString())
            }
            fetchData()
        }
    }
}
