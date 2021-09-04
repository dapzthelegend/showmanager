package com.example.showmanager.features.showsList.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.showmanager.core.network.model.Show
import com.example.showmanager.features.showsList.adapter.holders.LoadingViewHolder
import com.example.showmanager.features.showsList.adapter.holders.ShowViewHolder
import javax.inject.Inject

internal enum class ItemView(val type: Int) {
    SHOW(type = 0),
    LOADING(type = 1);

    companion object {
        fun valueOf(type: Int): ItemView = values().first { it.type == type }
    }
}

/**
 * Class for presenting shows list in a [RecyclerView], including computing
 * diffs on a background thread
 *
 * @see RecyclerView.Adapter
 */
class ShowsListAdapter @Inject constructor() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onEndOfListReached: (() -> Unit)? = {}
    private var shows: MutableList<Show> = mutableListOf()

    /**
     * Called when RecyclerView needs a new [RecyclerView.ViewHolder] of the given type to
     * represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see RecyclerView.Adapter.onCreateViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (ItemView.valueOf(viewType)) {
            ItemView.SHOW -> ShowViewHolder(LayoutInflater.from(parent.context))
            ItemView.LOADING -> LoadingViewHolder(LayoutInflater.from(parent.context))
        }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     * @see RecyclerView.Adapter.onBindViewHolder
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemView(position)) {
            ItemView.SHOW ->
                shows[position].let {
                    if (holder is ShowViewHolder) {
                        holder.bind(it)
                    }
                }
            else -> {
            }
        }
        if (position == shows.size) {
            if (onEndOfListReached != null) {
                onEndOfListReached?.invoke()
            }
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     * @see RecyclerView.Adapter.getItemCount
     */
    override fun getItemCount(): Int =
        if (onEndOfListReached != null) {
            shows.size + 1
        } else {
            shows.size
        }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(shows: List<Show>) {
        this.shows.addAll(shows)
        notifyDataSetChanged()
    }

    /**
     * Return the view type of the item at position for the purposes of view recycling.
     *
     * @param position Position to query.
     * @return Integer value identifying the type of the view needed to represent at position.
     * @see RecyclerView.Adapter.getItemViewType
     */
    override fun getItemViewType(position: Int) = getItemView(position).type

    /**
     * Obtain the type of view by the item position.
     *
     * @param position Current item position.
     * @return ItemView type.
     */
    private fun getItemView(position: Int) =
        if (onEndOfListReached != null && position == itemCount - 1) {
            ItemView.LOADING
        } else {
            ItemView.SHOW
        }
}
