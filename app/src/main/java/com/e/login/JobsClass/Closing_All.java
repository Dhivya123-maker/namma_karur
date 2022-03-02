package com.e.login.JobsClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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

public class Closing_All extends AppCompatActivity {
    List<Jobs_two_Model> jobsTwoModelList;
    Jobs_two_Adapter adapter;
    RecyclerView recyclerView;
    String id,name,image,j_name,c_name,vacancy,address,end_date;
    String data,data1;
    TextView break_txt;
    String api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closing_all);

        break_txt = findViewById(R.id.break_txtt);

        Api a = new Api();
        api = a.getBASE_URL();


        Intent intent = getIntent();
        data = intent.getStringExtra("id");
        data1 = intent.getStringExtra("cat1");

        if(data1.equals("closing_jobs")){
            String url = api+"view-closing-full-page";
            all_close(url);
           break_txt.setText("Closing Jobs");
        }
        else if(data1.equals("all_jobs")){
            String url = api+"view-job-details?job_id="+data;
            all_close(url);
            break_txt.setText("All Jobs");

        }
        else if(data1.equals("category")){
            String url = api+"category-Filter-Job?category_id=1";
            all_cls(url);
            break_txt.setText("Categories");
        }

        recyclerView =findViewById(R.id.closing_all);




    }
    public void all_close( String url){
//        String JSON_URL = "http://nk.inevitabletech.email/public/api/view-job-details?job_id="+data;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {
                Log.i("hgtviubhtdruh6r y",response.toString());



                try {


                    jobsTwoModelList = new ArrayList<>();

                    JSONObject jsonObject = response.getJSONObject("data");
                    id = jsonObject.getString("id");
                    j_name = jsonObject.getString("job_name");
                    c_name = jsonObject.getString("company_name");
                    address = jsonObject.getString("address");
                    vacancy = jsonObject.getString("no_of_vacancy");
                    end_date = jsonObject.getString("apply_end_date");


                    Log.i("1",id);
                    Log.i("2",j_name);
                    Log.i("3",c_name);
                    Log.i("4",address);
                    Log.i("5",vacancy);
                    Log.i("6",end_date);

                    Jobs_two_Model viewmodel = new Jobs_two_Model();


                    viewmodel.setTxt(j_name);
                    viewmodel.setTxt1(c_name);
                    viewmodel.setTxt2(address);
                    viewmodel.setTxt3(vacancy);
                    viewmodel.setTxt4(end_date);



                    jobsTwoModelList.add(viewmodel);





                    recyclerView.setLayoutManager(new LinearLayoutManager(Closing_All.this));

                    adapter =  new Jobs_two_Adapter(Closing_All.this,jobsTwoModelList);
                    recyclerView.setAdapter(adapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Closing_All.this) {
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
                params.put("Accept", "application/json");
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Closing_All.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Closing_All.this);
        requestQueue.add(jsonObjectRequest);





    }

    public void all_cls( String url){
//        String JSON_URL = "http://nk.inevitabletech.email/public/api/view-job-details?job_id="+data;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {
                Log.i("hgtviubhtdruh6r y",response.toString());



                try {


                    jobsTwoModelList = new ArrayList<>();
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject((i));
                        id = jsonObject.getString("id");
                        j_name = jsonObject.getString("job_name");
                        c_name = jsonObject.getString("company_name");
                        address = jsonObject.getString("address");
                        vacancy = jsonObject.getString("no_of_vacancy");
                        end_date = jsonObject.getString("apply_end_date");





                        Jobs_two_Model viewmodel = new Jobs_two_Model();


                        viewmodel.setTxt(j_name);
                        viewmodel.setTxt1(c_name);
                        viewmodel.setTxt2(address);
                        viewmodel.setTxt3(vacancy);
                        viewmodel.setTxt4(end_date);



                        jobsTwoModelList.add(viewmodel);

                    }





                    recyclerView.setLayoutManager(new LinearLayoutManager(Closing_All.this));

                    adapter =  new Jobs_two_Adapter(Closing_All.this,jobsTwoModelList);
                    recyclerView.setAdapter(adapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Closing_All.this) {
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
                params.put("Accept", "application/json");
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Closing_All.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Closing_All.this);
        requestQueue.add(jsonObjectRequest);





    }
}