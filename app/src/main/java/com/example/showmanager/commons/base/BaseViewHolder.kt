/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.commons.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Base view holder to standardize and simplify initialization for this component.
 *
 * @param binding View data binding generated class instance.
 * @see RecyclerView.ViewHolder
 */
abstract class BaseViewHolder<T : ViewDataBinding>(
    val binding: T
) : RecyclerView.ViewHolder(binding.root)
