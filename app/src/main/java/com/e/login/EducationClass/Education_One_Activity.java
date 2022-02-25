package com.e.login.EducationClass;

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
import com.e.login.R;
import com.e.login.Services_Class.Service_Fragment;
import com.e.login.Services_Class.Service_OneModel;
import com.e.login.Services_Class.Service_One_activity;
import com.e.login.Services_Class.Service_one_Adapter;
import com.e.login.ShopscreenClass.Slidershop_Top_Adapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Education_One_Activity extends AppCompatActivity implements Education_one_Adapter.OnItemClickListener {


    List<Education_OneModel> educationOneModelList;
    Education_one_Adapter adapter;
    SliderView sliderView;
    int[] images = {R.drawable.first_one,
            R.drawable.banner,
            R.drawable.bank_banner,
            R.drawable.banner,
            R.drawable.first_one,
    };

    LinearLayout filter;
    String data,data1,id,logo,address,open_time,close_time,rating,verified;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_one);

        filter = findViewById(R.id.filterr);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        Intent intent = getIntent();
        data= intent.getStringExtra("token");
        data1 = intent.getStringExtra("id");


        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-education-catalog-list?education_category_id=1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


//                Log.i("0000000",response.toString());
//                Toast.makeText(Education_One_Activity.this, response.toString(), Toast.LENGTH_SHORT).show();

                try {



                    JSONArray data = response.getJSONArray("data");


                    JSONObject jsonObject = data.getJSONObject(0);
                    id = jsonObject.getString("id");

                    logo = jsonObject.getString("logo");
                    address = jsonObject.getString("address");
                    open_time = jsonObject.getString("open_time");
                    close_time = jsonObject.getString("close_time");
                    rating = jsonObject.getString("rating");
                    verified = jsonObject.getString("verified");






                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    if(Success.equals("true")){
//                        Log.i("123",msg);
//                        Toast.makeText(Education_One_Activity.this, msg, Toast.LENGTH_SHORT).show();

                        sliderView = findViewById(R.id.slider_edu);
                        Slidershop_Top_Adapter sliderAdapter = new Slidershop_Top_Adapter(images);

                        sliderView.setSliderAdapter(sliderAdapter);
                        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
                        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
                        sliderView.startAutoCycle();



                        educationOneModelList = new ArrayList<>();

                        RecyclerView recyclerView =findViewById(R.id.education_screen_fragment);


                        for (int i = 0; i < 3; i++) {

                            Education_OneModel viewmodel = new Education_OneModel();

                            viewmodel.setText("title");
                            viewmodel.setImage(logo);
                            viewmodel.setImage1("2");
                            viewmodel.setImage2("3");
                            viewmodel.setImage3("4");
                            viewmodel.setText_one(address);
                            viewmodel.setText_two(open_time);
                            viewmodel.setText_three(verified);
                            viewmodel.setText_four(rating);
                            educationOneModelList.add(viewmodel);

                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(Education_One_Activity.this));

                        adapter =  new Education_one_Adapter(Education_One_Activity.this,educationOneModelList);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(Education_One_Activity.this);



                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(Education_One_Activity.this, msg, Toast.LENGTH_SHORT).show();
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

                // params.put("Authorization","Bearer  eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNDFkNTgzOTFjNzU1YWExNTg3NDI3ZmU1YmM1ZTFlYWY3OWI0N2Q5ZGE4MWI1NzJhYTQxZjU5ZWM5YTVmZWRlMGIxMDkzNDI5ZTcwMmM3YWEiLCJpYXQiOjE2NDQ2Njg3NzMuODczNjYxLCJuYmYiOjE2NDQ2Njg3NzMuODczNjY4LCJleHAiOjE2NzYyMDQ3NzMuODY2NDEyLCJzdWIiOiIyNSIsInNjb3BlcyI6W119.Qv9HGBE2XYouZw8BEoFjw7-yPK6CsDyELyYKmIr87goixhTEfN2mYJFD2JQAdnDvWZuOXhqJdu7iderz14x3nlcUcc7XT1fMnETCqa4AhbW4XJA7_AFtxkyyeAGLXLsJQ_bgChKvnasM6RATLMD4yGKtylXIuqJdh9WeFAZaxwdfj6DrUb0xn2wEu3LzdjM1AexL6nKrA4Ye60HEpT3lohvG4j-vJrG0fxm3MTy3CKm5nu1hZ-OnES73VSLv07AueqEJKClyrE4edufyj4SVQNhDSWp4YiFTt7VVCfUL2AQDFIIGi-QwBBl-qeozMkvHmB5VGM70ivCQez0rKsGg0nqpRxGM80Gwzfgp51VldPXWmTH3hVV-vIFiAHAraF3uliLJ5pPLX3oPo2QxUqhOILKtT_pRhQU3psBww9OiAcTLb5pEdxbbia2m-sZBziOk-7fedUdb5IlUItS4r9bmOegRWm2mRU2TuYaO1U4mMHA6jgVp99P-JrlgLCZyxU-aEpP5BzcnlqCVl2ZE_PyFm62wqvEga74ZNZ-I-qeCU0Hm4nYRfzKZnPjW4wQAg5WieOnRU-9dOVrlHZR3RTz39EmujqcKol93MOMdHrXsiMmviweGzpfPYM1i6rLfkXutYzDRHZmKWWGTz5hys6NodP348cd9XtlwdTmCtgl95Qg");
                // params.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWUzNWRjYjg2ZjdjZGUwZTFmOTA5ZmM5YWQwMDdjZGI3ZTE1MzZkNjBhMTEyOTE2ZmQwYmQ0NjE5NzA5ZGYyNTQ3ZDQ5MTFkYzFiNmZkZDUiLCJpYXQiOjE2NDQyMjc1NzYuOTM5ODYyLCJuYmYiOjE2NDQyMjc1NzYuOTM5ODY3LCJleHAiOjE2NzU3NjM1NzYuOTMzMDM2LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.ggB8UvdkfGXmcnlz3KMShC00i-IEhJhq9UwYEq4Oagb73MxNm2WvllC_STJe2wD3FOZnpiYVul_crgERXcxh7C2LHK3UKLmsRSQfxSHHUs1nACk-KFalrcx-llruus8JYwTIjbWccyPWTljJI28aKlBApgfqivUEX0FveiE_LiJQpqSmpiMyojNSlJgN-ofZQc4vuHLdWUtNs-uTRjVpPyz9xw1zEVsPoy3EyVIZ321wlG9ZHGBzUTihuOEHpg0qsCOz_6dJOhQ4CQltWBrg6SJn0_QJ7qBaiMAITQbou2ebemuh945uapuqUCXJVbdFzsMTU2B-JOYoq2G2FrTdaxs_vxCO4ZENoPKFM1Vv-T1HPNnLeAv3Nsuhil5ou-2-uCHsn0tWsPn4zknlwIOulJNs8FbFDcmOG7Hqb8CwlZ-ihp5garS5QPcZxNvC5Qcay6Vijmq93snrR5rgPlq_hW-VFyOm7ZJKkv7uLIfJR529U310wP88Dv68FoOpmlpauO3iuyXt8qwhd_TIwbQM_EanLgz5jWsqtcSsTeMvVpdM8SL-tl_G2b-wjViP4vKqvgiSExZquMahW5yYUdTRN1vtlZ5U0jiQwGMAhKNs45AGgfHcXw68hAigKUQ_qbV7IAIwlun0T1fDvnbfU7tBeTRuX1yJVISTC0k-k4_H8lM");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Education_One_Activity.this);
        requestQueue.add(jsonObjectRequest);










    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(Education_One_Activity.this,Education_Fragment.class);

        intent.putExtra("id",data1);
        intent.putExtra("token",data);


        startActivity(intent);

    }
}