package com.pefdneves.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "action",
    indices = [
        Index(value = ["entry_id"], unique = true)
    ]
)
data class Action @JvmOverloads constructor(
    @ColumnInfo(name = "type") var type: ActionType = ActionType.NONE,
    @ColumnInfo(name = "action_data") var actionData: ActionData?,
    @ColumnInfo(name = "delay") var delay: Long = 0,
    @ColumnInfo(name = "completed") var isCompleted: Boolean = false,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "entry_id") var entryId: Long = 0
)
