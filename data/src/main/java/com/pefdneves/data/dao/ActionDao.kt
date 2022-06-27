package com.pefdneves.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pefdneves.data.entity.Action
import com.pefdneves.data.entity.ActionDeleteFolder
import com.pefdneves.data.entity.ActionSmsData

@Dao
interface ActionDao {

    @Query("SELECT * FROM `action`")
    fun observeActions(): LiveData<List<Action>>

    @Query("SELECT * FROM `actiondatasms` WHERE action_entry_id = :actionId")
    fun getActionDataSmsForAction(actionId: Long): ActionSmsData

    @Query("SELECT * FROM `actiondatadeletefolder` WHERE action_entry_id = :actionId")
    fun getActionDataDeleteFolderForAction(actionId: Long): ActionDeleteFolder

    @Query("SELECT * FROM `action` WHERE entry_id = :actionId")
    fun observeActionById(actionId: Long): LiveData<Action>

    @Query("SELECT * FROM `action`")
    suspend fun getActions(): List<Action>

    @Query("SELECT * FROM `action` WHERE entry_id = :actionId")
    suspend fun getActionById(actionId: Long): Action?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAction(action: Action): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertActionDataSms(actionData: ActionSmsData)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertActionDataDeleteFolder(actionData: ActionDeleteFolder)

    @Update
    suspend fun updateAction(action: Action): Int

    @Query("UPDATE `action` SET completed = :completed WHERE entry_id = :actionId")
    suspend fun updateCompleted(actionId: Long, completed: Boolean)

    @Query("DELETE FROM `action` WHERE entry_id = :actionId")
    suspend fun deleteActionById(actionId: Long): Int
}
