package com.e.login.ShopClass;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import com.e.login.Help_Class.Helpline;
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
    String data1,data2,data3 = null;
    String api;
    TextView shop_name,bustime;
    RecyclerView recyclerView;
    String aname=null,ades=null,aimg=null,anum=null,apri =null,asec =null,primary= null;
    String mid=null,mimg= null,mname = null,mview_count = null;
    String bname=null,bimage=null,bdes=null;
    String nname=null;
    String nimage=null;
    String nid = null;
    String bid= null;
    private static final int REQUEST_CALL = 1 ;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_shop_screen__main);


        shop_name =  findViewById(R.id.shopp_post);
        recyclerView =findViewById(R.id.shop_screen);
        bustime = findViewById(R.id.bus_txt_one);
        dialog = new Dialog(this);



        Api a = new Api();
        api = a.getBASE_URL();


       Intent intent = getIntent();
       data3 = intent.getStringExtra("cat");





        if (data3.equals("ShopCatalog")){
           String url = api+"get-shop-category-list";
           shop(url,data3);
           shop_name.setText("Shops");
       }else if (data3.equals("ServiceCatalog"))
        {
            String url = api+"get-service-category-list";
            shop(url,data3);
            shop_name.setText("Services");
        }else if (data3.equals("MarketCatalog"))
       {
           String url = api+"get-market-category-list";
           market(url,data3);
           shop_name.setText("Market");
       }else if (data3.equals("EducationCatalog"))
       {
           String url = api+"get-education-category-list";
           shop(url,data3);
           shop_name.setText("Education");
       }else if (data3.equals("TransportCatalog"))
       {
           String url = api+"get-transport-category-list";
           shop(url,data3);
           shop_name.setText("Transports");
       }else if (data3.equals("HospitalCatalog"))
       {
           String url = api+"get-hospital-category-list";
           shop(url,data3);
           shop_name.setText("Hospital");
       }else if (data3.equals("EventCatalog"))
        {
            String url = api+"get-event-category-list";
            shop(url,data3);
            shop_name.setText("Hall");
        }else if (data3.equals("AmbulanceCatalog")) {
            String url = api + "get-Ambulance-list";
            ambulance(url,data3);

            shop_name.setText("Ambulance");
        }else  if (data3.equals("HotelCatalog")){
            String url = api + "get-hotel-category-list";
            shop(url,data3);
            shop_name.setText("Hotels");

        }else  if (data3.equals("BankCatalog")){
            String url = api + "get-bank-category-list";
            shop(url,data3);
            shop_name.setText("Banks");

        }else if (data3.equals("BusTimeCatalog")){
            String url = api + "get-bus-time-category-list";
            bustime(url,data3);
            shop_name.setText("Bus Time");
            bustime.setVisibility(View.VISIBLE);
        }else if (data3.equals("GovtNgoCatalog")){
            String url = api + "get-ngo-govt-category-list";
            ngo(url,data3);
            shop_name.setText("Govt/NGO");
        }else if (data3.equals("ATMCatalog")){
            String url = api + "get-atm-category-list";
            atm(url,data3);
            shop_name.setText("ATM");
        }




            BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_shops11);
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

                   fragment = new EnquiryFragment();
                    break;


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layoutt, fragment).commit();

            return true;
        }
    };





