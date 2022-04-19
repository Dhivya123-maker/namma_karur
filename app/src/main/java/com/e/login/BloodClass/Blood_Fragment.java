package com.e.login.BloodClass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.ChatFeature;
import com.e.login.EnquiryFragment;
import com.e.login.Help_Class.Helpline;
import com.e.login.HomeClass.Fragment_Home;
import com.e.login.QrCodeFragment;
import com.e.login.R;
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

public class Blood_Fragment extends AppCompatActivity {

    List<Blood_Model> bloodModelList;
    Blood_Adapter adapter;
    Button blood;
    RecyclerView recyclerView;
    String data = null;
    String name,mail,img;
    String id,p_name,problem,blood_grp,needed,units,hos,address,primary,secondary;
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_fragment);

        Intent intent = getIntent();
        data = intent.getStringExtra("cat");


        recyclerView = findViewById(R.id.blood_screen);

        request();

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_blood);
        btnNav.setOnNavigationItemSelectedListener(navListener);


        blood = findViewById(R.id.blood_btn);
        blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Blood_Fragment.this,Blood_One.class);
                intent.putExtra("cat",data);
                startActivity(intent);
            }
        });
    }

    private void request() {

        String url = "http://nk.inevitabletech.email/public/api/get-blood-request-list";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



                try {
                    JSONArray res = response.getJSONArray("data");

                    bloodModelList = new ArrayList<>();


                    for (int i=0;i<res.length();i++){



                        JSONObject jsonObject = res.getJSONObject(i);
                        id = jsonObject.getString("id");
                        p_name = jsonObject.getString("patient_name");
                        blood_grp = jsonObject.getString("blood_group");
                        problem = jsonObject.getString("problem");
                        needed = jsonObject.getString("needed_within");
                        units = jsonObject.getString("no_of_units");
                        hos = jsonObject.getString("hospital");
                        address = jsonObject.getString("address");
                        primary = jsonObject.getString("primary_contact");
                        secondary = jsonObject.getString("secondary_contact");

                        JSONObject  user = jsonObject.getJSONObject("user");
                        name = user.getString("name");
                        mail = user.getString("email");
                        img = user.getString("image");



                        Blood_Model viewmodel = new Blood_Model();


                        viewmodel.setImg(img);
                        viewmodel.setName(name);
                        viewmodel.setPosted(mail);
                        viewmodel.setP_name(p_name);
                        viewmodel.setB_grp(blood_grp);
                        viewmodel.setProblem(problem);
                        viewmodel.setNeed(needed);
                        viewmodel.setUnits(units);
                        viewmodel.setHospital(hos);
                        viewmodel.setC_num(primary);
                        viewmodel.setA_num(secondary);
                        viewmodel.setAddress(address);


                        bloodModelList.add(viewmodel);



                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(Blood_Fragment.this));

                    adapter =  new Blood_Adapter(Blood_Fragment.this,bloodModelList);
                    recyclerView.setAdapter(adapter);




                } catch (JSONException e) {
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

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Blood_Fragment.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Blood_Fragment.this);
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
                    fragment = new ChatFeature();
                    break;


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).commit();

            return true;
        }
    };



}