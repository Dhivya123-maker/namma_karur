package com.e.login.Non_govt_Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.e.login.R;

import java.util.ArrayList;
import java.util.List;

public class NGO_two extends AppCompatActivity {

    List<NGO_two_Model> ngoTwoModelList;
    NGO_two_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_two);

        ngoTwoModelList = new ArrayList<>();

        RecyclerView recyclerView =findViewById(R.id.ngo_ngo);


        for (int i = 0; i < 2; i++) {

            NGO_two_Model viewmodel = new NGO_two_Model();


            viewmodel.setText("ARPE");
            viewmodel.setImage("1");

            viewmodel.setText_one("Main road, Velliyanai - Via,Karur");



            ngoTwoModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(NGO_two.this));

        adapter =  new NGO_two_Adapter(NGO_two.this,ngoTwoModelList);
        recyclerView.setAdapter(adapter);

    }
}