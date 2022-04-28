package com.e.login.ShopscreenClass;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.e.login.BaseApi.Api;
import com.e.login.HelperClass.ViewPagerAdapter;
import com.e.login.Help_Class.Helpline;
import com.e.login.HomeClass.Fragment_Home;
import com.e.login.HomeClass.Home;
import com.e.login.Home_Fragment_Class;
import com.e.login.ChatFeature;
import com.e.login.MainActivity;
import com.e.login.Profile;
import com.e.login.QrCodeFragment;
import com.e.login.R;
import com.e.login.ShopClass.ShopScreen_Class;
import com.e.login.info_Class.InformationFragment;
import com.e.login.utils.PreferenceUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShopsScreenFragment extends AppCompatActivity implements ShopScreenAdapter.OnItemClickListener {

    private ViewPagerAdapter viewPagerAdapter;
    List<ShopScreenModel> shop_screen_model;
    ShopScreenAdapter adapter;
    List<Banner_model> banner_modelList;
    Banner_Adapter adapter1;
    LinearLayout filter,gone,atm_visible,visible_lnr;
    SliderView sliderView;
    public static final String TAG = "bottom_sheet";


    String id = null;
    String banner_type,banner_type_id,banner_cat_id,banner_url,banner_img,view_count;
    String logo = null;
    String title = null;
    String address = null;
    String postal_code = null;
    String name,image,atm_cat = null;
    String open_time = null;
    String rating,loc = null;
    String verified = null;
    TextView ac;

    String data,data1,data2,data3;
    String api;
    RecyclerView recyclerView;
    private DatePickerDialog datePickerDialog;
    private Button dateButton,dateButton1;
    RecyclerView banner;

    LinearLayout location;

    LocationManager locationManager;
    String latitude, longitude;

    private static final int REQUEST_LOCATION = 1;

    Context  context;

    FusedLocationProviderClient mFusedLocationClient;


    String latitudeTextView, longitTextView;
    private static final int REQUEST_CALL = 1 ;

    protected static final int REQUEST_CHECK_SETTINGS = 0x2;
    private GoogleApiClient googleApiClient;

    int PERMISSION_ID = 44;

    Status status;

    String locat;

    TextView lat,log;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.shops_screen_fragment);




        gone = findViewById(R.id.gone_lnr);
        location = findViewById(R.id.location);
        atm_visible = findViewById(R.id.atm_visivle_lnr);
        visible_lnr = findViewById(R.id.visible_linear);
        lat = findViewById(R.id.lat);
        log = findViewById(R.id.log);


        recyclerView =findViewById(R.id.shop_screen_fragment);
        banner = findViewById(R.id.banner_recycle);


        ac = findViewById(R.id.ac_shops1);

        Api a = new Api();
        api = a.getBASE_URL();

        Intent intent = getIntent();
        data = intent.getStringExtra("name");
        data2 = intent.getStringExtra("list");
        data3 = intent.getStringExtra("id");





        if(data2.equals("ShopCatalog")){
            String url = api + "get-shop-catalog-list?category_id="+data3;
            shop_screen(url);
            ac.setText(data);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);
            gone.setVisibility(View.GONE);
        }else if(data2.equals("ServiceCatalog")) {
            String url = api + "get-service-catalog-list?service_category_id="+data3;
            shop_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);

        }else if(data2.equals("MarketCatalog")){
            String url = api + "get-market-category-list"+data3;
            shop_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);

        } else if(data2.equals("EducationCatalog")){
            String url = api + "get-education-catalog-list?education_category_id="+data3;
            shop_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);

        }else if(data2.equals("TransportCatalog")){
            String url = api + "get-transport-catalog-list?category_id="+data3;
            shop_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);

        }else if(data2.equals("HospitalCatalog")){
            String url = api + "get-hospital-catalog-list?hospital_category_id="+data3;
            shop_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);

        }else if(data2.equals("EventCatalog")){
            String url = api + "get-event-catalog-list?event_category_id="+data3;
            shop_screen(url);
            ac.setText(data);

            initDatePicker();
            dateButton = findViewById(R.id.fromdatepicker);
            dateButton.setText("From Date");
            dateButton = findViewById(R.id.todatepicker);
            dateButton.setText("To Date");
            getTodaysDate();


        }else if(data2.equals("HotelCatalog")){
            String url = api + "get-hotel-catalog-list?hotel_category_id="+data3;
            shop_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);

        }else if(data2.equals("BankCatalog")){
            String url = api + "get-bank-catalog-list?bank_category_id="+data3;
            shop_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);

        }else if(data2.equals("ATMCatalog")){
            String url = api + "get-atm-catalog-list?atm_category_id="+data3;
            atm_screen(url);
            ac.setText(data);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.VISIBLE);
            visible_lnr.setVisibility(View.GONE);


        }




        if(data2.equals("ShopCatalog")){
            String url = api + "display-banner?banner_type=ShopBanner&banner_category_id="+data3;
            ban(url);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);


        }else if(data2.equals("ServiceCatalog")) {
            String url = api + "display-banner?banner_type=ServiceBanner&banner_category_id="+data3;
            ban(url);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);





        } else if(data2.equals("EducationCatalog")){
            String url = api + "display-banner?banner_type=EducationBanner&banner_category_id="+data3;
            ban(url);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);



        }else if(data2.equals("TransportCatalog")){
            String url = api + "display-banner?banner_type=TransportBanner&banner_category_id="+data3;
            ban(url);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);


        }else if(data2.equals("HospitalCatalog")){
            String url = api + "display-banner?banner_type=HospitalBanner&banner_category_id="+data3;
            ban(url);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);


        }else if(data2.equals("EventCatalog")){
            String url = api + "display-banner?banner_type=EventBanner&banner_category_id="+data3;
            ban(url);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);



        }else if(data2.equals("HotelCatalog")){
            String url = api + "display-banner?banner_type=HotelBanner&banner_category_id="+data3;
            ban(url);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);



        }else if(data2.equals("BankCatalog")){
            String url = api + "display-banner?banner_type=BankBanner&banner_category_id="+data3;
            ban(url);
            gone.setVisibility(View.GONE);
            atm_visible.setVisibility(View.GONE);
            visible_lnr.setVisibility(View.VISIBLE);


        }else if(data2.equals("ATMCatalog")){

        }



        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();
                googleApiClient = getAPIClientInstance();
                if (googleApiClient != null) {
                    googleApiClient.connect();
                    requestGPSSettings();
                }
                ActivityCompat.requestPermissions( ShopsScreenFragment.this,
                        new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
//                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(ShopsScreenFragment.this);

                // method to get the location

            }
        });

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_shops1);
        btnNav.setOnNavigationItemSelectedListener(navListener);




    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            Fragment fragment = null;

            switch (id) {
                case R.id.nav_home:
//                    fragment = new Fragment_Home();
//                    break;
                    Intent in=new Intent(ShopsScreenFragment.this, Home.class);
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




                        ShopScreenModel viewmodel = new ShopScreenModel();


                        viewmodel.setId(id);
                        viewmodel.setText(title);
                        viewmodel.setImage(logo);
                        viewmodel.setText_one(address);
                        viewmodel.setText_two(rating);
                        viewmodel.setText_three(verified);
                        viewmodel.setText_four(open_time);
                        viewmodel.setCategory(data2);



                        shop_screen_model.add(viewmodel);



                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(ShopsScreenFragment.this));
                adapter =  new ShopScreenAdapter(ShopsScreenFragment.this,shop_screen_model);
                adapter.setOnItemClickListener(ShopsScreenFragment.this);
                recyclerView.setAdapter(adapter);


                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShopsScreenFragment.this) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                recyclerView.setLayoutManager(linearLayoutManager);



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
//                        loc = jsonObject.getString("location");





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
                        Log.i("wdhtouiwrytiwprugt",shop_screen_model.toString());


                        recyclerView.setLayoutManager(new LinearLayoutManager(ShopsScreenFragment.this));
                        adapter =  new ShopScreenAdapter(ShopsScreenFragment.this,shop_screen_model);
                        //adapter.setOnItemClickListener(ShopsScreenFragment.this);
                        recyclerView.setAdapter(adapter);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShopsScreenFragment.this) {
                            @Override
                            public boolean canScrollVertically() {
                                return false;
                            }
                        };
                        recyclerView.setLayoutManager(linearLayoutManager);


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

    public  void ban(String url){

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
                        view_count = jsonObject.getString("view_count");




                        Banner_model viewmodel = new Banner_model();

                        viewmodel.setImage(banner_img);

                        banner_modelList.add(viewmodel);


                        adapter1 =  new Banner_Adapter(ShopsScreenFragment.this,banner_modelList);
                        banner.setAdapter(adapter1);
                        banner.setLayoutManager(new LinearLayoutManager(ShopsScreenFragment.this, LinearLayoutManager.HORIZONTAL, false));



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
        String cat = model.getCategory();




        Intent intent = new Intent(ShopsScreenFragment.this, Home_Fragment_Class.class);
        intent.putExtra("list",cat);
        intent.putExtra("id",S_id);
        intent.putExtra("name",S_name);
        startActivity(intent);


    }


    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
                dateButton1.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }



    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
