package com.example.showmanager.features.showsList.adapter

/**
 * Different states for [ShowsListAdapter]
 *
 */
sealed class ShowsListAdapterState(
    val hasExtraRow: Boolean
) {

    /**
     * Added new shows into the show list
     */
    object Added : ShowsListAdapterState(hasExtraRow = true)

    /**
     * Loading for new shows to add to list
     */
    object AddLoading : ShowsListAdapterState(hasExtraRow = true)

    /**
     * Error on adding new shows to list
     */
    object AddError : ShowsListAdapterState(hasExtraRow = true)

    /**
     * No more shows to add to list
     */
    object NoMore : ShowsListAdapterState(hasExtraRow = false)

    // =============================================================================================
    // Public helper methods
    // =============================================================================================

    /**
     * Check if current view state is [Added]
     *
     * @return True if view state is [Added], else false
     */
    fun isAdded() = this is Added

    /**
     * Check if current view state is [AddLoading]
     *
     * @return True if view state is [AddLoading], else false
     */
    fun isAddLoading() = this is AddLoading

    /**
     * Check if current view state is [AddError]
     *
     * @return True if view state is [AddError], else false
     */
    fun isAddError() = this is AddError

    /**
     * Check if current view state is [NoMore]
     *
     * @return True if view state is [NoMore], else false
     */
    fun isNoMore() = this is NoMore
}
