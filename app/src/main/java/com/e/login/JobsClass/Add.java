package com.e.login.JobsClass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.EditProfile;
import com.e.login.EnquiryFragment;
import com.e.login.Help_Class.Helpline;
import com.e.login.HomeClass.Fragment_Home;
import com.e.login.QrCodeFragment;
import com.e.login.R;
import com.e.login.SignUpActivity;
import com.e.login.VolleyMultipartRequest;
import com.e.login.info_Class.InformationFragment;
import com.e.login.utils.PreferenceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Add extends AppCompatActivity {
    EditText j_name,comp_name,gender,address,no_vacancy,exp,age,salary,about;
    String J_name,Comp_name,Gender,Address,No_vacancy,Exp,Age,Salary,About,Start,End;
    Button btn;
    String data,data1,category_id;
    Calendar calendar;
    DatePickerDialog dd;
    TextView J_err,Co_err,G_err,A_err,No_err,Q_err,Ex_err,Age_err,S_err,Sk_err,About_er,Star_er,En_err,start,end;

    LinearLayout qualification_layoutList, skill_layoutList;
    Button qualification_buttonAdd,  skill_buttonAdd;

    int qua_count = 1;
    int skill_count = 1;


    ArrayList<String> qualification = new ArrayList<>();
    ArrayList<String> skill_name = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        Intent intent = getIntent();
        data = intent.getStringExtra("cat");
        data1 = intent.getStringExtra("id");
        category_id = intent.getStringExtra("category_id");

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_profile);
        btnNav.setOnNavigationItemSelectedListener(navListener);



        J_err = findViewById(R.id.error1);
        Co_err = findViewById(R.id.error2);
        G_err = findViewById(R.id.error3);
        A_err = findViewById(R.id.error4);
        No_err = findViewById(R.id.error5);
        Q_err = findViewById(R.id.error6);
        Ex_err = findViewById(R.id.error7);
        Age_err = findViewById(R.id.error8);
        S_err = findViewById(R.id.error9);
        Sk_err = findViewById(R.id.error10);
        About_er = findViewById(R.id.error11);
        Star_er = findViewById(R.id.error12);
        En_err = findViewById(R.id.error13);


        j_name = findViewById(R.id.NameDetails);
        comp_name = findViewById(R.id.NameDetails1);
        gender = findViewById(R.id.NameDetails2);
        address = findViewById(R.id.NameDetails3);
        no_vacancy = findViewById(R.id.NameDetails5);
        exp = findViewById(R.id.NameDetails7);
        age = findViewById(R.id.NameDetails8);
        salary = findViewById(R.id.NameDetails9);
        about = findViewById(R.id.NameDetails11);
        start = findViewById(R.id.NameDetails12);
        end = findViewById(R.id.NameDetails13);

        qualification_layoutList = findViewById(R.id.qualification_layout_list);
        qualification_buttonAdd = findViewById(R.id.qualification_button_add);

        skill_layoutList = findViewById(R.id.skill_layout_list);
        skill_buttonAdd = findViewById(R.id.skill_button_add);

        qualification_buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Qualification_addView();
            }
        });

        skill_buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skill_addView();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                dd = new DatePickerDialog(Add.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {

                        start.setText(mYear+"/"+ (mMonth+1) + "/" + mDay);


                    }
                },day,month,year);

                dd.show();

            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                dd = new DatePickerDialog(Add.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {

                        end.setText(mYear+"-"+ (mMonth+1) + "-" + mDay);


                    }
                },day,month,year);

                dd.show();

            }
        });

        btn = findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_p();





    }
    public void add_p(){
//
//        J_name = j_name.getText().toString();
//        Comp_name = comp_name.getText().toString();
//        Gender = gender.getText().toString();
//        Address = address.getText().toString();
//        No_vacancy = no_vacancy.getText().toString();
//        Qualify = qualify.getText().toString();
//        Exp = exp.getText().toString();
//        Age = age.getText().toString();
//        Salary = salary.getText().toString();
//        Skills = skills.getText().toString();
//        About = about.getText().toString();
//        Start = start.getText().toString();
//        End = end.getText().toString();
//
//        String URL = "http://nk.inevitabletech.email/public/api/post-a-jobs";
//
//
//
//        JSONObject jsonBody = new JSONObject();
//
//        try {
//            jsonBody.put("job_name",J_name);
//            jsonBody.put("company_name",Comp_name );
//           jsonBody.put("category_id",category_id);
//            jsonBody.put("gender",Gender);
//            jsonBody.put("address",Address);
//            jsonBody.put("no_of_vacancy",No_vacancy);
//            jsonBody.put("qualification[0]",Qualify);
////            jsonBody.put("qualification[1]",Qualify);
//            jsonBody.put("experience", Exp );
//            jsonBody.put("age_limit",Age);
//            jsonBody.put("salary",Salary);
//            jsonBody.put("skills[0]",Skills);
////            jsonBody.put("skills[1]",Skills);
//            jsonBody.put("apply_start_date",Start);
//            jsonBody.put("apply_end_date",End);
//            jsonBody.put("about_company",About);
//
//
//
//
//
//
//        Log.i("jhdoq",jsonBody.toString());
//
//            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,URL,jsonBody,new Response.Listener<JSONObject>() {
//                @SuppressLint("CheckResult")
//                @Override
//                public void onResponse(JSONObject response) {
//
//
//                    try{
//
//                        String Success = response.getString("success");
//                        String msg  = response.getString("message");
//
//
//                        if (Success == "true"){
//
//                            Toast.makeText(Add.this, msg, Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(Add.this, Jobs.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                            startActivity(intent);
//
//
//                        }
//                        else {
//
//
//
//
//                        }
//
//
//                    }catch (Exception e) {
//                        e.printStackTrace();
//
//                    }
//
//
//
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Charset charset = Charset.defaultCharset();
//                    String str = new String(error.networkResponse.data,charset);
//
//
//                    try {
//                        JSONObject jsonObject = new JSONObject(str);
//                        JSONObject data = jsonObject.getJSONObject("errors");
//                        Log.i("sdsa",data.toString());
//                        if (J_name.isEmpty()) {
//
//
//                            JSONArray jsonArray = data.getJSONArray("job_name");
//                            J_err.setText(jsonArray.getString(0));
//                            J_err.setVisibility(View.VISIBLE);
//                            Co_err.setVisibility(View.GONE);
//                            G_err.setVisibility(View.GONE);
//                            A_err.setVisibility(View.GONE);
//                            No_err.setVisibility(View.GONE);
//                            Q_err.setVisibility(View.GONE);
//                            Ex_err.setVisibility(View.GONE);
//                            Age_err.setVisibility(View.GONE);
//                            S_err.setVisibility(View.GONE);
//                            Sk_err.setVisibility(View.GONE);
//                            About_er.setVisibility(View.GONE);
//                            Star_er.setVisibility(View.GONE);
//                            En_err.setVisibility(View.GONE);
//
//
//                        }
//
//                        else if (Comp_name.isEmpty()) {
//                            JSONArray jsonArray1 = data.getJSONArray("company_name");
//                            Co_err.setText(jsonArray1.getString(0));
//                            Co_err.setVisibility(View.VISIBLE);
//                            J_err.setVisibility(View.GONE);
//                            G_err.setVisibility(View.GONE);
//                            A_err.setVisibility(View.GONE);
//                            No_err.setVisibility(View.GONE);
//                            Q_err.setVisibility(View.GONE);
//                            Ex_err.setVisibility(View.GONE);
//                            Age_err.setVisibility(View.GONE);
//                            S_err.setVisibility(View.GONE);
//                            Sk_err.setVisibility(View.GONE);
//                            About_er.setVisibility(View.GONE);
//                            Star_er.setVisibility(View.GONE);
//                            En_err.setVisibility(View.GONE);
//                        }
//
//                        else if (Gender.isEmpty() ) {
//                            JSONArray jsonArray2 = data.getJSONArray("gender");
//                            G_err.setText(jsonArray2.getString(0));
//                            G_err.setVisibility(View.VISIBLE);
//                            Co_err.setVisibility(View.GONE);
//                            J_err.setVisibility(View.GONE);
//                            A_err.setVisibility(View.GONE);
//                            No_err.setVisibility(View.GONE);
//                            Q_err.setVisibility(View.GONE);
//                            Ex_err.setVisibility(View.GONE);
//                            Age_err.setVisibility(View.GONE);
//                            S_err.setVisibility(View.GONE);
//                            Sk_err.setVisibility(View.GONE);
//                            About_er.setVisibility(View.GONE);
//                            Star_er.setVisibility(View.GONE);
//                            En_err.setVisibility(View.GONE);
//
//
//
//                        }
//                        else if (Address.isEmpty()) {
//                            JSONArray jsonArray3 = data.getJSONArray("address");
//                            A_err.setText(jsonArray3.getString(0));
//                            A_err.setVisibility(View.VISIBLE);
//                            Co_err.setVisibility(View.GONE);
//                            J_err.setVisibility(View.GONE);
//                            G_err.setVisibility(View.GONE);
//                            No_err.setVisibility(View.GONE);
//                            Q_err.setVisibility(View.GONE);
//                            Ex_err.setVisibility(View.GONE);
//                            Age_err.setVisibility(View.GONE);
//                            S_err.setVisibility(View.GONE);
//                            Sk_err.setVisibility(View.GONE);
//                            About_er.setVisibility(View.GONE);
//                            Star_er.setVisibility(View.GONE);
//                            En_err.setVisibility(View.GONE);
//
//
//                        }else if(No_vacancy.isEmpty()){
//                            JSONArray jsonArray4 = data.getJSONArray("no_of_vacancy");
//                            No_err.setText(jsonArray4.getString(0));
//                            No_err.setVisibility(View.VISIBLE);
//                            J_err.setVisibility(View.GONE);
//                            Co_err.setVisibility(View.GONE);
//                            G_err.setVisibility(View.GONE);
//                            A_err.setVisibility(View.GONE);
//                            Q_err.setVisibility(View.GONE);
//                            Ex_err.setVisibility(View.GONE);
//                            Age_err.setVisibility(View.GONE);
//                            S_err.setVisibility(View.GONE);
//                            Sk_err.setVisibility(View.GONE);
//                            About_er.setVisibility(View.GONE);
//                            Star_er.setVisibility(View.GONE);
//                            En_err.setVisibility(View.GONE);
//
//
//                        }else if(Qualify.isEmpty()){
//                            JSONArray jsonArray5 = data.getJSONArray("qualification");
//                            Q_err.setText(jsonArray5.getString(0));
//                            Q_err.setVisibility(View.VISIBLE);
//                            J_err.setVisibility(View.GONE);
//                            Co_err.setVisibility(View.GONE);
//                            G_err.setVisibility(View.GONE);
//                            A_err.setVisibility(View.GONE);
//                            No_err.setVisibility(View.GONE);
//                            Ex_err.setVisibility(View.GONE);
//                            Age_err.setVisibility(View.GONE);
//                            S_err.setVisibility(View.GONE);
//                            Sk_err.setVisibility(View.GONE);
//                            About_er.setVisibility(View.GONE);
//                            Star_er.setVisibility(View.GONE);
//                            En_err.setVisibility(View.GONE);
//
//                        }else if(Exp.isEmpty()){
//                            JSONArray jsonArray6 = data.getJSONArray("experience");
//                            Ex_err.setText(jsonArray6.getString(0));
//                            Ex_err.setVisibility(View.VISIBLE);
//                            J_err.setVisibility(View.GONE);
//                            Co_err.setVisibility(View.GONE);
//                            G_err.setVisibility(View.GONE);
//                            A_err.setVisibility(View.GONE);
//                            No_err.setVisibility(View.GONE);
//                            Q_err.setVisibility(View.GONE);
//                            Age_err.setVisibility(View.GONE);
//                            S_err.setVisibility(View.GONE);
//                            Sk_err.setVisibility(View.GONE);
//                            About_er.setVisibility(View.GONE);
//                            Star_er.setVisibility(View.GONE);
//                            En_err.setVisibility(View.GONE);
//
//
//                        }
//                        else if(Age.isEmpty() ){
//                            JSONArray jsonArray7 = data.getJSONArray("age_limit");
//                            Age_err.setText(jsonArray7.getString(0));
//                            Age_err.setVisibility(View.VISIBLE);
//                            J_err.setVisibility(View.GONE);
//                            Co_err.setVisibility(View.GONE);
//                            G_err.setVisibility(View.GONE);
//                            A_err.setVisibility(View.GONE);
//                            No_err.setVisibility(View.GONE);
//                            Q_err.setVisibility(View.GONE);
//                            Ex_err.setVisibility(View.GONE);
//                            S_err.setVisibility(View.GONE);
//                            Sk_err.setVisibility(View.GONE);
//                            About_er.setVisibility(View.GONE);
//                            Star_er.setVisibility(View.GONE);
//                            En_err.setVisibility(View.GONE);
//
//                        }
//                        else if(Salary.isEmpty() ){
//                            JSONArray jsonArray8 = data.getJSONArray("salary");
//                            S_err.setText(jsonArray8.getString(0));
//                            S_err.setVisibility(View.VISIBLE);
//                            J_err.setVisibility(View.GONE);
//                            Co_err.setVisibility(View.GONE);
//                            G_err.setVisibility(View.GONE);
//                            A_err.setVisibility(View.GONE);
//                            No_err.setVisibility(View.GONE);
//                            Q_err.setVisibility(View.GONE);
//                            Ex_err.setVisibility(View.GONE);
//                            Age_err.setVisibility(View.GONE);
//                            Sk_err.setVisibility(View.GONE);
//                            About_er.setVisibility(View.GONE);
//                            Star_er.setVisibility(View.GONE);
//                            En_err.setVisibility(View.GONE);
//
//
//                        } else if(Skills.isEmpty() ){
//                            JSONArray jsonArray9 = data.getJSONArray("skills[0]");
//                            Sk_err.setText(jsonArray9.getString(0));
//                            Sk_err.setVisibility(View.VISIBLE);
//                            J_err.setVisibility(View.GONE);
//                            Co_err.setVisibility(View.GONE);
//                            G_err.setVisibility(View.GONE);
//                            A_err.setVisibility(View.GONE);
//                            No_err.setVisibility(View.GONE);
//                            Q_err.setVisibility(View.GONE);
//                            Ex_err.setVisibility(View.GONE);
//                            Age_err.setVisibility(View.GONE);
//                            S_err.setVisibility(View.GONE);
//                            About_er.setVisibility(View.GONE);
//                            Star_er.setVisibility(View.GONE);
//                            En_err.setVisibility(View.GONE);
//
//                        }
//                        else if(About.isEmpty() ){
//                            JSONArray jsonArray10 = data.getJSONArray("about");
//                            About_er.setText(jsonArray10.getString(0));
//                            About_er.setVisibility(View.VISIBLE);
//                            J_err.setVisibility(View.GONE);
//                            G_err.setVisibility(View.GONE);
//                            Co_err.setVisibility(View.GONE);
//                            A_err.setVisibility(View.GONE);
//                            No_err.setVisibility(View.GONE);
//                            Q_err.setVisibility(View.GONE);
//                            Ex_err.setVisibility(View.GONE);
//                            Age_err.setVisibility(View.GONE);
//                            S_err.setVisibility(View.GONE);
//                            Sk_err.setVisibility(View.GONE);
//                            Star_er.setVisibility(View.GONE);
//                            En_err.setVisibility(View.GONE);
//
//                        }
//
//                        else if(Start.isEmpty() ){
//                            JSONArray jsonArray11 = data.getJSONArray("apply_start_date");
//                            Star_er.setText(jsonArray11.getString(0));
//                            Star_er.setVisibility(View.VISIBLE);
//                            J_err.setVisibility(View.GONE);
//                            Co_err.setVisibility(View.GONE);
//                            G_err.setVisibility(View.GONE);
//                            A_err.setVisibility(View.GONE);
//                            No_err.setVisibility(View.GONE);
//                            Q_err.setVisibility(View.GONE);
//                            Ex_err.setVisibility(View.GONE);
//                            Age_err.setVisibility(View.GONE);
//                            S_err.setVisibility(View.GONE);
//                            Sk_err.setVisibility(View.GONE);
//                            About_er.setVisibility(View.GONE);
//                            En_err.setVisibility(View.GONE);
//
//                        }
//
//                        else if(End.isEmpty() ){
//                            JSONArray jsonArray12 = data.getJSONArray("apply_end_date");
//                            En_err.setText(jsonArray12.getString(0));
//                            En_err.setVisibility(View.VISIBLE);
//                            J_err.setVisibility(View.GONE);
//                            G_err.setVisibility(View.GONE);
//                            Co_err.setVisibility(View.GONE);
//                            A_err.setVisibility(View.GONE);
//                            No_err.setVisibility(View.GONE);
//                            Q_err.setVisibility(View.GONE);
//                            Ex_err.setVisibility(View.GONE);
//                            Age_err.setVisibility(View.GONE);
//                            S_err.setVisibility(View.GONE);
//                            Sk_err.setVisibility(View.GONE);
//                            About_er.setVisibility(View.GONE);
//                            Star_er.setVisibility(View.GONE);
//
//
//
//                        }
//
//
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//
//
//
//                }
//            }) {
//                @Override
//                protected Map<String,String> getParams(){
//                    Map<String,String> params = new HashMap<String, String>();
//
//
//                    return params;
//                }
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String,String> headers = new HashMap<String, String>();
//
//                    headers.put("Accept","application/json");
//                    headers.put("Authorization", "Bearer " + PreferenceUtils.getToken(Add.this));
//                    return headers;
//                }
//
//
//            };
//            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
//                    10000,
//                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//            RequestQueue requestQueue = Volley.newRequestQueue(Add.this);
//            requestQueue.add(jsonObjectRequest);
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        upload();

            }
        });



    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            Fragment fragment = null;

            switch (id) {
                case R.id.nav_home:
                    fragment = new Fragment_Home();
                    break;
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

    private void upload() {

        String URL = "http://nk.inevitabletech.email/public/api/post-a-jobs";

        J_name = j_name.getText().toString();
        Comp_name = comp_name.getText().toString();
        Gender = gender.getText().toString();
        Address = address.getText().toString();
        No_vacancy = no_vacancy.getText().toString();
        Exp = exp.getText().toString();
        Age = age.getText().toString();
        Salary = salary.getText().toString();
        About = about.getText().toString();
        Start = start.getText().toString();
        End = end.getText().toString();



        if (checkIfValidAndRead()) {


            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, URL,
                    new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response) {


                            try {
                                JSONObject obj = new JSONObject(new String(response.data));
                                String msg = obj.getString("message");

                                Toast.makeText(Add.this, msg, Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(Add.this,Jobs.class);
                                startActivity(i);

                                Log.i("kkkkk", msg.toString());

                            } catch (Exception e) {
                                e.printStackTrace();

                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Charset charset = Charset.defaultCharset();
                            String str = new String(error.networkResponse.data, charset);


                            try {
                                JSONObject jsonObject = new JSONObject(str);
                                JSONObject data = jsonObject.getJSONObject("errors");
                                Log.i("kkkkk", jsonObject.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    params.put("Authorization", "Bearer " + PreferenceUtils.getToken(Add.this));
                    return params;
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();


                    params.put("job_name",J_name);
                    params.put("company_name",Comp_name );
                    params.put("category_id",category_id);
                    params.put("gender",Gender);
                    params.put("address",Address);
                    params.put("no_of_vacancy",No_vacancy);
                    params.put("experience", Exp );
                    params.put("age_limit",Age);
                    params.put("salary",Salary);
                    params.put("apply_start_date",Start);
                    params.put("apply_end_date",End);
                    params.put("about_company",About);

                    for (int i = 0; i < qualification_layoutList.getChildCount(); i++) {


                        params.put("qualification[" + i + "]", skill_name.get(i));

                    }

                    for (int k = 0; k < skill_layoutList.getChildCount(); k++) {

                        params.put("skills[" + k + "]", skill_name.get(k));

//                        params.put("education[" + i + "][degree]", degree_name.get(i));
//                        params.put("education[" + i + "][year]", institute_name.get(i));

                    }




                    return params;
                }

                /*
                 * Here we are passing image by renaming it with a unique name
                 * */

            };

            //adding the request to volley
            Volley.newRequestQueue(this).add(volleyMultipartRequest);


            JSONObject jsonBody = new JSONObject();



//
//            try {
//                jsonBody.put("name", "Email");
//
//                for (int k = 0; k < skill_layoutList.getChildCount(); k++) {
//
//
//                        String success = skill_name.get(k);
//
//                    jsonBody.put("skills["+k+"]", success);
//
//
//                    }
//
//
////                jsonBody.put("skills["+0+"]", "Password");
////                jsonBody.put("skills["+1+"]", "d_id");
//
//
//
//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//
//
//                        try {
//
//                            String msg = response.getString("message");
//
//
//                            Log.i("ssff",response.toString());
//
//                                Toast.makeText(EditProfile.this, msg, Toast.LENGTH_SHORT).show();
//
//
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//
//
//
//
//                        try {
//                            Charset charset = Charset.defaultCharset();
//                            String str = new String(error.networkResponse.data,charset);
//                            JSONObject jsonObject = new JSONObject(str);
//                            JSONObject data = jsonObject.getJSONObject("data");
//
//                            Log.i("fjfifeofsdf",jsonObject.toString());
//                            JSONArray jsonArray1 = data.getJSONArray("email");
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//
//
//                    }
//                }){
//                    @Override
//                    public Map<String, String> getHeaders() throws AuthFailureError {
//                        Map<String,String> params = new HashMap<String, String>();
//                        params.put("Accept","application/json");
//                        params.put("Authorization", "Bearer " + PreferenceUtils.getToken(EditProfile.this));
//
//                        return params;
//                    }
//
//                };
//
//                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
//                        10000,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//                RequestQueue requestQueue = Volley.newRequestQueue(EditProfile.this);
//                requestQueue.add(jsonObjectRequest);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }

    }


    private void Qualification_addView() {

        final View QualificationView = getLayoutInflater().inflate(R.layout.job_qulification_layout, null, false);

        EditText position = (EditText) QualificationView.findViewById(R.id.qualification);
        TextView count_text = (TextView) QualificationView.findViewById(R.id.number);
        ImageView imageClose = (ImageView) QualificationView.findViewById(R.id.image_remove);


        count_text.setText(String.valueOf(qua_count));

        qua_count++;


        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qua_removeView(QualificationView);
            }
        });

        qualification_layoutList.addView(QualificationView);
    }

    private void skill_addView() {

        final View skillView = getLayoutInflater().inflate(R.layout.skill_layout, null, false);

        EditText skill = (EditText) skillView.findViewById(R.id.skill);
        TextView count_text = (TextView) skillView.findViewById(R.id.number);
        ImageView imageClose = (ImageView) skillView.findViewById(R.id.image_remove);


        count_text.setText(String.valueOf(skill_count));

        skill_count++;


        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skill_removeView(skillView);
            }
        });

        skill_layoutList.addView(skillView);
    }

    private void qua_removeView(View view) {

        qualification_layoutList.removeView(view);
        qua_count--;

    }


    private void skill_removeView(View view) {

        skill_layoutList.removeView(view);
        skill_count--;

    }


    private boolean checkIfValidAndRead() {

        qualification.clear();

        skill_name.clear();

        boolean result = true;



        for (int i = 0; i < qualification_layoutList.getChildCount(); i++) {

            View qualificationView = qualification_layoutList.getChildAt(i);

            EditText qualification = (EditText) qualificationView.findViewById(R.id.qualification);

            TextView error1 = (TextView) qualificationView.findViewById(R.id.error1);

            error1.setVisibility(View.GONE);

            if(!qualification.getText().toString().equals("")){
                skill_name.add(qualification.getText().toString());
            }else {
                result = false;
                error1.setVisibility(View.VISIBLE);
                error1.setText("The skill field is required.");
                break;
            }


        }


        for (int i = 0; i < skill_layoutList.getChildCount(); i++) {

            View skillView = skill_layoutList.getChildAt(i);

            EditText skill = (EditText) skillView.findViewById(R.id.skill);

            TextView error1 = (TextView) skillView.findViewById(R.id.error1);

            error1.setVisibility(View.GONE);

            if(!skill.getText().toString().equals("")){
                skill_name.add(skill.getText().toString());
            }else {
                result = false;
                error1.setVisibility(View.VISIBLE);
                error1.setText("The skill field is required.");
                break;
            }


        }



        return result;
    }

}