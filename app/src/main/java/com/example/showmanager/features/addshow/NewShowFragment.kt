package com.example.showmanager.features.addshow

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.showmanager.R
import com.example.showmanager.commons.extensions.collect
import com.example.showmanager.commons.extensions.showError
import com.example.showmanager.databinding.FragmentNewShowBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar

/**
 * User interface view to add new tv show.
 *
 * @see Fragment
 */
@AndroidEntryPoint
class NewShowFragment : Fragment() {

    private lateinit var binding: FragmentNewShowBinding
    private val viewModel: NewShowViewModel by viewModels()

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @see Fragment.onCreateView
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_new_show, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @see Fragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitDataBinding()
        collect(viewModel.event, lifecycle, ::onViewEventChanged)
        collect(viewModel.state, ::onViewStateChanged)
    }

    /**
     * Called to Initialize view data binding variables when fragment view is created.
     */
    private fun onInitDataBinding() {
        binding.viewModel = viewModel
    }

    // =============================================================================================
    // Private collectors
    // =============================================================================================

    /**
     * Collector on view event changed.
     */
    private fun onViewEventChanged(event: NewShowViewEvent) {
        when (event) {
            is NewShowViewEvent.OpenDatePicker -> {
                val cldr: Calendar = Calendar.getInstance()
                val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
                val month: Int = cldr.get(Calendar.MONTH)
                val year: Int = cldr.get(Calendar.YEAR)

                val picker = DatePickerDialog(
                    requireContext(),
                    { _, selectedYear, monthOfYear, dayOfMonth ->
                        lifecycleScope.launch {
                            viewModel.releaseDate.emit(
                                "$dayOfMonth-${monthOfYear + 1}-$selectedYear"
                            )
                        }
                    },
                    year,
                    month,
                    day
                )
                picker.show()
            }
        }
    }

    /**
     * Collector view state change on [NewShowViewState].
     *
     * @param state State of new show.
     */
    private fun onViewStateChanged(state: NewShowViewState) {
        when (state) {
            is NewShowViewState.Error -> {
                showError(state.message)
            }
            is NewShowViewState.Dismiss -> findNavController().popBackStack()
            else -> {
            }
        }
    }
}
