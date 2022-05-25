package com.pefdneves.ui.actions

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pefdneves.data.entity.Action

@BindingAdapter("app:actions")
fun setActions(listView: RecyclerView, items: List<Action>?) {
    items?.let {
        (listView.adapter as ActionsAdapter).submitList(items)
    }
}