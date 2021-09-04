package com.example.showmanager.features.showsList.adapter.holders

import android.view.LayoutInflater
import com.example.showmanager.commons.base.BaseViewHolder
import com.example.showmanager.core.network.model.Show
import com.example.showmanager.databinding.ListItemShowBinding

/**
 * Class describes show view and meta data about it's place within the RecyclerView.
 *
 * @see BaseViewHolder
 */
class ShowViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemShowBinding>(
    binding = ListItemShowBinding.inflate(inflater)
) {

    /**
     * Bind view data binding variables
     *
     * @param item Show list item
     */
    fun bind(item: Show) {
        binding.show = item
        binding.executePendingBindings()
    }
}
