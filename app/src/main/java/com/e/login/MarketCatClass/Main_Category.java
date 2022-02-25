package com.e.login.MarketCatClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.BusTimeClass.Bus_TimeActivity;
import com.e.login.MarketListClass.MarketActivity;
import com.e.login.R;
import com.e.login.fragment_dialog.BottomSheetFragment_filter;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main_Category extends AppCompatActivity {
    List<MarketClassModel> marketClassModelList;
   MarketClassAdapter adapter;
    TextView market;
    String data,data1,name = null,view_count =null,img = null,id = null;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_category);

        Intent intent = getIntent();
        data = intent.getStringExtra("token");
        Intent i1 = getIntent();
        data1 = i1.getStringExtra("id");


        recyclerView = findViewById(R.id.market_cat_screen);

        market = findViewById(R.id.mkt);
        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Category.this, MarketActivity.class);
                intent.putExtra("token",data);
                intent.putExtra("id",data1);
                startActivity(intent);
            }
        });




        market();
//        filter = findViewById(R.id.market_filter);
//        filter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                com.e.login.fragment_dialog.BottomSheetFragment_filter fragment = new BottomSheetFragment_filter();
//                fragment.show(getSupportFragmentManager(), TAG);
//            }
//        });

//
//        marketClassModelList = new ArrayList<>();
//
//        RecyclerView recyclerView =findViewById(R.id.market_cat_screen);
//
//
//        for (int i = 0; i < 4; i++) {
//
//            MarketClassModel viewmodel = new MarketClassModel();
//
//
//
//            viewmodel.setImage("1");
//            viewmodel.setText("Vegetables");
//
//            viewmodel.setText_one("867 views");
//            viewmodel.setImg1("2");
//
//
//          marketClassModelList.add(viewmodel);
//
//        }
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(Main_Category.this));
//
//        adapter =  new MarketClassAdapter(Main_Category.this,marketClassModelList);
//        recyclerView.setAdapter(adapter);


    }
    public void market(){
        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-market-category-list";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {




                try {




                    String Success = response.getString("success");
                    String msg = response.getString("message");



                    if(Success.equals("true")){

                        JSONArray array = response.getJSONArray("data");

                        marketClassModelList = new ArrayList<>();

                        for (int i = 0; i < array.length(); i++) {



                            JSONObject jsonObject = array.getJSONObject(i);

                            name = jsonObject.getString("name");
                            //des = jsonObject.getString("view_count");
                            img = jsonObject.getString("logo");
                            id = jsonObject.getString("id");

                            view_count = "20";
//                            Log.i("juioyfgiwuuihyoihoijsoi",name.toString());
//                            Toast.makeText(Main_Category.this, img.toString(), Toast.LENGTH_SHORT).show();

                            MarketClassModel viewmodel = new MarketClassModel();


                            viewmodel.setImg(img);
                            viewmodel.setName(name);
                            viewmodel.setView_count(view_count);
                            viewmodel.setId(id);


                            marketClassModelList.add(viewmodel);
                        }



                        recyclerView.setLayoutManager(new LinearLayoutManager(Main_Category.this));
                        adapter =  new MarketClassAdapter(Main_Category.this,marketClassModelList);
                        recyclerView.setAdapter(adapter);



                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(Main_Category.this, msg, Toast.LENGTH_SHORT).show();
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Accept", "application/json");
                params.put("Authorization", "Bearer " + PreferenceUtils.getToken(Main_Category.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Main_Category.this);
        requestQueue.add(jsonObjectRequest);
    }


}