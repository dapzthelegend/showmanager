package com.example.showmanager.features.showsList

/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

sealed class ShowsListViewState {

    /**
     * Loaded shows list.
     */
    object Loaded : ShowsListViewState()

    /**
     * Loading shows list.
     */
    object Loading : ShowsListViewState()

    /**
     * Empty shows list.
     */
    object Empty : ShowsListViewState()

    /**
     * Error on loading shows list.
     */
    object Error : ShowsListViewState()

    /**
     * Error on add more elements into shows list.
     */
    object AddError : ShowsListViewState()

    /**
     * No more elements for adding into shows list.
     */
    object NoMoreElements : ShowsListViewState()

    object Dismiss : ShowsListViewState()

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [Loaded].
     *
     * @return True if is loaded state, otherwise false.
     */
    fun isLoaded() = this is Loaded

    /**
     * Check if current view state is [Loading].
     *
     * @return True if is loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    /**
     * Check if current view state is [Empty].
     *
     * @return True if is empty state, otherwise false.
     */
    fun isEmpty() = this is Empty

    /**
     * Check if current view state is [Error].
     *
     * @return True if is error state, otherwise false.
     */
    fun isError() = this is Error

    /**
     * Check if current view state is [NoMoreElements].
     *
     * @return True if is no more elements state, otherwise false.
     */
    fun isNoMoreElements() = this is NoMoreElements
}
