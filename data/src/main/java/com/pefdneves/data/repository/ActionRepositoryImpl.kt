package com.pefdneves.data.repository

import androidx.lifecycle.LiveData
import com.pefdneves.data.Result
import com.pefdneves.data.datasource.ActionLocalDataSource
import com.pefdneves.data.entity.Action
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class ActionRepositoryImpl @Inject constructor(
    private val actionLocalDataSource: ActionLocalDataSource
) : ActionRepository {

    override fun observeActions(): LiveData<Result<List<Action>>> = actionLocalDataSource.observeActions()

    override suspend fun getActions(): Result<List<Action>> = actionLocalDataSource.getActions()

    override fun observeAction(actionId: Long): LiveData<Result<Action>> = actionLocalDataSource.observeAction(actionId)

    override suspend fun getAction(actionId: Long): Result<Action> = actionLocalDataSource.getAction(actionId)

    override suspend fun saveAction(action: Action) = actionLocalDataSource.saveAction(action)

    override suspend fun completeAction(actionId: Long) = actionLocalDataSource.completeAction(actionId)

    override suspend fun deleteAction(actionId: Long) = actionLocalDataSource.deleteAction(actionId)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class ActionsRepositoryModule {
    @Binds
    abstract fun provideActionsRepository(actionRepositoryImpl: ActionRepositoryImpl): ActionRepository
}