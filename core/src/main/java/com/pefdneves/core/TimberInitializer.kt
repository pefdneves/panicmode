package com.pefdneves.core

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber
import timber.log.Timber.Forest.plant

class TimberInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        } else {
            plant(ReleaseTree())
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf()
    }

}