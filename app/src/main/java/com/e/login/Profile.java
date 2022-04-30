package com.e.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
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
import com.bumptech.glide.Glide;
import com.e.login.BlankFragment.Blank_Comments_Adapter;
import com.e.login.Help_Class.Helpline;
import com.e.login.HomeClass.Fragment_Home;
import com.e.login.HomeClass.Home;
import com.e.login.Profile_details.EducationAdapter;
import com.e.login.Profile_details.Education_Model;
import com.e.login.Verification.Email_OTP;
import com.e.login.Verification.Email_Verification;
import com.e.login.Verification.Phone_txt;
import com.e.login.info_Class.InformationFragment;
import com.e.login.utils.PreferenceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile extends AppCompatActivity {
    Button btn, edit, save, email_verify, contact_verify;
    String data, data1, data2, goo_id, dobb;
    TextView user_name, emailtxt, contacttxt;
    LinearLayout skill;
    ImageView  email_edit, con_edit,profile;
    Spinner bl;
    Context mContext;
    int SELECT_PICTURE = 1;

    TextView blood;

    RecyclerView education_recycler,experience_recycler,skill_recycler;

    ProfileAdapter profileAdapter;

    List<ProfileModel> profileModelList1;
    List<ProfileModel> profileModelList2;
    List<ProfileModel> profileModelList3;


    String data3;

    String id, email, phone, namee, email_verifyy, phone_verifyy, image;


    private ListView userDataListView = null;
    // Below edittext and button are all exist in the popup dialog view.
    private View popupInputDialogView = null;
    // Contains user name data.
    private EditText userNameEditText = null;
    // Contains password data.
    private EditText passwordEditText = null;
    // Contains email data.
    private EditText emailEditText = null;
    // Click this button in popup dialog to save user input data in above three edittext.
    private Button saveUserDataButton = null;
    // Click this button to cancel edit user data.
    private Button cancelUserDataButton = null;
    TextView dob;



    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);


        Intent intent = getIntent();
        data = intent.getStringExtra("token");
        data1 = intent.getStringExtra("id");
        data2 = intent.getStringExtra("user_name");
        data3 = intent.getStringExtra("email");


        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_profile);
        btnNav.setOnNavigationItemSelectedListener(navListener);




        get_profile();



        user_name = findViewById(R.id.user_name);


        btn = findViewById(R.id.change_btn);
        blood = findViewById(R.id.blood);
        dob = findViewById(R.id.dob);






        email_edit = findViewById(R.id.edit_email);
        con_edit = findViewById(R.id.edit_phone);

        emailtxt = findViewById(R.id.email_text);
        contacttxt = findViewById(R.id.phone_text);
        email_verify = findViewById(R.id.email_verify);
        contact_verify = findViewById(R.id.contact_verify);

        education_recycler = findViewById(R.id.education_recyclerview);
        experience_recycler = findViewById(R.id.experience_recyclerview);
        skill_recycler = findViewById(R.id.skill_recyclerview);
        profile = findViewById(R.id.profile);


        edit = findViewById(R.id.edit_btn);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Profile.this, EditProfile.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });




        email_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                send_mail();

            }
        });


        contact_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                Intent intent1 = new Intent(Profile.this,VerifyActivity.class);
