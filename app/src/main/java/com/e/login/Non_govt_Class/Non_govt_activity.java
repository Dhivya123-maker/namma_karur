package com.e.login.Non_govt_Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.e.login.GovtClass.GovtActivity;
import com.e.login.GovtClass.GovtAdapter;
import com.e.login.GovtClass.GovtModel;
import com.e.login.R;

import java.util.ArrayList;
import java.util.List;

public class Non_govt_activity extends AppCompatActivity {

    List<Non_govt_model> nonGovtModelList;
    Non_govt_Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_govt);

        nonGovtModelList = new ArrayList<>();

        RecyclerView recyclerView =findViewById(R.id.NGOO_screen);


        for (int i = 0; i < 3; i++) {

            Non_govt_model viewmodel = new Non_govt_model();


            viewmodel.setText("ARPE");
            viewmodel.setImage("1");


            nonGovtModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(Non_govt_activity.this));

        adapter =  new Non_govt_Adapter(Non_govt_activity.this,nonGovtModelList);
        recyclerView.setAdapter(adapter);
    }
}