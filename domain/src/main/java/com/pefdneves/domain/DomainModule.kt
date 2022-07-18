package com.pefdneves.domain

import android.telephony.SmsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideSmsManager(): SmsManager? = SmsManager.getDefault()
}
