package com.app.alcheringa2020.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class feedAdapter extends RecyclerView.Adapter<feedAdapter.ViewHolder> {

    private ArrayList<feedClass> itemList;
    private Context context;

    public feedAdapter(ArrayList<feedClass> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_feed,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        feedClass ne = itemList.get(position);
        holder.title.setText(ne.getTitle());
        holder.des.setText(ne.getText());
        String s = ne.getImage();
        if(!s.isEmpty()){
            Picasso.get().load(s).centerCrop().fit().into(holder.img);
            holder.img.setVisibility(View.VISIBLE);
        }else{
            holder.img.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title,des;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.single_feed_image);
            title = itemView.findViewById(R.id.single_feed_title);
            des = itemView.findViewById(R.id.single_feed_text);
        }
    }
}
