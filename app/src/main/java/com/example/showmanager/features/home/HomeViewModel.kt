/**
 * Created by Sola-Aremu Oluwadara on 02/09/2021.
 */

package com.example.showmanager.features.home

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dapzthelegend.spacexodyssey.core.flow.SingleFlow
import com.example.showmanager.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Class for managing events for [HomeFragment]
 *
 * @see ViewModel
 */
@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    val event = SingleFlow<HomeViewEvent>()

    // =============================================================================================
    // Public helper methods
    // =============================================================================================

    /**
     * Called when a view is clicked.
     */
    fun onViewClicked(v: View) {
        when (v.id) {
            R.id.add_new_shows -> viewModelScope.launch {
                event.emit(HomeViewEvent.NavigateToNewShow)
            }
            R.id.show_added_shows -> viewModelScope.launch {
                event.emit(HomeViewEvent.NavigateToAddedShows)
            }
        }
    }
}
