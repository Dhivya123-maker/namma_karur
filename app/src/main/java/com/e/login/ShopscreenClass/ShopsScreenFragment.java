package com.e.login.ShopscreenClass;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.BaseApi.Api;
import com.e.login.EnquiryFragment;
import com.e.login.HelperClass.ViewPagerAdapter;
import com.e.login.Help_Class.Helpline;
import com.e.login.HomeClass.Fragment_Home;
import com.e.login.Home_Fragment_Class;
import com.e.login.Post_Fragment;
import com.e.login.QrCodeFragment;
import com.e.login.R;
import com.e.login.info_Class.InformationFragment;
import com.e.login.utils.PreferenceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShopsScreenFragment extends AppCompatActivity implements ShopScreenAdapter.OnItemClickListener {

    private ViewPagerAdapter viewPagerAdapter;
    List<ShopScreenModel> shop_screen_model;
    ShopScreenAdapter adapter;
    LinearLayout filter,location,gone,atm_visible,visible_lnr;
    SliderView sliderView;
    public static final String TAG = "bottom_sheet";

    int[] images = {R.drawable.first_one,
            R.drawable.banner,
            R.drawable.bank_banner,
            R.drawable.banner,
            R.drawable.first_one,
    };
    String id = null;
    String logo = null;
    String title = null;
    String address = null;
    String postal_code = null;
    String name,image,atm_cat = null;
    String open_time = null;
    String rating,loc = null;
    String verified = null;
   TextView ac;

    String data,data1,data2,data3;
    String api;
    RecyclerView recyclerView;
    private DatePickerDialog datePickerDialog;
    private Button dateButton,dateButton1;


    Context  context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.shops_screen_fragment);



        gone = findViewById(R.id.gone_lnr);
        filter = findViewById(R.id.filter_lnr);
        atm_visible = findViewById(R.id.atm_visivle_lnr);
        visible_lnr = findViewById(R.id.visible_linear);
        sliderView = findViewById(R.id.slider_slider);

       recyclerView =findViewById(R.id.shop_screen_fragment);


        ac = findViewById(R.id.ac_shops1);

        Api a = new Api();
        api = a.getBASE_URL();

        Intent intent = getIntent();
    data = intent.getStringExtra("name");
//     data1 = intent.getStringExtra("id");
     data2 = intent.getStringExtra("list");
        data3 = intent.getStringExtra("id");





        if(data2.equals("ShopCatalog")){
            String url = api + "get-shop-catalog-list?category_id="+data3;
            shop_screen(url);
            ac.setText(data);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);
            gone.setVisibility(View.GONE);
        }else if(data2.equals("ServiceCatalog")) {
            String url = api + "get-service-catalog-list?service_category_id="+data3;
            shop_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);

        }else if(data2.equals("MarketCatalog")){
            String url = api + "get-market-category-list"+data3;
            shop_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);

        } else if(data2.equals("EducationCatalog")){
            String url = api + "get-education-catalog-list?education_category_id="+data3;
            shop_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);

        }else if(data2.equals("TransportCatalog")){
            String url = api + "get-transport-catalog-list?category_id="+data3;
            shop_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);

        }else if(data2.equals("HospitalCatalog")){
            String url = api + "get-hospital-catalog-list?hospital_category_id="+data3;
            shop_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);

        }else if(data2.equals("EventCatalog")){
            String url = api + "get-event-catalog-list?event_category_id="+data3;
            shop_screen(url);
            ac.setText(data);
            sliderView.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);

            initDatePicker();
            dateButton = findViewById(R.id.fromdatepicker);
            dateButton.setText("From Date");
            dateButton = findViewById(R.id.todatepicker);
            dateButton.setText("To Date");
            getTodaysDate();


        }else if(data2.equals("HotelCatalog")){
            String url = api + "get-hotel-catalog-list?hotel_category_id="+data3;
            shop_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);

        }else if(data2.equals("BankCatalog")){
            String url = api + "get-bank-catalog-list?bank_category_id="+data3;
            shop_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);

        }else if(data2.equals("ATMCatalog")){
            String url = api + "get-atm-catalog-list?atm_category_id="+data3;
            atm_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.VISIBLE);
            visible_lnr.setVisibility(View.GONE);


        }


        filter = findViewById(R.id.filter);
