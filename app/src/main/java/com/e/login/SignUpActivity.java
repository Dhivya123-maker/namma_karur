package com.e.login;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.Verification.Mobile_verification;
import com.e.login.Verification.Signup_google;
import com.e.login.Verification.VerificationActivity;
import com.e.login.Verification.VerifyActivity;
import com.e.login.utils.PreferenceUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements OnConnectionFailedListener, GoogleApiClient.OnConnectionFailedListener  {
    private static final int RC_SIGN_IN = 1;
    Button signupbtn;
    TextView signin;
    ImageView show_pass_btn_reg,signupback;
    EditText password, name, email, phone;
    String Password, Name, Email, Phone;
    String msg = null;
    GoogleSignInAccount account;
    String Id,Mobile;
    TextView userName, userEmail, userId;

    JSONObject data = null, user = null;
    String id = null;
    String token = null;
    String phonee = null;
    String goo_id = null;

    String d_id = null;
    TextView err;
    String Nme ;
    String email1,name1;



    LinearLayout reg;
    GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient googleApiClient;

    private GoogleSignInOptions gso;

    TextView Error1,Error2,Error3,Error4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        signupbtn =  findViewById(R.id.signup_btn);
        signin =  findViewById(R.id.sign_text);
        signupback = findViewById(R.id.signup_back);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.Phone_number);
        password = findViewById(R.id.signup_pass);
        err = findViewById(R.id.err);

        Error1 = findViewById(R.id.error1);
        Error2 = findViewById(R.id.error2);
        Error3 = findViewById(R.id.error3);
        Error4 = findViewById(R.id.error4);

        userName = (TextView) findViewById(R.id.name1);
        userEmail = (TextView) findViewById(R.id.email1);
        userId = (TextView) findViewById(R.id.userId);


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


        signupback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(i);

            }
        });





        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this,LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                i.putExtra("user_name",Name);
                i.putExtra("email",Email);
                i.putExtra("phone",Phone);
                startActivity(i);

            }
        });

        reg = findViewById(R.id.reg_google);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (view.getId()) {
                    case R.id.reg_google:
                        err.setVisibility(View.GONE);
                        signIn();
//                        register1();
                        break;

                }



            }
        });


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Error1.setVisibility(View.GONE);
                Error2.setVisibility(View.GONE);
                Error3.setVisibility(View.GONE);
                Error4.setVisibility(View.GONE);
                register();

            }
        });



        signupback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);


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


        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//            updateUI(account);

            Toast.makeText(SignUpActivity.this, "Signup successfull", Toast.LENGTH_SHORT).show();




            Intent intent = new Intent(SignUpActivity.this,Signup_google.class);

            startActivity(intent);


        } catch (ApiException e) {


            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(SignUpActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }





    }



    public void register(){


                Name = name.getText().toString();
                Email = email.getText().toString();
                Password = password.getText().toString();
                Phone = phone.getText().toString();



                String URL = "http://nk.inevitabletech.email/public/api/register";

        JSONObject jsonBody = new JSONObject();




        try {

            jsonBody.put("name", Name);
            jsonBody.put("email", Email);
            jsonBody.put("password", Password);
            jsonBody.put("phone", Phone);
            jsonBody.put("device_id", d_id);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {



                    try{
                       String Success = response.getString("success");
                       msg = response.getString("message");



                       data = response.getJSONObject("data");
                       token = data.getString("token");


                       user = data.getJSONObject("user");
                       id = user.getString("id");
                       phonee = user.getString("phone");




                        if (Success == "true"){

                            Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this,VerifyActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                intent.putExtra("phone","+91 " + phonee);
                               intent.putExtra("token", token);
                               intent.putExtra("id",id);
                               intent.putExtra("email",Email);
                               intent.putExtra("password",Password);
                               intent.putExtra("user_name",Name);


                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);


                        }
                        else {


//                            Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_SHORT).show();


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

                        JSONArray jsonArray1 = data.getJSONArray("name");
                        Error1.setText(jsonArray1.getString(0));
                        Error1.setVisibility(View.VISIBLE);
//                            email.setError(jsonArray1.getString(0));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");


                        JSONArray jsonArray2 = data.getJSONArray("email");
                        Error2.setText(jsonArray2.getString(0));
                        Error2.setVisibility(View.VISIBLE);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");

                        JSONArray jsonArray2 = data.getJSONArray("phone");

                        Error3.setText(jsonArray2.getString(0));
                        Error3.setVisibility(View.VISIBLE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");

                        JSONArray jsonArray2 = data.getJSONArray("password");
//                        email.setError(jsonArray1);
                        Error4.setText(jsonArray2.getString(0));
                        Error4.setVisibility(View.VISIBLE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }







                }

            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }








    }




    public void ShowHidePass(View view){

        if(view.getId() == R.id.show_pass_btn_reg){

            if(password.getTransformationMethod() == (PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_baseline_visibility_24);

                //Show Password
              password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else {
                ((ImageView)(view)).setImageResource(R.drawable.ic_baseline_visibility_off_24);

                //Hide Password
         password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }}


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

            Nme = account.getDisplayName();
            Email = account.getEmail();
            Id = account.getId();




        }


    }



    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



    public  void register1(){



        Nme = account.getDisplayName();
        Email = account.getEmail();
        Id = account.getId();



        String url = "http://nk.inevitabletech.email/public/api/register-with-google";

        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put("email",Email);
            jsonBody.put("google_id", Id);
            jsonBody.put("name", Name);
//            jsonBody.put("phone", Mobile);
            jsonBody.put("device_id", d_id);




            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @SuppressLint("CheckResult")
                @Override
                public void onResponse(JSONObject response) {


                    try {

                        String Success = response.getString("success");
                        String msg = response.getString("message");


                        data = response.getJSONObject("data");
                        token = data.getString("token");


                        user = data.getJSONObject("user");
                        id = user.getString("id");
                        email1 = user.getString("email");
                        name1 = user.getString("name");
                        goo_id = user.getString("google_id");
                        phonee = user.getString("phone");


                        if (Success == "true") {

                            Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, Mobile_verification.class);
                            intent.putExtra("email",email1);
                            intent.putExtra("token", token);
                            intent.putExtra( "name", name1);
//                            intent.putExtra("phone", phonee);
                            intent.putExtra("id",id);



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
                        JSONArray jsonArray = jsonObject1.getJSONArray("phone");
                        err.setText(jsonArray.getString(0));
                        err.setVisibility(View.VISIBLE);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
                    headers.put("Authorization", "Bearer " + PreferenceUtils.getToken(SignUpActivity.this));
                    return headers;
                }


            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
            requestQueue.add(jsonObjectRequest);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    }