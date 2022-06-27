package com.pefdneves.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(
    tableName = "actiondatadeletefolder",
    indices = [
        Index(value = ["action_data_delete_folder_id"], unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = Action::class,
            parentColumns = arrayOf("entry_id"),
            childColumns = arrayOf("action_entry_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)

@TypeConverters
data class ActionDeleteFolder(
    @ColumnInfo(name = "path") var path: String = "",
    @ColumnInfo(name = "action_entry_id") var actionEntryId: Long = 0,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "action_data_delete_folder_id") var actionDataId: Long = 0
) : ActionData()
