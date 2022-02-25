package com.e.login.Blog_Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.e.login.HelperClass.ViewPagerAdapter;
import com.e.login.NewsClass.Karur;
import com.e.login.R;
import com.google.android.material.tabs.TabLayout;

public class BlogActivity extends AppCompatActivity {

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_fragment);

        viewPager = findViewById(R.id.viewpager_blog);

        // setting up the adapter
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new Blog_Fragment(), "Home");
        viewPagerAdapter.add(new Karur(), "Flash");
        viewPagerAdapter.add(new Karur(), "Videos");


        // Set the adapter
        viewPager.setAdapter(viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to set the page viewer
        // we use the setupWithViewPager().
        tabLayout = findViewById(R.id.tab_layout_blog);
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