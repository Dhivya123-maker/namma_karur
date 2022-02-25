package com.e.login.HospitalClass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.R;
import com.e.login.TransportClass.Transport_ServicesFragmentAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Hospital_services_fragment extends Fragment implements Transport_ServicesFragmentAdapter.OnItemClickListener{

    List<Hospital_Services_Model> hospitalServicesModelList;
    Hospital_ServicesFragmentAdapter adapter;
    String data,data1,id,image,trans_id,desc,rate,name;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View root = inflater.inflate(R.layout.fragment_hospital_services_fragment, container, false);


        Intent intent = getActivity().getIntent();
        data= intent.getStringExtra("token");
        data1 = intent.getStringExtra("id");


        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-hospital-treatments?hospital_id=1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



                Log.i("jehuihrfo",response.toString());
                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();

                try {




                    String Success = response.getString("success");
                    String msg = response.getString("message");

//
                    if(Success.equals("true")){
                        Log.i("123",msg);
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();



                        JSONArray data = response.getJSONArray("data");


                        JSONObject jsonObject = data.getJSONObject(0);

                        id = jsonObject.getString("id");
                        name = jsonObject.getString("name");
                        image = jsonObject.getString("image");
                        desc = jsonObject.getString("description");
                        rate = jsonObject.getString("rate");






                        Log.i("johoi",trans_id);
                        Log.i("hiuhiu",image);


                        hospitalServicesModelList = new ArrayList<>();

                        RecyclerView recyclerView = root.findViewById(R.id.hospital_services_recycle);


                        for (int i = 0; i < response.length(); i++) {

                            Hospital_Services_Model viewmodel = new Hospital_Services_Model ();



                            viewmodel.setImage(image);
                            viewmodel.setButton("more");
                            viewmodel.setText_one(desc);
                            viewmodel.setText(name);


                            hospitalServicesModelList.add(viewmodel);

                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        adapter =  new Hospital_ServicesFragmentAdapter(getContext(),hospitalServicesModelList);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener((Hospital_ServicesFragmentAdapter.OnItemClickListener) getActivity());









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
                params.put("Authorization","Bearer  "+ data);
                // params.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMTM5N2QxNTAyOGZjZmFjYWVmNjg4MGI0M2Q2OWE0MjU0NjFhMjQwMDQ2ZDc2MDQ1MDk0ZWIxZDk2NDNhZTk4YmE3YTkwMGZhODQwZDIzZGMiLCJpYXQiOjE2NDQ2NjIwNDUuNjk5MTE4LCJuYmYiOjE2NDQ2NjIwNDUuNjk5MTIyLCJleHAiOjE2NzYxOTgwNDUuNjk2MDgxLCJzdWIiOiIxNiIsInNjb3BlcyI6W119.kCe6D4wazRjA5cmETRJhsbKiD6BKhyY_ENT8Ve9QluNjdix7PJI-3HK82fdOAD_A0KYtDhHtCqQlWEEVYT9E6MAueMvPTJ06LQyK5o8C_iUS1n_dWPS04bb1N5R_pIIRdS3wz20JuobRBkAXxTcYM74bnfMmKEVxcmyhwoFdlnDctm3aNEN7NI-2dFVrviYUIbN8L2y3bbZy8zlijMBs7vh77sSVVFMkLgJCiMaKxF-hTyS-wrRz-2ClGqRdQYMQK9y6zlw_-47I9arebNWxukZUGc4-cgJUSedJ7GNuJVBux4PclLns-z6hXIQjCr_C8icbMUmAU7GOWHTnpc1fA-lR-1OQfLFBcwksRWmEFB_O60PVjQzw3L1uh_rW-DsNJs_y1BADJ8RpwMfA9-dVcL7Df-EdnrMP-E1ZgI62_QfuMcc6jM1-LWXzmUHitb8sUSFLqX6OfUGpM2sQ6jtPF3bVR5H9P4Idck3RFfNa1OGikyoldYGFaMOKs-C5fgW2t4YtVFnKJ3ROC9Pg-7ipguu4uCAxUmqsOWDlTAfQglKWZuNsSq_4eKDbq2eq6oDtlFDgO8e5XeYk6bwIQFTcf5RZlcHbMz135zLYu4872r2nsCLrzgeyb5-aveqzS9I5rWufpAz3TkejDzUbb9yItuo0LjbppHY8cw3r4Vz837U");
                //  params.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNDRjOGQyOWFhMzdmYTk1MTE2OTAzNWI3MzcwZTk2Njk4MmVmMmQzZTcxZjI3MjRkNjViYTI0YTdhMTE1MzMxM2QyYmFjNmM4YTcwNDJjYzQiLCJpYXQiOjE2NDQ1NTc5MDIuMTU3NTI0LCJuYmYiOjE2NDQ1NTc5MDIuMTU3NTI4LCJleHAiOjE2NzYwOTM5MDIuMTUxOTIzLCJzdWIiOiI1NiIsInNjb3BlcyI6W119.xhAjKhtWGprH1hAHckkYdXpjl7MmITLytdcawHOQ6h_6MYO8-CZpavBknPbeJXLf8G51LbypFf9VOKML7kKmmgqWi6SB5MxhtUWs7JgszMDi2B8URqj1-hXBQqOOOaR5BJzaXXa37T4Br6NeyvVz_U9z-9pxw-pQzshjSAwJCHAXMpGwMLy4IEug4npqa-Ym7ixBRt0b_VBw0tLzCw4wqDU61OgBWZqFy0J8Mwn4_OKi8XrqJxK1CzZxtwFzDAE7p5eTyOiRD8Ijb4k20AqIXkcb1Cz1BMGYrVQiDmdOtFKUmuHERTvNvF4578ES77ZBE0pvyueDgU2u5713vUeDGPjLyL1cQUXsXg8gmoIdVGl1-S0mOP-y0wTW8-PxTHQuFdsiPcVMLfVP9RQ4GzA2ehEStRJbZyOQwXe7WrS2DMTkg4FkZqMe9q7qGn7Lsuz2tNSM7OooyDxnNTORhWs8w3yDgn_KhSrN-PW7aZQs5w3pVSI4oLdOiR-vMtyPw_ofIiJQV0UFiTCvlX9ln99aVybh8HzWIrQlz96RP8127lIPfQC4-bQAKrXya0xXnfOYSLzWNja13ohdEgz0lN25bLPieW_eQyJT2_Ypk3Cy44lEsaMPndy2xvdt9EfXA_LfLXUzlwTLSCjRgxBJbO-g6bmgZPEoACxwkrVKJzVzZac");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
        return  root;
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), HospitalCarrier.class);
        startActivity(intent);

    }
}