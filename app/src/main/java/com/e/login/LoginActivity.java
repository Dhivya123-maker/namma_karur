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



        userName = (TextView) findViewById(R.id.name);
        userName.setVisibility(View.GONE);
        userEmail = (TextView) findViewById(R.id.email);
        userEmail.setVisibility(View.GONE);
        userId = (TextView) findViewById(R.id.userId);
        userId.setVisibility(View.GONE);

        Error1 = findViewById(R.id.error1);
        Error2 = findViewById(R.id.error2);





        signin_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;

                }

               login_google();


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



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

//
//        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//


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
        startActivityForResult(signInIntent, RC_SIGN_IN);

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
//            updateUI(account);

            Toast.makeText(LoginActivity.this, "Signin successfully", Toast.LENGTH_SHORT).show();



            // Signed in successfully, show authenticated UI.
            startActivity(new Intent(LoginActivity.this, Home.class));
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
//                            intent.putExtra("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNjY5ZWM2NzE0MDFmYTk2MWUwNzQ5NjMyMTNmODljODkxMzIwODA5N2VhNmMwNTI0N2Y4NWRjZTMxN2Y0MGQ2ZTUyZjc1MzVjNjVhYWMzMGUiLCJpYXQiOjE2NDcwODc1NjAuMDEyNjE2LCJuYmYiOjE2NDcwODc1NjAuMDEyNjIsImV4cCI6MTY3ODYyMzU2MC4wMDc4NjMsInN1YiI6IjEyIiwic2NvcGVzIjpbXX0.k67qYf2_wLc3roJU7vnuxzht64dpaFfcH18TJvYIQQtOBXT1CdF8p5XdetsvG2WZVkpttJo-O3ckFwYfKoh2otjPAYuGNOTk0ORlsJcgN6JOsHOs9sDxJZIthiPZ9lTe4xVBvreNVSfVDI9ciGgb0X2OatQ7_Xq5jdqIKF9dkf-sk6PqzfBIGR1z-25ST-0WikCzm7HIMAStMehAKj-v5xy0RCFytMItY-QwCkHT6Pr56QZeY8z81Ec0N-RGjxRUpLI9gxzADGLpACWhWuBwQiw8X71Ws1ey-VkrrSolsREuyzosm6jirERAmIuTq9UjYrjHCysVo1HGnjH1QI9DOfElicyWc_4ZLUi8z1l6q3m5D8qps_IKnk0pU4StC9jOxp6DRt5LJRQlVHRQTtj6h1ElHlV1B0Xm3YgWTWeMXxQovmif6Xf4684E0k11Amcm5RLb5aCNmZaki1Ctu46hVEB-6Sp4E44ReGaB70UaEtv8V2DYkMhJMHbYn2WIbaZicbiuwsICroipIk72Z7qiJ7THBO40sHvTuO9LW_WxHfhdWUMHCikUX9CWCcY5jyUo4HkOpoylSwqmYX31Ybm_-SK2kSIKNs3RlDHsdbCCs8aTRkWA5uRV_Y7ijUHtqYl1zau5ItET-X-oiJPYtGnWOVbAQ-PU3KOXkDmgIcGX9jE");

//                            PreferenceUtils.saveid("4", LoginActivity.this);
//                            PreferenceUtils.saveToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNjY5ZWM2NzE0MDFmYTk2MWUwNzQ5NjMyMTNmODljODkxMzIwODA5N2VhNmMwNTI0N2Y4NWRjZTMxN2Y0MGQ2ZTUyZjc1MzVjNjVhYWMzMGUiLCJpYXQiOjE2NDcwODc1NjAuMDEyNjE2LCJuYmYiOjE2NDcwODc1NjAuMDEyNjIsImV4cCI6MTY3ODYyMzU2MC4wMDc4NjMsInN1YiI6IjEyIiwic2NvcGVzIjpbXX0.k67qYf2_wLc3roJU7vnuxzht64dpaFfcH18TJvYIQQtOBXT1CdF8p5XdetsvG2WZVkpttJo-O3ckFwYfKoh2otjPAYuGNOTk0ORlsJcgN6JOsHOs9sDxJZIthiPZ9lTe4xVBvreNVSfVDI9ciGgb0X2OatQ7_Xq5jdqIKF9dkf-sk6PqzfBIGR1z-25ST-0WikCzm7HIMAStMehAKj-v5xy0RCFytMItY-QwCkHT6Pr56QZeY8z81Ec0N-RGjxRUpLI9gxzADGLpACWhWuBwQiw8X71Ws1ey-VkrrSolsREuyzosm6jirERAmIuTq9UjYrjHCysVo1HGnjH1QI9DOfElicyWc_4ZLUi8z1l6q3m5D8qps_IKnk0pU4StC9jOxp6DRt5LJRQlVHRQTtj6h1ElHlV1B0Xm3YgWTWeMXxQovmif6Xf4684E0k11Amcm5RLb5aCNmZaki1Ctu46hVEB-6Sp4E44ReGaB70UaEtv8V2DYkMhJMHbYn2WIbaZicbiuwsICroipIk72Z7qiJ7THBO40sHvTuO9LW_WxHfhdWUMHCikUX9CWCcY5jyUo4HkOpoylSwqmYX31Ybm_-SK2kSIKNs3RlDHsdbCCs8aTRkWA5uRV_Y7ijUHtqYl1zau5ItET-X-oiJPYtGnWOVbAQ-PU3KOXkDmgIcGX9jE",LoginActivity.this);
                            PreferenceUtils.saveid(id, LoginActivity.this);
                            PreferenceUtils.saveToken(token, LoginActivity.this);

                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);


//                            JSONObject res =  response.getJSONObject("data");
//
//                            String token = res.getString("token");
//                            JSONObject user = res.getJSONObject("user");
//
//                            String id = user.getString("id");

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

                            JSONArray jsonArray1 = data.getJSONArray("email");
                            Error1.setText(jsonArray1.getString(0));
                            Error1.setVisibility(View.VISIBLE);
//                            email.setError(jsonArray1.getString(0));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");


                            JSONArray jsonArray2 = data.getJSONArray("password");
                            Error2.setText(jsonArray2.getString(0));
                            Error2.setVisibility(View.VISIBLE);
//                        password.setError(jsonArray2.getString(0));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");

                        String jsonArray1 = data.getString("error");
//                        email.setError(jsonArray1);
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

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            account = result.getSignInAccount();

            userName.setText(account.getDisplayName());
            userEmail.setText(account.getEmail());
            userId.setText(account.getId());

            Name = account.getDisplayName();
            Email = account.getEmail();
            Id = account.getId();



        }


    }

    public void login_google(){


//        Email = account.getEmail();
//        Id = account.getId();

        String url = "http://nk.inevitabletech.email/public/api/login-with-google";

        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put("email",Email);
            jsonBody.put("google_id", Id);



          //  Toast.makeText(LoginActivity.this, jsonBody.toString(), Toast.LENGTH_SHORT).show();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @SuppressLint("CheckResult")
                @Override
                public void onResponse(JSONObject response) {
//                    Log.i("0000000000000", response.toString());
//                    Toast.makeText(Signup_google.this, response.toString(), Toast.LENGTH_SHORT).show();

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
//                            PreferenceUtils.saveid1(goo_id,LoginActivity.this);
//                           PreferenceUtils.saveToken1(token,LoginActivity.this);



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
                    Log.i("wkjlgroiwt", str);


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
//                    headers.put("Authorization", "Bearer " + PreferenceUtils.getToken1(LoginActivity.this));
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}




