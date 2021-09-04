package com.example.showmanager.features.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.showmanager.R
import com.example.showmanager.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Splash user interface view. Initial display screen to welcome users.
 *
 * @see Fragment
 */
@AndroidEntryPoint
class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_splash, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startAnimation()
    }

    // =============================================================================================
    // Private helper methods
    // =============================================================================================

    private fun startAnimation() {
        lifecycleScope.launch {
            delay(2000)
            navigateWithTransition()
        }
    }

    private fun navigateWithTransition() {
        val extras = FragmentNavigatorExtras(
            binding.splashLabel to "titleLabelTransition",
            binding.splashLogo to "logoTransition"
        )
        val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
        findNavController().navigate(action, extras)
    }
}
