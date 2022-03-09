package com.e.login.Verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.e.login.Profile;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Change_Phone extends AppCompatActivity {
    Button verification;
    EditText otp1,otp2,otp3,otp4;
    String OTP1,OTP2,OTP3,OTP4;
    String data,data1,data2,data3,phone,user_id,id;
    TextView resend,mail;
    String name,email,phonee;
    private Context mContext;
    String phone_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);


        initialize();

        addTextWatcher(otp1);
        addTextWatcher(otp2);
        addTextWatcher(otp3);
        addTextWatcher(otp4);

        Intent intent = getIntent();
        phone_get = intent.getStringExtra("phone");
        user_id = intent.getStringExtra("user_id");

        mail = findViewById(R.id.maa);
        mail.setText("+91 "+phone_get);




        otp1 = findViewById(R.id.et_otp_ph1);
        otp2 = findViewById(R.id.et_otp_ph2);
        otp3 = findViewById(R.id.et_otp_ph3);
        otp4 = findViewById(R.id.et_otp_ph4);
        resend = findViewById(R.id.resend_otp_ph);
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_resend_otp();
            }
        });

        verification = findViewById(R.id.verify_button_ph);
        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone_otp();
            }
        });

    }
    public void phone_otp(){
        OTP1 = otp1.getText().toString();
        OTP2 = otp2.getText().toString();
        OTP3 = otp3.getText().toString();
        OTP4 = otp4.getText().toString();



        String URL = "http://nk.inevitabletech.email/public/api/verifyOtp";

        JSONObject jsonBody = new JSONObject();




        try {

            jsonBody.put("otp", OTP1+OTP2+OTP3+OTP4);
            jsonBody.put("user_id",user_id);






            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

//                    Log.i("000000000000",response.toString());
//
                    //Toast.makeText(Email_OTP.this, data, Toast.LENGTH_SHORT).show();

                    // Toast.makeText(VerifyActivity.this, response.toString(), Toast.LENGTH_SHORT).show();


                    try{

                        String Success = response.getString("success");
                        String msg = response.getString("message");


                        if (Success.equals("true")){

                            Toast.makeText(Change_Phone.this, msg, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Change_Phone.this, Profile.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);


                            startActivity(intent);

//                            PreferenceUtils.saveid(data1,Email_OTP.this);
//                            PreferenceUtils.saveToken(data,Email_OTP.this);
//

                        }
                        else {

                            Toast.makeText(Change_Phone.this, msg, Toast.LENGTH_SHORT).show();


                        }


                    }catch (Exception e) {
                        e.printStackTrace();

                    }




                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Charset charset = Charset.defaultCharset();
                    String str = new String(error.networkResponse.data,charset);
                    Toast.makeText(Change_Phone.this, str, Toast.LENGTH_SHORT).show();
                    Log.i("dejklghoisrujhg",str);

                }


            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Accept","application/json");
                    params.put("Authorization","Bearer "+ PreferenceUtils.getToken(Change_Phone.this));
                    params.put("Authorization","Bearer "+PreferenceUtils.getToken1(Change_Phone.this));

                    //  params.put("Content-Type","application/x-www-form-urlencoded");
                    //params.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNmYwZGY2MTljYWE0NDJlMWM2NTM4YTRjYTcyNjc0Mjc1ZGY3YWJiZTU4ODgyZjEyZGM0MGQxNTA0ZGI5NTJmNzQ1ZWIwYzQ3OTQ4ZTIxZWQiLCJpYXQiOjE2NDQ0ODQ3OTIuMzc0NTU1LCJuYmYiOjE2NDQ0ODQ3OTIuMzc0NTYsImV4cCI6MTY3NjAyMDc5Mi4zNzA5MzIsInN1YiI6IjE5Iiwic2NvcGVzIjpbXX0.PLityoxwwHfLl4DMJz77NoxIAT6bbPx9UFaEn8LKjxYFyFcAnTDxVFobY43BkKR1xOm27YX3420XTxBf0s0iB1EW_XrJcTDClP8Y9G4rBZ0c06_2siDUDFYTPA8KeuQBDeCr8Aj6B7E_pT3qp9p3yG99AIUPK4onZNYDG_gZR6kQrvTlWwwgOSKD3ViTVTy91vQYZe7oxWbqUb_nhmL3Gb2wPdpZZ6j3FJiAj2MilCWml-doKID905ltazZc14aAEHOWFkB3UM4ryAEvFaXteAi5-gB1HseIPgguS8elMZ4BemaeJ1d7IJBnwY8pllsJmC9GKfpt66IPxT8KkSaILTLItJjtsCxretOx-x3Ngh6AULjQLvMFt1D27Z2PNei_zvVHDI7ECm0QjA-dO-rUuphq4Nrxw34qfcL4eW0znGbeHIQtSIL8AnPlFavJ7MjjnN24EZSrNjD_X8jJoNSqjUbwZgTef76RjHWUahja_w7IoX7IdjU9w6dvtEhwm5z_5LWORlCpND5zBxmQeoyHftgaokGPNK5tzc4It4VYt_K24s018Uwow4XE0_B3urSIkxJqzBVbEueV_w9tpTQSVp6P2YtH29SAkHDkw4j5FrdhHVK694-QHM-_qFQFol3CgEYWb7RfpcpDwkLXUd4Z4hqBAFJUmbD0HCoRBm73yGo");
                    return params;
                }

            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Change_Phone.this);
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void email_resend_otp(){

        OTP1 = otp1.getText().toString();
        OTP2 = otp2.getText().toString();
        OTP3 = otp3.getText().toString();
        OTP4 = otp4.getText().toString();



        String URL = "http://nk.inevitabletech.email/public/api/resendEmailOtp";

        JSONObject jsonBody = new JSONObject();




        try {

            jsonBody.put("otp", OTP1+OTP2+OTP3+OTP4);
            jsonBody.put("user_id",user_id);





            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    try{

                        String Success = response.getString("success");
                        String msg = response.getString("message");

                        if (Success.equals("true")){

                            Toast.makeText(Change_Phone.this, msg, Toast.LENGTH_SHORT).show();



                        }
                        else {

                            Toast.makeText(Change_Phone.this, msg, Toast.LENGTH_SHORT).show();


                        }


                    }catch (Exception e) {
                        e.printStackTrace();

                    }




                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Charset charset = Charset.defaultCharset();
                    String str = new String(error.networkResponse.data,charset);
                    Toast.makeText(Change_Phone.this, str, Toast.LENGTH_SHORT).show();

                }


            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Accept","application/json");
                    params.put("Authorization","Bearer "+ PreferenceUtils.getToken(Change_Phone.this));
                    params.put("Authorization","Bearer "+PreferenceUtils.getToken1(Change_Phone.this));
                    //  params.put("Content-Type","application/x-www-form-urlencoded");
                    //params.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNmYwZGY2MTljYWE0NDJlMWM2NTM4YTRjYTcyNjc0Mjc1ZGY3YWJiZTU4ODgyZjEyZGM0MGQxNTA0ZGI5NTJmNzQ1ZWIwYzQ3OTQ4ZTIxZWQiLCJpYXQiOjE2NDQ0ODQ3OTIuMzc0NTU1LCJuYmYiOjE2NDQ0ODQ3OTIuMzc0NTYsImV4cCI6MTY3NjAyMDc5Mi4zNzA5MzIsInN1YiI6IjE5Iiwic2NvcGVzIjpbXX0.PLityoxwwHfLl4DMJz77NoxIAT6bbPx9UFaEn8LKjxYFyFcAnTDxVFobY43BkKR1xOm27YX3420XTxBf0s0iB1EW_XrJcTDClP8Y9G4rBZ0c06_2siDUDFYTPA8KeuQBDeCr8Aj6B7E_pT3qp9p3yG99AIUPK4onZNYDG_gZR6kQrvTlWwwgOSKD3ViTVTy91vQYZe7oxWbqUb_nhmL3Gb2wPdpZZ6j3FJiAj2MilCWml-doKID905ltazZc14aAEHOWFkB3UM4ryAEvFaXteAi5-gB1HseIPgguS8elMZ4BemaeJ1d7IJBnwY8pllsJmC9GKfpt66IPxT8KkSaILTLItJjtsCxretOx-x3Ngh6AULjQLvMFt1D27Z2PNei_zvVHDI7ECm0QjA-dO-rUuphq4Nrxw34qfcL4eW0znGbeHIQtSIL8AnPlFavJ7MjjnN24EZSrNjD_X8jJoNSqjUbwZgTef76RjHWUahja_w7IoX7IdjU9w6dvtEhwm5z_5LWORlCpND5zBxmQeoyHftgaokGPNK5tzc4It4VYt_K24s018Uwow4XE0_B3urSIkxJqzBVbEueV_w9tpTQSVp6P2YtH29SAkHDkw4j5FrdhHVK694-QHM-_qFQFol3CgEYWb7RfpcpDwkLXUd4Z4hqBAFJUmbD0HCoRBm73yGo");
                    return params;
                }

            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Change_Phone.this);
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void initialize(){
        otp1 = findViewById(R.id.et_otp_ph1);
        otp2= findViewById(R.id.et_otp_ph2);
        otp3 = findViewById(R.id.et_otp_ph3);
        otp4 = findViewById(R.id.et_otp_ph4);

        mContext =Change_Phone.this;
    }
    private void addTextWatcher(final EditText one) {
        one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                switch (one.getId()) {
                    case R.id.et_otp_ph1:
                        if (one.length() == 1) {
                            otp2.requestFocus();
                        }
                        break;
                    case R.id.et_otp_ph2:
                        if (one.length() == 1) {
                            otp3.requestFocus();
                        } else if (one.length() == 0) {
                            otp1.requestFocus();
                        }
                        break;
                    case R.id.et_otp_ph3:
                        if (one.length() == 1) {
                            otp4.requestFocus();
                        } else if (one.length() == 0) {
                            otp2.requestFocus();
                        }
                        break;

                    case R.id.et_otp_ph4:
                        if (one.length() == 1) {
                            InputMethodManager inputManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(Change_Phone.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        } else if (one.length() == 0) {
                            otp3.requestFocus();
                        }
                        break;
                }
            }
        });


    }
}