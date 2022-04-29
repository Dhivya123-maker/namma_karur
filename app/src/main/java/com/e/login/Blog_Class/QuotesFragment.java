package com.e.login.Blog_Class;

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

import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QuotesFragment extends Fragment {
    List<Quotes_model> quotesModelList;
    Quotes_Adapter adapter;
    RecyclerView recyclerView;
    String id,name,image,view_count = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View root =  inflater.inflate(R.layout.fragment_quotes, container, false);



        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-quote-category-list";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {




                try {

                    recyclerView = root.findViewById(R.id.quotes_recycle);
                    quotesModelList = new ArrayList<>();

//                    String Success = response.getString("success");
//                    String msg = response.getString("message");

                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        id = jsonObject.getString("id");
                        name = jsonObject.getString("name");
                        image = jsonObject.getString("image");
                       view_count = jsonObject.getString("view_count");

                        Quotes_model  model = new Quotes_model();
                        model.setTxt(name);
                        model.setImg(image);
                        model.setTxt1(view_count);
                        model.setId(id);

                        quotesModelList.add(model);

                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    adapter =  new Quotes_Adapter(getActivity(),quotesModelList);
                    recyclerView.setAdapter(adapter);



                } catch (Exception e) {
                    e.printStackTrace();

                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    adapter =  new Quotes_Adapter(getActivity(),quotesModelList);
                    recyclerView.setAdapter(adapter);


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

                params.put("Authorization", "Bearer " + PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);




        return  root;
    }
}