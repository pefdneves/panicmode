package com.pefdneves.data.converters

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.pefdneves.data.entity.ActionData
import com.pefdneves.data.entity.ActionType

class RoomTypeConverters {

    @TypeConverter
    fun toActionType(value: Int) = enumValues<ActionType>()[value]

    @TypeConverter
    fun fromActionType(value: ActionType) = value.ordinal

    @TypeConverter
    fun toActionData(value: String?): ActionData? {
        return if (value == null) null else {
            val gson = GsonBuilder().apply {
                registerTypeAdapter(ActionData::class.java, ActionDataDeserializer())
            }.create()
            gson.fromJson(value, ActionData::class.java)
        }
    }

    @TypeConverter
    fun fromActionData(actionData: ActionData?): String? {
        return GsonBuilder().create().toJson(actionData)
    }
}
