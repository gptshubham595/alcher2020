package com.app.alcheringa2020.schedule;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.events.EventHorizontalAdapter;
import com.app.alcheringa2020.schedule.model.EventModel;
import com.app.alcheringa2020.schedule.model.ScheduleModel;
import com.app.alcheringa2020.schedule.model.VanueModel;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 11-Jan-20.
 */
public class ScheduleEventAdapter extends RecyclerView.Adapter<ScheduleEventAdapter.ViewHolder> {
    String TAG = ScheduleEventAdapter.class.getSimpleName();
    ArrayList<VanueModel> vanueModelArrayList;
    Context mContext;

    public ScheduleEventAdapter(Context context, ArrayList<VanueModel> vanueModelArrayList) {
        this.vanueModelArrayList = vanueModelArrayList;
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView venueTxt;
        RecyclerView eventRecycler;

        public ViewHolder(View v) {
            super(v);
            venueTxt = (TextView) v.findViewById(R.id.venueTxt);
            eventRecycler = (RecyclerView) v.findViewById(R.id.eventRecycler);
        }
    }


    @Override
    public ScheduleEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_schedule, parent, false);
        return new ScheduleEventAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ScheduleEventAdapter.ViewHolder holder, final int position) {
        VanueModel vanueModel = vanueModelArrayList.get(position);
        holder.venueTxt.setText(vanueModel.getVenue_name());
        Typeface typeface = Typeface.createFromAsset(holder.itemView.getContext().getAssets(), "font/exo_regular.ttf");
        holder.venueTxt.setTypeface(typeface);

        ScheduleChildAdapter itemAdapter = new ScheduleChildAdapter(mContext, vanueModel.getEventModelArrayList());
        holder.eventRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.eventRecycler.setAdapter(itemAdapter);
    }

    @Override
    public int getItemCount() {
        return vanueModelArrayList.size();
    }
}