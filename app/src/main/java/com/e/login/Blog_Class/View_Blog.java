package com.e.login.Blog_Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.e.login.NewsClass.NewsOneModel;


import com.e.login.NewsClass.View_Breaking;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

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




    }
    public  void Blog(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


//                Log.i("00000001111",response.toString());
//                Toast.makeText(View_Breaking.this, response.toString(), Toast.LENGTH_SHORT).show();

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
                // params.put("Accept", "application/json");
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(View_Blog.this));
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken1(View_Blog.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(View_Blog.this);
        requestQueue.add(jsonObjectRequest);

    }
}