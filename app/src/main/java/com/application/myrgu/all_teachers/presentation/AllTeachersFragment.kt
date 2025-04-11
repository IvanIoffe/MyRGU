package com.application.myrgu.all_teachers.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.application.myrgu.R
import com.application.myrgu.all_teachers.domain.model.AllTeachers
import com.application.myrgu.all_teachers.domain.model.Teacher
import com.application.myrgu.app.App
import com.application.myrgu.core.data.Result
import com.application.myrgu.core.di.AppComponent
import com.application.myrgu.core.utils.Constants
import com.application.myrgu.core.utils.ShimmerManager
import com.application.myrgu.databinding.FragmentAllTeachersBinding
import com.application.myrgu.schedule.domain.model.ScheduleType
import kotlinx.coroutines.launch

class AllTeachersFragment : Fragment(), TeacherAdapter.Listener {
    private lateinit var binding: FragmentAllTeachersBinding
    private lateinit var appComponent: AppComponent

    private val allTeachersViewModel: AllTeachersViewModel by viewModels {
        appComponent.daggerViewModelFactory
    }

    private var teacherAdapter: TeacherAdapter? = null

    private var shimmerManager: ShimmerManager? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent = App.appComponent
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllTeachersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViewAllTeachersShimmer()
        setupRecyclerViewAllTeachers()

        initShimmerManager()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                allTeachersViewModel.state.collect {
                    handleAllTeachersState(it)
                }
            }
        }

        binding.toolbarAllTeachersFragment.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.searchViewAllTeachersFragment.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                allTeachersViewModel.filterList(newText)
                return true
            }
        })

        binding.allTeachersFragmentError.buttonRepeatLoading.setOnClickListener {
            getAllTeachers()
        }
    }

    override fun onClick(teacher: Teacher) {
        val bundle = bundleOf(
            Constants.USER_ID to teacher.id,
            Constants.SCHEDULE_TYPE to ScheduleType.TEACHER.name,
            Constants.DISPLAY_NAME to teacher.shortName
        )
        findNavController().navigate(
            R.id.action_allTeachersFragment_to_scheduleFragment,
            bundle
        )
    }

    private fun setupRecyclerViewAllTeachersShimmer() {
        val shimmerAdapter = TeacherShimmerAdapter()
        binding.recyclerViewAllTeachersShimmer.adapter = shimmerAdapter
    }

    private fun setupRecyclerViewAllTeachers() {
        teacherAdapter = TeacherAdapter(this)
        binding.recyclerViewAllTeachers.apply {
            adapter = teacherAdapter
        }
    }

    private fun initShimmerManager() {
        shimmerManager =
            ShimmerManager(
                shimmers = listOf(
                    binding.shimmerFrameLayoutRecyclerViewAllTeachers
                )
            )
    }

    private fun handleAllTeachersState(state: Result<AllTeachers>) {
        when (state) {
            is Result.Success -> handleAllTeachersSuccessState(state)
            is Result.Error -> handleAllTeachersErrorState()
            Result.Loading -> handleAllTeachersLoadingState()
            Result.Initial -> handleAllTeachersInitialState()
        }
    }

    private fun handleAllTeachersSuccessState(state: Result.Success<AllTeachers>) {
        updateRecyclerViewAllTeachers(state.data.items)

        shimmerManager?.stopShimmers()
        binding.allTeachersFragmentLoading.visibility = View.INVISIBLE
        binding.searchViewAllTeachersFragment.visibility = View.VISIBLE
        binding.allTeachersFragmentContent.visibility = View.VISIBLE
    }

    private fun handleAllTeachersErrorState() {
        shimmerManager?.stopShimmers()
        binding.allTeachersFragmentLoading.visibility = View.INVISIBLE
        binding.allTeachersFragmentError.root.visibility = View.VISIBLE
    }

    private fun handleAllTeachersLoadingState() {
        shimmerManager?.startShimmers()
        binding.allTeachersFragmentLoading.visibility = View.VISIBLE
        binding.allTeachersFragmentError.root.visibility = View.INVISIBLE
    }

    private fun handleAllTeachersInitialState() {
        getAllTeachers()
    }

    private fun updateRecyclerViewAllTeachers(list: List<Teacher>) {
        binding.recyclerViewAllTeachers.visibility = View.VISIBLE
        binding.textViewNoTeachers.visibility = View.INVISIBLE

        if (list.isEmpty()) {
            binding.recyclerViewAllTeachers.visibility = View.INVISIBLE
            binding.textViewNoTeachers.visibility = View.VISIBLE
        }
        teacherAdapter?.submitList(list)
    }

    private fun getAllTeachers() {
        allTeachersViewModel.getAllTeachers()
    }
}