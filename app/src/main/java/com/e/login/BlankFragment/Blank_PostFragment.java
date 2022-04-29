package com.e.login.BlankFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.BaseApi.Api;
import com.e.login.R;
import com.e.login.ReviewsActivity;
import com.e.login.ShopscreenClass.Banner_Adapter;
import com.e.login.ShopscreenClass.Banner_model;
import com.e.login.ShopscreenClass.ShopsScreenFragment;
import com.e.login.utils.PreferenceUtils;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class Blank_PostFragment extends Fragment {
    private static final int REQUEST_CALL = 1;
    LinearLayout reviews, phone_call, visible;

    TextView titlee, addres, rate, verify, view_ct, open_tm, close_tm, desc, phone_num;
    ImageView ac_image, loc, mail, whatsp, fb, inst, twitt, u_tube;
    RelativeLayout web;
    List<Banner_model> banner_modelList;
    Banner_Adapter adapter1;
    private long mRequestStartTime;

    String id;
    String follow_id;
    String banner_type,banner_type_id,banner_cat_id,banner_url,banner_img,view_count;

    String logo;
    String Banner;
    String title, address;
    String open_time;
    String close_time;
    String rating;
    String category_id;
    String catalog_id;
    String catalog_type;
    String api;
    String comment, com_rating;
    String verified, description, location, website, email, phone, whatsapp, facebook, instagram, twitter, youtube, viewcount = null, name = null, img = null;

    JSONObject followArray;
    LinearLayout ifsc;

    List<Blank_Comments_Model> blank_comments_modelList;
    Blank_Comments_Adapter adapter;
    String data, data2, data3;
    RecyclerView recyclerView;
    LinearLayout follow, unfollow, review1;
    TextView follow_txt,unfollow_txt;
    Button view_more;
    RecyclerView banner;


    com.kyleduo.switchbutton.SwitchButton switchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_blank__post, container, false);


        recyclerView = root.findViewById(R.id.reviews_recycle_postt);
        ifsc = root.findViewById(R.id.ifsc_linear);
        banner = root.findViewById(R.id.banner_recycle);

        Api a = new Api();
        api = a.getBASE_URL();

        Intent intent = getActivity().getIntent();
        data = intent.getStringExtra("rate");
        data2 = intent.getStringExtra("list");
        data3 = intent.getStringExtra("id");
        verify = root.findViewById(R.id.verifyy_txt);
        follow = root.findViewById(R.id.follow_linear);
        follow_txt = root.findViewById(R.id.follow_txt);




        if (data2.equals("ShopCatalog")) {
            String url = api + "get-shop-details?shop_id=" + data3;
            social(url);
            ifsc.setVisibility(View.GONE);

        } else if (data2.equals("ServiceCatalog")) {
            String url = api + "get-service-details?service_id=" + data3;
            social(url);

            ifsc.setVisibility(View.GONE);

        } else if (data2.equals("EducationCatalog")) {
            String url = api + "get-education-catalog-details?education_id=" + data3;
            social(url);
            ifsc.setVisibility(View.GONE);

        } else if (data2.equals("TransportCatalog")) {
            String url = api + "get-transport-details?transport_id=" + data3;
            social(url);

            ifsc.setVisibility(View.GONE);
        } else if (data2.equals("HospitalCatalog")) {
            String url = api + "get-hospital-details?hospital_id=" + data3;
            social(url);
            ifsc.setVisibility(View.GONE);

        } else if (data2.equals("EventCatalog")) {
            String url = api + "get-event-details?event_id=" + data3;
            social(url);
            ifsc.setVisibility(View.GONE);

        } else if (data2.equals("HotelCatalog")) {
            String url = api + "get-hotel-details?hotel_id=" + data3;
            social(url);
            ifsc.setVisibility(View.GONE);

        } else if (data2.equals("BankCatalog")) {
            String url = api + "get-bank-details?bank_id=" + data3;
            social(url);
            ifsc.setVisibility(View.VISIBLE);

        }

        if(data2.equals("ShopCatalog")){
            String url = api + "display-banner?banner_type=ShopBanner&banner_category_id="+data3;
            home_banner(url);

        }else if(data2.equals("ServiceCatalog")) {
            String url = api + "display-banner?banner_type=ServiceBanner&banner_category_id="+data3;
            home_banner(url);



        } else if(data2.equals("EducationCatalog")){
            String url = api + "display-banner?banner_type=EducationBanner&banner_category_id="+data3;
            home_banner(url);


        }else if(data2.equals("TransportCatalog")){
            String url = api + "display-banner?banner_type=TransportBanner&banner_category_id="+data3;
            home_banner(url);

        }else if(data2.equals("HospitalCatalog")){
            String url = api + "display-banner?banner_type=HospitalBanner&banner_category_id="+data3;
            home_banner(url);

        }else if(data2.equals("EventCatalog")){
            String url = api + "display-banner?banner_type=EventBanner&banner_category_id="+data3;
            home_banner(url);


        }else if(data2.equals("HotelCatalog")){
            String url = api + "display-banner?banner_type=HotelBanner&banner_category_id="+data3;
            home_banner(url);


        }else if(data2.equals("BankCatalog")){
            String url = api + "display-banner?banner_type=BankBanner&banner_category_id="+data3;
            home_banner(url);

        }





