package com.app.alcheringa2020.notification;

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
import com.app.alcheringa2020.notification.model.NotificationModel;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 09-Jan-20.
 */
public class NotificationFragment extends BaseFragment {
    private static String TAG = NotificationFragment.class.getSimpleName();
    static ViewGroup fragment_container;
    static NotificationFragment fragment;
    RecyclerView notification_recycler;
    ArrayList<NotificationModel> notificationModelArrayList;
    NotificationDataAdapter notificationDataAdapter;

    public NotificationFragment() {
        //blank Constructor
    }

    public static NotificationFragment newInstance(Context context1) {
        fragment = new NotificationFragment();
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
        notification_recycler = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        notification_recycler.setLayoutManager(mLayoutManager);
        notification_recycler.setItemAnimator(new DefaultItemAnimator());
        initData();
        return view;
    }

    private void initData() {
        try {
            notificationModelArrayList = NotificationDataModel.notificationModelArrayList(context);
            notificationDataAdapter = new NotificationDataAdapter(context, notificationModelArrayList);
            notification_recycler.setAdapter(notificationDataAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
