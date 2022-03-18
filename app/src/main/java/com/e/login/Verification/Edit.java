package com.e.login.Verification;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Edit extends AppCompatActivity {
    EditText dob,b_gp,desc,deg,year,com,exp,pos,skills,inst,yr,add3,add4,add5,add6,add7;
    TextView ins;
    String Dob,B_gp,Desc;
     ArrayList<String> arrayList,arrayList1,arrayList2;
     String  Ins,Deg,Year,Com,Exp,Pos,Skills;
    Button btn;
    private LinearLayout parentLayout;
    private int hint=0;
    ImageView add,minus,add_s,minus_s,add_skills,minus_skills;
    Context myContext;
    LinearLayout lnr,lnr1,lnr2;
    Context context;

   String picture;
    byte[] byteArray;

    Bitmap bitmap;
    int imagevalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);



//        Intent intent = getIntent();
//        byteArray = intent.getByteArrayExtra("image");
//        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray,0, byteArray.length);

//        bitmap bmp = (Bitmap) getIntent().getParcelableExtra("image");
//        Intent intent = getIntent();
//        picture = intent.getStringExtra("image");


//        Toast.makeText(Edit.this, bitmap.toString(), Toast.LENGTH_SHORT).show();


      arrayList = new ArrayList<String>();
      arrayList1 = new ArrayList<>();
      arrayList2 = new ArrayList<>();


        ins = findViewById(R.id.p_details4);

        dob = findViewById(R.id.p_details1);
        b_gp = findViewById(R.id.p_details2);
        desc = findViewById(R.id.p_details3);
        exp = findViewById(R.id.p_details8);
        btn = findViewById(R.id.submit_profile);
        add = findViewById(R.id.add);
        minus = findViewById(R.id.minus);
        add_s = findViewById(R.id.add_exp);
        minus_s = findViewById(R.id.minus_exp);
        add_skills = findViewById(R.id.add_skill);
        minus_skills = findViewById(R.id.minus_skill);
        add7 = findViewById(R.id.add7);


        inst = findViewById(R.id.inst1);
        yr = findViewById(R.id.year);
        deg = findViewById(R.id.p_details5);


        add4 = findViewById(R.id.add4);
        add5 = findViewById(R.id.add5);


        inst.setVisibility(GONE);
        deg.setVisibility(GONE);
        yr.setVisibility(GONE);
        add.setVisibility(View.VISIBLE);
        minus.setVisibility(GONE);
        minus_s.setVisibility(GONE);
        minus_skills.setVisibility(GONE);
        add7.setVisibility(GONE);

        add4.setVisibility(GONE);
        exp.setVisibility(GONE);
        add5.setVisibility(GONE);

        lnr = (LinearLayout)findViewById(R.id.linn);
        lnr1 = (LinearLayout)findViewById(R.id.linn1);
        lnr2 = (LinearLayout)findViewById(R.id.linn2);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                minus.setVisibility(View.VISIBLE);
                add.setVisibility(View.VISIBLE);


                inst = new EditText(Edit.this);
                inst.setHint("Institute");
                inst.setTextSize(14);
                inst.setBackgroundDrawable(getDrawable(R.drawable.border_only));
                inst.setHeight(100);
                inst.setHintTextColor(Color.BLACK);
                inst.setMaxWidth(200);
                inst.setSingleLine(true);
                inst.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                inst.setPadding(30, 20, 20, 20);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0,0,0,20);
                inst.setLayoutParams(lp);


                 lnr.addView(inst);


                deg = new EditText(Edit.this);
                deg.setHint("Degree");
                deg.setTextSize(14);
                deg.setBackgroundDrawable(getDrawable(R.drawable.border_only));
                deg.setHeight(100);
                deg.setHintTextColor(Color.BLACK);
                deg.setMaxWidth(200);
                deg.setSingleLine(true);
                deg.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                deg.setPadding(30, 20, 20, 20);
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp1.setMargins(0,0,0,20);
                deg.setLayoutParams(lp1);


                lnr.addView(deg);

                yr = new EditText(Edit.this);
                yr.setHint("Academic Year");
                yr.setTextSize(14);
                yr.setBackgroundDrawable(getDrawable(R.drawable.border_only));
                yr.setHeight(100);
                yr.setHintTextColor(Color.BLACK);
                yr.setMaxWidth(200);
                yr.setSingleLine(true);
                yr.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                yr.setPadding(30, 20, 20, 20);
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp2.setMargins(0,0,0,20);
                yr.setLayoutParams(lp2);


                lnr.addView(yr);




            }


        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add.setVisibility(View.VISIBLE);
                minus.setVisibility(View.VISIBLE);






                inst = new EditText(Edit.this);
                inst.setHint("Institute");
                inst.setTextSize(14);
                inst.setBackgroundDrawable(getDrawable(R.drawable.border_only));
                inst.setHeight(100);
                inst.setHintTextColor(Color.BLACK);
                inst.setMaxWidth(200);
                inst.setSingleLine(true);
                inst.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                inst.setPadding(30, 20, 20, 20);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0,0,0,20);
                inst.setLayoutParams(lp);

                lnr.removeViewAt(3);

                deg = new EditText(Edit.this);
                deg.setHint("Degree");
                deg.setTextSize(14);
                deg.setBackgroundDrawable(getDrawable(R.drawable.border_only));
                deg.setHeight(100);
                deg.setHintTextColor(Color.BLACK);
                deg.setMaxWidth(200);
                deg.setSingleLine(true);
                deg.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                deg.setPadding(30, 20, 20, 20);
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp1.setMargins(0,0,0,20);
                deg.setLayoutParams(lp1);

                lnr.removeViewAt(3);


                yr = new EditText(Edit.this);
                yr.setHint("Academic Year");
                yr.setTextSize(14);
                yr.setBackgroundDrawable(getDrawable(R.drawable.border_only));
                yr.setHeight(100);
                yr.setHintTextColor(Color.BLACK);
                yr.setMaxWidth(200);
                yr.setSingleLine(true);
                yr.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                yr.setPadding(30, 20, 20, 20);
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp2.setMargins(0,0,0,20);
                yr.setLayoutParams(lp2);


                lnr.removeViewAt(3);
                lnr.setVisibility(GONE);






            }
        });


        add_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                minus_s.setVisibility(View.VISIBLE);
                add_s.setVisibility(View.VISIBLE);

                add4 = new EditText(Edit.this);
                add4.setHint("Company");
                add4.setTextSize(14);
                add4.setBackgroundDrawable(getDrawable(R.drawable.border_only));
                add4.setHeight(100);
                add4.setHintTextColor(Color.BLACK);
                add4.setMaxWidth(200);
                add4.setSingleLine(true);
                add4.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                add4.setPadding(30, 20, 20, 20);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0,0,0,20);
                add4.setLayoutParams(lp);


                lnr1.addView(add4);

                exp = new EditText(Edit.this);
                exp.setHint("Experience");
                exp.setTextSize(14);
                exp.setBackgroundDrawable(getDrawable(R.drawable.border_only));
                exp.setHeight(100);
                exp.setHintTextColor(Color.BLACK);
                exp.setMaxWidth(200);
                exp.setSingleLine(true);
                exp.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                exp.setPadding(30, 20, 20, 20);
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp1.setMargins(0,0,0,20);
                exp.setLayoutParams(lp1);


                lnr1.addView(exp);

                add5 = new EditText(Edit.this);
                add5.setHint("Position");
                add5.setTextSize(14);
                add5.setBackgroundDrawable(getDrawable(R.drawable.border_only));
                add5.setHeight(100);
                add5.setHintTextColor(Color.BLACK);
                add5.setMaxWidth(200);
                add5.setSingleLine(true);
                add5.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                add5.setPadding(30, 20, 20, 20);
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp2.setMargins(0,0,0,20);
                add5.setLayoutParams(lp2);


                lnr1.addView(add5);




            }
        });
        minus_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_s.setVisibility(View.VISIBLE);
                minus_s.setVisibility(View.VISIBLE);

                add4 = new EditText(Edit.this);
                add4.setHint("Company");
                add4.setTextSize(14);
                add4.setBackgroundDrawable(getDrawable(R.drawable.border_only));
                add4.setHeight(100);
                add4.setHintTextColor(Color.BLACK);
                add4.setMaxWidth(200);
                add4.setSingleLine(true);
                add4.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                add4.setPadding(30, 20, 20, 20);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0,0,0,20);
                add4.setLayoutParams(lp);


                lnr1.removeAllViews();

                exp = new EditText(Edit.this);
                exp.setHint("Experience");
                exp.setTextSize(14);
                exp.setBackgroundDrawable(getDrawable(R.drawable.border_only));
                exp.setHeight(100);
                exp.setHintTextColor(Color.BLACK);
                exp.setMaxWidth(200);
                exp.setSingleLine(true);
                exp.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                exp.setPadding(30, 20, 20, 20);
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp1.setMargins(0,0,0,20);
                exp.setLayoutParams(lp1);


                lnr1.removeAllViews();

                add5 = new EditText(Edit.this);
                add5.setHint("Position");
                add5.setTextSize(14);
                add5.setBackgroundDrawable(getDrawable(R.drawable.border_only));
                add5.setHeight(100);
                add5.setHintTextColor(Color.BLACK);
                add5.setMaxWidth(200);
                add5.setSingleLine(true);
                add5.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                add5.setPadding(30, 20, 20, 20);
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp2.setMargins(0,0,0,20);
                add5.setLayoutParams(lp2);


                lnr1.removeAllViews();
                minus_s.setVisibility(GONE);

            }
        });


        add_skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                minus_skills.setVisibility(View.VISIBLE);
                add_skills.setVisibility(View.VISIBLE);

                add7 = new EditText(Edit.this);
                add7.setHint("Skills");
                add7.setTextSize(14);
                add7.setBackgroundDrawable(getDrawable(R.drawable.border_only));
                add7.setHeight(100);
                add7.setHintTextColor(Color.BLACK);
                add7.setMaxWidth(200);
                add7.setSingleLine(true);
                add7.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                add7.setPadding(30, 20, 20, 20);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0,0,0,20);
                add7.setLayoutParams(lp);


                lnr2.addView(add7);



            }
        });
        minus_skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_skills.setVisibility(View.VISIBLE);
                minus_skills.setVisibility(GONE);

                add7 = new EditText(Edit.this);
                add7.setHint("Skills");
                add7.setTextSize(14);
                add7.setBackgroundDrawable(getDrawable(R.drawable.border_only));
                add7.setHeight(100);
                add7.setHintTextColor(Color.BLACK);
                add7.setMaxWidth(200);
                add7.setSingleLine(true);
                add7.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                add7.setPadding(30, 20, 20, 20);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0,0,0,20);
                add7.setLayoutParams(lp);


                lnr2.removeAllViews();
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

    public  void profile_page() {

        Dob = dob.getText().toString();
        B_gp = b_gp.getText().toString();
        Desc = desc.getText().toString();

        Com = add4.getText().toString();
        Exp = exp.getText().toString();
        Pos = add5.getText().toString();
        Skills = add7.getText().toString();



        for (int i = 0; i<arrayList.size();i++){

            for (int j = 0; j< 3;j++){

                Ins = inst.getText().toString();
                Deg = deg.getText().toString();
                Year = yr.getText().toString();

                arrayList.add(Ins);
                arrayList.add(Year);
                arrayList.add(Deg);


                Log.i("epjhrfueioyhrt9",arrayList.toString());

        }
        }



        String url = "http://nk.inevitabletech.email/public/api/send-profile-details";
        JSONObject jsonBody = new JSONObject();


        try {

            final String education = "[\n" +
                    " {\n" +
                    "  \"education\": " + arrayList +
                    " }\n" +
                    "]"
                    ;


            try {
                jsonBody.put("dob", Dob);
                jsonBody.put("blood_group", B_gp);
                jsonBody.put("description", Desc);
//                jsonBody.put("image",picture);


                for (int i = 0; i < education.length(); i++) {
                    for (int j = 0; j<education.length();j++){
                        jsonBody.put("education", education);
                        Log.i("kjfhggy",education);
                    }

               }


//            Log.i("qpowru0qp9eruiop-",jsonBody.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {





                    try {
                        String Success = response.getString("success");
                        String msg = response.getString("message");


                        if (Success == "true") {

                            Toast.makeText(Edit.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Edit.this, Profile.class);
                            startActivity(intent);

                        } else {


                            Toast.makeText(Edit.this, msg, Toast.LENGTH_SHORT).show();


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
                    Toast.makeText(Edit.this, str, Toast.LENGTH_SHORT).show();
                    Log.i("ewohfg9uwrytg9", str);


                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
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


        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}

