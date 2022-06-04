package com.pefdneves.ui.actions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.pefdneves.data.Result
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

    private val _actions: LiveData<List<Action>> =
        actionRepository.observeActions().distinctUntilChanged().switchMap { filterActions(it) }
    val actions: LiveData<List<Action>> = _actions

    val empty: LiveData<Boolean> = Transformations.map(_actions) {
        it.isEmpty()
    }

    private fun filterActions(actions: Result<List<Action>>): LiveData<List<Action>> {
        return MutableLiveData(listOf())
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
        }
    }
}
