/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.features.addshow

/**
 * Class that manages view events for [NewShowFragment]
 */
sealed class NewShowViewEvent {

    /**
     * Open date picker event.
     */
    object OpenDatePicker : NewShowViewEvent()
}
