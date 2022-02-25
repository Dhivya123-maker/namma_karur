package com.e.login.Facility_Class;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.Gallery_Class.Gallery_Adapter;
import com.e.login.Gallery_Class.Gallery_Model;
import com.e.login.R;

import java.util.ArrayList;
import java.util.List;


public class Facility_Fragment extends Fragment {


    List<Facility_Model> facilityModelList;
    Facility_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_facility_, container, false);


        facilityModelList = new ArrayList<>();

        RecyclerView recyclerView = root.findViewById(R.id.facility_recycle);



        for (int i = 0; i < 4; i++) {

            Facility_Model viewmodel = new Facility_Model();


            viewmodel.setImg("1");
            viewmodel.setTxt("Wedding Reception Hall");
            viewmodel.setTxt1("Lorem ispum may be used as a placeholder before the lorem ispum");

            facilityModelList.add(viewmodel);


        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter =  new Facility_Adapter(getContext(),facilityModelList);
        recyclerView.setAdapter(adapter);


        return  root;
    }
}