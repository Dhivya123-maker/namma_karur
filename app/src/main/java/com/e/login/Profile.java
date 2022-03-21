package com.e.login;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.ShopClass.ShopScreen_Class;
import com.e.login.Verification.Change_Email_OTP;
import com.e.login.Verification.Change_Phone;
import com.e.login.Verification.Edit;
import com.e.login.Verification.Email_OTP;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
    Button btn, edit, save, email_verify, contact_verify;
    String data, data1, data2, user_id;
    TextView user_name, summary, edu, exp_head, skills_head, name, dob, b_grp, desc, inst, deg, yea, com_txt, exp_txt, pos_txt, emailtxt, contacttxt, verified;
    LinearLayout skill, comm;
    ImageView profile, edit_img, email_edit, con_edit;
    EditText edit_txt, ins, degree, year, com, exp, pos, skill_edit, com_edit, user, doob, blood, email_edit_txt, con_edit_txt;
    String Edit, Ins, Degree, Year, Comp, Exp, Pos, Skill, Com, User, Dob, Blood, Img;
    private long pressedTime;
    String data3;
    View view, view1, view2, view3;
    String id, email, phone, namee, email_verifyy, phone_verifyy, image;
    Button savee, save1;
    String Email_get, Phone_get;
    LinearLayout gone1, gone2;


    String filePath;
    Context context;
    Bitmap bitmap = null;


    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);


        get_profile();


        view = findViewById(R.id.first_view);
        view1 = findViewById(R.id.second_view);
        view2 = findViewById(R.id.third_view);
        view3 = findViewById(R.id.fourth_view);
        user_name = findViewById(R.id.user_name);


//        verified = findViewById(R.id.verified_txtt);


        summary = findViewById(R.id.summary_head);
        edu = findViewById(R.id.edu_head);
        exp_head = findViewById(R.id.experience_head);
        skills_head = findViewById(R.id.skills_head);


        btn = findViewById(R.id.change_btn);
        save = findViewById(R.id.save_btn);
        inst = findViewById(R.id.ins_txt);
        deg = findViewById(R.id.deg_txt);
        yea = findViewById(R.id.year_txt);
        com_txt = findViewById(R.id.com_txt);
        exp_txt = findViewById(R.id.exp_txt);
        pos_txt = findViewById(R.id.pos_txt);
        desc = findViewById(R.id.descrip);
        skill = findViewById(R.id.skill_lnr);
        profile = findViewById(R.id.profile_img);
        email_edit = findViewById(R.id.gmail_edit);
        con_edit = findViewById(R.id.contact_edit);

//        con_edit_txt = findViewById(R.id.contact_edit_txt);
//        savee = findViewById(R.id.save_number);
//        save1 = findViewById(R.id.save_number1);
        emailtxt = findViewById(R.id.emailtxt);
        contacttxt = findViewById(R.id.contacttxt);
        email_verify = findViewById(R.id.email_verify);
        contact_verify = findViewById(R.id.contact_verify);
//        verified = findViewById(R.id.verified_txtt);
//


        save.setVisibility(View.VISIBLE);


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
        skill.setVisibility(GONE);
        view1.setVisibility(GONE);
        view2.setVisibility(GONE);
        view3.setVisibility(GONE);

        email_verify.setVisibility(View.VISIBLE);


//        savee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                change_email();
//
//            }
//        });
//        save1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                change_phone();
//
//            }
//        });

        Intent intent = getIntent();
        data = intent.getStringExtra("token");
        data1 = intent.getStringExtra("id");
        data2 = intent.getStringExtra("user_name");
        data3 = intent.getStringExtra("email");


        emailtxt.setText(data3);

//        email_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                email_edit_txt.setVisibility(View.VISIBLE);
//                email_edit.setVisibility(GONE);
//                emailtxt.setVisibility(GONE);
//                email_verify.setVisibility(View.VISIBLE);
//                savee.setVisibility(View.VISIBLE);
//
//
//            }
//        });
        email_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                send_mail();

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

                send_mail();
            }
        });

//
//        Bitmap bmp = BitmapFactory.decodeResource(getResources(),SELECT_PICTURE);
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);


