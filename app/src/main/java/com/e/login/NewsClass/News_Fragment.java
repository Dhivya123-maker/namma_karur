package com.e.login.NewsClass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.HomeClass.BannerModel;
import com.e.login.HomeClass.Slider_Top_Adapter;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class News_Fragment extends Fragment implements NewsoneAdapter.OnItemClickListener {
    Recycler_news_Adapter adapter;
    MyRecyclerViewAdapter_two adapter1;
    List<News_Breaking_Model> newsBreakingModelList;
    List<NewsModel> newsModelList;
    SliderView sliderView;
    RecyclerView recyclerView;

    List<NewsOneModel> newsOneModelList;
    NewsoneAdapter adapter2;

    JSONArray latest;
    JSONArray breaking;
    JSONArray all ;
    String api,data;
    TextView view,view_latest,view_breaking,late;
    String id,img,title,view_count = null;
    List<BannerModel> bannerModelList;
    Slider_Top_Adapter slider_top_adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View root = inflater.inflate(R.layout.fragment_news_, container, false);


        late = root.findViewById(R.id.late);
        recyclerView = root.findViewById(R.id.top_banneer);
        Intent intent = getActivity().getIntent();
        data = intent.getStringExtra("id");

        Ban();


        view_breaking = root.findViewById(R.id.breaking_vview);
        view_breaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(),View_Breaking.class);
                intent1.putExtra("cat1","breaking_news");
                startActivity(intent1);
            }
        });


        view_latest = root.findViewById(R.id.latest_view);
        view_latest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(),View_Breaking.class);
                intent1.putExtra("cat1","Latest_news");
                startActivity(intent1);
            }
        });





        RecyclerView recyclerView= root.findViewById(R.id.rv);
        RecyclerView recyclerView1 = root.findViewById(R.id.rv1);
        RecyclerView recyclerView2 =root.findViewById(R.id.news_screen);



        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-all-news-home";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



                try {




                    String Success = response.getString("success");
                    String msg = response.getString("message");



                    newsModelList = new ArrayList<>();
                    newsBreakingModelList = new ArrayList<>();
                    newsOneModelList = new ArrayList<>();


                    JSONObject jsonObject = response.getJSONObject("data");
                    latest = jsonObject.getJSONArray("latest");
                    breaking = jsonObject.getJSONArray("breaking");
                    all = jsonObject.getJSONArray("all");


                        for (int i = 0; i < latest.length(); i++) {
                            JSONObject jsonObject1 = latest.getJSONObject((i));

                            id = jsonObject1.getString("id");
                            img = jsonObject1.getString("image");
                            title = jsonObject1.getString("title");


                            NewsModel viewmodel = new NewsModel();


                            viewmodel.setImage(img);
                            viewmodel.setTxt(title);
                            viewmodel.setId(id);


                            newsModelList.add(viewmodel);

                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


                        adapter =  new Recycler_news_Adapter(getContext(),newsModelList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                       // adapter.setOnItemClickListener(getActivity());








                        for (int i = 0; i < breaking.length(); i++) {
                            JSONObject break_news = breaking.getJSONObject((i));

                            id = break_news.getString("id");
                            img = break_news.getString("image");
                            title = break_news.getString("title");

                            News_Breaking_Model viewmodel = new News_Breaking_Model();


                            viewmodel.setImage(img);
                            viewmodel.setTxt(title);
                            viewmodel.setId(id);







                            newsBreakingModelList.add(viewmodel);

                        }

                        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));


                        adapter1 =  new MyRecyclerViewAdapter_two(getContext(),newsBreakingModelList );
                        recyclerView1.setAdapter(adapter1);
                        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));



                        for (int i = 0; i < all.length(); i++) {

                            JSONObject all_news = all.getJSONObject((i));

                            id = all_news.getString("id");
                            img = all_news.getString("image");
                            title = all_news.getString("title");
                           view_count = all_news.getString("view_count");

                            NewsOneModel viewmodel = new NewsOneModel();



                            viewmodel.setImage(img);

//                            viewmodel.setImage("1");
//                            viewmodel.setImage1("2");
                            viewmodel.setText(title);
                          viewmodel.setText_one(view_count);
                            viewmodel.setButton("View");
                            viewmodel.setId(id);





                            newsOneModelList.add(viewmodel);

                        }

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };

                    recyclerView2.setLayoutManager(linearLayoutManager);

                        adapter2 =  new NewsoneAdapter(getContext(),newsOneModelList);
                        recyclerView2.setAdapter(adapter2);


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
//
//        for (int i = 0; i < 2; i++) {
//
//            NewsOneModel viewmodel = new NewsOneModel();
//
//
//
//            viewmodel.setImage("1");
//            viewmodel.setImage1("2");
//            viewmodel.setText("Lorem ispum may be used as a holder\nbefore the final copy is available");
//            viewmodel.setText_one("186");
//            viewmodel.setButton("View");
//
//
//
//
//            newsOneModelList.add(viewmodel);
//
//        }
//
//        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        adapter2 =  new NewsoneAdapter(getContext(),newsOneModelList);
//        recyclerView2.setAdapter(adapter2);

