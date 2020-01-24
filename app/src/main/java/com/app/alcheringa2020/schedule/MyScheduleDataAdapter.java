package com.app.alcheringa2020.schedule;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.external.SwipeToDeleteCallback;
import com.app.alcheringa2020.schedule.model.MyScheduleModel;
import com.app.alcheringa2020.schedule.model.ScheduleModel;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 07-Jan-20.
 */
public class MyScheduleDataAdapter extends   RecyclerView.Adapter<MyScheduleDataAdapter.ViewHolder>{
    String TAG = MyScheduleDataAdapter.class.getSimpleName();

    ArrayList<MyScheduleModel> scheduleModelArrayList;
    Context mContext;

    public MyScheduleDataAdapter(Context context, ArrayList<MyScheduleModel> scheduleModelArrayList) {
        this.scheduleModelArrayList = scheduleModelArrayList;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_text;
        RecyclerView rv_child;
        int position_data;

        public ViewHolder(View v) {
            super(v);
            title_text = (TextView) v.findViewById(R.id.text_title);
            rv_child = (RecyclerView) v.findViewById(R.id.event_data);

        }

    }

    @Override
    public MyScheduleDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_my_schedule, parent, false);
        return new MyScheduleDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyScheduleDataAdapter.ViewHolder holder, int position) {
        holder.position_data = position;
        MyScheduleModel scheduleModel = scheduleModelArrayList.get(position);
        holder.title_text.setText(scheduleModelArrayList.get(position).getDay());

        MyScheduleChildAdapter myScheduleChildAdapter = new MyScheduleChildAdapter(mContext, scheduleModel.getEventModelArrayList());
        holder.rv_child.setLayoutManager(new LinearLayoutManager(mContext));
        holder.rv_child.setAdapter(myScheduleChildAdapter);

        enableSwipeToDeleteAndUndo(myScheduleChildAdapter, holder);
        Typeface typeface = Typeface.createFromAsset(holder.itemView.getContext().getAssets(), "font/exo_regular.ttf");
        holder.title_text.setTypeface(typeface);
    }

    private void enableSwipeToDeleteAndUndo(final MyScheduleChildAdapter mAdapter, ViewHolder holder) {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(mContext) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final String item = mAdapter.getData().get(position).getVenue();

                mAdapter.removeItem(position);



            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(holder.rv_child);
    }

    @Override
    public int getItemCount() {

        return scheduleModelArrayList.size();
    }
}