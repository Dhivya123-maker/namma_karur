package com.e.login.info_Class;



import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.HelperClass.InfoPagerFragmentAdapter;
import com.e.login.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class InformationFragment extends Fragment {
//    private InfoPagerFragmentAdapter infoPagerFragmentAdapter;
//    private ViewPager viewPager;
//    private TabLayout tabLayout;
//


    List<InfoModel> infoModelList;
    InformationAdapter adapter;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_information, container, false);


        infoModelList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.info_fragment);

        for (int i = 0; i < 4; i++) {


            InfoModel viewmodel = new InfoModel();


            viewmodel.setText("SR Air Conditioner");
            viewmodel.setText_one("Senguthapuram, karur.");
            viewmodel.setText_two("5 days ago");
            viewmodel.setText_three("Lorem ispum is simply dummy text of printing");
            viewmodel.setImage("1");
            viewmodel.setImage2("3");
            viewmodel.setImage3("4");
            viewmodel.setImage5("6");
            infoModelList.add(viewmodel);


        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter =  new InformationAdapter(getActivity(),infoModelList);
        recyclerView.setAdapter(adapter);




//        viewPager = root.findViewById(R.id.viewpager);
//
//
////
//
//
//        // setting up the adapter
//        infoPagerFragmentAdapter = new InfoPagerFragmentAdapter(getActivity().getSupportFragmentManager());
//
//        // add the fragments
//        infoPagerFragmentAdapter.add(new InfoShops(), "Shops");
//        infoPagerFragmentAdapter.add(new VaccineInfo(), "Vaccine Info");
//        infoPagerFragmentAdapter.add(new FeverCamp(), "Fever Info");
//        infoPagerFragmentAdapter.add(new PostFragment(), "Shop post");
//        infoPagerFragmentAdapter.add(new Karur(), "karur");
//        // Set the adapter
//        viewPager.setAdapter(infoPagerFragmentAdapter);
//
//
//        // The Page (fragment) titles will be displayed in the
//        // tabLayout hence we need to set the page viewer
//        // we use the setupWithViewPager().
//        tabLayout = root.findViewById(R.id.tab_layout);
//        tabLayout.setupWithViewPager(viewPager);
//
//










        return  root;
    }
}