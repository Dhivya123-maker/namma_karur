package com.e.login;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.e.login.BlankFragment.Blank_PostFragment;
import com.e.login.HelperClass.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Fragment_Shops extends AppCompatActivity
{

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager1;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_shops);





        viewPager1 = findViewById(R.id.viewpager);

//


        // setting up the adapter
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new Blank_PostFragment(), "Shops");
//        viewPagerAdapter.add(new Products_Fragment(), "Service");
//        viewPagerAdapter.add(new Post_Fragment(), "Transport");
//

        // Set the adapter
        viewPager1.setAdapter(viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to set the page viewer
        // we use the setupWithViewPager().
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager1);


    }
}
