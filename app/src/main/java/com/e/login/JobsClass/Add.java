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
import com.e.login.HomeClass.Home;
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
    TextView qualify;
    TextView skill_err;
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
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();

                final DatePickerDialog.OnDateSetListener dateA = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                        start.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);

                    }
                };

                new DatePickerDialog(Add.this, dateA, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();

                final DatePickerDialog.OnDateSetListener dateA = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                        end.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);

                    }
                };

                new DatePickerDialog(Add.this, dateA, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        btn = findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                upload();
                J_err.setVisibility(View.GONE);
                Co_err.setVisibility(View.GONE);
                G_err.setVisibility(View.GONE);
                A_err.setVisibility(View.GONE);
                No_err.setVisibility(View.GONE);
                Q_err.setVisibility(View.GONE);
                Ex_err.setVisibility(View.GONE);
                Age_err.setVisibility(View.GONE);
                S_err.setVisibility(View.GONE);
                Sk_err.setVisibility(View.GONE);
                About_er.setVisibility(View.GONE);
                Star_er.setVisibility(View.GONE);
                En_err.setVisibility(View.GONE);




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
                    Intent intent = new Intent(Add.this, Home.class);
                    startActivity(intent);
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
                                JSONArray jsonArray = data.getJSONArray("job_name");
                                J_err.setText(jsonArray.getString(0));
                                J_err.setVisibility(View.VISIBLE);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                JSONObject jsonObject = new JSONObject(str);
                                JSONObject data = jsonObject.getJSONObject("errors");
                                JSONArray jsonArray = data.getJSONArray("company_name");
                                Co_err.setText(jsonArray.getString(0));
                                Co_err.setVisibility(View.VISIBLE);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                JSONObject jsonObject = new JSONObject(str);
                                JSONObject data = jsonObject.getJSONObject("errors");
                                JSONArray jsonArray = data.getJSONArray("gender");
                                G_err.setText(jsonArray.getString(0));
                                G_err.setVisibility(View.VISIBLE);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                JSONObject jsonObject = new JSONObject(str);
                                JSONObject data = jsonObject.getJSONObject("errors");
                                JSONArray jsonArray = data.getJSONArray("address");
                                A_err.setText(jsonArray.getString(0));
                                A_err.setVisibility(View.VISIBLE);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                JSONObject jsonObject = new JSONObject(str);
                                JSONObject data = jsonObject.getJSONObject("errors");
                                JSONArray jsonArray = data.getJSONArray("no_of_vacancy");
                                No_err.setText(jsonArray.getString(0));
                                No_err.setVisibility(View.VISIBLE);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {

                                JSONObject jsonObject = new JSONObject(str);
                                JSONObject data = jsonObject.getJSONObject("errors");
                                JSONArray jsonArray = data.getJSONArray("qualification");
                                Q_err.setText(jsonArray.getString(0));
                                Q_err.setVisibility(View.VISIBLE);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                JSONObject jsonObject = new JSONObject(str);
                                JSONObject data = jsonObject.getJSONObject("errors");
                                JSONArray jsonArray = data.getJSONArray("experience");
                                Ex_err.setText(jsonArray.getString(0));
                                Ex_err.setVisibility(View.VISIBLE);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                JSONObject jsonObject = new JSONObject(str);
                                JSONObject data = jsonObject.getJSONObject("errors");
                                JSONArray jsonArray = data.getJSONArray("age_limit");
                                Age_err.setText(jsonArray.getString(0));
                                Age_err.setVisibility(View.VISIBLE);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                JSONObject jsonObject = new JSONObject(str);
                                JSONObject data = jsonObject.getJSONObject("errors");
                                JSONArray jsonArray = data.getJSONArray("salary");
                                S_err.setText(jsonArray.getString(0));
                                S_err.setVisibility(View.VISIBLE);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                JSONObject jsonObject = new JSONObject(str);
                                JSONObject data = jsonObject.getJSONObject("errors");
                                JSONArray jsonArray = data.getJSONArray("skills");
                                Sk_err.setText(jsonArray.getString(0));
                                Sk_err.setVisibility(View.VISIBLE);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {



                                JSONObject jsonObject = new JSONObject(str);
                                JSONObject data = jsonObject.getJSONObject("errors");
                                JSONArray jsonArray = data.getJSONArray("apply_start_date");
                                Star_er.setText(jsonArray.getString(0));
                                Star_er.setVisibility(View.VISIBLE);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                JSONObject jsonObject = new JSONObject(str);
                                JSONObject data = jsonObject.getJSONObject("errors");
                                JSONArray jsonArray = data.getJSONArray("apply_end_date");
                                En_err.setText(jsonArray.getString(0));
                                En_err.setVisibility(View.VISIBLE);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                JSONObject jsonObject = new JSONObject(str);
                                JSONObject data = jsonObject.getJSONObject("errors");
                                JSONArray jsonArray = data.getJSONArray("about_company");
                                About_er.setText(jsonArray.getString(0));
                                About_er.setVisibility(View.VISIBLE);


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


                    }




                    return params;
                }



            };

            //adding the request to volley
            Volley.newRequestQueue(this).add(volleyMultipartRequest);




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

        qualify = (TextView) qualificationView.findViewById(R.id.error1);

            qualify.setVisibility(View.GONE);

            if(!qualification.getText().toString().equals("")){
                skill_name.add(qualification.getText().toString());
            }else {
                result = false;
                qualify.setVisibility(View.VISIBLE);
                qualify.setText("The qualification field is required.");
                break;
            }


        }

        for (int i = 0; i < skill_layoutList.getChildCount(); i++) {

            View skillView = skill_layoutList.getChildAt(i);

            EditText skill = (EditText) skillView.findViewById(R.id.skill);

         skill_err = (TextView) skillView.findViewById(R.id.error1);
            skill_err.setVisibility(View.GONE);

            if(!skill.getText().toString().equals("")){
                skill_name.add(skill.getText().toString());
            }else {
                result = false;
                skill_err.setVisibility(View.VISIBLE);
                skill_err.setText("The skill field is required.");
                break;
            }


        }



        return result;
    }

}