package com.pefdneves.domain

import com.pefdneves.data.entity.Action
import com.pefdneves.data.repository.ActionRepository
import com.pefdneves.domain.base.SuspendedUseCaseWithParams
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

interface SaveActionUseCase : SuspendedUseCaseWithParams<Unit, Action>

class SaveActionUseCaseImpl @Inject constructor(
    private val actionRepository: ActionRepository,
) : SaveActionUseCase {
    override suspend fun invoke(param: Action) {
        actionRepository.saveAction(param)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class SaveActionUseCaseModule {
    @Binds
    abstract fun provideSaveActionUseCase(saveActionUseCaseImpl: SaveActionUseCaseImpl): SaveActionUseCase
}
