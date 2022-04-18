package com.e.login.ShopscreenClass;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
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
import com.e.login.EnquiryFragment;
import com.e.login.HelperClass.ViewPagerAdapter;
import com.e.login.Help_Class.Helpline;
import com.e.login.HomeClass.BannerModel;
import com.e.login.HomeClass.Fragment_Home;
import com.e.login.HomeClass.Slider_Top_Adapter;
import com.e.login.Home_Fragment_Class;
import com.e.login.QrCodeFragment;
import com.e.login.R;
import com.e.login.info_Class.InformationFragment;
import com.e.login.utils.PreferenceUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShopsScreenFragment extends AppCompatActivity implements ShopScreenAdapter.OnItemClickListener {

    private ViewPagerAdapter viewPagerAdapter;
    List<ShopScreenModel> shop_screen_model;
    ShopScreenAdapter adapter;
    List<BannerModel> bannerModelList;
    Slider_Top_Adapter slider_top_adapter;
    TextView textView;
    LinearLayout filter,gone,atm_visible,visible_lnr,loc_lnr;
    SliderView sliderView;


    protected static final String TAG = "LocationOnOff";

    private GoogleApiClient googleApiClient;
    final static int REQUEST_LOCATION = 199;
    private static final int REQUEST_CODE = 1;


    String id = null;
    String logo = null;
    String title = null;
    String address = null;
    String postal_code = null;
    String name,image,atm_cat = null;
    String open_time = null;
    String rating,loc = null;
    String verified = null;
   TextView ac;
   Location location;
    String data,data1,data2,data3;
    String api;
    RecyclerView recyclerView,shop_banner;
    String lat;
    String longi;
    double distance;
    double latitude,longitude;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shops_screen_fragment);


        loc_lnr = findViewById(R.id.loc_lnr);
        atm_visible = findViewById(R.id.atm_visivle_lnr);
        visible_lnr = findViewById(R.id.visible_linear);
        textView = findViewById(R.id.loc);


       recyclerView =findViewById(R.id.shop_screen_fragment);
       shop_banner = findViewById(R.id.shop_banner);



        ac = findViewById(R.id.ac_shops1);

        Api a = new Api();
        api = a.getBASE_URL();

        Intent intent = getIntent();
    data = intent.getStringExtra("name");
     data2 = intent.getStringExtra("list");
        data3 = intent.getStringExtra("id");



        enableLoc();


//        this.setFinishOnTouchOutside(true);


