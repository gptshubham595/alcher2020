package com.app.alcheringa2020.schedule;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.base.BaseFragment;
import com.app.alcheringa2020.schedule.model.ScheduleModel;
import com.app.alcheringa2020.schedule.model.VanueModel;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 11-Jan-20.
 */
public class ScheduleFragment extends BaseFragment implements ScheduleListner {
    private static String TAG = ScheduleFragment.class.getSimpleName();
    static ViewGroup fragment_container;
    static ScheduleFragment fragment;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    RecyclerView dayRecycler, venueRecycler, eventRecycler;
    ArrayList<ScheduleModel> scheduleModelArrayList;
    ArrayList<VanueModel> vanueModelArrayList;
    ScheduleDayAdapter scheduleDayAdapter;
    ScheduleEventAdapter scheduleEventAdapter;

    public ScheduleFragment() {
        //blank Constructor
    }

    public static ScheduleFragment newInstance(Context context1) {
        fragment = new ScheduleFragment();
        context = context1;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_container = container;
        context = getActivity().getApplicationContext();
        view = inflater.inflate(R.layout.fragment_schedule, container, false);
        dayRecycler = view.findViewById(R.id.dayRecycler);
        venueRecycler = (RecyclerView) view.findViewById(R.id.venueRecycler);
        //eventRecycler = view.findViewById(R.id.eventRecycler);
        initData();
        return view;
    }

    private void initData() {
        dayRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        dayRecycler.setItemAnimator(new DefaultItemAnimator());

        scheduleModelArrayList = ScheduleDataModel.scheduleModelArrayList(context);
        scheduleDayAdapter = new ScheduleDayAdapter(context, scheduleModelArrayList, this);
        dayRecycler.setAdapter(scheduleDayAdapter);

        for (int i = 0; i < scheduleModelArrayList.size(); i++) {
            ScheduleModel scheduleModel = scheduleModelArrayList.get(i);
            displayData(scheduleModel.getId());
            break;
        }

        venueRecycler.setLayoutManager(new LinearLayoutManager(context));
        venueRecycler.setItemAnimator(new DefaultItemAnimator());

    }

    private void displayData(int day_id) {
        try {
            for (int i = 0; i < scheduleModelArrayList.size(); i++) {
                ScheduleModel scheduleModel = scheduleModelArrayList.get(i);
                if (scheduleModel.getId() == day_id) {
                    vanueModelArrayList = new ArrayList<>();
                    for (int j = 0; j < scheduleModel.getVanueModelArrayList().size(); j++) {
                        VanueModel vanueModel = scheduleModel.getVanueModelArrayList().get(j);
                        vanueModelArrayList.add(new VanueModel(vanueModel.getId(), vanueModel.getVenue_name(), vanueModel.getEventModelArrayList()));
                    }
                    break;
                }
            }
            scheduleEventAdapter = new ScheduleEventAdapter(context, vanueModelArrayList);
            venueRecycler.setAdapter(scheduleEventAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onitemClicked(int position) {
        try {
            Log.d(TAG, "position data: " + position);
            displayData(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
