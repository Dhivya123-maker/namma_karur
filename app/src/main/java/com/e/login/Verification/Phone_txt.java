package com.e.login.Verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.e.login.Profile;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Phone_txt extends AppCompatActivity {
    Button send;
    EditText number;
    String Num,user_id,id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_txt);

        Intent intent = getIntent();

        id = intent.getStringExtra("id");


        send = findViewById(R.id.send_phone);
        number = findViewById(R.id.verify_edit1);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_phone();

            }
        });
    }

    public void change_phone() {


        Num = number.getText().toString();

        String url = "http://nk.inevitabletech.email/public/api/change-phone";
        JSONObject jsonBody = new JSONObject();


        try {
            jsonBody.put("phone", Num);


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

//                    Log.i("0000000000000",response.toString());
//                    Toast.makeText(Profile.this, response.toString(), Toast.LENGTH_SHORT).show();


                    try {
                        String Success = response.getString("success");
                        String msg = response.getString("message");


                        if (Success.equals("true")) {
                            Toast.makeText(Phone_txt.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Phone_txt.this,Change_Phone.class);
                            intent.putExtra("phone",Num);
                            intent.putExtra("id", id);

                            Toast.makeText(Phone_txt.this, id, Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        } else {


                            Toast.makeText(Phone_txt.this, msg, Toast.LENGTH_SHORT).show();


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
                    Toast.makeText(Phone_txt.this, str, Toast.LENGTH_SHORT).show();


                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");
                        Toast.makeText(Phone_txt.this, data.toString(), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Phone_txt.this));
                    return params;


                }
            };

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Phone_txt.this);
            requestQueue.add(jsonObjectRequest);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}