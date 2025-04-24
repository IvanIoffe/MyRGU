package com.application.myrgu.schedule.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.application.myrgu.R
import com.application.myrgu.app.App
import com.application.myrgu.auth.domain.model.UserAuthData
import com.application.myrgu.auth.presentation.UserAuthDataViewModel
import com.application.myrgu.core.data.Result
import com.application.myrgu.core.di.AppComponent
import com.application.myrgu.core.utils.Constants
import com.application.myrgu.core.utils.ShimmerManager
import com.application.myrgu.core.utils.changeVisibility
import com.application.myrgu.databinding.FragmentScheduleBinding
import com.application.myrgu.schedule.domain.model.Schedule
import com.application.myrgu.schedule.domain.model.ScheduleRequest
import com.application.myrgu.schedule.presentation.weekCalendar.ChangeDateListener
import com.application.myrgu.schedule.presentation.weekCalendar.DayOfWeekViewModel
import com.application.myrgu.schedule.presentation.weekCalendar.WeekCalendarAdapter
import com.application.myrgu.schedule.presentation.weekCalendarShimmer.WeekCalendarShimmerAdapter
import com.application.myrgu.schedule.utils.createScheduleRequest
import com.application.myrgu.schedule.utils.dateOfMondayFirst
import com.application.myrgu.schedule.utils.dateOfMondaySecond
import com.application.myrgu.schedule.utils.displayName
import com.application.myrgu.schedule.utils.getReasonEmptySchedule
import com.application.myrgu.schedule.utils.selectedDate
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Locale

class ScheduleFragment : Fragment(), ChangeDateListener {
    private lateinit var binding: FragmentScheduleBinding
    private lateinit var appComponent: AppComponent

    private val userAuthDataViewModel: UserAuthDataViewModel by activityViewModels {
        appComponent.daggerViewModelFactory
    }
    private val scheduleViewModel: ScheduleViewModel by viewModels {
        appComponent.daggerViewModelFactory
    }
    private val dayOfWeekViewModel: DayOfWeekViewModel by viewModels {
        appComponent.daggerViewModelFactory
    }

    private var lessonAdapter: LessonAdapter? = null
    private var weekCalendarAdapter: WeekCalendarAdapter? = null

    private var scheduleRequest: ScheduleRequest? = null

    private var shimmerManager: ShimmerManager? = null

    override fun onAttach(context: Context) {
        appComponent = App.appComponent
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScheduleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMenu()

        setupWeekCalendarViewShimmer()
        setupRecyclerViewScheduleShimmer()

        initShimmerManager()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    userAuthDataViewModel.userAuthData
                        .distinctUntilChanged()
                        .collect { userAuthData ->
                            handleUserAuthData(userAuthData)
                        }
                }

