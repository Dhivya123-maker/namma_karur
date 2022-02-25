package com.e.login.Mall;



import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.Bank.Bank_Comments_Adapter;
import com.e.login.Bank.Bank_Comments_Model;
import com.e.login.HomeClass.Slider_Top_Adapter;
import com.e.login.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class Mall_fragment_class extends Fragment {
    int[] images = {R.drawable.first_one,
            R.drawable.banner,
            R.drawable.bank_banner,
            R.drawable.banner,
            R.drawable.first_one,
    };
    SliderView sliderView;

    List<Mall_Comments_Model> mallCommentsModelList;
    Mall_Comments_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_mall___class, container, false);
        sliderView = root.findViewById(R.id.slider_mall_two);

        Slider_mall_one_Adapter sliderAdapter = new Slider_mall_one_Adapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        mallCommentsModelList = new ArrayList<>();

        RecyclerView recyclerView = root.findViewById(R.id.comments_mall);


        for (int i = 0; i < 4; i++) {

            Mall_Comments_Model viewmodel = new Mall_Comments_Model();



            viewmodel.setImg("1");
            viewmodel.setImg1("2");
            viewmodel.setTxt("Namma Karur User");
            viewmodel.setTxt1("Good");


            viewmodel.setTxt2("4.6");


            mallCommentsModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter =  new Mall_Comments_Adapter(getContext(),mallCommentsModelList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));




        return root;

    }
}