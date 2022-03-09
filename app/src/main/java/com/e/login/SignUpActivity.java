package com.e.login;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SignUpActivity extends AppCompatActivity implements OnConnectionFailedListener, GoogleApiClient.OnConnectionFailedListener  {
    private static final int RC_SIGN_IN = 1;
    Button signupbtn;
    TextView signin;
    ImageView show_pass_btn_reg,signupback;
    EditText password, name, email, phone;
    String Password, Name, Email, Phone;
    private long pressedTime;
    String msg = null;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";
    String pass_pattern =" \"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$\";";

    JSONObject data = null, user = null;
    String id = null;
    String token = null;
    String phonee = null;
    String goo_token = null;
    String goo_id = null;



    LinearLayout reg;
    GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient googleApiClient;

    private GoogleSignInOptions gso;


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
//                PreferenceUtils.saveEmail(Email, SignUpActivity.this);
//                PreferenceUtils.savePhone(Phone,SignUpActivity.this);
                startActivity(i);

            }
        });

        reg = findViewById(R.id.reg_google);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (view.getId()) {
                    case R.id.reg_google:
                        signIn();
                        break;

                }



            }
        });


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
////
                register();
//                Intent intent = new Intent(SignUpActivity.this, VerifyActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);

//                Name = name.getText().toString();
//                Email = email.getText().toString();
//                Password = password.getText().toString();
//                Phone = phone.getText().toString();
//
//
//                if (Name.isEmpty()) {
//                    name.setError("Please enter the username");
//                    name.requestFocus();
//                }
//                else if (Email.isEmpty()) {
//                    email.setError("Please enter mail id");
//                    email.requestFocus();
//                }
//                else if (Phone.isEmpty() ) {
//                    phone.setError("Please enter the mobile number");
//                    phone.requestFocus();
//
//
//                } else if (Password.isEmpty()) {
//                    password.setError("Please enter the password");
//                    password.requestFocus();
//                    show_pass_btn_reg.setVisibility(View.INVISIBLE);
//
//                } else if(email.getText().toString().trim().matches(emailPattern) &&
//                        (phone.getText().toString().trim().matches(MobilePattern))){
//                    register();
//
//                }
//                else {
//
//                    Toast.makeText(SignUpActivity.this, "Invalid email id or phone number", Toast.LENGTH_SHORT).show();
//
//                }
//
//
//
//
            }
        });


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

            Toast.makeText(SignUpActivity.this, "Signup successfull", Toast.LENGTH_SHORT).show();


            // Signed in successfully, show authenticated UI.
            Intent intent = new Intent(SignUpActivity.this,Signup_google.class);

            startActivity(intent);
//            startActivity(new Intent(SignUpActivity.this,Signup_google.class));

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(SignUpActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }


      //  GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(SignUpActivity.this);
//        if (acct != null) {
//            String personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
//            String personEmail = acct.getEmail();
//            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();
//        }

//
//


//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



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
//                    Log.i("wkjlgroiwt",str);

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");
                        if (Name.isEmpty()) {

                            JSONArray jsonArray = data.getJSONArray("name");
                            Toast.makeText(SignUpActivity.this, jsonArray.toString(), Toast.LENGTH_SHORT).show();
                            Log.i("wkhgiuktrehg",jsonArray.toString());

                        }

                       else if (Email.isEmpty()) {
                            JSONArray jsonArray1 = data.getJSONArray("email");
                            Toast.makeText(SignUpActivity.this, jsonArray1.toString(), Toast.LENGTH_SHORT).show();
                        }

                        else if (Phone.isEmpty() ) {
                            JSONArray jsonArray3 = data.getJSONArray("phone");
                            Toast.makeText(SignUpActivity.this, jsonArray3.toString(), Toast.LENGTH_SHORT).show();


                        }
                       else if (Password.isEmpty() ) {
                            JSONArray jsonArray2 = data.getJSONArray("password");
                            Toast.makeText(SignUpActivity.this, jsonArray2.toString(), Toast.LENGTH_SHORT).show();


                        }else if(!email.getText().toString().trim().matches(emailPattern) ){
                            JSONArray jsonArray1 = data.getJSONArray("email");
                            Toast.makeText(SignUpActivity.this, jsonArray1.toString(), Toast.LENGTH_SHORT).show();

                        }else if(!phone.getText().toString().trim().matches(MobilePattern) ){
                            JSONArray jsonArray3 = data.getJSONArray("phone");
                            Toast.makeText(SignUpActivity.this, jsonArray3.toString(), Toast.LENGTH_SHORT).show();

                        }else if(!password.getText().toString().trim().matches(pass_pattern) ){
                            JSONArray jsonArray2 = data.getJSONArray("password");
                            Toast.makeText(SignUpActivity.this, jsonArray2.toString(), Toast.LENGTH_SHORT).show();

                        }else  {
                            Toast.makeText(SignUpActivity.this, str, Toast.LENGTH_SHORT).show();

                        }






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


    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }




    }