//                startActivity(intent1);
//                send_mail();
            }
        });

        email_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(Profile.this, Email_Verification.class);
                intent1.putExtra("id", data1);
                intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent1);


            }
        });







        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, ChangePassword.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("token", data);
                intent.putExtra("id", data1);
                startActivity(intent);
            }
        });


    }


    public void send_mail() {


        String url = "http://nk.inevitabletech.email/public/api/sendEmailOtp";
        JSONObject jsonBody = new JSONObject();


        try {
            jsonBody.put("user_id", data1);


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    try {
                        String Success = response.getString("success");
                        String msg = response.getString("message");


                        if (Success.equals("true")) {
                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();

                            Intent intent1 = new Intent(Profile.this, Email_OTP.class);
                            intent1.putExtra("email", data3);
                            intent1.putExtra("id", data1);

                            intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent1);


                        } else {


                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();


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
                        JSONObject data = jsonObject.getJSONObject("data");

                        String err = data.getString("error");
                        Toast.makeText(Profile.this, err, Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Profile.this));
                    return params;


                }
            };

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Profile.this);
            requestQueue.add(jsonObjectRequest);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void get_profile() {

        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-profile-details";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {

                Log.i("00000000",response.toString());

                try {
                    try {
                    profileModelList1 = new ArrayList<>();
                    profileModelList2 = new ArrayList<>();
                    profileModelList3 = new ArrayList<>();

                    String year ;
                    String institute ;
                    String degree ;
                    String company ;
                    String experience_data;
                    String position ;
                    String skills_data;

                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    JSONObject jsonObject = response.getJSONObject("data");
                    id = jsonObject.getString("id");
                    namee = jsonObject.getString("name");
                    email = jsonObject.getString("email");
                    phone = jsonObject.getString("phone");
                    email_verifyy = jsonObject.getString("email_verification_status");
                    phone_verifyy = jsonObject.getString("phone_verification_status");
                    image = jsonObject.getString("image");
                    JSONObject profile_details = jsonObject.getJSONObject("profile_details");

                    String Dob = profile_details.getString("dob");
                    String Blood = profile_details.getString("blood_group");


                        emailtxt.setText(email);
                        contacttxt.setText(phone);
                        user_name.setText(namee);
                        email_verify.setText(email_verifyy);
                        contact_verify.setText(phone_verifyy);
                        blood.setText(Blood);
                        dob.setText(Dob);



                        if (image != "null"){
                            Glide.with(getApplicationContext()).load(image).into(profile);
                        }


                    JSONArray education = profile_details.getJSONArray("education");
                    JSONArray experience =profile_details.getJSONArray("experience");
                    JSONArray skills =profile_details.getJSONArray("skills");









                        for (int i = 0; i<education.length();i++){

                            JSONObject edu = education.getJSONObject(i);

                            year = edu.getString("year");
                            institute = edu.getString("institute");
                            degree = edu.getString("degree");

                        }

                        for (int i = 0; i<education.length();i++){

                            JSONObject edu = education.getJSONObject(i);

                            year = edu.getString("year");
                            institute = edu.getString("institute");
                            degree = edu.getString("degree");

                            ProfileModel profileModel = new ProfileModel();

                            profileModel.setYear(year);
                            profileModel.setInstitute(institute);
                            profileModel.setDegree(degree);
                            profileModel.setType("edu");

                            profileModelList1.add(profileModel);

                            Log.i("fsdfsf",institute);
                        }

                        education_recycler.setLayoutManager(new LinearLayoutManager(Profile.this));
                        profileAdapter = new ProfileAdapter(Profile.this, profileModelList1);
                        education_recycler.setAdapter(profileAdapter);

                        for (int i = 0; i<experience.length();i++){

                            JSONObject edu = experience.getJSONObject(i);

                            company = edu.getString("company");
                            experience_data = edu.getString("experience");
                            position = edu.getString("position");

                            ProfileModel profileModel = new ProfileModel();

                            profileModel.setCompany("Company : "+company);
                            profileModel.setExperience_data("Experience : "+experience_data);
                            profileModel.setPosition("Position : "+position);
                            profileModel.setType("exp");

                            profileModelList2.add(profileModel);

                            Log.i("fsdfsf",company);

                        }

                        experience_recycler.setLayoutManager(new LinearLayoutManager(Profile.this));
                        profileAdapter = new ProfileAdapter(Profile.this, profileModelList2);
                        experience_recycler.setAdapter(profileAdapter);

                        for (int i = 0; i<skills.length();i++){

                            skills_data= skills.getString(i);


                            ProfileModel profileModel = new ProfileModel();

                            profileModel.setSkills_data(skills_data);
                            profileModel.setType("ski");

                            profileModelList3.add(profileModel);

                            Log.i("fsdfsf",skills_data);
                        }

                        skill_recycler.setLayoutManager(new LinearLayoutManager(Profile.this));
                        profileAdapter = new ProfileAdapter(Profile.this, profileModelList3);
                        skill_recycler.setAdapter(profileAdapter);






                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                } catch (Exception e) {
                    e.printStackTrace();


                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


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


                headers.put("Authorization", "Bearer " + PreferenceUtils.getToken(Profile.this));
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Profile.this);
        requestQueue.add(jsonObjectRequest);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            Fragment fragment = null;


            switch (id) {
                case R.id.nav_home:
//                    fragment = new Fragment_Home();
                    Intent in=new Intent(Profile.this, Home.class);
                    startActivity(in);
                    return  true;

//                    break;
                case R.id.nav_tree:
                    fragment = new InformationFragment();
                    break;
                case R.id.nav_qr:
                    fragment = new QrCodeFragment();
                    break;
                case R.id.nav_profilee:

                    fragment = new Helpline();
                    break;
                case R.id.nav_notifications:

                    fragment = new EnquiryFragment();
                    break;


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).commit();

            return true;
        }
    };


}



