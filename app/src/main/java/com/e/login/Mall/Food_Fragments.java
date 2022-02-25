package com.e.login.Mall;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.login.HelperClass.ViewPagerAdapter;
import com.e.login.NewsClass.Karur;
import com.e.login.R;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class Food_Fragments extends Fragment {

    List<Food_Model> foodModelList;
    Food_Adapter adapter;
    SliderView sliderView;
    int[] images = {R.drawable.first_one,
            R.drawable.banner,
            R.drawable.bank_banner,
            R.drawable.banner,
            R.drawable.first_one,
    };
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout_mall;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View root =  inflater.inflate(R.layout.fragment_food__fragments, container, false);

        foodModelList= new ArrayList<>();

    recyclerView =root.findViewById(R.id.food_recycle);


        for (int i = 0; i < 3; i++) {

            Food_Model viewmodel = new Food_Model();


            viewmodel.setImg("1");
            viewmodel.setImg1("2");
            viewmodel.setImg2("3");
            viewmodel.setImg2("4");
            viewmodel.setTxt("4.6");
            viewmodel.setTxt1("Kailsah Parbat");
            viewmodel.setTxt2("LG - 23,brookefield mall,23,Dr.Krishnasamy mudaliyar Rd, Brookefields, R.S.Puram.");
            viewmodel.setTxt3("8.00am - 10.00am");
            viewmodel.setTxt4("Verified");


            foodModelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter =  new Food_Adapter(getContext(),foodModelList);
        recyclerView.setAdapter(adapter);



        viewPager = root.findViewById(R.id.viewpager_mall_food);

        tabLayout_mall = root.findViewById(R.id.tab_layout_mall_food);


        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new Inside_fragment(), "Food");
        viewPagerAdapter.add(new Karur(), "Clothing");
        viewPagerAdapter.add(new Karur(), "Cosmetics");
        viewPagerAdapter.add(new Karur(), "Electronics");


        // Set the adapter
        viewPager.setAdapter(viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to set the page viewer
        // we use the setupWithViewPager().

        tabLayout_mall.setupWithViewPager(viewPager);


        tabLayout_mall.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                if (tab.getPosition() == 0) {
                    recyclerView.setVisibility(View.VISIBLE);

                } else if (tab.getPosition() == 1) {

                    recyclerView.setVisibility(View.GONE);


                } else if (tab.getPosition() == 2) {
                    recyclerView.setVisibility(View.GONE);


                } else if (tab.getPosition() == 3) {
                    recyclerView.setVisibility(View.GONE);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        sliderView = root.findViewById(R.id.slider_toop);
      Slider_mall_Adapter sliderAdapter = new Slider_mall_Adapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


        return  root;


    }

}