package com.e.login.Verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.e.login.R;
import com.e.login.SignUpActivity;
import com.e.login.utils.PreferenceUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Signup_google extends AppCompatActivity implements OnConnectionFailedListener, GoogleApiClient.OnConnectionFailedListener {
    ImageView back;
    Button google;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    TextView userName, userEmail, userId;
    String Name, Email, Id,Mobile;
    EditText mobile;
    GoogleSignInAccount account;

    JSONObject data = null, user = null;
    String id = null;
    String token = null;
    String phonee = null;
    String email,name,goo_id;
    TextView err;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_google);

        userName = (TextView) findViewById(R.id.name);
        userName.setVisibility(View.GONE);
        userEmail = (TextView) findViewById(R.id.email);
        userEmail.setVisibility(View.GONE);
        userId = (TextView) findViewById(R.id.userId);
        userId.setVisibility(View.GONE);

        mobile = findViewById(R.id.mobile_edit);
        err = findViewById(R.id.error);




        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        google = findViewById(R.id.send_google);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             register();

            }


        });
        back = findViewById(R.id.back_google);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup_google.this, SignUpActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
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
            Mobile = mobile.getText().toString();


//            Toast.makeText(Signup_google.this, Name, Toast.LENGTH_SHORT).show();
//            Toast.makeText(Signup_google.this, Email, Toast.LENGTH_SHORT).show();
//            Toast.makeText(Signup_google.this, Id, Toast.LENGTH_SHORT).show();
//            Toast.makeText(Signup_google.this, Mobile, Toast.LENGTH_SHORT).show();


            }


        }




    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public  void register(){



        Name = account.getDisplayName();
        Email = account.getEmail();
        Id = account.getId();
        Mobile = mobile.getText().toString();


        String url = "http://nk.inevitabletech.email/public/api/register-with-google";

        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put("email",Email);
            jsonBody.put("google_id", Id);
            jsonBody.put("name", Name);
            jsonBody.put("phone", Mobile);




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
                        email = user.getString("email");
                        name = user.getString("name");
                        goo_id = user.getString("google_id");
                        phonee = user.getString("phone");


                        if (Success == "true") {

                            Toast.makeText(Signup_google.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Signup_google.this, Mobile_verification.class);
                            intent.putExtra("email",email);
//                            intent.putExtra("google_id", goo_id);
                            intent.putExtra("token", token);
                            intent.putExtra( "name", name);
                            intent.putExtra("phone", phonee);
//                            intent.putExtra("token1",token);
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
                    headers.put("Authorization", "Bearer " + PreferenceUtils.getToken(Signup_google.this));
                    return headers;
                }


            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Signup_google.this);
            requestQueue.add(jsonObjectRequest);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}

