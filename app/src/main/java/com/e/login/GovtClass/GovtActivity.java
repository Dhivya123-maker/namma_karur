package com.e.login.GovtClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.R;
import com.e.login.ShopClass.ShopClassAdapter;
import com.e.login.ShopClass.ShopModel;
import com.e.login.ShopClass.ShopScreen_Class;
import com.e.login.fragment_dialog.BottomSheetFragment_filter;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GovtActivity extends AppCompatActivity {


    List<GovtModel> govtModelList;
    GovtAdapter adapter;
    LinearLayout filter;
    public static final String TAG = "bottom_sheet";
    String id = null,name = null,image =null;
    String get_id = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govt);


        Intent i = getIntent();
        get_id = i.getStringExtra("id");



        String url = "http://nk.inevitabletech.email/public/api/get-ngo-govt-subcategory-list?category_id="+get_id;

        RecyclerView recyclerView =findViewById(R.id.govt_screen);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


                govtModelList = new ArrayList<>();


                try {
                    JSONArray res = response.getJSONArray("data");




                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);

;

                        id = jsonObject.getString("id");
                        image = jsonObject.getString("logo");
                        name = jsonObject.getString("title");


//                        Toast.makeText(GovtActivity.this, image.toString(), Toast.LENGTH_SHORT).show();
//                        Log.i("jbfhusduycfhb",image.toString());

                        GovtModel viewmodel = new GovtModel();


                        viewmodel.setText(name);
                        viewmodel.setImage(image);
                        viewmodel.setId(id);


                        govtModelList.add(viewmodel);

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


                recyclerView.setLayoutManager(new LinearLayoutManager(GovtActivity.this));

                adapter =  new GovtAdapter(GovtActivity.this,govtModelList);
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

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(GovtActivity.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(GovtActivity.this);
        requestQueue.add(jsonObjectRequest);








    }
}