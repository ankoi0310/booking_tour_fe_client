package vn.edu.hcmuaf.fit.travie.booking.ui.choosetime;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

import vn.edu.hcmuaf.fit.travie.booking.data.model.BookingRequest;
import vn.edu.hcmuaf.fit.travie.booking.ui.BookingViewModel;
import vn.edu.hcmuaf.fit.travie.booking.ui.BookingViewModelFactory;
import vn.edu.hcmuaf.fit.travie.booking.ui.choosetime.adapter.HourAdapter;
import vn.edu.hcmuaf.fit.travie.booking.ui.choosetime.adapter.TimeAdapter;
import vn.edu.hcmuaf.fit.travie.core.common.ui.SpaceItemDecoration;
import vn.edu.hcmuaf.fit.travie.core.shared.utils.DateTimeUtil;
import vn.edu.hcmuaf.fit.travie.databinding.FragmentChooseTimeByNightBinding;
import vn.edu.hcmuaf.fit.travie.hotel.data.model.BookingType;

public class ChooseTimeByNightFragment extends Fragment {
    FragmentChooseTimeByNightBinding binding;
    Calendar calendar = Calendar.getInstance();
    LocalDateTime checkInTime, checkOutTime;

    private final SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(12, RecyclerView.HORIZONTAL);
    private static final String ARG_BOOKING_TYPE = "bookingType";
    private BookingRequest bookingRequest;

    private BookingType bookingType;
    private HourAdapter hourAdapter;

    public ChooseTimeByNightFragment() {
        // Required empty public constructor
    }

    public static ChooseTimeByNightFragment newInstance(BookingType bookingType) {
        ChooseTimeByNightFragment fragment = new ChooseTimeByNightFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_BOOKING_TYPE, bookingType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bookingType = getArguments().getParcelable(ARG_BOOKING_TYPE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChooseTimeByNightBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManagerEvening = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvCheckInTimeEvening.setLayoutManager(layoutManagerEvening);
        binding.rvCheckInTimeEvening.addItemDecoration(spaceItemDecoration);

        LinearLayoutManager layoutManagerMorning = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvCheckInTimeMorning.setLayoutManager(layoutManagerMorning);
        binding.rvCheckInTimeMorning.addItemDecoration(spaceItemDecoration);

        initTimeList();

        binding.calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            calendar.set(year, month, dayOfMonth);
        });
    }

    private void initTimeList() {
        LocalTime currentTime = DateTimeUtil.roundUpToNearestHalfHour(LocalTime.now());
        LocalTime midnight = LocalTime.of(0, 0);
        LocalTime endOfDay = LocalTime.of(23, 59);
        List<String> eveningTimeList = DateTimeUtil.generateTimeList(
                currentTime.isAfter(bookingType.getStartTime()) ? currentTime :
                bookingType.getStartTime(), endOfDay);
        TimeAdapter nightTimeAdapter = new TimeAdapter(eveningTimeList);
        binding.rvCheckInTimeEvening.setAdapter(nightTimeAdapter);

        List<String> morningTimeList = DateTimeUtil.generateTimeList(
                currentTime.isBefore(bookingType.getEndTime()) ? currentTime :
                midnight, bookingType.getEndTime());
        TimeAdapter morningTimeAdapter = new TimeAdapter(morningTimeList);
        binding.rvCheckInTimeMorning.setAdapter(morningTimeAdapter);
    }
}