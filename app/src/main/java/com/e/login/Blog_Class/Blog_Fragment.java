package com.e.login.Blog_Class;

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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.FoodClass.FoodAdapter;
import com.e.login.FoodClass.FoodModel;
import com.e.login.JobsClass.Jobs;
import com.e.login.JobsClass.Jobs_Adapter;
import com.e.login.JobsClass.Jobs_Model;
import com.e.login.JobsClass.Jobs_two_Adapter;
import com.e.login.JobsClass.Jobs_two_Model;
import com.e.login.R;
import com.e.login.ShopscreenClass.Slidershop_Top_Adapter;
import com.e.login.utils.PreferenceUtils;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Blog_Fragment extends Fragment {

    SliderView sliderView;
    int[] images = {R.drawable.first_one,
            R.drawable.banner,
            R.drawable.bank_banner,
            R.drawable.banner,
            R.drawable.first_one,
    };
    List<BlogModel> blogModelList;
    BlogAdapter adapter;

    List<Blog_One_Model> blogOneModelList;
    Blog_One_Adapter adapter1;


    List<Blog_two_Model> blogTwoModelList;
    Blog_two_Adapter adapter2;

    List<Blog_three_Model> blog_three_modelList;
    Blog_three_Adapter adapter3;
    RecyclerView recyclerView,recyclerView1,recyclerView2;
    String id,image,desc;
    String data;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_blog_, container, false);

        sliderView = root. findViewById(R.id.slider_blog);
        Slidershop_Top_Adapter sliderAdapter = new Slidershop_Top_Adapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


        blogModelList = new ArrayList<>();
        blogOneModelList = new ArrayList<>();

      recyclerView = root.findViewById(R.id.blog_recycler);
      recyclerView1 = root.findViewById(R.id.blog1_recycler);
      recyclerView2 = root.findViewById(R.id.blog2_recycler);

        Intent intent = getActivity().getIntent();
        data = intent.getStringExtra("cat");


        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-blog-home-page";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {
                Log.i("hgtviuy",response.toString());



                try {



                    JSONObject jsonObject = response.getJSONObject("data");
                    JSONArray jsonArray = jsonObject.getJSONArray("trending");
                    JSONArray jsonArray1 = jsonObject.getJSONArray("latest");
                    JSONArray jsonArray2 = jsonObject.getJSONArray("popular");




                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject((i));
                        id = jsonObject1.getString("id");
                        image = jsonObject1.getString("image");
                        desc = jsonObject1.getString("description");


                        BlogModel viewmodel = new BlogModel();

                        viewmodel.setImage(image);

//                        viewmodel.setText_one("Read more");
                        viewmodel.setText(desc);
                        viewmodel.setId(id);

                        blogModelList.add(viewmodel);


                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                    adapter =  new BlogAdapter(getContext(),blogModelList);
                    recyclerView.setAdapter(adapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    recyclerView.setLayoutManager(linearLayoutManager);





                    for (int i = 0; i < jsonArray1.length(); i++) {
                        JSONObject jsonObject2 = jsonArray1.getJSONObject((i));
                        id = jsonObject2.getString("id");
                        image = jsonObject2.getString("image");
                        desc = jsonObject2.getString("description");

                        Log.i("41oijuyhtf98",jsonArray1.toString());


                        Blog_One_Model viewmodel = new Blog_One_Model();

                        viewmodel.setImg(image);

                        viewmodel.setTxt1("Read more");
                        viewmodel.setTxt(desc);
                        viewmodel.setId(id);

                        blogOneModelList.add(viewmodel);


                    }
                    recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));

                    adapter1 =  new Blog_One_Adapter(getContext(),blogOneModelList);
                    recyclerView1.setAdapter(adapter1);
                    recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));



                    for (int i = 0; i < jsonArray2.length(); i++) {
                        JSONObject jsonObject3 = jsonArray2.getJSONObject((i));
                        id = jsonObject3.getString("id");
                        image = jsonObject3.getString("image");
                        desc = jsonObject3.getString("description");

                        Log.i("41oijuyhtf98",jsonArray2.toString());
                        Blog_One_Model viewmodel = new Blog_One_Model();

                        viewmodel.setImg(image);

                        viewmodel.setTxt1("Read more");
                        viewmodel.setTxt(desc);
                        viewmodel.setId(id);

                        blogOneModelList.add(viewmodel);


                    }
                    recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));

                    adapter1 =  new Blog_One_Adapter(getContext(),blogOneModelList);
                    recyclerView2.setAdapter(adapter1);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));






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
                params.put("Accept", "application/json");
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);






//
//        for (int i = 0; i < 2; i++) {
//
//            BlogModel viewmodel = new BlogModel();
//
//
//
//            viewmodel.setImage("1");
//
//            viewmodel.setText_one("Read more");
//            viewmodel.setText("Lorem ispum may be used as a holder\nbefore the final copy is available");
//
//
//
//            blogModelList.add(viewmodel);
//
//        }
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        adapter =  new BlogAdapter(getContext(),blogModelList);
//        recyclerView.setAdapter(adapter);

//
//
//

//
//        RecyclerView recyclerView1 = root.findViewById(R.id.blog1_recycler);
//
//
//        for (int i = 0; i < 4; i++) {
//
//            Blog_One_Model viewmodel = new Blog_One_Model();
//
//
//
//            viewmodel.setImg("1");
//
//            viewmodel.setTxt1("Read more");
//            viewmodel.setTxt("Lorem ispum may be used as a placeholder");
//
//
//
//            blogOneModelList.add(viewmodel);
//
//        }
//
//        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        adapter1 =  new Blog_One_Adapter(getContext(),blogOneModelList);
//        recyclerView1.setAdapter(adapter1);
//        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//
//
//
//
//        blogTwoModelList = new ArrayList<>();
//
//        RecyclerView recyclerView2 = root.findViewById(R.id.blog2_recycler);
//
//
//        for (int i = 0; i < 4; i++) {
//
//            Blog_two_Model viewmodel = new Blog_two_Model();
//
//
//
//            viewmodel.setImg("1");
//
//            viewmodel.setTxt1("Read more");
//            viewmodel.setTxt("Lorem ispum may be used as a placeholder");
//
//
//
//            blogTwoModelList.add(viewmodel);
//
//        }
//
//        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        adapter2 =  new Blog_two_Adapter(getContext(),blogTwoModelList);
//        recyclerView2.setAdapter(adapter2);
//        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));





        return  root;
    }
}