package com.e.login.NewsClass;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.BaseApi.Api;
import com.e.login.BusTimeClass.Bus_TimeActivity;
import com.e.login.Mall.Slider_mall_Adapter;
import com.e.login.R;
import com.e.login.ShopClass.ShopClassAdapter;
import com.e.login.ShopClass.ShopScreen_Class;
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


public class News_Fragment extends Fragment implements NewsoneAdapter.OnItemClickListener {
    Recycler_news_Adapter adapter;
    MyRecyclerViewAdapter_two adapter1;
    List<News_Breaking_Model> newsBreakingModelList;
    List<NewsModel> newsModelList;
    SliderView sliderView;

    List<NewsOneModel> newsOneModelList;
    NewsoneAdapter adapter2;
    int[] images = {R.drawable.first_one,
            R.drawable.banner,
            R.drawable.bank_banner,
            R.drawable.banner,
            R.drawable.first_one,
    };
    JSONArray latest;
    JSONArray breaking;
    JSONArray all ;
    String api,data= null;
    TextView view,view_latest,view_breaking,late;
    String id,img,title,view_count = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View root = inflater.inflate(R.layout.fragment_news_, container, false);


//        Api a = new Api();
//        api = a.getBASE_URL();
        late = root.findViewById(R.id.late);

        view_breaking = root.findViewById(R.id.breaking_vview);
        view_breaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(),View_Breaking.class);
                intent1.putExtra("cat1","breaking_news");
                startActivity(intent1);
            }
        });
//

        view_latest = root.findViewById(R.id.latest_view);
        view_latest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(),View_Breaking.class);
                intent1.putExtra("cat1","Latest_news");
                startActivity(intent1);
            }
        });

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1 = new Intent(getActivity(),View_Breaking.class);
//                intent1.putExtra("cat1","All_news");
//                startActivity(intent1);

//
////                all_view();
//            }
//        });

        sliderView = root.findViewById(R.id.slide_slide);
        Slider_mall_Adapter sliderAdapter = new Slider_mall_Adapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


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

//                    Toast.makeText(getActivity(), all.toString(), Toast.LENGTH_SHORT).show();
//                    Log.i("uwgye9iuhrf9yui",all.toString());

//                    breaking = jsonObject.getString("breaking");
//                    all = jsonObject.getString("all");

//
//                    if(Success.equals("true")){
//                        Log.i("123",msg);
//                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();


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

//

//
//                    }else{
//                        Log.i("1234",msg);
//                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
//                    }


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