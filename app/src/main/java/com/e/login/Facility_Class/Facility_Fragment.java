package com.e.login.Facility_Class;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.Gallery_Class.Gallery_Adapter;
import com.e.login.Gallery_Class.Gallery_Model;
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


public class Facility_Fragment extends Fragment {


    List<Facility_Model> facilityModelList;
    Facility_Adapter adapter;
    RecyclerView recyclerView;
    String data;
    String id,cat_id,catalog_id,img,title,desc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_facility_, container, false);


        recyclerView = root.findViewById(R.id.facility_recycle);

        Intent intent = getActivity().getIntent();
        data = intent.getStringExtra("id");

        Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
        facility();
//
//        for (int i = 0; i < 4; i++) {
//
//            Facility_Model viewmodel = new Facility_Model();
//
//
//            viewmodel.setImg("1");
//            viewmodel.setTxt("Wedding Reception Hall");
//            viewmodel.setTxt1("Lorem ispum may be used as a placeholder before the lorem ispum");
//
//            facilityModelList.add(viewmodel);

//
//        }
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        adapter =  new Facility_Adapter(getContext(),facilityModelList);
//        recyclerView.setAdapter(adapter);


        return  root;
    }


    public  void facility(){
        String url = "http://nk.inevitabletech.email/public/api/get-event-facility?catalog_id="+data;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



                try {
                    JSONArray res = response.getJSONArray("data");

                    facilityModelList = new ArrayList<>();

                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);


                        id = jsonObject.getString("id");
                        cat_id = jsonObject.getString("category_id");
                        catalog_id = jsonObject.getString("catalog_id");
                        img = jsonObject.getString("image");
                        title = jsonObject.getString("title");
                        desc = jsonObject.getString("description");
                        Log.i("124hjdg",id);
                        Log.i("124hjd",cat_id);
                        Log.i("12",catalog_id);
                        Log.i("124hjdg",title);
                        Log.i("deklf",desc);
                        Log.i("124hjdg",img);



                        Facility_Model viewmodel = new Facility_Model();


                        viewmodel.setImg(img);
                        viewmodel.setTxt(title);
                        viewmodel.setTxt1(desc);

                        facilityModelList.add(viewmodel);



                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                adapter =  new Facility_Adapter(getContext(),facilityModelList);
                recyclerView.setAdapter(adapter);



            }

//
//
//        }
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

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);

    }
}