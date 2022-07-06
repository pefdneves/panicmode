package com.pefdneves.domain

import com.pefdneves.data.entity.Action
import com.pefdneves.data.entity.ActionSmsData
import com.pefdneves.data.entity.ActionType
import com.pefdneves.domain.base.SuspendedUseCaseWithParams
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

interface RunActionUseCase : SuspendedUseCaseWithParams<Result<Unit>, Action>

class RunActionUseCaseImpl @Inject constructor(
    private val runSmsActionUseCase: RunSmsActionUseCase
) : RunActionUseCase {
    override suspend fun invoke(param: Action): Result<Unit> {
        return when {
            param.type == ActionType.SEND_SMS && param.actionData is ActionSmsData -> {
                runSmsActionUseCase(param)
            }
            else -> {
                Result.failure(InvalidActionException("Could not validate action type to execute"))
            }
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RunActionUseCaseModule {
    @Binds
    abstract fun provideRunActionUseCase(runActionUseCaseImpl: RunActionUseCaseImpl): RunActionUseCase
}
