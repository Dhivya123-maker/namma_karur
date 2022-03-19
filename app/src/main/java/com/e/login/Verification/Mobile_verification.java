package com.e.login.Verification;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.e.login.NewsClass.All_news;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Mobile_verification extends AppCompatActivity {
    Button verifyy;
    ImageView back;
    String data,data1,data2,data3,data4;
    String token,id;
    TextView verify,resend;
    EditText otp1,otp2,otp3,otp4;
    String OTP1,OTP2,OTP3,OTP4;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verification);
        verifyy = findViewById(R.id.verifyy);

        verify = findViewById(R.id.num_txt);
        resend = findViewById(R.id.resend_otp_mobile);

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              ResendOtp();
            }
        });

        initialize();



        addTextWatcher(otp1);
        addTextWatcher(otp2);
        addTextWatcher(otp3);
        addTextWatcher(otp4);



        Intent intent = getIntent();
        data = intent.getStringExtra("email");
        data1 = intent.getStringExtra("google_id");
        data4 = intent.getStringExtra("token");
        data2 = intent.getStringExtra("name");
        data3 = intent.getStringExtra("phone");
//        token = intent.getStringExtra("token1");
        id = intent.getStringExtra("id");
        verify.setText("+91 "+data3);
       // Toast.makeText(Mobile_verification.this, data1, Toast.LENGTH_SHORT).show();


        back = findViewById(R.id.back_mobile);

        verifyy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otpp();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mobile_verification.this, VerificationActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });



    }

    public void otpp(){



        OTP1 = otp1.getText().toString();
        OTP2 = otp2.getText().toString();
        OTP3 = otp3.getText().toString();
        OTP4 = otp4.getText().toString();



        String URL = "http://nk.inevitabletech.email/public/api/verifyOtp";

        JSONObject jsonBody = new JSONObject();




        try {

            jsonBody.put("otp", OTP1+OTP2+OTP3+OTP4);
            jsonBody.put("user_id",id);





            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

//                    Log.i("000000000000",response.toString());
//
//                    Toast.makeText(VerifyActivity.this, data, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(VerifyActivity.this, data1, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(VerifyActivity.this, response.toString(), Toast.LENGTH_SHORT).show();


                    try{

                        String Success = response.getString("success");
                        String msg = response.getString("message");

                        if (Success.equals("true")){

                            Intent intent = new Intent(Mobile_verification.this, Home.class);
                            intent.putExtra("goo_token",data4);
                            intent.putExtra("goo_id",data1);
                            PreferenceUtils.saveid1(data1,Mobile_verification.this);
                            PreferenceUtils.saveToken1(data4,Mobile_verification.this);
                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                            startActivity(intent);




                        }
                        else {

                            Toast.makeText(Mobile_verification.this, msg, Toast.LENGTH_SHORT).show();


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

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");

                        Toast.makeText(Mobile_verification.this, data.toString(), Toast.LENGTH_SHORT).show();




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    //  params.put("Content-Type","application/x-www-form-urlencoded");
                    params.put("Authorization","Bearer "+data);
                    return params;
                }

            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Mobile_verification.this);
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public void ResendOtp(){


        String URL = "http://nk.inevitabletech.email/public/api/resendOtp";

        JSONObject jsonBody = new JSONObject();




        try {


            jsonBody.put("user_id",data1);




            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

//                    Log.i("000000000000",response.toString());
//
//                  Log.i("hgfyiidyiudi",response.toString());
//                    Toast.makeText(VerifyActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(VerifyActivity.this, data, Toast.LENGTH_SHORT).show();


                    try{

                        String Success = response.getString("success");
                        String msg = response.getString("message");

                        if (Success.equals("true")){

                            Toast.makeText(Mobile_verification.this, msg, Toast.LENGTH_SHORT).show();
                            Log.i("123",msg);
//                            Intent intent = new Intent(VerifyActivity.this,Email_OTP.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                            startActivity(intent);



                        }
                        else {

                            Toast.makeText(Mobile_verification.this, msg, Toast.LENGTH_SHORT).show();


                        }


                    }catch (Exception e) {
                        e.printStackTrace();

                    }





                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }


            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    //  params.put("Content-Type","application/x-www-form-urlencoded");
                    params.put("Authorization","Bearer "+PreferenceUtils.getToken(Mobile_verification.this));
                    return params;
                }



            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Mobile_verification.this);
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }










    private void initialize() {
        otp1 = findViewById(R.id.et_otp_mobile);
        otp2= findViewById(R.id.et_otp_mobile1);
        otp3 = findViewById(R.id.et_otp_mobile2);
        otp4 = findViewById(R.id.et_otp_mobile3);

        mContext = Mobile_verification.this;
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
                    case R.id.et_otp_mobile:
                        if (one.length() == 1) {
                            otp2.requestFocus();
                        }
                        break;
                    case R.id.et_otp_mobile1:
                        if (one.length() == 1) {
                            otp3.requestFocus();
                        } else if (one.length() == 0) {
                            otp1.requestFocus();
                        }
                        break;
                    case R.id.et_otp_mobile2:
                        if (one.length() == 1) {
                            otp4.requestFocus();
                        } else if (one.length() == 0) {
                            otp2.requestFocus();
                        }
                        break;

                    case R.id.et_otp_mobile3:
                        if (one.length() == 1) {
                            InputMethodManager inputManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(Mobile_verification.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        } else if (one.length() == 0) {
                            otp3.requestFocus();
                        }
                        break;
                }
            }
        });


    }


}