package com.app.alcheringa2020.map;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;

import java.util.ArrayList;
import java.util.Locale;

public class mapAdapter extends RecyclerView.Adapter<mapAdapter.ViewHolder> {

    private ArrayList<mapClass> itemList;
    private Context context;

    public mapAdapter(ArrayList<mapClass> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_event_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mapClass ne = itemList.get(position);
        holder.event.setText(ne.getEvent());
        holder.date.setText(ne.getDate());
        holder.venue.setText(ne.getVenue());
        final float latitude = Float.valueOf(ne.getLatitude());
        final float longitude = Float.valueOf(ne.getLongitude());
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude) ;
        uri  = uri + "?q="+ Uri.encode(ne.getVenue() + "iit guwahati");
        final String uu = uri;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uu));
                context.startActivity(intent);
//                Toast.makeText(context, "layout "+position+ " is clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView event,venue,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            event = itemView.findViewById(R.id.eventTxt);
            venue = itemView.findViewById(R.id.venueTxt);
            date = itemView.findViewById(R.id.timeDate);
        }
    }
    public void filterList(ArrayList<mapClass> filteredList) {
        itemList = filteredList;
        notifyDataSetChanged();
    }
}
