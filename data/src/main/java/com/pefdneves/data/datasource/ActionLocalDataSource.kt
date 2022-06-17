package com.pefdneves.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.pefdneves.data.dao.ActionDao
import com.pefdneves.data.entity.Action
import com.pefdneves.data.entity.ActionSmsData
import com.pefdneves.data.entity.ActionType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActionLocalDataSource @Inject constructor(
    private val actionDao: ActionDao
) : ActionsDataSource {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override fun observeActions(): LiveData<Result<List<Action>>> {
        return actionDao.observeActions().map {
            Result.success(mapActionDataToActions(it))
        }
    }

    private fun mapActionDataToActions(it: List<Action>): MutableList<Action> {
        val newList = mutableListOf<Action>()
        for (action in it) {
            newList.add(mapActionDataToAction(action))
        }
        return newList
    }

    private fun mapActionDataToAction(action: Action): Action {
        return when (action.type) {
            ActionType.SEND_SMS -> {
                action.copy(actionData = actionDao.getActionDataSmsForAction(action.entryId))
            }
            else -> action
        }
    }

    override suspend fun getActions(): Result<List<Action>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.success(mapActionDataToActions(actionDao.getActions()))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun observeAction(actionId: Long): LiveData<Result<Action>> {
        return actionDao.observeActionById(actionId).map {
            Result.success((mapActionDataToAction(it)))
        }
    }

    override suspend fun getAction(actionId: Long): Result<Action> = withContext(ioDispatcher) {
        try {
            val action = actionDao.getActionById(actionId)
            if (action != null) {
                return@withContext Result.success((mapActionDataToAction(action)))
            } else {
                return@withContext Result.failure((Exception("Action not found!")))
            }
        } catch (e: Exception) {
            return@withContext Result.failure((e))
        }
    }

    override suspend fun saveAction(action: Action) {
        return withContext(ioDispatcher) {
            actionDao.insertAction(action)
            when (action.type) {
                ActionType.SEND_SMS -> {
                    (action.actionData as? ActionSmsData)?.also {
                        actionDao.insertActionDataSms(it)
                    }
                }
                else -> {}
            }
        }
    }

    override suspend fun completeAction(actionId: Long) {
        return withContext(ioDispatcher) {
            actionDao.updateCompleted(actionId, true)
        }
    }

    override suspend fun deleteAction(actionId: Long) {
        return withContext(ioDispatcher) {
            actionDao.deleteActionById(actionId)
        }
    }
}
