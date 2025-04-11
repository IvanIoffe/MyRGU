package com.application.myrgu.all_groups.presentation

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
import com.application.myrgu.all_groups.domain.model.AllGroups
import com.application.myrgu.all_groups.domain.model.Group
import com.application.myrgu.app.App
import com.application.myrgu.core.data.Result
import com.application.myrgu.core.di.AppComponent
import com.application.myrgu.core.utils.Constants
import com.application.myrgu.databinding.FragmentAllGroupsBinding
import com.application.myrgu.schedule.domain.model.ScheduleType
import kotlinx.coroutines.launch

class AllGroupsFragment : Fragment(), GroupAdapter.Listener {
    private lateinit var binding: FragmentAllGroupsBinding
    private lateinit var appComponent: AppComponent

    private val allGroupsViewModel: AllGroupsViewModel by viewModels {
        appComponent.daggerViewModelFactory
    }

    private var groupAdapter: GroupAdapter? = null

    override fun onAttach(context: Context) {
        appComponent = App.appComponent
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllGroupsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViewAllGroupsShimmer()
        setupRecyclerViewAllGroups()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                allGroupsViewModel.state.collect {
                    handleAllGroupsState(it)
                }
            }
        }

        binding.toolbarAllGroupsFragment.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.searchViewAllGroupsFragment.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                allGroupsViewModel.filterList(newText)
                return true
            }
        })

        binding.allGroupsFragmentError.buttonRepeatLoading.setOnClickListener {
            getAllGroups()
        }
    }

    override fun onClick(group: Group) {
        val bundle = bundleOf(
            Constants.USER_ID to group.id,
            Constants.SCHEDULE_TYPE to ScheduleType.GROUP.name,
            Constants.DISPLAY_NAME to "${getString(R.string.group_prefix)} ${group.number}"
        )
        findNavController().navigate(
            R.id.action_allGroupsFragment_to_scheduleFragment,
            bundle
        )
    }

    private fun setupRecyclerViewAllGroupsShimmer() {
        val shimmerAdapter = GroupShimmerAdapter()
        binding.recyclerViewAllGroupsShimmer.adapter = shimmerAdapter
    }

    private fun setupRecyclerViewAllGroups() {
        groupAdapter = GroupAdapter(this)
        binding.recyclerViewAllGroups.adapter = groupAdapter
    }

    private fun handleAllGroupsState(state: Result<AllGroups>) {
        when (state) {
            is Result.Success -> handleAllGroupsSuccessState(state)
            is Result.Error -> handleAllGroupsErrorState()
            Result.Loading -> handleAllGroupsLoadingState()
            Result.Initial -> handleAllGroupsInitialState()
        }
    }

    private fun handleAllGroupsSuccessState(state: Result.Success<AllGroups>) {
        updateRecyclerViewAllGroups(state.data.items)

        binding.searchViewAllGroupsFragment.visibility = View.VISIBLE
        binding.allGroupsFragmentContent.visibility = View.VISIBLE
        binding.allGroupsFragmentLoading.visibility = View.INVISIBLE
    }

    private fun handleAllGroupsErrorState() {
        binding.allGroupsFragmentLoading.visibility = View.INVISIBLE
        binding.allGroupsFragmentError.root.visibility = View.VISIBLE
    }

    private fun handleAllGroupsLoadingState() {
        binding.allGroupsFragmentLoading.visibility = View.VISIBLE
        binding.allGroupsFragmentError.root.visibility = View.INVISIBLE
    }

    private fun handleAllGroupsInitialState() {
        getAllGroups()
    }

    private fun updateRecyclerViewAllGroups(list: List<Group>) {
        binding.recyclerViewAllGroups.visibility = View.VISIBLE
        binding.textViewNoGroups.visibility = View.INVISIBLE

        if (list.isEmpty()) {
            binding.recyclerViewAllGroups.visibility = View.INVISIBLE
            binding.textViewNoGroups.visibility = View.VISIBLE
        }
        groupAdapter?.submitList(list)
    }

    private fun getAllGroups() {
        allGroupsViewModel.getAllGroups()
    }
}