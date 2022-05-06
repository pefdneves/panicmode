package com.pefdneves.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    private const val DB_FILENAME = "actions.db"

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        val builder = Room.databaseBuilder(context, AppDatabase::class.java, DB_FILENAME)
            .fallbackToDestructiveMigration()
/*        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries()
        }*/
        return builder.build()
    }

    @Provides
    fun provideActionDao(db: AppDatabase) = db.actionDao()
}
