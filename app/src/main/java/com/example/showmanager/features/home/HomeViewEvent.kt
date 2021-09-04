/**
 * Created by Sola-Aremu Oluwadara on 02/09/2021.
 */

package com.example.showmanager.features.home

/**
 * Class for managing view events for [HomeFragment]
 */
sealed class HomeViewEvent {

    /**
     * Navigate current view to add new show view.
     */
    object NavigateToNewShow : HomeViewEvent()

    /**
     * Navigate current view to added shows view.
     */
    object NavigateToAddedShows : HomeViewEvent()
}
