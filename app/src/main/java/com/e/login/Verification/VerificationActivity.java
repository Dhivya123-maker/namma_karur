package com.e.login.Verification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.e.login.ShopscreenClass.ShopScreenModel;
import com.e.login.SignUpActivity;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VerificationActivity extends AppCompatActivity {
    Button send;
    ImageView back;
    String data,data1,data3;
    String data2;
    EditText verify_edit;
    String Verify;
    String id,name,email,phone,message;


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
        data3 = intent.getStringExtra("id");



        verify_edit = findViewById(R.id.verify_edit);
        Verify = verify_edit.getText().toString();

        //Toast.makeText(VerificationActivity.this, Verify, Toast.LENGTH_SHORT).show();
//        Toast.makeText(VerificationActivity.this, data2, Toast.LENGTH_SHORT).show();


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

        Verify = verify_edit.getText().toString();
        String JSON_URL = "http://nk.inevitabletech.email/public/api/forget-password?user_name="+Verify;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


//                Log.i("0000000",response.toString());
//                Toast.makeText(VerificationActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                try {


                    String Success = response.getString("success");
                    String msg = response.getString("message");



                    JSONArray jsonArray = response.getJSONArray("data");


//                    for (int i=0;i<jsonArray.length();i++){

                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(1);
                            Log.i("okfheiog",jsonObject.toString());

                            id = jsonObject.getString("id");
                            name = jsonObject.getString("name");
                            email = jsonObject.getString("email");
                            phone = jsonObject.getString("phone");




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


//                    }

                    if (Success.equals("true")){

                        Toast.makeText(VerificationActivity.this, msg, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(VerificationActivity.this,Forget_OTP.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                        intent.putExtra("phone",Verify);
                        intent.putExtra("user_id",id);
                        intent.putExtra("email",email);


                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);


                        startActivity(intent);

                    }else{
                        Toast.makeText(VerificationActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();


                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Charset charset = Charset.defaultCharset();
                String str = new String(error.networkResponse.data,charset);
//
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONObject data = jsonObject.getJSONObject("data");

                        Toast.makeText(VerificationActivity.this, data.toString(), Toast.LENGTH_SHORT).show();




                } catch (JSONException e) {
                    e.printStackTrace();
                }



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

                params.put("Content-Type", "application/json");
                params.put("Authorization", "Bearer " + PreferenceUtils.getToken(VerificationActivity.this));

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(VerificationActivity.this);
        requestQueue.add(jsonObjectRequest);

    }

    }
