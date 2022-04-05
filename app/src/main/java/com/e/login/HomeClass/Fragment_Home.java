package com.e.login.HomeClass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.Bank.BankActivity;
import com.e.login.BaseApi.Api;
import com.e.login.Blog_Class.BlogActivity;
import com.e.login.Blog_Class.Blog_Fragment;
import com.e.login.BloodClass.Blood_Fragment;
import com.e.login.BloodClass.Blood_One;
import com.e.login.BusTimeClass.Bus_TimeActivity;
import com.e.login.EducationClass.EducationActivity;
import com.e.login.EventClass.EventActivity;
import com.e.login.GovtClass.Government_Activity;
import com.e.login.GovtClass.GovtActivity;
import com.e.login.HospitalClass.Hospital_Activity;
import com.e.login.HotelClass.Hotel_Activity;
import com.e.login.JobSearchClass.JobSearchActivity;
import com.e.login.JobsClass.Jobs;
import com.e.login.MarketCatClass.Main_Category;
import com.e.login.MarketListClass.MarketActivity;
import com.e.login.NewsClass.NewsActivity;
import com.e.login.NewsClass.News_Fragment;
import com.e.login.Offers.OfferActivity;
import com.e.login.Offers.Offer_Adapter;
import com.e.login.Offers.Offer_Model;
import com.e.login.Offers.Offer_One_Adapter;
import com.e.login.Offers.Offer_One_Model;
import com.e.login.Offers.Offer_two_Adapter;
import com.e.login.Offers.Offer_two_model;
import com.e.login.R;
import com.e.login.Services_Class.Services_Activity;
import com.e.login.ShopClass.ShopClassAdapter;
import com.e.login.ShopClass.ShopModel;
import com.e.login.ShopClass.ShopScreen_Class;
import com.e.login.Shopping_Activity;
import com.e.login.ShopscreenClass.ShopsScreenFragment;
import com.e.login.SmallBusClass.SmallBusActivity;
import com.e.login.TransportClass.Transport_Activity;
import com.e.login.chatClass.Chat_Activity;
import com.e.login.utils.PreferenceUtils;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;


public class Fragment_Home extends Fragment{

    LinearLayout bus;
    ImageView bell,message;

    MyRecyclerViewAdapter adapter;
    List<Recycler_Model> recyclerModelList;

    Slider_Top_Adapter slider_top_adapter;
    List<BannerModel> bannerModelList;


    List<Top_rating_model> topRatingModelList;
   Top_rating_Adapter adapter6;

    List<ReviewsModel> reviewsModelList;
     ReviewsAdapter adapter7;

    int[] images = {R.drawable.first_one,
            R.drawable.banner,
            R.drawable.bank_banner,
            R.drawable.banner,
            R.drawable.first_one,
         };

    String data,data1;


    RecyclerView cat_recyclerView,top_recycler,middle_one,middle_two,bottom;
    List<CategoryModel> cat;
    CategoryAdapter CAtAdapter;

    String cat_name = null,name = null, image = null,id = null;
    String api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View root =  inflater.inflate(R.layout.fragment_home, container, false);


        Api a = new Api();
        api = a.getBASE_URL();


        Intent intent = getActivity().getIntent();
        data = intent.getStringExtra("token");
        Intent i1 = getActivity().getIntent();
        data1 = i1.getStringExtra("id");

        cat_recyclerView =  root.findViewById(R.id.category);
        top_recycler = root.findViewById(R.id.top_banner);
        middle_one = root.findViewById(R.id.middle_one);
        middle_two = root.findViewById(R.id.middle_two);
        bottom = root.findViewById(R.id.bottom_recycle);

        cat = new ArrayList<>();

        category();

        top_ban();
        Middle_one();
        Middle_two();
        Bottom();








     message = root.findViewById(R.id.message);

    bus = root.findViewById(R.id.busLayout);

     bell = root.findViewById(R.id.bellu);







     recyclerModelList = new ArrayList<>();

        RecyclerView recycler = root.findViewById(R.id.top_rated_recycle);


