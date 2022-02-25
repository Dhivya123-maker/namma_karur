package com.e.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.e.login.BlankFragment.Blank_PostFragment;
import com.e.login.CarrierClass.Carrier_Fragment;
import com.e.login.HelperClass.ViewPagerAdapter;
import com.e.login.MoreInfoClass.MoreInfo;
import com.e.login.ProductsFragmentClass.Products_Fragment;
import com.e.login.ShoppostClass.ShopPost;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class Home_Fragment_Class extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager3;
    private TabLayout tabLayout;

    TextView textView;

    FloatingActionButton floatingActionButton;
    public static final String TAG = "bottom_sheet";
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment_class);
        viewPager3 = findViewById(R.id.viewpager_home);

        Intent intent = getIntent();
        data = intent.getStringExtra("name");

        textView = findViewById(R.id.name);
        textView.setText(data);


//
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                com.e.login.fragment_dialog.BottomSheetFragment fragment = new BottomSheetFragment();
//                fragment.show(getSupportFragmentManager(), TAG);
//            }
//        });

//


        // setting up the adapter
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new Blank_PostFragment(), "Home");
        viewPagerAdapter.add(new Products_Fragment(), "Products");
        viewPagerAdapter.add(new ShopPost(), "Flash");
        viewPagerAdapter.add(new MoreInfo(), "Offers");


        // Set the adapter
        viewPager3.setAdapter(viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to set the page viewer
        // we use the setupWithViewPager().
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager3);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                if (tab.getPosition() == 0) {

//                    floatingActionButton.setVisibility(View.VISIBLE);
                } else if (tab.getPosition() == 1) {

//                    floatingActionButton.setVisibility(View.GONE);

                } else if (tab.getPosition() == 2) {

//                    floatingActionButton.setVisibility(View.GONE);

                } else if (tab.getPosition() == 3) {

//                    floatingActionButton.setVisibility(View.GONE);

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

//    private void clickEvents() {
//
//       floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Home_Fragment_Class.this, Home_Fragment_Class.class);
//                startActivity(intent);
//
//
//            }
//        });
}