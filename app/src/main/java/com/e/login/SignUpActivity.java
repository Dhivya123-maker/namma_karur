package com.e.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.Verification.Signup_google;
import com.e.login.Verification.VerificationActivity;
import com.e.login.Verification.VerifyActivity;
import com.e.login.utils.PreferenceUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {
    Button signupbtn;
    TextView signin;
    ImageView show_pass_btn_reg,signupback;
    EditText password, name, email, phone;
    String Password, Name, Email, Phone;
    private long pressedTime;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";

    JSONObject data = null, user = null;
    String id = null;
    String token = null;
    String phonee = null;

    int RC_SIGN_IN = 0;
    LinearLayout reg;
    GoogleSignInClient mGoogleSignInClient;


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


        //Initializing Views


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this,LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);

            }
        });

        reg = findViewById(R.id.reg_google);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActivity.this, Signup_google.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);


               // signIn();
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


////        // Configure sign-in to request the user's ID, email address, and basic
////        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
////
////        // Build a GoogleSignInClient with the options specified by gso.
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//
//
//        signupback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SignUpActivity.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);
//
//
//            }
//        });
//    }
////
//    private void signIn() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }
//
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//            // Signed in successfully, show authenticated UI.
//            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
//        } catch (ApiException e) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
//            Toast.makeText(SignUpActivity.this, "Failed", Toast.LENGTH_LONG).show();
//        }
//
//
//
//


//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



//
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

//                    Log.i("0000000000000",response.toString());
//                    Toast.makeText(SignUpActivity.this, response.toString(), Toast.LENGTH_SHORT).show();


//                   Boolean Success ;
//                    String msg;



                    try{
                       String Success = response.getString("success");
                       String msg = response.getString("message");

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
                            Log.i("456",token);
                            Log.i("789",id);
//                            Toast.makeText(SignUpActivity.this, token, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(SignUpActivity.this,id, Toast.LENGTH_SHORT).show();


                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);


                        }
                        else {

                            Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_SHORT).show();


                        }


                    }catch (Exception e) {
                        e.printStackTrace();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


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

//        @Override
//    protected void onStart() {
//        // Check for existing Google Sign In account, if the user is already signed in
//        // the GoogleSignInAccount will be non-null.
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        if(account != null) {
//            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
//        }
//        super.onStart();
//    }


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


    }