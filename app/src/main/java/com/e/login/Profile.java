package com.e.login;

import static android.view.View.GONE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
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
import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.HomeClass.Home;
import com.e.login.ShopClass.ShopScreen_Class;
import com.e.login.Verification.Change_Email_OTP;
import com.e.login.Verification.Change_Phone;
import com.e.login.Verification.Edit;
import com.e.login.Verification.Email_OTP;
import com.e.login.Verification.VerifyActivity;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
    Button btn,edit,save,email_verify,contact_verify;
    String data,data1,data2,user_id;
    TextView user_name,summary,edu,exp_head,skills_head,name,dob,b_grp,desc,inst,deg,yea,com_txt,exp_txt,pos_txt,emailtxt,contacttxt,verified;
    LinearLayout skill,comm;
    ImageView profile,edit_img,email_edit,con_edit;
    EditText edit_txt,ins,degree,year,com,exp,pos,skill_edit,com_edit,user,doob,blood,email_edit_txt,con_edit_txt;
    String Edit,Ins,Degree,Year,Comp,Exp,Pos,Skill,Com,User,Dob,Blood,Img;
    private long pressedTime;
    String data3;
    View view,view1,view2,view3;
    String id,email,phone,namee,email_verifyy,phone_verifyy;
    Button savee,save1;
    String Email_get,Phone_get;
    LinearLayout gone1,gone2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);


        get_profile();

        view =findViewById(R.id.first_view);
        view1 =findViewById(R.id.second_view);
        view2 =findViewById(R.id.third_view);
        view3 =findViewById(R.id.fourth_view);
        user_name = findViewById(R.id.user_name);
        gone1 = findViewById(R.id.gone1);
        gone2 = findViewById(R.id.gone2);
//        verified = findViewById(R.id.verified_txtt);


        edit_img = findViewById(R.id.img_edit);

        summary = findViewById(R.id.summary_head);
        edu = findViewById(R.id.edu_head);
        exp_head = findViewById(R.id.experience_head);
        skills_head = findViewById(R.id.skills_head);

        btn = findViewById(R.id.change_btn);
        save = findViewById(R.id.save_btn);
//        edit_txt = findViewById(R.id.edit_txt);
//        ins = findViewById(R.id.ins_edit_txt);
//        degree = findViewById(R.id.degree_edit_txt);
//        year = findViewById(R.id.year_edit_txt);
        inst = findViewById(R.id.ins_txt);
        deg = findViewById(R.id.deg_txt);
        yea = findViewById(R.id.year_txt);
        com_txt = findViewById(R.id.com_txt);
        exp_txt = findViewById(R.id.exp_txt);
        pos_txt = findViewById(R.id.pos_txt);
        desc = findViewById(R.id.descrip);
//        com = findViewById(R.id.com_edit_txt);
//        exp = findViewById(R.id.exp_edit_txt);
//        pos = findViewById(R.id.pos_edit_txt);
        skill = findViewById(R.id.skill_lnr);
//        comm = findViewById(R.id.com_linear);
//        skill_edit = findViewById(R.id.skill_edit);
       // com_edit = findViewById(R.id.com_edit);
//        user = findViewById(R.id.user_edit);
//        doob = findViewById(R.id.dob_edit);
//        blood = findViewById(R.id.blood_edit);
        profile = findViewById(R.id.profile_img);
        email_edit = findViewById(R.id.gmail_edit);
        con_edit = findViewById(R.id.contact_edit);
        email_edit_txt = findViewById(R.id.email_edit_txt);
        con_edit_txt = findViewById(R.id.contact_edit_txt);
        savee = findViewById(R.id.save_number);
        save1 = findViewById(R.id.save_number1);
        emailtxt = findViewById(R.id.emailtxt);
        contacttxt = findViewById(R.id.contacttxt);
        email_verify = findViewById(R.id.email_verify);
        contact_verify = findViewById(R.id.contact_verify);
//        verified = findViewById(R.id.verified_txtt);
//


        save.setVisibility(GONE);

        email_edit.setVisibility(View.VISIBLE);

        summary.setVisibility(GONE);
        edu.setVisibility(GONE);
        exp_head.setVisibility(GONE);
        skills_head.setVisibility(GONE);


        inst.setVisibility(GONE);
        deg.setVisibility(GONE);
        yea.setVisibility(GONE);
        com_txt.setVisibility(GONE);
        exp_txt.setVisibility(GONE);
        pos_txt.setVisibility(GONE);
//        desc .setVisibility(GONE);
        skill.setVisibility(GONE);
