package com.e.login.BloodClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Blood_Fragment extends AppCompatActivity {

    List<Blood_Model> bloodModelList;
    Blood_Adapter adapter;
    Button blood;
    RecyclerView recyclerView;
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9;
    String data1,data2,data3,data4,data5,data6,data7,data8,data9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_fragment);

        recyclerView = findViewById(R.id.blood_screen);

//
//        bloodModelList = new ArrayList<>();
//
//
//        for (int i = 0; i < 4; i++) {
//
//
//
//            Blood_Model viewmodel = new Blood_Model();


//
//            viewmodel.setImg("1");
//            viewmodel.setName("Jenifer");
//            viewmodel.setPosted("Posted on 30");
//            viewmodel.setP_name("Patient name4566666666666666666frcyuswgyswi4jrftigshwj");
//            viewmodel.setB_grp("Blood group7454646444444444444444444");
//            viewmodel.setProblem("Problem546666666frwtfvrwrgtrd44444444444444444444444");
//            viewmodel.setNeed("Needed within546cdvfgbjhvguhjhjighvjohjhgjo");
//            viewmodel.setUnits("No.of.Units456ahbgfrhgviofrhgvibytgpijh");
//            viewmodel.setHospital("Hospitalhgv45ip6tguhb;povgpdep");
//            viewmodel.setC_num("Contact number456etfjghbpot9be3");
//            viewmodel.setA_num("Alternative number546hib4444444444444444444444");
//            viewmodel.setAddress("karur, India,TamilNadudthytdujh7");
//


//
//            bloodModelList.add(viewmodel);
//
//        }
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(Blood_Fragment.this));
//
//        adapter =  new Blood_Adapter(Blood_Fragment.this,bloodModelList);
//        recyclerView.setAdapter(adapter);




        blood = findViewById(R.id.blood_btn);
        blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Blood_Fragment.this,Blood_One.class);
                startActivity(intent);
            }
        });
    }


    public void request(){


    }

}