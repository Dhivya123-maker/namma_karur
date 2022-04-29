package com.e.login.Bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.e.login.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class Bank_One extends AppCompatActivity {
    List<Bank_One_Model> bank_one_modelList;
    Bank_One_Adapter adapter;
    SliderView sliderView;
    int[] images = {R.drawable.first_one,
            R.drawable.banner,
            R.drawable.bank_banner,
            R.drawable.banner,
            R.drawable.first_one,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_one);

        sliderView = findViewById(R.id.slider_axis);

        Slider_bank_Adapter sliderAdapter = new Slider_bank_Adapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


        bank_one_modelList = new ArrayList<>();

        RecyclerView recyclerView =findViewById(R.id.axis_bank_screen);


        for (int i = 0; i < 3; i++) {

            Bank_One_Model viewmodel = new Bank_One_Model();



            viewmodel.setImage("1");
            viewmodel.setImage1("2");
            viewmodel.setImage2("3");
            viewmodel.setImage3("4");

            viewmodel.setText("Axis Bank - Karur");
            viewmodel.setText1("No.421, Vaiyapuri nagar,1st cross,\nNear canara bank,karur-639 002");
            viewmodel.setText2("8.00am - 10.00am");
            viewmodel.setText3("Verified");
            viewmodel.setText4("4.6");

            bank_one_modelList.add(viewmodel);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(Bank_One.this));

        adapter =  new Bank_One_Adapter(Bank_One.this,bank_one_modelList);
        recyclerView.setAdapter(adapter);

    }
}