//        verified.setVisibility(GONE);

        view1.setVisibility(GONE);
        view2.setVisibility(GONE);
        view3.setVisibility(GONE);
//        comm.setVisibility(GONE);

        edit_img.setVisibility(GONE);
        email_edit_txt.setVisibility(GONE);
        con_edit_txt.setVisibility(GONE);
        savee.setVisibility(GONE);
        save1.setVisibility(GONE);
        email_verify.setVisibility(View.VISIBLE);


        emailtxt.setVisibility(View.VISIBLE);
        contacttxt.setVisibility(View.VISIBLE);
//        verified.setVisibility(GONE);



//        dob = findViewById(R.id.d_o_b);
//        b_grp = findViewById(R.id.blood_grp);


        savee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                email_edit.setVisibility(View.VISIBLE);
//                gone1.setVisibility(GONE);

                change_email();

            }
        });
        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                change_phone();

            }
        });

        Intent intent = getIntent();
        data = intent.getStringExtra("token");
        data1 = intent.getStringExtra("id");
        data2 = intent.getStringExtra("user_name");
        data3 = intent.getStringExtra("email");
        //user_id = intent.getStringExtra("user_id");
        emailtxt.setText(data3);

//        Toast.makeText(Profile.this, user_id, Toast.LENGTH_SHORT).show();
//        Toast.makeText(Profile.this, data, Toast.LENGTH_SHORT).show();
        Toast.makeText(Profile.this, data1, Toast.LENGTH_SHORT).show();
//

        email_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_edit_txt.setVisibility(View.VISIBLE);
                email_edit.setVisibility(GONE);
                emailtxt.setVisibility(GONE);
                email_verify.setVisibility(View.VISIBLE);
                savee.setVisibility(View.VISIBLE);



            }
        });
        email_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                send_mail();
//                verified.setVisibility(View.VISIBLE);
//                email_edit_txt.setVisibility(GONE);
//                email_verify.setVisibility(GONE);
                //email_edit.setVisibility(View.VISIBLE);
//                emailtxt.setVisibility(View.VISIBLE);
            }
        });

        con_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                con_edit_txt.setVisibility(View.VISIBLE);
                con_edit.setVisibility(GONE);
                contacttxt.setVisibility(GONE);
                contact_verify.setVisibility(View.VISIBLE);
                save1.setVisibility(View.VISIBLE);



            }
        });
        contact_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                con_edit_txt.setVisibility(GONE);
//                contact_verify.setVisibility(GONE);
//                con_edit.setVisibility(View.VISIBLE);
//                contacttxt.setVisibility(View.VISIBLE);
                send_mail();
            }
        });


        edit = findViewById(R.id.edit_button);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Profile.this, com.e.login.Verification.Edit.class);
                startActivity(intent1);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,ChangePassword.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("token",data);
                intent.putExtra("id",data1);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                edit_txt.setVisibility(GONE);
//                ins.setVisibility(GONE);
//                degree.setVisibility(GONE);
//                year.setVisibility(GONE);
//                com.setVisibility(GONE);
//                exp.setVisibility(GONE);
//                pos.setVisibility(GONE);
               // skill_edit.setVisibility(GONE);
                //com_edit.setVisibility(GONE);

                save.setVisibility(GONE);
                btn.setVisibility(View.VISIBLE);
                desc.setVisibility(View.VISIBLE);
                inst.setVisibility(View.VISIBLE);
                deg.setVisibility(View.VISIBLE);
                yea.setVisibility(View.VISIBLE);
                com_txt.setVisibility(View.VISIBLE);
                exp_txt.setVisibility(View.VISIBLE);
                pos_txt.setVisibility(View.VISIBLE);
                skill.setVisibility(View.VISIBLE);
                comm.setVisibility(View.VISIBLE);
                user.setVisibility(GONE);
                doob.setVisibility(GONE);
                blood.setVisibility(GONE);
                name.setVisibility(View.VISIBLE);
                dob.setVisibility(View.VISIBLE);
                b_grp.setVisibility(View.VISIBLE);
                edit_img.setVisibility(GONE);

//                email_edit.setVisibility(View.GONE);
//                con_edit.setVisibility(View.GONE);

