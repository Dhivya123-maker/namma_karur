package com.e.login.NewsClass;

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

public class View_Breaking extends AppCompatActivity {


    RecyclerView recyclerView;
    String data;
    TextView news;
    String api;
    String id,title,desc,img,view_count = null;

    List<NewsOneModel> newsOneModelList;
    NewsoneAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_breaking);

        newsOneModelList = new ArrayList<>();
        recyclerView=findViewById(R.id.breaking_view);

        news = findViewById(R.id.break_txt);

        Api a = new Api();
        api = a.getBASE_URL();



        Intent intent = getIntent();
        data = intent.getStringExtra("cat1");

        if(data.equals("Latest_news")){
            String url = api+"get-latest-news-list";
            view_news(url);
            news.setText("Latest News");
        }
       else if(data.equals("breaking_news")){
            String url = api+"get-breaking-list";
            view_news(url);
            news.setText("Breaking News");

        }

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_shops);
        btnNav.setOnNavigationItemSelectedListener(navListener);


    }

    public void view_news(String url){



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



                try {




                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    JSONArray jsonArray = response.getJSONArray("data");


                    if(Success.equals("true")){



                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getString("id");
                            img = jsonObject.getString("image");
//                            title = jsonObject.getString("title");
                           desc = jsonObject.getString("description");
                            view_count = jsonObject.getString("view_count");


                            NewsOneModel viewmodel = new NewsOneModel();


                            viewmodel.setImage(img);
                            viewmodel.setText(desc);
                            viewmodel.setButton("view more");
                            viewmodel.setId(id);
                           viewmodel.setText_one(view_count);

                            newsOneModelList.add(viewmodel);

                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(View_Breaking.this));


                        adapter =  new NewsoneAdapter(View_Breaking.this,newsOneModelList);
                        recyclerView.setAdapter(adapter);




                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(View_Breaking.this, msg, Toast.LENGTH_SHORT).show();
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
                // params.put("Accept", "application/json");
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(View_Breaking.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(View_Breaking.this);
        requestQueue.add(jsonObjectRequest);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            Fragment fragment = null;

            switch (id) {
                case R.id.nav_home:
                    Intent intent = new Intent(View_Breaking.this, Home.class);
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

}