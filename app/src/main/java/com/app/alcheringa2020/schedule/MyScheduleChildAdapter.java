package com.app.alcheringa2020.schedule;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.schedule.model.EventModel;
import com.app.alcheringa2020.schedule.model.MyScheduleEventModel;
import com.app.alcheringa2020.schedule.model.VanueModel;

import java.util.ArrayList;


/**
 * Created by Jiaur Rahman on 07-Jan-20.
 */
public class MyScheduleChildAdapter extends RecyclerView.Adapter<MyScheduleChildAdapter.ViewHolder> {
    String TAG = MyScheduleChildAdapter.class.getSimpleName();
    ArrayList<MyScheduleEventModel> myScheduleEventModelArrayList;
    Context mContext;

    public MyScheduleChildAdapter(Context context, ArrayList<MyScheduleEventModel> myScheduleEventModelArrayList) {
        this.myScheduleEventModelArrayList = myScheduleEventModelArrayList;
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView eventTxt, venueTxt, timeTxt;

        public ViewHolder(View v) {
            super(v);
            eventTxt = (TextView) v.findViewById(R.id.eventTxt);
            venueTxt = (TextView) v.findViewById(R.id.venueTxt);
            timeTxt = (TextView) v.findViewById(R.id.timeTxt);

        }
    }

    public void removeItem(int position) {
        myScheduleEventModelArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public ArrayList<MyScheduleEventModel> getData() {
        return myScheduleEventModelArrayList;
    }

    @Override
    public MyScheduleChildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_my_schedule_child, parent, false);
        return new MyScheduleChildAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyScheduleChildAdapter.ViewHolder holder, final int position) {
        MyScheduleEventModel eventModel = myScheduleEventModelArrayList.get(position);

        holder.venueTxt.setText(eventModel.getVenue());

        holder.eventTxt.setText(eventModel.getEvent_name());

        holder.timeTxt.setText(eventModel.getStart_time() + "-" + eventModel.getEnd_time());

        Typeface typeface = Typeface.createFromAsset(holder.itemView.getContext().getAssets(), "font/exo_regular.ttf");
        holder.eventTxt.setTypeface(typeface);
        holder.venueTxt.setTypeface(typeface);
        holder.timeTxt.setTypeface(typeface);


    }

    @Override
    public int getItemCount() {
        return myScheduleEventModelArrayList.size();
    }
}