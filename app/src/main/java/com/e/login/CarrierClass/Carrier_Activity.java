package com.e.login.CarrierClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.BaseApi.Api;
import com.e.login.BlankFragment.Blank_PostFragment;
import com.e.login.MoreInfoClass.MoreInfo;
import com.e.login.ProductsFragmentClass.Products_Fragment;
import com.e.login.R;
import com.e.login.ShoppostClass.ShopPost;
import com.e.login.utils.PreferenceUtils;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Carrier_Activity extends AppCompatActivity {



    List<Shop_Carrier_model> shopCarrierModelList;
    Shop_Carrier_Adapter adapter;
    String data,data1,id,shop_id,product_title,image,rate,description,sub_image1,sub_image2,sub_image3,sub_image4;
    RecyclerView recyclerView;
    String data2,data3,data4,api,service_id,name,education_id,course= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrier);


        Intent intent = getIntent();
        data1 = intent.getStringExtra("id");
        data2 = intent.getStringExtra("list");


      recyclerView = findViewById(R.id.carrier_services_recycle);
        Api a = new Api();
        api = a.getBASE_URL();


       // Toast.makeText(Carrier_Activity.this, data2, Toast.LENGTH_SHORT).show();


           // products();
       // String url  = "http://nk.inevitabletech.email/public/api/get-shop-product-details?product_id="+data1;




        if(data2.equals("ShopCatalog")){
            String url = api + "get-shop-product-details?product_id="+data1;
            products(url);

        }else if(data2.equals("ServiceCatalog")) {
            String url = api + "get-service-list-details?service_id="+data1;
            p_services(url);




        } else if(data2.equals("EducationCatalog")){
            String url = api + "get-education-course-detail?course_id="+data1;
            courses(url);



        }else if(data2.equals("TransportCatalog")){
            String url = api + "get-transport-list-detail?transport_id="+data1;
            p_services(url);


        }else if(data2.equals("HospitalCatalog")){
            String url = api + "get-hospital-treatment-Details?treatment_id=1"+data1;
            p_services(url);


        }


//


