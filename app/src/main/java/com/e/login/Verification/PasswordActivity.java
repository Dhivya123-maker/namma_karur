package com.e.login.Verification;

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
import com.e.login.LoginActivity;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class PasswordActivity extends AppCompatActivity {
    Button save;
    ImageView back;
    EditText new_pass,cfm_pass;
    String New,Confirm;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        save = findViewById(R.id.save_forget);
        back =findViewById(R.id.back_password);

        new_pass = findViewById(R.id.new_pass_forget);
        cfm_pass = findViewById(R.id.confirm_pass_forget);

        Intent intent = getIntent();
        id = intent.getStringExtra("user_id");



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();
//                Intent i = new Intent(PasswordActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PasswordActivity.this, Mobile_verification.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });


    }
    public void change(){

        New = new_pass.getText().toString();
        Confirm = cfm_pass.getText().toString();
        String url = "http://nk.inevitabletech.email/public/api/update-password";

        JSONObject jsonBody = new JSONObject();


        try {

            jsonBody.put("password", New);
            jsonBody.put("user_id", id);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    try {
                        String Success = response.getString("success");
                        String msg = response.getString("message");




                        if (Success.equals("true")) {

                            Toast.makeText(PasswordActivity.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PasswordActivity.this, LoginActivity.class);

                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);




                        } else {

                            Toast.makeText(PasswordActivity.this, msg, Toast.LENGTH_SHORT).show();


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




                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Accept","application/json");
                    params.put("Authorization", "Bearer " + PreferenceUtils.getToken(PasswordActivity.this));


                    return params;
                }

            };

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(PasswordActivity.this);
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}