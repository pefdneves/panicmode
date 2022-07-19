package com.pefdneves.domain

import com.pefdneves.data.entity.Action
import com.pefdneves.data.repository.ActionRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchActionsUseCaseTest {

    private val mockRepository = mockk<ActionRepository>()
    private val testSubject: FetchActionsUseCase = FetchActionsUseCaseImpl(mockRepository)

    @Test
    fun `given action when use case is called then call repository and return list of actions`() = runBlocking {
        val mockResult = mockk<Result<List<Action>>>()
        coEvery { mockRepository.getActions() } returns mockResult

        val result = testSubject.invoke()

        assertEquals(result, mockResult)

        coVerify(exactly = 1) {
            mockRepository.getActions()
        }
    }
}
