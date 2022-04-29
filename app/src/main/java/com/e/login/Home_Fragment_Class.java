package com.e.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.e.login.BlankFragment.Blank_PostFragment;
import com.e.login.Facility_Class.Facility_Fragment;
import com.e.login.Gallery_Class.Gallery_Fragment;
import com.e.login.Help_Class.Helpline;
import com.e.login.HelperClass.ViewPagerAdapter;
import com.e.login.HomeClass.Fragment_Home;
import com.e.login.MoreInfoClass.MoreInfo;
import com.e.login.ProductsFragmentClass.Products_Fragment;
import com.e.login.ShoppostClass.ShopPost;
import com.e.login.ShopscreenClass.ShopsScreenFragment;
import com.e.login.info_Class.InformationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class Home_Fragment_Class extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager3;
    private TabLayout tabLayout;

    TextView textView;

    FloatingActionButton floatingActionButton;
    public static final String TAG = "bottom_sheet";
    String data,data1,data2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment_class);
        viewPager3 = findViewById(R.id.viewpager_home);

        Intent intent = getIntent();
        data = intent.getStringExtra("name");
        data1 = intent.getStringExtra("list");
        data2 = intent.getStringExtra("id");


        textView = findViewById(R.id.name);
        textView.setText(data);

//        BottomNavigationView btnNav = findViewById(R.id.bottomNavigation_frag);
//        btnNav.setOnNavigationItemSelectedListener(navListener);


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

        if(data1.equals("EventCatalog")){
            viewPagerAdapter.add(new Blank_PostFragment(), "Home");
            viewPagerAdapter.add(new Gallery_Fragment(), "Gallery");
            viewPagerAdapter.add(new Facility_Fragment(), "Facility");
        }else if(data1.equals("ShopCatalog")){
            viewPagerAdapter.add(new Blank_PostFragment(), "Home");
            viewPagerAdapter.add(new Products_Fragment(), "Products");
            viewPagerAdapter.add(new ShopPost(), "Flash");
            viewPagerAdapter.add(new MoreInfo(), "Offers");

        }else if(data1.equals("ServiceCatalog")){
            viewPagerAdapter.add(new Blank_PostFragment(), "Home");
            viewPagerAdapter.add(new Products_Fragment(), "Services");
            viewPagerAdapter.add(new ShopPost(), "Flash");
            viewPagerAdapter.add(new MoreInfo(), "Offers");

        }else if(data1.equals("EducationCatalog")){
            viewPagerAdapter.add(new Blank_PostFragment(), "Home");
            viewPagerAdapter.add(new Products_Fragment(), "Courses");
            viewPagerAdapter.add(new ShopPost(), "Flash");
            viewPagerAdapter.add(new MoreInfo(), "Offers");

        }else if(data1.equals("TransportCatalog")){
            viewPagerAdapter.add(new Blank_PostFragment(), "Home");
            viewPagerAdapter.add(new Products_Fragment(), "Transports");
            viewPagerAdapter.add(new ShopPost(), "Flash");
            viewPagerAdapter.add(new MoreInfo(), "Offers");

        }else if(data1.equals("HotelCatalog")){
            viewPagerAdapter.add(new Blank_PostFragment(), "Home");
            viewPagerAdapter.add(new Products_Fragment(), "Menu");
            viewPagerAdapter.add(new ShopPost(), "Flash");
            viewPagerAdapter.add(new MoreInfo(), "Offers");

        }
        else if(data1.equals("HospitalCatalog")){
            viewPagerAdapter.add(new Blank_PostFragment(), "Home");
            viewPagerAdapter.add(new Products_Fragment(), "Treatment");
            viewPagerAdapter.add(new ShopPost(), "Flash");
            viewPagerAdapter.add(new MoreInfo(), "Offers");

        }

        else if(data1.equals("BankCatalog")){
            viewPagerAdapter.add(new Blank_PostFragment(), "Home");
            viewPagerAdapter.add(new ShopPost(), "Flash");


        }  else if(data1.equals("KarurblogCatalog")){
            viewPagerAdapter.add(new Blank_PostFragment(), "Home");
            viewPagerAdapter.add(new ShopPost(), "Quotes");
            viewPagerAdapter.add(new MoreInfo(), "Videos");

        }else if(data1.equals("ATMCatalog")){

        }


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
//
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
//                    fragment = new Post_Fragment();
//                    break;
//
//
//            }
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout1, fragment).commit();
//
//            return true;
//        }
//    };



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
