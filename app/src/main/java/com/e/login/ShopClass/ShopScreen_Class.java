package com.e.login.ShopClass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import com.e.login.GovtClass.GovtActivity;
import com.e.login.HomeClass.Fragment_Home;
import com.e.login.info_Class.InformationFragment;
import com.e.login.EnquiryFragment;
import com.e.login.Helpline;
import com.e.login.MarketListClass.MarketActivity;
import com.e.login.QrCodeFragment;
import com.e.login.R;
import com.e.login.Search_screen_class;
import com.e.login.ShopscreenClass.ShopsScreenFragment;
import com.e.login.SmallBusClass.SmallBusActivity;
import com.e.login.utils.PreferenceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShopScreen_Class extends AppCompatActivity implements ShopClassAdapter.OnItemClickListener {

    EditText search;
    LinearLayout linearLayout;
    List<ShopModel> shop_model;
    ShopClassAdapter adapter;
    String id = null;
    String name = null;
    String image = null;
    String view_count = null;
    String data1,data2,data3;
    String api;
    TextView shop_name,bustime;
    RecyclerView recyclerView;
    String aname=null,ades=null,aimg=null,anum=null,apri =null,asec =null;
    String mid=null,mimg= null,mname = null,mview_count = null;
    String bname=null,bimage=null,bdes=null;
    String nname=null;
    String nimage=null;
    String nid = null;
    String bid= null;



    ArrayList<String> ImgUrl= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_shop_screen__main);


        shop_name =  findViewById(R.id.shopp_post);
        recyclerView =findViewById(R.id.shop_screen);
        bustime = findViewById(R.id.bus_txt_one);



        Api a = new Api();
        api = a.getBASE_URL();



       Intent intent = getIntent();
       data3 = intent.getStringExtra("cat");

        Log.i("dfgdfg",data3);


        if (data3.equals("ShopCatalog")){
           String url = api+"get-shop-category-list";
           shop(url);
           shop_name.setText("Shops");
       }else if (data3.equals("ServiceCatalog"))
        {
            String url = api+"get-service-category-list";
            shop(url);
            shop_name.setText("Services");
        }else if (data3.equals("MarketCatalog"))
       {
           String url = api+"get-market-category-list";
           market(url);
           shop_name.setText("Market");
       }else if (data3.equals("EducationCatalog"))
       {
           String url = api+"get-education-category-list";
           shop(url);
           shop_name.setText("Education");
       }else if (data3.equals("TransportCatalog"))
       {
           String url = api+"get-transport-category-list";
           shop(url);
           shop_name.setText("Transports");
       }else if (data3.equals("HospitalCatalog"))
       {
           String url = api+"get-hospital-category-list";
           shop(url);
           shop_name.setText("Hospital");
       }else if (data3.equals("EventCatalog"))
        {
            String url = api+"get-event-category-list";
            shop(url);
            shop_name.setText("Events");
        }else if (data3.equals("AmbulanceCatalog")) {
            String url = api + "get-Ambulance-list";
            ambulance(url);
            shop_name.setText("Ambulance");
        }else  if (data3.equals("HotelCatalog")){
            String url = api + "get-hotel-category-list";
            shop(url);
            shop_name.setText("Hotels");

        }else  if (data3.equals("BankCatalog")){
            String url = api + "get-bank-category-list";
            shop(url);
            shop_name.setText("Banks");

        }else if (data3.equals("BusTimeCatalog")){
            String url = api + "get-bus-time-category-list";
            bustime(url);
            shop_name.setText("Bus Time");
            bustime.setVisibility(View.VISIBLE);
        }else if (data3.equals("NgoCatalog")){
            String url = api + "get-ngo-govt-category-list";
            ngo(url);
            shop_name.setText("Govt/NGO");
        }





            BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_shops);
        btnNav.setOnNavigationItemSelectedListener(navListener);

        search = findViewById(R.id.searching1);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopScreen_Class.this, Search_screen_class.class);
                startActivity(intent);
            }
        });

        linearLayout = findViewById(R.id.ac_shop);



    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

