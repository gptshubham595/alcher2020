package com.app.alcheringa2020.notification;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.notification.model.NotiDetailModel;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 09-Jan-20.
 */
public class NotificationChildAdapter extends RecyclerView.Adapter<NotificationChildAdapter.ViewHolder> {
    String TAG = NotificationChildAdapter.class.getSimpleName();
    ArrayList<NotiDetailModel> notiDetailModelArrayList;
    Context mContext;

    public NotificationChildAdapter(Context context, ArrayList<NotiDetailModel> notiDetailModelArrayList) {
        this.notiDetailModelArrayList = notiDetailModelArrayList;
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView noti;
        public TextView eventTxt,time;
        LinearLayout timeLyt;

        public ViewHolder(View v) {
            super(v);
            noti = (TextView)v.findViewById(R.id.venueTxt);
            eventTxt = (TextView) v.findViewById(R.id.eventTxt);
            timeLyt = (LinearLayout) v.findViewById(R.id.timeLyt);
            time = v.findViewById(R.id.timeTxt);
        }
    }

    public void removeItem(int position) {
        notiDetailModelArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public ArrayList<NotiDetailModel> getData() {
        return notiDetailModelArrayList;
    }
    @Override
    public NotificationChildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_my_schedule_child, parent, false);
        return new NotificationChildAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NotificationChildAdapter.ViewHolder holder, final int position) {
        NotiDetailModel notiDetailModel = notiDetailModelArrayList.get(position);
        holder.noti.setText(notiDetailModel.getMessage());
        holder.eventTxt.setText(notiDetailModel.getTitle());
        holder.time.setVisibility(View.GONE);
//        holder.timeLyt.setVisibility(View.GONE);
//        holder.eventTxt.setTextColor(ContextCompat.getColor(mContext,R.color.white));

        Typeface typeface = Typeface.createFromAsset(holder.itemView.getContext().getAssets(), "font/exo_regular.ttf");
        holder.eventTxt.setTypeface(typeface);
    }

    @Override
    public int getItemCount() {
        return notiDetailModelArrayList.size();
    }
}