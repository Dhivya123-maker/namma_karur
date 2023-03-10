package com.e.login.Offers;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.BaseApi.Api;
import com.e.login.ChatFeature;
import com.e.login.EnquiryFragment;
import com.e.login.Help_Class.Helpline;
import com.e.login.HomeClass.Fragment_Home;
import com.e.login.HomeClass.Home;
import com.e.login.Profile;
import com.e.login.QrCodeFragment;
import com.e.login.R;
import com.e.login.info_Class.InformationFragment;
import com.e.login.utils.PreferenceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfferActivity extends AppCompatActivity{

    List<Offer_Model> offerModelList;
    Offer_Adapter adapter;
    List<Offer_One_Model> offerOneModelList;
    Offer_One_Adapter adapter1;
    List<Offer_two_model> offerTwoModelList;
    Offer_two_Adapter adapter2;
    TextView top,close;
    public static final String TAG = "bottom_sheet";

    String api;


    String t_id = null, t_name = null, t_image = null, t_des = null;
    String c_id = null, c_name = null, c_image = null, c_des = null,c_start,c_end;
    String o_id = null, o_name = null, o_image = null, o_des = null;

    RecyclerView recyclerView,recyclerView1,recyclerView2;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);


        Api a = new Api();
        api = a.getBASE_URL();

        top = findViewById(R.id.top_view);
        close = findViewById(R.id.close_view);

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_offers);
        btnNav.setOnNavigationItemSelectedListener(navListener);

        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(OfferActivity.this, View_All.class);
                intent1.putExtra("cat1","top_offers");
                startActivity(intent1);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(OfferActivity.this,View_All.class);
                intent1.putExtra("cat1","closing_offers");
                startActivity(intent1);

            }
        });


        offerModelList = new ArrayList<>();

        recyclerView =findViewById(R.id.offers_screen);




        offerOneModelList= new ArrayList<>();

        recyclerView1 =findViewById(R.id.close_offer_screen);

        offerTwoModelList= new ArrayList<>();

         recyclerView2 =findViewById(R.id.only_offer_screen);


        offers();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void offers() {

//        LocalDateTime myDateObj = LocalDateTime.now();
//        System.out.println("Before formatting: " + myDateObj);
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//
//        String formattedDate = myDateObj.format(myFormatObj);
//        System.out.println("After formatting: " + formattedDate);
//
//
//
//        String stringDate = "2022-04-27T10:21:00.000000Z";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//
//        OffsetDateTime odt = OffsetDateTime.parse ( "2022-04-27T10:21:00.000000Z" , DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss.SSSX" ) ) ;
//
//        LocalDateTime today = LocalDateTime.parse("2022-04-27T10:21:00.000000Z");
//        System.out.println("today"+odt);


        String url = api + "offer-home-page";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



                try {
                    JSONObject res = response.getJSONObject("data");

                    JSONArray top,close,only;

                    top = res.getJSONArray("top");
                    close =  res.getJSONArray("closing");
                    only = res.getJSONArray("onlyForYou");

                    for (int i = 0; i < top.length(); i++) {


                        JSONObject jsonObject = top.getJSONObject(i);



                        t_id = jsonObject.getString("id");
                        t_name = jsonObject.getString("title");
                        t_image = jsonObject.getString("image");
                        t_des = jsonObject.getString("description");
                        Offer_Model viewmodel = new Offer_Model();



                        viewmodel.setImg(t_image);
                        viewmodel.setId(t_id);


                        offerModelList.add(viewmodel);

                    }

                    for (int i = 0; i < close.length(); i++) {

                        JSONObject jsonObject = close.getJSONObject(i);



                        c_id = jsonObject.getString("id");
                        c_name = jsonObject.getString("title");
                        c_image = jsonObject.getString("image");
                        c_des = jsonObject.getString("description");
                        c_start = jsonObject.getString("start_date");
                        c_end = jsonObject.getString("end_date");


                        Offer_One_Model viewmodel = new Offer_One_Model();


                        new CountDownTimer(50000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                // Used for formatting digit to be in 2 digits only
                                NumberFormat f = new DecimalFormat("00");
                                long hour = (millisUntilFinished / 3600000) % 24;
                                long min = (millisUntilFinished / 60000) % 60;
                                long sec = (millisUntilFinished / 1000) % 60;
                                viewmodel.setTxt(f.format(hour) + "  :  " +   f.format(min) + "  :  " +   f.format(sec));
//                                viewmodel.setTxt(c_end);
                            }

                            // When the task is over it will print 00:00:00 there
                            public void onFinish() {
                                viewmodel.setTxt("00 : 00 : 00");
                            }
                        }.start();



                        viewmodel.setImg(c_image);
//                        viewmodel.setTxt(c_end);
                        viewmodel.setTxt1(" Hrs     Min     sec");
                        viewmodel.setId(c_id);
                        viewmodel.setEnd_date(c_end);

                        offerOneModelList.add(viewmodel);

                    }


                    for (int i = 0; i < only.length(); i++) {



                        JSONObject jsonObject = only.getJSONObject(i);



                        o_id = jsonObject.getString("id");
                        o_name = jsonObject.getString("title");
                        o_image = jsonObject.getString("image");
                        o_des = jsonObject.getString("description");

                        Offer_two_model viewmodel = new Offer_two_model();




                        viewmodel.setImg(o_image);
                        viewmodel.setTxt(o_name);
                        viewmodel.setTxt1(o_des);
                        viewmodel.setId(o_id);

                        offerTwoModelList.add(viewmodel);

                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }


                recyclerView.setLayoutManager(new LinearLayoutManager(OfferActivity.this));

                adapter =  new Offer_Adapter(OfferActivity.this,offerModelList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(OfferActivity.this, LinearLayoutManager.HORIZONTAL, false));





                recyclerView1.setLayoutManager(new LinearLayoutManager(OfferActivity.this));

                adapter1 =  new Offer_One_Adapter(OfferActivity.this,offerOneModelList);
                recyclerView1.setAdapter(adapter1);
                recyclerView1.setLayoutManager(new LinearLayoutManager(OfferActivity.this, LinearLayoutManager.HORIZONTAL, false));

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OfferActivity.this) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };


                recyclerView2.setLayoutManager(linearLayoutManager);

                adapter2 =  new Offer_two_Adapter(OfferActivity.this,offerTwoModelList);
                recyclerView2.setAdapter(adapter2);




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

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(OfferActivity.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(OfferActivity.this);
        requestQueue.add(jsonObjectRequest);



    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            Fragment fragment = null;

            switch (id) {
                case R.id.nav_home:
                    Intent in=new Intent(OfferActivity.this, Home.class);
                    startActivity(in);
                case R.id.nav_tree:
                    fragment = new InformationFragment();
                    break;
                case R.id.nav_qr:
                    fragment = new QrCodeFragment();
                    break;
                case R.id.nav_profilee:

                    fragment = new Helpline();
                    break;
                case R.id.nav_notifications:
                    fragment = new ChatFeature();
                    break;


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).commit();

            return true;
        }
    };



}