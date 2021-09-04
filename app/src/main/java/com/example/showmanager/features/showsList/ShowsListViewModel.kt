/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.features.showsList

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.crop2cash.core.network.utils.Failure
import com.example.showmanager.core.network.model.Show
import com.example.showmanager.core.network.utils.BaseUseCase
import com.example.showmanager.features.addshow.data.ShowsError
import com.example.showmanager.features.showsList.domain.usecases.GetShows
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsListViewModel @Inject constructor(
    private val getShows: GetShows
) : ViewModel() {

    val state: MutableStateFlow<ShowsListViewState> = MutableStateFlow(ShowsListViewState.Loading)
    private val job = Job()
    val data: MutableStateFlow<List<Show>> = MutableStateFlow(emptyList())

    init {
        getShows()
    }

    // =============================================================================================
    // Private handlers.
    // =============================================================================================

    /**
     * Handler on success for [GetShows]
     */
    private fun onSuccess(shows: List<Show>) {
        viewModelScope.launch {
            state.emit(ShowsListViewState.Loaded)
            data.emit(shows)
        }
    }

    /**
     * Handler on failure for [GetShows]
     */
    private fun onFailure(failure: Failure) {
        when (failure) {
            is ShowsError.NoMoreShows ->
                viewModelScope.launch {
                    state.emit(ShowsListViewState.NoMoreElements)
                }
            else -> {
                if (state.value is ShowsListViewState.Loading) {
                    viewModelScope.launch {
                        state.emit(ShowsListViewState.Error)
                    }
                }
            }
        }
    }

    fun getShows() = getShows(job, BaseUseCase.None) {
        it.fold(
            ::onFailure,
            ::onSuccess
        )
    }
    // =============================================================================================
    // Private helper methods
    // =============================================================================================

    fun onViewClicked(v: View) {
        when (v.id) {
            -1 -> viewModelScope.launch {
                state.emit(ShowsListViewState.Dismiss)
            }
        }
    }
}
