package com.e.login.JobsClass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
import com.e.login.EnquiryFragment;
import com.e.login.Help_Class.Helpline;
import com.e.login.HomeClass.Fragment_Home;
import com.e.login.HomeClass.Home;
import com.e.login.QrCodeFragment;
import com.e.login.R;
import com.e.login.info_Class.InformationFragment;
import com.e.login.utils.PreferenceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
    String id,name,image,j_name,c_name,vacancy,address,end_date,category_id;
    String data,data1,data2;
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
        data2 = intent.getStringExtra("cat_id");





        if(data1.equals("closing_jobs")){
            String url = api+"view-closing-full-page";
            all_close(url);
           break_txt.setText("Closing Jobs");
        }
//        else if(data1.equals("all_jobs")){
//            String url = api+"view-job-details?job_id="+data;
//            all_close(url);
//            break_txt.setText("All Jobs");
//
//        }
        else if(data1.equals("category")){
            String url = api+"category-Filter-Job?category_id="+data2;
            Log.i("kjsagrfieuwrg",url);
            all_cls(url);
            break_txt.setText("Categories");
        }

        recyclerView =findViewById(R.id.closing_all);

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_shops);
        btnNav.setOnNavigationItemSelectedListener(navListener);


    }
    public void all_close( String url){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



                Log.i("nhdfldshog",response.toString());

                try {


                    jobsTwoModelList = new ArrayList<>();

                    JSONArray jsonArray = response.getJSONArray("data");




                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject((i));

                        id = jsonObject2.getString("id");
                        j_name = jsonObject2.getString("job_name");
                        c_name = jsonObject2.getString("company_name");
                        address = jsonObject2.getString("address");
                        vacancy = jsonObject2.getString("no_of_vacancy");
                        end_date = jsonObject2.getString("apply_end_date");

                        Jobs_two_Model viewmodel = new Jobs_two_Model();


                        viewmodel.setTxt(j_name);
                        viewmodel.setTxt1(c_name);
                        viewmodel.setTxt2(address);
                        viewmodel.setTxt3(vacancy);
                        viewmodel.setTxt4(end_date);
                        viewmodel.setCat_id(id);



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


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


                Log.i("jhwidhtowie",response.toString());


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
                        category_id = jsonObject.getString("category_id");



                        Jobs_two_Model viewmodel = new Jobs_two_Model();


                        viewmodel.setTxt(j_name);
                        viewmodel.setTxt1(c_name);
                        viewmodel.setTxt2(address);
                        viewmodel.setTxt3(vacancy);
                        viewmodel.setTxt4(end_date);
//                        viewmodel.setCat_id(category_id);


                        jobsTwoModelList.add(viewmodel);
                        Log.i("jonsmodelltis",jsonObject.toString());

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
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            Fragment fragment = null;

            switch (id) {
                case R.id.nav_home:
                    Intent intent = new Intent(Closing_All.this, Home.class);
                    startActivity(intent);
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

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();

            return true;
        }
    };

}