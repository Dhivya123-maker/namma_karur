package com.e.login.HomeClass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


public class Fragment_Home extends Fragment implements CategoryAdapter.OnItemClickListener{

    LinearLayout hospital,shop,trans,edu,services,more,shops_icon,bus,jobs,govt,ambulance,news,market,bank,fun,see_more,see_less,extra,extra_one,blood,mall,events,offer,hotels,ngo,drawer,blog;
    ImageView bell,see_less_image,see_more_image,message;

    MyRecyclerViewAdapter adapter;
    List<Recycler_Model> recyclerModelList;

    SliderView sliderView, sliderView1;


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
    int length = 0;

    RecyclerView cat_recyclerView;
    List<CategoryModel> cat;
    CategoryAdapter CAtAdapter;

    String cat_name = null,name = null, image = null,id = null;
    String api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View root =  inflater.inflate(R.layout.fragment_home, container, false);
     DrawerLayout drawerLayout = root.findViewById(R.id.drawer_layout);

        Api a = new Api();
        api = a.getBASE_URL();

        Intent intent = getActivity().getIntent();
        data = intent.getStringExtra("token");
        Intent i1 = getActivity().getIntent();
        data1 = i1.getStringExtra("id");

        cat_recyclerView =  root.findViewById(R.id.category);

        cat = new ArrayList<>();
//
//        Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getContext(), data1, Toast.LENGTH_SHORT).show();
//
//




        sliderView = root.findViewById(R.id.slider_top);
        sliderView1 = root.findViewById(R.id.slider_bottom1);

        Slider_Top_Adapter sliderAdapter = new Slider_Top_Adapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


        Slider_Bottom_Adapter sliderAdapter1 = new Slider_Bottom_Adapter(images);

        sliderView1.setSliderAdapter(sliderAdapter1);
        sliderView1.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView1.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView1.startAutoCycle();


//

       drawer = root.findViewById(R.id.drawerr);


//        extra = root.findViewById(R.id.layoutRow4);
//     extra_one = root.findViewById(R.id.layoutRow5);
//     see_more = root.findViewById(R.id.see_more_layout);
//     see_more_image = root.findViewById(R.id.see_more_image);
//     see_more_txt = root.findViewById(R.id.see_more_text);
//
//     see_less = root.findViewById(R.id.see_less_layout);
//     see_less_image = root.findViewById(R.id.see_less_image);
//     see_less_txt =  root.findViewById(R.id.see_less_text);
     message = root.findViewById(R.id.message);

