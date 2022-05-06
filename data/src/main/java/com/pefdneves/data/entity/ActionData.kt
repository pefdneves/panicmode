package com.pefdneves.data.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

abstract class ActionData {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "action_data_id")
    var actionDataId: Int = 0
}
