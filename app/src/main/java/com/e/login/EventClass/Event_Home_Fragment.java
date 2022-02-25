package com.e.login.EventClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.e.login.Facility_Class.Facility_Fragment;
import com.e.login.Gallery_Class.Gallery_Fragment;
import com.e.login.MoreInfoClass.MoreInfo;
import com.e.login.R;
import com.google.android.material.tabs.TabLayout;

public class Event_Home_Fragment extends AppCompatActivity {

    private EventViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPage;
    private TabLayout event_tablayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home_fragment);


        viewPage = findViewById(R.id.viewpager_events);


        viewPagerAdapter = new EventViewPagerAdapter(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new Event_Fragment_Class(), "Home");
        viewPagerAdapter.add(new Gallery_Fragment(), "Gallery");
        viewPagerAdapter.add(new Facility_Fragment(), "Facility");
//


        // Set the adapter
        viewPage.setAdapter(viewPagerAdapter);


        event_tablayout = findViewById(R.id.event_tab_layout);
        event_tablayout.setupWithViewPager(viewPage);


      event_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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