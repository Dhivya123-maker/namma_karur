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
import com.e.login.HomeClass.Home;
import com.e.login.Profile;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Email_OTP extends AppCompatActivity {

    Button verification;
    EditText otp1,otp2,otp3,otp4;
    String OTP1,OTP2,OTP3,OTP4;
    String data,data1,data2,data3,phone,user_id;
    TextView resend,mail;
    String id,name,email,phonee;
    String email_get;
    TextView er;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_otp);





        mail = findViewById(R.id.mai);
        er = findViewById(R.id.error1);
        Intent i = getIntent();
        data = i.getStringExtra("token");
        Intent i1 = getIntent();
        data1 = i1.getStringExtra("id");
        data2 = i1.getStringExtra("email");
        data3 = i1.getStringExtra("user_name");
        phone = i1.getStringExtra("phone");
//        user_id = i1.getStringExtra("user_id");
//        email_get = i1.getStringExtra("email");


        mail.setText(data2);

        initialize();




        addTextWatcher(otp1);
        addTextWatcher(otp2);
        addTextWatcher(otp3);
        addTextWatcher(otp4);

        otp1 = findViewById(R.id.et_otp_email);
        otp2 = findViewById(R.id.et_otp_email1);
        otp3 = findViewById(R.id.et_otp_email2);
        otp4 = findViewById(R.id.et_otp_email3);
        resend = findViewById(R.id.resend_otpp);

        verification = findViewById(R.id.verify_buttonn);

        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_otp();


            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_resend_otp();
            }
        });



    }

    public void email_otp(){

        OTP1 = otp1.getText().toString();
        OTP2 = otp2.getText().toString();
        OTP3 = otp3.getText().toString();
        OTP4 = otp4.getText().toString();



        String URL = "http://nk.inevitabletech.email/public/api/verifyEmailOtp";

        JSONObject jsonBody = new JSONObject();




        try {

            jsonBody.put("otp", OTP1+OTP2+OTP3+OTP4);
            jsonBody.put("user_id",data1);




            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    try{

                        String Success = response.getString("success");
                        String msg = response.getString("message");

                        JSONObject jsonObject = response.getJSONObject("data");
                        id = jsonObject.getString("id");
                        name = jsonObject.getString("name");
                        email = jsonObject.getString("email");
                        phonee = jsonObject.getString("phone");



                        if (Success.equals("true")){

                            Toast.makeText(Email_OTP.this, msg, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Email_OTP.this, Profile.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            intent.putExtra("id",id);
                            intent.putExtra("name",data3);
                            intent.putExtra("email",data2);
                            intent.putExtra("phone",phone);

                            startActivity(intent);

//                            PreferenceUtils.saveid(data1,Email_OTP.this);
//                            PreferenceUtils.saveToken(data,Email_OTP.this);


                        }
                        else {

                            Toast.makeText(Email_OTP.this, msg, Toast.LENGTH_SHORT).show();


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

                    Log.i("jknhfwroeitjf3o",str);

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        String err = jsonObject1.getString("error");
                        er.setText(err);
                        er.setVisibility(View.VISIBLE);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }


            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                   params.put("Accept","application/json");
                   params.put("Authorization","Bearer "+ PreferenceUtils.getToken(Email_OTP.this));

                    return params;
                }

            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Email_OTP.this);
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
            jsonBody.put("user_id",data1);





            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    try{

                        String Success = response.getString("success");
                        String msg = response.getString("message");

                        if (Success.equals("true")){

                            Toast.makeText(Email_OTP.this, msg, Toast.LENGTH_SHORT).show();



                        }
                        else {

                            Toast.makeText(Email_OTP.this, msg, Toast.LENGTH_SHORT).show();


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
                    Toast.makeText(Email_OTP.this, str, Toast.LENGTH_SHORT).show();

                }


            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Accept","application/json");
                    params.put("Authorization","Bearer "+PreferenceUtils.getToken(Email_OTP.this));
                    return params;
                }

            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Email_OTP.this);
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    private void initialize(){
        otp1 = findViewById(R.id.et_otp_email);
        otp2= findViewById(R.id.et_otp_email1);
        otp3 = findViewById(R.id.et_otp_email2);
        otp4 = findViewById(R.id.et_otp_email3);

        mContext = Email_OTP.this;
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
                    case R.id.et_otp_email:
                        if (one.length() == 1) {
                            otp2.requestFocus();
                        }
                        break;
                    case R.id.et_otp_email1:
                        if (one.length() == 1) {
                            otp3.requestFocus();
                        } else if (one.length() == 0) {
                            otp1.requestFocus();
                        }
                        break;
                    case R.id.et_otp_email2:
                        if (one.length() == 1) {
                            otp4.requestFocus();
                        } else if (one.length() == 0) {
                            otp2.requestFocus();
                        }
                        break;

                    case R.id.et_otp_email3:
                        if (one.length() == 1) {
                            InputMethodManager inputManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(Email_OTP.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        } else if (one.length() == 0) {
                            otp3.requestFocus();
                        }
                        break;
                }
            }
        });


    }
}