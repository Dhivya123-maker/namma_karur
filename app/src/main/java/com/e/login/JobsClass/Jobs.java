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
import android.view.View;
import android.widget.Button;
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
import com.e.login.Helpline;
import com.e.login.HomeClass.Fragment_Home;
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
    String id,name,image,j_name,c_name,vacancy,address,end_date;

    RecyclerView recyclerView,all_recycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        closing_view = findViewById(R.id.closing_view);
        all_view = findViewById(R.id.all_view);

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_jobs);
        btnNav.setOnNavigationItemSelectedListener(navListener);




        closing_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Jobs.this, Closing_All.class);
                intent1.putExtra("id",id);
                intent1.putExtra("cat1","closing_jobs");
                startActivity(intent1);

//                close_view();

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

         recyclerView =findViewById(R.id.category_job_screen);

      all_recycle =findViewById(R.id.category_job1_screen);


        String JSON_URL = "http://nk.inevitabletech.email/public/api/job-home-page";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {
                Log.i("hgtviuy",response.toString());



                try {

                    jobsModelList = new ArrayList<>();
                    jobsOneModelList = new ArrayList<>();
                    jobsTwoModelList = new ArrayList<>();

                    JSONObject jsonObject = response.getJSONObject("data");
                    JSONArray jsonArray = jsonObject.getJSONArray("categories");
                    JSONArray jsonArray1 = jsonObject.getJSONArray("closing");
                    JSONArray jsonArray2 = jsonObject.getJSONArray("all_jobs");




                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject((i));

                        id = jsonObject1.getString("id");
                        name = jsonObject1.getString("name");
                        image = jsonObject1.getString("image");
                        Log.i("1243w7153871",id);



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



                    for (int i = 0; i < jsonArray2.length(); i++) {
                        JSONObject jsonObject2 = jsonArray2.getJSONObject((i));

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
                        viewmodel.setId(id);



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
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Jobs.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Jobs.this);
        requestQueue.add(jsonObjectRequest);






//
//        jobsModelList = new ArrayList<>();
//
//        RecyclerView recyclerView =findViewById(R.id.category_job_screen);

//
//        for (int i = 0; i < 4; i++) {
//
//            Jobs_Model viewmodel = new Jobs_Model();
//
//
//            viewmodel.setTxt("Central govt\njobs");
//            viewmodel.setImg("1");
//
//
//            jobsModelList.add(viewmodel);
//
//        }
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(Jobs.this));
//
//        adapter =  new Jobs_Adapter(Jobs.this,jobsModelList);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(Jobs.this, LinearLayoutManager.HORIZONTAL, false));
//
//

//
//        jobsOneModelList = new ArrayList<>();

//        RecyclerView recyclerView1 =findViewById(R.id.category_job1_screen);
//
//
//        for (int i = 0; i < 4; i++) {
//
//            Jobs_One_Model viewmodel = new Jobs_One_Model();
//
//
//          viewmodel.setImg("1");
//            viewmodel.setImg1("2");
//            viewmodel.setImg2("3");
//            viewmodel.setTxt("Marketing Executice Wanted");
//            viewmodel.setTxt1("Inevitabletech, Spk Systems");
//            viewmodel.setTxt2("Karur");
//            viewmodel.setTxt3("Vacancy");
//            viewmodel.setTxt4("3 days left");
//
//
//            jobsOneModelList.add(viewmodel);
//
//        }
//
//        recyclerView1.setLayoutManager(new LinearLayoutManager(Jobs.this));
//
//        adapter1 =  new Jobs_One_Adapter(Jobs.this,jobsOneModelList);
//        recyclerView1.setAdapter(adapter1);
//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(Jobs.this) {
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        };
//        recyclerView1.setLayoutManager(linearLayoutManager1);

//
//
//
//
//        jobsTwoModelList = new ArrayList<>();
//
//        RecyclerView recyclerView2 =findViewById(R.id.category_job2_screen);
//
//
//        for (int i = 0; i < 4; i++) {
//
//            Jobs_two_Model viewmodel = new Jobs_two_Model();
//
//
//            viewmodel.setImg("1");
//            viewmodel.setImg1("2");
//            viewmodel.setImg2("3");
//            viewmodel.setTxt("Marketing Executice Wanted");
//            viewmodel.setTxt1("Inevitabletech, Spk Systems");
//            viewmodel.setTxt2("Karur");
//            viewmodel.setTxt3("Vacancy");
//            viewmodel.setTxt4("3 days left");
//
//
//            jobsTwoModelList.add(viewmodel);
//
//        }
//
//        recyclerView2.setLayoutManager(new LinearLayoutManager(Jobs.this));
//
//        adapter2 =  new Jobs_two_Adapter(Jobs.this,jobsTwoModelList);
//        recyclerView2.setAdapter(adapter2);
//        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(Jobs.this) {
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        };
//        recyclerView2.setLayoutManager(linearLayoutManager2);


        add = findViewById(R.id.add_btn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Jobs.this, Add.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });


    }

    public  void close_view(){
        String JSON_URL = "http://nk.inevitabletech.email/public/api/view-closing-full-page";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {
                Log.i("hgtviuy",response.toString());



                try {


                    jobsOneModelList = new ArrayList<>();

                    String Success = response.getString("success");
                    String msg = response.getString("message");
                    if (Success == "true") {

                        Toast.makeText(Jobs.this, msg, Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(Jobs.this,Closing_All.class);
//                        intent.putExtra("id",id);
//                        startActivity(intent);



                    }else{

                    }




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
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Jobs.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Jobs.this);
        requestQueue.add(jsonObjectRequest);





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

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).commit();

            return true;
        }
    };

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(Jobs.this,Closing_All.class);
        intent.putExtra("cat1","category");
        intent.putExtra("id",id);
       startActivity(intent);

    }
}