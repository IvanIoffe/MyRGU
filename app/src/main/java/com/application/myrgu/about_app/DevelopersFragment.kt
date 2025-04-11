package com.application.myrgu.about_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.myrgu.databinding.FragmentDevelopersBinding

class DevelopersFragment : Fragment() {
    private lateinit var binding: FragmentDevelopersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDevelopersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarFragmentDevelopers.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}