package com.application.myrgu.core.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.application.myrgu.R
import com.application.myrgu.app.App
import com.application.myrgu.auth.presentation.UserAuthDataViewModel
import com.application.myrgu.core.di.AppComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var appComponent: AppComponent

    private val userAuthDataViewModel: UserAuthDataViewModel by viewModels {
        appComponent.daggerViewModelFactory
    }

    private val findNavController by lazy {
        Navigation.findNavController(this, R.id.navHostFragmentMainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = App.appComponent
        installSplashScreen()
            .setKeepOnScreenCondition { !userAuthDataViewModel.isUserAuthDataLoaded }
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userAuthDataViewModel.userAuthData.first()?.let {
                    navigateToMainFragment()
                    delay(1000)
                }
                userAuthDataViewModel.isUserAuthDataLoaded = true
            }
        }
    }

    private fun navigateToMainFragment() {
        val currentDestinationId = findNavController.currentDestination?.id
        if (currentDestinationId == R.id.userRoleSelectionFragment) {
            findNavController.navigate(R.id.action_userRoleSelectionFragment_to_mainFragment)
        }
    }
}