package com.e.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.e.login.HelperClass.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class Search_screen_class extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter_search;
    private ViewPager viewPager_search;
    private TabLayout tabLayout;
    BottomNavigationView btm;
    private int[] tabIcons = {
            R.drawable.phones,
            R.drawable.filter,

    };



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_two);

        viewPager_search = findViewById(R.id.viewpager_search);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

//        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_shops);
//        btnNav.setOnNavigationItemSelectedListener(navListener);
//
//

        // setting up the adapter
        viewPagerAdapter_search = new ViewPagerAdapter(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter_search.add(new Serach_screen(), "Shops");
        //viewPagerAdapter_search.add(new Serach_screen(), R.drawable.phones);




        // Set the adapter
        viewPager_search.setAdapter(viewPagerAdapter_search);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to set the page viewer
        // we use the setupWithViewPager().
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager_search);




    }
//    private void setupTabIcons() {
////        ImageView imgView= new ImageView(Search_screen_class.this);
////        imgView.setImageResource(R.drawable.filter);
//
//      //  tabLayout.getTabAt(0).setCustomView(imgView);
////        tabLayout.getTabAt(0).setIcon(R.drawable.filter);
//       // tabLayout.getTabAt(1).setText("Shops");
////
////        View view = LayoutInflater.from(this).inflate(R.layout.search_two, null);
////        ((ImageView) view.findViewById(R.id.icon2)).setImageResource(R.drawable.filter);
////
//
//    }
//    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        Fragment selectedFragment = null;
//
////          #3  to create fragements for each
//
//        switch  (item.getItemId()){
//            case R.id.nav_home:
//                selectedFragment = new Fragment_Home();
//                break;
//            case R.id.nav_tree:
//                selectedFragment = new InformationFragment();
//                break;
//            case R.id.nav_qr:
//                selectedFragment = new QrCodeFragment();
//                break;
//            case R.id.nav_profilee:
//                selectedFragment = new ProfileFragment();
//                break;
//            case R.id.nav_notifications:
//                selectedFragment = new NotificationsFragment();
//                break;
//
//
//        }
//
////           #4  begin transaction
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,selectedFragment).commit();
//        return true;
//    }
//};

}