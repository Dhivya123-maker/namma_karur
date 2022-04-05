package com.e.login.Gallery_Class;

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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.ProductsFragmentClass.ProductsAdapter;
import com.e.login.ProductsFragmentClass.ProductsModel;
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


public class Gallery_Fragment extends Fragment {


    List<Gallery_Model> galleryModelList;
    Gallery_Adapter adapter;


    RecyclerView recyclerView;
    String data;
    String gallery_name;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_gallery_, container, false);


        recyclerView = root.findViewById(R.id.gallery_two_screen);


        Intent intent = getActivity().getIntent();
        data = intent.getStringExtra("id");


        gallery();


        return  root;

    }
    public void gallery(){

        String url = "http://nk.inevitabletech.email/public/api/get-event-gallery?catalog_id="+data;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



                try {

                    galleryModelList = new ArrayList<>();

                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i=0;i< jsonArray.length();i++){

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);


                        gallery_name = jsonObject1.getString("category");

                        JSONArray jsonArray2 = jsonObject1.getJSONArray("images");





                        Gallery_Model viewmodel = new Gallery_Model();



                        viewmodel.setTxt(gallery_name);
                        viewmodel.setTxt1("view all");




//                        Toast.makeText(getActivity(), jsonArray2.toString(), Toast.LENGTH_SHORT).show();
//                        Log.i("rdjeijfrie",jsonArray2.toString());


                        for (int j=0;j<jsonArray2.length();j++) {
                            JSONObject jsonObject2 = jsonArray2.getJSONObject(j);

                            String img;
                            String category_id;
                            String catalog_id;

                            img = jsonObject2.getString("image");
                            category_id = jsonObject2.getString("category_id");
                            catalog_id = jsonObject2.getString("catalog_id");





                            if (j == 0){
                                viewmodel.setImg1(img);
                                viewmodel.setCategory_id(category_id);
                                viewmodel.setCatalog_id(catalog_id);
                            }

                            if (j == 1){
                                viewmodel.setImd2(img);
                                viewmodel.setCategory_id(category_id);
                                viewmodel.setCatalog_id(catalog_id);
                            }
                            if (j == 2){
                                viewmodel.setImd3(img);
                                viewmodel.setCategory_id(category_id);
                                viewmodel.setCatalog_id(catalog_id);
                            }
                            if (j == 3){
                                viewmodel.setImd4(img);
                                viewmodel.setCategory_id(category_id);
                                viewmodel.setCatalog_id(catalog_id);
                            }


                        }

                        galleryModelList.add(viewmodel);
                   }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter =  new Gallery_Adapter(getActivity(),galleryModelList);
                recyclerView.setAdapter(adapter);



            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();

                params.put("Accept","Application/json");
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);

    }


}