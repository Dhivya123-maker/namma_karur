package com.e.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class JoinwithUs extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinwith_us);

        linearLayout = findViewById(R.id.final_lnr);

        linearLayout.setVisibility(View.INVISIBLE);
    }
}