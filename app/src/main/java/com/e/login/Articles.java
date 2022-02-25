package com.e.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.e.login.FoodClass.FoodActivity;
import com.e.login.fragment_dialog.BottomSheetFragment_filter;

public class Articles extends AppCompatActivity {

    LinearLayout food;
    LinearLayout filter;
    public static final String TAG = "bottom_sheet";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        filter = findViewById(R.id.articles_filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.e.login.fragment_dialog.BottomSheetFragment_filter fragment = new BottomSheetFragment_filter();
                fragment.show(getSupportFragmentManager(), TAG);
            }
        });

        food = findViewById(R.id.food);

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shopsIntent = new Intent(Articles.this, FoodActivity.class);
                startActivity(shopsIntent);
            }
        });
    }
}