package com.e.login.Services_Class;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.EducationClass.education_Carrier_Adapter;
import com.e.login.EducationClass.education_Carrier_model;
import com.e.login.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Service_Carrier extends Fragment {

    List<Services_Carrier_model> servicesCarrierModelList;
    Services_Carrier_Adapter adapter;
    String data,data1,id,shop_id,product_title,image,rate,description,sub_image1,sub_image2,sub_image3,sub_image4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_service__carrier, container, false);

        Intent intent = getActivity().getIntent();
        data = intent.getStringExtra("token");
        data1 = intent.getStringExtra("id");




        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-service-list-details?service_id=1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


//                Log.i("0000000",response.toString());
//                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();

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


                    Log.i("123",id);
                    Log.i("123",shop_id);
                    Log.i("123",product_title);
                    Log.i("123",image);
                    Log.i("123",rate);
                    Log.i("123",description);
                    Log.i("123",sub_image1);
                    Log.i("123",sub_image2);
                    Log.i("123",sub_image3);
                    Log.i("123",sub_image4);



                    if(Success.equals("true")){
//                        Log.i("123",msg);
//                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

                        servicesCarrierModelList = new ArrayList<>();

                        RecyclerView recyclerView = root.findViewById(R.id.ser_services_recycle);


                        for (int i = 0; i < 1; i++) {

                            Services_Carrier_model viewmodel = new Services_Carrier_model();



                            viewmodel.setImg(image);
                            viewmodel.setImg1(sub_image1);
                            viewmodel.setImg2(sub_image2);
                            viewmodel.setImg3(sub_image3);
                            viewmodel.setImg4(sub_image4);


                            viewmodel.setTxt(product_title);
                            viewmodel.setTxt1(rate);
                            viewmodel.setTxt2(description);




                            servicesCarrierModelList.add(viewmodel);

                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        adapter =  new Services_Carrier_Adapter(getContext(),servicesCarrierModelList);
                        recyclerView.setAdapter(adapter);




                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
//

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

                params.put("Authorization", "Bearer " + data);
                // params.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWUzNWRjYjg2ZjdjZGUwZTFmOTA5ZmM5YWQwMDdjZGI3ZTE1MzZkNjBhMTEyOTE2ZmQwYmQ0NjE5NzA5ZGYyNTQ3ZDQ5MTFkYzFiNmZkZDUiLCJpYXQiOjE2NDQyMjc1NzYuOTM5ODYyLCJuYmYiOjE2NDQyMjc1NzYuOTM5ODY3LCJleHAiOjE2NzU3NjM1NzYuOTMzMDM2LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.ggB8UvdkfGXmcnlz3KMShC00i-IEhJhq9UwYEq4Oagb73MxNm2WvllC_STJe2wD3FOZnpiYVul_crgERXcxh7C2LHK3UKLmsRSQfxSHHUs1nACk-KFalrcx-llruus8JYwTIjbWccyPWTljJI28aKlBApgfqivUEX0FveiE_LiJQpqSmpiMyojNSlJgN-ofZQc4vuHLdWUtNs-uTRjVpPyz9xw1zEVsPoy3EyVIZ321wlG9ZHGBzUTihuOEHpg0qsCOz_6dJOhQ4CQltWBrg6SJn0_QJ7qBaiMAITQbou2ebemuh945uapuqUCXJVbdFzsMTU2B-JOYoq2G2FrTdaxs_vxCO4ZENoPKFM1Vv-T1HPNnLeAv3Nsuhil5ou-2-uCHsn0tWsPn4zknlwIOulJNs8FbFDcmOG7Hqb8CwlZ-ihp5garS5QPcZxNvC5Qcay6Vijmq93snrR5rgPlq_hW-VFyOm7ZJKkv7uLIfJR529U310wP88Dv68FoOpmlpauO3iuyXt8qwhd_TIwbQM_EanLgz5jWsqtcSsTeMvVpdM8SL-tl_G2b-wjViP4vKqvgiSExZquMahW5yYUdTRN1vtlZ5U0jiQwGMAhKNs45AGgfHcXw68hAigKUQ_qbV7IAIwlun0T1fDvnbfU7tBeTRuX1yJVISTC0k-k4_H8lM");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);

        return root;
    }
}