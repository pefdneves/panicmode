package com.pefdneves.domain

import android.telephony.SmsManager
import com.pefdneves.data.entity.Action
import com.pefdneves.data.entity.ActionSmsData
import com.pefdneves.data.entity.ActionType
import com.pefdneves.domain.base.SuspendedUseCaseWithParams
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.delay
import javax.inject.Inject

interface RunSmsActionUseCase : SuspendedUseCaseWithParams<Result<Unit>, Action>

class RunSmsActionUseCaseImpl @Inject constructor() : RunSmsActionUseCase {

    override suspend fun invoke(param: Action): Result<Unit> {
        if (param.type != ActionType.SEND_SMS || param.actionData !is ActionSmsData) {
            return Result.failure(InvalidActionException("Invalid action type or action data type: ${param.type}"))
        }

        val actionData = param.actionData as? ActionSmsData

        delay(param.delay)

        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(actionData?.recipient, null, actionData?.message, null, null)
        } catch (ex: Exception) {
            return Result.failure(ex)
        }

        return Result.success(Unit)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RunSmsActionUseCaseModule {
    @Binds
    abstract fun provideRunSmsActionUseCase(runSmsActionUseCaseImpl: RunSmsActionUseCaseImpl): RunSmsActionUseCase
}
