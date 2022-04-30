package com.e.login;

import static java.nio.file.Paths.get;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
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
import com.e.login.BloodClass.Blood_One;
import com.e.login.HomeClass.Home;
import com.e.login.utils.PreferenceUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditProfile extends AppCompatActivity {
    Button save, cancel;

    LinearLayout education_layoutList, experience_layoutList, skill_layoutList;
    Button edu_buttonAdd, exp_buttonAdd, skill_buttonAdd;

    EditText name;
    Spinner blood ;


    ArrayList<String> institute_name = new ArrayList<>();
    ArrayList<String> degree_name = new ArrayList<>();
    ArrayList<String> years_name = new ArrayList<>();

    ArrayList<String> position_name = new ArrayList<>();
    ArrayList<String> company_name = new ArrayList<>();
    ArrayList<String> experience_name = new ArrayList<>();

    ArrayList<String> skill_name = new ArrayList<>();

    Bitmap bitmap;

    int edu_count = 1;
    int exp_count = 1;
    int skill_count = 1;

    TextView calende;

    Calendar calendar;

    DatePickerDialog dd;

    String date = "";

    FloatingActionButton image_upload;

    int CAMERA_REQUEST1 = 2;
    int MY_CAMERA_PERMISSION_CODE = 100;
    int SELECT_PHOTO = 1;

    ImageView avatar;

    JSONObject jsonBody;

    String Name= null,Blood= null;

    TextView Error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        save = findViewById(R.id.savee_btn);
        cancel = findViewById(R.id.cancell_btn);

        name = findViewById(R.id.NameDetails);

        blood = findViewById(R.id.edit2);

        avatar = findViewById(R.id.profile_img);

        education_layoutList = findViewById(R.id.education_layout_list);
        edu_buttonAdd = findViewById(R.id.education_button_add);

        experience_layoutList = findViewById(R.id.experience_layout_list);
        exp_buttonAdd = findViewById(R.id.experience_button_add);

        skill_layoutList = findViewById(R.id.skill_layout_list);
        skill_buttonAdd = findViewById(R.id.skill_button_add);

        Error = findViewById(R.id.error1);

        image_upload = findViewById(R.id.upload_img);


        image_upload.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                BottomSheetDialog dialog = new BottomSheetDialog(EditProfile.this);

                dialog.setContentView(R.layout.choose_image);

                TextView close = dialog.findViewById(R.id.close);
                TextView file = dialog.findViewById(R.id.file);
                TextView camera = dialog.findViewById(R.id.camera);


                file.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, SELECT_PHOTO);
                    }
                });


                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                        } else {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST1);
                        }
                    }
                });


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


                dialog.setCancelable(false);
