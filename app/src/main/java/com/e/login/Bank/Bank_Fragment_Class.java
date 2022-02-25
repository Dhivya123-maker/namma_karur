package com.e.login.Bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.e.login.MoreInfoClass.MoreInfo;
import com.e.login.R;
import com.google.android.material.tabs.TabLayout;

public class Bank_Fragment_Class extends AppCompatActivity {

    private Bank_ViewPager_Adapter viewPagerAdapter;
    private ViewPager viewPagerr;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_fragment_class);

        viewPagerr = findViewById(R.id.viewpager_bank);


        viewPagerAdapter = new Bank_ViewPager_Adapter(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new Bank_Home_Fragment(), "Home");
//        viewPagerAdapter.add(new ShopPost(), "Posts");
        viewPagerAdapter.add(new Bank_Home_Fragment(), "More info");
//


        // Set the adapter
        viewPagerr.setAdapter(viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to set the page viewer
        // we use the setupWithViewPager().
        tabLayout = findViewById(R.id.bank_tab_layout);
        tabLayout.setupWithViewPager(viewPagerr);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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