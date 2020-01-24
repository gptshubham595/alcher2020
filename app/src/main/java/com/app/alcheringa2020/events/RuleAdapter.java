package com.app.alcheringa2020.events;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.external.AppConstants;
import com.app.alcheringa2020.events.model.JudgeModel;
import com.app.alcheringa2020.events.model.RuleModel;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 07-Jan-20.
 */
public class RuleAdapter extends RecyclerView.Adapter<RuleAdapter.ViewHolder> {
    String TAG = RuleAdapter.class.getSimpleName();

    ArrayList<RuleModel> ruleModelArrayList;
    ArrayList<JudgeModel> judgeModelArrayList;
    Context mContext;
    String type;

    public RuleAdapter(Context context, ArrayList<RuleModel> ruleModelArrayList, ArrayList<JudgeModel> judgeModelArrayList, String type) {
        this.ruleModelArrayList = ruleModelArrayList;
        this.judgeModelArrayList = judgeModelArrayList;
        mContext = context;
        this.type = type;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ruleTxt;
        RecyclerView rv_child;

        public ViewHolder(View v) {
            super(v);
            ruleTxt = (TextView) v.findViewById(R.id.ruleTxt);
        }
    }

    @Override
    public RuleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_rule, parent, false);
        return new RuleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RuleAdapter.ViewHolder holder, int position) {
        if (type.equalsIgnoreCase(AppConstants.RULE)) {
            RuleModel ruleModel = ruleModelArrayList.get(position);
            holder.ruleTxt.setText(Integer.toString(position + 1) + ". " + ruleModel.getRule());
        } else {
            JudgeModel judgeModel = judgeModelArrayList.get(position);
            holder.ruleTxt.setText(Integer.toString(position + 1) + ". " + judgeModel.getJudgeCriteria());
        }
        Typeface typeface = Typeface.createFromAsset(holder.itemView.getContext().getAssets(), "font/exo_regular.ttf");
        holder.ruleTxt.setTypeface(typeface);

    }

    @Override
    public int getItemCount() {
        int arraySize;
        if (type.equalsIgnoreCase(AppConstants.RULE)) {
            arraySize = ruleModelArrayList.size();
        } else {
            arraySize = judgeModelArrayList.size();
        }
        return arraySize;
    }
}
