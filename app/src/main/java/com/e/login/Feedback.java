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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);


        submit = findViewById(R.id.sub_rev);


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

                    Comments = comments.getText().toString();

                    String url = "http://nk.inevitabletech.email/public/api/namma-karur-feedback";
                    JSONObject jsonBody = new JSONObject();

                    try {
                        jsonBody.put("rating", rt.getRating());
                        jsonBody.put("comment", Comments);
                        jsonBody.put("recommendation",selectedRbText);

                        Log.i("kiejqghriuwegtyr",jsonBody.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
//
//                                Log.i("0000000000000", response.toString());
//                                Toast.makeText(Feedback.this, response.toString(), Toast.LENGTH_SHORT).show();


                                try {
                                    String Success = response.getString("success");
                                    String msg = response.getString("message");


                                    if (Success.equals("true")) {
                                        Toast.makeText(Feedback.this, msg, Toast.LENGTH_SHORT).show();



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
                                Toast.makeText(Feedback.this, str, Toast.LENGTH_SHORT).show();




                            }
                        }) {

                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("Accept", "application/json");
                                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Feedback.this));
                                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken1(Feedback.this));
                                return params;


                            }
                        };

                        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                                10000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                        RequestQueue requestQueue = Volley.newRequestQueue(Feedback.this);
                        requestQueue.add(jsonObjectRequest);



                }else{


                }
            }
        });

        //selectedRbText = radioButton.getText().toString();
//        if (selectedRadioButtonId != -1) {
//            selectedRbText = radioButton.getText().toString();

//        }
//        else{
//
//        }

        }

        public  void feedback(){


        }


}