package com.e.login.AmbulanceClass;

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
import com.e.login.R;
import com.e.login.ShopClass.ShopScreen_Class;
import com.e.login.TransportClass.TransportAdapter;
import com.e.login.TransportClass.TransportModel;
import com.e.login.TransportClass.Transport_Activity;
import com.e.login.fragment_dialog.BottomSheetFragment_filter;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ambulance extends AppCompatActivity {

    List<AmbulanceModel> ambulanceModelList;
    AmbulanceAdapter adapter;
    LinearLayout filter;
    public static final String TAG = "bottom_sheet";
    String data,data1,name=null,des = null,img = null,pri = null,sec = null;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);


       recyclerView =findViewById(R.id.hospital_screen);

        Intent intent = getIntent();
        data= intent.getStringExtra("token");
        data1 = intent.getStringExtra("id");



        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-Ambulance-list";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


//                Log.i("0000000",response.toString());
//                Toast.makeText(Ambulance.this, response.toString(), Toast.LENGTH_SHORT).show();

                try {



                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    if(Success.equals("true")){
//                        Log.i("123",msg);
//                        Toast.makeText(Ambulance.this, msg, Toast.LENGTH_SHORT).show();

                        ambulanceModelList = new ArrayList<>();


                        JSONArray jsonArray = response.getJSONArray("data");



                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            name = jsonObject.getString("name");
                            des = jsonObject.getString("description");
                            img = jsonObject.getString("image");
                            pri = jsonObject.getString("primary_number");
                            sec = jsonObject.getString("secondary_number");



                            AmbulanceModel viewmodel = new AmbulanceModel();



                            viewmodel.setImage(img);

                            viewmodel.setText_one(des);
                            viewmodel.setText(name);
                            viewmodel.setBtn(pri);
                            viewmodel.setBtn_one(sec);


                            ambulanceModelList.add(viewmodel);

                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(Ambulance.this));

                        adapter =  new AmbulanceAdapter(Ambulance.this,ambulanceModelList);
                        recyclerView.setAdapter(adapter);




                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(Ambulance.this, msg, Toast.LENGTH_SHORT).show();
                    }
//

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

                params.put("Authorization", "Bearer " + PreferenceUtils.getToken(Ambulance.this));
                params.put("Authorization", "Bearer " + PreferenceUtils.getToken1(Ambulance.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Ambulance.this);
        requestQueue.add(jsonObjectRequest);


//
//        filter = findViewById(R.id.ambulance_filter);
//        filter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                com.e.login.fragment_dialog.BottomSheetFragment_filter fragment = new BottomSheetFragment_filter();
//                fragment.show(getSupportFragmentManager(), TAG);
//            }
//        });




    }

}