package com.e.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.e.login.Verification.VerificationActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button signin;
    LinearLayout signin_google;

    TextView textsignup,forget;
    ImageView show_pass_btn,back;
    EditText password,email;
    String Email,Password,email_intent,pass_intent;
    private  long pressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        signin = findViewById(R.id.signinbutton);
        textsignup = findViewById(R.id.txt_signup);
        show_pass_btn = (ImageView) findViewById(R.id.show_pass_btn_login);
        email = findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);
        forget = findViewById(R.id.forget);
        back = findViewById(R.id.back_button);
        signin_google = findViewById(R.id.sign_in_button);


        signin_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(LoginActivity.this,Home.class);
                startActivity(intent);


            }
        });




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);


            }
        });


        Intent i = getIntent();
        email_intent = i.getStringExtra("email");
        Intent i1 = getIntent();
        pass_intent = i1.getStringExtra("password");


        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, VerificationActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });


        textsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);


            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intSignUp = new Intent(LoginActivity.this, Home.class);
//                startActivity(intSignUp);


                login();


            }
        });



    }

    public void login() {


        Email = email.getText().toString();
        Password = password.getText().toString();


        String URL = "http://nk.inevitabletech.email/public/api/login";

        JSONObject jsonBody = new JSONObject();


        try {

            jsonBody.put("email", Email);
            jsonBody.put("password", Password);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.i("123", response.toString());
                    Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();


//                   Boolean Success ;
//                    String msg;
                    //create the boolean;


                    try {
                        String Success = response.getString("success");
                        String msg = response.getString("message");


                        if (Success.equals("true")) {

                            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, Home.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);


                        } else {

                            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();


                        }


                    } catch (Exception e) {
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

                    return params;
                }

            };

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

        public void ShowHidePass(View view){

        if(view.getId() == R.id.show_pass_btn_login){

            if(password.getTransformationMethod() == (PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_baseline_visibility_24);

                //Show Password
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());



            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_baseline_visibility_off_24);
//
                //Hide Password
               password.setTransformationMethod(PasswordTransformationMethod.getInstance());




            }
        }
    }
   }