//                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Location> task) {
//                        Location location = task.getResult();
//                        if (location == null) {
                            requestNewLocationData();
//                        } else {
////                            latitudeTextView.setText(location.getLatitude() + "");
////                            longitTextView.setText(location.getLongitude() + "");
//                        }
//                    }
//                });
            } else {
                //Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(intent);
//                requestPermissions();
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latitudeTextView = (mLastLocation.getLatitude()+"");
            longitTextView = (mLastLocation.getLongitude()+"");

//            log.setText(mLastLocation.getLatitude()+"");
//            lat.setText(mLastLocation.getLongitude()+"");

            Log.i("fdsdfswf",mLastLocation.getLatitude()+"");
        }
    };

    // method to check for permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    // method to check
    // if location is enabled
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // If everything is alright then
    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }else if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED){
//                Intent intent = new Intent(ShopsScreenFragment.this,home.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(intent);



                Toast.makeText(ShopsScreenFragment.this, "Please turn on your location", Toast.LENGTH_LONG).show();
            }
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }






    private GoogleApiClient getAPIClientInstance() {
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API).build();
        return mGoogleApiClient;
    }

    private void requestGPSSettings() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setInterval(2000);
        locationRequest.setFastestInterval(500);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                status = result.getStatus();
                // Toast.makeText(Emergency.this,status.getStatusCode() , Toast.LENGTH_SHORT).show();
                Log.i("srgfergdhgd", String.valueOf(status.getStatusCode()));
                locat = String.valueOf(status.getStatusCode());
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
//                        Log.i("", "All location settings are satisfied.");
                        //   Toast.makeText(getApplication(), "GPS is already enable", Toast.LENGTH_SHORT).show();
                        break;

                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                        try {

                            status.startResolutionForResult(ShopsScreenFragment.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.e("Applicationsett", e.toString());

                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i("", "Location settings are inadequate, and cannot be fixed here. Dialog " + "not created.");
                        Toast.makeText(getApplication(), "Location settings are inadequate, and cannot be fixed here", Toast.LENGTH_SHORT).show();
                        break;


                }
            }
        });
    }


}
