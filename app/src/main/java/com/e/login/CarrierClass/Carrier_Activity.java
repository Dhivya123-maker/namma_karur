package com.e.login.CarrierClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.e.login.BlankFragment.Blank_PostFragment;
import com.e.login.MoreInfoClass.MoreInfo;
import com.e.login.ProductsFragmentClass.Products_Fragment;
import com.e.login.R;
import com.e.login.ShoppostClass.ShopPost;
import com.google.android.material.tabs.TabLayout;

public class Carrier_Activity extends AppCompatActivity {

    private CarrierViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPage;
    private TabLayout carrier_tablayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrier);

        viewPage = findViewById(R.id.viewpager_carrier);



        viewPagerAdapter = new CarrierViewPagerAdapter(getSupportFragmentManager());

        // add the fragments
//        viewPagerAdapter.add(new  MoreInfo(), "Home");
//        viewPagerAdapter.add(new Carrier_Fragment(), "Products");
//        viewPagerAdapter.add(new MoreInfo(), "Posts");
//        viewPagerAdapter.add(new MoreInfo(), "More Info");

        viewPagerAdapter.add(new Blank_PostFragment(), "Home");
        viewPagerAdapter.add(new Carrier_Fragment(), "Products");
        viewPagerAdapter.add(new ShopPost(), "Flash");
        viewPagerAdapter.add(new MoreInfo(), "Offers");



        // Set the adapter
        viewPage.setAdapter(viewPagerAdapter);


      carrier_tablayout = findViewById(R.id.carrier_tab_layout);
        int pageIndex = 1;
        carrier_tablayout.setScrollPosition(pageIndex,0f,true);
        viewPage.setCurrentItem(pageIndex);
      carrier_tablayout.setupWithViewPager(viewPage);




    }
}