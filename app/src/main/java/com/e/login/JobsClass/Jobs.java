package com.e.login.JobsClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.e.login.Add;
import com.e.login.GovtClass.GovtActivity;
import com.e.login.GovtClass.GovtAdapter;
import com.e.login.GovtClass.GovtModel;
import com.e.login.R;

import java.util.ArrayList;
import java.util.List;

public class Jobs extends AppCompatActivity {


    Button add;
    List<Jobs_Model> jobsModelList;
    Jobs_Adapter adapter;
    List<Jobs_One_Model> jobsOneModelList;
    Jobs_One_Adapter adapter1;
    List<Jobs_two_Model> jobsTwoModelList;
    Jobs_two_Adapter adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);


        jobsModelList = new ArrayList<>();

        RecyclerView recyclerView =findViewById(R.id.category_job_screen);


        for (int i = 0; i < 4; i++) {

            Jobs_Model viewmodel = new Jobs_Model();


            viewmodel.setTxt("Central govt\njobs");
            viewmodel.setImg("1");


            jobsModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(Jobs.this));

        adapter =  new Jobs_Adapter(Jobs.this,jobsModelList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Jobs.this, LinearLayoutManager.HORIZONTAL, false));




        jobsOneModelList = new ArrayList<>();

        RecyclerView recyclerView1 =findViewById(R.id.category_job1_screen);


        for (int i = 0; i < 4; i++) {

            Jobs_One_Model viewmodel = new Jobs_One_Model();


          viewmodel.setImg("1");
            viewmodel.setImg1("2");
            viewmodel.setImg2("3");
            viewmodel.setTxt("Marketing Executice Wanted");
            viewmodel.setTxt1("Inevitabletech, Spk Systems");
            viewmodel.setTxt2("Karur");
            viewmodel.setTxt3("Vacancy");
            viewmodel.setTxt4("3 days left");


            jobsOneModelList.add(viewmodel);

        }

        recyclerView1.setLayoutManager(new LinearLayoutManager(Jobs.this));

        adapter1 =  new Jobs_One_Adapter(Jobs.this,jobsOneModelList);
        recyclerView1.setAdapter(adapter1);





        jobsTwoModelList = new ArrayList<>();

        RecyclerView recyclerView2 =findViewById(R.id.category_job2_screen);


        for (int i = 0; i < 4; i++) {

            Jobs_two_Model viewmodel = new Jobs_two_Model();


            viewmodel.setImg("1");
            viewmodel.setImg1("2");
            viewmodel.setImg2("3");
            viewmodel.setTxt("Marketing Executice Wanted");
            viewmodel.setTxt1("Inevitabletech, Spk Systems");
            viewmodel.setTxt2("Karur");
            viewmodel.setTxt3("Vacancy");
            viewmodel.setTxt4("3 days left");


            jobsTwoModelList.add(viewmodel);

        }

        recyclerView2.setLayoutManager(new LinearLayoutManager(Jobs.this));

        adapter2 =  new Jobs_two_Adapter(Jobs.this,jobsTwoModelList);
        recyclerView2.setAdapter(adapter2);

        add = findViewById(R.id.add_btn);
//
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Jobs.this, Add.class);
                startActivity(intent);
            }
        });

//
//        market.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Jobs.this,ViewActivity.class);
//                startActivity(intent);
//
//            }
//        });
    }
}