//        filter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent1 = new Intent(ShopsScreenFragment.this, Home_Fragment_Class.class);
////
////
////                startActivity(intent1);
//            }
//        });

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_shops1);
        btnNav.setOnNavigationItemSelectedListener(navListener);


        Slidershop_Top_Adapter sliderAdapter = new Slidershop_Top_Adapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();



}
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            Fragment fragment = null;

            switch (id) {
                case R.id.nav_home:
                    fragment = new Fragment_Home();
                    break;
                case R.id.nav_tree:
                    fragment = new InformationFragment();
                    break;
                case R.id.nav_qr:
                    fragment = new QrCodeFragment();
                    break;
                case R.id.nav_profilee:

                    fragment = new Helpline();
                    break;
                case R.id.nav_notifications:
                   fragment = new Post_Fragment();
                    break;


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).commit();

            return true;
        }
    };





    public  void shop_screen(String url){


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



//

                try {
                    JSONArray res = response.getJSONArray("data");

                    shop_screen_model = new ArrayList<>();


                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);


//                        Toast.makeText(ShopsScreenFragment.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
//                        Log.i("jbfhusduycfhb",jsonObject.toString());

                        id = jsonObject.getString("id");
                        logo = jsonObject.getString("logo");
                        title = jsonObject.getString("title");
                        address = jsonObject.getString("address");
                        open_time = jsonObject.getString("open_time");
                        rating = jsonObject.getString("rating");
                        verified = jsonObject.getString("verified");




                        ShopScreenModel viewmodel = new ShopScreenModel();


                        viewmodel.setId(id);
                        viewmodel.setText(title);
                        viewmodel.setImage(logo);
                        viewmodel.setText_one(address);
                        viewmodel.setText_two(rating);
                        viewmodel.setText_three(verified);
                        viewmodel.setText_four(open_time);
                        viewmodel.setCategory(data2);



                        shop_screen_model.add(viewmodel);



                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(ShopsScreenFragment.this));
                adapter =  new ShopScreenAdapter(ShopsScreenFragment.this,shop_screen_model);
                adapter.setOnItemClickListener(ShopsScreenFragment.this);
                recyclerView.setAdapter(adapter);



            }



        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();



                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();

                params.put("Authorization", "Bearer  " +PreferenceUtils.getToken(ShopsScreenFragment.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShopsScreenFragment.this);
        requestQueue.add(jsonObjectRequest);




    }
    public  void atm_screen(String url){


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



//

                try {
                    JSONArray res = response.getJSONArray("data");

                    shop_screen_model = new ArrayList<>();


                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);


//                        Toast.makeText(ShopsScreenFragment.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
//                        Log.i("jbfhusduycfhb",jsonObject.toString());

                        id = jsonObject.getString("id");
                        image = jsonObject.getString("image");
                        name = jsonObject.getString("name");
                        address = jsonObject.getString("address");
                        loc = jsonObject.getString("location");





                        ShopScreenModel viewmodel = new ShopScreenModel();


                        viewmodel.setId(id);
                        viewmodel.setText(name);
                        viewmodel.setImage(image);
                        viewmodel.setText_one(address);
//                        viewmodel.setText_two(rating);
                        viewmodel.setText_three(loc);
                        viewmodel.setCategory(data2);
                        viewmodel.setLoc(loc);



                        shop_screen_model.add(viewmodel);


                        recyclerView.setLayoutManager(new LinearLayoutManager(ShopsScreenFragment.this));
                        adapter =  new ShopScreenAdapter(ShopsScreenFragment.this,shop_screen_model);
                        //adapter.setOnItemClickListener(ShopsScreenFragment.this);
                        recyclerView.setAdapter(adapter);

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }



        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();



                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();

                params.put("Accept","application/json");
                params.put("Authorization", "Bearer  " +PreferenceUtils.getToken(ShopsScreenFragment.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShopsScreenFragment.this);
        requestQueue.add(jsonObjectRequest);




    }

    @Override
    public void onItemClick(int position) {


        ShopScreenModel model = shop_screen_model.get(position);
        String S_id = model.getId();
        String S_name = model.getText();


        if(data2.equals("ATMCatalog")){


        }
        else{
            Intent intent = new Intent(ShopsScreenFragment.this, Home_Fragment_Class.class);
            intent.putExtra("list",data2);
            intent.putExtra("id",S_id);
            intent.putExtra("name",S_name);
            startActivity(intent);
        }

    }


    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
                dateButton1.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

}
