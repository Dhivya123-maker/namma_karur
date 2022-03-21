package com.e.login.Verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.Profile;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Email_Verification extends AppCompatActivity {

    Button send;
    EditText mail;
    String Mail,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        send = findViewById(R.id.send_mail);
        mail = findViewById(R.id.address);
//        Mail = mail.getText().toString();

        Intent intent = getIntent();
        id = intent.getStringExtra("id");



       send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //otp();

              change_email();

            }
        });


    }
    public void change_email() {

        Mail = mail.getText().toString();


        String url = "http://nk.inevitabletech.email/public/api/change-email";
        JSONObject jsonBody = new JSONObject();


        try {
            jsonBody.put("email", Mail);


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

//                    Log.i("0000000000000",response.toString());
//                    Toast.makeText(Profile.this, response.toString(), Toast.LENGTH_SHORT).show();


                    try {
                        String Success = response.getString("success");
                        String msg = response.getString("message");


                        if (Success.equals("true")) {
                            Toast.makeText(Email_Verification.this, msg, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Email_Verification.this,Email_OTP.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            intent.putExtra("email",Mail);
                            intent.putExtra("id",id);
                            startActivity(intent);

                            Toast.makeText(Email_Verification.this, id, Toast.LENGTH_SHORT).show();
//                            PreferenceUtils.saveid(data1,Profile.this);
//                            PreferenceUtils.saveToken(data,Profile.this);


                        } else {


                            Toast.makeText(Email_Verification.this, msg, Toast.LENGTH_SHORT).show();


                        }


                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Charset charset = Charset.defaultCharset();
                    String str = new String(error.networkResponse.data, charset);
                    Toast.makeText(Email_Verification.this, str, Toast.LENGTH_SHORT).show();


                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");
                        Toast.makeText(Email_Verification.this, data.toString(), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Email_Verification.this));
                    return params;


                }
            };

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Email_Verification.this);
            requestQueue.add(jsonObjectRequest);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}