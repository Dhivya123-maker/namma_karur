package com.e.login.Services_Class;

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

import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.BlankFragment.Sliderblank_Top_Adapter;
import com.e.login.EducationClass.Education_Comments_Adapter;
import com.e.login.EducationClass.Education_Comments_Model;
import com.e.login.ProductsFragmentClass.ProductsAdapter;
import com.e.login.ProductsFragmentClass.ProductsModel;
import com.e.login.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment_Service extends Fragment implements FragmentAdapter.OnItemClickListener{



    List<Fragment_Model> fragmentModelList;
    FragmentAdapter adapter;

    String data,data1;
    LinearLayout reviews,phone_call;

    private static final int REQUEST_CALL = 1 ;
    TextView titlee,addres,rate,verify,view_ct,open_tm,close_tm,desc,phone_num;
    ImageView ac_image,loc,mail,whatsp,fb,inst,twitt,u_tube;

    RelativeLayout web;
    String id = null;
    String service_category_id= null;
    String logo = null;
    String Banner = null;
    String title,address = null;
    String open_time = null;
    String close_time = null;
    String rating = null;
    SliderView sliderView;


    int images[]={R.drawable.banner,
            R.drawable.banner,
            R.drawable.banner,
            R.drawable.banner};
    String verified,description,location,website,email,phone,whatsapp,facebook,instagram,twitter,youtube,view_count = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment__service, container, false);


        Intent intent = getActivity().getIntent();
        data= intent.getStringExtra("token");
        data1 = intent.getStringExtra("id");


        sliderView = root.findViewById(R.id.slider_sr1);

        Sliderblank_Top_Adapter sliderAdapter = new Sliderblank_Top_Adapter(images);

        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        titlee = root.findViewById(R.id.ac_title1);
        addres = root.findViewById(R.id.address_txt1);
        rate = root.findViewById(R.id.rate1);
        verify = root.findViewById(R.id.verifyy_txt1);
        view_ct = root.findViewById(R.id.view_count1);
        desc = root.findViewById(R.id.description1);
        open_tm = root.findViewById(R.id.open_time1);
        close_tm = root.findViewById(R.id.close_time1);
        phone_num = root.findViewById(R.id.phone_num1);


        ac_image = root.findViewById(R.id.shop_home_logo1);
        loc = root.findViewById(R.id.loc_image1);
        web = root.findViewById(R.id.we_img1);
        mail = root.findViewById(R.id.mail_img1);
        whatsp = root.findViewById(R.id.whatsapp1);
        fb = root.findViewById(R.id.facebook1);
        inst = root.findViewById(R.id.insta1);
        twitt = root.findViewById(R.id.twitter1);
        u_tube = root.findViewById(R.id.u_tube1);







        reviews = root.findViewById(R.id.reviews_lnrr1);
        phone_call = root.findViewById(R.id.phone_call1);


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





        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-service-details?service_id=1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



