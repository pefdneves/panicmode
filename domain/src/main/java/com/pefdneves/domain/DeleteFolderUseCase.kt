package com.pefdneves.domain

import com.pefdneves.core.IoDispatcher
import com.pefdneves.data.entity.Action
import com.pefdneves.data.entity.ActionDeleteFolder
import com.pefdneves.data.entity.ActionType
import com.pefdneves.domain.base.SuspendedUseCaseWithParams
import com.pefdneves.domain.exception.InvalidActionException
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import javax.inject.Inject

interface DeleteFolderUseCase : SuspendedUseCaseWithParams<Result<Unit>, Action>

class DeleteFolderUseCaseImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : DeleteFolderUseCase {

    override suspend fun invoke(param: Action): Result<Unit> {
        if (param.type != ActionType.DELETE_FOLDER || param.actionData !is ActionDeleteFolder) {
            return Result.failure(InvalidActionException("Invalid action type or action data type: ${param.type}"))
        }

        val actionData = param.actionData as? ActionDeleteFolder

        delay(param.delay)

        withContext(ioDispatcher) {
            actionData?.path?.also {
                deleteFiles(it)
            }
        }

        return Result.success(Unit)
    }

    @Throws(IOException::class)
    private fun deleteFiles(path: String) {
        val file = File(path)
        if (file.exists()) {
            val deleteCmd = "rm -r $path"
            val runtime = Runtime.getRuntime()
            try {
                runtime.exec(deleteCmd)
            } catch (e: IOException) {
                throw e
            }
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DeleteFolderUseCaseModule {
    @Binds
    abstract fun provideDeleteFolderUseCase(deleteFolderUseCaseImpl: DeleteFolderUseCaseImpl): DeleteFolderUseCase
}