//          #3  to create fragements for each

            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new Fragment_Home();
                    break;
                case R.id.nav_tree:
                    selectedFragment = new InformationFragment();
                    break;
                case R.id.nav_qr:
                    selectedFragment = new QrCodeFragment();
                    break;
                case R.id.nav_profilee:

                    selectedFragment = new Helpline();
                    break;
                case R.id.nav_notifications:
                    selectedFragment = new EnquiryFragment();
                    break;


            }

//           #4  begin transaction
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, selectedFragment).commit();
            return true;
        }
    };





public void shop(String url) {







    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        @SuppressLint("CheckResult")
        @Override
        public void onResponse(JSONObject response) {



            try {
                JSONArray res = response.getJSONArray("data");

                shop_model = new ArrayList<>();


                for (int i=0;i<res.length();i++){


                    JSONObject jsonObject = res.getJSONObject(i);



                    id = jsonObject.getString("id");
                    name = jsonObject.getString("name");
                    image = jsonObject.getString("image");
                    view_count = jsonObject.getString("view_count") ;





                    ShopModel model = new ShopModel();

                    model.setImage(image);
                    model.setText(name);
                    model.setText_one(view_count+" views");
                    model.setId(id);

                    model.setCategory(data3);





                    shop_model.add(model);


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            recyclerView.setLayoutManager(new LinearLayoutManager(ShopScreen_Class.this));
            adapter =  new ShopClassAdapter(ShopScreen_Class.this,shop_model);
            adapter.setOnItemClickListener(ShopScreen_Class.this);
            recyclerView.setAdapter(adapter);







        }

//
//
//        }
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

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(ShopScreen_Class.this));
               // params.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMTM5N2QxNTAyOGZjZmFjYWVmNjg4MGI0M2Q2OWE0MjU0NjFhMjQwMDQ2ZDc2MDQ1MDk0ZWIxZDk2NDNhZTk4YmE3YTkwMGZhODQwZDIzZGMiLCJpYXQiOjE2NDQ2NjIwNDUuNjk5MTE4LCJuYmYiOjE2NDQ2NjIwNDUuNjk5MTIyLCJleHAiOjE2NzYxOTgwNDUuNjk2MDgxLCJzdWIiOiIxNiIsInNjb3BlcyI6W119.kCe6D4wazRjA5cmETRJhsbKiD6BKhyY_ENT8Ve9QluNjdix7PJI-3HK82fdOAD_A0KYtDhHtCqQlWEEVYT9E6MAueMvPTJ06LQyK5o8C_iUS1n_dWPS04bb1N5R_pIIRdS3wz20JuobRBkAXxTcYM74bnfMmKEVxcmyhwoFdlnDctm3aNEN7NI-2dFVrviYUIbN8L2y3bbZy8zlijMBs7vh77sSVVFMkLgJCiMaKxF-hTyS-wrRz-2ClGqRdQYMQK9y6zlw_-47I9arebNWxukZUGc4-cgJUSedJ7GNuJVBux4PclLns-z6hXIQjCr_C8icbMUmAU7GOWHTnpc1fA-lR-1OQfLFBcwksRWmEFB_O60PVjQzw3L1uh_rW-DsNJs_y1BADJ8RpwMfA9-dVcL7Df-EdnrMP-E1ZgI62_QfuMcc6jM1-LWXzmUHitb8sUSFLqX6OfUGpM2sQ6jtPF3bVR5H9P4Idck3RFfNa1OGikyoldYGFaMOKs-C5fgW2t4YtVFnKJ3ROC9Pg-7ipguu4uCAxUmqsOWDlTAfQglKWZuNsSq_4eKDbq2eq6oDtlFDgO8e5XeYk6bwIQFTcf5RZlcHbMz135zLYu4872r2nsCLrzgeyb5-aveqzS9I5rWufpAz3TkejDzUbb9yItuo0LjbppHY8cw3r4Vz837U");
                           // params.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWUzNWRjYjg2ZjdjZGUwZTFmOTA5ZmM5YWQwMDdjZGI3ZTE1MzZkNjBhMTEyOTE2ZmQwYmQ0NjE5NzA5ZGYyNTQ3ZDQ5MTFkYzFiNmZkZDUiLCJpYXQiOjE2NDQyMjc1NzYuOTM5ODYyLCJuYmYiOjE2NDQyMjc1NzYuOTM5ODY3LCJleHAiOjE2NzU3NjM1NzYuOTMzMDM2LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.ggB8UvdkfGXmcnlz3KMShC00i-IEhJhq9UwYEq4Oagb73MxNm2WvllC_STJe2wD3FOZnpiYVul_crgERXcxh7C2LHK3UKLmsRSQfxSHHUs1nACk-KFalrcx-llruus8JYwTIjbWccyPWTljJI28aKlBApgfqivUEX0FveiE_LiJQpqSmpiMyojNSlJgN-ofZQc4vuHLdWUtNs-uTRjVpPyz9xw1zEVsPoy3EyVIZ321wlG9ZHGBzUTihuOEHpg0qsCOz_6dJOhQ4CQltWBrg6SJn0_QJ7qBaiMAITQbou2ebemuh945uapuqUCXJVbdFzsMTU2B-JOYoq2G2FrTdaxs_vxCO4ZENoPKFM1Vv-T1HPNnLeAv3Nsuhil5ou-2-uCHsn0tWsPn4zknlwIOulJNs8FbFDcmOG7Hqb8CwlZ-ihp5garS5QPcZxNvC5Qcay6Vijmq93snrR5rgPlq_hW-VFyOm7ZJKkv7uLIfJR529U310wP88Dv68FoOpmlpauO3iuyXt8qwhd_TIwbQM_EanLgz5jWsqtcSsTeMvVpdM8SL-tl_G2b-wjViP4vKqvgiSExZquMahW5yYUdTRN1vtlZ5U0jiQwGMAhKNs45AGgfHcXw68hAigKUQ_qbV7IAIwlun0T1fDvnbfU7tBeTRuX1yJVISTC0k-k4_H8lM");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShopScreen_Class.this);
        requestQueue.add(jsonObjectRequest);

}

    private void ambulance(String url) {


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {

                Log.i("dfgdfg",response.toString());

                try {
                    JSONArray res = response.getJSONArray("data");

                    shop_model = new ArrayList<>();


                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);





                        aname = jsonObject.getString("name");
                        ades = jsonObject.getString("description");
                        aimg = jsonObject.getString("image");
                        apri = jsonObject.getString("primary_number");
                        asec = jsonObject.getString("secondary_number");



                        ShopModel model = new ShopModel();

                        model.setCategory(data3);

                        model.setAdes(ades);
                        model.setAimg(aimg);
                        model.setAname(aname);
                        model.setApri(apri);
                        model.setAsec(asec);




                        shop_model.add(model);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                recyclerView.setLayoutManager(new LinearLayoutManager(ShopScreen_Class.this));
                adapter =  new ShopClassAdapter(ShopScreen_Class.this,shop_model);
                adapter.setOnItemClickListener(ShopScreen_Class.this);
                recyclerView.setAdapter(adapter);







            }

//
//
//        }
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

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(ShopScreen_Class.this));
                // params.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMTM5N2QxNTAyOGZjZmFjYWVmNjg4MGI0M2Q2OWE0MjU0NjFhMjQwMDQ2ZDc2MDQ1MDk0ZWIxZDk2NDNhZTk4YmE3YTkwMGZhODQwZDIzZGMiLCJpYXQiOjE2NDQ2NjIwNDUuNjk5MTE4LCJuYmYiOjE2NDQ2NjIwNDUuNjk5MTIyLCJleHAiOjE2NzYxOTgwNDUuNjk2MDgxLCJzdWIiOiIxNiIsInNjb3BlcyI6W119.kCe6D4wazRjA5cmETRJhsbKiD6BKhyY_ENT8Ve9QluNjdix7PJI-3HK82fdOAD_A0KYtDhHtCqQlWEEVYT9E6MAueMvPTJ06LQyK5o8C_iUS1n_dWPS04bb1N5R_pIIRdS3wz20JuobRBkAXxTcYM74bnfMmKEVxcmyhwoFdlnDctm3aNEN7NI-2dFVrviYUIbN8L2y3bbZy8zlijMBs7vh77sSVVFMkLgJCiMaKxF-hTyS-wrRz-2ClGqRdQYMQK9y6zlw_-47I9arebNWxukZUGc4-cgJUSedJ7GNuJVBux4PclLns-z6hXIQjCr_C8icbMUmAU7GOWHTnpc1fA-lR-1OQfLFBcwksRWmEFB_O60PVjQzw3L1uh_rW-DsNJs_y1BADJ8RpwMfA9-dVcL7Df-EdnrMP-E1ZgI62_QfuMcc6jM1-LWXzmUHitb8sUSFLqX6OfUGpM2sQ6jtPF3bVR5H9P4Idck3RFfNa1OGikyoldYGFaMOKs-C5fgW2t4YtVFnKJ3ROC9Pg-7ipguu4uCAxUmqsOWDlTAfQglKWZuNsSq_4eKDbq2eq6oDtlFDgO8e5XeYk6bwIQFTcf5RZlcHbMz135zLYu4872r2nsCLrzgeyb5-aveqzS9I5rWufpAz3TkejDzUbb9yItuo0LjbppHY8cw3r4Vz837U");
                // params.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWUzNWRjYjg2ZjdjZGUwZTFmOTA5ZmM5YWQwMDdjZGI3ZTE1MzZkNjBhMTEyOTE2ZmQwYmQ0NjE5NzA5ZGYyNTQ3ZDQ5MTFkYzFiNmZkZDUiLCJpYXQiOjE2NDQyMjc1NzYuOTM5ODYyLCJuYmYiOjE2NDQyMjc1NzYuOTM5ODY3LCJleHAiOjE2NzU3NjM1NzYuOTMzMDM2LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.ggB8UvdkfGXmcnlz3KMShC00i-IEhJhq9UwYEq4Oagb73MxNm2WvllC_STJe2wD3FOZnpiYVul_crgERXcxh7C2LHK3UKLmsRSQfxSHHUs1nACk-KFalrcx-llruus8JYwTIjbWccyPWTljJI28aKlBApgfqivUEX0FveiE_LiJQpqSmpiMyojNSlJgN-ofZQc4vuHLdWUtNs-uTRjVpPyz9xw1zEVsPoy3EyVIZ321wlG9ZHGBzUTihuOEHpg0qsCOz_6dJOhQ4CQltWBrg6SJn0_QJ7qBaiMAITQbou2ebemuh945uapuqUCXJVbdFzsMTU2B-JOYoq2G2FrTdaxs_vxCO4ZENoPKFM1Vv-T1HPNnLeAv3Nsuhil5ou-2-uCHsn0tWsPn4zknlwIOulJNs8FbFDcmOG7Hqb8CwlZ-ihp5garS5QPcZxNvC5Qcay6Vijmq93snrR5rgPlq_hW-VFyOm7ZJKkv7uLIfJR529U310wP88Dv68FoOpmlpauO3iuyXt8qwhd_TIwbQM_EanLgz5jWsqtcSsTeMvVpdM8SL-tl_G2b-wjViP4vKqvgiSExZquMahW5yYUdTRN1vtlZ5U0jiQwGMAhKNs45AGgfHcXw68hAigKUQ_qbV7IAIwlun0T1fDvnbfU7tBeTRuX1yJVISTC0k-k4_H8lM");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShopScreen_Class.this);
        requestQueue.add(jsonObjectRequest);


    }
    private void market(String url) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {

                Log.i("dfgdfg",response.toString());

                try {
                    JSONArray res = response.getJSONArray("data");

                    shop_model = new ArrayList<>();


                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);





                        mname = jsonObject.getString("name");
                        mview_count = jsonObject.getString("view_count");
                        mimg = jsonObject.getString("logo");
                        mid = jsonObject.getString("id");





                        ShopModel model = new ShopModel();

                        model.setCategory(data3);

                        model.setMid(mid);
                        model.setMimg(mimg);
                        model.setMname(mname);
                        model.setMview_count(mview_count);





                        shop_model.add(model);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                recyclerView.setLayoutManager(new LinearLayoutManager(ShopScreen_Class.this));
                adapter =  new ShopClassAdapter(ShopScreen_Class.this,shop_model);
                adapter.setOnItemClickListener(ShopScreen_Class.this);
                recyclerView.setAdapter(adapter);







            }

