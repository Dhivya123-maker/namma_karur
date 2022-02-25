package com.e.login.JobSearchClass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.login.R;

import java.util.ArrayList;
import java.util.List;


public class Job_Fragment extends Fragment {

    List<Job_Search_Model> jobSearchModelList;
    Job_Search_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_job_, container, false);


        jobSearchModelList = new ArrayList<>();

        RecyclerView recyclerView =root.findViewById(R.id.job_screen);


        for (int i = 0; i < 6; i++) {

            Job_Search_Model viewmodel = new Job_Search_Model();



            viewmodel.setImg("1");

            viewmodel.setTxt("Marketing Executive Wanted in Karur");
            viewmodel.setTxt1("Karur Job updated");


            jobSearchModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter =  new Job_Search_Adapter(getContext(),jobSearchModelList);
        recyclerView.setAdapter(adapter);




        return  root;
    }
}