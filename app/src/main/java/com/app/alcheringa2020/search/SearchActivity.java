package com.app.alcheringa2020.search;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.base.BaseActivity;

/**
 * Created by Jiaur Rahman on 09-Jan-20.
 */
public class SearchActivity extends BaseActivity {
    String TAG = SearchActivity.class.getSimpleName();
    ImageView toolbarBack;
    EditText searchEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        showInputMethod();
        toolbarBack = findViewById(R.id.toolbarBack);
        searchEdit = findViewById(R.id.searchEdit);
        initListner();
    }

    private void initListner() {
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideInputMethod();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideInputMethod();
        finish();
    }
    public void showInputMethod() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void hideInputMethod() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}