//        profile.buildDrawingCache();
//        Bitmap bitmap = profile.getDrawingCache();

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
                Intent intent = new Intent(Profile.this, ChangePassword.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("token", data);
                intent.putExtra("id", data1);
                startActivity(intent);
            }
        });




    }


    public void send_mail() {


        String url = "http://nk.inevitabletech.email/public/api/sendEmailOtp";
        JSONObject jsonBody = new JSONObject();


        try {
            jsonBody.put("user_id", data1);


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

//                    Log.i("0000000000000",response.toString());
//                    Toast.makeText(Profile.this, response.toString(), Toast.LENGTH_SHORT).show();


                    try {
                        String Success = response.getString("success");
                        String msg = response.getString("message");


                        if (Success.equals("true")) {
                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();

                            Intent intent1 = new Intent(Profile.this, Email_OTP.class);
                            intent1.putExtra("email", email);
                            intent1.putExtra("user_id", data1);
//
                            intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent1);
                            Toast.makeText(Profile.this, email, Toast.LENGTH_SHORT).show();
//                            PreferenceUtils.saveid(data1,Profile.this);
//                            PreferenceUtils.saveToken(data,Profile.this);


                        } else {


                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();


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
                    Toast.makeText(Profile.this, str, Toast.LENGTH_SHORT).show();


                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");
                        Toast.makeText(Profile.this, data.toString(), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Profile.this));
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

    public void get_profile() {

        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-profile-details";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


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
                    image = jsonObject.getString("image");


                    if (Success.equals("true")) {
                        Log.i("123", msg);


                        emailtxt.setText(email);
                        contacttxt.setText(phone);
                        user_name.setText(namee);
                        email_verify.setText(email_verifyy);
                        contact_verify.setText(phone_verifyy);


                        //  profile.setImageURI(Uri.parse(image));


                        profile.setImageURI(Uri.parse(image));

//
//                        ContextWrapper cw = new ContextWrapper(getApplicationContext());
//                        File directory = cw.getDir(filePath, Context.MODE_PRIVATE);
//                        File file = new File(directory,filePath);
//                        profile.setImageDrawable(Drawable.createFromPath(file.toString()));


//                        ContextWrapper cw = new ContextWrapper(getApplicationContext());
//                        File directory = cw.getDir("image", Context.MODE_PRIVATE);
//                        File file = new File(directory, "image" + ".png");
//                        profile.setImageDrawable(Drawable.createFromPath(file.toString()));

                        Toast.makeText(Profile.this, profile.toString(), Toast.LENGTH_SHORT).show();


                    } else {
                        Log.i("1234", msg);
                        Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();
                    }


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
                Toast.makeText(Profile.this, "Not", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();


                headers.put("Authorization", "Bearer " + PreferenceUtils.getToken(Profile.this));
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Profile.this);
        requestQueue.add(jsonObjectRequest);


    }

    public void change_email() {

        Email_get = email_edit_txt.getText().toString();


        String url = "http://nk.inevitabletech.email/public/api/change-email";
        JSONObject jsonBody = new JSONObject();


        try {
            jsonBody.put("email", Email_get);
            Toast.makeText(Profile.this, Email_get, Toast.LENGTH_SHORT).show();


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

//                    Log.i("0000000000000",response.toString());
//                    Toast.makeText(Profile.this, response.toString(), Toast.LENGTH_SHORT).show();


                    try {
                        String Success = response.getString("success");
                        String msg = response.getString("message");


                        if (Success.equals("true")) {
                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();

                            Intent intent1 = new Intent(Profile.this, Change_Email_OTP.class);
                            intent1.putExtra("email", Email_get);
                            intent1.putExtra("user_id", data1);

                            intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent1);
//                            PreferenceUtils.saveid(data1,Profile.this);
//                            PreferenceUtils.saveToken(data,Profile.this);


                        } else {


                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();


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
                    Toast.makeText(Profile.this, str, Toast.LENGTH_SHORT).show();


                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");
                        Toast.makeText(Profile.this, data.toString(), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Profile.this));
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


    public void change_phone() {


        Phone_get = con_edit_txt.getText().toString();


        String url = "http://nk.inevitabletech.email/public/api/change-phone";
        JSONObject jsonBody = new JSONObject();


        try {
            jsonBody.put("phone", Phone_get);
            Toast.makeText(Profile.this, Phone_get, Toast.LENGTH_SHORT).show();


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

//                    Log.i("0000000000000",response.toString());
//                    Toast.makeText(Profile.this, response.toString(), Toast.LENGTH_SHORT).show();


                    try {
                        String Success = response.getString("success");
                        String msg = response.getString("message");


                        if (Success.equals("true")) {
                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();

                            Intent intent1 = new Intent(Profile.this, Change_Phone.class);
                            intent1.putExtra("phone", Phone_get);
                            intent1.putExtra("user_id", id);

                            Toast.makeText(Profile.this, id, Toast.LENGTH_SHORT).show();

                            intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent1);
//                            PreferenceUtils.saveid(data1,Profile.this);
//                            PreferenceUtils.saveToken(data,Profile.this);


                        } else {


                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();


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
                    Toast.makeText(Profile.this, str, Toast.LENGTH_SHORT).show();


                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");
                        Toast.makeText(Profile.this, data.toString(), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Profile.this));
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

//
//    void imageChooser() {
//
//
//        Intent pickImageIntent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        pickImageIntent.setType("image/*");
//        pickImageIntent.putExtra("aspectX", 1);
//        pickImageIntent.putExtra("aspectY", 1);
//        pickImageIntent.putExtra("scale", true);
//        pickImageIntent.putExtra("outputFormat",
//                Bitmap.CompressFormat.JPEG.toString());
//        startActivityForResult(pickImageIntent, SELECT_PICTURE);
//
//    }

//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK ) {
//
//            // compare the resultCode with the
//            // SELECT_PICTURE constant
//            if (requestCode == SELECT_PICTURE) {
//                // Get the url of the image from data
//                Uri selectedImageUri = data.getData();
//                if (null != selectedImageUri) {
//                    // update the preview image in the layout
//                    profile.setImageURI(selectedImageUri);
//
//                }
//            }
//        }
//    }



//    private void imageUpload() {
//
//
//        JSONObject jsonObject = new JSONObject();
////        try {
////            jsonObject.put("image",filePath);
////
////            Toast.makeText(Profile.this, filePath, Toast.LENGTH_SHORT).show();
//
//  String url = "http://nk.inevitabletech.email/public/api/send-profile-details";
//        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,null, new Response.Listener<NetworkResponse>() {
//            @Override
//            public void onResponse(NetworkResponse response) {
//
//                try {
//                    JSONObject obj = new JSONObject(new String(response.data));
//
//                    Toast.makeText(Profile.this, obj.toString(), Toast.LENGTH_SHORT).show();
//                    Log.i("uyfdthggu",obj.toString());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Charset charset = Charset.defaultCharset();
//                String str = new String(error.networkResponse.data,charset);
//                Toast.makeText(Profile.this, str, Toast.LENGTH_SHORT).show();
//                Log.i("uyfdthggu", str.toString());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//
//                return params;
//            }
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Accept","application/json");
//                params.put("Authorization", "Bearer  " +PreferenceUtils.getToken(Profile.this));
//
//                return params;
//            }
//
//
//
//            @Override
//            protected Map<String, DataPart> getByteData() {
//                Map<String, DataPart> params = new HashMap<>();
//                long imagename = System.currentTimeMillis();
//                params.put("image", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
//                return params;
//            }
//        };
//
//        multipartRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        MyApplication.getInstance().addToRequestQueue(multipartRequest);
//    }


//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
//            @Override
//            public void onResponse(NetworkResponse response) {
//
//
//                Toast.makeText(getApplicationContext(), "Profile photo uploaded", Toast.LENGTH_LONG).show();
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//
//                return params;
//            }
//
//            @Override
//            protected Map<String, DataPart> getByteData() {
//                Map<String, DataPart> params = new HashMap<>();
//                // file name could found file base or direct access from real path
//                // for now just get bitmap data from ImageView
////                params.put("avatar", new DataPart("file_avatar.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), mAvatarImage.getDrawable()), "image/jpeg"));
////                params.put("cover", new DataPart("file_cover.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), mCoverImage.getDrawable()), "image/jpeg"));
//
//                return params;
//            }
//        };
//
//        VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);

//
//        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("Response", response);
//                        try {
//                            JSONObject jObj = new JSONObject(response);
//                            String message = jObj.getString("message");
//
//                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
//
//                        } catch (JSONException e) {
//                            // JSON error
//                            e.printStackTrace();
//                            Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        smr.addFile("image", imagePath);
//        MyApplication.getInstance().addToRequestQueue(smr);
//
//    }
//


//    private String getPath(Uri contentUri) {
//        String[] proj = { MediaStore.Images.Media.DATA };
//        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
//        Cursor cursor = loader.loadInBackground();
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String result = cursor.getString(column_index);
//        cursor.close();
//        return result;
//    }



  //  public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//
//            if(requestCode == PICK_IMAGE_REQUEST){
//                Uri picUri = data.getData();
//
//                filePath = getPath(picUri);
//
//                Log.d("picUri", picUri.toString());
//                Log.d("filePath", filePath);
//
//                profile.setImageURI(picUri);
//
//            }
//
//        }
//
//    }
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//
////                params.put("image",filePath);
////                Log.i("lwhgfiruweyt3ru",params.toString());
//                return params;
//            }