                launch {
                    scheduleViewModel.state.collect {
                        handleScheduleState(it)
                    }
                }
            }
        }

        scheduleViewModel.schedule.observe(viewLifecycleOwner) {
            scheduleViewModel.setScheduleForWeek(
                dateOfMonday = selectedDate.with(DayOfWeek.MONDAY)
            )
            dayOfWeekViewModel.updateDayOfWeek(selectedDate.dayOfWeek)
        }

        scheduleViewModel.scheduleForWeek.observe(viewLifecycleOwner) { scheduleForWeek ->
            binding.textViewTypeOfWeek.text = scheduleForWeek.typeOfWeek
        }

        dayOfWeekViewModel.dayOfWeek.observe(viewLifecycleOwner) { dayOfWeek ->
            scheduleViewModel.setScheduleForDay(
                dayOfWeek = dayOfWeek.displayName
            )
        }

        scheduleViewModel.scheduleForDay.observe(viewLifecycleOwner) { scheduleForDay ->
            binding.apply {
                recyclerViewSchedule.changeVisibility(scheduleForDay.isNotEmpty())
                textViewReasonEmptySchedule.changeVisibility(scheduleForDay.isEmpty())
                if (textViewReasonEmptySchedule.isVisible) {
                    textViewReasonEmptySchedule.text = getReasonEmptySchedule(requireContext())
                }
            }
            lessonAdapter?.submitList(scheduleForDay)
        }

        binding.weekCalendarView.weekScrollListener = { week ->
            val dateOfMonday = week.days.first().date
            scheduleViewModel.setScheduleForWeek(dateOfMonday)
        }

        binding.scheduleFragmentError.buttonRepeatLoading.setOnClickListener {
            scheduleViewModel.getSchedule(scheduleRequest)
        }
    }

    override fun onChanged(newDate: LocalDate, oldDate: LocalDate) {
        notifyWeekCalendarDateChange(newDate, oldDate)
        dayOfWeekViewModel.updateDayOfWeek(newDate.dayOfWeek)
    }

    private fun setupMenu() {
        binding.toolbarScheduleFragment.inflateMenu(R.menu.menu_schedule_fragment_toolbar)
        binding.toolbarScheduleFragment.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.allGroupsFragment -> {
                    findNavController().navigate(R.id.action_scheduleFragment_to_allGroupsFragment)
                    true
                }

                R.id.allTeachersFragment -> {
                    findNavController().navigate(R.id.action_scheduleFragment_to_allTeachersFragment)
                    true
                }

                R.id.aboutAppFragment -> {
                    findNavController().navigate(R.id.action_scheduleFragment_to_aboutAppFragment)
                    true
                }

                R.id.logout -> {
                    userAuthDataViewModel.deleteAuthData()
                    scheduleViewModel.deleteAllSchedule()
                    true
                }

                else -> false
            }
        }
    }

    private fun setupWeekCalendarViewShimmer() {
        val weekCalendarShimmerAdapter = WeekCalendarShimmerAdapter()
        binding.weekCalendarViewShimmer.apply {
            dayBinder = weekCalendarShimmerAdapter
            setup(
                startDate = dateOfMondayFirst,
                endDate = dateOfMondaySecond,
                firstDayOfWeek = firstDayOfWeekFromLocale()
            )
        }
    }

    private fun setupWeekCalendarView() {
        weekCalendarAdapter = WeekCalendarAdapter(this)
        binding.weekCalendarView.apply {
            dayBinder = weekCalendarAdapter
            setup(
                startDate = dateOfMondayFirst,
                endDate = dateOfMondaySecond,
                firstDayOfWeek = firstDayOfWeekFromLocale(Locale.forLanguageTag("RU"))
            )
            scrollToWeek(date = selectedDate)
        }
    }

    private fun setupRecyclerViewScheduleShimmer() {
        val lessonShimmerAdapter = LessonShimmerAdapter(25)
        binding.recyclerViewScheduleShimmer.apply {
            adapter = lessonShimmerAdapter
        }
    }

    private fun setupRecyclerViewSchedule() {
        lessonAdapter = LessonAdapter(scheduleType = scheduleRequest?.scheduleType)
        binding.recyclerViewSchedule.apply {
            adapter = lessonAdapter
        }
    }

    private fun initShimmerManager() {
        shimmerManager =
            ShimmerManager(
                shimmers = listOf(
                    binding.shimmerFrameLayoutTextView,
                    binding.shimmerFrameLayoutWeekCalendarView,
                    binding.shimmerFrameLayoutRecyclerViewSchedule,
                )
            )
    }

    private fun handleUserAuthData(userAuthData: UserAuthData?) {
        userAuthData?.let {
            scheduleRequest = createScheduleRequest(
                arguments = arguments,
                userAuthData = userAuthData,
            )
            binding.toolbarScheduleFragment.title = getToolbarTitle(userAuthData)
        } ?: navigateToUserRoleSelectionFragment()
    }

    private fun getToolbarTitle(userAuthData: UserAuthData): String {
        val prefixToolbarTitle = getString(R.string.title_scheduleFragment)
        val displayName = arguments?.getString(Constants.DISPLAY_NAME) ?: userAuthData.displayName

        return "$prefixToolbarTitle $displayName"
        // "Расписание, гр. 0001",
        // "Расписание, Иванов И.И"
    }

    private fun navigateToUserRoleSelectionFragment() {
        val navControllerMainActivity = Navigation.findNavController(
            requireActivity(),
            R.id.navHostFragmentMainActivity
        )
        navControllerMainActivity.navigate(R.id.action_mainFragment_to_userRoleSelectionFragment)
    }

    private fun handleScheduleState(state: Result<Schedule>) {
        when (state) {
            is Result.Success -> handleScheduleSuccessState(state)
            is Result.Error -> handleScheduleErrorState()
            Result.Loading -> handleScheduleLoadingState()
            Result.Initial -> handleScheduleInitialState()
        }
    }

    private fun handleScheduleSuccessState(state: Result.Success<Schedule>) {
        setupWeekCalendarView()
        setupRecyclerViewSchedule()
        scheduleViewModel.setSchedule(state.data)

        shimmerManager?.stopShimmers()
        binding.scheduleFragmentLoading.visibility = View.INVISIBLE
        binding.scheduleFragmentContent.visibility = View.VISIBLE
    }

    private fun handleScheduleErrorState() {
        shimmerManager?.stopShimmers()
        binding.scheduleFragmentLoading.visibility = View.INVISIBLE
        binding.scheduleFragmentError.root.visibility = View.VISIBLE
    }

    private fun handleScheduleLoadingState() {
        shimmerManager?.startShimmers()
        binding.scheduleFragmentLoading.visibility = View.VISIBLE
        binding.scheduleFragmentError.root.visibility = View.INVISIBLE
    }

    private fun handleScheduleInitialState() {
        scheduleViewModel.getSchedule(scheduleRequest)
    }

    private fun notifyWeekCalendarDateChange(newDate: LocalDate, oldDate: LocalDate) {
        binding.weekCalendarView.notifyDateChanged(newDate)
        binding.weekCalendarView.notifyDateChanged(oldDate)
    }
}