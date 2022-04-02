package com.e.login.Gallery_Class;

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
import com.e.login.Blog_Class.Quotes_Catalog;
import com.e.login.Blog_Class.Quotes_Catalog_Adapter;
import com.e.login.Blog_Class.Quotes_Catalog_Model;
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

public class View_All_Cat extends AppCompatActivity {
    String data, data1;
    String api = null;
    String category_id,catalog_id,gallery_cat,img;
    RecyclerView recyclerView;
    List<Gallery_One_Model> galleryOneModelList;
    Gallery_One_Adapter adapter1;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_cat);

        Intent intent = getIntent();
        data = intent.getStringExtra("catalog_id");
        data1 = intent.getStringExtra("category_id");
        String name = intent.getStringExtra("name");


        Api a = new Api();
        api = a.getBASE_URL();


        recyclerView = findViewById(R.id.view_cat_recycle);
        txt = findViewById(R.id.v_txt);

            String url = api + "get-event-gallery-view-full?gallery_category_id=" + data1 + "&catalog_id=" + data;
            gallery_full(url);
            txt.setText(name);



//        String url = "http://nk.inevitabletech.email/public/api/get-event-gallery-view-full?gallery_category="+data1+"&catalog_id="+data;
//        Log.i("klhwqfgou3rgytuoetyu",url);


    }

    public void gallery_full(String url){

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
                            category_id = jsonObject.getString("category_id");
                            catalog_id = jsonObject.getString("catalog_id");
                            gallery_cat = jsonObject.getString("gallery_category");
                            img = jsonObject.getString("image");




                            galleryOneModelList = new ArrayList<>();

                            Gallery_One_Model viewmodel = new Gallery_One_Model();


                            viewmodel.setImg(img);

                            galleryOneModelList.add(viewmodel);

                        }
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(View_All_Cat.this, 2);
                        recyclerView.setLayoutManager(mLayoutManager);
                        adapter1 =  new Gallery_One_Adapter(View_All_Cat.this,galleryOneModelList);
                        recyclerView.setAdapter(adapter1);



                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(View_All_Cat.this, msg, Toast.LENGTH_SHORT).show();
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
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(View_All_Cat.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(View_All_Cat.this);
        requestQueue.add(jsonObjectRequest);

    }
}