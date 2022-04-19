package com.e.login.MoreInfoClass;

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

import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.BaseApi.Api;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoreInfo extends Fragment {

    List<More_Info_Model> moreInfoModelList;
    More_Info_Adapter adapter;
    RecyclerView recyclerView;
    String title,img;
    String data,data1 = null;
    String api = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_more_info, container, false);

        Intent intent = getActivity().getIntent();
        data = intent.getStringExtra("id");
        data1 = intent.getStringExtra("list");

        Api a = new Api();
        api = a.getBASE_URL();


        info();






        recyclerView = root.findViewById(R.id.offers_home_screen);


        return root;
    }


    public void info(){

        String url = api+"get-offer-by-model-id?model="+data1+"&model_id="+data;
//        String url = "http://nk.inevitabletech.email/public/api/get-offer-by-model-id?"+data1+data;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


//                Log.i("0000000",response.toString());
//                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();






                try {
                    JSONArray res = response.getJSONArray("data");


                    moreInfoModelList = new ArrayList<>();


                    for (int i=0;i<res.length();i++){
//
//                        Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
//                        Log.i("jbfhusduycfhb",response.toString());

                        JSONObject jsonObject = res.getJSONObject(i);
                        title = jsonObject.getString("title");
                        img = jsonObject.getString("image");




                        More_Info_Model viewmodel = new More_Info_Model();




                        viewmodel.setImg("1");
                        viewmodel.setImg1(img);
                        viewmodel.setTxt(title);




                        moreInfoModelList.add(viewmodel);


                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    adapter =  new More_Info_Adapter(getActivity(),moreInfoModelList);
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }



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

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);



    }
}