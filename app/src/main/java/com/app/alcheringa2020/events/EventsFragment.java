package com.app.alcheringa2020.events;

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
import com.app.alcheringa2020.events.model.ProgrammeModel;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 31-Dec-19.
 */
public class EventsFragment extends BaseFragment {
    private static String TAG = EventsFragment.class.getSimpleName();
    static ViewGroup fragment_container;
    static EventsFragment fragment;
    RecyclerView event_recycler;
    EventDataAdapter eventDataAdapter;
    ArrayList<ProgrammeModel> programmeModelArrayList;

    public EventsFragment() {
        //blank Constructor
    }

    public static EventsFragment newInstance(Context context1) {
        fragment = new EventsFragment();
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
        event_recycler = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        event_recycler.setLayoutManager(mLayoutManager);
        event_recycler.setItemAnimator(new DefaultItemAnimator());
        initData();
        return view;
    }

    private void initData() {
        try {
            programmeModelArrayList  = EventsDataModel.programmeModelArrayList(context);
            eventDataAdapter = new EventDataAdapter(context, programmeModelArrayList);
            event_recycler.setAdapter(eventDataAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
