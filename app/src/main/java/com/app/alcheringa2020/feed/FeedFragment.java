package com.app.alcheringa2020.feed;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.MainActivity;
import com.app.alcheringa2020.R;
import com.app.alcheringa2020.base.BaseFragment;
import com.app.alcheringa2020.notification.model.NotiDetailModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Jiaur Rahman on 27-Dec-19.
 */
public class FeedFragment extends BaseFragment {
    private static String TAG = FeedFragment.class.getSimpleName();
    static ViewGroup fragment_container;
    static FeedFragment fragment;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private RecyclerView recyclerView;
    private feedAdapter adapter;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private ArrayList<feedClass> itemList;
    RoundedImageView h1,h2,h3,h4,h5;
    ProgressDialog dialog;

    public FeedFragment() {
        //blank Constructor
    }

    public static FeedFragment newInstance(Context context1) {
        fragment = new FeedFragment();
        context = context1;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_container = container;
        context = getActivity().getApplicationContext();
        dialog=new ProgressDialog(getContext());

        view = inflater.inflate(R.layout.fragment_feed, container, false);

        h1= view.findViewById(R.id.h1);
        h2= view.findViewById(R.id.h2);
        h3=view.findViewById(R.id.h3);
        h4= view.findViewById(R.id.h4);
        h5= view.findViewById(R.id.h5);

        h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.highligh);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                RoundedImageView img=dialog.findViewById(R.id.titleimage);
                img.setImageResource(R.drawable.h1);

                dialog.show();

            }
        });


        h2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.highligh);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                RoundedImageView img=dialog.findViewById(R.id.titleimage);
                img.setImageResource(R.drawable.h2);

                dialog.show();

            }
        });

        h3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.highligh);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                RoundedImageView img=dialog.findViewById(R.id.titleimage);
                img.setImageResource(R.drawable.h3);

                dialog.show();

            }
        });

        h4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.highligh);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                RoundedImageView img=dialog.findViewById(R.id.titleimage);
                img.setImageResource(R.drawable.h4);
                dialog.show();
            }
        });

        h5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.highligh);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                RoundedImageView img=dialog.findViewById(R.id.titleimage);
                img.setImageResource(R.drawable.h5);

                dialog.show();

            }
        });


        databaseReference = databaseReference.child("feed");
        databaseReference.keepSynced(true);
        recyclerView = view.findViewById(R.id.feedRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemList = new ArrayList<>();

        adapter = new feedAdapter(itemList,getContext());
        recyclerView.setAdapter(adapter);
        dialog.setMessage("Loading");
        dialog.show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    itemList.add(new feedClass(snapshot.child("title").getValue().toString(),snapshot.child("text").getValue().toString(),snapshot.child("image").getValue().toString(),snapshot.child("time").getValue().toString()));
                }
                Collections.sort(itemList,new SortbyId());
                adapter.notifyDataSetChanged();
            dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}

class SortbyId implements Comparator<feedClass>
{
    // Used for sorting in ascending order of
    // roll number
    public int compare(feedClass a, feedClass b)
    {
        return (int) (Long.parseLong(b.getTime()) - Long.parseLong(a.getTime()));
    }
}