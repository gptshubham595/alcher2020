package com.app.alcheringa2020.schedule;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.base.BaseFragment;
import com.app.alcheringa2020.schedule.model.MyScheduleModel;
import com.app.alcheringa2020.schedule.model.ScheduleModel;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 31-Dec-19.
 */
public class MyScheduleFragment extends BaseFragment {
    private static String TAG = MyScheduleFragment.class.getSimpleName();
    static ViewGroup fragment_container;
    static MyScheduleFragment fragment;
    RecyclerView schedule_recycler;
    ArrayList<MyScheduleModel> scheduleModelArrayList;
    MyScheduleDataAdapter myScheduleDataAdapter;

    public MyScheduleFragment() {
        //blank Constructor
    }

    public static MyScheduleFragment newInstance(Context context1) {
        fragment = new MyScheduleFragment();
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
        view = inflater.inflate(R.layout.recycler_layout, container, false);
        schedule_recycler = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        schedule_recycler.setLayoutManager(mLayoutManager);
        schedule_recycler.setItemAnimator(new DefaultItemAnimator());
        initData();
        return view;
    }
    private void initData() {
        try {
            scheduleModelArrayList  = MyScheduleDataModel.myScheduleModelArrayList(context);
            myScheduleDataAdapter = new MyScheduleDataAdapter(context, scheduleModelArrayList);
            schedule_recycler.setAdapter(myScheduleDataAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