public void shop(String url,String cat) {



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

                    model.setCategory(cat);





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

    private void ambulance(String url,String cat) {


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray res = response.getJSONArray("data");

                    shop_model = new ArrayList<>();


                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);





                        aname = jsonObject.getString("name");
                        ades = jsonObject.getString("description");
                        aimg = jsonObject.getString("image");
                        apri = jsonObject.getString("vehicle_no");
                        primary = jsonObject.getString("primary_number");

                        asec = jsonObject.getString("secondary_number");


                        ShopModel model = new ShopModel();

                        model.setCategory("AmbulanceCatalog");

                        model.setAdes(ades);
                        model.setAimg(aimg);
                        model.setAname(aname);
                        model.setApri(apri);
                        model.setAsec("Call Now");
                        model.setNum(primary);
                        model.setNum_one(asec);





                        shop_model.add(model);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                recyclerView.setLayoutManager(new LinearLayoutManager(ShopScreen_Class.this));
                adapter =  new ShopClassAdapter(ShopScreen_Class.this,shop_model);
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
    private void market(String url,String cat) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


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

                        model.setCategory(cat);

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
    private void bustime(String url,String cat) {


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

                        model.setCategory(cat);





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
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();

                params.put("Authorization", "Bearer  " +PreferenceUtils.getToken(ShopScreen_Class.this));

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShopScreen_Class.this);
        requestQueue.add(jsonObjectRequest);


    }

    private void ngo(String url,String cat) {



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {





                try {
                    JSONArray res = response.getJSONArray("data");

                    shop_model = new ArrayList<>();


                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);


                        nid = jsonObject.getString("id");
                        nimage = jsonObject.getString("logo");
                        nname = jsonObject.getString("name");



                        ShopModel model = new ShopModel();


                        model.setNid(nid);
                        model.setNimage(nimage);
                        model.setNname(nname);


                        model.setCategory(cat);

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



    private void atm(String url,String cat) {



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {

                Log.i("huq1rduq4oa",response.toString());
                try {
                    JSONArray res = response.getJSONArray("data");

                    shop_model = new ArrayList<>();


                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);



                        id = jsonObject.getString("id");
                        name = jsonObject.getString("name");
                        image = jsonObject.getString("image");





                        ShopModel model = new ShopModel();

                        model.setImage(image);
                        model.setText(name);
//                        model.setText_one(view_count+" views");
                        model.setId(id);

                        model.setCategory(cat);





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
//    private void makePhoneCall() {
//        String number = primary;
//
//        if (number.trim().length() > 0) {
//
//            if (ContextCompat.checkSelfPermission(ShopScreen_Class.this,
//                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(ShopScreen_Class.this,
//                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
//            }
//            else {
//                String dial = "tel:" + number;
//                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
//            }
//
//        }
//    }
//    private void makePhoneCall_one() {
//        String number = asec;
//
//        if (number.trim().length() > 0) {
//
//            if (ContextCompat.checkSelfPermission(ShopScreen_Class.this,
//                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(ShopScreen_Class.this,
//                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
//            }
//            else {
//                String dial = "tel:" + number;
//                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
//            }
//
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CALL) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                makePhoneCall();
//            } else {
//                Toast.makeText(ShopScreen_Class.this, "Permission Denied to make a call" + "", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }





    @Override
    public void onItemClick(int position) {

          ShopModel model = shop_model.get(position);

          String S_id = model.getId();
        String S_name = model.getText();
        String m_id = model.getMid();
        String b_id =  model.getBid();
        String n_id = model.getNid();
        String cat = model.getCategory();





        if (data3.equals("MarketCatalog"))
        {
            Intent intent = new Intent(ShopScreen_Class.this, MarketActivity.class);
            intent.putExtra("id",m_id);
            startActivity(intent);
        }
        else if (data3.equals("BusTimeCatalog")){
            Intent intent = new Intent(ShopScreen_Class.this, SmallBusActivity.class);
            intent.putExtra("b_id",b_id);
            startActivity(intent);

        }else if (data3.equals("GovtNgoCatalog")){
            Intent intent = new Intent(ShopScreen_Class.this, GovtActivity.class);
            intent.putExtra("id",n_id);
            startActivity(intent);

        }
        else
        {
            Intent intent = new Intent(ShopScreen_Class.this, ShopsScreenFragment.class);
            intent.putExtra("list", cat);
            intent.putExtra("id", S_id);
            intent.putExtra("name", S_name);

            startActivity(intent);
        }




    }

//        public  void dialog(){
//        dialog.setContentView(R.layout.ambulance_call_recycle);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        TextView txt = dialog.findViewById(R.id.primary_con_txt);
//        TextView txt1 = dialog.findViewById(R.id.sec_con_txt);
//        ImageView img = dialog.findViewById(R.id.primary_con_img);
//        ImageView img1 = dialog.findViewById(R.id.sec_img);
//            Button btn = dialog.findViewById(R.id.cancel_buttonn);
//
//
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                makePhoneCall();
//            }
//        });
//            img1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    makePhoneCall_one();
//                }
//            });
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    dialog.cancel();
//                }
//            });
//        dialog.show();
//
//        }

}


