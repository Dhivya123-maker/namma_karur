package com.e.login.TransportClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.e.login.BlankFragment.Blank_PostFragment;
import com.e.login.HelperClass.ViewPagerAdapter;
import com.e.login.MoreInfoClass.MoreInfo;
import com.e.login.ProductsFragmentClass.Products_Fragment;
import com.e.login.R;
import com.e.login.ShoppostClass.ShopPost;
import com.google.android.material.tabs.TabLayout;

public class TransportFragment extends AppCompatActivity {

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_fragment);

        viewPager = findViewById(R.id.viewpager_trans);

        // setting up the adapter
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new Fragment_Transport(), "Home");
        viewPagerAdapter.add(new Transport_services_fragment(), "Transports");
        viewPagerAdapter.add(new ShopPost(), "Flash");
        viewPagerAdapter.add(new MoreInfo(), "Offers");


        // Set the adapter
        viewPager.setAdapter(viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to set the page viewer
        // we use the setupWithViewPager().
        tabLayout = findViewById(R.id.tab_layout_trans);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                if (tab.getPosition() == 0) {


                } else if (tab.getPosition() == 1) {



                } else if (tab.getPosition() == 2) {


                } else if (tab.getPosition() == 3) {


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