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
import com.pefdneves.data.repository.ActionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActionsViewModel @Inject constructor(
    private val actionRepository: ActionRepository
) : ViewModel() {

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
            actionRepository.getActions().onSuccess {
                _actions.value = it
            }.onFailure {
                // Error
            }
        }
    }

    fun option1Clicked() {
        viewModelScope.launch {
            actionRepository.saveAction(
                Action(
                    type = ActionType.SEND_SMS,
                    actionData = ActionSmsData(
                        "teste",
                        "00351934335287"
                    )
                )
            )
            actionRepository.getActions().onSuccess {
                Log.e("teste", it.size.toString())
            }
            fetchData()
        }
    }
}
