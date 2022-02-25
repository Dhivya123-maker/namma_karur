package com.e.login.Mall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.e.login.HelperClass.ViewPagerAdapter;
import com.e.login.R;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class Malls_One extends AppCompatActivity {

    List<Mall_One_Model> mall_one_modelList;
    Mall_One_Adapter adapter;

    List<Mall_two_Model> mall_two_modelList;
    Mall_Two_Adapter adapter5;

    int[] images = {R.drawable.first_one,
            R.drawable.banner,
            R.drawable.bank_banner,
            R.drawable.banner,
            R.drawable.first_one,
    };
    SliderView sliderView;
    private ViewPagerAdapter viewPagerAdapter,viewPagerAdapter1;
    private ViewPager viewPager,viewpager1;
    private TabLayout tabLayout_mall,tabLayout_mall1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malls_one);

        viewPager = findViewById(R.id.viewpage_mall);
       // viewpager1 = findViewById(R.id.viewpager_mall_food);

        tabLayout_mall = findViewById(R.id.tab_layoutt_mall);
       // tabLayout_mall1 = findViewById(R.id.tab_layout_mall_food);




//        sliderView = findViewById(R.id.slider_toop);
//      Slider_mall_Adapter sliderAdapter = new Slider_mall_Adapter(images);
//
//        sliderView.setSliderAdapter(sliderAdapter);
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
//        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
//        sliderView.startAutoCycle();

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new Food_Fragments(), "Home");
        viewPagerAdapter.add(new Mall_Offers_fragment(), "offers");
//        viewPagerAdapter1.add(new Karur(), "Cosmetics");
//        viewPagerAdapter1.add(new Karur(), "Electronics");



        viewPager.setAdapter(viewPagerAdapter);


        tabLayout_mall.setupWithViewPager(viewPager);


        tabLayout_mall.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                if (tab.getPosition() == 0) {

//                    floatingActionButton.setVisibility(View.VISIBLE);
                } else if (tab.getPosition() == 1) {

//                    floatingActionButton.setVisibility(View.GONE);

                } else if (tab.getPosition() == 2) {

//                    floatingActionButton.setVisibility(View.GONE);

                } else if (tab.getPosition() == 3) {

//                    floatingActionButton.setVisibility(View.GONE);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });








//        viewPagerAdapter1 = new ViewPagerAdapter(getSupportFragmentManager());
//
//        // add the fragments
//        viewPagerAdapter1.add(new Inside_fragment(), "Food");
//        viewPagerAdapter1.add(new Karur(), "Clothing");
//        viewPagerAdapter1.add(new Karur(), "Cosmetics");
//        viewPagerAdapter1.add(new Karur(), "Electronics");
//
//
//        // Set the adapter
//        viewpager1.setAdapter(viewPagerAdapter1);
//
//        // The Page (fragment) titles will be displayed in the
//        // tabLayout hence we need to set the page viewer
//        // we use the setupWithViewPager().
//
//        tabLayout_mall1.setupWithViewPager(viewpager1);
//
//
//        tabLayout_mall1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//
//                if (tab.getPosition() == 0) {
//
////                    floatingActionButton.setVisibility(View.VISIBLE);
//                } else if (tab.getPosition() == 1) {
//
////                    floatingActionButton.setVisibility(View.GONE);
//
//                } else if (tab.getPosition() == 2) {
//
////                    floatingActionButton.setVisibility(View.GONE);
//
//                } else if (tab.getPosition() == 3) {
//
////                    floatingActionButton.setVisibility(View.GONE);
//
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

//
//        ArrayList<Slider_Top_Model> sliderTopModelArrayList= new ArrayList<>();
//        slider = findViewById(R.id.slider_topp);
//
//
//        Slider_Top_Model sd = new Slider_Top_Model(R.drawable.axis_banner);
//
//        sd.setImage("https://media.istockphoto.com/photos/blue-christmas-and-new-year-holiday-frame-picture-id1336074248?b=1&k=20&m=1336074248&s=170667a&w=0&h=YV0m1tzqq2F-DJhoX-W_FBy8HFTBojptjbfzLkQcLF0=");
//        sliderTopModelArrayList.add(sd);
//
//
//        Slider_Top_Adapter adapter1 = new Slider_Top_Adapter(Malls_One.this, sliderTopModelArrayList);
//        slider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
//        slider.setSliderAdapter(adapter1);
//


//
//        mall_one_modelList = new ArrayList<>();
//
//        RecyclerView recyclerView =findViewById(R.id.malls_screen);
//
//
//        for (int i = 0; i < 3; i++) {
//
//            Mall_One_Model viewmodel = new Mall_One_Model();
//
//
//
//            viewmodel.setImage("1");
//            viewmodel.setText("Food");
//
//            mall_one_modelList.add(viewmodel);
//
//        }
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(Malls_One.this));
//
//        adapter =  new Mall_One_Adapter(Malls_One.this,mall_one_modelList);
//        recyclerView.setAdapter(adapter);
//
//
//
//
//        mall_two_modelList = new ArrayList<>();
//
//        RecyclerView recyclerView1 =findViewById(R.id.shops_scroll);
//
//
//        for (int i = 0; i < 5; i++) {
//
//            Mall_two_Model viewmodel = new Mall_two_Model();
//
//
//
//            viewmodel.setImage("1");
//
//            mall_two_modelList.add(viewmodel);
//
//        }
//
//        recyclerView1.setLayoutManager(new LinearLayoutManager(Malls_One.this));
//
//        adapter5 =  new Mall_Two_Adapter(Malls_One.this,mall_two_modelList);
//        recyclerView1.setAdapter(adapter5);
//        recyclerView1.setLayoutManager(new LinearLayoutManager(Malls_One.this, LinearLayoutManager.HORIZONTAL, false));
//
//





    }
}