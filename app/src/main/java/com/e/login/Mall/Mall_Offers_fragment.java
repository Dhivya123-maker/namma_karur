package com.e.login.Mall;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.login.MoreInfoClass.More_Info_Adapter;
import com.e.login.MoreInfoClass.More_Info_Model;
import com.e.login.R;

import java.util.ArrayList;
import java.util.List;


public class Mall_Offers_fragment extends Fragment {

    List<Mall_Offer_Model> mallOfferModelList;
    Mall_Offer_Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View root =  inflater.inflate(R.layout.fragment_mall__offers_fragment, container, false);


        mallOfferModelList = new ArrayList<>();

        RecyclerView recyclerView = root.findViewById(R.id.offers_mall_screen);


        for (int i = 0; i < 4; i++) {

            Mall_Offer_Model viewmodel = new Mall_Offer_Model();



            viewmodel.setImg("1");
            viewmodel.setImg("2");
            viewmodel.setTxt("Air Conditioners special offer");




            mallOfferModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter =  new Mall_Offer_Adapter(getActivity(),mallOfferModelList);
        recyclerView.setAdapter(adapter);




        return  root;
    }
}