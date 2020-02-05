package com.app.alcheringa2020.events;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.events.model.ItemModel;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

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
        RoundedImageView item_image,back;
        EventListner eventListner;
        int eventId;

        public ViewHolder(View v, EventListner eventListner) {
            super(v);
            this.eventListner = eventListner;
            child_textView = (TextView) v.findViewById(R.id.textViewTitle);
            item_image = (RoundedImageView) v.findViewById(R.id.imageView);

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
    public void onBindViewHolder(final EventDetailAdapter.ViewHolder holder, final int position) {
//        Toast.makeText(mContext, "EVENT", Toast.LENGTH_SHORT).show();
//        Toast.makeText(mContext, itemModelArrayList.get(position).getItemImage(), Toast.LENGTH_SHORT).show();
        try {
            Picasso.get().load(itemModelArrayList.get(position).getItemImage()).networkPolicy(NetworkPolicy.OFFLINE).into(holder.item_image, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(itemModelArrayList.get(position).getItemImage()).into(holder.item_image);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        holder.child_textView.setText(itemModelArrayList.get(position).getItem());
        holder.eventId = itemModelArrayList.get(position).getItemId();
        Typeface typeface = Typeface.createFromAsset(holder.itemView.getContext().getAssets(), "font/exo_regular.ttf");
        holder.child_textView.setTypeface(typeface);

        holder.item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, itemModelArrayList.get(position).getItem(), Toast.LENGTH_SHORT).show();
                try {
                    EventActivity.descriptionTxt.setText(itemModelArrayList.get(position).getItemDescription());
                    EventActivity.competitionTxt.setText(itemModelArrayList.get(position).getItem());
                    EventActivity.bountyTxt.setText("Bounties - " +itemModelArrayList.get(position).getItemBounty());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return itemModelArrayList.size();
    }
}