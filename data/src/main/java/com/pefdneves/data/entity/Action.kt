package com.pefdneves.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Immutable model class for a Task. In order to compile with Room, we can't use @JvmOverloads to
 * generate multiple constructors.
 *
 * @param title title of the task
 * @param description description of the task
 * @param isCompleted whether or not this task is completed
 * @param id id of the task
 */
@Entity(
    tableName = "action",
    indices = [
        Index(value = ["entry_id"], unique = true)
    ]
)
data class Action @JvmOverloads constructor(
    @ColumnInfo(name = "type") var type: ActionType?,
    @ColumnInfo(name = "action_data") var actionData: ActionData?,
    @ColumnInfo(name = "delay") var delay: Long = 0,
    @ColumnInfo(name = "completed") var isCompleted: Boolean = false,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "entry_id") var entryId: Long = 0
)