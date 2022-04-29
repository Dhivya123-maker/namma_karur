package com.e.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

import org.json.JSONArray;
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
    TextView er1,er2,er3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Intent i = getIntent();
        data = i.getStringExtra("token");
        Intent i1 = getIntent();
        data1 = i1.getStringExtra("id");

        er1 = findViewById(R.id.error1);
        er2 = findViewById(R.id.error2);


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
//        confirm_pass = findViewById(R.id.confirm_passs);

        btn = findViewById(R.id.save_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Old = old.getText().toString();
              New_pass = new_pass.getText().toString();
//               Confirm_pass = confirm_pass.getText().toString();



            pass();
            er1.setVisibility(View.GONE);
            er2.setVisibility(View.GONE);


            }
        });
    }


    public  void pass(){

         Old = old.getText().toString();
         New_pass = new_pass.getText().toString();
//        Confirm_pass = confirm_pass.getText().toString();


        String URL = "http://nk.inevitabletech.email/public/api/change-password";

        JSONObject jsonBody = new JSONObject();

        try {

            jsonBody.put("old_password",Old);
            jsonBody.put("password", New_pass);


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


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

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = jsonObject1.getJSONArray("old_password");
                        er1.setText(jsonArray.getString(0));
                        er1.setVisibility(View.VISIBLE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = jsonObject1.getJSONArray("password");
                        er2.setText(jsonArray.getString(0));
                        er2.setVisibility(View.VISIBLE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        String errr = jsonObject1.getString("error");

                        er1.setText(errr);
                        er1.setVisibility(View.VISIBLE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }


            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Accept","application/json");
                    params.put("Authorization","Bearer "+ PreferenceUtils.getToken(ChangePassword.this));
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