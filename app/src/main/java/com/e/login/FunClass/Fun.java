package com.e.login.FunClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.e.login.BusTimeClass.BusTimeAdapter;
import com.e.login.BusTimeClass.BusTimeModel;
import com.e.login.BusTimeClass.Bus_TimeActivity;
import com.e.login.R;
import com.e.login.fragment_dialog.BottomSheetFragment_filter;

import java.util.ArrayList;
import java.util.List;

public class Fun extends AppCompatActivity {
    List<FunModel> funModelList;
    FunAdapter adapter;
    LinearLayout filter;
    public static final String TAG = "bottom_sheet";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun);

        filter = findViewById(R.id.fun_filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.e.login.fragment_dialog.BottomSheetFragment_filter fragment = new BottomSheetFragment_filter();
                fragment.show(getSupportFragmentManager(), TAG);
            }
        });


        funModelList= new ArrayList<>();

        RecyclerView recyclerView =findViewById(R.id.fun_screen);


        for (int i = 0; i < 3; i++) {

            FunModel viewmodel = new FunModel();



            viewmodel.setImage("1");
            viewmodel.setText("Articles");

          funModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(Fun.this));

        adapter =  new FunAdapter(Fun.this,funModelList);
        recyclerView.setAdapter(adapter);


//
//        articles = findViewById(R.id.articles);
//       articles.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shopsIntent = new Intent(Fun.this,Articles.class);
//                startActivity(shopsIntent);
//            }
//        });
    }
}