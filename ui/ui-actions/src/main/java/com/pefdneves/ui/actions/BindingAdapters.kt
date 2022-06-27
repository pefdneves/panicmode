package com.pefdneves.ui.actions

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pefdneves.data.entity.Action
import com.pefdneves.data.entity.ActionData
import com.pefdneves.data.entity.ActionDeleteFolder
import com.pefdneves.data.entity.ActionSmsData
import com.pefdneves.data.entity.ActionType
import com.pefdneves.ui.common.R as UiCommonR

@BindingAdapter("app:actions")
fun setActions(listView: RecyclerView, items: List<Action>?) {
    items?.let {
        (listView.adapter as? ActionsAdapter)?.submitList(items)
    }
}

@BindingAdapter(value = ["bind:actionTypeToItemDescription", "bind:actionDescriptionToItemDescription"], requireAll = true)
fun setItemDescription(textView: TextView, type: ActionType?, data: ActionData?) {
    data?.also { actionData ->
        type?.also { actionType ->
            val context = textView.context
            textView.text = when (actionType) {
                ActionType.SEND_SMS -> {
                    val action = actionData as ActionSmsData
                    "${action.actionDataId} - ${action.recipient} : ${action.message}"
                }
                ActionType.DELETE_FOLDER -> {
                    val action = actionData as ActionDeleteFolder
                    "${action.actionDataId} - path:${action.path}"
                }
                else -> context.getString(UiCommonR.string.default_error)
            }
        }
    }
}

@BindingAdapter("bind:actionTypeToItemType")
fun setItemTitle(textView: TextView, type: ActionType?) {
    val context = textView.context
    type?.also {
        textView.text = when (type) {
            ActionType.SEND_SMS -> context.getString(R.string.action_type_title_sms)
            ActionType.DELETE_FOLDER -> context.getString(R.string.action_type_delete_folder)
            else -> context.getString(UiCommonR.string.default_error)
        }
    }
}
