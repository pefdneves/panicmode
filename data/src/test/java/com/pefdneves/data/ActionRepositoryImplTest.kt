package com.pefdneves.data

import androidx.lifecycle.LiveData
import com.pefdneves.data.datasource.ActionLocalDataSource
import com.pefdneves.data.entity.Action
import com.pefdneves.data.repository.ActionRepository
import com.pefdneves.data.repository.ActionRepositoryImpl
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ActionRepositoryImplTest {

    private val mockDataSource = mockk<ActionLocalDataSource>()
    private val testSubject: ActionRepository = ActionRepositoryImpl(mockDataSource)

    @Test
    fun `given actionId when observeAction is called then call data source`() = runBlocking {
        val fakeId = 1L
        coEvery { mockDataSource.observeAction(fakeId) } returns mockk()

        testSubject.observeAction(fakeId)

        coVerify(exactly = 1) {
            mockDataSource.observeAction(fakeId)
        }
    }

    @Test
    fun `given actionId when getAction is called then call data source`() = runBlocking {
        val fakeId = 1L
        coEvery { mockDataSource.getAction(fakeId) } returns mockk()

        testSubject.getAction(fakeId)

        coVerify(exactly = 1) {
            mockDataSource.getAction(fakeId)
        }
    }

    @Test
    fun `given actionId when completeAction is called then call data source`() = runBlocking {
        val fakeId = 1L
        coEvery { mockDataSource.completeAction(fakeId) } returns mockk()

        testSubject.completeAction(fakeId)

        coVerify(exactly = 1) {
            mockDataSource.completeAction(fakeId)
        }
    }

    @Test
    fun `given actionId when deleteAction is called then call data source`() = runBlocking {
        val fakeId = 1L
        coEvery { mockDataSource.deleteAction(fakeId) } returns mockk()

        testSubject.deleteAction(fakeId)

        coVerify(exactly = 1) {
            mockDataSource.deleteAction(fakeId)
        }
    }

    @Test
    fun `given action when saveAction is called then call data source`() = runBlocking {
        val mockAction = mockk<Action>()
        coEvery { mockDataSource.saveAction(mockAction) } just Runs

        testSubject.saveAction(mockAction)

        coVerify(exactly = 1) {
            mockDataSource.saveAction(mockAction)
        }
    }

    @Test
    fun `when getActions is called then call data source`() = runBlocking {
        val mockResponse = mockk<Result<List<Action>>>()
        coEvery { mockDataSource.getActions() } returns mockResponse

        val result = testSubject.getActions()

        coVerify(exactly = 1) {
            mockDataSource.getActions()
        }
        assertEquals(mockResponse, result)
    }

    @Test
    fun `when observeActions is called then call data source`() = runBlocking {
        val mockResponse = mockk<LiveData<Result<List<Action>>>>()
        coEvery { mockDataSource.observeActions() } returns mockResponse

        val result = testSubject.observeActions()

        coVerify(exactly = 1) {
            mockDataSource.observeActions()
        }
        assertEquals(mockResponse, result)
    }
}
