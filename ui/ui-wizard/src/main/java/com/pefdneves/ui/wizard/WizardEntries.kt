package com.pefdneves.ui.wizard

import android.content.Context
import android.graphics.drawable.Drawable

data class WizardEntry(val title: String, val description: String, val image: Drawable)

fun getWizardEntries(context: Context) = listOf(
    WizardEntry(
        context.getString(R.string.wizard_entry_test_title),
        context.getString(R.string.wizard_entry_test_description),
        context.resources.getDrawable(androidx.core.R.drawable.notification_bg, null)
    ),
    WizardEntry(
        context.getString(R.string.wizard_entry_test_title),
        context.getString(R.string.wizard_entry_test_description),
        context.resources.getDrawable(androidx.core.R.drawable.notification_bg, null)
    )
)