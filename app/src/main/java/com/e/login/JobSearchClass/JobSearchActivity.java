package com.e.login.JobSearchClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.e.login.HelperClass.ViewPagerAdapter;
import com.e.login.NewsClass.Karur;
import com.e.login.R;
import com.google.android.material.tabs.TabLayout;

public class JobSearchActivity extends AppCompatActivity {
    LinearLayout settings;

    private ViewPagerAdapter viewPagerAdapter_job;
    private ViewPager viewPager_job;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search);


        settings = findViewById(R.id.settings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JobSearchActivity.this,Job_search_two.class);
                startActivity(intent);
            }
        });



        // setting up the adapter
        viewPagerAdapter_job = new ViewPagerAdapter(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter_job.add(new Job_Fragment(), "All");
        viewPagerAdapter_job.add(new Karur(), "Shops");
        viewPagerAdapter_job.add(new Karur(), "Market");
        viewPagerAdapter_job.add(new Karur(), "Jobs");
        viewPagerAdapter_job.add(new Karur(), "Events");


        // Set the adapter
        viewPager_job = findViewById(R.id.viewpager_jobsearch);
        viewPager_job.setAdapter(viewPagerAdapter_job);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to set the page viewer
        // we use the setupWithViewPager().
        tabLayout = findViewById(R.id.tab_layout_jobsearch);
        tabLayout.setupWithViewPager(viewPager_job);


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