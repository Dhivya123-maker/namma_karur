package com.e.login.info_Class;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.e.login.HelperClass.InfoPagerFragmentAdapter;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InformationFragment extends Fragment {
//    private InfoPagerFragmentAdapter infoPagerFragmentAdapter;
//    private ViewPager viewPager;
//    private TabLayout tabLayout;
//


    List<InfoModel> infoModelList;
    InformationAdapter adapter;
    RecyclerView recyclerView;
    String title,img,desc;
    String data,data1 = null;
    String api = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_information, container, false);



        Intent intent = getActivity().getIntent();
        data = intent.getStringExtra("id");
        data1 = intent.getStringExtra("list");

        Api a = new Api();
        api = a.getBASE_URL();

        recyclerView = root.findViewById(R.id.info_fragment);

        main_flash();
//

//        recyclerView = root.findViewById(R.id.info_fragment);
//
//        for (int i = 0; i < 4; i++) {
//
//
//
//        }
//





        return  root;
    }


    public void main_flash(){
        String url = api+"flash-home-page";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {






                try {
                    JSONArray res = response.getJSONArray("data");

                    infoModelList = new ArrayList<>();



                    for (int i=0;i<res.length();i++){



                        JSONObject jsonObject = res.getJSONObject(i);
                        title = jsonObject.getString("title");
                        img = jsonObject.getString("image");
                        desc = jsonObject.getString("description");


                        InfoModel viewmodel = new InfoModel();

                        viewmodel.setText(title);

                        viewmodel.setText_two("5 days ago");
                        viewmodel.setText_three(desc);
                        viewmodel.setImage("1");
                        viewmodel.setImage1("2");
                        viewmodel.setImage2(img);

                        viewmodel.setImage3("4");
                        viewmodel.setImage4("5");


                        infoModelList.add(viewmodel);




                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    adapter =  new InformationAdapter(getActivity(),infoModelList);
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