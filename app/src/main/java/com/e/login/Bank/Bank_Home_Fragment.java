package com.e.login.Bank;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.login.BlankFragment.Blank_Adapter;
import com.e.login.BlankFragment.Blank_Model;
import com.e.login.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class Bank_Home_Fragment extends Fragment {
    SliderView sliderView;
    int[] images = {R.drawable.first_one,
            R.drawable.banner,
            R.drawable.bank_banner,
            R.drawable.banner,
            R.drawable.first_one,
    };
    List<Bank_Comments_Model> bankCommentsModelList;
    Bank_Comments_Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_bank__home_, container, false);

        sliderView = root.findViewById(R.id.slider_axis_bank);

        Slider_bank_one_Adapter sliderAdapter = new Slider_bank_one_Adapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();



        bankCommentsModelList = new ArrayList<>();

        RecyclerView recyclerView = root.findViewById(R.id.bank_screen_comments);


        for (int i = 0; i < 4; i++) {

            Bank_Comments_Model viewmodel = new Bank_Comments_Model();



            viewmodel.setImg("1");
            viewmodel.setImg1("2");
            viewmodel.setTxt("Namma Karur User");
            viewmodel.setTxt1("Good");
            viewmodel.setTxt2("4.6");


            bankCommentsModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter =  new Bank_Comments_Adapter(getContext(),bankCommentsModelList);
        recyclerView.setAdapter(adapter);
   //     recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));




        return  root;

    }
}