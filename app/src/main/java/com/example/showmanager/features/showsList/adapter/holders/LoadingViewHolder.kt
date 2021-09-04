package com.example.showmanager.features.showsList.adapter.holders

import android.view.LayoutInflater
import com.example.showmanager.commons.base.BaseViewHolder
import com.example.showmanager.databinding.ListItemLoadingBinding

/**
 * Class describes show loading view and meta data about it's place within the RecyclerView.
 *
 * @see BaseViewHolder
 */
class LoadingViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemLoadingBinding>(
    binding = ListItemLoadingBinding.inflate(inflater)
)
