package com.application.myrgu.core.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.application.myrgu.R
import com.application.myrgu.app.App
import com.application.myrgu.core.di.AppComponent
import com.application.myrgu.core.utils.network.NetworkState
import com.application.myrgu.databinding.FragmentMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var appComponent: AppComponent

    private val mainFragmentViewModel: MainFragmentViewModel by activityViewModels {
        appComponent.daggerViewModelFactory
    }

    private var snackbar: Snackbar? = null

    override fun onAttach(context: Context) {
        appComponent = App.appComponent
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snackbar = Snackbar.make(
            view,
            getString(R.string.no_internet_connection_message),
            Snackbar.LENGTH_INDEFINITE
        ).apply {
            setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
            setAction(getString(R.string.clearly)) {
                dismiss()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainFragmentViewModel.networkState.collect {
                    when (it) {
                        NetworkState.AVAILABLE -> snackbar?.dismiss()

                        NetworkState.LOST, NetworkState.UNAVAILABLE -> snackbar?.show()

                        else -> {}
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        snackbar?.dismiss()
    }
}