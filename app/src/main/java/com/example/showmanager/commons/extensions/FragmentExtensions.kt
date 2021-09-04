/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.commons.extensions

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

/**
 * Called to show error.
 *
 * @param error The error that occurred.
 */
fun Fragment.showError(error: String) {
    Snackbar.make(requireView(), error, BaseTransientBottomBar.LENGTH_LONG)
        .show()
}
