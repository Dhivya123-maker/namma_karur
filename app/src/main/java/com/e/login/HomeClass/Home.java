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
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.BuildConfig;
import com.e.login.ContactusActivity;
import com.e.login.Feedback;
import com.e.login.LoginActivity;
import com.e.login.Verification.Mobile_verification;
import com.e.login.info_Class.InformationFragment;
import com.e.login.JoinwithUs;
import com.e.login.EnquiryFragment;
import com.e.login.Profile;
import com.e.login.Helpline;
import com.e.login.QrCodeFragment;
import com.e.login.R;
import com.e.login.utils.Constants;
import com.e.login.utils.PreferenceUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity implements OnConnectionFailedListener,GoogleApiClient.OnConnectionFailedListener {

    SearchView searchView;
    LinearLayout lnr;
    Button logout;
    NavigationView navigationView;
    FloatingActionButton floatingActionButton;
    String data,data1,data2,data3;
    private long pressedTime;
    TextView userName, userEmail, userId;
    String Name, Email, Id;
    public static final String TAG = "bottom_sheet";
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    GoogleSignInAccount account;

    String goo_token,goo_id,u_name,phone,user_id;
    BottomNavigationView bottomNavigationView;

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
        goo_id = i.getStringExtra("google_id");
        u_name = i.getStringExtra("name");
        phone = i.getStringExtra("phone");
        user_id = i.getStringExtra("user_id");
//        Toast.makeText(Home.this, data3, Toast.LENGTH_SHORT).show();
//        Toast.makeText(Home.this, goo_id, Toast.LENGTH_SHORT).show();

//         Toast.makeText(Home.this, user_id, Toast.LENGTH_SHORT).show();
       // Toast.makeText(Home.this, data1, Toast.LENGTH_SHORT).show();





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

        //bottomNavigationView = findViewById(R.id.bottomNavigation);

      BottomNavigationView btnNav= findViewById(R.id.bottomNavigation);
//       btnNav.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));

        btnNav.setOnNavigationItemSelectedListener(navListener);

        DrawerLayout drawerLayout = findViewById(R.id.drawerr_layout);
        lnr= findViewById(R.id.touch_drawer);

        navigationView = findViewById(R.id.nav_view1);

        lnr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

////

//        linearLayout = (LinearLayout) findViewById(R.id.profile_lnr);
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });
////
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(Home.this,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//
////
// navigationView.setCheckedItem(R.id.nav_profile);
//
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
                        Toast.makeText(Home.this, data2, Toast.LENGTH_SHORT).show();

//


                        home.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(home);
                        break;

                    case R.id.nav_JoinwithUs:
//
//                        Intent join = new Intent(Home.this, JoinwithUs.class);
//                       join.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                        startActivity(join);
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


//        setting home fragments as main fragment as well as default fragments
      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout_home,new Fragment_Home()).commit();


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

//          #3  to create fragements for each

            switch  (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new Fragment_Home();

                    break;
                case R.id.nav_tree:
                    selectedFragment = new InformationFragment();

                    break;
                case R.id.nav_qr:
                    selectedFragment = new QrCodeFragment();

                    break;
                case R.id.nav_profilee:
                    selectedFragment = new Helpline();

                    break;
                case R.id.nav_notifications:
                    selectedFragment = new EnquiryFragment();

                    break;


            }

//           #4  begin transaction
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout_home,selectedFragment).commit();
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
                params.put("Authorization", "Bearer " + PreferenceUtils.getToken1(Home.this));



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
