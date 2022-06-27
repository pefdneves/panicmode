package com.pefdneves.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pefdneves.data.converters.RoomTypeConverters
import com.pefdneves.data.dao.ActionDao
import com.pefdneves.data.entity.Action
import com.pefdneves.data.entity.ActionDeleteFolder
import com.pefdneves.data.entity.ActionSmsData

@Database(entities = [Action::class, ActionSmsData::class, ActionDeleteFolder::class], version = 1, exportSchema = false)
@TypeConverters(RoomTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun actionDao(): ActionDao
}
