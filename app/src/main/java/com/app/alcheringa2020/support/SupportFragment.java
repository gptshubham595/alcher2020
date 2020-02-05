package com.app.alcheringa2020.support;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.alcheringa2020.ContactUs;
import com.app.alcheringa2020.Developer;
import com.app.alcheringa2020.Feedback;
import com.app.alcheringa2020.MainActivity;
import com.app.alcheringa2020.R;
import com.app.alcheringa2020.base.BaseFragment;

/**
 * Created by Jiaur Rahman on 31-Dec-19.
 */
public class SupportFragment extends BaseFragment {
    private static String TAG = SupportFragment.class.getSimpleName();
    static ViewGroup fragment_container;
    static SupportFragment fragment;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    RelativeLayout profile_rlt;

    public SupportFragment() {
        //blank Constructor
    }

    public static SupportFragment newInstance(Context context1) {
        fragment = new SupportFragment();
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
        view = inflater.inflate(R.layout.fragment_support, container, false);
        RelativeLayout feedback_rlt,contact_rlt,sponsor_rlt,hospitality_rlt,developers_rlt;
        feedback_rlt=view.findViewById(R.id.feedback_rlt);
        contact_rlt=view.findViewById(R.id.contact_rlt);
        developers_rlt=view.findViewById(R.id.developers_rlt);

        feedback_rlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i=new Intent(context, Feedback.class);
                startActivityForResult(i, 1);

            }
        });
        contact_rlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ContactUs.class));
            }
        });
        developers_rlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Developer.class));
            }
        });
//        sponsor_rlt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        hospitality_rlt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        return view;
    }

}