//
//        if(followArray != null){
//            follow_txt.setText("Following");
//
//
//        }else {
//            follow_txt.setText("Follow");
//        }
//
//


        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (followArray == null) {
                    follow();
                }

                else if(followArray != null){

                    un_follow();

                }

            }
        });




        view_more = root.findViewById(R.id.view_more_btn);
        view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view_more.setVisibility(View.GONE);

                if(data2.equals("ShopCatalog")){
                    String url = api + "get-shop-comments?shop_id="+data3;
                    shop_comments(url);


                }else if(data2.equals("ServiceCatalog")) {
                    String url = api + "get-service-comments?service_id="+data3;
                    shop_comments(url);


                } else if(data2.equals("EducationCatalog")){
                    String url = api + "get-education-comments?education_id="+data3;
                    shop_comments(url);

                }else if(data2.equals("TransportCatalog")){
                    String url = api + "get-transport-comments?transport_id="+data3;
                    shop_comments(url);


                }else if(data2.equals("HospitalCatalog")){
                    String url = api + "get-hospital-comments?hospital_id="+data3;
                    shop_comments(url);


                }else if(data2.equals("EventCatalog")){
                    String url = api + "get-event-comments?event_id="+data3;
                    shop_comments(url);

                }else if(data2.equals("HotelCatalog")){
                    String url = api + "get-hotel-comments?hotel_id="+data3;
                    shop_comments(url);

                }else if(data2.equals("BankCatalog")){
                    String url = api + "get-bank-comments?bank_id="+data3;
                    shop_comments(url);


                }
            }
        });

        Date date = new Date();

        SimpleDateFormat formatTime = new SimpleDateFormat("hh.mm aa");


        String time = formatTime.format(
                date); // changing the format of 'date'

        // display time as per format
        System.out.println(
                "Current Time in AM/PM Format is : " + time);




        titlee = root.findViewById(R.id.ac_title);
        addres = root.findViewById(R.id.address_txt);
        rate = root.findViewById(R.id.rate);

        view_ct = root.findViewById(R.id.view_count);
        desc = root.findViewById(R.id.description);
        open_tm = root.findViewById(R.id.open_time);
        close_tm = root.findViewById(R.id.close_time);
        phone_num = root.findViewById(R.id.phone_num);


        ac_image = root.findViewById(R.id.shop_home_logo);
        loc = root.findViewById(R.id.loc_imagee);
        web = root.findViewById(R.id.we_imgg);
        mail = root.findViewById(R.id.mail_img);
        whatsp = root.findViewById(R.id.whatsapp);
        fb = root.findViewById(R.id.facebook);
        inst = root.findViewById(R.id.insta);
        twitt = root.findViewById(R.id.twitter);
        u_tube = root.findViewById(R.id.u_tube);


        reviews = root.findViewById(R.id.reviews_lnrr);
        switchButton = root.findViewById(R.id.sb_text1);
        phone_call = root.findViewById(R.id.phone_call);

        phone_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();

            }
        });


        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String lc = location;

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://" + lc));
                startActivity(intent);
            }
        });


        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ml = email;


                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + ml));

                i.putExtra(Intent.EXTRA_EMAIL, new String[]{ml});

                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        whatsp.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                String wp = whatsapp;
