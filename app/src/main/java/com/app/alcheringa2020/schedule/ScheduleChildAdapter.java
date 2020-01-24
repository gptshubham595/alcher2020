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
import com.app.alcheringa2020.schedule.model.VanueModel;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 11-Jan-20.
 */
public class ScheduleChildAdapter extends RecyclerView.Adapter<ScheduleChildAdapter.ViewHolder> {
    String TAG = ScheduleChildAdapter.class.getSimpleName();
    ArrayList<EventModel> eventModelArrayList;
    Context mContext;

    public ScheduleChildAdapter(Context context, ArrayList<EventModel> eventModelArrayList) {
        this.eventModelArrayList = eventModelArrayList;
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
    public ScheduleChildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_schedule_child, parent, false);
        return new ScheduleChildAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ScheduleChildAdapter.ViewHolder holder, final int position) {
        EventModel eventModel = eventModelArrayList.get(position);
        //holder.venueTxt.setText(vanueModel.getVenue_name());
        Typeface typeface = Typeface.createFromAsset(holder.itemView.getContext().getAssets(), "font/exo_regular.ttf");
        //holder.venueTxt.setTypeface(typeface);

    }

    @Override
    public int getItemCount() {
        return eventModelArrayList.size();
    }
}