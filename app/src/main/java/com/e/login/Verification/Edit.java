package com.e.login.Verification;

import static android.view.View.GONE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.MyApplication;
import com.e.login.Profile;
import com.e.login.R;
import com.e.login.VolleyMultipartRequest;
import com.e.login.utils.PreferenceUtils;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Edit extends AppCompatActivity {
    EditText dob,b_gp,desc,deg,year,com,exp,pos,skills,inst,yr,add3,add4,add5,add6,add7;
    TextView ins;
    String Dob,B_gp,Desc;
     ArrayList<String> arrayList,arrayList1,arrayList2;
     String  Ins,Deg,Year,Com,Exp,Pos,Skills;
    Button btn;
    private LinearLayout parentLayout;
    private int hint=0;
    ImageView add,editt,add_s,minus_s,add_skills,minus_skills,edit;
    CircleImageView profile;
    Context myContext;
    LinearLayout lnr,lnr1,lnr2;
    Context context;
    static final int PICK_IMAGE_REQUEST = 1;
   String picture;
    byte[] byteArray;
    Bitmap bitmap;

    Uri imageuri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);



        Intent intent = getIntent();
        picture = intent.getStringExtra("image");






      arrayList = new ArrayList<String>();
      arrayList1 = new ArrayList<>();
      arrayList2 = new ArrayList<>();


        ins = findViewById(R.id.p_details4);

        dob = findViewById(R.id.p_details1);
        b_gp = findViewById(R.id.p_details2);
        desc = findViewById(R.id.p_details3);
//        exp = findViewById(R.id.p_details8);
        btn = findViewById(R.id.submit_profile);
        add = findViewById(R.id.add);
        editt = findViewById(R.id.editt);
        add_s = findViewById(R.id.add_exp);
        minus_s = findViewById(R.id.minus_exp);
        add_skills = findViewById(R.id.add_skill);
        minus_skills = findViewById(R.id.minus_skill);
//        add7 = findViewById(R.id.add7);

        profile = findViewById(R.id.profile_image);
        edit = findViewById(R.id.img_choose);





        inst.setVisibility(GONE);
        deg.setVisibility(GONE);
        yr.setVisibility(GONE);
        add.setVisibility(View.VISIBLE);

        minus_s.setVisibility(GONE);
        minus_skills.setVisibility(GONE);
        add7.setVisibility(GONE);

        add4.setVisibility(GONE);
        exp.setVisibility(GONE);
        add5.setVisibility(GONE);
//
//        lnr = (LinearLayout)findViewById(R.id.linn);
//        lnr1 = (LinearLayout)findViewById(R.id.linn1);
//        lnr2 = (LinearLayout)findViewById(R.id.linn2);




        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageBrowse();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





//                add.setVisibility(View.VISIBLE);
//
//
//                inst = new EditText(Edit.this);
//                inst.setHint("Institute");
//                inst.setTextSize(14);
//                inst.setBackgroundDrawable(getDrawable(R.drawable.border_only));
//                inst.setHeight(100);
//                inst.setHintTextColor(Color.BLACK);
//                inst.setMaxWidth(200);
//                inst.setSingleLine(true);
//                inst.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
//                inst.setPadding(30, 20, 20, 20);
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                lp.setMargins(0,0,0,20);
//                inst.setLayoutParams(lp);
//
//
//                 lnr.addView(inst);
//
//
//                deg = new EditText(Edit.this);
//                deg.setHint("Degree");
//                deg.setTextSize(14);
//                deg.setBackgroundDrawable(getDrawable(R.drawable.border_only));
//                deg.setHeight(100);
//                deg.setHintTextColor(Color.BLACK);
//                deg.setMaxWidth(200);
//                deg.setSingleLine(true);
//                deg.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
//                deg.setPadding(30, 20, 20, 20);
//                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                lp1.setMargins(0,0,0,20);
//                deg.setLayoutParams(lp1);
//
//
//                lnr.addView(deg);
//
//                yr = new EditText(Edit.this);
//                yr.setHint("Academic Year");
//                yr.setTextSize(14);
//                yr.setBackgroundDrawable(getDrawable(R.drawable.border_only));
//                yr.setHeight(100);
//                yr.setHintTextColor(Color.BLACK);
//                yr.setMaxWidth(200);
//                yr.setSingleLine(true);
//                yr.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
//                yr.setPadding(30, 20, 20, 20);
//                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                lp2.setMargins(0,0,0,20);
//                yr.setLayoutParams(lp2);
//
//
//                lnr.addView(yr);




            }


        });



        add_skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                minus_skills.setVisibility(View.VISIBLE);
//                add_skills.setVisibility(View.VISIBLE);
//
//                add7 = new EditText(Edit.this);
//                add7.setHint("Skills");
//                add7.setTextSize(14);
//                add7.setBackgroundDrawable(getDrawable(R.drawable.border_only));
//                add7.setHeight(100);
//                add7.setHintTextColor(Color.BLACK);
//                add7.setMaxWidth(200);
//                add7.setSingleLine(true);
//                add7.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
//                add7.setPadding(30, 20, 20, 20);
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                lp.setMargins(0,0,0,20);
//                add7.setLayoutParams(lp);
//
//
//                lnr2.addView(add7);
//


            }
        });




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                   ed();
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
//                jsonBody.put("image","Q9Qke1NMU1.png");


                for (int i = 0; i < education.length(); i++) {
                    for (int j = 0; j<education.length();j++){
                        jsonBody.put("education", education);
                        Log.i("kjfhggy",education);
                    }

               }


            Log.i("qpowru0qp9eruiop-",jsonBody.toString());

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


    public  byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();


    }


    private void imageBrowse() {
        CropImage.activity().start(Edit.this);
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri picUri = data.getData();
//
//
//            try {
//
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
//                profile.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE ) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

                Uri resultUri = result.getUri();
                Picasso.with(this).load(resultUri).into(profile);

        }


//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri picUri = data.getData();
//
//
//            try {
//
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
//                profile.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//        }
    }

    public void ed(){


        Dob = dob.getText().toString();
        B_gp = b_gp.getText().toString();
        Desc = desc.getText().toString();

        Com = add4.getText().toString();
        Exp = exp.getText().toString();
        Pos = add5.getText().toString();
        Skills = add7.getText().toString();


        String url = "http://nk.inevitabletech.email/public/api/send-profile-details";


//        JSONObject jsonBody = new JSONObject();
//
//        try {
//            jsonBody.put("dob",Dob);
//            jsonBody.put("blood_group", B_gp);
//            jsonBody.put("description", Desc);
//


        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,null, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {

                try {
                    JSONObject obj = new JSONObject(new String(response.data));

                    Toast.makeText(Edit.this, obj.toString(), Toast.LENGTH_SHORT).show();
                    Log.i("uyfdthggu",obj.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Charset charset = Charset.defaultCharset();
                String str = new String(error.networkResponse.data,charset);
                Toast.makeText(Edit.this, str, Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Accept","application/json");
                params.put("Authorization", "Bearer  " +PreferenceUtils.getToken(Edit.this));

                return params;
            }



            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();

                long imagename = System.currentTimeMillis();
                params.put("image", new DataPart(imagename + ".jpg", getFileDataFromDrawable(bitmap)));

                return params;
            }

        };

        multipartRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyApplication.getInstance().addToRequestQueue(multipartRequest);

//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

}

