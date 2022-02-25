package com.e.login.BusTimeClass;

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
import com.e.login.GovtClass.GovtActivity;
import com.e.login.GovtClass.GovtAdapter;
import com.e.login.GovtClass.GovtModel;
import com.e.login.R;
import com.e.login.ShopClass.ShopClassAdapter;
import com.e.login.ShopClass.ShopModel;
import com.e.login.ShopClass.ShopScreen_Class;
import com.e.login.SmallBusClass.SmallBusActivity;
import com.e.login.fragment_dialog.BottomSheetFragment_filter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bus_TimeActivity extends AppCompatActivity {

    List<BusTimeModel> busTimeModelList;
    BusTimeAdapter adapter;
   TextView bus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_time);


        bus = findViewById(R.id.bus);
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bus_TimeActivity.this, SmallBusActivity.class);
                startActivity(intent);
            }
        });

                    bus();

//
//        busTimeModelList = new ArrayList<>();
//
//        RecyclerView recyclerView =findViewById(R.id.bustime_screen);
//
//
//        for (int i = 0; i < 2; i++) {
//
//            BusTimeModel viewmodel = new BusTimeModel();
//
//
//
//            viewmodel.setImage("1");
//
//            viewmodel.setText_one("S1(Small Bus)");
//            viewmodel.setText_two("Karur - Puliyur");
//
//
//            busTimeModelList.add(viewmodel);
//
//        }
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(Bus_TimeActivity.this));
//
//        adapter =  new BusTimeAdapter(Bus_TimeActivity.this,busTimeModelList);
//        recyclerView.setAdapter(adapter);
//
//


    }

    public void bus(){
        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-bus-time-category-list";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


                Log.i("00000001111",response.toString());
                Toast.makeText(Bus_TimeActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                try {




                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    if(Success.equals("true")){
                        Log.i("123",msg);
                        Toast.makeText(Bus_TimeActivity.this, msg, Toast.LENGTH_SHORT).show();










                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(Bus_TimeActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                params.put("Accept", "application/json");
                params.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNDRjOGQyOWFhMzdmYTk1MTE2OTAzNWI3MzcwZTk2Njk4MmVmMmQzZTcxZjI3MjRkNjViYTI0YTdhMTE1MzMxM2QyYmFjNmM4YTcwNDJjYzQiLCJpYXQiOjE2NDQ1NTc5MDIuMTU3NTI0LCJuYmYiOjE2NDQ1NTc5MDIuMTU3NTI4LCJleHAiOjE2NzYwOTM5MDIuMTUxOTIzLCJzdWIiOiI1NiIsInNjb3BlcyI6W119.xhAjKhtWGprH1hAHckkYdXpjl7MmITLytdcawHOQ6h_6MYO8-CZpavBknPbeJXLf8G51LbypFf9VOKML7kKmmgqWi6SB5MxhtUWs7JgszMDi2B8URqj1-hXBQqOOOaR5BJzaXXa37T4Br6NeyvVz_U9z-9pxw-pQzshjSAwJCHAXMpGwMLy4IEug4npqa-Ym7ixBRt0b_VBw0tLzCw4wqDU61OgBWZqFy0J8Mwn4_OKi8XrqJxK1CzZxtwFzDAE7p5eTyOiRD8Ijb4k20AqIXkcb1Cz1BMGYrVQiDmdOtFKUmuHERTvNvF4578ES77ZBE0pvyueDgU2u5713vUeDGPjLyL1cQUXsXg8gmoIdVGl1-S0mOP-y0wTW8-PxTHQuFdsiPcVMLfVP9RQ4GzA2ehEStRJbZyOQwXe7WrS2DMTkg4FkZqMe9q7qGn7Lsuz2tNSM7OooyDxnNTORhWs8w3yDgn_KhSrN-PW7aZQs5w3pVSI4oLdOiR-vMtyPw_ofIiJQV0UFiTCvlX9ln99aVybh8HzWIrQlz96RP8127lIPfQC4-bQAKrXya0xXnfOYSLzWNja13ohdEgz0lN25bLPieW_eQyJT2_Ypk3Cy44lEsaMPndy2xvdt9EfXA_LfLXUzlwTLSCjRgxBJbO-g6bmgZPEoACxwkrVKJzVzZac");
                // params.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWUzNWRjYjg2ZjdjZGUwZTFmOTA5ZmM5YWQwMDdjZGI3ZTE1MzZkNjBhMTEyOTE2ZmQwYmQ0NjE5NzA5ZGYyNTQ3ZDQ5MTFkYzFiNmZkZDUiLCJpYXQiOjE2NDQyMjc1NzYuOTM5ODYyLCJuYmYiOjE2NDQyMjc1NzYuOTM5ODY3LCJleHAiOjE2NzU3NjM1NzYuOTMzMDM2LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.ggB8UvdkfGXmcnlz3KMShC00i-IEhJhq9UwYEq4Oagb73MxNm2WvllC_STJe2wD3FOZnpiYVul_crgERXcxh7C2LHK3UKLmsRSQfxSHHUs1nACk-KFalrcx-llruus8JYwTIjbWccyPWTljJI28aKlBApgfqivUEX0FveiE_LiJQpqSmpiMyojNSlJgN-ofZQc4vuHLdWUtNs-uTRjVpPyz9xw1zEVsPoy3EyVIZ321wlG9ZHGBzUTihuOEHpg0qsCOz_6dJOhQ4CQltWBrg6SJn0_QJ7qBaiMAITQbou2ebemuh945uapuqUCXJVbdFzsMTU2B-JOYoq2G2FrTdaxs_vxCO4ZENoPKFM1Vv-T1HPNnLeAv3Nsuhil5ou-2-uCHsn0tWsPn4zknlwIOulJNs8FbFDcmOG7Hqb8CwlZ-ihp5garS5QPcZxNvC5Qcay6Vijmq93snrR5rgPlq_hW-VFyOm7ZJKkv7uLIfJR529U310wP88Dv68FoOpmlpauO3iuyXt8qwhd_TIwbQM_EanLgz5jWsqtcSsTeMvVpdM8SL-tl_G2b-wjViP4vKqvgiSExZquMahW5yYUdTRN1vtlZ5U0jiQwGMAhKNs45AGgfHcXw68hAigKUQ_qbV7IAIwlun0T1fDvnbfU7tBeTRuX1yJVISTC0k-k4_H8lM");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Bus_TimeActivity.this);
        requestQueue.add(jsonObjectRequest);
    }
}