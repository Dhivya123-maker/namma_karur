package com.e.login.Offers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.e.login.HomeClass.Slider_Top_Adapter;
import com.e.login.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class Top_Offer_Activity extends AppCompatActivity {

    int[] images = {R.drawable.first_one,
            R.drawable.banner,
            R.drawable.bank_banner,
            R.drawable.banner,
            R.drawable.first_one,
    };
    SliderView sliderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_offer);

//        sliderView = findViewById(R.id.slider_lenskart);
//        Top_offer_Adapter sliderAdapter = new Top_offer_Adapter(images);
//
//        sliderView.setSliderAdapter(sliderAdapter);
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
//        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
//        sliderView.startAutoCycle();

    }
}