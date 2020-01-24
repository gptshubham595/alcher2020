package com.app.alcheringa2020.events;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.events.model.ItemModel;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 06-Jan-20.
 */
public class EventDetailAdapter extends RecyclerView.Adapter<EventDetailAdapter.ViewHolder> {
    String TAG = EventDetailAdapter.class.getSimpleName();

    ArrayList<ItemModel> itemModelArrayList;
    Context mContext;
    EventListner eventListner;

    public EventDetailAdapter(Context context, ArrayList<ItemModel> itemModelArrayList, EventListner eventListner) {
        this.itemModelArrayList = itemModelArrayList;
        mContext = context;
        this.eventListner = eventListner;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView child_textView;
        ImageView item_image;
        EventListner eventListner;
        int eventId;

        public ViewHolder(View v, EventListner eventListner) {
            super(v);
            this.eventListner = eventListner;
            child_textView = (TextView) v.findViewById(R.id.textViewTitle);
            item_image = (ImageView) v.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View v) {
            if (eventListner != null) {
                eventListner.onitemClicked(eventId);
            }
        }
    }

    @Override
    public EventDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_event_horizontal, parent, false);
        return new EventDetailAdapter.ViewHolder(view, eventListner);
    }

    @Override
    public void onBindViewHolder(EventDetailAdapter.ViewHolder holder, int position) {
        holder.child_textView.setText(itemModelArrayList.get(position).getItem());
        holder.eventId = itemModelArrayList.get(position).getItemId();
        Typeface typeface = Typeface.createFromAsset(holder.itemView.getContext().getAssets(), "font/exo_regular.ttf");
        holder.child_textView.setTypeface(typeface);
    }

    @Override
    public int getItemCount() {

        return itemModelArrayList.size();
    }
}