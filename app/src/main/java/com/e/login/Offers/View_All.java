package com.e.login.Offers;

import androidx.appcompat.app.AppCompatActivity;
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

public class View_All extends AppCompatActivity {
    RecyclerView recyclerView;
    String data,data1;
    TextView offer;
    String api;
    String id,title,desc,img,view_count = null;


    List<Offer_two_model> offerTwoModelList;
    Offer_two_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);




        recyclerView=findViewById(R.id.offer_view_recycle);

        offer = findViewById(R.id.offer_view);

        Api a = new Api();
        api = a.getBASE_URL();



        Intent intent = getIntent();
        data = intent.getStringExtra("cat1");
        data1 =  intent.getStringExtra("id");


        Toast.makeText(View_All.this, data, Toast.LENGTH_SHORT).show();
        Toast.makeText(View_All.this, data1, Toast.LENGTH_SHORT).show();

        if(data.equals("top_offers")){
            String url = api+"top-offer-full-page";
            view_all(url);
            offer.setText("Top Offers");
        }
        else if(data.equals("closing_offers")){
            String url = api+"closing-offer-full-page";
            view_all(url);
            offer.setText("Closing Offers");

        }


    }

    public void view_all(String url){



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


//            Log.i("123",response.toString());
//                Toast.makeText(View_All.this, response.toString(), Toast.LENGTH_SHORT).show();

                try {



                    offerTwoModelList = new ArrayList<>();

                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    JSONArray  jsonArray = response.getJSONArray("data");

                    if(Success.equals("true")){

//                        Log.i("456",jsonArray.toString());
//                        Toast.makeText(View_All.this, jsonArray.toString(), Toast.LENGTH_SHORT).show();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);



                            id = jsonObject.getString("id");
                            img = jsonObject.getString("image");
                            title = jsonObject.getString("title");
                            desc = jsonObject.getString("description");



                            Offer_two_model viewmodel = new Offer_two_model();


                            viewmodel.setImg(img);
                            viewmodel.setTxt(title);
                            viewmodel.setTxt1(desc);
                            viewmodel.setId(id);

                            offerTwoModelList.add(viewmodel);

                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(View_All.this));


                        adapter =  new Offer_two_Adapter(View_All.this,offerTwoModelList);
                        recyclerView.setAdapter(adapter);




                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(View_All.this, msg, Toast.LENGTH_SHORT).show();
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
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(View_All.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(View_All.this);
        requestQueue.add(jsonObjectRequest);

    }
}