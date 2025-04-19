package com.application.myrgu.about_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.myrgu.R
import com.application.myrgu.databinding.FragmentAboutAppBinding

class AboutAppFragment : Fragment() {
    private lateinit var binding: FragmentAboutAppBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarAboutAppFragment.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.textViewDevelopers.setOnClickListener {
            findNavController().navigate(R.id.action_aboutAppFragment_to_developersFragment)
        }
    }
}