package com.app.alcheringa2020.notification;

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
import com.app.alcheringa2020.notification.model.NotificationModel;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 09-Jan-20.
 */
public class NotificationDataAdapter extends RecyclerView.Adapter<NotificationDataAdapter.ViewHolder> {
    String TAG = NotificationDataAdapter.class.getSimpleName();

    ArrayList<NotificationModel> notificationModelArrayList;
    Context mContext;

    public NotificationDataAdapter(Context context, ArrayList<NotificationModel> notificationModelArrayList) {
        this.notificationModelArrayList = notificationModelArrayList;
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
    public NotificationDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_my_schedule, parent, false);
        return new NotificationDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationDataAdapter.ViewHolder holder, int position) {
        holder.position_data = position;
        NotificationModel notificationModel = notificationModelArrayList.get(position);
        holder.title_text.setText(notificationModel.getDay());
        NotificationChildAdapter notificationChildAdapter = new NotificationChildAdapter(mContext, notificationModel.getNotiDetailModelArrayList());
        holder.rv_child.setLayoutManager(new LinearLayoutManager(mContext));
        holder.rv_child.setAdapter(notificationChildAdapter);
//        enableSwipeToDeleteAndUndo(notificationChildAdapter, holder);
        Typeface typeface = Typeface.createFromAsset(holder.itemView.getContext().getAssets(), "font/exo_regular.ttf");
        holder.title_text.setTypeface(typeface);
    }

    private void enableSwipeToDeleteAndUndo(final NotificationChildAdapter mAdapter, NotificationDataAdapter.ViewHolder holder) {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(mContext) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final String item = mAdapter.getData().get(position).getMessage();

                mAdapter.removeItem(position);


                /*Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mAdapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();*/

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(holder.rv_child);
    }

    @Override
    public int getItemCount() {

        return notificationModelArrayList.size();
    }
}