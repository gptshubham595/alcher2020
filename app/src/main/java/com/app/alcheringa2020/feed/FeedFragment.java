package com.app.alcheringa2020.feed;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.base.BaseFragment;
import com.app.alcheringa2020.notification.model.NotiDetailModel;

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
    private ArrayList<feedClass> itemList;


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
        view = inflater.inflate(R.layout.fragment_feed, container, false);

        recyclerView = view.findViewById(R.id.feedRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemList = new ArrayList<>();
        itemList.add(new feedClass("Test feed","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.","https://scontent.fdel29-1.fna.fbcdn.net/v/t1.0-9/s960x960/83723262_486448612246622_7145432168599126016_o.jpg?_nc_cat=111&_nc_ohc=kvBafHEbtn4AX_jDKBP&_nc_ht=scontent.fdel29-1.fna&oh=d521de8caffaf27f3fab33a82524db1d&oe=5E9401A9","1"));
        itemList.add(new feedClass("Another feed Class","It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)","","2"));
        itemList.add(new feedClass("Test feed","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.","https://scontent.fdel29-1.fna.fbcdn.net/v/t1.0-9/s960x960/83723262_486448612246622_7145432168599126016_o.jpg?_nc_cat=111&_nc_ohc=kvBafHEbtn4AX_jDKBP&_nc_ht=scontent.fdel29-1.fna&oh=d521de8caffaf27f3fab33a82524db1d&oe=5E9401A9","3"));
        itemList.add(new feedClass("Another feed Class","It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)","","4"));
        itemList.add(new feedClass("Test feed","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.","https://scontent.fdel29-1.fna.fbcdn.net/v/t1.0-9/s960x960/83723262_486448612246622_7145432168599126016_o.jpg?_nc_cat=111&_nc_ohc=kvBafHEbtn4AX_jDKBP&_nc_ht=scontent.fdel29-1.fna&oh=d521de8caffaf27f3fab33a82524db1d&oe=5E9401A9","5"));
        itemList.add(new feedClass("Another feed Class","It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)","","6"));
        itemList.add(new feedClass("Test feed","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.","https://scontent.fdel29-1.fna.fbcdn.net/v/t1.0-9/s960x960/83723262_486448612246622_7145432168599126016_o.jpg?_nc_cat=111&_nc_ohc=kvBafHEbtn4AX_jDKBP&_nc_ht=scontent.fdel29-1.fna&oh=d521de8caffaf27f3fab33a82524db1d&oe=5E9401A9","7"));
        itemList.add(new feedClass("Another feed Class","It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)","","8"));
        Collections.sort(itemList,new SortbyId());
        adapter = new feedAdapter(itemList,getContext());
        recyclerView.setAdapter(adapter);
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