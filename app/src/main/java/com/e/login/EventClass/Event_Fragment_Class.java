package com.e.login.EventClass;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.e.login.BlankFragment.Blank_Adapter;
import com.e.login.BlankFragment.Blank_Model;

import com.e.login.Offers.Top_offer_Adapter;
import com.e.login.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class Event_Fragment_Class extends Fragment {
    int[] images = {R.drawable.first_one,
            R.drawable.banner,
            R.drawable.bank_banner,
            R.drawable.banner,
            R.drawable.first_one,
    };
    SliderView sliderView;
    ImageView gallery;
    List<Events_Comments_Model> eventsCommentsModelList;
   Event_Comments_Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View root = inflater.inflate(R.layout.fragment_event___class, container, false);


        sliderView = root.findViewById(R.id.slider_event);
       Slider_event_adapter sliderAdapter = new Slider_event_adapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        gallery = root.findViewById(R.id.gallery_img);



        eventsCommentsModelList= new ArrayList<>();

        RecyclerView recyclerView = root.findViewById(R.id.event_recycle_screen);


        for (int i = 0; i < 4; i++) {

            Events_Comments_Model viewmodel = new Events_Comments_Model();



            viewmodel.setImg("1");
            viewmodel.setImg1("2");
            viewmodel.setTxt("Namma Karur User");
            viewmodel.setTxt1("Good");
            viewmodel.setTxt2("4.6");


            eventsCommentsModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter =  new Event_Comments_Adapter(getContext(),eventsCommentsModelList);
        recyclerView.setAdapter(adapter);






        return root;
    }
}