    bus = root.findViewById(R.id.busLayout);
//    jobs = root.findViewById(R.id.JobLayout);
//     ambulance = root.findViewById(R.id.AmbulanceLayout);
//     news =  root.findViewById(R.id.NewsLayout);
//     market = root.findViewById(R.id.MarketLayout);
     bell = root.findViewById(R.id.bellu);
//    blood = root.findViewById(R.id.blood_lnr);
//     //fun = root.findViewById(R.id.FunLayout);
//     govt = root.findViewById(R.id.govt_lnr);
//     bank = root.findViewById(R.id.BankLayout);
//    // mall = root.findViewById(R.id.mall_lnr);
//     events = root.findViewById(R.id.events_lnr);
//     offer = root.findViewById(R.id.offer_lnr);
//     blog = root.findViewById(R.id.Blog_Layout);
//     more = root.findViewById(R.id.MoreLayout);
//     services = root.findViewById(R.id.ServicesLayout);
//     edu = root.findViewById(R.id.eduLayout);
//     trans = root.findViewById(R.id.TransLayout);
//     shop = root.findViewById(R.id.ShoppingLayout);
//     hospital = root.findViewById(R.id.HospitalLayout);
//     hotels = root.findViewById(R.id.hotel_icons);
//     ngo = root.findViewById(R.id.ngo_lr);




//   extra.setVisibility(View.GONE);
//   extra_one.setVisibility(View.GONE);
//  see_less.setVisibility(View.GONE);
//  see_more.setVisibility(View.VISIBLE);





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








//        see_more.setOnClickListener(new View.OnClickListener() {
//       @Override
//       public void onClick(View view) {
//
//           extra.setVisibility(View.VISIBLE);
//           extra_one.setVisibility(View.VISIBLE);
//           see_less.setVisibility(View.VISIBLE);
//           see_less_txt.setVisibility(View.VISIBLE);
//           see_less_image.setVisibility(View.VISIBLE);
//           see_more_txt.setVisibility(View.GONE);
//           see_more_image.setVisibility(View.GONE);
//
//
//
//
//
//       }
//   });
//
//        see_less.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//                extra.setVisibility(View.GONE);
//                extra_one.setVisibility(View.GONE);
//                see_more_txt.setVisibility(View.VISIBLE);
//                see_more_image.setVisibility(View.VISIBLE);
//                see_less_txt.setVisibility(View.GONE);
//                see_less_image.setVisibility(View.GONE);
//
//
//
//
//            }
//        });



//        RecyclerView recyclerView =root. findViewById(R.id.rvAnimals);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//
//        adapter =  new MyRecyclerViewAdapter(getActivity(),home_model );
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
////
//        RecyclerView recyclerView1 =root. findViewById(R.id.rvAnimals1);
//        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//
//        adapter =  new MyRecyclerViewAdapter(getActivity(),home_model );
//        recyclerView1.setAdapter(adapter);
//        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//

//        RecyclerView recyclerView2 =root. findViewById(R.id.rvAnimals2);
//        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));


//        recyclerView2.setAdapter(adapterOne);
//        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//


//
//        shops_icon = root.findViewById(R.id.shopsIcons_home);
//        shops_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent shopsIntent = new Intent(getActivity(), ShopScreen_Class.class);
//                shopsIntent.putExtra("cat","ShopCatalog");
//                startActivity(shopsIntent);
//
//            }
//        });
//
//     bus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shopsIntent = new Intent(getActivity(), ShopScreen_Class.class);
//                shopsIntent.putExtra("cat","BusTimeCatalog");
//                startActivity(shopsIntent);
//            }
//        });
//        jobs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shopsIntent = new Intent(getActivity(), Jobs.class);
//                startActivity(shopsIntent);
//            }
//        });
//
//
//
//
//      govt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shopsIntent = new Intent(getActivity(), ShopScreen_Class.class);
//                shopsIntent.putExtra("cat","NgoCatalog");
//                startActivity(shopsIntent);
//            }
//        });
//
//        ambulance.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shopsIntent = new Intent(getActivity(), ShopScreen_Class.class);
//                shopsIntent.putExtra("cat","AmbulanceCatalog");
//                startActivity(shopsIntent);
//            }
//        });
//
//       news.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shopsIntent = new Intent(getActivity(), NewsActivity.class);
//                shopsIntent.putExtra("cat","NewsCatalog");
//                startActivity(shopsIntent);
//            }
//        });
//
//       market.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shopsIntent = new Intent(getActivity(), ShopScreen_Class.class);
//                shopsIntent.putExtra("cat","MarketCatalog");
//                startActivity(shopsIntent);
//            }
//        });
//

//
//       blood.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent shopsIntent = new Intent(getActivity(), Blood_Fragment.class);
//                startActivity(shopsIntent);
//            }
//        });
//
//
//        blog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent shopsIntent = new Intent(getActivity(), BlogActivity.class);
//                startActivity(shopsIntent);
//            }
//        });
//
//
//        services.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent shopsIntent = new Intent(getActivity(), ShopScreen_Class.class);
//                shopsIntent.putExtra("cat","ServiceCatalog");
//                startActivity(shopsIntent);
//            }
//        });
//
//        edu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent shopsIntent = new Intent(getActivity(), ShopScreen_Class.class);
//                shopsIntent.putExtra("cat","EducationCatalog");
//                startActivity(shopsIntent);
//            }
//        });
//
//        trans.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent shopsIntent = new Intent(getActivity(), ShopScreen_Class.class);
//                shopsIntent.putExtra("cat","TransportCatalog");
//                startActivity(shopsIntent);
//            }
//        });
//
//
//        shop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shopsIntent = new Intent(getActivity(), Shopping_Activity.class);
//                startActivity(shopsIntent);
//            }
//        });
//
//        hospital.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shopsIntent = new Intent(getActivity(), ShopScreen_Class.class);
//                shopsIntent.putExtra("cat","HospitalCatalog");
//                startActivity(shopsIntent);
//            }
//        });
//
//
////
////        fun.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent shopsIntent = new Intent(getActivity(), Fun.class);
////                startActivity(shopsIntent);
////            }
////        });
//
//
//        bank.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shopsIntent = new Intent(getActivity(), ShopScreen_Class.class);
//                shopsIntent.putExtra("cat","BankCatalog");
//                startActivity(shopsIntent);
//            }
//        });
//
////       mall.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent shopsIntent = new Intent(getActivity(), MallClass.class);
////                startActivity(shopsIntent);
////            }
////        });
//
//
//        events.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shopsIntent = new Intent(getActivity(), ShopScreen_Class.class);
//                shopsIntent.putExtra("cat","EventCatalog");
//                startActivity(shopsIntent);
//            }
//        });
//
//       offer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shopsIntent = new Intent(getActivity(), OfferActivity.class);
//                startActivity(shopsIntent);
//            }
//        });
//
//        hotels.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shopsIntent = new Intent(getActivity(), ShopScreen_Class.class);
//                shopsIntent.putExtra("cat","HotelCatalog");
//                startActivity(shopsIntent);
//            }
//        });
//        ngo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shopsIntent = new Intent(getActivity(), ShopScreen_Class.class);
//                shopsIntent.putExtra("cat","NgoCatalog");
//                startActivity(shopsIntent);
//            }
//        });
//

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shopsIntent = new Intent(getActivity(), JobSearchActivity.class);
                startActivity(shopsIntent);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shopsIntent = new Intent(getActivity(), Chat_Activity.class);
                startActivity(shopsIntent);
            }
        });


        category();

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

//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
//
//                };

                cat_recyclerView.setLayoutManager(layoutManager);
                CAtAdapter =  new CategoryAdapter(getActivity(),cat);
                CAtAdapter.setOnItemClickListener(Fragment_Home.this);
                cat_recyclerView.setAdapter(CAtAdapter);







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

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);


    }

    @Override
    public void onItemClick(int position) {

        CategoryModel model = cat.get(position);

        String cat_name = model.getCat_name();

        Toast.makeText(getActivity(), cat_name.toString(), Toast.LENGTH_SHORT).show();
        Log.i("bdfhgfyhsdufs",cat_name);

        if(cat_name.equals("OfferCatalog")){

            Intent intent = new Intent(getActivity(), OfferActivity.class);
            intent.putExtra("cat",cat_name);
            startActivity(intent);
        }else if (cat_name.equals("Newscatalog")){
            Intent intent = new Intent(getActivity(), NewsActivity.class);
            intent.putExtra("cat",cat_name);
            startActivity(intent);
        }else if (cat_name.equals("BloodCatalog")){
            Intent intent = new Intent(getActivity(), Blood_Fragment.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(getActivity(), ShopScreen_Class.class);
            intent.putExtra("cat", cat_name);
            startActivity(intent);
        }


    }
}