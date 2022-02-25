package com.e.login.MarketListClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
import com.e.login.MarketCatClass.Main_Category;
import com.e.login.MarketCatClass.MarketClassAdapter;
import com.e.login.MarketCatClass.MarketClassModel;
import com.e.login.R;
import com.e.login.fragment_dialog.BottomSheetFragment_filter;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketActivity extends AppCompatActivity {
    List<MarketListModel> marketListModelList;
    MarketListAdapter adapter;
    LinearLayout filter;
//    public static final String TAG = "bottom_sheet";
    RecyclerView recyclerView;
    String name = null,img = null,price = null,id = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
//
//        filter = findViewById(R.id.market_list_filter);
//        filter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                com.e.login.fragment_dialog.BottomSheetFragment_filter fragment = new BottomSheetFragment_filter();
//                fragment.show(getSupportFragmentManager(), TAG);
//            }
//        });
        Intent intent = getIntent();

        id = intent.getStringExtra("id");




        recyclerView =findViewById(R.id.market_veg_screen);

        market();




    }

    public void market(){
        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-market-catalog-list?market_category_id="+id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {




                try {




                    String Success = response.getString("success");
                    String msg = response.getString("message");



                    if(Success.equals("true")){

                        JSONArray array = response.getJSONArray("data");

                        marketListModelList = new ArrayList<>();

                        for (int i = 0; i < array.length(); i++) {



                            JSONObject jsonObject = array.getJSONObject(i);

                            name = jsonObject.getString("name");
                            price = jsonObject.getString("price");
                            img = jsonObject.getString("logo");




                            MarketListModel viewmodel = new MarketListModel();

                            viewmodel.setImg(img);
                            viewmodel.setName(name);
                            viewmodel.setPrice("Rs."+price+"/kg");

                            marketListModelList.add(viewmodel);
                        }



                        recyclerView.setLayoutManager(new LinearLayoutManager(MarketActivity.this));

                        adapter =  new MarketListAdapter(MarketActivity.this,marketListModelList);
                        recyclerView.setAdapter(adapter);


                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(MarketActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();


                }


            }


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
                params.put("Authorization", "Bearer " + PreferenceUtils.getToken(MarketActivity.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MarketActivity.this);
        requestQueue.add(jsonObjectRequest);
    }


}