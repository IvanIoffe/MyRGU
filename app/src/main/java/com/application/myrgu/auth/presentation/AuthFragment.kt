package com.application.myrgu.auth.presentation

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.application.myrgu.R
import com.application.myrgu.app.App
import com.application.myrgu.auth.domain.model.AuthRequest
import com.application.myrgu.auth.domain.model.AuthResponse
import com.application.myrgu.auth.domain.model.UserAuthData
import com.application.myrgu.core.data.Result
import com.application.myrgu.core.di.AppComponent
import com.application.myrgu.core.utils.Constants
import com.application.myrgu.databinding.FragmentAuthBinding
import kotlinx.coroutines.launch

class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private lateinit var appComponent: AppComponent

    private val authViewModel: AuthViewModel by viewModels {
        appComponent.daggerViewModelFactory
    }
    private val userAuthDataViewModel: UserAuthDataViewModel by activityViewModels {
        appComponent.daggerViewModelFactory
    }

    private val userRole by lazy {
        UserRole.valueOf(arguments?.getString(Constants.USER_ROLE).orEmpty())
    }

    override fun onAttach(context: Context) {
        appComponent = App.appComponent
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (userRole) {
            UserRole.GROUP -> {
                binding.textInputLayoutLogin.apply {
                    hint = getString(R.string.group_number_hint)
                    placeholderText = getString(R.string.group_number_placeholder)
                }
                binding.textInputEditTextLogin.inputType = InputType.TYPE_CLASS_NUMBER
            }

            UserRole.TEACHER -> {
                binding.textInputLayoutLogin.apply {
                    hint = getString(R.string.teacher_login_hint)
                    suffixText = getString(R.string.teacher_login_suffix_text)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                authViewModel.auth.collect {
                    handleAuthState(it)
                }
            }
        }

        binding.buttonAuth.setOnClickListener {
            val login = binding.textInputEditTextLogin.text.toString()
            if (login.isBlank()) {
                binding.textInputLayoutLogin.error = getString(R.string.enter_data)
            } else {
                authViewModel.auth(
                    AuthRequest(
                        login = getFormattedLogin(login),
                        userRole = userRole
                    )
                )
            }
        }
    }

    private fun handleAuthState(state: Result<AuthResponse>) {
        when (state) {
            is Result.Success -> handleAuthSuccessState(state)
            is Result.Error -> handleAuthErrorState(state)
            Result.Loading -> handleAuthLoadingState()
            else -> {}
        }
    }

    private fun handleAuthSuccessState(state: Result.Success<AuthResponse>) {
        val authResponse = state.data
        userAuthDataViewModel.setAuthData(
            UserAuthData(
                id = authResponse.id,
                displayName = authResponse.displayName,
                role = userRole
            )
        )
        findNavController().navigate(R.id.action_authFragment_to_mainFragment)
    }

    private fun handleAuthErrorState(state: Result.Error) {
        binding.authFragmentLoading.root.visibility = View.INVISIBLE
        binding.authFragmentContent.visibility = View.VISIBLE
        binding.textInputLayoutLogin.error = state.message
    }

    private fun handleAuthLoadingState() {
        binding.authFragmentLoading.root.visibility = View.VISIBLE
        binding.authFragmentContent.visibility = View.INVISIBLE
    }

    private fun getFormattedLogin(loginText: String): String {
        return when (userRole) {
            UserRole.TEACHER -> {
                loginText + getString(R.string.teacher_login_suffix_text)
            }

            else -> loginText
        }
    }
}