//
//
//        for (int i = 0; i < 4; i++) {
//
//            NewsModel viewmodel = new NewsModel();
//
//
//
//            viewmodel.setImage("1");
//
//            viewmodel.setBtn("Lorem ispum may be used as a holder");
//
//
//
//
//
//            newsModelList.add(viewmodel);
//
//        }
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//
//        adapter =  new Recycler_news_Adapter(getContext(),newsModelList);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//
//

//


        return  root;
    }

//    public  void all_view(){
//
//        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-all-news?"+data;
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
//            @SuppressLint("CheckResult")
//            @Override
//            public void onResponse(JSONObject response) {
//
//
//                Log.i("1111",response.toString());
//                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
//
//                try {
//
//
//
//
//                    String Success = response.getString("success");
//                    String msg = response.getString("message");
//
//
//
//                    newsModelList = new ArrayList<>();
//                    newsOneModelList = new ArrayList<>();
//
//
//
//                    if(Success.equals("true")){
//                        Log.i("123",msg);
//                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
//
//
//
//
//
//
//
//
//
//                    }else{
//                        Log.i("1234",msg);
//                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
//                    }
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//
//
//                }
//
//
//            }
//
////
////
////        }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
//
//
//
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                // params.put("Accept", "application/json");
//                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));
//                return params;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        requestQueue.add(jsonObjectRequest);
//
//
//    }


    public void Ban(){

        String url = "http://nk.inevitabletech.email/public/api/display-banner?banner_type=JobBanner&banner_category_id="+data;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {

                try {
                    bannerModelList = new ArrayList<>();
                    JSONArray jsonArray = response.getJSONArray("data");


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

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                slider_top_adapter =  new Slider_Top_Adapter(getActivity(),bannerModelList);
                recyclerView.setAdapter(slider_top_adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


                final int interval = 3000;
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    int count =0;
                    @Override
                    public void run() {

                        if(count< bannerModelList.size()){
                            recyclerView.scrollToPosition(count++);
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
                params.put("Authorization", "Bearer "+PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);


    }
    @Override
    public void onItemClick(int position) {
//        Intent intent1 = new Intent(getActivity(),All_news.class);
//        intent1.putExtra("cat1","All_news");
//        startActivity(intent1);

    }

//public  void v_view(){
//    String JSON_URL = "http://nk.inevitabletech.email/public/api/get-all-details?"+data;
//
//    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
//        @SuppressLint("CheckResult")
//        @Override
//        public void onResponse(JSONObject response) {
//
//
//            Log.i("1111y3tgrdiw",response.toString());
//            Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
//
//            try {
//
//
//
//
//                String Success = response.getString("success");
//                String msg = response.getString("message");
//
//
//
//                newsModelList = new ArrayList<>();
//                newsOneModelList = new ArrayList<>();
//
//
//
//                if(Success.equals("true")){
//                    Log.i("123",msg);
//                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
//
//
//
//
//
//
//
//
//
//                }else{
//                    Log.i("1234",msg);
//                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//
//
//            }
//
//
//        }
//
////
////
////        }
//    }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//
//        }
//    }){
//        @Override
//        protected Map<String,String> getParams(){
//            Map<String,String> params = new HashMap<String, String>();
//
//
//
//            return params;
//        }
//
//        @Override
//        public Map<String, String> getHeaders() throws AuthFailureError {
//            Map<String,String> params = new HashMap<String, String>();
//            // params.put("Accept", "application/json");
//            params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));
//            return params;
//        }
//    };
//
//    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//    requestQueue.add(jsonObjectRequest);
//
//
//}
}