//                Log.i("jehuihrfo",response.toString());
//                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();



                try {



                    JSONObject jsonObject = response.getJSONObject("data");
                    id = jsonObject.getString("id");
                    service_category_id = jsonObject.getString("service_category_id");
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
                    Log.i("shop",service_category_id);
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
//                        Log.i("123",msg);
//                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
//


                        titlee.setText(title);
                        addres.setText(address);
                        open_tm.setText(open_time);
                        close_tm.setText(close_time);
                        rate.setText(rating);
                        verify.setText(verified);
                        desc.setText(description);
                        phone_num.setText(phone);
                        view_ct.setText(view_count);


                        fragmentModelList = new ArrayList<>();

                        RecyclerView recyclerView = root.findViewById(R.id.reviews_recycle_post1);


                        for (int i = 0; i < response.length(); i++) {

                            Fragment_Model viewmodel = new Fragment_Model();

                            viewmodel.setText("Namma karur user");
                            viewmodel.setText_one("Good");
                            viewmodel.setText_two("4.6");
                            viewmodel.setImage("1");
                           viewmodel.setImage1("2");



                            fragmentModelList.add(viewmodel);

                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        adapter =  new FragmentAdapter(getContext(),fragmentModelList);
                        recyclerView.setAdapter(adapter);






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
                params.put("Authorization","Bearer "+ data);
                //params.put("Authorization","Bearer  eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNDFkNTgzOTFjNzU1YWExNTg3NDI3ZmU1YmM1ZTFlYWY3OWI0N2Q5ZGE4MWI1NzJhYTQxZjU5ZWM5YTVmZWRlMGIxMDkzNDI5ZTcwMmM3YWEiLCJpYXQiOjE2NDQ2Njg3NzMuODczNjYxLCJuYmYiOjE2NDQ2Njg3NzMuODczNjY4LCJleHAiOjE2NzYyMDQ3NzMuODY2NDEyLCJzdWIiOiIyNSIsInNjb3BlcyI6W119.Qv9HGBE2XYouZw8BEoFjw7-yPK6CsDyELyYKmIr87goixhTEfN2mYJFD2JQAdnDvWZuOXhqJdu7iderz14x3nlcUcc7XT1fMnETCqa4AhbW4XJA7_AFtxkyyeAGLXLsJQ_bgChKvnasM6RATLMD4yGKtylXIuqJdh9WeFAZaxwdfj6DrUb0xn2wEu3LzdjM1AexL6nKrA4Ye60HEpT3lohvG4j-vJrG0fxm3MTy3CKm5nu1hZ-OnES73VSLv07AueqEJKClyrE4edufyj4SVQNhDSWp4YiFTt7VVCfUL2AQDFIIGi-QwBBl-qeozMkvHmB5VGM70ivCQez0rKsGg0nqpRxGM80Gwzfgp51VldPXWmTH3hVV-vIFiAHAraF3uliLJ5pPLX3oPo2QxUqhOILKtT_pRhQU3psBww9OiAcTLb5pEdxbbia2m-sZBziOk-7fedUdb5IlUItS4r9bmOegRWm2mRU2TuYaO1U4mMHA6jgVp99P-JrlgLCZyxU-aEpP5BzcnlqCVl2ZE_PyFm62wqvEga74ZNZ-I-qeCU0Hm4nYRfzKZnPjW4wQAg5WieOnRU-9dOVrlHZR3RTz39EmujqcKol93MOMdHrXsiMmviweGzpfPYM1i6rLfkXutYzDRHZmKWWGTz5hys6NodP348cd9XtlwdTmCtgl95Qg");
                //  params.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNDRjOGQyOWFhMzdmYTk1MTE2OTAzNWI3MzcwZTk2Njk4MmVmMmQzZTcxZjI3MjRkNjViYTI0YTdhMTE1MzMxM2QyYmFjNmM4YTcwNDJjYzQiLCJpYXQiOjE2NDQ1NTc5MDIuMTU3NTI0LCJuYmYiOjE2NDQ1NTc5MDIuMTU3NTI4LCJleHAiOjE2NzYwOTM5MDIuMTUxOTIzLCJzdWIiOiI1NiIsInNjb3BlcyI6W119.xhAjKhtWGprH1hAHckkYdXpjl7MmITLytdcawHOQ6h_6MYO8-CZpavBknPbeJXLf8G51LbypFf9VOKML7kKmmgqWi6SB5MxhtUWs7JgszMDi2B8URqj1-hXBQqOOOaR5BJzaXXa37T4Br6NeyvVz_U9z-9pxw-pQzshjSAwJCHAXMpGwMLy4IEug4npqa-Ym7ixBRt0b_VBw0tLzCw4wqDU61OgBWZqFy0J8Mwn4_OKi8XrqJxK1CzZxtwFzDAE7p5eTyOiRD8Ijb4k20AqIXkcb1Cz1BMGYrVQiDmdOtFKUmuHERTvNvF4578ES77ZBE0pvyueDgU2u5713vUeDGPjLyL1cQUXsXg8gmoIdVGl1-S0mOP-y0wTW8-PxTHQuFdsiPcVMLfVP9RQ4GzA2ehEStRJbZyOQwXe7WrS2DMTkg4FkZqMe9q7qGn7Lsuz2tNSM7OooyDxnNTORhWs8w3yDgn_KhSrN-PW7aZQs5w3pVSI4oLdOiR-vMtyPw_ofIiJQV0UFiTCvlX9ln99aVybh8HzWIrQlz96RP8127lIPfQC4-bQAKrXya0xXnfOYSLzWNja13ohdEgz0lN25bLPieW_eQyJT2_Ypk3Cy44lEsaMPndy2xvdt9EfXA_LfLXUzlwTLSCjRgxBJbO-g6bmgZPEoACxwkrVKJzVzZac");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);

        return  root;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(),Service_Carrier.class);
        startActivity(intent);
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