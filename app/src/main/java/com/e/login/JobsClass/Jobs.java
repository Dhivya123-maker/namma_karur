package com.e.login.JobsClass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.EnquiryFragment;
import com.e.login.Help_Class.Helpline;
import com.e.login.HomeClass.BannerModel;
import com.e.login.HomeClass.Fragment_Home;
import com.e.login.HomeClass.Home;
import com.e.login.HomeClass.Slider_Top_Adapter;
import com.e.login.QrCodeFragment;
import com.e.login.R;
import com.e.login.ShopscreenClass.ShopsScreenFragment;
import com.e.login.info_Class.InformationFragment;
import com.e.login.utils.PreferenceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jobs extends AppCompatActivity  implements Jobs_Adapter.OnItemClickListener {


    Button add;
    List<Jobs_Model> jobsModelList;
    Jobs_Adapter adapter;
    List<Jobs_One_Model> jobsOneModelList;
    Jobs_One_Adapter adapter1;
    List<Jobs_two_Model> jobsTwoModelList;
    Jobs_two_Adapter adapter2;
    String data,data1;
    TextView closing_view,all_view;
    String id,name,image,j_name,c_name,vacancy,address,end_date,category_id;
    RecyclerView jobs_banner;
    List<BannerModel> bannerModelList;
    Slider_Top_Adapter slider_top_adapter;
    LinearLayout lnr;

    RecyclerView recyclerView,all_recycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        closing_view = findViewById(R.id.closing_view);
        all_view = findViewById(R.id.all_view);

        lnr = findViewById(R.id.more_linear);

        lnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),View_MoreJobs.class);
                view.getContext().startActivity(intent);

            }
        });

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_jobs);
        btnNav.setOnNavigationItemSelectedListener(navListener);


        jobs_banner = findViewById(R.id.jobs_banner);

        Ban();
        closing_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Jobs.this, Closing_All.class);
                intent1.putExtra("id",id);
                intent1.putExtra("cat1","closing_jobs");
                startActivity(intent1);



            }
        });

        all_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Jobs.this,Closing_All.class);
                intent.putExtra("id",id);
                intent.putExtra("cat1","all_jobs");
                startActivity(intent);

            }
        });


        Intent intent = getIntent();
        data = intent.getStringExtra("cat");
        data1 = intent.getStringExtra("id");

         recyclerView =findViewById(R.id.category_job_screen);

      all_recycle =findViewById(R.id.category_job1_screen);


        String JSON_URL = "http://nk.inevitabletech.email/public/api/job-home-page";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {




                try {

                    jobsModelList = new ArrayList<>();
                    jobsOneModelList = new ArrayList<>();
                    jobsTwoModelList = new ArrayList<>();

                    JSONObject jsonObject = response.getJSONObject("data");
                    JSONArray jsonArray = jsonObject.getJSONArray("categories");
                    JSONArray jsonArray1 = jsonObject.getJSONArray("closing");
                    JSONArray jsonArray2 = jsonObject.getJSONArray("all_jobs");




                    for (int i = 0; i < 3; i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject((i));

                        id = jsonObject1.getString("id");
                        name = jsonObject1.getString("name");
                        image = jsonObject1.getString("image");


                        Log.i("1243w7153871",jsonObject1.toString());



                        Jobs_Model viewmodel = new Jobs_Model();


                        viewmodel.setTxt(name);
                        viewmodel.setImg(image);
                        viewmodel.setId(id);


                        jobsModelList.add(viewmodel);
                    }


                    recyclerView.setLayoutManager(new LinearLayoutManager(Jobs.this));

                    adapter =  new Jobs_Adapter(Jobs.this,jobsModelList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Jobs.this, LinearLayoutManager.HORIZONTAL, false));
                    adapter.setOnItemClickListener(Jobs.this::onItemClick);




                    for (int i = 0; i < jsonArray1.length(); i++) {
                        JSONObject jsonObject3 = jsonArray1.getJSONObject((i));

                        id = jsonObject3.getString("id");
                        j_name = jsonObject3.getString("job_name");
                        c_name = jsonObject3.getString("company_name");
                        address = jsonObject3.getString("address");
                        vacancy = jsonObject3.getString("no_of_vacancy");
                        end_date = jsonObject3.getString("apply_end_date");


                        Jobs_two_Model viewmodel = new Jobs_two_Model();


                        viewmodel.setTxt(j_name);
                        viewmodel.setTxt1(c_name);
                        viewmodel.setTxt2(address);
                        viewmodel.setTxt3(vacancy);
                        viewmodel.setTxt4(end_date);
                        viewmodel.setId(id);



                        jobsTwoModelList.add(viewmodel);
                    }


                    all_recycle.setLayoutManager(new LinearLayoutManager(Jobs.this));

                    adapter2 =  new Jobs_two_Adapter(Jobs.this,jobsTwoModelList);
                    all_recycle.setAdapter(adapter2);
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(Jobs.this) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    all_recycle.setLayoutManager(linearLayoutManager1);




                    for (int i = 0; i < jsonArray2.length(); i++) {
                        JSONObject jsonObject2 = jsonArray2.getJSONObject((i));

                        id = jsonObject2.getString("id");
                        j_name = jsonObject2.getString("job_name");
                        c_name = jsonObject2.getString("company_name");
                        address = jsonObject2.getString("address");
                        vacancy = jsonObject2.getString("no_of_vacancy");
                        end_date = jsonObject2.getString("apply_end_date");
                        category_id = jsonObject2.getString("category_id");


                        Jobs_two_Model viewmodel = new Jobs_two_Model();


                        viewmodel.setTxt(j_name);
                        viewmodel.setTxt1(c_name);
                        viewmodel.setTxt2(address);
                        viewmodel.setTxt3(vacancy);
                        viewmodel.setTxt4(end_date);
                        viewmodel.setId(id);
                        viewmodel.setCat_id(category_id);



                        jobsTwoModelList.add(viewmodel);
                    }


                    all_recycle.setLayoutManager(new LinearLayoutManager(Jobs.this));

                    adapter2 =  new Jobs_two_Adapter(Jobs.this,jobsTwoModelList);
                    all_recycle.setAdapter(adapter2);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Jobs.this) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    all_recycle.setLayoutManager(linearLayoutManager);






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
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Jobs.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Jobs.this);
        requestQueue.add(jsonObjectRequest);








        add = findViewById(R.id.add_btn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Jobs.this, Add.class);
                intent.putExtra("id",id);
                intent.putExtra("category_id",category_id);
                startActivity(intent);
            }
        });


    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            Fragment fragment = null;

            switch (id) {
                case R.id.nav_home:
                    Intent intent = new Intent(Jobs.this, Home.class);
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

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).commit();

            return true;
        }
    };

    public void Ban(){

        String url = "http://nk.inevitabletech.email/public/api/display-banner?banner_type=JobBanner&banner_category_id="+data1;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {

                try {
                    bannerModelList = new ArrayList<>();
                    JSONArray jsonArray = response.getJSONArray("data");


                    for(int i=0;i< jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String banner_type_id = jsonObject.getString("banner_type_id");
                        String banner_type = jsonObject.getString("banner_type");
                        String banner_category_id = jsonObject.getString("banner_category_id");
                        String banner_url = jsonObject.getString("banner_url");
                        String order_no = jsonObject.getString("order_no");

                        String img = jsonObject.getString("image");
                        String v_count = jsonObject.getString("view_count");




                        BannerModel bannerModel = new BannerModel();
                        bannerModel.setImg(img);

                        bannerModelList.add(bannerModel);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                jobs_banner.setLayoutManager(new LinearLayoutManager(Jobs.this));
                slider_top_adapter =  new Slider_Top_Adapter(Jobs.this,bannerModelList);
                jobs_banner.setAdapter(slider_top_adapter);
                jobs_banner.setLayoutManager(new LinearLayoutManager(Jobs.this, LinearLayoutManager.HORIZONTAL, false));


                final int interval = 3000;
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    int count =0;
                    @Override
                    public void run() {

                        if(count< bannerModelList.size()){
                            jobs_banner.scrollToPosition(count++);
                            handler.postDelayed(this,interval);

                        }
                        if(count== bannerModelList.size()){

                            count = 0;

                        }
                    }
                };
                handler.postDelayed(runnable,interval);


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
                params.put("Authorization", "Bearer "+PreferenceUtils.getToken(Jobs.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Jobs.this);
        requestQueue.add(jsonObjectRequest);


    }


    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(Jobs.this,Closing_All.class);
        intent.putExtra("cat1","category");
        intent.putExtra("id",id);
        startActivity(intent);

    }
}