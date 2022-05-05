package com.pefdneves.data.datasource

import androidx.lifecycle.LiveData
import com.pefdneves.data.entity.Action
import com.pefdneves.data.Result

interface ActionsDataSource {

    fun observeActions(): LiveData<Result<List<Action>>>

    suspend fun getActions(): Result<List<Action>>

    fun observeAction(actionId: Long): LiveData<Result<Action>>

    suspend fun getAction(actionId: Long): Result<Action>

    suspend fun saveAction(action: Action)

    suspend fun completeAction(actionId: Long)

    suspend fun deleteAction(actionId: Long)

}