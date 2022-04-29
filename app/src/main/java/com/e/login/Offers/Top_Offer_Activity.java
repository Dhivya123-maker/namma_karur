package com.e.login.Offers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.BaseApi.Api;
import com.e.login.ChatFeature;
import com.e.login.Help_Class.Helpline;
import com.e.login.HomeClass.Home;
import com.e.login.QrCodeFragment;
import com.e.login.R;
import com.e.login.info_Class.InformationFragment;
import com.e.login.utils.PreferenceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Top_Offer_Activity extends AppCompatActivity {

  String data,data1;
  String api = null;
  RecyclerView recyclerView;
  String title,desc,img,start,end,verify,id,view_count = null;
  ImageView im;
  TextView titl,des,star,en,veri,view_cnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_offer);


        titl = findViewById(R.id.lens_title);
        des = findViewById(R.id.lens_desc);
        star = findViewById(R.id.lens_start);
        en = findViewById(R.id.lens_end);
        veri = findViewById(R.id.lens_verified);
        view_cnt = findViewById(R.id.view_cnt);
        im = findViewById(R.id.lens_img);




        Intent intent = getIntent();
        data = intent.getStringExtra("id");
        data1 = intent.getStringExtra("list");


        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_shops);
        btnNav.setOnNavigationItemSelectedListener(navListener);


        Api a = new Api();
        api = a.getBASE_URL();


        top_offer();

    }

    public void top_offer(){
        String url = api+"get-offer-details?offer_id="+data;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {




                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    id = jsonObject.getString("id");
                    title = jsonObject.getString("title");
                    desc = jsonObject.getString("description");
                    img = jsonObject.getString("image");
                    start = jsonObject.getString("start_date");
                    end = jsonObject.getString("end_date");
                    verify = jsonObject.getString("verification");
                    view_count = jsonObject.getString("view_count");


                    titl.setText(title);
                    des.setText(desc);
                    star.setText(start);
                    en.setText(end);
                    veri.setText(verify);
                    view_cnt.setText(view_count + " Views");

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

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Top_Offer_Activity.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Top_Offer_Activity.this);
        requestQueue.add(jsonObjectRequest);



    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            Fragment fragment = null;

            switch (id) {
                case R.id.nav_home:
                    Intent in=new Intent(Top_Offer_Activity.this, Home.class);
                    startActivity(in);
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