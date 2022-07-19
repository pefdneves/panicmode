package com.pefdneves.domain

import android.telephony.SmsManager
import com.pefdneves.data.entity.Action
import com.pefdneves.data.entity.ActionData
import com.pefdneves.data.entity.ActionSmsData
import com.pefdneves.data.entity.ActionType
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RunSmsActionUseCaseTest {

    private val mockSmsManager = mockk<SmsManager>()
    private val testSubject: RunSmsActionUseCase = RunSmsActionUseCaseImpl(mockSmsManager)

    @Test
    fun `given send sms action when use case is called then return success`() = runBlocking {
        val fakeDestination = "1"
        val fakeMessage = "a"
        val fakeAction = Action(
            ActionType.SEND_SMS,
            ActionSmsData(
                fakeMessage,
                fakeDestination,
                0,
                0
            ),
            0,
            false,
            0
        )

        every {
            mockSmsManager.sendTextMessage(fakeDestination, null, fakeMessage, null, null)
        } just Runs

        val result = testSubject.invoke(fakeAction)

        assert(result.isSuccess)

        verify(exactly = 1) {
            mockSmsManager.sendTextMessage(fakeDestination, null, fakeMessage, null, null)
        }
    }

    @Test
    fun `given send sms action with invalid data when use case is called then return failure`() =
        runBlocking {
            val fakeAction = Action(
                ActionType.DELETE_FOLDER,
                ActionData(),
                0,
                false,
                0
            )

            val result = testSubject.invoke(fakeAction)

            assert(result.isFailure)

            verify(exactly = 0) {
                mockSmsManager.sendTextMessage(any(), any(), any(), any(), any())
            }
        }
}
