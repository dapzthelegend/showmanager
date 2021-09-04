/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.features.addshow

import android.text.TextUtils
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.crop2cash.core.network.utils.Failure
import com.dapzthelegend.spacexodyssey.core.flow.SingleFlow
import com.example.showmanager.R
import com.example.showmanager.commons.utils.Constants.TOOLBAR_NAVIGATION_ICON
import com.example.showmanager.commons.utils.formatDate
import com.example.showmanager.core.network.model.Show
import com.example.showmanager.features.addshow.domain.usecase.AddShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Class that prepares data, manages events and state for [NewShowFragment]
 *
 * @see ViewModel
 */
@HiltViewModel
class NewShowViewModel @Inject constructor(
    private val addShow: AddShow
) : ViewModel() {

    val event = SingleFlow<NewShowViewEvent>()
    val releaseDate = MutableStateFlow("")
    val title = MutableStateFlow("")
    val seasons = MutableStateFlow("")
    val state = MutableStateFlow<NewShowViewState>(
        NewShowViewState.IncompleteFields
    )
    private val job = Job()

    init {
        combine(title, seasons, releaseDate) { title, seasons, releaseDate ->
            fun fieldsCompleted() = !TextUtils.isEmpty(title) &&
                !TextUtils.isEmpty(seasons) &&
                !TextUtils.isEmpty(releaseDate)

            if (fieldsCompleted()) {
                state.emit(NewShowViewState.FieldsCompleted)
            } else {
                state.emit(NewShowViewState.IncompleteFields)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = NewShowViewState.IncompleteFields
        )
    }

    // =============================================================================================
    // Private handlers.
    // =============================================================================================

    /**
     * Handler on success for [AddShow]
     */
    private fun onSuccess(completed: Boolean) {
        viewModelScope.launch {
            if (completed) {
                state.emit(NewShowViewState.Dismiss)
            } else {
                state.emit(NewShowViewState.Error())
            }
        }
    }

    /**
     * Handler on failure for [AddShow]
     */
    private fun onFailure(failure: Failure) {
        viewModelScope.launch {
            state.emit(NewShowViewState.Error(failure.toString()))
        }
    }

    // =============================================================================================
    // Public methods
    // =============================================================================================

    /**
     * Called when a view is clicked.
     */
    fun onViewClicked(v: View) {
        when (v.id) {
            TOOLBAR_NAVIGATION_ICON -> viewModelScope.launch {
                state.emit(NewShowViewState.Dismiss)
            }
            R.id.release_date -> viewModelScope.launch {

                event.emit(NewShowViewEvent.OpenDatePicker)
            }
            R.id.add_show -> viewModelScope.launch {
                state.emit(NewShowViewState.Loading)
                val show = Show(
                    id = null,
                    title = title.value,
                    seasons = seasons.value.toInt(),
                    releaseDate = formatDate(releaseDate.value)
                )
                addShow(job, show) {
                    it.fold(
                        ::onFailure,
                        ::onSuccess
                    )
                }
            }
        }
    }
}
