package com.pefdneves.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pefdneves.data.dao.ActionDao
import com.pefdneves.data.entity.Action
import com.pefdneves.data.entity.ActionSmsData

@Database(entities = [Action::class, ActionSmsData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun actionDao(): ActionDao
}
