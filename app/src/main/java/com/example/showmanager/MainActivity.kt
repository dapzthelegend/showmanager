/**
 * Created by Sola-Aremu Oluwadara on 02/09/2021.
 */

package com.example.showmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Called when the application is started i.e Launch Activity. Class for single activity design
 * system. Activity for the nav host fragment.
 *
 * @see AppCompatActivity
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /**
     * Called when application is started.
     *
     * @param savedInstanceState If non-null, application is resuming from a previous state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
