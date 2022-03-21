package com.e.login.Verification;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.WindowManager;

import com.e.login.R;

public class Edit extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);


    }
}
