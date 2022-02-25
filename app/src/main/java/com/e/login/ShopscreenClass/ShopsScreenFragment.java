package com.e.login.ShopscreenClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.BaseApi.Api;
import com.e.login.BlankFragment.Blank_PostFragment;
import com.e.login.HelperClass.ViewPagerAdapter;
import com.e.login.HomeClass.Slider_Top_Adapter;
import com.e.login.Home_Fragment_Class;
import com.e.login.R;
import com.e.login.ShopClass.ShopClassAdapter;
import com.e.login.ShopClass.ShopModel;
import com.e.login.ShopClass.ShopScreen_Class;
import com.e.login.fragment_dialog.BottomSheetFragment_filter;
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


public class ShopsScreenFragment extends AppCompatActivity implements ShopScreenAdapter.OnItemClickListener {

    private ViewPagerAdapter viewPagerAdapter;
    List<ShopScreenModel> shop_screen_model;
    ShopScreenAdapter adapter;
    LinearLayout filter,location;
    SliderView sliderView;
    public static final String TAG = "bottom_sheet";

    int[] images = {R.drawable.first_one,
            R.drawable.banner,
            R.drawable.bank_banner,
            R.drawable.banner,
            R.drawable.first_one,
    };
    String id = null;
    String shop_category_id = null;
    String logo = null;
    String title = null;
    String address = null;
    String postal_code = null;
    String open_time = null;
    String rating = null;
    String verified = null;
   TextView ac;

    String data,data1,data2,data3;
    String api;
    RecyclerView recyclerView;

    Context  context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.shops_screen_fragment);




       recyclerView =findViewById(R.id.shop_screen_fragment);


        ac = findViewById(R.id.ac_shops1);

        Api a = new Api();
        api = a.getBASE_URL();

        Intent intent = getIntent();
    data = intent.getStringExtra("name");
//     data1 = intent.getStringExtra("id");
     data2 = intent.getStringExtra("list");
        data3 = intent.getStringExtra("id");






        if(data2.equals("ShopCatalog")){
            String url = api + "get-shop-catalog-list?category_id="+data3;
            shop_screen(url);
            ac.setText(data);
        }else if(data2.equals("ServiceCatalog")) {
            String url = api + "get-service-catalog-list?service_category_id="+data3;
            shop_screen(url);
            ac.setText(data);


        }else if(data2.equals("MarketCatalog")){
            String url = api + "get-market-category-list"+data3;
            shop_screen(url);
            ac.setText(data);

        } else if(data2.equals("EducationCatalog")){
            String url = api + "get-education-catalog-list?education_category_id="+data3;
            shop_screen(url);
            ac.setText(data);

        }else if(data2.equals("TransportCatalog")){
            String url = api + "get-transport-catalog-list?category_id="+data3;
            shop_screen(url);
            ac.setText(data);

        }else if(data2.equals("HospitalCatalog")){
            String url = api + "get-hospital-catalog-list?hospital_category_id="+data3;
            shop_screen(url);
            ac.setText(data);

        }else if(data2.equals("EventCatalog")){
            String url = api + "get-event-catalog-list?event_category_id="+data3;
            shop_screen(url);
            ac.setText(data);

        }else if(data2.equals("HotelCatalog")){
            String url = api + "get-hotel-catalog-list?hotel_category_id="+data3;
            shop_screen(url);
            ac.setText(data);

        }else if(data2.equals("BankCatalog")){
            String url = api + "get-bank-catalog-list?bank_category_id="+data3;
            shop_screen(url);
            ac.setText(data);

        }


        filter = findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent1 = new Intent(ShopsScreenFragment.this, Home_Fragment_Class.class);
//
//
//                startActivity(intent1);
            }
        });


        sliderView = findViewById(R.id.slider_slider);
        Slidershop_Top_Adapter sliderAdapter = new Slidershop_Top_Adapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();



}
    public  void shop_screen(String url){


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



//

                try {
                    JSONArray res = response.getJSONArray("data");

                    shop_screen_model = new ArrayList<>();


                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);


//                        Toast.makeText(ShopsScreenFragment.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
//                        Log.i("jbfhusduycfhb",jsonObject.toString());

                        id = jsonObject.getString("id");
                        logo = jsonObject.getString("logo");
                        title = jsonObject.getString("title");
                        address = jsonObject.getString("address");
                        open_time = jsonObject.getString("open_time");
                        rating = jsonObject.getString("rating");
                        verified = jsonObject.getString("verified");




                        ShopScreenModel viewmodel = new ShopScreenModel();


                        viewmodel.setId(id);
                        viewmodel.setText(title);
                        viewmodel.setImage(logo);
                        viewmodel.setText_one(address);
                        viewmodel.setText_two(rating);
                        viewmodel.setText_three(verified);
                        viewmodel.setText_four(open_time);

                        shop_screen_model.add(viewmodel);



                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(ShopsScreenFragment.this));
                adapter =  new ShopScreenAdapter(ShopsScreenFragment.this,shop_screen_model);
                adapter.setOnItemClickListener(ShopsScreenFragment.this);
                recyclerView.setAdapter(adapter);



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

                params.put("Authorization", "Bearer  " +PreferenceUtils.getToken(ShopsScreenFragment.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShopsScreenFragment.this);
        requestQueue.add(jsonObjectRequest);




    }

    @Override
    public void onItemClick(int position) {


        ShopScreenModel model = shop_screen_model.get(position);
        String S_id = model.getId();
        String S_name = model.getText();


        Intent intent = new Intent(ShopsScreenFragment.this, Home_Fragment_Class.class);
        intent.putExtra("list",data2);
        intent.putExtra("id",S_id);
        intent.putExtra("name",S_name);
        startActivity(intent);
    }
}