//        final LocationManager manager = (LocationManager) ShopsScreenFragment.this.getSystemService(Context.LOCATION_SERVICE);
//        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(ShopsScreenFragment.this)) {
//            Toast.makeText(ShopsScreenFragment.this,"Gps already enabled",Toast.LENGTH_SHORT).show();
//
//        }else{
//            enableLoc();
//        }




        if (data2.equals("ShopCatalog")) {

            String url = api + "get-shop-catalog-list?category_id="+data3+"&latitude=10.96997034405102&longitude=78.04131520267028&radius=10";
//                    String url = api + "get-shop-catalog-list?category_id=" +data3 +"&latitude="+latitude+"&longitude="+longitude+"&radius=10";

                    shop_screen(url);
                    ac.setText(data);
                    atm_visible.setVisibility(View.GONE);
                    visible_lnr.setVisibility(View.VISIBLE);

            } else if (data2.equals("ServiceCatalog")) {
                String url = api + "get-service-catalog-list?service_category_id=" + data3 + "&latitude=10.96997034405102&longitude=78.04131520267028&radius=10";
                shop_screen(url);
                ac.setText(data);

                atm_visible.setVisibility(View.GONE);
                visible_lnr.setVisibility(View.VISIBLE);

            } else if (data2.equals("MarketCatalog")) {
                String url = api + "get-market-category-list" + data3 + "&latitude=10.96997034405102&longitude=78.04131520267028&radius=10";
                shop_screen(url);
                ac.setText(data);

                atm_visible.setVisibility(View.GONE);
                visible_lnr.setVisibility(View.VISIBLE);

            } else if (data2.equals("EducationCatalog")) {
                String url = api + "get-education-catalog-list?education_category_id=" + data3 + "&latitude=10.96997034405102&longitude=78.04131520267028&radius=10";
                shop_screen(url);
                ac.setText(data);

                atm_visible.setVisibility(View.GONE);
                visible_lnr.setVisibility(View.VISIBLE);

            } else if (data2.equals("TransportCatalog")) {
                String url = api + "get-transport-catalog-list?category_id=" + data3 + "&latitude=10.96997034405102&longitude=78.04131520267028&radius=10";
                shop_screen(url);
                ac.setText(data);
                atm_visible.setVisibility(View.GONE);
                visible_lnr.setVisibility(View.VISIBLE);

            } else if (data2.equals("HospitalCatalog")) {
                String url = api + "get-hospital-catalog-list?hospital_category_id=" + data3 + "&latitude=10.96997034405102&longitude=78.04131520267028&radius=10";
                shop_screen(url);
                ac.setText(data);
                atm_visible.setVisibility(View.GONE);
                visible_lnr.setVisibility(View.VISIBLE);

            } else if (data2.equals("EventCatalog")) {
                String url = api + "get-event-catalog-list?event_category_id=" + data3 + "&latitude=10.96997034405102&longitude=78.04131520267028&radius=10";
                shop_screen(url);
                ac.setText(data);

                atm_visible.setVisibility(View.GONE);
                visible_lnr.setVisibility(View.VISIBLE);

//            initDatePicker();
//            dateButton = findViewById(R.id.fromdatepicker);
//            dateButton.setText("From Date");
//            dateButton = findViewById(R.id.todatepicker);
//            dateButton.setText("To Date");
//            getTodaysDate();


            } else if (data2.equals("HotelCatalog")) {
                String url = api + "get-hotel-catalog-list?hotel_category_id=" + data3 + "&latitude=10.96997034405102&longitude=78.04131520267028&radius=10";
                shop_screen(url);
                ac.setText(data);
                atm_visible.setVisibility(View.GONE);
                visible_lnr.setVisibility(View.VISIBLE);

            } else if (data2.equals("BankCatalog")) {
                String url = api + "get-bank-catalog-list?bank_category_id=" + data3 + "&latitude=10.96997034405102&longitude=78.04131520267028&radius=10";
                shop_screen(url);
                ac.setText(data);
                atm_visible.setVisibility(View.GONE);
                visible_lnr.setVisibility(View.VISIBLE);

            } else if (data2.equals("ATMCatalog")) {
                String url = api + "get-atm-catalog-list?atm_category_id=" + data3 + "&latitude=10.96997034405102&longitude=78.04131520267028&radius=10";
                atm_screen(url);
                ac.setText(data);
                atm_visible.setVisibility(View.VISIBLE);
                visible_lnr.setVisibility(View.GONE);


            }




        if(data2.equals("ShopCatalog")){
            String url = api + "display-banner?banner_type=ShopBanner&banner_category_id="+data3;
            Ban(url);
        }else if(data2.equals("ServiceCatalog")) {
            String url = api + "display-banner?banner_type=ServiceBanner&banner_category_id="+data3;
            Ban(url);

        } else if(data2.equals("EducationCatalog")){
            String url = api + "display-banner?banner_type=EducationBanner&banner_category_id="+data3;
            Ban(url);


        }else if(data2.equals("TransportCatalog")){
            String url = api + "display-banner?banner_type=TransportBanner&banner_category_id="+data3;
            Ban(url);



        }else if(data2.equals("HospitalCatalog")){
            String url = api + "display-banner?banner_type=HospitalBanner&banner_category_id="+data3;
            Ban(url);



        }else if(data2.equals("EventCatalog")){
            String url = api + "display-banner?banner_type=EventBanner&banner_category_id="+data3;
            Ban(url);

        }else if(data2.equals("HotelCatalog")){
            String url = api + "display-banner?banner_type=HotelBanner&banner_category_id="+data3;
            Ban(url);

        }else if(data2.equals("BankCatalog")){
            String url = api + "display-banner?banner_type=BankBanner&banner_category_id="+data3;
            Ban(url);

        }



        filter = findViewById(R.id.filter);
//        filter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent1 = new Intent(ShopsScreenFragment.this, Home_Fragment_Class.class);
////
////
////                startActivity(intent1);
//            }
//        });

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_shops1);
        btnNav.setOnNavigationItemSelectedListener(navListener);

