package com.e.login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.e.login.BlankFragment.Blank_Adapter;
import com.e.login.BlankFragment.Blank_Model;
import com.e.login.BlankFragment.Blank_PostFragment;
import com.e.login.ShopClass.ShopScreen_Class;
import com.e.login.Verification.VerifyActivity;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReviewsActivity extends AppCompatActivity {

    ImageView rating;
    RatingBar rt;
    EditText comment;
    String Comment,Rating,data,data2;
    Button send;
    float rat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);


        Intent intent = getIntent();
        data = intent.getStringExtra("cat");
        data2 = intent.getStringExtra("id");

        Log.i("khjhjh",data);

        rt = (RatingBar) findViewById(R.id.ratingBar);


        LayerDrawable stars=(LayerDrawable)rt.getProgressDrawable();


        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        stars.getDrawable(1).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        comment = findViewById(R.id.review_desc);
        send = findViewById(R.id.buton);



     rat = rt.getRating();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Comment = comment.getText().toString();
//
                register();

//
////                Toast.makeText(ReviewsActivity.this, (int) rat, Toast.LENGTH_SHORT).show();
////                Log.i("gtsgdgsh", String.valueOf(rt.getRating()));
//                 Toast.makeText(ReviewsActivity.this, data, Toast.LENGTH_SHORT).show();
//                Toast.makeText(ReviewsActivity.this, data2, Toast.LENGTH_SHORT).show();
//                Toast.makeText(ReviewsActivity.this, Comment, Toast.LENGTH_SHORT).show();
//
//
//                String URL = "http://nk.inevitabletech.email/public/api/review-submit";
//                RequestQueue requestQueue1 = Volley.newRequestQueue(ReviewsActivity.this);
//                JSONObject jsonBody = new JSONObject();
//
//                try{
//                    jsonBody.put("model",data);
//                    jsonBody.put("model_id",data2);
//                    jsonBody.put("rating","3");
//                    jsonBody.put("comment",Comment);
//
//
//                    JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//
//
//                            Toast.makeText(ReviewsActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//
//
////                    try{
////                        String Success = response.getString("success");
////                        String msg = response.getString("message");
////
////
////
////
////                        if (Success.equals("true")){
////
////                            Toast.makeText(ReviewsActivity.this, msg, Toast.LENGTH_SHORT).show();
////                            Log.i("yut86t8",msg);
//
//
//                            Intent intent = new Intent(ReviewsActivity.this,Home_Fragment_Class.class);
//                            intent.putExtra("list",data);
//                            intent.putExtra("id",data2);
//                            startActivity(intent);
////
////                        }
////                        else {
////
////                            Toast.makeText(ReviewsActivity.this, msg, Toast.LENGTH_SHORT).show();
////
////
////                        }
////
////
////                    }catch (Exception e) {
////                        e.printStackTrace();
////
////                    }
////
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                        }
//                    }){
//
//                        @Override
//                        public Map<String, String> getHeaders() throws AuthFailureError {
//                            Map<String,String> params = new HashMap<String, String>();
//                            params.put("Authorization","Bearer "+ PreferenceUtils.getToken(ReviewsActivity.this));
//                            return params;
//                        }
//                    };
//
//                    jsonObjectRequest1.setRetryPolicy(new DefaultRetryPolicy(
//                            10000,
//                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//                    requestQueue1.add(jsonObjectRequest1);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }


        });



    }


    public void register(){




        String URL = "http://nk.inevitabletech.email/public/api/review-submit";

        JSONObject jsonBody = new JSONObject();




        try {


            jsonBody.put("model",data);
            jsonBody.put("model_id",data2);
            jsonBody.put("rating",rt.getRating());
            jsonBody.put("comment",Comment);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Intent intent = new Intent(ReviewsActivity.this,Home_Fragment_Class.class);
                    intent.putExtra("list",data);
                    intent.putExtra("id",data2);

                    startActivity(intent);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            }){

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(ReviewsActivity.this));
                    return params;
                }


            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(ReviewsActivity.this);
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }






    }


}