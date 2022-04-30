package com.e.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.HomeClass.Home;
import com.e.login.Verification.Email_OTP;
import com.e.login.Verification.Mobile_verification;
import com.e.login.Verification.Signup_google;
import com.e.login.Verification.VerificationActivity;
import com.e.login.utils.PreferenceUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements OnConnectionFailedListener, GoogleApiClient.OnConnectionFailedListener  {
    Button signin;
    LinearLayout signin_google;

    TextView textsignup,forget;
    ImageView show_pass_btn,back;
    EditText password,email,phn;
    String Email,Password,email_intent,pass_intent,user_intent,phone,Phone;
    private  long pressedTime;
    GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient googleApiClient;

    private GoogleSignInOptions gso;
    private static final int RC_SIGN_IN = 1;
    GoogleSignInAccount account;
    TextView userName, userEmail, userId;
    String Name, EMail, Id,Mobile;
    JSONObject data = null, user = null;
    String id = null;
    String token = null;
    String phonee = null;
    String eemail,name,goo_id,goo_token,user_id;

    TextView Error1,Error2;
    String d_id = null;
    String Name1,Email1,Id1;
    TextView err;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        signin = findViewById(R.id.signinbutton);
        textsignup = findViewById(R.id.txt_signup);
        show_pass_btn = (ImageView) findViewById(R.id.show_pass_btn_login);
        email = findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);
        forget = findViewById(R.id.forget);
        back = findViewById(R.id.back_button);
        signin_google = findViewById(R.id.sign_in_button);



        userName = (TextView) findViewById(R.id.name);
        userName.setVisibility(View.GONE);
        userEmail = (TextView) findViewById(R.id.email);
        userEmail.setVisibility(View.GONE);
        userId = (TextView) findViewById(R.id.userId);
        userId.setVisibility(View.GONE);

        Error1 = findViewById(R.id.error1);
        Error2 = findViewById(R.id.error2);
        err = findViewById(R.id.err);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);




        FirebaseMessaging messaging =  FirebaseMessaging.getInstance();
        messaging.getToken().addOnSuccessListener(s -> {
            Log.d("Device ID:",s);

            d_id = s;

        });


        signin_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;

                }




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
        user_intent = i1.getStringExtra("user_name");
        phone = i1.getStringExtra("phone");




        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(LoginActivity.this,VerificationActivity.class);
                intent.putExtra("user_name",user_intent);
                intent.putExtra("email",email_intent);
                intent.putExtra("phone",phone);

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

                login();

                Error1.setVisibility(View.GONE);
                Error2.setVisibility(View.GONE);

            }
        });



    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {

            GoogleSignInAccount account = completedTask.getResult(ApiException.class);



            login_google();
            err.setVisibility(View.GONE);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

        public void login() {


        Email = email.getText().toString();
        Password = password.getText().toString();


        String URL = "http://nk.inevitabletech.email/public/api/login";


        JSONObject jsonBody = new JSONObject();




            try {

            jsonBody.put("email", Email);
            jsonBody.put("password", Password);
            jsonBody.put("device_id", d_id);



            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {



                    try {
                        String Success = response.getString("success");
                        String msg = response.getString("message");



                        data = response.getJSONObject("data");
                        token = data.getString("token");


                        user = data.getJSONObject("user");
                        id = user.getString("id");
                        phonee = user.getString("phone");



                        if (Success.equals("true")) {

                            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, Home.class);
                            intent.putExtra("id", id);
                            intent.putExtra("token", token);

//                            PreferenceUtils.saveid("4", LoginActivity.this);
//                            PreferenceUtils.saveToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNjY5ZWM2NzE0MDFmYTk2MWUwNzQ5NjMyMTNmODljODkxMzIwODA5N2VhNmMwNTI0N2Y4NWRjZTMxN2Y0MGQ2ZTUyZjc1MzVjNjVhYWMzMGUiLCJpYXQiOjE2NDcwODc1NjAuMDEyNjE2LCJuYmYiOjE2NDcwODc1NjAuMDEyNjIsImV4cCI6MTY3ODYyMzU2MC4wMDc4NjMsInN1YiI6IjEyIiwic2NvcGVzIjpbXX0.k67qYf2_wLc3roJU7vnuxzht64dpaFfcH18TJvYIQQtOBXT1CdF8p5XdetsvG2WZVkpttJo-O3ckFwYfKoh2otjPAYuGNOTk0ORlsJcgN6JOsHOs9sDxJZIthiPZ9lTe4xVBvreNVSfVDI9ciGgb0X2OatQ7_Xq5jdqIKF9dkf-sk6PqzfBIGR1z-25ST-0WikCzm7HIMAStMehAKj-v5xy0RCFytMItY-QwCkHT6Pr56QZeY8z81Ec0N-RGjxRUpLI9gxzADGLpACWhWuBwQiw8X71Ws1ey-VkrrSolsREuyzosm6jirERAmIuTq9UjYrjHCysVo1HGnjH1QI9DOfElicyWc_4ZLUi8z1l6q3m5D8qps_IKnk0pU4StC9jOxp6DRt5LJRQlVHRQTtj6h1ElHlV1B0Xm3YgWTWeMXxQovmif6Xf4684E0k11Amcm5RLb5aCNmZaki1Ctu46hVEB-6Sp4E44ReGaB70UaEtv8V2DYkMhJMHbYn2WIbaZicbiuwsICroipIk72Z7qiJ7THBO40sHvTuO9LW_WxHfhdWUMHCikUX9CWCcY5jyUo4HkOpoylSwqmYX31Ybm_-SK2kSIKNs3RlDHsdbCCs8aTRkWA5uRV_Y7ijUHtqYl1zau5ItET-X-oiJPYtGnWOVbAQ-PU3KOXkDmgIcGX9jE",LoginActivity.this);
                            PreferenceUtils.saveid(id, LoginActivity.this);
                            PreferenceUtils.saveToken(token, LoginActivity.this);

                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);



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



                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");

                        Log.i("fjfifeofsdf",data.toString());
                            JSONArray jsonArray1 = data.getJSONArray("email");
                            Error1.setText(jsonArray1.getString(0));
                            Error1.setVisibility(View.VISIBLE);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");


                            JSONArray jsonArray2 = data.getJSONArray("password");
                            Error2.setText(jsonArray2.getString(0));
                            Error2.setVisibility(View.VISIBLE);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");

                        String jsonArray1 = data.getString("error");

                        Error1.setText(jsonArray1);
                        Error1.setVisibility(View.VISIBLE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }




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




    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void login_google(){

        GoogleSignInAccount account =  GoogleSignIn.getLastSignedInAccount(this);




        Email1 = account.getEmail();
        Id1 = account.getId();
//        Email1 = account.getEmail();
//        Id1 = account.getId();

        String url = "http://nk.inevitabletech.email/public/api/login-with-google";

        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put("email",Email1);
            jsonBody.put("google_id", Id1);
            jsonBody.put("device_id",d_id);

            Log.i("wdkjfbjlfhbj",jsonBody.toString());



            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @SuppressLint("CheckResult")
                @Override
                public void onResponse(JSONObject response) {


                    Log.i("heiuqhfeuoh",response.toString());
                    try {

                        String Success = response.getString("success");
                        String msg = response.getString("message");


                        data = response.getJSONObject("data");
                        token = data.getString("token");


                        user = data.getJSONObject("user");
                        id = user.getString("id");
                        eemail = user.getString("email");
                        name = user.getString("name");
                        goo_id = user.getString("google_id");
                        phonee = user.getString("phone");


                        if (Success == "true") {

                            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, Home.class);
                            intent.putExtra("email",eemail);
//                            intent.putExtra("google_id", goo_id);
                            intent.putExtra("id",id);
//                            intent.putExtra("token",token);

                            PreferenceUtils.saveid(id,LoginActivity.this);
                            PreferenceUtils.saveToken(token,LoginActivity.this);




                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);





                        } else {


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

                    try {


                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                      String data = jsonObject1.getString("error");
                        err.setText(data);
                        err.setVisibility(View.VISIBLE);
                        GoogleSignInOptions gso = new GoogleSignInOptions.
                                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                                build();

                        GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(LoginActivity.this,gso);
                        googleSignInClient.signOut();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//
                    try {


                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = jsonObject1.getJSONArray("email");
                        err.setText(jsonArray.getString(0));
                        err.setVisibility(View.VISIBLE);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {


                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = jsonObject1.getJSONArray("google_id");
                        err.setText(jsonArray.getString(0));
                        err.setVisibility(View.VISIBLE);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();


                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<String, String>();

                    headers.put("Accept", "application/json");
                    headers.put("Authorization", "Bearer " + PreferenceUtils.getToken(LoginActivity.this));
                    return headers;
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




