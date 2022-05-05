package com.pefdneves.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.pefdneves.data.Result
import com.pefdneves.data.dao.ActionDao
import com.pefdneves.data.entity.Action
import com.pefdneves.data.Result.Success
import com.pefdneves.data.Result.Error
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ActionLocalDataSource internal constructor(
    private val actionDao: ActionDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): ActionsDataSource {

    override fun observeActions(): LiveData<Result<List<Action>>> {
        return actionDao.observeActions().map {
            Success(it)
        }
    }

    override suspend fun getActions(): Result<List<Action>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(actionDao.getActions())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override fun observeAction(actionId: Long): LiveData<Result<Action>> {
        return actionDao.observeActionById(actionId).map {
            Success(it)
        }
    }

    override suspend fun getAction(actionId: Long): Result<Action> = withContext(ioDispatcher) {
        try {
            val action = actionDao.getActionById(actionId)
            if (action != null) {
                return@withContext Success(action)
            } else {
                return@withContext Error(Exception("Action not found!"))
            }
        } catch (e: Exception) {
            return@withContext Error(e)
        }
    }

    override suspend fun saveAction(action: Action) {
        return withContext(ioDispatcher) {
            actionDao.insertAction(action)
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