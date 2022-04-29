package com.e.login.BloodClass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.ChatFeature;
import com.e.login.EnquiryFragment;
import com.e.login.Help_Class.Helpline;
import com.e.login.HomeClass.Fragment_Home;
import com.e.login.HomeClass.Home;
import com.e.login.JobsClass.Jobs;
import com.e.login.QrCodeFragment;
import com.e.login.R;
import com.e.login.SignUpActivity;
import com.e.login.info_Class.InformationFragment;
import com.e.login.utils.PreferenceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Blood_One extends AppCompatActivity {

    Button submit;
    EditText patient, problem, need, units, hospital, address, num, alternate_num;
    Spinner blood;
    String data, data1;
    String Patient, Blood, Problem, Need, Units, Hospital, Address, Num, Alternate_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_one);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        Intent intent = getIntent();
        data = intent.getStringExtra("cat");


        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_blood1);
        btnNav.setOnNavigationItemSelectedListener(navListener);


        patient = findViewById(R.id.edit1);
        blood = findViewById(R.id.edit2);
        problem = findViewById(R.id.edit3);
        need = findViewById(R.id.edit4);
        units = findViewById(R.id.edit5);
        hospital = findViewById(R.id.edit6);
        address = findViewById(R.id.edit7);
        num = findViewById(R.id.edit8);
        alternate_num = findViewById(R.id.edit9);

        submit = findViewById(R.id.sub_btn);

        ArrayList sname = new ArrayList();


        sname.add("Blood Group");

        sname.add("a1_positive");
        sname.add("a1_negative");
        sname.add("a2_positive");
        sname.add("a2_negative");
        sname.add("b_positive");
        sname.add("b_negative");
        sname.add("a1b_positive");
        sname.add("a1b_negative");
        sname.add("a2b_positive");
        sname.add("a2b_negative");
        sname.add("ab_positive");
        sname.add("ab_negative");
        sname.add("o_positive");
        sname.add("o_negative");
        sname.add("a_positive");
        sname.add("a_negative");



        ArrayAdapter<String> Adapter1 = new ArrayAdapter<String>(Blood_One.this,
                R.layout.text_color1,sname);
        Adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood.setAdapter(Adapter1);




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                blood();

            }
        });


    }


    public void blood(){

        Patient = patient.getText().toString();
        Blood = blood.getSelectedItem().toString();
        Problem = problem.getText().toString();
        Need = need.getText().toString();
        Units = units.getText().toString();
        Hospital = hospital.getText().toString();
        Address = address.getText().toString();
        Num = num.getText().toString();
        Alternate_num = alternate_num.getText().toString();


        String JSON_URL = "http://nk.inevitabletech.email/public/api/blood-request-register";

        JSONObject jsonBody = new JSONObject();


        try {
            jsonBody.put("patient_name", Patient);
            jsonBody.put("blood_group", Blood);
            jsonBody.put("problem", Problem);
            jsonBody.put("needed_within", Need);
            jsonBody.put("no_of_units", Units);
            jsonBody.put("hospital", Hospital);
            jsonBody.put("address", Address);
            jsonBody.put("primary_contact", Num);
            jsonBody.put("secondary_contact", Alternate_num);



            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, JSON_URL, jsonBody, new Response.Listener<JSONObject>() {
                @SuppressLint("CheckResult")
                @Override
                public void onResponse(JSONObject response) {



                    try {



                        String Success = response.getString("success");
                        String msg = response.getString("message");



                        if(Success.equals("true")){
                            Log.i("123",msg);
                            Toast.makeText(Blood_One.this, msg, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Blood_One.this, Blood_Fragment.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);


                        }else{
                            Log.i("1234",msg);
                            Toast.makeText(Blood_One.this, msg, Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {
                        e.printStackTrace();


                    }


                }


            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    try {
                        Charset charset = Charset.defaultCharset();
                        String str = new String(error.networkResponse.data,charset);



                        JSONObject jsonObject = new JSONObject(str);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }
            }){

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("Authorization", "Bearer " + PreferenceUtils.getToken(Blood_One.this));
                    return params;
                }
            };

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            RequestQueue requestQueue = Volley.newRequestQueue(Blood_One.this);
            requestQueue.add(jsonObjectRequest);




        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            Fragment fragment = null;

            switch (id) {
                case R.id.nav_home:
                    Intent intent = new Intent(Blood_One.this, Home.class);
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
                    fragment = new ChatFeature();
                    break;


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).commit();

            return true;
        }
    };
}
