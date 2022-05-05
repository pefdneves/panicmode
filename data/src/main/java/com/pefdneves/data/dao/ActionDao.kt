package com.pefdneves.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pefdneves.data.entity.Action

@Dao
interface ActionDao {

    @Query("SELECT * FROM `action`")
    fun observeActions(): LiveData<List<Action>>

    @Query("SELECT * FROM `action` WHERE entry_id = :actionId")
    fun observeActionById(actionId: Long): LiveData<Action>

    @Query("SELECT * FROM `action`")
    suspend fun getActions(): List<Action>

    @Query("SELECT * FROM `action` WHERE entry_id = :actionId")
    suspend fun getActionById(actionId: Long): Action?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAction(action: Action)

    @Update
    suspend fun updateAction(action: Action): Int

    @Query("UPDATE `action` SET completed = :completed WHERE entry_id = :actionId")
    suspend fun updateCompleted(actionId: Long, completed: Boolean)

    @Query("DELETE FROM `action` WHERE entry_id = :actionId")
    suspend fun deleteActionById(actionId: Long): Int
}
