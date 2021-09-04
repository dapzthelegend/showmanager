/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.commons.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.showmanager.commons.recyclerview.RecyclerViewItemDecoration
import com.example.showmanager.commons.recyclerview.RecyclerViewItemDivider

/**
 * Add an [RecyclerViewItemDecoration] to this RecyclerView. Item decorations can
 * affect both measurement and drawing of individual item views.
 *
 * @param spacingPx Spacing in pixels to set as a item margin.
 * @see RecyclerView.addItemDecoration
 */
@BindingAdapter("itemDecorationSpacing", "itemMargin")
fun RecyclerView.setItemDecoration(spacingPx: Float, margin: Float) {
    addItemDecoration(
        RecyclerViewItemDivider(this.context, DividerItemDecoration.VERTICAL, margin.toInt())
    )
    addItemDecoration(
        RecyclerViewItemDecoration(spacingPx.toInt())
    )
}