        for (int i = 0; i < 5; i++) {

           Recycler_Model viewmodel = new Recycler_Model();



       viewmodel.setImg("1");
       viewmodel.setImg1("2");
       viewmodel.setTxt("Best Mobiles");
       viewmodel.setTxt1("4.6");

         recyclerModelList.add(viewmodel);

        }

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter =  new MyRecyclerViewAdapter(getContext(),recyclerModelList);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));







       topRatingModelList = new ArrayList<>();

        RecyclerView recyclerView6 = root.findViewById(R.id.top_recycle);


        for (int i = 0; i < 5; i++) {

            Top_rating_model viewmodel = new Top_rating_model();



            viewmodel.setImg("1");
            viewmodel.setImg1("2");
            viewmodel.setText("Lorem ispum may be used as a placeholder before the final");
            viewmodel.setText1("4.6");


            topRatingModelList.add(viewmodel);

        }

        recyclerView6.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter6 =  new Top_rating_Adapter(getContext(),topRatingModelList);
        recyclerView6.setAdapter(adapter6);
        recyclerView6.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));




        reviewsModelList = new ArrayList<>();

        RecyclerView recyclerView7 = root.findViewById(R.id.reviews_recycle);


        for (int i = 0; i < 5; i++) {

            ReviewsModel viewmodel = new ReviewsModel();



        viewmodel.setImg("1");
        viewmodel.setTxt("D");
        viewmodel.setTxt1("Deepika");
        viewmodel.setTxt2("Lorem ispum may be used as a placeholder before the final");
        viewmodel.setTxt3("4.6");


        reviewsModelList.add(viewmodel);

        }

        recyclerView7.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter7 =  new ReviewsAdapter(getContext(),reviewsModelList);
        recyclerView7.setAdapter(adapter7);
        recyclerView7.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));







        return  root;
    }

    private void category() {




        String url = api+"get-all-Main-Menu";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



                try {
                    JSONArray res = response.getJSONArray("data");




                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);



                        id = jsonObject.getString("id");
                        name = jsonObject.getString("name");
                        image = jsonObject.getString("logo");
                        cat_name = jsonObject.getString("category_name");






                        CategoryModel model = new CategoryModel();

                        model.setImg(image);
                        model.setName(name);
                        model.setId(id);

                        model.setCat_name(cat_name);


                        cat.add(model);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),4){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };



                cat_recyclerView.setLayoutManager(layoutManager);
                CAtAdapter =  new CategoryAdapter(getActivity(),cat);
                cat_recyclerView.setAdapter(CAtAdapter);







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

                params.put("Accept","application/json");
                params.put("Authorization", "Bearer "+PreferenceUtils.getToken(getActivity()));

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);


    }
    public void top_ban(){
        String url = "http://nk.inevitabletech.email/public/api/display-banner?banner_type=TopBanner&banner_category_id="+data1;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    bannerModelList = new ArrayList<>();

                 for(int i=0;i< jsonArray.length();i++){
                   JSONObject jsonObject = jsonArray.getJSONObject(i);
                   String id = jsonObject.getString("id");
                   String banner_type_id = jsonObject.getString("banner_type_id");
                     String banner_type = jsonObject.getString("banner_type");
                     String banner_category_id = jsonObject.getString("banner_category_id");
                     String banner_url = jsonObject.getString("banner_url");
                     String order_no = jsonObject.getString("order_no");

                   String img = jsonObject.getString("image");
                   String v_count = jsonObject.getString("view_count");




                   BannerModel bannerModel = new BannerModel();
                   bannerModel.setImg(img);

                   bannerModelList.add(bannerModel);

                 }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                top_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                slider_top_adapter =  new Slider_Top_Adapter(getActivity(),bannerModelList);
                top_recycler.setAdapter(slider_top_adapter);
                top_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


                final int interval = 3000;
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    int count =0;
                    @Override
                    public void run() {

                        if(count< bannerModelList.size()){
                            top_recycler.scrollToPosition(count++);
                            handler.postDelayed(this,interval);

                        }
                        if(count== bannerModelList.size()){

                            count = 0;

                        }
                    }
                };
                handler.postDelayed(runnable,interval);

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

    public void Middle_one(){
        String url = "http://nk.inevitabletech.email/public/api/display-banner?banner_type=MiddleOneBanner&banner_category_id="+data1;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    bannerModelList = new ArrayList<>();

                    for(int i=0;i< jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String banner_type_id = jsonObject.getString("banner_type_id");
                        String banner_type = jsonObject.getString("banner_type");
                        String banner_category_id = jsonObject.getString("banner_category_id");
                        String banner_url = jsonObject.getString("banner_url");
                        String order_no = jsonObject.getString("order_no");

                        String img = jsonObject.getString("image");
                        String v_count = jsonObject.getString("view_count");




                        BannerModel bannerModel = new BannerModel();
                        bannerModel.setImg(img);

                        bannerModelList.add(bannerModel);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                middle_one.setLayoutManager(new LinearLayoutManager(getActivity()));
                slider_top_adapter =  new Slider_Top_Adapter(getActivity(),bannerModelList);
                middle_one.setAdapter(slider_top_adapter);
                middle_one.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


                final int interval = 3000;
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    int count =0;
                    @Override
                    public void run() {

                        if(count< bannerModelList.size()){
                            middle_one.scrollToPosition(count++);
                            handler.postDelayed(this,interval);

                        }
                        if(count== bannerModelList.size()){

                            count = 0;

                        }
                    }
                };
                handler.postDelayed(runnable,interval);


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

                params.put("Accept","application/json");
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);


    }

    public void Middle_two(){
        String url = "http://nk.inevitabletech.email/public/api/display-banner?banner_type=MiddleTwoBanner&banner_category_id="+data1;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    bannerModelList = new ArrayList<>();

                    for(int i=0;i< jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String banner_type_id = jsonObject.getString("banner_type_id");
                        String banner_type = jsonObject.getString("banner_type");
                        String banner_category_id = jsonObject.getString("banner_category_id");
                        String banner_url = jsonObject.getString("banner_url");
                        String order_no = jsonObject.getString("order_no");

                        String img = jsonObject.getString("image");
                        String v_count = jsonObject.getString("view_count");




                        BannerModel bannerModel = new BannerModel();
                        bannerModel.setImg(img);

                        bannerModelList.add(bannerModel);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                middle_two.setLayoutManager(new LinearLayoutManager(getActivity()));
                slider_top_adapter =  new Slider_Top_Adapter(getActivity(),bannerModelList);
                middle_two.setAdapter(slider_top_adapter);
                middle_two.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


                final int interval = 3000;
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    int count =0;
                    @Override
                    public void run() {

                        if(count< bannerModelList.size()){
                            middle_two.scrollToPosition(count++);
                            handler.postDelayed(this,interval);

                        }
                        if(count== bannerModelList.size()){

                            count = 0;

                        }
                    }
                };
                handler.postDelayed(runnable,interval);


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

                params.put("Accept","application/json");
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);


    }

    public void Bottom(){
        String url = "http://nk.inevitabletech.email/public/api/display-banner?banner_type=BottomBanner&banner_category_id="+data1;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    bannerModelList = new ArrayList<>();

                    for(int i=0;i< jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String banner_type_id = jsonObject.getString("banner_type_id");
                        String banner_type = jsonObject.getString("banner_type");
                        String banner_category_id = jsonObject.getString("banner_category_id");
                        String banner_url = jsonObject.getString("banner_url");
                        String order_no = jsonObject.getString("order_no");

                        String img = jsonObject.getString("image");
                        String v_count = jsonObject.getString("view_count");




                        BannerModel bannerModel = new BannerModel();
                        bannerModel.setImg(img);

                        bannerModelList.add(bannerModel);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                bottom.setLayoutManager(new LinearLayoutManager(getActivity()));
                slider_top_adapter =  new Slider_Top_Adapter(getActivity(),bannerModelList);
                bottom.setAdapter(slider_top_adapter);
                bottom.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


                final int interval = 3000;
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    int count =0;
                    @Override
                    public void run() {

                        if(count< bannerModelList.size()){
                            bottom.scrollToPosition(count++);
                            handler.postDelayed(this,interval);

                        }
                        if(count== bannerModelList.size()){

                            count = 0;

                        }
                    }
                };
                handler.postDelayed(runnable,interval);


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

                params.put("Accept","application/json");
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);


    }
}