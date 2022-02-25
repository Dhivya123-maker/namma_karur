package com.e.login.HospitalClass;

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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.BlankFragment.Sliderblank_Top_Adapter;
import com.e.login.R;
import com.e.login.ReviewsActivity;
import com.e.login.TransportClass.Transport_Comments_Model;
import com.e.login.TransportClass.transport_Comments_Adapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Hospital_Fragment extends Fragment {
    LinearLayout reviews,phone_call;

    private static final int REQUEST_CALL = 1 ;
    TextView titlee,addres,rate,verify,view_ct,open_tm,close_tm,desc,phone_num;
    ImageView ac_image,loc,mail,whatsp,fb,inst,twitt,u_tube;

    RelativeLayout web;
    String id = null;
    String logo = null;
    String Banner = null;
    String title,address = null;
    String open_time = null;
    String close_time = null;
    String rating = null;
    SliderView sliderView;

    List<Hospital_Comments_Model> hospitalCommentsModelList;
    Hospital_Comments_Adapter adapter;

    int images[]={R.drawable.banner,
            R.drawable.banner,
            R.drawable.banner,
            R.drawable.banner};
    String hospital_category_id,data,data1,verified,description,location,website,email,phone,whatsapp,facebook,instagram,twitter,youtube,view_count = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_hospital_, container, false);


        Intent intent = getActivity().getIntent();
        data= intent.getStringExtra("token");
        data1 = intent.getStringExtra("id");



        sliderView = root.findViewById(R.id.slider_sr3);

        Sliderblank_Top_Adapter sliderAdapter = new Sliderblank_Top_Adapter(images);

        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        titlee = root.findViewById(R.id.ac_title3);
        addres = root.findViewById(R.id.address_txt3);
        rate = root.findViewById(R.id.rate3);
        verify = root.findViewById(R.id.verifyy_txt3);
        view_ct = root.findViewById(R.id.view_count3);
        desc = root.findViewById(R.id.description3);
        open_tm = root.findViewById(R.id.open_time3);
        close_tm = root.findViewById(R.id.close_time3);
        phone_num = root.findViewById(R.id.phone_num3);


        ac_image = root.findViewById(R.id.shop_home_logo3);
        loc = root.findViewById(R.id.loc_image3);
        web = root.findViewById(R.id.we_img3);
        mail = root.findViewById(R.id.mail_img3);
        whatsp = root.findViewById(R.id.whatsapp3);
        fb = root.findViewById(R.id.facebook3);
        inst = root.findViewById(R.id.insta3);
        twitt = root.findViewById(R.id.twitter3);
        u_tube = root.findViewById(R.id.u_tube3);







        reviews = root.findViewById(R.id.reviews_lnr3);
        phone_call = root.findViewById(R.id.phone_call3);




//
//



        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String lc = location;

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://"+lc ));
                startActivity(intent);
            }
        });


        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ml = email;




                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + ml));

                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{ml});
