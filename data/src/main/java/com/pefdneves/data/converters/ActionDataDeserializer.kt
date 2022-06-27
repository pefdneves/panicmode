package com.pefdneves.data.converters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.pefdneves.data.entity.ActionData
import com.pefdneves.data.entity.ActionDeleteFolder
import com.pefdneves.data.entity.ActionSmsData
import org.json.JSONException
import java.lang.reflect.Type

class ActionDataDeserializer : JsonDeserializer<ActionData> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ActionData? {
        try {
            json as JsonObject?

            return when {
                json.hasAllFieldsFor(ActionSmsData::class.java) -> {
                    context?.deserialize(json, ActionSmsData::class.java)
                }
                json.hasAllFieldsFor(ActionDeleteFolder::class.java) -> {
                    context?.deserialize(json, ActionDeleteFolder::class.java)
                }
                else -> {
                    null
                }
            }
        } catch (ex: Exception) {
            throw JSONException(ex.message, ex.cause)
        }
    }

    private fun JsonObject?.hasAllFieldsFor(clazz: Type): Boolean {
        val fields = clazz.javaClass.declaredFields
        if (fields.isEmpty() || this == null) return false

        var hasFields = true
        for (field in fields) {
            hasFields = hasFields && has(field.name)
        }

        return hasFields
    }
}
