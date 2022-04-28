package com.e.login.HomeClass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.BaseApi.Api;
import com.e.login.Blog_Class.BlogActivity;
import com.e.login.Blog_Class.Blog_Fragment;
import com.e.login.BloodClass.Blood_Fragment;
import com.e.login.BloodClass.Blood_One;
import com.e.login.BusTimeClass.Bus_TimeActivity;

import com.e.login.EventClass.EventActivity;
import com.e.login.GovtClass.Government_Activity;
import com.e.login.GovtClass.GovtActivity;
import com.e.login.HospitalClass.Hospital_Activity;
import com.e.login.HotelClass.Hotel_Activity;
import com.e.login.JobSearchClass.JobSearchActivity;
import com.e.login.JobsClass.Jobs;
import com.e.login.MarketCatClass.Main_Category;
import com.e.login.MarketListClass.MarketActivity;
import com.e.login.MoreClass.MoreActivity;
import com.e.login.NewsClass.NewsActivity;
import com.e.login.NewsClass.News_Fragment;
import com.e.login.Offers.OfferActivity;
import com.e.login.R;
import com.e.login.Services_Class.Services_Activity;
import com.e.login.ShopClass.ShopClassAdapter;
import com.e.login.ShopClass.ShopModel;
import com.e.login.ShopClass.ShopScreen_Class;
import com.e.login.Shopping_Activity;
import com.e.login.ShopscreenClass.Banner_Adapter;
import com.e.login.ShopscreenClass.Banner_model;
import com.e.login.ShopscreenClass.ShopsScreenFragment;
import com.e.login.SmallBusClass.SmallBusActivity;
import com.e.login.TransportClass.Transport_Activity;
import com.e.login.chatClass.Chat_Activity;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment_Home extends Fragment {

    LinearLayout bus,drawer,blog;
    ImageView bell,see_less_image,see_more_image,message;

    MyRecyclerViewAdapter adapter;
    List<Recycler_Model> recyclerModelList;
    String banner_type,banner_type_id,banner_cat_id,banner_url,banner_img,view_count;

    List<Banner_model> banner_modelList;
    Banner_Adapter adapter1;
    List<Top_rating_model> topRatingModelList;
   Top_rating_Adapter adapter6;

    List<ReviewsModel> reviewsModelList;
     ReviewsAdapter adapter7;


    String data,data1;
    int length = 0;

    RecyclerView cat_recyclerView;
    List<CategoryModel> cat;
    CategoryAdapter CAtAdapter;
    RecyclerView top,middle,middle_two,bottom;

    String cat_name = null,name = null, image = null,id = null;
    String api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View root =  inflater.inflate(R.layout.fragment_home, container, false);
//     DrawerLayout drawerLayout = root.findViewById(R.id.drawerr_layout_home);
//        drawer = root.findViewById(R.id.drawerr);
//
//        drawer.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View view) {
//
//             drawerLayout.openDrawer(GravityCompat.START);
//         }
//     });

        Api a = new Api();
        api = a.getBASE_URL();

        Log.i("qufdryuwgdr",PreferenceUtils.getToken(getActivity()));
        Intent intent = getActivity().getIntent();
        data = intent.getStringExtra("token");
        Intent i1 = getActivity().getIntent();
        data1 = i1.getStringExtra("id");

        cat_recyclerView =  root.findViewById(R.id.category);
        top = root.findViewById(R.id.top_banner);
        middle = root.findViewById(R.id.middle_banner);
        middle_two = root.findViewById(R.id.middle_two_banner);
        bottom = root.findViewById(R.id.bottom_banner);

        cat = new ArrayList<>();



          category();




     message = root.findViewById(R.id.message);

    bus = root.findViewById(R.id.busLayout);

     bell = root.findViewById(R.id.bellu);

        top_ban();
        Middle_ban();
        Middle_two_ban();
        Bottom_banner();






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




                    for (int i=0;i<20;i++){


                        JSONObject jsonObject = res.getJSONObject(i);



                        id = jsonObject.getString("id");
                        name = jsonObject.getString("name");
                        image = jsonObject.getString("logo");
                        cat_name = jsonObject.getString("category_name");
                        String url = jsonObject.getString("url");



                        CategoryModel model = new CategoryModel();

                        model.setImg(image);
                        model.setName(name);
                        model.setId(id);
                        model.setUrl(url);

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
//                CAtAdapter.setOnItemClickListener(Fragment_Home.this);
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

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);


    }
    public void top_ban(){

        String url = "http://nk.inevitabletech.email/public/api/display-banner?banner_type=TopBanner&banner_category_id=1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {




                try {
                    JSONArray res = response.getJSONArray("data");
                    banner_modelList = new ArrayList<>();

                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);

                        id = jsonObject.getString("id");
                        banner_type = jsonObject.getString("banner_type");
                        banner_type_id = jsonObject.getString("banner_type_id");
                        banner_cat_id = jsonObject.getString("banner_category_id");
                        banner_img = jsonObject.getString("image");
                        view_count = jsonObject.getString("view_count");




                        Banner_model viewmodel = new Banner_model();

                        viewmodel.setImage(banner_img);

                        banner_modelList.add(viewmodel);


                        adapter1 =  new Banner_Adapter(getActivity(),banner_modelList);
                        top.setAdapter(adapter1);
                        top.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));



                        final  int interval_time = 3000;
                        Handler handler = new Handler();
                        Runnable runnable = new Runnable() {
                            int count =0;
                            @Override
                            public void run() {
                                if(count<banner_modelList.size()){
                                    top.scrollToPosition(count++);
                                    handler.postDelayed(this,interval_time);
                                    if(count == banner_modelList.size()){
                                        count =0;
                                    }

                                }

                            }
                        };
                        handler.postDelayed(runnable,interval_time);




                    }



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

                params.put("Accept","application/json");
                params.put("Authorization", "Bearer  " +PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }


    public void Middle_ban(){

        String url = "http://nk.inevitabletech.email/public/api/display-banner?banner_type=MiddleOneBanner&banner_category_id=1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {




                try {
                    JSONArray res = response.getJSONArray("data");
                    banner_modelList = new ArrayList<>();

                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);

                        id = jsonObject.getString("id");
                        banner_type = jsonObject.getString("banner_type");
                        banner_type_id = jsonObject.getString("banner_type_id");
                        banner_cat_id = jsonObject.getString("banner_category_id");
                        banner_img = jsonObject.getString("image");
                        view_count = jsonObject.getString("view_count");




                        Banner_model viewmodel = new Banner_model();

                        viewmodel.setImage(banner_img);

                        banner_modelList.add(viewmodel);


                        adapter1 =  new Banner_Adapter(getActivity(),banner_modelList);
                        middle.setAdapter(adapter1);
                        middle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));



                        final  int interval_time = 3000;
                        Handler handler = new Handler();
                        Runnable runnable = new Runnable() {
                            int count =0;
                            @Override
                            public void run() {
                                if(count<banner_modelList.size()){
                                    middle.scrollToPosition(count++);
                                    handler.postDelayed(this,interval_time);
                                    if(count == banner_modelList.size()){
                                        count =0;
                                    }

                                }

                            }
                        };
                        handler.postDelayed(runnable,interval_time);




                    }



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

                params.put("Accept","application/json");
                params.put("Authorization", "Bearer  " +PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }

    public void Middle_two_ban(){

        String url = "http://nk.inevitabletech.email/public/api/display-banner?banner_type=MiddleTwoBanner&banner_category_id=1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {




                try {
                    JSONArray res = response.getJSONArray("data");
                    banner_modelList = new ArrayList<>();

                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);

                        id = jsonObject.getString("id");
                        banner_type = jsonObject.getString("banner_type");
                        banner_type_id = jsonObject.getString("banner_type_id");
                        banner_cat_id = jsonObject.getString("banner_category_id");
                        banner_img = jsonObject.getString("image");
                        view_count = jsonObject.getString("view_count");




                        Banner_model viewmodel = new Banner_model();

                        viewmodel.setImage(banner_img);

                        banner_modelList.add(viewmodel);


                        adapter1 =  new Banner_Adapter(getActivity(),banner_modelList);
                        middle_two.setAdapter(adapter1);
                        middle_two.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));



                        final  int interval_time = 3000;
                        Handler handler = new Handler();
                        Runnable runnable = new Runnable() {
                            int count =0;
                            @Override
                            public void run() {
                                if(count<banner_modelList.size()){
                                    middle_two.scrollToPosition(count++);
                                    handler.postDelayed(this,interval_time);
                                    if(count == banner_modelList.size()){
                                        count =0;
                                    }

                                }

                            }
                        };
                        handler.postDelayed(runnable,interval_time);




                    }



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

                params.put("Accept","application/json");
                params.put("Authorization", "Bearer  " +PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }

    public void Bottom_banner(){

        String url = "http://nk.inevitabletech.email/public/api/display-banner?banner_type=BottomBanner&banner_category_id=1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {




                try {
                    JSONArray res = response.getJSONArray("data");
                    banner_modelList = new ArrayList<>();

                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);

                        id = jsonObject.getString("id");
                        banner_type = jsonObject.getString("banner_type");
                        banner_type_id = jsonObject.getString("banner_type_id");
                        banner_cat_id = jsonObject.getString("banner_category_id");
                        banner_img = jsonObject.getString("image");
                        view_count = jsonObject.getString("view_count");




                        Banner_model viewmodel = new Banner_model();

                        viewmodel.setImage(banner_img);

                        banner_modelList.add(viewmodel);


                        adapter1 =  new Banner_Adapter(getActivity(),banner_modelList);
                        bottom.setAdapter(adapter1);
                        bottom.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));



                        final  int interval_time = 3000;
                        Handler handler = new Handler();
                        Runnable runnable = new Runnable() {
                            int count =0;
                            @Override
                            public void run() {
                                if(count<banner_modelList.size()){
                                    bottom.scrollToPosition(count++);
                                    handler.postDelayed(this,interval_time);
                                    if(count == banner_modelList.size()){
                                        count =0;
                                    }

                                }

                            }
                        };
                        handler.postDelayed(runnable,interval_time);




                    }



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

                params.put("Accept","application/json");
                params.put("Authorization", "Bearer  " +PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }

