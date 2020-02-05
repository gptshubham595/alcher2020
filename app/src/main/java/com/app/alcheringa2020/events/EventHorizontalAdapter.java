package com.app.alcheringa2020.events;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.events.model.ItemModel;
import com.app.alcheringa2020.external.PrefManager;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.app.alcheringa2020.external.AppConstants.PRO_CATEGORY;
import static com.app.alcheringa2020.external.AppConstants.PRO_EVENT;
import static com.app.alcheringa2020.external.AppConstants.PRO_ID;

/**
 * Created by Jiaur Rahman on 04-Jan-20.
 */
public class EventHorizontalAdapter extends RecyclerView.Adapter<EventHorizontalAdapter.ViewHolder> {
    String TAG = EventHorizontalAdapter.class.getSimpleName();
    ArrayList<ItemModel> itemModelArrayList;
    Context mContext;
    int typeInt;
    String programmeCategory;
    PrefManager prefManager;

    public EventHorizontalAdapter(Context context, ArrayList<ItemModel> itemModelArrayList1, int position, String programmeCategory) {
        this.itemModelArrayList = itemModelArrayList1;
        this.mContext = context;
        this.typeInt = position;
        this.programmeCategory = programmeCategory;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView child_textView;
        RoundedImageView item_image,back;

        public ViewHolder(View v) {
            super(v);
            child_textView = (TextView) v.findViewById(R.id.textViewTitle);
            item_image = (RoundedImageView) v.findViewById(R.id.imageView);
        }
    }

    @Override
    public EventHorizontalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_event_horizontal, parent, false);
        return new EventHorizontalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EventHorizontalAdapter.ViewHolder holder, final int position) {
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("DEBUGTEST===", typeInt + " " + programmeCategory + " " + itemModelArrayList.get(position).getItem() + " " + itemModelArrayList.get(position).getItemCompetition());

                Intent intent = new Intent(mContext, EventActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(PRO_ID, typeInt);
                intent.putExtra(PRO_CATEGORY, programmeCategory);
                intent.putExtra(PRO_EVENT, itemModelArrayList.get(position).getItem());
                intent.putExtra("DESC", itemModelArrayList.get(position).getItemDescription());
                intent.putExtra("COMP", itemModelArrayList.get(position).getItemCompetition());
                intent.putExtra("BOUNTY", itemModelArrayList.get(position).getItemBounty());
                mContext.startActivity(intent);
            }
        });

        Typeface typeface = Typeface.createFromAsset(holder.itemView.getContext().getAssets(), "font/exo_regular.ttf");
        holder.child_textView.setTypeface(typeface);
    }

    @Override
    public int getItemCount() {
        return itemModelArrayList.size();
    }
}