package com.pefdneves.domain

import com.pefdneves.data.entity.Action
import com.pefdneves.data.entity.ActionSmsData
import com.pefdneves.data.entity.ActionType
import com.pefdneves.data.repository.ActionRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SaveActionUseCaseTest {

    private val mockRepository = mockk<ActionRepository>()
    private val testSubject: SaveActionUseCase = SaveActionUseCaseImpl(mockRepository)

    @Test
    fun `given action when use case is called then call repository`() =
        runBlocking {
            val fakeAction = Action(
                ActionType.SEND_SMS,
                ActionSmsData(),
                0,
                false,
                0
            )
            coEvery { mockRepository.saveAction(fakeAction) } just Runs

            testSubject.invoke(fakeAction)

            coVerify(exactly = 1) {
                mockRepository.saveAction(fakeAction)
            }
        }
}
