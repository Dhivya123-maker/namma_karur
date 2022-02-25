package com.e.login.GovtClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NGOActivity extends AppCompatActivity {

    List<NGOModel> ngoModelList;
    NGOAdapter adapter;
    String id = null,name = null,image =null,des = null;

    String get_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngoactivity);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);


        Intent i = getIntent();
        get_id = i.getStringExtra("id");



        String url = "http://nk.inevitabletech.email/public/api/get-ngo-govt-catalog-list?subcategory_id="+get_id;

        RecyclerView recyclerView =findViewById(R.id.ngoo_screens);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


                ngoModelList = new ArrayList<>();


                try {
                    JSONArray res = response.getJSONArray("data");




                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);



                        id = jsonObject.getString("id");
                        image = jsonObject.getString("logo");
                        name = jsonObject.getString("title");
                        des = jsonObject.getString("address");


                        Toast.makeText(NGOActivity.this, image.toString(), Toast.LENGTH_SHORT).show();
                        Log.i("jbfhusduycfhb",image.toString());

                        NGOModel viewmodel = new NGOModel();


                        viewmodel.setText(name);
                        viewmodel.setImage(image);
                        viewmodel.setId(id);

                        viewmodel.setText_one(des);



                        ngoModelList.add(viewmodel);

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


                recyclerView.setLayoutManager(new LinearLayoutManager(NGOActivity.this));

                adapter =  new NGOAdapter(NGOActivity.this,ngoModelList);
                recyclerView.setAdapter(adapter);

            }



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

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(NGOActivity.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(NGOActivity.this);
        requestQueue.add(jsonObjectRequest);






    }
}