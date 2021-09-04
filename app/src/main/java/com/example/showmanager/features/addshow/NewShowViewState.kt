/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.features.addshow

/**
 * Class that managers view state for [NewShowFragment]
 */
sealed class NewShowViewState {

    /**
     * View state for incomplete fields.
     */
    object IncompleteFields : NewShowViewState()

    /**
     * Loading view state
     */
    object Loading : NewShowViewState()

    /**
     * An error has occurred.
     */
    class Error(val message: String = "An unknown error has occurred!") :
        NewShowViewState()

    /**
     * Completed fields view state.
     */
    object FieldsCompleted : NewShowViewState()

    /**
     * View state to dismiss view.
     */
    object Dismiss : NewShowViewState()

    // =============================================================================================
    // Public methods
    // =============================================================================================

    /**
     * Check if current view state is [FieldsCompleted].
     *
     * @return True if fields completed state, otherwise false.
     */
    fun isFieldsCompleted() = this is FieldsCompleted

    /**
     * Check if current view state is [Loading].
     *
     * @return True if loading state, otherwise false.
     */
    fun isLoading() = this is Loading
}
