package com.app.alcheringa2020.feed;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.base.BaseFragment;

/**
 * Created by Jiaur Rahman on 27-Dec-19.
 */
public class FeedFragment extends BaseFragment {
    private static String TAG = FeedFragment.class.getSimpleName();
    static ViewGroup fragment_container;
    static FeedFragment fragment;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    public FeedFragment() {
        //blank Constructor
    }

    public static FeedFragment newInstance(Context context1) {
        fragment = new FeedFragment();
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
        view = inflater.inflate(R.layout.fragment_feed, container, false);
        return view;
    }
}
