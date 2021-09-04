package com.example.showmanager.features.showsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.showmanager.R
import com.example.showmanager.commons.extensions.collect
import com.example.showmanager.core.network.model.Show
import com.example.showmanager.databinding.FragmentShowsListBinding
import com.example.showmanager.features.showsList.adapter.ShowsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShowsListFragment : Fragment() {
    private lateinit var binding: FragmentShowsListBinding
    private val viewModel: ShowsListViewModel by viewModels()

    @Inject
    lateinit var viewAdapter: ShowsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shows_list, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitDataBinding()
        collect(viewModel.state, ::onViewStateChanged)
        collect(viewModel.data, ::onViewDataChanged)
    }

    private fun onInitDataBinding() {
        binding.viewModel = viewModel
        binding.includeList.showsList.apply {
            viewAdapter.onEndOfListReached = {
                viewModel.getShows()
            }
            adapter = viewAdapter
        }
    }

    // =============================================================================================
    // Private collectors
    // =============================================================================================

    private fun onViewStateChanged(state: ShowsListViewState) {
        when (state) {
            is ShowsListViewState.NoMoreElements -> {
                viewAdapter.onEndOfListReached = null
            }

            is ShowsListViewState.Dismiss -> findNavController().popBackStack()
        }
    }

    private fun onViewDataChanged(data: List<Show>) {
        viewAdapter.setList(data)
    }
}
