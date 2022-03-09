package com.e.login.Blog_Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quotes_Catalog extends AppCompatActivity {

    List<Quotes_Catalog_Model> quotesCatalogModelList;
    Quotes_Catalog_Adapter adapter;
    RecyclerView recyclerView;
    String id,img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_catalog);



        recyclerView = findViewById(R.id.quotes_catalog_recycle);

        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-quote-catalog-list?quote_category_id=1";

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

                        quotesCatalogModelList = new ArrayList<>();


                        JSONArray jsonArray = response.getJSONArray("data");



                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            id = jsonObject.getString("id");
                            img = jsonObject.getString("image");




                            Quotes_Catalog_Model viewmodel = new Quotes_Catalog_Model();



                            viewmodel.setImg(img);


                            quotesCatalogModelList.add(viewmodel);

                        }


                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Quotes_Catalog.this, 2);
                        recyclerView.setLayoutManager(mLayoutManager);
                        adapter =  new Quotes_Catalog_Adapter(Quotes_Catalog.this,quotesCatalogModelList);
                        recyclerView.setAdapter(adapter);




                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(Quotes_Catalog.this, msg, Toast.LENGTH_SHORT).show();
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

                params.put("Authorization", "Bearer " + PreferenceUtils.getToken(Quotes_Catalog.this));
                // params.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWUzNWRjYjg2ZjdjZGUwZTFmOTA5ZmM5YWQwMDdjZGI3ZTE1MzZkNjBhMTEyOTE2ZmQwYmQ0NjE5NzA5ZGYyNTQ3ZDQ5MTFkYzFiNmZkZDUiLCJpYXQiOjE2NDQyMjc1NzYuOTM5ODYyLCJuYmYiOjE2NDQyMjc1NzYuOTM5ODY3LCJleHAiOjE2NzU3NjM1NzYuOTMzMDM2LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.ggB8UvdkfGXmcnlz3KMShC00i-IEhJhq9UwYEq4Oagb73MxNm2WvllC_STJe2wD3FOZnpiYVul_crgERXcxh7C2LHK3UKLmsRSQfxSHHUs1nACk-KFalrcx-llruus8JYwTIjbWccyPWTljJI28aKlBApgfqivUEX0FveiE_LiJQpqSmpiMyojNSlJgN-ofZQc4vuHLdWUtNs-uTRjVpPyz9xw1zEVsPoy3EyVIZ321wlG9ZHGBzUTihuOEHpg0qsCOz_6dJOhQ4CQltWBrg6SJn0_QJ7qBaiMAITQbou2ebemuh945uapuqUCXJVbdFzsMTU2B-JOYoq2G2FrTdaxs_vxCO4ZENoPKFM1Vv-T1HPNnLeAv3Nsuhil5ou-2-uCHsn0tWsPn4zknlwIOulJNs8FbFDcmOG7Hqb8CwlZ-ihp5garS5QPcZxNvC5Qcay6Vijmq93snrR5rgPlq_hW-VFyOm7ZJKkv7uLIfJR529U310wP88Dv68FoOpmlpauO3iuyXt8qwhd_TIwbQM_EanLgz5jWsqtcSsTeMvVpdM8SL-tl_G2b-wjViP4vKqvgiSExZquMahW5yYUdTRN1vtlZ5U0jiQwGMAhKNs45AGgfHcXw68hAigKUQ_qbV7IAIwlun0T1fDvnbfU7tBeTRuX1yJVISTC0k-k4_H8lM");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Quotes_Catalog.this);
        requestQueue.add(jsonObjectRequest);


    }
}