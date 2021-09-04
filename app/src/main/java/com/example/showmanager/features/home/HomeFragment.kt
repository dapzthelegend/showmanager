package com.example.showmanager.features.home

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.showmanager.R
import com.example.showmanager.commons.extensions.collect
import com.example.showmanager.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * User interface for home view. Has options to create and view added tv shows.
 *
 * @see Fragment
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

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
        sharedElementEnterTransition = TransitionInflater
            .from(this.context).inflateTransition(R.transition.change_bounds)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
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
    }

    // =============================================================================================
    // Private helper methods
    // =============================================================================================

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
    private fun onViewEventChanged(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.NavigateToAddedShows -> findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToAddedShowsFragment())
            is HomeViewEvent.NavigateToNewShow -> findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToNewShowFragment())
        }
    }
}