//
//        Slidershop_Top_Adapter sliderAdapter = new Slidershop_Top_Adapter(images);
//
//        sliderView.setSliderAdapter(sliderAdapter);
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
//        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
//        sliderView.startAutoCycle();
//


}
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            Fragment fragment = null;

            switch (id) {
                case R.id.nav_home:
                    fragment = new Fragment_Home();
                    break;
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
                   fragment = new EnquiryFragment();
                    break;


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).commit();

            return true;
        }
    };





    public  void shop_screen(String url){


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray res = response.getJSONArray("data");

                    shop_screen_model = new ArrayList<>();


                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);

                        id = jsonObject.getString("id");
                        logo = jsonObject.getString("logo");
                        title = jsonObject.getString("title");
                        address = jsonObject.getString("address");
                        open_time = jsonObject.getString("open_time");
                        rating = jsonObject.getString("rating");
                        verified = jsonObject.getString("verified");
                        String shop_category_id = jsonObject.getString("shop_category_id");
                        lat = jsonObject.getString("latitude");
                        longi= jsonObject.getString("longitude");
                        distance = jsonObject.getDouble("distance");





                        ShopScreenModel viewmodel = new ShopScreenModel();


                        viewmodel.setId(id);
                        viewmodel.setText(title);
                        viewmodel.setImage(logo);
                        viewmodel.setText_one(address);
                        viewmodel.setText_two(rating);
                        viewmodel.setText_three(verified);
                        viewmodel.setText_four(open_time);
                        viewmodel.setCategory(data2);
                        if(distance<=1000){
                            viewmodel.setDistance("Distance : "+String.format("%.0f", distance)+"m");

                        }else{
                            viewmodel.setDistance("Distance : "+String.format("%.0f", distance/1000)+"Km");

                        }



                        shop_screen_model.add(viewmodel);



                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(ShopsScreenFragment.this));
                adapter =  new ShopScreenAdapter(ShopsScreenFragment.this,shop_screen_model);
                adapter.setOnItemClickListener(ShopsScreenFragment.this);
                recyclerView.setAdapter(adapter);


                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(ShopsScreenFragment.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE);
                } else {


              getcurrentlocation();
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
                params.put("Authorization", "Bearer  " +PreferenceUtils.getToken(ShopsScreenFragment.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShopsScreenFragment.this);
        requestQueue.add(jsonObjectRequest);




    }
    public  void atm_screen(String url){


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {




                try {
                    JSONArray res = response.getJSONArray("data");

                    shop_screen_model = new ArrayList<>();


                    for (int i=0;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);




                        id = jsonObject.getString("id");
                        image = jsonObject.getString("image");
                        name = jsonObject.getString("name");
                        address = jsonObject.getString("address");
                        loc = jsonObject.getString("location");





                        ShopScreenModel viewmodel = new ShopScreenModel();


                        viewmodel.setId(id);
                        viewmodel.setText(name);
                        viewmodel.setImage(image);
                        viewmodel.setText_one(address);
//                        viewmodel.setText_two(rating);
                        viewmodel.setText_three(loc);
                        viewmodel.setCategory(data2);
                        viewmodel.setLoc(loc);




                        shop_screen_model.add(viewmodel);


                        recyclerView.setLayoutManager(new LinearLayoutManager(ShopsScreenFragment.this));
                        adapter =  new ShopScreenAdapter(ShopsScreenFragment.this,shop_screen_model);
                        //adapter.setOnItemClickListener(ShopsScreenFragment.this);
                        recyclerView.setAdapter(adapter);

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
                params.put("Authorization", "Bearer  " +PreferenceUtils.getToken(ShopsScreenFragment.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShopsScreenFragment.this);
        requestQueue.add(jsonObjectRequest);



    }
    public void Ban(String url){


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

                shop_banner.setLayoutManager(new LinearLayoutManager(ShopsScreenFragment.this));
                slider_top_adapter =  new Slider_Top_Adapter(ShopsScreenFragment.this,bannerModelList);
                shop_banner.setAdapter(slider_top_adapter);
                shop_banner.setLayoutManager(new LinearLayoutManager(ShopsScreenFragment.this, LinearLayoutManager.HORIZONTAL, false));


                final int interval = 3000;
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    int count =0;
                    @Override
                    public void run() {

                        if(count< bannerModelList.size()){
                            shop_banner.scrollToPosition(count++);
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
                params.put("Authorization", "Bearer "+PreferenceUtils.getToken(ShopsScreenFragment.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShopsScreenFragment.this);
        requestQueue.add(jsonObjectRequest);


    }

    public  void locationn(){
//        String url = "http://nk.inevitabletech.email/public/api/get-shop-catalog-list?category_id=1&radius=1";
        String url = "http://nk.inevitabletech.email/public/api/get-shop-catalog-list?category_id=1&latitude=10.960203354333085&longitude=78.07130288043845&radius=1";
//        String url = "http://nk.inevitabletech.email/public/api/get-shop-catalog-list?category_id=1&latitude="+latitude+"&longitude="+longitude+"&radius=1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {

                Log.i("bfirejhgiru3yht4oi",response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    shop_screen_model = new ArrayList<>();

                    for(int i=0;i<jsonArray.length();i++)
                    {
                       JSONObject jsonObject = jsonArray.getJSONObject(i);
                       String id = jsonObject.getString("id");
                       String shop_category_id = jsonObject.getString("shop_category_id");
                        lat = jsonObject.getString("latitude");
                         longi = jsonObject.getString("longitude");





                        String geoUri = "http://maps.google.com/maps?q=loc:" + lat+ "," + longi + " (" + "location" + ")";
//                        Uri gmmIntentUri = Uri.parse("geo:"+latitude+longitude);
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                        mapIntent.setPackage("com.google.android.apps.maps");

                      startActivity(mapIntent);




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
                params.put("Authorization", "Bearer " + PreferenceUtils.getToken(ShopsScreenFragment.this));
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
        String cat = model.getCategory();




            Intent intent = new Intent(ShopsScreenFragment.this, Home_Fragment_Class.class);
            intent.putExtra("list",cat);
            intent.putExtra("id",S_id);
            intent.putExtra("name",S_name);
            startActivity(intent);


    }
//    private boolean hasGPSDevice(Context context) {
//        final LocationManager mgr = (LocationManager) context
//                .getSystemService(Context.LOCATION_SERVICE);
//        if (mgr == null)
//            return false;
//        final List<String> providers = mgr.getAllProviders();
//        if (providers == null)
//            return false;
//        return providers.contains(LocationManager.GPS_PROVIDER);
//    }





    private void enableLoc() {

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(ShopsScreenFragment.this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(Bundle bundle) {

                        }

                        @Override
                        public void onConnectionSuspended(int i) {
                            googleApiClient.connect();
                        }
                    })
                    .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(ConnectionResult connectionResult) {

                            Log.d("Location error","Location error " + connectionResult.getErrorCode());
                        }
                    }).build();
            googleApiClient.connect();

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            builder.setAlwaysShow(true);

            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(ShopsScreenFragment.this, REQUEST_LOCATION);

//                                finish();
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                    }
                }
            });
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getcurrentlocation();
            } else {
                Toast.makeText(ShopsScreenFragment.this, "Permission denied", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void getcurrentlocation() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        LocationServices.getFusedLocationProviderClient(ShopsScreenFragment.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(@NonNull LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(ShopsScreenFragment.this)
                                .removeLocationUpdates(this);
                        if(locationResult!= null && locationResult.getLocations().size()>0){
                            int latest = locationResult.getLocations().size()-1;
                            double latitude = locationResult.getLocations().get(latest).getLatitude();
                            double longitude = locationResult.getLocations().get(latest).getLongitude();
                            textView.setText(String.format("Latitude : %s\nlongtitude : %s",latitude,longitude));


                        }
                    }
                }, Looper.getMainLooper());

    }



//
//    private String getTodaysDate()
//    {
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        month = month + 1;
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        return makeDateString(day, month, year);
//    }
//
//    private void initDatePicker()
//    {
//        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
//        {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day)
//            {
//                month = month + 1;
//                String date = makeDateString(day, month, year);
//                dateButton.setText(date);
//                dateButton1.setText(date);
//            }
//        };
//
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//
//        int style = AlertDialog.THEME_HOLO_LIGHT;
//
//        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
//        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
//
//    }
//
//    private String makeDateString(int day, int month, int year)
//    {
//        return getMonthFormat(month) + " " + day + " " + year;
//    }
//
//    private String getMonthFormat(int month)
//    {
//        if(month == 1)
//            return "JAN";
//        if(month == 2)
//            return "FEB";
//        if(month == 3)
//            return "MAR";
//        if(month == 4)
//            return "APR";
//        if(month == 5)
//            return "MAY";
//        if(month == 6)
//            return "JUN";
//        if(month == 7)
//            return "JUL";
//        if(month == 8)
//            return "AUG";
//        if(month == 9)
//            return "SEP";
//        if(month == 10)
//            return "OCT";
//        if(month == 11)
//            return "NOV";
//        if(month == 12)
//            return "DEC";
//
//        //default should never happen
//        return "JAN";
//    }
//
//    public void openDatePicker(View view)
//    {
//        datePickerDialog.show();
//    }

}
