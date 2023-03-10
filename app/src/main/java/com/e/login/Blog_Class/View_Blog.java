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
import com.e.login.NewsClass.NewsOneModel;


import com.e.login.NewsClass.View_Breaking;
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

public class View_Blog extends AppCompatActivity {
    RecyclerView recyclerView;
    String data;
    TextView news;
    String api;
    List<Blog_One_Model> blogOneModelList;
    Blog_One_Adapter adapter;
    String id,img,desc,link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_blog);

        blogOneModelList = new ArrayList<>();
        recyclerView=findViewById(R.id.breaking_view1);

        news = findViewById(R.id.break_txt1);

        Api a = new Api();
        api = a.getBASE_URL();

        Intent intent = getIntent();
        data = intent.getStringExtra("cat1");

        if(data.equals("Latest")){
            String url = api+"get-latest-blog-list";
            Blog(url);
            news.setText("Latest");
        }
        else if(data.equals("popular")){
            String url = api+"get-popular-blog-list";
            Blog(url);
            news.setText("Popular");

        }

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_shops);
        btnNav.setOnNavigationItemSelectedListener(navListener);


    }
    public  void Blog(String url){
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
                            desc = jsonObject.getString("description");
                            link = jsonObject.getString("link");



                           Blog_One_Model viewmodel = new Blog_One_Model();


                            viewmodel.setImg(img);
                            viewmodel.setTxt(desc);
                            viewmodel.setTxt1("Read more");
                            viewmodel.setId(id);
                            viewmodel.setLink(link);


                            blogOneModelList.add(viewmodel);

                        }
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(View_Blog.this, 2);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setLayoutManager(new LinearLayoutManager(View_Blog.this));
                        adapter =  new Blog_One_Adapter(View_Blog.this,blogOneModelList);
                        recyclerView.setAdapter(adapter);




                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(View_Blog.this, msg, Toast.LENGTH_SHORT).show();
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

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(View_Blog.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(View_Blog.this);
        requestQueue.add(jsonObjectRequest);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            Fragment fragment = null;

            switch (id) {
                case R.id.nav_home:
                    Intent intent = new Intent(View_Blog.this, Home.class);
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