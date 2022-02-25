package com.e.login.MoreInfoClass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;

import java.util.ArrayList;
import java.util.List;

public class MoreInfo extends Fragment {

    List<More_Info_Model> moreInfoModelList;
    More_Info_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_more_info, container, false);

        moreInfoModelList = new ArrayList<>();

        RecyclerView recyclerView = root.findViewById(R.id.offers_home_screen);


        for (int i = 0; i < 4; i++) {

            More_Info_Model viewmodel = new More_Info_Model();



            viewmodel.setImg("1");
            viewmodel.setImg("2");
            viewmodel.setTxt("Air Conditioners special offer");




            moreInfoModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter =  new More_Info_Adapter(getActivity(),moreInfoModelList);
        recyclerView.setAdapter(adapter);


        return root;
    }
}