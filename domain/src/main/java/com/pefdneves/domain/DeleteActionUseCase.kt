package com.pefdneves.domain

import com.pefdneves.data.entity.Action
import com.pefdneves.data.repository.ActionRepository
import com.pefdneves.domain.base.SuspendedUseCaseWithParams
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

interface DeleteActionUseCase : SuspendedUseCaseWithParams<Unit, Action>

class DeleteActionUseCaseImpl @Inject constructor(
    private val actionRepository: ActionRepository,
) : DeleteActionUseCase {
    override suspend fun invoke(param: Action) {
        actionRepository.deleteAction(param.entryId)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DeleteActionUseCaseModule {
    @Binds
    abstract fun provideDeleteActionUseCase(deleteActionUseCaseImpl: DeleteActionUseCaseImpl): DeleteActionUseCase
}
