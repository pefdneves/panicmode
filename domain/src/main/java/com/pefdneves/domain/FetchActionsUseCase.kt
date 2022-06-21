package com.pefdneves.domain

import com.pefdneves.data.entity.Action
import com.pefdneves.data.repository.ActionRepository
import com.pefdneves.domain.base.SuspendedUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

interface FetchActionsUseCase : SuspendedUseCase<Result<List<Action>>>

class FetchActionsUseCaseImpl @Inject constructor(
    private val actionRepository: ActionRepository,
) : FetchActionsUseCase {
    override suspend fun invoke(): Result<List<Action>> {
        return actionRepository.getActions()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class FetchActionsUseCaseModule {
    @Binds
    abstract fun provideFetchActionsUseCase(fetchActionsUseCaseImpl: FetchActionsUseCaseImpl): FetchActionsUseCase
}
