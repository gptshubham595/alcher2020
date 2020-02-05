package com.app.alcheringa2020.events;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.MainActivity;
import com.app.alcheringa2020.R;
import com.app.alcheringa2020.authentication.SharedPrefManager;
import com.app.alcheringa2020.base.BaseActivity;
import com.app.alcheringa2020.events.model.ItemModel;
import com.app.alcheringa2020.events.model.JudgeModel;
import com.app.alcheringa2020.events.model.ProgrammeModel;
import com.app.alcheringa2020.events.model.RuleModel;
import com.app.alcheringa2020.external.AppConstants;

import java.util.ArrayList;

import static com.app.alcheringa2020.events.EventsDataModel.programmeModelArrayList;

/**
 * Created by Jiaur Rahman on 04-Jan-20.
 */
public class EventActivity extends BaseActivity implements EventListner {
    public static TextView text_title, competitionTxt, bountyTxt, descriptionTxt, prelimsDescription, finalsDescription;
    String TAG = EventActivity.class.getSimpleName();
    int programmeID;
    String categoryName, eventName, compname, descname, bountyname;
    RecyclerView event_data, ruleRecycler, judgeRecycler;
    LinearLayout ruleLyt, prelimsLyt, finalsLyt, judgeLyt;
    int eventId;
    ArrayList<ItemModel> itemModelArrayList;
    RuleAdapter ruleAdapter;
    LinearLayout registerLyt;
    Button btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        initView();
        initData();
        initListner();

    }

    private void initView() {
        registerLyt = findViewById(R.id.registerLyt);
        text_title = findViewById(R.id.text_title);
        competitionTxt = findViewById(R.id.competitionTxt);
        event_data = findViewById(R.id.event_data);
        ruleRecycler = findViewById(R.id.ruleRecycler);
        bountyTxt = findViewById(R.id.bountyTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        prelimsDescription = findViewById(R.id.prelimsDescription);
        finalsDescription = findViewById(R.id.finalsDescription);
        judgeRecycler = findViewById(R.id.judgeRecycler);
        ruleLyt = findViewById(R.id.ruleLyt);
        prelimsLyt = findViewById(R.id.prelimsLyt);
        finalsLyt = findViewById(R.id.finalsLyt);
        judgeLyt = findViewById(R.id.judgeLyt);

        event_data.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        event_data.setItemAnimator(new DefaultItemAnimator());

        ruleRecycler.setLayoutManager(new LinearLayoutManager(context));
        ruleRecycler.setItemAnimator(new DefaultItemAnimator());

        judgeRecycler.setLayoutManager(new LinearLayoutManager(context));
        judgeRecycler.setItemAnimator(new DefaultItemAnimator());

    }

    private void initData() {
        try {
            Intent intent = getIntent();
            programmeID = intent.getIntExtra(AppConstants.PRO_ID, 0);

            //TOP CLASSAPRT
            categoryName = intent.getStringExtra(AppConstants.PRO_CATEGORY);

            //RockMusic
            eventName = intent.getStringExtra(AppConstants.PRO_EVENT);

            descname = intent.getStringExtra("DESC");
            compname = intent.getStringExtra("COMP");
            bountyname = intent.getStringExtra("BOUNTY");
            //CA , ROP
            Log.d(TAG, "pager id: " + programmeID + " " + categoryName + " " + eventName);
            //
            text_title.setText(compname);

            toolbar(categoryName);
            itemModelArrayList = new ArrayList<>();
            for (int i = 0; i < programmeModelArrayList(context).size(); i++) {
                ProgrammeModel programmeModel = programmeModelArrayList(context).get(i);

                if (programmeModel.getProId() == programmeID) {
                    itemModelArrayList = programmeModel.getItemModelArrayList();
                    for (int j = 0; j < itemModelArrayList.size(); j++) {
                        eventId = itemModelArrayList.get(j).getItemId();

                    }
                    break;
                }
            }

            Log.d(TAG, "item data: " + itemModelArrayList.size());
            EventDetailAdapter itemAdapter = new EventDetailAdapter(context, itemModelArrayList, this);
            event_data.setAdapter(itemAdapter);
            viewData(eventId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initListner() {
        try {
            ruleLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ruleRecycler.setVisibility(View.VISIBLE);
                    prelimsDescription.setVisibility(View.GONE);
                    finalsDescription.setVisibility(View.GONE);
                    judgeRecycler.setVisibility(View.GONE);
                }
            });
            prelimsLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ruleRecycler.setVisibility(View.GONE);
                    prelimsDescription.setVisibility(View.VISIBLE);
                    finalsDescription.setVisibility(View.GONE);
                    judgeRecycler.setVisibility(View.GONE);
                }
            });

            finalsLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ruleRecycler.setVisibility(View.GONE);
                    prelimsDescription.setVisibility(View.GONE);
                    finalsDescription.setVisibility(View.VISIBLE);
                    judgeRecycler.setVisibility(View.GONE);
                }
            });
            judgeLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ruleRecycler.setVisibility(View.GONE);
                    prelimsDescription.setVisibility(View.GONE);
                    finalsDescription.setVisibility(View.GONE);
                    judgeRecycler.setVisibility(View.VISIBLE);
                }
            });
            registerLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onitemClicked(int position) {
        Log.d(TAG, "kkghk: " + position);
        eventId = position;
        viewData(position);
    }

    private void viewData(int event_id) {
        try {
            for (int i = 0; i < itemModelArrayList.size(); i++) {
                ItemModel itemModel = itemModelArrayList.get(i);
                Log.d("ITEMMODELARRAY", "" + itemModel.getItemCompetition());

                if (event_id == itemModel.getItemId()) {

//                    competitionTxt.setText(itemModel.getItemCompetition());
//                    bountyTxt.setText("Bounties - " + itemModel.getItemBounty());
//                    descriptionTxt.setText(itemModel.getItemDescription());

                    competitionTxt.setText(eventName);
                    bountyTxt.setText("Bounties - " + bountyname);
                    descriptionTxt.setText(descname);

                    prelimsDescription.setText(Html.fromHtml("<h2>" + itemModel.getPre_header() + "</h2>") + " " + itemModel.getPre_theme());
                    finalsDescription.setText(Html.fromHtml("<h2>" + itemModel.getFinal_header() + "</h2>") + " " + itemModel.getFinal_theme());
                    ArrayList<RuleModel> ruleModelArrayList = new ArrayList<>();
                    ArrayList<JudgeModel> judgeModelArrayList = new ArrayList<>();

                    for (int j = 0; j < itemModel.getRuleModelArrayList().size(); j++) {
                        RuleModel ruleModel = itemModel.getRuleModelArrayList().get(j);
                        ruleModelArrayList.add(new RuleModel(ruleModel.getRuleId(), ruleModel.getRule()));
                    }
                    ruleAdapter = new RuleAdapter(context, ruleModelArrayList, judgeModelArrayList, AppConstants.RULE);
                    ruleRecycler.setAdapter(ruleAdapter);


                    for (int j = 0; j < itemModel.getJudgeModelArrayList().size(); j++) {
                        JudgeModel judgeModel = itemModel.getJudgeModelArrayList().get(j);
                        judgeModelArrayList.add(new JudgeModel(judgeModel.getJudgeId(), judgeModel.getJudgeCriteria()));
                    }
                    ruleAdapter = new RuleAdapter(context, ruleModelArrayList, judgeModelArrayList, AppConstants.JUDGE);
                    judgeRecycler.setAdapter(ruleAdapter);


                    break;
                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        exitApp();
    }

    private void exitApp() {
//        Toast.makeText(this, "BACK", Toast.LENGTH_SHORT).show();
        try {
            SharedPrefManager.getInstance(this).fragmentwhere("events");
        } catch (Exception e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, MainActivity.class));
//        Toast.makeText(this, ""+SharedPrefManager.getInstance(this).getFragmentName(), Toast.LENGTH_SHORT).show();
    }

    public void backOption(View view) {
        exitApp();
    }

}
