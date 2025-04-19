package com.application.myrgu.auth.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.myrgu.R
import com.application.myrgu.core.utils.Constants
import com.application.myrgu.databinding.FragmentUserRoleSelectionBinding

class UserRoleSelectionFragment : Fragment() {
    private lateinit var binding: FragmentUserRoleSelectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserRoleSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAuthForGroups.setOnClickListener {
            navigateToAuthFragment(UserRole.GROUP)
        }

        binding.buttonAuthForTeachers.setOnClickListener {
            navigateToAuthFragment(UserRole.TEACHER)
        }
    }

    private fun navigateToAuthFragment(userRole: UserRole) {
        val bundle = bundleOf(Constants.USER_ROLE to userRole.name)
        findNavController().navigate(
            R.id.action_userRoleSelectionFragment_to_authFragment,
            bundle
        )
    }
}