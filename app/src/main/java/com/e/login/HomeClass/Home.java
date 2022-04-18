package com.e.login.HomeClass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.e.login.BuildConfig;
import com.e.login.ContactusActivity;
import com.e.login.EnquiryFragment;
import com.e.login.Feedback;
import com.e.login.LoginActivity;
import com.e.login.NewsClass.Karur;
import com.e.login.Post_Fragment;
import com.e.login.Verification.Edit;
import com.e.login.info_Class.InformationFragment;
import com.e.login.Profile;
import com.e.login.Help_Class.Helpline;
import com.e.login.QrCodeFragment;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity implements OnConnectionFailedListener,GoogleApiClient.OnConnectionFailedListener {


    LinearLayout lnr;
    Button logout;
    NavigationView navigationView;
    String data,data1,data2,data3;
    private long pressedTime;

    public static final String TAG = "bottom_sheet";
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    GoogleSignInAccount account;
    String name,email;
    ImageView profile;
    TextView namee,mail;

    String goo_token,goo_id,u_name,phone,user_id;
    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);


        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    logout();


            }
        });


        Intent i = getIntent();
        data = i.getStringExtra("token");
        data1 = i.getStringExtra("id");
       data2 = i.getStringExtra("user_name");
        data3 = i.getStringExtra("email");
        goo_token = i.getStringExtra("goo_token");
        goo_id = i.getStringExtra("goo_id");
        u_name = i.getStringExtra("name");
        phone = i.getStringExtra("phone");
        user_id = i.getStringExtra("user_id");





        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



//
//        clickEvents();


//
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                com.e.login.fragment_dialog.BottomSheetFragment fragment = new BottomSheetFragment();
//                fragment.show(getSupportFragmentManager(), TAG);
//            }
//        });

        imag();

      BottomNavigationView btnNav= findViewById(R.id.bottomNavigation);


        btnNav.setOnNavigationItemSelectedListener(navListener);

        DrawerLayout drawerLayout = findViewById(R.id.drawerr_layout);
        lnr= findViewById(R.id.touch_drawer);

        navigationView = findViewById(R.id.nav_view1);

        View hView =  navigationView.inflateHeaderView(R.layout.header);
        profile = (ImageView)hView.findViewById(R.id.head_img);
        namee = (TextView)hView.findViewById(R.id.name_txt);
        mail = (TextView)hView.findViewById(R.id.mail_txtt);


        lnr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(Home.this,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id = item.getItemId();


                switch (id) {

                    case R.id.nav_profile:

                        Intent home = new Intent(Home.this, Profile.class);
                        home.putExtra("token",data);
                        home.putExtra("id",data1);
                        home.putExtra("user_id",user_id);
                        home.putExtra("user_name",data2);
                        home.putExtra("email",data3);



                        home.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(home);
                        break;

                    case R.id.nav_JoinwithUs:

                        break;



                    case R.id.nav_Feedback:

                        Intent fb = new Intent(Home.this, Feedback.class);
                        fb.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(fb);
                        break;
                    case R.id.nav_Contact:

                        Intent ct = new Intent(Home.this, ContactusActivity.class);
                        ct.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(ct);
                        break;

                    case R.id.nav_Share_app:

                      Clicked();
                      break;
                    case R.id.nav_RateUs:

                        launch();


                        break;
                }


                return true;

            }

        });


      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout_homee,new Fragment_Home()).commit();


    }

  public  void Clicked()
    {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Namma Karur");
        String shareMessage= "\nகருரை பற்றிய அனைத்து\nதகவல்களையும் ஒரே இடத்தில்\nதெரிந்துகொள்ள Download Now\n";
        sendIntent.setType("text/plain");
        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        Intent.createChooser(sendIntent,"Share via");
        startActivity(sendIntent);
    }

    private void launch() {
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
        Intent myAppLink= new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLink);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " unable to find Namma Karur app", Toast.LENGTH_LONG).show();
        }
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;



            switch  (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new Fragment_Home();
                    lnr.setClickable(true);
                    lnr.setVisibility(View.VISIBLE);

                    break;
                case R.id.nav_tree:
                    selectedFragment = new InformationFragment();
                    lnr.setClickable(false);
                    lnr.setVisibility(View.GONE);
                    break;
                case R.id.nav_qr:
                    selectedFragment = new QrCodeFragment();
                    lnr.setClickable(false);
                    lnr.setVisibility(View.GONE);
                    break;
                case R.id.nav_profilee:
                    selectedFragment = new Helpline();
                    lnr.setClickable(false);
                    lnr.setVisibility(View.GONE);

                    break;

                case R.id.nav_notifications:
                  selectedFragment = new EnquiryFragment();
                    lnr.setClickable(false);
                    lnr.setVisibility(View.GONE);
                  break;
            }


            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout_homee,selectedFragment).commit();
            return true;
        }
    };
//    private void clickEvents() {
//
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Home.this, Home.class);
//                startActivity(intent);
//
//
//            }
//        });

    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            new AlertDialog.Builder(Home.this).setIcon(R.drawable.ic_baseline_warning_24)
                    .setMessage("Are you sure want to exit")
                    .setNegativeButton(android.R.string.no,null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                           Home.super.onBackPressed();
                          finishAffinity();
                        }
                    }).create().show();

        }
        else  {

            Toast.makeText(Home.this, "Press back to exit", Toast.LENGTH_SHORT).show();

        }
        pressedTime = System.currentTimeMillis();





    }
    public  void logout(){

        String JSON_URL = "http://nk.inevitabletech.email/public/api/logout";
//        String JSON_URL = "http://eagle.spksystems.in/public/api/logout";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {

//
//                Log.i("0000000",response.toString());
//                Toast.makeText(Home.this, response.toString(), Toast.LENGTH_SHORT).show();

                try {


                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    if(Success.equals("true")){
//                        Log.i("123",msg);
//                        Toast.makeText(Home.this, msg, Toast.LENGTH_SHORT).show();
                        finishAffinity();
                        PreferenceUtils.saveid(null,Home.this);
                        PreferenceUtils.saveToken(null,Home.this);


                        String id = null;

                        SharedPreferences settings = getSharedPreferences("YOUR_PREF_NAM", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("id", id);
                        editor.commit();




                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(Home.this, msg, Toast.LENGTH_SHORT).show();
                    }


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
                params.put("Authorization","Bearer "+ PreferenceUtils.getToken(Home.this));



                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Home.this);
        requestQueue.add(jsonObjectRequest);
    }


    public void imag(){

        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-profile-details";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint({"CheckResult", "ResourceType"})
            @Override
            public void onResponse(JSONObject response) {


                try {



                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    JSONObject jsonObject = response.getJSONObject("data");

                    image = jsonObject.getString("image");
                    name = jsonObject.getString("name");
                    email = jsonObject.getString("email");
                    Log.i("weloiht9urocty8d3w",image);



                    if(Success.equals("true")){
                        Log.i("123",msg);


                        profile.setImageURI(Uri.parse(image));
                        namee.setText(name);
                        mail.setText(email);



                        Log.i("akfjhowiuejtfhwoi",profile.toString());




                    }else{
                        Log.i("1234",msg);
                        Toast.makeText(Home.this, msg, Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
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
                params.put("Authorization", "Bearer " + PreferenceUtils.getToken(Home.this));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Home.this);
        requestQueue.add(jsonObjectRequest);

}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
