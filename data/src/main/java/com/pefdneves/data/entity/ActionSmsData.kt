package com.pefdneves.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(
    tableName = "actiondatasms",
    indices = [
        Index(value = ["action_data_id"], unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = Action::class,
            parentColumns = arrayOf("entry_id"),
            childColumns = arrayOf("action_data_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)

@TypeConverters
data class ActionSmsData(
    @ColumnInfo(name = "message") var message: String = "",
    @ColumnInfo(name = "recipient") var recipient: String = "",
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "action_data_id") var actionDataId: Long = 0
) : ActionData()
