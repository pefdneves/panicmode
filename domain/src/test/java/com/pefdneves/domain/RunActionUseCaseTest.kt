package com.pefdneves.domain

import com.pefdneves.data.entity.Action
import com.pefdneves.data.entity.ActionSmsData
import com.pefdneves.data.entity.ActionType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RunActionUseCaseTest {

    private val mockRunSmsActionUseCase = mockk<RunSmsActionUseCase>()
    private val testSubject: RunActionUseCase = RunActionUseCaseImpl(mockRunSmsActionUseCase)

    @Test
    fun `given send sms action when use case is called then call RunSmsActionUseCase`() =
        runBlocking {
            val fakeResult = Result.success(Unit)
            val fakeAction = Action(
                ActionType.SEND_SMS,
                ActionSmsData(),
                0,
                false,
                0
            )
            coEvery { mockRunSmsActionUseCase.invoke(fakeAction) } returns fakeResult

            testSubject.invoke(fakeAction)

            coVerify(exactly = 1) {
                mockRunSmsActionUseCase.invoke(fakeAction)
            }
        }
}
