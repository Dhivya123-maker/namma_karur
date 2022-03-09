package com.e.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.HomeClass.Home;
import com.e.login.Verification.VerifyActivity;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;


public class OnBoarding extends AppCompatActivity {


    ViewPager viewPager;
    LinearLayout dotsLayout;
    //    w hav to dfin th slidr adpatr variabls
    SliderAdapter sliderAdapter;
    TextView[] dots;
    ImageView back;
    Button LetsGetStarted,skip,nxt;
    String data,data1;
    String id = null;
    String token = null;
    String goo_id;

//    animations for get started

    Animation animation;

    //    if the user clicks the next screen button it wikll go to the current position
    int currentPosition;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
      // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.onboarding_screen);
//        setTheme(R.style.MyTheme);

        linearLayout =  findViewById(R.id.linear);


        if(PreferenceUtils.getToken(OnBoarding.this) != null){
            Intent intent = new Intent(OnBoarding.this, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);

//            PreferenceUtils.saveid(id, OnBoarding.this);
//            PreferenceUtils.saveToken(token,OnBoarding.this);
//            PreferenceUtils.saveid1(goo_id, OnBoarding.this);
//            PreferenceUtils.saveToken1(token,OnBoarding.this);

            linearLayout.setVisibility(View.INVISIBLE);
        }
        else {

            linearLayout.setVisibility(View.VISIBLE);
            viewPager = findViewById(R.id.slider);
            dotsLayout = findViewById(R.id.dots);
            back = findViewById(R.id.board_back);
//        letsgetstarted button
            LetsGetStarted = findViewById(R.id.get_started_btn);
        skip = findViewById(R.id.skip);
            nxt = findViewById(R.id.nxt_btn);

            back.setVisibility(View.INVISIBLE);

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                Intent intent = new Intent(OnBoarding.this,OnBoarding.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);
//                finishAffinity();
                    viewPager.setCurrentItem(getItem(-1), true);
                }

                private int getItem(int i) {
                    return viewPager.getCurrentItem() + i;
                }


            });

            LetsGetStarted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                        Intent intent = new Intent(OnBoarding.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);



                }
            });

//        call the adapter
            sliderAdapter = new SliderAdapter(this);

            viewPager.setAdapter(sliderAdapter);

            addDots(0);

//          call this function get the chaNGFE IOF THE COLOR
//        create the method and we need to call that listener
            viewPager.addOnPageChangeListener(changeListener);

        }
    }

    //    to acces the skip button
    public  void skip (View view){


        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    public void next(View view){
        viewPager.setCurrentItem(currentPosition + 1);

    }

//    to click letsgetstarted





    //        to create th dots
    private  void addDots (int position){

        dots = new  TextView[4];
        dotsLayout.removeAllViews();

        for(int i =0;i<dots.length;i++)
        {         dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.argb(255,128,128,128));

////            add th dots on our layout
            dotsLayout.addView(dots[i]);
        }
        if(dots.length > 0){

            dots[position].setTextColor(getResources().getColor(R.color.purple_500));
        }


    }

//    to chang th dots color we hav to implment these stps

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPosition = position;



//            we want to hide the specific button
            if(position == 0){
                back.setVisibility(View.INVISIBLE);
                LetsGetStarted.setVisibility(View.INVISIBLE);
                dotsLayout.setVisibility(View.VISIBLE);
//                skip.setVisibility(View.VISIBLE);
                nxt.setVisibility(View.VISIBLE);

            }else if(position == 1){

                LetsGetStarted.setVisibility(View.INVISIBLE);
                back.setVisibility(View.VISIBLE);
                dotsLayout.setVisibility(View.VISIBLE);
//                skip.setVisibility(View.VISIBLE);
                nxt.setVisibility(View.VISIBLE);



            }
            else if(position == 2){
                LetsGetStarted.setVisibility(View.INVISIBLE);
                back.setVisibility(View.VISIBLE);
                dotsLayout.setVisibility(View.VISIBLE);
//                skip.setVisibility(View.VISIBLE);
                nxt.setVisibility(View.VISIBLE);



            }
            else {
                LetsGetStarted.setVisibility(View.VISIBLE);
                dotsLayout.setVisibility(View.INVISIBLE);
//                skip.setVisibility(View.INVISIBLE);
                nxt.setVisibility(View.INVISIBLE);
                back.setVisibility(View.VISIBLE);


//                animation = AnimationUtils.loadAnimation(OnBoarding.this,R.anim.bottom_anim);
//                LetsGetStarted.setAnimation(animation);

            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };






    }

