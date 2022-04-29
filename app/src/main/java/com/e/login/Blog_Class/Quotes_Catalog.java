package com.e.login.Blog_Class;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.ChatFeature;
import com.e.login.Help_Class.Helpline;
import com.e.login.HomeClass.Fragment_Home;
import com.e.login.HomeClass.Home;
import com.e.login.JobsClass.Jobs;
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

public class Quotes_Catalog extends AppCompatActivity {

    List<Quotes_Catalog_Model> quotesCatalogModelList;
    Quotes_Catalog_Adapter adapter;
    RecyclerView recyclerView;
    String id,img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_catalog);

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigation);
        btnNav.setOnNavigationItemSelectedListener(navListener);


        Intent intent = getIntent();
        String idd = intent.getStringExtra("id");


        recyclerView = findViewById(R.id.quotes_catalog_recycle);

        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-quote-catalog-list?quote_category_id="+idd;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


                try {



                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    if(Success.equals("true")){


                        quotesCatalogModelList = new ArrayList<>();


                        JSONArray jsonArray = response.getJSONArray("data");



                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            id = jsonObject.getString("id");
                            img = jsonObject.getString("image");




                            Quotes_Catalog_Model viewmodel = new Quotes_Catalog_Model();



                            viewmodel.setImg(img);


                            quotesCatalogModelList.add(viewmodel);

                        }


                        recyclerView.setLayoutManager(new LinearLayoutManager(Quotes_Catalog.this));
                        adapter =  new Quotes_Catalog_Adapter(Quotes_Catalog.this,quotesCatalogModelList);
                        recyclerView.setAdapter(adapter);




                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(Quotes_Catalog.this, msg, Toast.LENGTH_SHORT).show();
                    }


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

                params.put("Authorization", "Bearer " + PreferenceUtils.getToken(Quotes_Catalog.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Quotes_Catalog.this);
        requestQueue.add(jsonObjectRequest);


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            Fragment fragment = null;

            switch (id) {
                case R.id.nav_home:
                    Intent intent = new Intent(Quotes_Catalog.this, Home.class);
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
                    fragment = new ChatFeature();
                    break;


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout_home, fragment).commit();

            return true;
        }
    };
}