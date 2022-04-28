package com.e.login.NewsClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.e.login.HelperClass.ViewPagerAdapter;
import com.e.login.R;
import com.google.android.material.tabs.TabLayout;

public class NewsActivity extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter_news;
    private ViewPager viewPager_news;
    private TabLayout tabLayout;

    public static final String TAG = "bottom_sheet";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);



        // setting up the adapter
        viewPagerAdapter_news = new ViewPagerAdapter(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter_news.add(new News_Fragment(), "All news");
        viewPagerAdapter_news.add(new Today_News(), "Today news");
        viewPagerAdapter_news.add(new Karur(), "Karur news");
        viewPagerAdapter_news.add(new Astrology_news(), "astrology news");


        // Set the adapter
        viewPager_news = findViewById(R.id.viewpager_news);
        viewPager_news.setAdapter(viewPagerAdapter_news);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to set the page viewer
        // we use the setupWithViewPager().
        tabLayout = findViewById(R.id.tabb_layout);
        tabLayout.setupWithViewPager(viewPager_news);


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

//    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//            int id = item.getItemId();
//            Fragment fragment = null;
//
//            switch (id) {
//                case R.id.nav_home:
//                    fragment = new Fragment_Home();
//                    break;
//                case R.id.nav_tree:
//                    fragment = new InformationFragment();
//                    break;
//                case R.id.nav_qr:
//                    fragment = new QrCodeFragment();
//                    break;
//                case R.id.nav_profilee:
//
//                    fragment = new Helpline();
//                    break;
//                case R.id.nav_notifications:
//                    fragment = new EnquiryFragment();
//                    break;
//
//
//            }
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_news, fragment).commit();
//
//            return true;
//        }
//    };
}