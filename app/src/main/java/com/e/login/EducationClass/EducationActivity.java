package com.e.login.EducationClass;

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
import com.e.login.R;
import com.e.login.Services_Class.ServiceAdapter;
import com.e.login.Services_Class.ServicesModel;
import com.e.login.Services_Class.Services_Activity;
import com.e.login.ShopscreenClass.Slidershop_Top_Adapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EducationActivity extends AppCompatActivity implements EducationAdapter.OnItemClickListener {

    List<EducationModel> educationModelList;
    EducationAdapter adapter;
    String data,data1;
    String id,name,image,view_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);




        Intent intent = getIntent();
        data= intent.getStringExtra("token");
        data1 = intent.getStringExtra("id");


        //RequestQueue queue = Volley.newRequestQueue(ShopScreen_Class.this);
        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-education-category-list";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {

//
//                Log.i("0000000",response.toString());
//                Toast.makeText(EducationActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                try {


                    JSONArray data = response.getJSONArray("data");


                    JSONObject jsonObject = data.getJSONObject(0);
                    id = jsonObject.getString("id");
                    name = jsonObject.getString("name");
                    image = jsonObject.getString("image");
                    view_count = jsonObject.getString("view_count");





                    Log.i("789",id);
                    Log.i("001",name);
                    Log.i("002",image);
                    Log.i("003",view_count);

//                    Log.i("456",data.toString());
//                    Toast.makeText(EducationActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
//
//

                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    if(Success.equals("true")){
//                        Log.i("123",msg);
//                        Toast.makeText(EducationActivity.this, msg, Toast.LENGTH_SHORT).show();


                        educationModelList = new ArrayList<>();

                        RecyclerView recyclerView =findViewById(R.id.education_screen);


                        for (int i = 0; i < 4; i++) {

                            EducationModel viewmodel = new EducationModel();


                            viewmodel.setText(name);
                            viewmodel.setImage(image);
                            viewmodel.setImage1("2");
                            viewmodel.setText_one(view_count + " Views");

                            educationModelList.add(viewmodel);

                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(EducationActivity.this));

                        adapter =  new EducationAdapter(EducationActivity.this,educationModelList);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(EducationActivity.this);





                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(EducationActivity.this, msg, Toast.LENGTH_SHORT).show();
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

                params.put("Authorization", "Bearer " + data);
                // params.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWUzNWRjYjg2ZjdjZGUwZTFmOTA5ZmM5YWQwMDdjZGI3ZTE1MzZkNjBhMTEyOTE2ZmQwYmQ0NjE5NzA5ZGYyNTQ3ZDQ5MTFkYzFiNmZkZDUiLCJpYXQiOjE2NDQyMjc1NzYuOTM5ODYyLCJuYmYiOjE2NDQyMjc1NzYuOTM5ODY3LCJleHAiOjE2NzU3NjM1NzYuOTMzMDM2LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.ggB8UvdkfGXmcnlz3KMShC00i-IEhJhq9UwYEq4Oagb73MxNm2WvllC_STJe2wD3FOZnpiYVul_crgERXcxh7C2LHK3UKLmsRSQfxSHHUs1nACk-KFalrcx-llruus8JYwTIjbWccyPWTljJI28aKlBApgfqivUEX0FveiE_LiJQpqSmpiMyojNSlJgN-ofZQc4vuHLdWUtNs-uTRjVpPyz9xw1zEVsPoy3EyVIZ321wlG9ZHGBzUTihuOEHpg0qsCOz_6dJOhQ4CQltWBrg6SJn0_QJ7qBaiMAITQbou2ebemuh945uapuqUCXJVbdFzsMTU2B-JOYoq2G2FrTdaxs_vxCO4ZENoPKFM1Vv-T1HPNnLeAv3Nsuhil5ou-2-uCHsn0tWsPn4zknlwIOulJNs8FbFDcmOG7Hqb8CwlZ-ihp5garS5QPcZxNvC5Qcay6Vijmq93snrR5rgPlq_hW-VFyOm7ZJKkv7uLIfJR529U310wP88Dv68FoOpmlpauO3iuyXt8qwhd_TIwbQM_EanLgz5jWsqtcSsTeMvVpdM8SL-tl_G2b-wjViP4vKqvgiSExZquMahW5yYUdTRN1vtlZ5U0jiQwGMAhKNs45AGgfHcXw68hAigKUQ_qbV7IAIwlun0T1fDvnbfU7tBeTRuX1yJVISTC0k-k4_H8lM");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EducationActivity.this);
        requestQueue.add(jsonObjectRequest);

//
//

    }

    @Override
    public void onItemClick(int position) {
        Intent intent= new Intent(EducationActivity.this,Education_One_Activity.class);
        intent.putExtra("id",data1);
        intent.putExtra("token",data);
        startActivity(intent);



    }
}