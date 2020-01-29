package com.app.alcheringa2020.notification;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.base.BaseFragment;
import com.app.alcheringa2020.notification.model.NotiDetailModel;
import com.app.alcheringa2020.notification.model.NotificationModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jiaur Rahman on 09-Jan-20.
 */
//public class NotificationFragment extends BaseFragment {
//    private static String TAG = NotificationFragment.class.getSimpleName();
//    static ViewGroup fragment_container;
//    static NotificationFragment fragment;
//    RecyclerView notification_recycler;
//    ArrayList<NotificationModel> notificationModelArrayList;
//    NotificationDataAdapter notificationDataAdapter;
//
//    public NotificationFragment() {
//        //blank Constructor
//    }
//
//    public static NotificationFragment newInstance(Context context1) {
//        fragment = new NotificationFragment();
//        context = context1;
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        fragment_container = container;
//        context = getActivity().getApplicationContext();
//        view = inflater.inflate(R.layout.recycler_layout, container, false);
//        notification_recycler = view.findViewById(R.id.recyclerView);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//        notification_recycler.setLayoutManager(mLayoutManager);
//        notification_recycler.setItemAnimator(new DefaultItemAnimator());
//        initData();
//        return view;
//    }
//
//    private void initData() {
//        try {
//            notificationModelArrayList = NotificationDataModel.notificationModelArrayList(context);
//            notificationDataAdapter = new NotificationDataAdapter(context, notificationModelArrayList);
//            notification_recycler.setAdapter(notificationDataAdapter);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
public class NotificationFragment extends BaseFragment {
    private static String TAG = NotificationFragment.class.getSimpleName();
    static ViewGroup fragment_container;
    static NotificationFragment fragment;
    RecyclerView notification_recycler;
    ArrayList<NotificationModel> notificationModelArrayList;
    ArrayList<NotiDetailModel> data = new ArrayList<>();
    NotificationDataAdapter notificationDataAdapter;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

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
        try{
        notification_recycler = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        notification_recycler.setLayoutManager(mLayoutManager);
        notification_recycler.setItemAnimator(new DefaultItemAnimator());
        notificationModelArrayList = new ArrayList<>();
        notificationDataAdapter = new NotificationDataAdapter(context, notificationModelArrayList);
        notification_recycler.setAdapter(notificationDataAdapter);

        Map<String,Object> mp=new HashMap<>();
        mp.put("name","mohan");

        firestore.collection("notificationLog").document("allNotifications").collection("notifications").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isComplete()){
                    Toast.makeText(getContext(), "task completed", Toast.LENGTH_SHORT).show();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        data.add(new NotiDetailModel(Integer.parseInt(document.getString("id")),document.getString("body"),document.getString("title")));
                    }
                    Collections.sort(data,new SortbyId());
                    notificationModelArrayList.add(new NotificationModel(1,"today","1/2/2020",data));
                    notificationDataAdapter.notifyDataSetChanged();

                }else{
                    Toast.makeText(getContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });}catch (Exception e){e.printStackTrace();
            Toast.makeText(context, "Some Error Occured", Toast.LENGTH_SHORT).show();}

//        initData();
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
class SortbyId implements Comparator<NotiDetailModel>
{
    // Used for sorting in ascending order of
    // roll number
    public int compare(NotiDetailModel a, NotiDetailModel b)
    {
        return b.getId() - a.getId();
    }
}
