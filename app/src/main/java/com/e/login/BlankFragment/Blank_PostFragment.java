package com.e.login.BlankFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
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
import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.BaseApi.Api;
import com.e.login.HomeClass.Slider_Top_Adapter;
import com.e.login.R;
import com.e.login.ReviewsActivity;
import com.e.login.ShopscreenClass.ShopScreenAdapter;
import com.e.login.ShopscreenClass.ShopScreenModel;
import com.e.login.ShopscreenClass.ShopsScreenFragment;
import com.e.login.SignUpActivity;
import com.e.login.Verification.VerifyActivity;
import com.e.login.utils.PreferenceUtils;
import com.kyleduo.switchbutton.SwitchButton;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Blank_PostFragment extends Fragment {
    private static final int REQUEST_CALL = 1 ;
    LinearLayout reviews,phone_call,visible;

   TextView titlee,addres,rate,verify,view_ct,open_tm,close_tm,desc,phone_num;
   ImageView ac_image,loc,mail,whatsp,fb,inst,twitt,u_tube;
   RelativeLayout web;

    String id = null;
    String shop_category_id = null;
    String logo = null;
    String Banner = null;
    String title,address = null;
    String open_time = null;
    String close_time = null;
    String rating = null;
    String api;
    String comment,com_rating;
    String verified,description,location,website,email,phone,whatsapp,facebook,instagram,twitter,youtube,view_count = null,name=null,img = null;

    SliderView sliderView;

    int images[]={R.drawable.banner,
            R.drawable.banner,
            R.drawable.banner,
            R.drawable.banner};

    String token,idd;

    List<Blank_Comments_Model> blank_comments_modelList;
    Blank_Comments_Adapter adapter;
    String data = null,data2 = null,data3 = null;
    RecyclerView recyclerView;

    com.kyleduo.switchbutton.SwitchButton switchButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_blank__post, container, false);



        recyclerView = root.findViewById(R.id.reviews_recycle_postt);

        Api a = new Api();
        api = a.getBASE_URL();

        Intent intent = getActivity().getIntent();
        data = intent.getStringExtra("rate");
        data2 = intent.getStringExtra("list");
        data3 = intent.getStringExtra("id");

//        token= intent.getStringExtra("token");
//        idd = intent.getStringExtra("id");
//        Toast.makeText(getContext(),data2, Toast.LENGTH_SHORT).show();
//      Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getContext(), data3, Toast.LENGTH_SHORT).show();
//        String data = intent.getStringExtra("shop_category_id");
//
//
//
//        String data1 = intent.getStringExtra("comment");
//        String data2 = intent.getStringExtra("rating");


       // Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getContext(), data2, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getContext(), data3, Toast.LENGTH_SHORT).show();






        if(data2.equals("ShopCatalog")){
            String url = api + "get-shop-details?shop_id="+data3;
          social(url);

        }else if(data2.equals("ServiceCatalog")) {
            String url = api + "get-service-details?service_id="+data3;
            social(url);



        } else if(data2.equals("EducationCatalog")){
            String url = api + "get-education-catalog-details?education_id="+data3;
            social(url);


        }else if(data2.equals("TransportCatalog")){
            String url = api + "get-transport-details?transport_id="+data3;
            social(url);


        }else if(data2.equals("HospitalCatalog")){
            String url = api + "get-hospital-details?hospital_id="+data3;
            social(url);


        }else if(data2.equals("EventCatalog")){
            String url = api + "get-event-details?event_id="+data3;
            social(url);

        }else if(data2.equals("HotelCatalog")){
            String url = api + "get-hotel-details?hotel_id="+data3;
            social(url);

        }else if(data2.equals("BankCatalog")){
            String url = api + "get-bank-details?bank_id="+data3;
            social(url);

        }




        sliderView = root.findViewById(R.id.slider_sr);

        Sliderblank_Top_Adapter sliderAdapter = new Sliderblank_Top_Adapter(images);

        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        titlee = root.findViewById(R.id.ac_title);
        addres = root.findViewById(R.id.address_txt);
        rate = root.findViewById(R.id.rate);
        verify = root.findViewById(R.id.verifyy_txt);
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
//                String[] number = {"8056553064","9360999506"};
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setTitle("Choose Contact");
//                builder.setItems(number, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // the user clicked on colors[which]
//
//
//                    }
//                });
//                builder.show();
            }
        });




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






        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intentList = new Intent(getContext(),ReviewsActivity.class);
                    intentList.putExtra("cat",data2);
                    intentList.putExtra("id",data3);
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



public void social(String url){
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        @SuppressLint("CheckResult")
        @Override
        public void onResponse(JSONObject response) {



//

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
                location = jsonObject.getString("location");
                website = jsonObject.getString("website");
                email = jsonObject.getString("email");
                phone = jsonObject.getString("phone");
               // whatsapp = jsonObject.getString("whatsapp");
               // facebook = jsonObject.getString("facebook");
              //  instagram = jsonObject.getString("instagram");
              //  twitter = jsonObject.getString("twitter");
              //    youtube = jsonObject.getString("youtube");
                view_count = jsonObject.getString("view_count");




                String Success = response.getString("success");
                String msg = response.getString("message");

                JSONArray res = jsonObject.getJSONArray("comments");

//                Toast.makeText(getActivity(), res.toString(), Toast.LENGTH_SHORT).show();
//                Log.i("yfyhtu",res.toString());
                blank_comments_modelList = new ArrayList<>();
                for (int i = 0; i < res.length(); i++) {


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
                adapter =  new Blank_Comments_Adapter(getContext(),blank_comments_modelList);
                recyclerView.setAdapter(adapter);

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
//                    phone_num.setText(phone);
                    view_ct.setText(view_count);

                    blank_comments_modelList= new ArrayList<>();



                }else{
                    Log.i("1234",msg);
                    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
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

            params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(getActivity()));
            return params;
        }
    };

    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
    requestQueue.add(jsonObjectRequest);



}


}