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


    List<Gallery_One_Model> galleryOneModelList;
    Gallery_One_Adapter adapter1;

    List<Gallery_two_Model> galleryTwoModelList;
    Gallery_two_Adapter adapter2;

    RecyclerView recyclerView,recyclerView1,recyclerView2;
    String data;
    String id,cat_id,catalog_id,gallery_id,img;
    JSONArray jsonArray,jsonArray1;
    TextView txt,txt1,txt2,view1;
    String text;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_gallery_, container, false);


        recyclerView = root.findViewById(R.id.gallery_two_screen);
//        recyclerView1 = root.findViewById(R.id.gallery_three_screen);

//        txt = root.findViewById(R.id.category_one_txt);
//        txt1 = root.findViewById(R.id.view_one_txt);

//        txt2 = root.findViewById(R.id.cat_two);
//        view1 = root.findViewById(R.id.view_two_txt);

//        txt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),View_All_Cat.class);
//                intent.putExtra("catalog_id", catalog_id);
//                intent.putExtra("gallery_id","dfg");
//                startActivity(intent);
//            }
//        });
//
//       view1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),View_All_Cat.class);
//                intent.putExtra("catalog_id", catalog_id);
//                intent.putExtra("gallery_id","photos");
//                startActivity(intent);
//            }
//        });

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


                    JSONObject jsonObject = response.getJSONObject("data");
                    jsonArray = jsonObject.getJSONArray("gvkdfgj");
//                    jsonArray1 = jsonObject.getJSONArray("photos");

//
//                    for (int i=0;i< jsonArray.length();i++){
//
//                        JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
//
//                        id = jsonObject1.getString("id");
//                        cat_id = jsonObject1.getString("category_id");
//                        catalog_id = jsonObject1.getString("catalog_id");
//                        gallery_id = jsonObject1.getString("gallery_category");
//                        img = jsonObject1.getString("image");
//
//                        Log.i("lwgnjhiorwhgt",jsonArray.toString());
//
//
//
//
//                        galleryModelList = new ArrayList<>();
//
//                        Gallery_Model viewmodel = new Gallery_Model();
//
//
////                        viewmodel.setImg(img);
//                     viewmodel.setTxt(gallery_id);
//                     viewmodel.setTxt1("view all");
//
//
//                        galleryModelList.add(viewmodel);
//
//
//
//
//                   }
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//                    //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
////                    recyclerView.setLayoutManager(mLayoutManager);
//                    adapter =  new Gallery_Adapter(getActivity(),galleryModelList);
//                    recyclerView.setAdapter(adapter);
//
//
//
//                    galleryOneModelList = new ArrayList<>();
//
//                    Gallery_One_Model viewmodel = new Gallery_One_Model();
//
//
//                        viewmodel.setImg(img);
//
//                    galleryOneModelList.add(viewmodel);
//
//
//
//
//
//                recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
//                //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
////                    recyclerView.setLayoutManager(mLayoutManager);
//                adapter1 =  new Gallery_One_Adapter(getActivity(),galleryOneModelList);
//                recyclerView1.setAdapter(adapter1);


//                    for (int i=0;i< jsonArray1.length();i++){
//                        JSONObject jsonObject2 = jsonArray1.getJSONObject(i);
//                        id = jsonObject2.getString("id");
//                        cat_id = jsonObject2.getString("category_id");
//                        catalog_id = jsonObject2.getString("catalog_id");
//                        gallery_id = jsonObject2.getString("gallery_category");
//                        img = jsonObject2.getString("image");
//
//                        Log.i("id",id);
//                        Log.i("cat_id",cat_id);
//                        Log.i("catlog_id",catalog_id);
//                        Log.i("gall_cat",gallery_id);
//                        Log.i("img",img);
//                        Log.i("length", String.valueOf(jsonArray1.length()));
//
//
//                       galleryModelList = new ArrayList<>();
//
//                        Gallery_Model viewmodel = new Gallery_Model();
//
//
//                        viewmodel.setImg(img);
//                        txt2.setText(gallery_id);
//                        txt1.setText("View all");
//
//
//                        galleryModelList.add(viewmodel);
//
//                    }
//
//
//
//                    RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getActivity(), 2);
//                    recyclerView1.setLayoutManager(mLayoutManager1);
////                    recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
//                    adapter =  new Gallery_Adapter(getActivity(),galleryModelList);
//                    recyclerView1.setAdapter(adapter);


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

                params.put("Accept","Application/json");
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);

    }


}