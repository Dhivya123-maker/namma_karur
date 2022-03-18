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


//        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_shops);
//        btnNav.setOnNavigationItemSelectedListener(navListener);
//
//
    }}