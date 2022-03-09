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
import com.e.login.ChangePassword;
import com.e.login.HomeClass.Home;
import com.e.login.R;
import com.e.login.SignUpActivity;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Forget_OTP extends AppCompatActivity {
    Button verify;
    EditText otp1,otp2,otp3,otp4;
    String OTP1,OTP2,OTP3,OTP4;
    String data,data1,data2,data3,user_id;
    ImageView back;
    TextView resend,mobile;
    private Context mContext;
    String u_name;
    String name,id,email,phone;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_otp);

        back = findViewById(R.id.back_verify_forget);
        resend = findViewById(R.id.resend_otp_forget);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Forget_OTP.this, VerificationActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        mobile = findViewById(R.id.mobile_forget);


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


//
        Intent i = getIntent();
        data = i.getStringExtra("token");
        Intent i1 = getIntent();
        data1 = i1.getStringExtra("id");
        data2 = i1.getStringExtra("phone");
        data3 = i1.getStringExtra("email");
        user_id = i1.getStringExtra("user_id");
//        data4 = i1.getStringExtra("user_name");
//        u_name = i1.getStringExtra("name");
        if(data2.matches(MobilePattern)){
            mobile.setText("+91 "+data2);

        }else  {
            mobile.setText(data3);
        }


       // Toast.makeText(Forget_OTP.this, user_id, Toast.LENGTH_SHORT).show();
        //Toast.makeText(Forget_OTP.this, data3, Toast.LENGTH_SHORT).show();


        verify = findViewById(R.id.verify_otp_forget);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp();

            }
        });



    }
    public void otp(){



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



                    try{

                        String Success = response.getString("success");
                        String msg = response.getString("message");


                        JSONObject jsonObject = response.getJSONObject("data");
                        id = jsonObject.getString("id");
                        name = jsonObject.getString("name");
                        email = jsonObject.getString("email");
                        phone = jsonObject.getString("phone");


                        if(Success.equals("true")){
                            Toast.makeText(Forget_OTP.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Forget_OTP.this, PasswordActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            intent.putExtra("user_id",id);


                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Forget_OTP.this, msg, Toast.LENGTH_SHORT).show();
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

                }


            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    params.put("Authorization","Bearer "+PreferenceUtils.getToken(Forget_OTP.this));
                    return params;
                }

            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Forget_OTP.this);
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

                            Toast.makeText(Forget_OTP.this, msg, Toast.LENGTH_SHORT).show();
                            Log.i("123",msg);



                        }
                        else {

                            Toast.makeText(Forget_OTP.this, msg, Toast.LENGTH_SHORT).show();


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
                     params.put("Accept","application/json");
                    params.put("Authorization","Bearer "+PreferenceUtils.getToken(Forget_OTP.this));
                    return params;
                }



            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Forget_OTP.this);
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }



    private void initialize() {
        otp1 = findViewById(R.id.et_otp_forget);
        otp2= findViewById(R.id.et_otp_forget1);
        otp3 = findViewById(R.id.et_otp_forget2);
        otp4 = findViewById(R.id.et_otp_forget3);

        mContext = Forget_OTP.this;
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
                    case R.id.et_otp_forget:
                        if (one.length() == 1) {
                            otp2.requestFocus();
                        }
                        break;
                    case R.id.et_otp_forget1:
                        if (one.length() == 1) {
                            otp3.requestFocus();
                        } else if (one.length() == 0) {
                            otp1.requestFocus();
                        }
                        break;
                    case R.id.et_otp_forget2:
                        if (one.length() == 1) {
                            otp4.requestFocus();
                        } else if (one.length() == 0) {
                            otp2.requestFocus();
                        }
                        break;

                    case R.id.et_otp_forget3:
                        if (one.length() == 1) {
                            InputMethodManager inputManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(Forget_OTP.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        } else if (one.length() == 0) {
                            otp3.requestFocus();
                        }
                        break;
                }
            }
        });


    }
}