//                String mobileNumber = "7397093106";
                String message = "Hii";

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91" + wp + "&text=" + message));
                startActivity(intent);
            }
        });


        fb.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String fb = facebook;

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://" + fb));
                startActivity(intent);

            }

        });

        inst.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String insta = instagram;


                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://" + insta));
                startActivity(intent);


            }
        });
        twitt.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String tw = twitter;


                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://" + tw));
                startActivity(intent);


            }
        });

        u_tube.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String u = youtube;


                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://" + u));
                startActivity(intent);


            }
        });

        web.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String web = website;


                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://" + web));
                startActivity(intent);


            }
        });


        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentList = new Intent(getContext(), ReviewsActivity.class);
                intentList.putExtra("cat", data2);
                intentList.putExtra("id", data3);
                intentList.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentList);



            }
        });


        return root;
    }

    private void makePhoneCall() {
        String number = phone;

        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(getActivity(), "Permission Denied to make a call" + "", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void social(String url) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint({"CheckResult", "SetTextI18n"})
            @Override
            public void onResponse(JSONObject response) {


                try {

                    JSONObject jsonObject = response.getJSONObject("data");


                    id = jsonObject.getString("id");
//                shop_category_id = jsonObject.getString("shop_category_id");
                    Banner = jsonObject.getString("banner");
                    logo = jsonObject.getString("logo");
                    title = jsonObject.getString("title");
                    address = jsonObject.getString("address");
                    open_time = jsonObject.getString("open_time");
                    close_time = jsonObject.getString("close_time");
                    rating = jsonObject.getString("rating");
                    verified = jsonObject.getString("verified");
                    description = jsonObject.getString("description");
//                    location = jsonObject.getString("location");
                    website = jsonObject.getString("website");
                    email = jsonObject.getString("email");
                    phone = jsonObject.getString("phone");
                    view_count = jsonObject.getString("view_count");

//
//                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
//                    Date firstDate = null;
//
//                    String time = "04:05:06";
//                    firstDate = sdf.parse(time);
//
//                    String times = sdf.format(firstDate);
//
//
//                    Log.i("fbjedhfeqipoRJFPoq",times);

                    titlee.setText(title);
                    addres.setText(address);
                    open_tm.setText(open_time);
                    close_tm.setText(close_time);
                    rate.setText(rating);
                    verify.setText(verified);
                    desc.setText(description);
//                    phone_num.setText(phone);
                    view_ct.setText(view_count);


                    followArray = jsonObject.getJSONObject("follow");
                    catalog_id = followArray.getString("catalog_id");
                    catalog_type = followArray.getString("catalog_type");
                    follow_id = followArray.getString("id");


                    if(jsonObject.getJSONObject("follow")!=null){
                        follow_txt.setText("Following");

                        JSONArray res = jsonObject.getJSONArray("comments");


                        blank_comments_modelList = new ArrayList<>();
                        for (int i = 0; i<3; i++) {

                            JSONObject data = res.getJSONObject(i);

                            comment = data.getString("comment");
                            com_rating = data.getString("rating");
                            JSONObject user = data.getJSONObject("user");
                            name = user.getString("name");
                            img = user.getString("image");


                            Blank_Comments_Model viewmodel = new Blank_Comments_Model();

                            viewmodel.setImg(img);
                            viewmodel.setTxt(name);
                            viewmodel.setTxt1(comment);
                            viewmodel.setTxt2(com_rating);



                            blank_comments_modelList.add(viewmodel);

                        }




                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter = new Blank_Comments_Adapter(getContext(), blank_comments_modelList);
                        recyclerView.setAdapter(adapter);


                    }else if(jsonObject.getJSONObject("follow")==null){
                        follow_txt.setText("Follow");
                        JSONArray res = jsonObject.getJSONArray("comments");


                        blank_comments_modelList = new ArrayList<>();
                        for (int i = 0; i<3; i++) {

                            JSONObject data = res.getJSONObject(i);

                            comment = data.getString("comment");
                            com_rating = data.getString("rating");
                            JSONObject user = data.getJSONObject("user");
                            name = user.getString("name");
                            img = user.getString("image");


                            Blank_Comments_Model viewmodel = new Blank_Comments_Model();

                            viewmodel.setImg(img);
                            viewmodel.setTxt(name);
                            viewmodel.setTxt1(comment);
                            viewmodel.setTxt2(com_rating);

                            blank_comments_modelList.add(viewmodel);

                        }




                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter = new Blank_Comments_Adapter(getContext(), blank_comments_modelList);
                        recyclerView.setAdapter(adapter);


                    }





                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Charset charset = Charset.defaultCharset();
                String str = new String(error.networkResponse.data, charset);
                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                Log.i("dsfigyrsuitr", str);

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);


    }

    public void follow() {


        String url = "http://nk.inevitabletech.email/public/api/follow";

        JSONObject jsonBody = new JSONObject();


        try {

            jsonBody.put("catalog_type",data2);
            jsonBody.put("catalog_id",data3);



            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    try {
                        String Success = response.getString("success");
                        String msg = response.getString("message");


                        if (Success == "true") {

                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                            follow_txt.setText("Following");

                        } else {


                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();


                        }


                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

//                    Charset charset = Charset.defaultCharset();
//                    String str = new String(error.networkResponse.data, charset);
//                    Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
//                    Log.i("dsfigyrsuitr", str);


                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();

                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));

                    return params;
                }
            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void un_follow() {


        String url = "http://nk.inevitabletech.email/public/api/unfollow?id=" + follow_id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {
                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    if (Success == "true") {


                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

                        follow_txt.setText("Follow");

                    } else {


                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();


                    }


                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Charset charset = Charset.defaultCharset();
                String str = new String(error.networkResponse.data, charset);
                Toast.makeText(getActivity(), "The selected id is invalid", Toast.LENGTH_SHORT).show();






            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("Accept", "application/json");
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));
                return params;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);

    }

    public void shop_comments(String url) {


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {

                    JSONArray jsonArray = response.getJSONArray("data");



                    blank_comments_modelList = new ArrayList<>();



                    for (int i = 0; i<jsonArray.length(); i++) {


                        JSONObject data = jsonArray.getJSONObject(i);

                        comment = data.getString("comment");
                        com_rating = data.getString("rating");
                        JSONObject user = data.getJSONObject("user");
                        name = user.getString("name");
                        img = user.getString("image");


                        Blank_Comments_Model viewmodel = new Blank_Comments_Model();

                        viewmodel.setImg(img);
                        viewmodel.setTxt(name);
                        viewmodel.setTxt1(comment);
                        viewmodel.setTxt2(com_rating);

                        blank_comments_modelList.add(viewmodel);

                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new Blank_Comments_Adapter(getContext(), blank_comments_modelList);
                    recyclerView.setAdapter(adapter);



                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Charset charset = Charset.defaultCharset();
                String str = new String(error.networkResponse.data, charset);
                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                Log.i("ewohfg9uwrytg9", str);
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept", "application/json");
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));


                return params;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);




    }
    public  void home_banner(String url){

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
                        viewcount = jsonObject.getString("view_count");




                        Banner_model viewmodel = new Banner_model();

                        viewmodel.setImage(banner_img);

                        banner_modelList.add(viewmodel);


                        adapter1 =  new Banner_Adapter(getActivity(),banner_modelList);
                        banner.setAdapter(adapter1);
                        banner.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));



                        final  int interval_time = 3000;
                        Handler handler = new Handler();
                        Runnable runnable = new Runnable() {
                            int count =0;
                            @Override
                            public void run() {
                                if(count<banner_modelList.size()){
                                    banner.scrollToPosition(count++);
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






}

