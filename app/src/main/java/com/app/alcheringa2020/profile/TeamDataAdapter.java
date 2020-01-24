package com.app.alcheringa2020.profile;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.profile.model.TeamModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jiaur Rahman on 10-Jan-20.
 */
public class TeamDataAdapter extends RecyclerView.Adapter<TeamDataAdapter.ViewHolder> {
    String TAG = TeamDataAdapter.class.getSimpleName();

    ArrayList<TeamModel> teamModelArrayList;
    Context mContext;

    public TeamDataAdapter(Context context, ArrayList<TeamModel> teamModelArrayList) {
        this.teamModelArrayList = teamModelArrayList;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profile_image;
        TextView member_name,member_code;
        int position_data;

        public ViewHolder(View v) {
            super(v);
            member_name = (TextView) v.findViewById(R.id.member_name);
            member_code = (TextView) v.findViewById(R.id.member_code);
            profile_image = (CircleImageView) v.findViewById(R.id.profile_image);
        }
    }

    @Override
    public TeamDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_team, parent, false);
        return new TeamDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamDataAdapter.ViewHolder holder, int position) {
        holder.position_data = position;
        TeamModel teamModel = teamModelArrayList.get(position);
        holder.member_name.setText(teamModel.getName());
        holder.member_code.setText("#"+teamModel.getCode());
        Typeface typeface = Typeface.createFromAsset(holder.itemView.getContext().getAssets(), "font/exo_regular.ttf");
        holder.member_name.setTypeface(typeface);
        holder.member_code.setTypeface(typeface);
    }

    @Override
    public int getItemCount() {
        return teamModelArrayList.size();
    }
}