//                i.putExtra(Intent.EXTRA_SUBJECT, "nk");
//                i.putExtra(Intent.EXTRA_TEXT   , "nk");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        phone_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                makePhoneCall();
            }
        });

        whatsp.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {



                String wp = whatsapp;
//                String mobileNumber = "7397093106";
                String message = "Hii";

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+wp + "&text="+message));
                startActivity(intent);
            }
        });


        fb.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String fb = facebook;

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://"+fb ));
                startActivity(intent);

            }

        });

        inst.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String insta = instagram;


                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://"+insta ));
                startActivity(intent);



            }
        });
        twitt.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String tw = twitter;


                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://"+tw ));
                startActivity(intent);



            }
        });

        u_tube.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String u = youtube;


                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://"+u ));
                startActivity(intent);


            }
        });

        web.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String web = website;


                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://"+ web ));
                startActivity(intent);



            }
        });



        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-hospital-details?hospital_id=1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


                Log.i("0000000",response.toString());
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();


                try {



                    JSONObject jsonObject = response.getJSONObject("data");
                    id = jsonObject.getString("id");
                    hospital_category_id = jsonObject.getString("hospital_category_id");
                    Banner = jsonObject.getString("banner");
                    logo = jsonObject.getString("logo");
                    title = jsonObject.getString("title");
                    address = jsonObject.getString("address");
                    open_time = jsonObject.getString("open_time");
                    close_time = jsonObject.getString("close_time");
                    rating = jsonObject.getString("rating");
                    verified = jsonObject.getString("verified");
                    description = jsonObject.getString("description");
                    location = jsonObject.getString("location");
                    website = jsonObject.getString("website");
                    email = jsonObject.getString("email");
                    phone = jsonObject.getString("phone");
                    whatsapp = jsonObject.getString("whatsapp");
                    facebook = jsonObject.getString("facebook");
                    instagram = jsonObject.getString("instagram");
                    twitter = jsonObject.getString("twitter");
                    youtube = jsonObject.getString("youtube");
                    view_count = jsonObject.getString("view_count");



//
                    Log.i("id",id);
                    Log.i("shop",hospital_category_id);
                    Log.i("ban",Banner);
                    Log.i("l",logo);
                    Log.i("t",title);
                    Log.i("a",address);
                    Log.i("o",open_time);
                    Log.i("c",close_time);
                    Log.i("r",rating);
                    Log.i("v",verified);
                    Log.i("d",description);
                    Log.i("l",location);
                    Log.i("w",website);
                    Log.i("e",email);
                    Log.i("p",phone);
                    Log.i("w",whatsapp);
                    Log.i("f",facebook);
                    Log.i("i",instagram);
                    Log.i("tt",twitter);
                    Log.i("u",youtube);
                    Log.i("vv",view_count);










                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    if(Success.equals("true")){
                        Log.i("123",msg);
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();



                        titlee.setText(title);
                        addres.setText(address);
                        open_tm.setText(open_time);
                        close_tm.setText(close_time);
                        rate.setText(rating);
                        verify.setText(verified);
                        desc.setText(description);
                        phone_num.setText(phone);
                        view_ct.setText(view_count);

                        hospitalCommentsModelList = new ArrayList<>();

                        RecyclerView recyclerView = root.findViewById(R.id.reviews_recycle_post3);


                        for (int i = 0; i < response.length(); i++) {

                            Hospital_Comments_Model viewmodel = new Hospital_Comments_Model();



                            viewmodel.setImg("1");
                            viewmodel.setImg1("2");
                            viewmodel.setTxt("Namma Karur User");
                            viewmodel.setTxt1("Good");
                            viewmodel.setTxt2("4.6");



                            hospitalCommentsModelList.add(viewmodel);

                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        adapter =  new Hospital_Comments_Adapter(getContext(),hospitalCommentsModelList);
                        recyclerView.setAdapter(adapter);
                        // recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));







                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    }
//

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
                params.put("Authorization","Bearer  " + data);
                // params.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMTM5N2QxNTAyOGZjZmFjYWVmNjg4MGI0M2Q2OWE0MjU0NjFhMjQwMDQ2ZDc2MDQ1MDk0ZWIxZDk2NDNhZTk4YmE3YTkwMGZhODQwZDIzZGMiLCJpYXQiOjE2NDQ2NjIwNDUuNjk5MTE4LCJuYmYiOjE2NDQ2NjIwNDUuNjk5MTIyLCJleHAiOjE2NzYxOTgwNDUuNjk2MDgxLCJzdWIiOiIxNiIsInNjb3BlcyI6W119.kCe6D4wazRjA5cmETRJhsbKiD6BKhyY_ENT8Ve9QluNjdix7PJI-3HK82fdOAD_A0KYtDhHtCqQlWEEVYT9E6MAueMvPTJ06LQyK5o8C_iUS1n_dWPS04bb1N5R_pIIRdS3wz20JuobRBkAXxTcYM74bnfMmKEVxcmyhwoFdlnDctm3aNEN7NI-2dFVrviYUIbN8L2y3bbZy8zlijMBs7vh77sSVVFMkLgJCiMaKxF-hTyS-wrRz-2ClGqRdQYMQK9y6zlw_-47I9arebNWxukZUGc4-cgJUSedJ7GNuJVBux4PclLns-z6hXIQjCr_C8icbMUmAU7GOWHTnpc1fA-lR-1OQfLFBcwksRWmEFB_O60PVjQzw3L1uh_rW-DsNJs_y1BADJ8RpwMfA9-dVcL7Df-EdnrMP-E1ZgI62_QfuMcc6jM1-LWXzmUHitb8sUSFLqX6OfUGpM2sQ6jtPF3bVR5H9P4Idck3RFfNa1OGikyoldYGFaMOKs-C5fgW2t4YtVFnKJ3ROC9Pg-7ipguu4uCAxUmqsOWDlTAfQglKWZuNsSq_4eKDbq2eq6oDtlFDgO8e5XeYk6bwIQFTcf5RZlcHbMz135zLYu4872r2nsCLrzgeyb5-aveqzS9I5rWufpAz3TkejDzUbb9yItuo0LjbppHY8cw3r4Vz837U");
                // params.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNzRjNTcyMDZmMDJmZGI2ZWU3YzMxODVjMzU4NmUxNjRmNTUwZGU2Nzc1ZjRkNzBjODJhZWU2MjI0MTAyMGJmNzNhODkxZTRmYjA3N2Q3ZDIiLCJpYXQiOjE2NDQyMTExOTguMzExMjk5LCJuYmYiOjE2NDQyMTExOTguMzExMzA0LCJleHAiOjE2NzU3NDcxOTguMzAyNTMzLCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.RTfdEV0paZ5aBBZAfISWLaTXaFYh0y8hA0ePKskH3gN2UAcgtulBlev6nC8DuOrPrcP5jMv-YuioJjuuxd8jHixEUlnnRQSQvGDcaXk-TdrpNpbYwV_ISoN6lfRLWBp-lkZRRus4Z_91hDpZK9eNRYCXhIpybTuL-XZh-Zu2c4Pxervug5ngnw7XwRITeZFcIZsRbIFcXrV-eEm_h0093D4k2zBWNsmxHA0iKTYogOOT1lFreUOZ8VA-8BPPiws-3GuTzP1v464vVA4LxbZuZ18HgZW2ZVZtJjPKokizi2QIsi4OcO3sEnP8aP5LokTJXfug7iHSEc_Gb7qwQYBc70wjAC5-i0yQtmZHtqKYUBWl-yR2iOdFMxX9abysRYGqHMeRMNBC3lVtu88P9Eb0KRDLfLHAyfFoy4bNm9JgGJLt_-TNaM317eEiQeQ5lLw5OcGLFccAMB3gQV3AJ4C4H_omNUJ22Jkh7_6ftacNizj0VN3IClcX99U5P4r368yO4suuG6FxYQUprO3e2fMUqBMzEA6pdI5MZh-aG029-Gv3CSOMnMigawrkinHGJYwTK5mIZdAACatPkps-luG2LQ0Q80Idx5afC7ZE3709gQ8VRsqveczaikv3dC3fz0iq8zDohll7rzxoQusO7QpVwQGhNucCt4lYz9DNbjUTnMI");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);


        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentList = new Intent(getContext(), ReviewsActivity.class);;
                startActivity(intentList);
                //    visible.setVisibility(View.VISIBLE);
//



            }
        });





        return  root;
    }
    private void makePhoneCall() {
        String number = phone;
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }
            else {
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





}