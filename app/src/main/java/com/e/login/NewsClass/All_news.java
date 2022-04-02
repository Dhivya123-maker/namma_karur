package com.e.login.NewsClass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
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
import java.util.Map;

public class All_news extends AppCompatActivity {

    TextView txt,txt1;
    ImageView img;
    String id,title,desc,image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_news);

        txt = findViewById(R.id.news_title);
        txt1 = findViewById(R.id.news_desc);
        img = findViewById(R.id.news_image);

        Intent intent = getIntent();
        String get_id = intent.getStringExtra("id");
//        Toast.makeText(All_news.this, get_id, Toast.LENGTH_SHORT).show();


        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_shops);
        btnNav.setOnNavigationItemSelectedListener(navListener);

        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-news-details?news_id="+get_id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



                try {




                    String Success = response.getString("success");
                    String msg = response.getString("message");






                    if(Success.equals("true")){


                        for (int i = 0; i < response.length(); i++) {

                            JSONObject jsonObject = response.getJSONObject("data");

                            id = jsonObject.getString("id");
                            image = jsonObject.getString("image");
                            title = jsonObject.getString("title");
                            desc = jsonObject.getString("description");

                            txt.setText(title);
                            txt1.setText(desc);




                        }







                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(All_news.this, msg, Toast.LENGTH_SHORT).show();
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
                 params.put("Accept", "application/json");
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(All_news.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(All_news.this);
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


}