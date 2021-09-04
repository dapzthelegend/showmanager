/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.commons.bindings

import android.view.View
import androidx.databinding.BindingAdapter
import com.kusu.loadingbutton.LoadingButton

/**
 * Simplification to check and setup view as visible.
 */
@set:BindingAdapter("visible")
var View.visible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

/**
 * Simplification to check and setup view as gone.
 */
@set:BindingAdapter("gone")
var View.gone
    get() = visibility == View.GONE
    set(value) {
        visibility = if (value) View.GONE else View.VISIBLE
    }

/**
 * Simplification to check and setup view as invisible.
 */
@set:BindingAdapter("invisible")
var View.invisible
    get() = visibility == View.INVISIBLE
    set(value) {
        visibility = if (value) View.INVISIBLE else View.VISIBLE
    }

/**
 * View binding loading of circular progress button.
 *
 * @param state The current loading state of the button.
 */
@BindingAdapter("loading")
fun LoadingButton.loading(state: Boolean) = if (state) {
    this.showLoading()
} else {
    this.hideLoading()
}