//        viewPage = findViewById(R.id.viewpager_carrier);
//
//
//
//        viewPagerAdapter = new CarrierViewPagerAdapter(getSupportFragmentManager());
//
//
//
//        viewPagerAdapter.add(new Blank_PostFragment(), "Home");
//        viewPagerAdapter.add(new Carrier_Fragment(), "Products");
//        viewPagerAdapter.add(new ShopPost(), "Flash");
//        viewPagerAdapter.add(new MoreInfo(), "Offers");
//
//
//
//        // Set the adapter
//        viewPage.setAdapter(viewPagerAdapter);
//
//
//      carrier_tablayout = findViewById(R.id.carrier_tab_layout);
//        int pageIndex = 1;
//        carrier_tablayout.setScrollPosition(pageIndex,0f,true);
//        viewPage.setCurrentItem(pageIndex);
//      carrier_tablayout.setupWithViewPager(viewPage);




    }
    public void products(String url){


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


//                Log.i("0000000",response.toString());
//                Toast.makeText(Carrier_Activity.this, response.toString(), Toast.LENGTH_SHORT).show();

                try {



                    String Success = response.getString("success");
                    String msg = response.getString("message");



                    JSONObject jsonObject = response.getJSONObject("data");
                    id = jsonObject.getString("id");
                    shop_id = jsonObject.getString("shop_id");
                    product_title = jsonObject.getString("product_title");
                    image =jsonObject.getString("image");
                    rate = jsonObject.getString("rate");
                    description = jsonObject.getString("description");
                    sub_image1 = jsonObject.getString("sub_image1");
                    sub_image2 = jsonObject.getString("sub_image2");
                    sub_image3 = jsonObject.getString("sub_image3");
                    sub_image4 = jsonObject.getString("sub_image4");



                    shopCarrierModelList = new ArrayList<>();




                    Shop_Carrier_model viewmodel = new Shop_Carrier_model();



                    viewmodel.setImg(image);
                    viewmodel.setImg1(sub_image1);
                    viewmodel.setImg2(sub_image2);
                    viewmodel.setImg3(sub_image3);
                    viewmodel.setImg4(sub_image4);


                    viewmodel.setTxt(product_title);
                    viewmodel.setTxt1(rate);
                    viewmodel.setTxt2(description);




                    shopCarrierModelList.add(viewmodel);



                    recyclerView.setLayoutManager(new LinearLayoutManager(Carrier_Activity.this));

                    adapter =  new Shop_Carrier_Adapter(Carrier_Activity.this,shopCarrierModelList);
                    recyclerView.setAdapter(adapter);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Carrier_Activity.this) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    recyclerView.setLayoutManager(linearLayoutManager);





                } catch (Exception e) {
                    e.printStackTrace();


                }


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

                params.put("Accept","application/json");
                params.put("Authorization", "Bearer " + PreferenceUtils.getToken(Carrier_Activity.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Carrier_Activity.this);
        requestQueue.add(jsonObjectRequest);




    }
    public  void p_services(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


                Log.i("0000000",response.toString());
                Toast.makeText(Carrier_Activity.this, response.toString(), Toast.LENGTH_SHORT).show();

                try {




                    JSONObject jsonObject = response.getJSONObject("data");
                    id = jsonObject.getString("id");
                    service_id = jsonObject.getString("service_id");
                    name = jsonObject.getString("name");
                    image =jsonObject.getString("image");
                    rate = jsonObject.getString("rate");
                    description = jsonObject.getString("description");
                    sub_image1 = jsonObject.getString("sub_image1");
                    sub_image2 = jsonObject.getString("sub_image2");
                    sub_image3 = jsonObject.getString("sub_image3");
                    sub_image4 = jsonObject.getString("sub_image4");



                    shopCarrierModelList = new ArrayList<>();





                        Shop_Carrier_model viewmodel = new Shop_Carrier_model();



                        viewmodel.setImg(image);
                        viewmodel.setImg1(sub_image1);
                        viewmodel.setImg2(sub_image2);
                        viewmodel.setImg3(sub_image3);
                        viewmodel.setImg4(sub_image4);


                        viewmodel.setTxt(name);
                        viewmodel.setTxt1(rate);
                        viewmodel.setTxt2(description);




                        shopCarrierModelList.add(viewmodel);


                    recyclerView.setLayoutManager(new LinearLayoutManager(Carrier_Activity.this));

                    adapter =  new Shop_Carrier_Adapter(Carrier_Activity.this,shopCarrierModelList);
                    recyclerView.setAdapter(adapter);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Carrier_Activity.this) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    recyclerView.setLayoutManager(linearLayoutManager);





                } catch (Exception e) {
                    e.printStackTrace();


                }


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

                params.put("Accept","application/json");
                params.put("Authorization", "Bearer " + PreferenceUtils.getToken(Carrier_Activity.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Carrier_Activity.this);
        requestQueue.add(jsonObjectRequest);
    }

    public  void courses(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


                Log.i("0000000",response.toString());
                Toast.makeText(Carrier_Activity.this, response.toString(), Toast.LENGTH_SHORT).show();

                try {




                    JSONObject jsonObject = response.getJSONObject("data");
                    id = jsonObject.getString("id");
                    education_id = jsonObject.getString("education_id");
                    course = jsonObject.getString("course_title");
                    image =jsonObject.getString("image");
                    rate = jsonObject.getString("rate");
                    description = jsonObject.getString("description");
                    sub_image1 = jsonObject.getString("sub_image1");
                    sub_image2 = jsonObject.getString("sub_image2");
                    sub_image3 = jsonObject.getString("sub_image3");
                    sub_image4 = jsonObject.getString("sub_image4");



                    shopCarrierModelList = new ArrayList<>();




                        Shop_Carrier_model viewmodel = new Shop_Carrier_model();


                        viewmodel.setImg(image);
                        viewmodel.setImg1(sub_image1);
                        viewmodel.setImg2(sub_image2);
                        viewmodel.setImg3(sub_image3);
                        viewmodel.setImg4(sub_image4);


                        viewmodel.setTxt(course);
                        viewmodel.setTxt1(rate);
                        viewmodel.setTxt2(description);




                        shopCarrierModelList.add(viewmodel);



                    recyclerView.setLayoutManager(new LinearLayoutManager(Carrier_Activity.this));

                    adapter =  new Shop_Carrier_Adapter(Carrier_Activity.this,shopCarrierModelList);
                    recyclerView.setAdapter(adapter);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Carrier_Activity.this) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    recyclerView.setLayoutManager(linearLayoutManager);





                } catch (Exception e) {
                    e.printStackTrace();


                }


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

                params.put("Accept","application/json");
                params.put("Authorization", "Bearer " + PreferenceUtils.getToken(Carrier_Activity.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Carrier_Activity.this);
        requestQueue.add(jsonObjectRequest);
    }
}