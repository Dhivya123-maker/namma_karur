package com.e.login.NewsClass;

import android.annotation.SuppressLint;
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
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Astrology_news extends Fragment {
    List<NewsOneModel> newsOneModelList;
    NewsoneAdapter adapter2;
    RecyclerView recyclerView;
    String id,title,desc,image,view_count = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_astrology_news, container, false);


        recyclerView =root.findViewById(R.id.news_screen);



        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-astrology-list";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


                try {




                    String Success = response.getString("success");
                    String msg = response.getString("message");




                    newsOneModelList = new ArrayList<>();

                    JSONArray res = response.getJSONArray("data");



                    if(Success.equals("true")){

                        for (int i = 0; i < res.length(); i++) {

                            JSONObject jsonObject = res.getJSONObject(i);

                            id = jsonObject.getString("id");
                            image = jsonObject.getString("image");
                            title = jsonObject.getString("title");
                            view_count = jsonObject.getString("view_count");

                            NewsOneModel viewmodel = new NewsOneModel();



                            viewmodel.setImage(image);
                            viewmodel.setText(title);
                            viewmodel.setText_one(view_count);
                            viewmodel.setButton("View");



                            newsOneModelList.add(viewmodel);

                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        adapter2 =  new NewsoneAdapter(getContext(),newsOneModelList);
                        recyclerView.setAdapter(adapter2);








                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();


                }


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
                // params.put("Accept", "application/json");
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);


        return  root;
    }
}