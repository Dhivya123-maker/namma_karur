package com.e.login.ShoppostClass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.e.login.BaseApi.Api;
import com.e.login.ProductsFragmentClass.ProductsAdapter;
import com.e.login.ProductsFragmentClass.ProductsModel;
import com.e.login.R;
import com.e.login.ShopscreenClass.ShopScreenAdapter;
import com.e.login.ShopscreenClass.ShopsScreenFragment;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShopPost extends Fragment {
    List<ShopPostModel> shopPostModelList;
    ShopPostAdapter adapter;
    String title,img,desc;
    String data,data1 = null;
    String api = null;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_shop_post, container, false);


        Intent intent = getActivity().getIntent();
        data = intent.getStringExtra("id");
        data1 = intent.getStringExtra("list");

        Api a = new Api();
        api = a.getBASE_URL();

        recyclerView = root.findViewById(R.id.sam_shop);

        flash();

//

//
//
//        for (int i = 0; i < 2; i++) {
//

//
//        }
//


        return root;
    }


    public void flash(){
        String url = api+"get-flash-by-model-id?model="+data1+"&model_id="+data;
//        String url = "http://nk.inevitabletech.email/public/api/get-offer-by-model-id?"+data1+data;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


//                Log.i("0000000",response.toString());
//                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();





                try {
                    JSONArray res = response.getJSONArray("data");


                    shopPostModelList = new ArrayList<>();



                    for (int i=0;i<res.length();i++){
//
//                        Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
//                        Log.i("jbfhusduycfhb",response.toString());

                        JSONObject jsonObject = res.getJSONObject(i);
                        title = jsonObject.getString("title");
                        img = jsonObject.getString("image");
                        desc = jsonObject.getString("description");

                        ShopPostModel viewmodel = new ShopPostModel();


                        viewmodel.setText(title);

                        viewmodel.setText_two("5 days ago");
                        viewmodel.setText_three(desc);
                        viewmodel.setImage("1");
                        viewmodel.setImage1("2");
                        viewmodel.setImage2(img);

                        viewmodel.setImage3("4");
                        viewmodel.setImage4("5");




                        shopPostModelList.add(viewmodel);




                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    adapter =  new ShopPostAdapter(getActivity(),shopPostModelList);
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