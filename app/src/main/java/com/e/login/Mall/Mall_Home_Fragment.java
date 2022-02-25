package com.e.login.Mall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.e.login.MoreInfoClass.MoreInfo;
import com.e.login.R;
import com.google.android.material.tabs.TabLayout;

public class Mall_Home_Fragment extends AppCompatActivity {


    private MallViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPage;
    private TabLayout mall_tablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_home_fragment);


        viewPage = findViewById(R.id.viewpager_mall);


        viewPagerAdapter = new MallViewPagerAdapter(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new Mall_fragment_class(), "Home");
        viewPagerAdapter.add(new Mall_fragment_class(), "Variety");
        viewPagerAdapter.add(new Mall_fragment_class(), "Posts");
        viewPagerAdapter.add(new Mall_fragment_class(), "More Info");
//


        // Set the adapter
        viewPage.setAdapter(viewPagerAdapter);


        mall_tablayout = findViewById(R.id.mall_tab_layout);
        mall_tablayout.setupWithViewPager(viewPage);


        mall_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                if (tab.getPosition()==0){


                }else if (tab.getPosition()==1){


                }
                else if (tab.getPosition()==2){



                }
                else if (tab.getPosition()==3){



                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }





    }
