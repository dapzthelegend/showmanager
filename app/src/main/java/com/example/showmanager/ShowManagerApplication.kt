/**
 * Created by Sola-Aremu Oluwadara on 02/09/2021.
 */

package com.example.showmanager

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Base class for maintaining global application state.
 *
 * @see Application
 */
@HiltAndroidApp
class ShowManagerApplication : Application() {

    /**
     * Called when application is started.
     *
     * @see Application.onCreate
     */
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
