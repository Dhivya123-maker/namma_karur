package com.e.login.JobsClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.BaseApi.Api;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class View_MoreJobs extends AppCompatActivity {
    List<View_MoreJob_Model> view_moreJob_models;
    View_Morejobs_Adapter adapter;
    RecyclerView recyclerView;
    String id,name,image;
    String api,data,data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more_jobs);


        Api a = new Api();
        api = a.getBASE_URL();


        Intent intent = getIntent();
        data = intent.getStringExtra("id");
        data1 = intent.getStringExtra("cat1");


        recyclerView =findViewById(R.id.view_more_screen);



        view_more();



    }
    public  void  view_more(){

        String url = "http://nk.inevitabletech.email/public/api/view-more-category";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {
                Log.i("hgtviuy",response.toString());



                try {

                    view_moreJob_models = new ArrayList<>();


                    JSONArray jsonArray = response.getJSONArray("data");



                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject((i));

                        id = jsonObject.getString("id");
                        name = jsonObject.getString("name");
                        image = jsonObject.getString("image");




                        View_MoreJob_Model viewmodel = new View_MoreJob_Model();


                        viewmodel.setTxt(name);
                        viewmodel.setImg(image);



                        view_moreJob_models.add(viewmodel);
                    }


                    recyclerView.setLayoutManager(new LinearLayoutManager(View_MoreJobs.this));

                    adapter =  new View_Morejobs_Adapter(View_MoreJobs.this,view_moreJob_models);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(View_MoreJobs.this, LinearLayoutManager.HORIZONTAL, false));



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

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(View_MoreJobs.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(View_MoreJobs.this);
        requestQueue.add(jsonObjectRequest);


    }
}