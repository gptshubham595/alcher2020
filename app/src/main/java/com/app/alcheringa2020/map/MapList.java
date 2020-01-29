package com.app.alcheringa2020.map;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MapList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private mapAdapter adapter;
    private ArrayList<mapClass> itemList;
    private EditText search;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Context context;
        dialog= new ProgressDialog(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Events and Venue");
        search = findViewById(R.id.event_search);
        recyclerView  = findViewById(R.id.mapRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<>();

        adapter = new mapAdapter(itemList,this);
        recyclerView.setAdapter(adapter);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        dialog.setMessage("Loading ..");
        dialog.show();
        firestore.collection("events").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isComplete()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        itemList.add(new mapClass(document.getString("name"),document.getString("venue"),document.getString("date"),"",document.getString("latitude"),document.getString("longitude")));
                    }
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MapList.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

    }
    private void filter(String text) {
        ArrayList<mapClass> filteredList = new ArrayList<>();

        for (mapClass item : itemList) {
            if (item.getEvent().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
