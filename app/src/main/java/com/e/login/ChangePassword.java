package com.e.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.HomeClass.Home;
import com.e.login.Verification.Email_OTP;
import com.e.login.Verification.VerifyActivity;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ChangePassword extends AppCompatActivity {

    Button btn;
    EditText old,new_pass,confirm_pass;
    String Old,New_pass,Confirm_pass;
    String data,data1;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Intent i = getIntent();
        data = i.getStringExtra("token");
        Intent i1 = getIntent();
        data1 = i1.getStringExtra("id");

        back = findViewById(R.id.backk);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangePassword.this,Profile.class);
                startActivity(intent);
            }
        });




        old = findViewById(R.id.old_pass);
        new_pass = findViewById(R.id.new_pass);
        confirm_pass = findViewById(R.id.confirm_passs);

        btn = findViewById(R.id.save_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Old = old.getText().toString();
              New_pass = new_pass.getText().toString();
               Confirm_pass = confirm_pass.getText().toString();



        if (New_pass.isEmpty()) {
            new_pass.setError("Please enter mail id");
            new_pass.requestFocus();
        } else if (Confirm_pass.isEmpty()) {
            confirm_pass.setError("Please enter the password");
            confirm_pass.requestFocus();
        }else if (New_pass.equals(Confirm_pass)){



            pass();

        }
        else {
            Toast.makeText(ChangePassword.this, "password doesn't match", Toast.LENGTH_SHORT).show();


        }

                //pass();
            }
        });
    }


    public  void pass(){

         Old = old.getText().toString();
         New_pass = new_pass.getText().toString();
        Confirm_pass = confirm_pass.getText().toString();


        String URL = "http://nk.inevitabletech.email/public/api/change-password";

        JSONObject jsonBody = new JSONObject();

        try {

            jsonBody.put("old_password",Old);
            jsonBody.put("password", New_pass);


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.i("0000000000000",response.toString());
                    Toast.makeText(ChangePassword.this, response.toString(), Toast.LENGTH_SHORT).show();


                    try{
                        String Success = response.getString("success");
                        String msg = response.getString("message");

                      if (Success == "true" ){

                            Toast.makeText(ChangePassword.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ChangePassword.this, Profile.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);


                        }



                        else {

                            Toast.makeText(ChangePassword.this, msg, Toast.LENGTH_SHORT).show();


                        }


                    }catch (Exception e) {
                        e.printStackTrace();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Charset charset = Charset.defaultCharset();
                    String str = new String(error.networkResponse.data, charset);

                    Toast.makeText(ChangePassword.this, str, Toast.LENGTH_SHORT).show();



                }


            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Accept","application/json");
                    params.put("Authorization","Bearer "+ PreferenceUtils.getToken(ChangePassword.this));
                 //   params.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiODY0NjNlZTcxNWNlMmZjMzdmNTFjZTU3YTkwMTJkZDQ4YTZiZjU5MWJjYWFjNzNlM2RhYjM5ZDk4NGE5ZWYzZGIwNjBiZGFkNzNjMDY1YTciLCJpYXQiOjE2NDQ0ODg4OTQuMjQ5NTQ1LCJuYmYiOjE2NDQ0ODg4OTQuMjQ5NTUxLCJleHAiOjE2NzYwMjQ4OTQuMjMzMDg0LCJzdWIiOiIzNSIsInNjb3BlcyI6W119.mkvSK7-SBsD3kgrB6VUGkJ5G-lnDMTOQd6xwtOUQQRAVvKqd9ZHVNmfaD5G5kmDZkv_cs6lKjJFoO05JZ2NtHFbZbtVh-afYAKP7HWXEBvBvQmiP7zcDnD0mNlYRWYn-gK_pExNNYjlcLM8LZ2D5hZ_G8X57nbMA4Ak1gt8VUrpnnZbrYtk_1R6OEt6uXct1IAIHUWxmebpoH1ZbaIDya8aimn4HpLxwYj5wsxbttjivYmRTZ9qSybciAB85tSsUyrD5uHC1i5m1xrqsKVupN8Zv8DVPPniluoqgidhhxm5WWoX48QQIbHOntggDRUms8VdCG1dl_YgHe1C276IDztKJzfGwHEdEYg5Vr1Yv2YkpHE49pLys0gyL5MYE0wKWPHjOJXFEetN-4yrA_XSgPJesgs6vkV5uJLcKSbt4X-puTzrHYS4lQQBybXSr5bCrWkGrCZxeeO2IspJv5ylZGONvu2CnuJYffkuwHHW-1CnFW_elOu2_eDY4dwkCGEo7MujzcyySAIq9q9dG1b5jp2nf5lGx45smM5aktFLd-kkho8w_dCSEWmW6QPd2aA_9JIpLrFWIXAw1iGQ73ySeZxPbv_SLbLtv0Wtd-B4IpI-03GklSA6qDLw0zeW7xCfofnU2BQqtKOeYfVd3KHp4ZNkF5uilf79hvQm-85_vRCM");
                    return params;
                }

            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(ChangePassword.this);
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }







    }
}