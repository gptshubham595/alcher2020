package com.app.alcheringa2020.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.base.BaseFragment;
import com.app.alcheringa2020.profile.model.TeamModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jiaur Rahman on 09-Jan-20.
 */
public class TeamFragment extends BaseFragment {
    private static String TAG = TeamFragment.class.getSimpleName();
    static ViewGroup fragment_container;
    static TeamFragment fragment;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    ArrayList<TeamModel> teamModelArrayList;
    TeamDataAdapter teamDataAdapter;
    RecyclerView teamRecycler;
    CircleImageView profile_image;
    TextView leader_name, leader_code;

    public TeamFragment() {
        //blank Constructor
    }

    public static TeamFragment newInstance(Context context1) {
        fragment = new TeamFragment();
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
        view = inflater.inflate(R.layout.fragment_team, container, false);
        initView();
        initData();
        return view;
    }

    private void initView() {
        try {
            teamRecycler = view.findViewById(R.id.teamRecycler);
            leader_name = view.findViewById(R.id.leader_name);
            leader_code = view.findViewById(R.id.leader_code);
            teamRecycler.setLayoutManager(new LinearLayoutManager(context));
            teamRecycler.setItemAnimator(new DefaultItemAnimator());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        try {
            teamModelArrayList = TeamDataModel.teamModelArrayList(context);
            teamDataAdapter = new TeamDataAdapter(context, teamModelArrayList);
            teamRecycler.setAdapter(teamDataAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
