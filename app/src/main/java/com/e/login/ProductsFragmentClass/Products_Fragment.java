package com.e.login.ProductsFragmentClass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.BaseApi.Api;
import com.e.login.BusTimeClass.Bus_TimeActivity;
import com.e.login.CarrierClass.Carrier_Fragment;
import com.e.login.R;
import com.e.login.ShopClass.ShopClassAdapter;
import com.e.login.ShopClass.ShopScreen_Class;
import com.e.login.ShopscreenClass.ShopScreenAdapter;
import com.e.login.ShopscreenClass.ShopScreenModel;
import com.e.login.ShopscreenClass.ShopsScreenFragment;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Products_Fragment extends Fragment {


    List<ProductsModel> productsModelList;
    ProductsAdapter adapter;
    String data1,data2,data3,data4,data5,api,id,name,image,product_title,desc,course_title;
    RecyclerView recyclerView;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View root= inflater.inflate(R.layout.fragment_products_, container, false);


      recyclerView = root.findViewById(R.id.carrier_ac_screens);

        Api a = new Api();
        api = a.getBASE_URL();
        Intent intent = getActivity().getIntent();

        data1 = intent.getStringExtra("name");
//        data4 = intent.getStringExtra("list");
//        data5 = intent.getStringExtra("id");
        data2 = intent.getStringExtra("list");
        data3 = intent.getStringExtra("id");


        if(data2.equals("ShopCatalog")){
            String url = api + "get-shop-products?shop_id="+data3;
            products(url);

        }else if(data2.equals("ServiceCatalog")) {
            String url = api + "get-service-list?service_id="+data3;
            p_services(url);



        } else if(data2.equals("EducationCatalog")){
            String url = api + "get-education-courses-list?education_id="+data3;
           courses(url);


        }else if(data2.equals("TransportCatalog")){
            String url = api + "get-transport-list-detail?transport_id="+data3;
           p_services(url);


        }else if(data2.equals("HospitalCatalog")){
            String url = api + "get-hospital-treatments?hospital_id="+data3;
            p_services(url);


        }




        return root;

    }
    public void products(String url){



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



                try {


                    productsModelList = new ArrayList<>();

                    JSONArray res = response.getJSONArray("data");

                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);

                        id = jsonObject.getString("id");
                        product_title = jsonObject.getString("product_title");
                        image = jsonObject.getString("image");
                        desc = jsonObject.getString("description");



                            ProductsModel viewmodel = new ProductsModel();

                            viewmodel.setText(product_title);
                            viewmodel.setImage(image);
                            viewmodel.setButton("more");
                            viewmodel.setText_one(desc);


                            productsModelList.add(viewmodel);

                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter =  new ProductsAdapter(getActivity(),productsModelList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
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
                params.put("Authorization","Bearer  "+ PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }


    public void p_services(String url){



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



                try {


                    productsModelList = new ArrayList<>();

                    JSONArray res = response.getJSONArray("data");

                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);

                        id = jsonObject.getString("id");
                       name = jsonObject.getString("name");
                        image = jsonObject.getString("image");
                        desc = jsonObject.getString("description");



                        ProductsModel viewmodel = new ProductsModel();

                        viewmodel.setText(name);
                        viewmodel.setImage(image);
                        viewmodel.setButton("more");
                        viewmodel.setText_one(desc);


                        productsModelList.add(viewmodel);

                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter =  new ProductsAdapter(getActivity(),productsModelList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
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
                params.put("Authorization","Bearer  "+ PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }

    public void courses(String url){



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



                try {


                    productsModelList = new ArrayList<>();

                    JSONArray res = response.getJSONArray("data");

                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);

                        id = jsonObject.getString("id");
                       course_title= jsonObject.getString("course_title");
                        image = jsonObject.getString("image");
                        desc = jsonObject.getString("description");



                        ProductsModel viewmodel = new ProductsModel();

                        viewmodel.setText(course_title);
                        viewmodel.setImage(image);
                        viewmodel.setButton("more");
                        viewmodel.setText_one(desc);


                        productsModelList.add(viewmodel);

                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter =  new ProductsAdapter(getActivity(),productsModelList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
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
                params.put("Authorization","Bearer  "+ PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }

//    @Override
//    public void onItemClick(int position) {
//        Intent intent = new Intent(getContext(),Carrier_Fragment.class);
//        startActivity(intent);
//    }


}