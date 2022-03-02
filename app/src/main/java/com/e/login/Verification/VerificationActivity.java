package com.e.login.Verification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.e.login.LoginActivity;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VerificationActivity extends AppCompatActivity {
    Button send;
    ImageView back;
    String data,data1;
    String data2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification_activity);

        send = findViewById(R.id.send);
        back = findViewById(R.id.back_btn);
        Intent intent = getIntent();
        data = intent.getStringExtra("user_name");
        data1 = intent.getStringExtra("email");
        data2 = intent.getStringExtra("phone");

        Toast.makeText(VerificationActivity.this, data1, Toast.LENGTH_SHORT).show();
        Toast.makeText(VerificationActivity.this, data2, Toast.LENGTH_SHORT).show();


//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forget();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VerificationActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });


    }
    public  void forget(){

        String JSON_URL = "http://nk.inevitabletech.email/public/api/forget-password?user_name="+data2;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


                Log.i("0000000",response.toString());
                Toast.makeText(VerificationActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                try {



                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    if(Success.equals("true")){
                        Toast.makeText(VerificationActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(VerificationActivity.this, Mobile_verification.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        i.putExtra("user_name",data);
//                        startActivity(i);

                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(VerificationActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                Log.i("jkgfuieygt98i7u",error.toString());

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

                params.put("Accept","application/json");
               // params.put("Authorization", "Bearer " + PreferenceUtils.getToken(VerificationActivity.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(VerificationActivity.this);
        requestQueue.add(jsonObjectRequest);

    }

    }
