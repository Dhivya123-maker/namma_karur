package com.e.login.Verification;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.e.login.Profile;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Edit extends AppCompatActivity {
    EditText dob,b_gp,desc,deg,year,com,exp,pos,skills,add1,add2,add3,add4,add5,add6,add7;
    TextView ins;
    String Dob,B_gp,Desc,Ins,Deg,Year,Com,Exp,Pos,Skills;
    Button btn;
    private LinearLayout parentLayout;
    private int hint=0;
    ImageView add,minus,add_s,minus_s,add_skills,minus_skills;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);


        ins = findViewById(R.id.p_details4);


        dob = findViewById(R.id.p_details1);
        b_gp = findViewById(R.id.p_details2);
        desc = findViewById(R.id.p_details3);
        deg = findViewById(R.id.p_details5);
        exp = findViewById(R.id.p_details8);
        btn = findViewById(R.id.submit_profile);
        add = findViewById(R.id.add);
        minus = findViewById(R.id.minus);
        add_s = findViewById(R.id.add_exp);
        minus_s = findViewById(R.id.minus_exp);
        add_skills = findViewById(R.id.add_skill);
        minus_skills = findViewById(R.id.minus_skill);
        add7 = findViewById(R.id.add7);


        add1 = findViewById(R.id.add1);
        add2 = findViewById(R.id.add2);
        add4 = findViewById(R.id.add4);
        add5 = findViewById(R.id.add5);


        add1.setVisibility(GONE);
        deg.setVisibility(GONE);
        add2.setVisibility(GONE);
        add.setVisibility(View.VISIBLE);
        minus.setVisibility(GONE);
        minus_s.setVisibility(GONE);
        minus_skills.setVisibility(GONE);
        add7.setVisibility(GONE);

        add4.setVisibility(GONE);
        exp.setVisibility(GONE);
        add5.setVisibility(GONE);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                minus.setVisibility(View.VISIBLE);
                add.setVisibility(GONE);
                add1.setVisibility(View.VISIBLE);
                deg.setVisibility(View.VISIBLE);
                add2.setVisibility(View.VISIBLE);


            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add.setVisibility(View.VISIBLE);
                add1.setVisibility(GONE);
                deg.setVisibility(GONE);
                add2.setVisibility(GONE);
                minus.setVisibility(GONE);
            }
        });




        add_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                minus_s.setVisibility(View.VISIBLE);
                add_s.setVisibility(GONE);
                add4.setVisibility(View.VISIBLE);
                exp.setVisibility(View.VISIBLE);
                add5.setVisibility(View.VISIBLE);


            }
        });
        minus_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_s.setVisibility(View.VISIBLE);
                add4.setVisibility(GONE);
                exp.setVisibility(GONE);
                add5.setVisibility(GONE);
                minus_s.setVisibility(GONE);
            }
        });


        add_skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                minus_skills.setVisibility(View.VISIBLE);
                add_skills.setVisibility(GONE);
                add7.setVisibility(View.VISIBLE);


            }
        });
        minus_skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_skills.setVisibility(View.VISIBLE);
                add7.setVisibility(GONE);

                minus_skills.setVisibility(GONE);
            }
        });





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    profile_page();
            }
        });



    }

    public  void profile_page(){

        Dob = dob.getText().toString();
        B_gp = b_gp.getText().toString();
        Ins = add1.getText().toString();
        Desc = desc.getText().toString();
        Deg = deg.getText().toString();
        Year = add2.getText().toString();
        Com= add4.getText().toString();
        Exp = exp.getText().toString();
        Pos= add5.getText().toString();
        Skills = add7.getText().toString();





        String url = "http://nk.inevitabletech.email/public/api/send-profile-details";
        JSONObject jsonBody = new JSONObject();


        try {

            JSONArray education = new JSONArray();
            JSONArray education1 = new JSONArray();
            JSONArray education2 = new JSONArray();

            education.put(Year);
            education1.put(Ins);
            education2.put(Deg);

            jsonBody.put("dob", Dob);
            jsonBody.put("blood_group", B_gp);
            jsonBody.put("description", Desc);
            jsonBody.put("education[0][year]", education);
            jsonBody.put("education[0][institute]",education1);
            jsonBody.put("education[0][degree]", education2);

//            jsonBody.put("education[1][year]", education);
//            jsonBody.put("education[1][institute]",education1);
//            jsonBody.put("education[1][degree]", education2);

            jsonBody.put("experience[0][company]", Com);
            jsonBody.put("experience[0][experience]", Exp);
            jsonBody.put("experience[0][position]", Pos);

//            jsonBody.put("experience[1][company]", Com);
//            jsonBody.put("experience[1][experience]", Exp);
//            jsonBody.put("experience[1][position]", Pos);

            jsonBody.put("skills[0]", Skills);
//            jsonBody.put("skills[1]", Skills);



            Log.i("kjfhriuweryteruitoh",jsonBody.toString());



            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

//                    Log.i("0000000000000",response.toString());
//                    Toast.makeText(Edit.this, response.toString(), Toast.LENGTH_SHORT).show();
//


                    try{
                        String Success = response.getString("success");
                        String msg = response.getString("message");





                        if (Success == "true"){

                            Toast.makeText(Edit.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Edit.this,Profile.class);
                            startActivity(intent);

                        }
                        else {


                            Toast.makeText(Edit.this, msg, Toast.LENGTH_SHORT).show();


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
                    Toast.makeText(Edit.this, str, Toast.LENGTH_SHORT).show();
                    Log.i("ewohfg9uwrytg9",str);
                }
            }){

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Accept","application/json");
                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Edit.this));
                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken1(Edit.this));

                    return params;
                }
            };

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Edit.this);
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    protected void createEditTextView() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.setMargins(0,10,0,10);
        EditText edittTxt = new EditText(this);
        int maxLength = 5;
        hint++;
        edittTxt.setHint("editText"+hint);
        edittTxt.setLayoutParams(params);
        // edtTxt.setBackgroundColor(Color.WHITE);
        edittTxt.setInputType(InputType.TYPE_CLASS_TEXT);
        edittTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        edittTxt.setId(hint);
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        edittTxt.setFilters(fArray);
        parentLayout.addView(edittTxt);
    }
}