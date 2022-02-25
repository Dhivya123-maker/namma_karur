
package com.e.login.JobSearchClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.e.login.R;

import java.util.ArrayList;
import java.util.List;

public class Job_search_two extends AppCompatActivity {

    List<Job_Search_one_Model> jobSearchOneModelList;
    Job_Search_one_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search_two);

//        jobSearchOneModelList = new ArrayList<>();
//
//        RecyclerView recyclerView =findViewById(R.id.job_one_screen);
//
//
//        for (int i = 0; i < 6; i++) {
//
//            Job_Search_one_Model viewmodel = new Job_Search_one_Model();
//
//
//
//            viewmodel.setImg("1");
//
//            viewmodel.setTxt("AC Shops");
//            viewmodel.setSwitch_button("2");
//
//
//
//            jobSearchOneModelList.add(viewmodel);
//
//        }
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(Job_search_two.this));
//
//        adapter =  new Job_Search_one_Adapter(Job_search_two.this,jobSearchOneModelList);
//        recyclerView.setAdapter(adapter);
    }
}