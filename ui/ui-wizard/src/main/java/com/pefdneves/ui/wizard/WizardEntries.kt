package com.pefdneves.ui.wizard

import android.content.Context

data class WizardEntry(val title: String, val description: String, val image: Int)

fun getWizardEntries(context: Context) = listOf(
    WizardEntry(
        context.getString(R.string.wizard_entry_test_title),
        context.getString(R.string.wizard_entry_test_description),
        R.drawable.testimage
    ),
    WizardEntry(
        context.getString(R.string.wizard_entry_test_title),
        context.getString(R.string.wizard_entry_test_description),
        R.drawable.testimage
    )
)