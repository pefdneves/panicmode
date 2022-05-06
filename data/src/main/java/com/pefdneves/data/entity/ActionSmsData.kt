package com.pefdneves.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "actiondata",
    indices = [
        Index(value = ["trakt_id"], unique = true),
        Index(value = ["show_id"])
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
class ActionSmsData(
    @ColumnInfo(name = "message") var message: String = "",
    @ColumnInfo(name = "recipient") var recipient: String = "",
) : ActionData()
