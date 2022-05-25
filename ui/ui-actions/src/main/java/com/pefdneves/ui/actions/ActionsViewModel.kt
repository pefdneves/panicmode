package com.pefdneves.ui.actions

import androidx.lifecycle.*
import com.pefdneves.data.Result
import com.pefdneves.data.entity.Action
import com.pefdneves.data.repository.ActionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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


}