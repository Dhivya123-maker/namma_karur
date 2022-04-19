package com.e.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
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
import com.e.login.Verification.Email_OTP;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Feedback extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton r1,r2,radioButton;
    int selectedRadioButtonId = -1;
    String selectedRbText="";
    RatingBar rt;
    float rat;
    EditText comments;
    String Comments;
    Button submit;
    TextView feedback,rec,rate_err;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);


        submit = findViewById(R.id.sub_rev);
        feedback = findViewById(R.id.feedback_err);
        rec = findViewById(R.id.rec_err);
        rate_err = findViewById(R.id.rate_err);


        rt = (RatingBar) findViewById(R.id.ratingBar);


        LayerDrawable stars=(LayerDrawable)rt.getProgressDrawable();


        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        stars.getDrawable(1).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);


        comments = findViewById(R.id.fback);

        rat = rt.getRating();

        radioGroup = findViewById(R.id.radio_gp);

        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);

        if (r1.isChecked()){
            r2.setClickable(false);
            selectedRbText = r1.getText().toString();

        }else{
            r1.setClickable(true);
            selectedRbText = r2.getText().toString();
        }



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedRadioButtonId);


                if (selectedRadioButtonId != -1) {
                    selectedRbText = radioButton.getText().toString();



                    String url = "http://nk.inevitabletech.email/public/api/namma-karur-feedback";
                    JSONObject jsonBody = new JSONObject();

                    try {
                        jsonBody.put("rating", rt.getRating());
                        jsonBody.put("comment", Comments);
                        jsonBody.put("recommendation",selectedRbText);

                        Log.i("kiejqghriuwegtyr",jsonBody.toString());


                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {



                                try {
                                    String Success = response.getString("success");
                                    String msg = response.getString("message");


                                    if (Success.equals("true")) {
                                        Toast.makeText(Feedback.this, msg, Toast.LENGTH_SHORT).show();
                                        rate_err.setVisibility(View.GONE);
                                        feedback.setVisibility(View.GONE);
                                        rec.setVisibility(View.GONE);




                                    } else {


                                        Toast.makeText(Feedback.this, msg, Toast.LENGTH_SHORT).show();


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

//
//                                try {
//                                    JSONObject jsonObject = new JSONObject(str);
//                                    JSONObject jsonObject1 = jsonObject.getJSONObject("errors");
//                                    if(Comments.isEmpty()){
//                                        JSONArray jsonArray = jsonObject1.getJSONArray("comment");
//                                        feedback.setText(jsonArray.getString(0));
//                                        feedback.setVisibility(View.VISIBLE);
//                                        rec.setVisibility(View.GONE);
//
//                                    }

//                                    else if(selectedRbText.isEmpty()){
//                                        JSONArray jsonArray1 = jsonObject1.getJSONArray("recommendation");
//                                        rec.setText(jsonArray1.getString(0));
//                                        rec.setVisibility(View.VISIBLE);

//                                    }
//
//                                 else{
//                                        JSONArray jsonArray2 = jsonObject1.getJSONArray("rating");
//                                        rate_err.setText(jsonArray2.getString(0));
//                                        rate_err.setVisibility(View.VISIBLE);
//
//                                    }


//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }


                            }
                        }) {

                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("Accept", "application/json");
                                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Feedback.this));
                                return params;


                            }
                        };

                        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                                10000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                        RequestQueue requestQueue = Volley.newRequestQueue(Feedback.this);
                        requestQueue.add(jsonObjectRequest);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }else{
                    Comments = comments.getText().toString();
                     if(rt.getRating()==0){
                        rate_err.setText("The rating field is required.");
                        rate_err.setVisibility(View.VISIBLE);
                        feedback.setVisibility(View.GONE);
                        rec.setVisibility(View.GONE);
                    }
                   else if(Comments.isEmpty()){
                        feedback.setText("The comment field is required.");
                        feedback.setVisibility(View.VISIBLE);
                        rec.setVisibility(View.GONE);
                        rate_err.setVisibility(View.GONE);
                    }


                   else {
                        rec.setText("The recommendation field is required.");
                        rec.setVisibility(View.VISIBLE);
                        rate_err.setVisibility(View.GONE);
                        feedback.setVisibility(View.GONE);
                    }





                }
            }
        });



        }




}