package com.app.alcheringa2020.schedule;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.events.EventListner;
import com.app.alcheringa2020.schedule.model.EventModel;
import com.app.alcheringa2020.schedule.model.ScheduleModel;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 11-Jan-20.
 */
public class ScheduleDayAdapter extends RecyclerView.Adapter<ScheduleDayAdapter.ViewHolder> {
    String TAG = ScheduleDayAdapter.class.getSimpleName();
    ArrayList<ScheduleModel> scheduleModelArrayList;
    Context mContext;
    ScheduleListner scheduleListner;
    int pre_position = -1;

    public ScheduleDayAdapter(Context context, ArrayList<ScheduleModel> scheduleModelArrayList, ScheduleListner scheduleListner) {
        this.scheduleModelArrayList = scheduleModelArrayList;
        this.mContext = context;
        this.scheduleListner = scheduleListner;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView day;
        ScheduleListner scheduleListner;
        int dayId, position_data;
        CardView day_lyt;

        public ViewHolder(View v, ScheduleListner scheduleListner) {
            super(v);
            this.scheduleListner = scheduleListner;
            day = (TextView) v.findViewById(R.id.day);
            day_lyt = (CardView) v.findViewById(R.id.day_lyt);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (scheduleListner != null) {
                scheduleListner.onitemClicked(dayId);

            }
        }
    }


    @Override
    public ScheduleDayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_schedule_day, parent, false);
        return new ScheduleDayAdapter.ViewHolder(view, scheduleListner);
    }

    @Override
    public void onBindViewHolder(final ScheduleDayAdapter.ViewHolder holder, final int position) {
        ScheduleModel scheduleModel = scheduleModelArrayList.get(position);
        holder.day.setText(scheduleModel.getDay());
        holder.dayId = scheduleModel.getId();
        holder.position_data = position;


        Typeface typeface = Typeface.createFromAsset(holder.itemView.getContext().getAssets(), "font/exo_regular.ttf");
        holder.day.setTypeface(typeface);
    }

    @Override
    public int getItemCount() {
        return scheduleModelArrayList.size();
    }
}