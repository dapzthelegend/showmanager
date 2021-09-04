/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.commons.recyclerview

import android.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

private val ATTRS = intArrayOf(
    R.attr.listDivider
)

/**
 * Simple item decoration allows the application to add a special drawing and layout offset
 * to specific item views from the adapter's data set. Support the grid and linear layout.
 *
 * @see RecyclerView.ItemDecoration
 */
class RecyclerViewItemDivider(
    @VisibleForTesting(otherwise = PRIVATE)
    private val context: Context,
    private val orientation: Int,
    private val margin: Int
) : RecyclerView.ItemDecoration() {

    private var mDivider: Drawable

    init {
        val a =
            context.obtainStyledAttributes(ATTRS)
        mDivider = a.getDrawable(0)!!
        a.recycle()
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (orientation == DividerItemDecoration.VERTICAL) {
            drawVertical(c, parent)
        }
    }

    // ============================================================================================
    //  Private configs methods
    // ============================================================================================

    private fun drawVertical(c: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child
                .layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom: Int = top + mDivider.intrinsicHeight
            mDivider.setBounds(left + margin, top, right - margin, bottom)
            mDivider.draw(c)
        }
    }
}