//                dialog.behavior.peekHeight = 10000;
                dialog.show();
            }
        });


        edu_buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edu_addView();
            }
        });

        exp_buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp_addView();
            }
        });

        skill_buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skill_addView();
            }
        });

        calende = findViewById(R.id.dob);

        calende.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);


                dd = new DatePickerDialog(EditProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {


//                        Toast.makeText(Pending_Class.this,mDay+"-"+ (mMonth+1) + "-" + +mYear , Toast.LENGTH_SHORT).show();

                        date = mDay + "-" + (mMonth + 1) + "-" + mYear;
                        calende.setText(date);


                    }
                }, day, month, year);

                dd.show();

            }
        });

        ArrayList b_name = new ArrayList();


        b_name.add("Blood Group");

        b_name.add("a1_positive");
        b_name.add("a1_negative");
        b_name.add("a2_positive");
        b_name.add("a2_negative");
        b_name.add("b_positive");
        b_name.add("b_negative");
        b_name.add("a1b_positive");
        b_name.add("a1b_negative");
        b_name.add("a2b_positive");
        b_name.add("a2b_negative");
        b_name.add("ab_positive");
        b_name.add("ab_negative");
        b_name.add("o_positive");
        b_name.add("o_negative");
        b_name.add("a_positive");
        b_name.add("a_negative");


        ArrayAdapter<String> Adapter1 = new ArrayAdapter<String>(EditProfile.this,
                R.layout.text_color1, b_name);
        Adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood.setAdapter(Adapter1);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               uploadBitmap(bitmap);




            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this, Profile.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }




    private boolean checkIfValidAndRead() {
        institute_name.clear();
        degree_name.clear();
        years_name.clear();

        position_name.clear();
        company_name.clear();
        experience_name.clear();

        skill_name.clear();

        boolean result = true;



        for (int i = 0; i < education_layoutList.getChildCount(); i++) {

            View educationView = education_layoutList.getChildAt(i);

            EditText institute = (EditText) educationView.findViewById(R.id.institute);
            EditText degree = (EditText) educationView.findViewById(R.id.degree);
            EditText years = (EditText) educationView.findViewById(R.id.years);

            TextView error1 = (TextView) educationView.findViewById(R.id.error1);
            TextView error2 = (TextView) educationView.findViewById(R.id.error2);
            TextView error3 = (TextView) educationView.findViewById(R.id.error3);

            error1.setVisibility(View.GONE);
            error2.setVisibility(View.GONE);
            error3.setVisibility(View.GONE);

//             EducationModel model = new EducationModel();

            if(!institute.getText().toString().equals("")){
                institute_name.add(institute.getText().toString());
            }else {
                result = false;
                error1.setVisibility(View.VISIBLE);
                error1.setText("The institute field is required.");
                break;
            }

            if(!degree.getText().toString().equals("")){
                degree_name.add(degree.getText().toString());
            }else {
                result = false;
                error2.setVisibility(View.VISIBLE);
                error2.setText("The degree field is required.");
                break;
            }

            if(!years.getText().toString().equals("")){
                years_name.add(years.getText().toString());
            }else {
                result = false;
                error3.setVisibility(View.VISIBLE);
                error3.setText("The years field is required.");
                break;
            }




        }

        for (int i = 0; i < experience_layoutList.getChildCount(); i++) {

            View experienceView = experience_layoutList.getChildAt(i);

            EditText position = (EditText) experienceView.findViewById(R.id.position);
            EditText company = (EditText) experienceView.findViewById(R.id.company);
            EditText experience = (EditText) experienceView.findViewById(R.id.experience);

            TextView error1 = (TextView) experienceView.findViewById(R.id.error1);
            TextView error2 = (TextView) experienceView.findViewById(R.id.error2);
            TextView error3 = (TextView) experienceView.findViewById(R.id.error3);

            error1.setVisibility(View.GONE);
            error2.setVisibility(View.GONE);
            error3.setVisibility(View.GONE);

//             EducationModel model = new EducationModel();
//
            if(!position.getText().toString().equals("")){
                position_name.add(position.getText().toString());
            }else {
                result = false;
                error1.setVisibility(View.VISIBLE);
                error1.setText("The position field is required.");
                break;
            }

            if(!company.getText().toString().equals("")){
                company_name.add(company.getText().toString());
            }else {
                result = false;
                error2.setVisibility(View.VISIBLE);
                error2.setText("The company field is required.");
                break;
            }

            if(!experience.getText().toString().equals("")){
                experience_name.add(experience.getText().toString());
            }else {
                result = false;
                error3.setVisibility(View.VISIBLE);
                error3.setText("The experience field is required.");
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

    private void edu_addView() {

        final View educationView = getLayoutInflater().inflate(R.layout.education_layout, null, false);

        EditText institute = (EditText) educationView.findViewById(R.id.institute);
        EditText degree = (EditText) educationView.findViewById(R.id.degree);
        EditText years = (EditText) educationView.findViewById(R.id.years);
        TextView count_text = (TextView) educationView.findViewById(R.id.number);
        ImageView imageClose = (ImageView) educationView.findViewById(R.id.image_remove);
        institute.setSingleLine(true);
        degree.setSingleLine(true);
        years.setSingleLine(true);


        count_text.setText(String.valueOf(edu_count));

        edu_count++;


        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edu_removeView(educationView);
            }
        });

        education_layoutList.addView(educationView);
    }

    private void exp_addView() {

        final View experienceView = getLayoutInflater().inflate(R.layout.experience_layout, null, false);

        EditText position = (EditText) experienceView.findViewById(R.id.position);
        EditText company = (EditText) experienceView.findViewById(R.id.company);
        EditText experience = (EditText) experienceView.findViewById(R.id.experience);
        TextView count_text = (TextView) experienceView.findViewById(R.id.number);
        ImageView imageClose = (ImageView) experienceView.findViewById(R.id.image_remove);
        position.setSingleLine(true);
        company.setSingleLine(true);
        experience.setSingleLine(true);

        count_text.setText(String.valueOf(exp_count));

        exp_count++;


        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp_removeView(experienceView);
            }
        });

        experience_layoutList.addView(experienceView);
    }

    private void skill_addView() {

        final View skillView = getLayoutInflater().inflate(R.layout.skill_layout, null, false);

        EditText skill = (EditText) skillView.findViewById(R.id.skill);
        TextView count_text = (TextView) skillView.findViewById(R.id.number);
        ImageView imageClose = (ImageView) skillView.findViewById(R.id.image_remove);
        skill.setSingleLine(true);

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

    private void edu_removeView(View view) {

        education_layoutList.removeView(view);
        edu_count--;

    }

    private void exp_removeView(View view) {

        experience_layoutList.removeView(view);
        exp_count--;

    }

    private void skill_removeView(View view) {

        skill_layoutList.removeView(view);
        skill_count--;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                avatar.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == CAMERA_REQUEST1 && resultCode == RESULT_OK) {

            bitmap = (Bitmap) data.getExtras().get("data");
            avatar.setImageBitmap(bitmap);

        }
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void uploadBitmap(Bitmap bitmap) {

        String URL = "http://nk.inevitabletech.email/public/api/send-profile-details";

        Name = name.getText().toString().trim();
        Blood = blood.getSelectedItem().toString().trim();
        Error.setVisibility(View.GONE);

        if (checkIfValidAndRead()) {


            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, URL,
                    new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response) {


                            try {
                                JSONObject obj = new JSONObject(new String(response.data));
                                String msg = obj.getString("message");

                                Toast.makeText(EditProfile.this, msg, Toast.LENGTH_SHORT).show();

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

                                JSONArray nama = data.getJSONArray("name");

                                Error.setText(nama.getString(0));
                                Error.setVisibility(View.VISIBLE);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    params.put("Authorization", "Bearer " + PreferenceUtils.getToken(EditProfile.this));
                    return params;
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

//                    for (int k = 0; k < skill_layoutList.getChildCount(); k++) {
//
//
//                        Log.i("fdsfs", String.valueOf(k));
//
//
//                        params.put("skills["+k+"]", skill_name.get(k));
//
//
//                    }
                    params.put("name", Name);
                    params.put("dob", date);
                    if (Blood != "Blood Group") {
                        params.put("blood_group", Blood);
                    }






                    for (int i = 0; i < education_layoutList.getChildCount(); i++) {


                        params.put("education[" + i + "][institute]", institute_name.get(i));
                        params.put("education[" + i + "][degree]", degree_name.get(i));
                        params.put("education[" + i + "][year]", institute_name.get(i));

                    }

                    for (int k = 0; k < skill_layoutList.getChildCount(); k++) {

                        params.put("skills[" + k + "]", skill_name.get(k));

//                        params.put("education[" + i + "][degree]", degree_name.get(i));
//                        params.put("education[" + i + "][year]", institute_name.get(i));

                    }

                    for (int j = 0; j < experience_layoutList.getChildCount(); j++) {


                        params.put("experience[" + j + "][company]", company_name.get(j));
                        params.put("experience[" + j + "][experience]", degree_name.get(j));
                        params.put("experience[" + j + "][position]", position_name.get(j));

                    }



                    return params;
                }

                /*
                 * Here we are passing image by renaming it with a unique name
                 * */
                @Override
                protected Map<String, DataPart> getByteData() {
                    Map<String, DataPart> params = new HashMap<>();
                    long imagename = System.currentTimeMillis();
                    if (bitmap != null){
                        params.put("image", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                    }

                    return params;
                }
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

    public void onBackPressed() {
        Intent i = new Intent(EditProfile.this,Profile.class);
        startActivity(i);
    }
}