package com.e.login.AmbulanceClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ambulance_call extends AppCompatActivity {
    List<Ambulance_Call_Model> ambulanceCallModelList;
    Ambulance_call_adapter adapter;
    RecyclerView recyclerView;
    List<ShopModel> shop_model;
    ShopClassAdapter adapter1;
    String data3;
    String aname=null,ades=null,aimg=null,anum=null,apri =null,asec =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_call);

        recyclerView = findViewById(R.id.ambulance_call);

        Intent intent = getIntent();
        data3 = intent.getStringExtra("cat");
//
//        ambulanceCallModelList = new ArrayList<>();
//
//
//            Ambulance_Call_Model viewmodel = new Ambulance_Call_Model();
//
//
//            viewmodel.setPri_call("Primary call");
//
//            viewmodel.setPri("1");
//
//            ambulanceCallModelList.add(viewmodel);
//
//
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(Ambulance_call.this));
//
//        adapter =  new Ambulance_call_adapter(Ambulance_call.this,ambulanceCallModelList);
//        recyclerView.setAdapter(adapter);
//




    }

}