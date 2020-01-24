package com.app.alcheringa2020.events;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.events.model.ProgrammeModel;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 04-Jan-20.
 */
public class EventDataAdapter extends RecyclerView.Adapter<EventDataAdapter.ViewHolder> {
    String TAG = EventDataAdapter.class.getSimpleName();

    ArrayList<ProgrammeModel> programmeModelArrayList;
    Context mContext;

    public EventDataAdapter(Context context, ArrayList<ProgrammeModel> programmeModelArrayList1) {
        programmeModelArrayList = programmeModelArrayList1;
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
    public EventDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_event, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.position_data = position;
        ProgrammeModel programmeModel = programmeModelArrayList.get(position);
        holder.title_text.setText(programmeModelArrayList.get(position).getProCategory());

        EventHorizontalAdapter itemAdapter = new EventHorizontalAdapter(mContext, programmeModel.getItemModelArrayList(),programmeModel.getProId(),programmeModel.getProCategory());
        holder.rv_child.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.rv_child.setAdapter(itemAdapter);

        Typeface typeface = Typeface.createFromAsset(holder.itemView.getContext().getAssets(), "font/exo_regular.ttf");
        holder.title_text.setTypeface(typeface);
    }

    @Override
    public int getItemCount() {

        return programmeModelArrayList.size();
    }
}