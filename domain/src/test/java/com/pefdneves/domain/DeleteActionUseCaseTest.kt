package com.pefdneves.domain

import com.pefdneves.data.entity.Action
import com.pefdneves.data.repository.ActionRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DeleteActionUseCaseTest {

    private val mockRepository = mockk<ActionRepository>()
    private val testSubject: DeleteActionUseCase = DeleteActionUseCaseImpl(mockRepository)

    @Test
    fun `given action when use case is called then call repository`() = runBlocking {
        val fakeId = 1L
        val mockAction = mockk<Action>().apply {
            every { entryId } returns fakeId
        }
        coEvery { mockRepository.deleteAction(fakeId) } just Runs

        testSubject.invoke(mockAction)

        coVerify(exactly = 1) {
            mockRepository.deleteAction(fakeId)
        }
    }
}