//                profile_page();


            }
        });


    }




    public void send_mail(){


        String url = "http://nk.inevitabletech.email/public/api/sendEmailOtp";
        JSONObject jsonBody = new JSONObject();


        try {
           jsonBody.put("user_id",data1);





            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.i("0000000000000",response.toString());
                    Toast.makeText(Profile.this, response.toString(), Toast.LENGTH_SHORT).show();



                    try{
                        String Success = response.getString("success");
                        String msg = response.getString("message");





                        if (Success.equals("true")){
                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();

                            Intent intent1 = new Intent(Profile.this, Email_OTP.class);
                            intent1.putExtra("email",email);
                            intent1.putExtra("user_id",data1);
//
                            intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent1);
                            Toast.makeText(Profile.this, email, Toast.LENGTH_SHORT).show();
//                            PreferenceUtils.saveid(data1,Profile.this);
//                            PreferenceUtils.saveToken(data,Profile.this);


                        }
                        else {


                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();


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
                    Toast.makeText(Profile.this, str, Toast.LENGTH_SHORT).show();


                    try {
                        JSONObject   jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");
                        Toast.makeText(Profile.this, data.toString(), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }





                }
            }){

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Accept","application/json");
                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Profile.this));
                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken1(Profile.this));
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

    public void get_profile(){

        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-profile-details";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


                Log.i("0000000",response.toString());
                Toast.makeText(Profile.this, response.toString(), Toast.LENGTH_SHORT).show();

                try {



                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    JSONObject jsonObject = response.getJSONObject("data");
                    id = jsonObject.getString("id");
                    namee = jsonObject.getString("name");
                    email = jsonObject.getString("email");
                    phone = jsonObject.getString("phone");
                    email_verifyy = jsonObject.getString("email_verification_status");
                    phone_verifyy = jsonObject.getString("phone_verification_status");


                    if(Success.equals("true")){
                        Log.i("123",msg);

                        Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();
                        emailtxt.setText(email);
                        contacttxt.setText(phone);
                        user_name.setText(namee);
                        email_verify.setText(email_verifyy);
                        contact_verify.setText(phone_verifyy);

//


                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();
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

                params.put("Accept","application/json");
                params.put("Authorization", "Bearer " + PreferenceUtils.getToken(Profile.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Profile.this);
        requestQueue.add(jsonObjectRequest);


    }
    public void change_email(){

        Email_get = email_edit_txt.getText().toString();


        String url = "http://nk.inevitabletech.email/public/api/change-email";
        JSONObject jsonBody = new JSONObject();


        try {
            jsonBody.put("email",Email_get);
            Toast.makeText(Profile.this, Email_get, Toast.LENGTH_SHORT).show();





            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.i("0000000000000",response.toString());
                    Toast.makeText(Profile.this, response.toString(), Toast.LENGTH_SHORT).show();



                    try{
                        String Success = response.getString("success");
                        String msg = response.getString("message");





                        if (Success.equals("true")){
                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();

                            Intent intent1 = new Intent(Profile.this, Change_Email_OTP.class);
                            intent1.putExtra("email",Email_get);
                            intent1.putExtra("user_id",data1);

                            intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent1);
//                            PreferenceUtils.saveid(data1,Profile.this);
//                            PreferenceUtils.saveToken(data,Profile.this);


                        }
                        else {


                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();


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
                    Toast.makeText(Profile.this, str, Toast.LENGTH_SHORT).show();


                    try {
                        JSONObject   jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");
                        Toast.makeText(Profile.this, data.toString(), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }





                }
            }){

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Accept","application/json");
                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Profile.this));
                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken1(Profile.this));
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



    public void change_phone(){


        Phone_get = con_edit_txt.getText().toString();


        String url = "http://nk.inevitabletech.email/public/api/change-phone";
        JSONObject jsonBody = new JSONObject();


        try {
            jsonBody.put("phone",Phone_get);
            Toast.makeText(Profile.this, Phone_get, Toast.LENGTH_SHORT).show();





            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.i("0000000000000",response.toString());
                    Toast.makeText(Profile.this, response.toString(), Toast.LENGTH_SHORT).show();



                    try{
                        String Success = response.getString("success");
                        String msg = response.getString("message");





                        if (Success.equals("true")){
                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();

                            Intent intent1 = new Intent(Profile.this, Change_Phone.class);
                            intent1.putExtra("phone",Phone_get);
                            intent1.putExtra("user_id",id);

                            Toast.makeText(Profile.this, id, Toast.LENGTH_SHORT).show();

                            intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent1);
//                            PreferenceUtils.saveid(data1,Profile.this);
//                            PreferenceUtils.saveToken(data,Profile.this);


                        }
                        else {


                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();


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
                    Toast.makeText(Profile.this, str, Toast.LENGTH_SHORT).show();


                    try {
                        JSONObject   jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");
                        Toast.makeText(Profile.this, data.toString(), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }





                }
            }){

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Accept","application/json");
                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Profile.this));
                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken1(Profile.this));
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

}