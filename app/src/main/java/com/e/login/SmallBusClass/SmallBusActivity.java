package com.e.login.SmallBusClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.BusTimeClass.BusTimeAdapter;
import com.e.login.BusTimeClass.BusTimeModel;
import com.e.login.BusTimeClass.Bus_TimeActivity;
import com.e.login.R;
import com.e.login.ShopscreenClass.ShopScreenAdapter;
import com.e.login.ShopscreenClass.ShopScreenModel;
import com.e.login.ShopscreenClass.ShopsScreenFragment;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmallBusActivity extends AppCompatActivity {

    List<SmallBusModel> smallBusModelList;
    SmallBusAdapter adapter;
    RecyclerView recyclerView;
    String b_id = null;
    String id=null,name=null,image=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_bus);





        recyclerView =findViewById(R.id.small_bus_screen);

        Intent i = getIntent();
        b_id = i.getStringExtra("b_id");
        small_bus();


    }
    public void small_bus(){



        String url = "http://nk.inevitabletech.email/public/api/get-bus-time-sub-category-list?category_id="+b_id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {




                try {
                    JSONArray res = response.getJSONArray("data");

                    smallBusModelList = new ArrayList<>();


                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);


                        id = jsonObject.getString("id");
                        image = jsonObject.getString("image");
                        name = jsonObject.getString("name");



                        SmallBusModel viewmodel = new SmallBusModel();



                        viewmodel.setImage(image);
                        viewmodel.setText(name);

                        smallBusModelList.add(viewmodel);



                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(SmallBusActivity.this));
                adapter =  new SmallBusAdapter(SmallBusActivity.this,smallBusModelList);
                recyclerView.setAdapter(adapter);



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

                params.put("Authorization", "Bearer  " +PreferenceUtils.getToken(SmallBusActivity.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(SmallBusActivity.this);
        requestQueue.add(jsonObjectRequest);

    }}