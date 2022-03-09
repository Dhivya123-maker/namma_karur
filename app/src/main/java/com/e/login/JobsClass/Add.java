package com.e.login.JobsClass;

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
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Add extends AppCompatActivity {
    EditText j_name,comp_name,gender,address,no_vacancy,qualify,exp,age,salary,skills,about,start,end;
    String J_name,Comp_name,Gender,Address,No_vacancy,Qualify,Exp,Age,Salary,Skills,About,Start,End;
    Button btn;
    String data,data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        Intent intent = getIntent();
        data = intent.getStringExtra("cat");
        data1 = intent.getStringExtra("id");
        Toast.makeText(Add.this, data1, Toast.LENGTH_SHORT).show();



        j_name = findViewById(R.id.NameDetails);
        comp_name = findViewById(R.id.NameDetails1);
        gender = findViewById(R.id.NameDetails2);
        address = findViewById(R.id.NameDetails3);
        no_vacancy = findViewById(R.id.NameDetails5);
        qualify = findViewById(R.id.NameDetails6);
        exp = findViewById(R.id.NameDetails7);
        age = findViewById(R.id.NameDetails8);
        salary = findViewById(R.id.NameDetails9);
        skills = findViewById(R.id.NameDetails10);
        about = findViewById(R.id.NameDetails11);
        start = findViewById(R.id.NameDetails12);
        end = findViewById(R.id.NameDetails13);

        btn = findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_p();





    }
    public void add_p(){

        J_name = j_name.getText().toString();
        Comp_name = comp_name.getText().toString();
        Gender = gender.getText().toString();
        Address = address.getText().toString();
        No_vacancy = no_vacancy.getText().toString();
        Qualify = qualify.getText().toString();
        Exp = exp.getText().toString();
        Age = age.getText().toString();
        Salary = salary.getText().toString();
        Skills = skills.getText().toString();
        About = about.getText().toString();
        Start = start.getText().toString();
        End = end.getText().toString();

        String URL = "http://nk.inevitabletech.email/public/api/post-a-jobs";


        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("job_name",J_name);
            jsonBody.put("company_name",Comp_name );
            jsonBody.put("category_id",data1);
            jsonBody.put("gender",Gender);
            jsonBody.put("address",Address);
            jsonBody.put("no_of_vacancy",No_vacancy);
            jsonBody.put("qualification","[1,2]");
//            jsonBody.put("qualification[1]",Qualify);
            jsonBody.put("experience", Exp );
            jsonBody.put("age_limit",Age);
            jsonBody.put("salary",Salary);
            jsonBody.put("skills","[1,2]");
//            jsonBody.put("skills[1]",Skills);
            jsonBody.put("apply_start_date",Start);
            jsonBody.put("apply_end_date",End);
            jsonBody.put("about_company",About);




      //  final String requestBody = jsonBody.toString();

        Log.i("jhdoq",jsonBody.toString());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,URL,jsonBody,new Response.Listener<JSONObject>() {
                @SuppressLint("CheckResult")
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("0000000000000",response.toString());
                    Toast.makeText(Add.this, response.toString(), Toast.LENGTH_SHORT).show();

                    try{

                        String Success = response.getString("success");
                        String msg  = response.getString("message");


                        if (Success == "true"){

                            Toast.makeText(Add.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Add.this, Jobs.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);


                        }
                        else {




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
                    Log.i("wkjlgroiwt",str);


                }
            }) {
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String, String>();

//                        params.put("job_name",J_name);
//                        params.put("company_name", Comp_name );
//                        params.put("gender",Gender);
//                        params.put("address",Address);
//                        params.put("no_of_vacancy",No_vacancy);
//                       // params.put("qualification[0]",Qualify);
//                        params.put("experience", Exp );
//                        params.put("age_limit",Age);
//                        params.put("salary",Salary);
//                        //params.put("skills[0]",Skills);
//                        params.put("apply_start_date",Start);
//                        params.put("apply_end_date",End);
//                        params.put("about_company",About);
//                        Toast.makeText(Add.this, params.toString(), Toast.LENGTH_SHORT).show();
//                        Log.i("kgjwf9iw3tgy97uoe",params.toString());


                    return params;
                }
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> headers = new HashMap<String, String>();

                    headers.put("Accept","application/json");
                    headers.put("Authorization", "Bearer " + PreferenceUtils.getToken(Add.this));
                    return headers;
                }


            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Add.this);
            requestQueue.add(jsonObjectRequest);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
        });



    }

}