//    @Override
//    public void onItemClick(int position) {
//
//        CategoryModel model = cat.get(position);
//
//        String cat_name = model.getCat_name();
//

//             if(cat_name.equals("OfferCatalog")){
//
//            Intent intent = new Intent(getActivity(), OfferActivity.class);
//            intent.putExtra("cat",cat_name);
//            startActivity(intent);
//        }else if (cat_name.equals("NewsCatalog")){
//            Intent intent = new Intent(getActivity(), NewsActivity.class);
//            intent.putExtra("cat",cat_name);
//
//            startActivity(intent);
//        }else if (cat_name.equals("BloodCatalog")){
//            Intent intent = new Intent(getActivity(), Blood_Fragment.class);
//            startActivity(intent);
//        }else if (cat_name.equals("KarurBlogCatalog")){
//                 Intent intent = new Intent(getActivity(), BlogActivity.class);
//                 intent.putExtra("cat",cat_name);
//                 startActivity(intent);
//             }
//             else if (cat_name.equals("JobsCatalog")){
//            Intent intent = new Intent(getActivity(), Jobs.class);
//            intent.putExtra("cat",cat_name);
//
//            startActivity(intent);
//        }  else if(cat_name.equals("ShoppingCatalog")){
//                 Intent intent = new Intent(getActivity(), Shopping_Activity.class);
//                 startActivity(intent);
//
//             }
//             else if(cat_name.equals("ShopCatalog") ||cat_name.equals("ServiceCatalog") ||cat_name.equals("EducationCatalog")||cat_name.equals("TransportCatalog")
//             ||cat_name.equals("HotelCatalog")  ||cat_name.equals("HospitalCatalog") ||cat_name.equals("EventCatalog")
//                     ||cat_name.equals("MarketCatalog") ||cat_name.equals("BankCatalog") || cat_name.equals("ATMCatalog")
//                     ||cat_name.equals("BusTimeCatalog")  ||cat_name.equals("GovtNgoCatalog") ||cat_name.equals("AmbulanceCatalog") )    {
//
//                 Intent intent = new Intent(getActivity(), ShopScreen_Class.class);
//                 intent.putExtra("cat", cat_name);
//                 startActivity(intent);
//             }
//             else{
//                 Intent intent = new Intent(getActivity(), MoreActivity.class);
//                 intent.putExtra("cat", cat_name);
//                 startActivity(intent);
//             }




   // }
}