//
//
//        }
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

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(ShopScreen_Class.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShopScreen_Class.this);
        requestQueue.add(jsonObjectRequest);


    }
    private void bustime(String url) {


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



                try {
                    JSONArray res = response.getJSONArray("data");

                    shop_model = new ArrayList<>();


                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);



                        bid = jsonObject.getString("id");
                        bname = jsonObject.getString("name");
                        bimage = jsonObject.getString("image");
                        bdes = jsonObject.getString("description") ;





                        ShopModel model = new ShopModel();

                        model.setBimg(bimage);
                        model.setBname(bname);
                        model.setBdes(bdes);
                        model.setBid(bid);

                        model.setCategory(data3);





                        shop_model.add(model);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                recyclerView.setLayoutManager(new LinearLayoutManager(ShopScreen_Class.this));
                adapter =  new ShopClassAdapter(ShopScreen_Class.this,shop_model);
                adapter.setOnItemClickListener(ShopScreen_Class.this);
                recyclerView.setAdapter(adapter);







            }

//
//
//        }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(ShopScreen_Class.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShopScreen_Class.this);
        requestQueue.add(jsonObjectRequest);


    }

    private void ngo(String url) {



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {





                try {
                    JSONArray res = response.getJSONArray("data");

                    shop_model = new ArrayList<>();


                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);

//
//                        Toast.makeText(ShopScreen_Class.this, response.toString(), Toast.LENGTH_SHORT).show();
//                        Log.i("jbfhusduycfhb",response.toString());

                        nid = jsonObject.getString("id");
                        nimage = jsonObject.getString("logo");
                        nname = jsonObject.getString("name");



                        ShopModel model = new ShopModel();


                        model.setNid(nid);
                        model.setNimage(nimage);
                        model.setNname(nname);


                        model.setCategory(data3);

                        shop_model.add(model);

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(ShopScreen_Class.this));
                adapter =  new ShopClassAdapter(ShopScreen_Class.this,shop_model);
                adapter.setOnItemClickListener(ShopScreen_Class.this);
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

                params.put("Authorization", "Bearer  " +PreferenceUtils.getToken(ShopScreen_Class.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShopScreen_Class.this);
        requestQueue.add(jsonObjectRequest);

    }



    @Override
    public void onItemClick(int position) {

          ShopModel model = shop_model.get(position);

          String S_id = model.getId();
        String S_name = model.getText();
        String m_id = model.getMid();
        String b_id =  model.getBid();
        String n_id = model.getNid();



        if (data3.equals("AmbulanceCatalog")){

        }else if (data3.equals("MarketCatalog"))
        {
            Intent intent = new Intent(ShopScreen_Class.this, MarketActivity.class);
            intent.putExtra("id",m_id);
            startActivity(intent);
        }
        else if (data3.equals("BusTimeCatalog")){
            Intent intent = new Intent(ShopScreen_Class.this, SmallBusActivity.class);
            intent.putExtra("b_id",b_id);
            startActivity(intent);

        }else if (data3.equals("NgoCatalog")){
            Intent intent = new Intent(ShopScreen_Class.this, GovtActivity.class);
            intent.putExtra("id",n_id);
            startActivity(intent);

        } else {
            Intent intent = new Intent(ShopScreen_Class.this, ShopsScreenFragment.class);
            intent.putExtra("list", data3);
            intent.putExtra("id", S_id);
            intent.putExtra("name", S_name);
            startActivity(intent);
        }

    }
}

