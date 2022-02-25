package com.e.login.BloodClass;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.R;
import com.e.login.ShopClass.ShopScreen_Class;
import com.e.login.SignUpActivity;
import com.e.login.Verification.VerifyActivity;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Blood_One extends AppCompatActivity {

    Button submit;
    EditText patient, blood, problem, need, units, hospital, address, num, alternate_num;
    String data, data1;
    String Patient, Blood, Problem, Need, Units, Hospital, Address, Num, Alternate_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_one);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        Intent intent = getIntent();
        data = intent.getStringExtra("token");
        data1 = intent.getStringExtra("id");


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




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                blood();
//

//

            }
        });


    }


    public void blood(){

        Patient = patient.getText().toString();
        Blood = blood.getText().toString();
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



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


                Log.i("0000000",response.toString());
                Toast.makeText(Blood_One.this, response.toString(), Toast.LENGTH_SHORT).show();

                try {



                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    if(Success.equals("true")){
                        Log.i("123",msg);
                        Toast.makeText(Blood_One.this, msg, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Blood_One.this, Blood_Fragment.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);


                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(Blood_One.this, msg, Toast.LENGTH_SHORT).show();
                    }
//

                } catch (Exception e) {
                    e.printStackTrace();


                }


            }

//
//
//        }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();



                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();

                params.put("Authorization", "Bearer " + PreferenceUtils.getToken(Blood_One.this));
                // params.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWUzNWRjYjg2ZjdjZGUwZTFmOTA5ZmM5YWQwMDdjZGI3ZTE1MzZkNjBhMTEyOTE2ZmQwYmQ0NjE5NzA5ZGYyNTQ3ZDQ5MTFkYzFiNmZkZDUiLCJpYXQiOjE2NDQyMjc1NzYuOTM5ODYyLCJuYmYiOjE2NDQyMjc1NzYuOTM5ODY3LCJleHAiOjE2NzU3NjM1NzYuOTMzMDM2LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.ggB8UvdkfGXmcnlz3KMShC00i-IEhJhq9UwYEq4Oagb73MxNm2WvllC_STJe2wD3FOZnpiYVul_crgERXcxh7C2LHK3UKLmsRSQfxSHHUs1nACk-KFalrcx-llruus8JYwTIjbWccyPWTljJI28aKlBApgfqivUEX0FveiE_LiJQpqSmpiMyojNSlJgN-ofZQc4vuHLdWUtNs-uTRjVpPyz9xw1zEVsPoy3EyVIZ321wlG9ZHGBzUTihuOEHpg0qsCOz_6dJOhQ4CQltWBrg6SJn0_QJ7qBaiMAITQbou2ebemuh945uapuqUCXJVbdFzsMTU2B-JOYoq2G2FrTdaxs_vxCO4ZENoPKFM1Vv-T1HPNnLeAv3Nsuhil5ou-2-uCHsn0tWsPn4zknlwIOulJNs8FbFDcmOG7Hqb8CwlZ-ihp5garS5QPcZxNvC5Qcay6Vijmq93snrR5rgPlq_hW-VFyOm7ZJKkv7uLIfJR529U310wP88Dv68FoOpmlpauO3iuyXt8qwhd_TIwbQM_EanLgz5jWsqtcSsTeMvVpdM8SL-tl_G2b-wjViP4vKqvgiSExZquMahW5yYUdTRN1vtlZ5U0jiQwGMAhKNs45AGgfHcXw68hAigKUQ_qbV7IAIwlun0T1fDvnbfU7tBeTRuX1yJVISTC0k-k4_H8lM");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Blood_One.this);
        requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
