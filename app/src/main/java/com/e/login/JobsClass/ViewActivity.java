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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.EnquiryFragment;
import com.e.login.Help_Class.Helpline;
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

public class ViewActivity extends AppCompatActivity {

    String data;
    RecyclerView recyclerView;
    List<View_Activity_Model> view_activity_modelList;
    View_activity_Adapter view_activity_adapter;
    String id,j_name,comp_name,address,gender,vacancy,exp,age,salary,start,end,abt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recyclerView = findViewById(R.id.job_view);


        full_page();
        Intent intent = getIntent();
        data = intent.getStringExtra("cat_id");

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_profile);
        btnNav.setOnNavigationItemSelectedListener(navListener);
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


    public  void full_page(){
        String url = "http://nk.inevitabletech.email/public/api/view-job-details?job_id=1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {




                try {


                    view_activity_modelList = new ArrayList<>();

                    JSONObject jsonObject = response.getJSONObject("data");
                    id = jsonObject.getString("id");
                    j_name = jsonObject.getString("job_name");
                    comp_name = jsonObject.getString("company_name");
                    address = jsonObject.getString("address");
                    gender=jsonObject.getString("gender");
                    vacancy = jsonObject.getString("no_of_vacancy");
                    exp =  jsonObject.getString("experience");
                    age =  jsonObject.getString("age_limit");
                    salary =  jsonObject.getString("salary");
                    start = jsonObject.getString("apply_start_date");
                    end = jsonObject.getString("apply_end_date");
                    abt = jsonObject.getString("about_company");



                    JSONArray  jsonArray = jsonObject.getJSONArray("qualification");
                    JSONArray  jsonArray1 = jsonObject.getJSONArray("skills");





                    View_Activity_Model viewmodel = new View_Activity_Model();


                    viewmodel.setJob(j_name);
                    viewmodel.setComp(comp_name);
                    viewmodel.setAddress(address);
                    viewmodel.setGender(gender);
                    viewmodel.setVacancy(vacancy);
                    viewmodel.setExp(exp);
                    viewmodel.setAge(age);
                    viewmodel.setSalary(salary);
                    viewmodel.setStart_end(start+" to "+end);
                    viewmodel.setAbt(abt);






                    view_activity_modelList.add(viewmodel);





                    recyclerView.setLayoutManager(new LinearLayoutManager(ViewActivity.this));

                    view_activity_adapter =  new View_activity_Adapter(ViewActivity.this,view_activity_modelList);
                    recyclerView.setAdapter(view_activity_adapter);



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
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(ViewActivity.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ViewActivity.this);
        requestQueue.add(jsonObjectRequest);


    }
}