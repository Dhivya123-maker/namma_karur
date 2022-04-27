package com.e.login.MoreClass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.BaseApi.Api;
import com.e.login.Blog_Class.BlogActivity;
import com.e.login.BloodClass.Blood_Fragment;
import com.e.login.ChatFeature;
import com.e.login.Help_Class.Helpline;
import com.e.login.HomeClass.CategoryAdapter;
import com.e.login.HomeClass.CategoryAdapter_more;
import com.e.login.HomeClass.CategoryModel;
import com.e.login.HomeClass.Fragment_Home;
import com.e.login.JobsClass.Jobs;
import com.e.login.NewsClass.NewsActivity;
import com.e.login.Offers.OfferActivity;
import com.e.login.QrCodeFragment;
import com.e.login.R;
import com.e.login.ShopClass.ShopScreen_Class;
import com.e.login.Shopping_Activity;
import com.e.login.info_Class.InformationFragment;
import com.e.login.utils.PreferenceUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoreActivity extends AppCompatActivity implements CategoryAdapter_more.OnItemClickListener{
    String api;
    String data,data1;
    RecyclerView recyclerView;
    List<CategoryModel> cat;
    CategoryAdapter_more CAtAdapter;
    String cat_name = null,name = null, image = null,id = null,URL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);


        Api a = new Api();
        api = a.getBASE_URL();


        Intent intent =getIntent();
        data = intent.getStringExtra("token");
        Intent i1 = getIntent();
        data1 = i1.getStringExtra("id");

        recyclerView = findViewById(R.id.more_screen);
        cat = new ArrayList<>();


        category();


        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView_shops);
        btnNav.setOnNavigationItemSelectedListener(navListener);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @SuppressLint("NonConstantResourceId")
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
                    fragment = new ChatFeature();
                    break;


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).commit();

            return true;
        }
    };

    private void category() {


        String url = api+"get-all-Main-Menu";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {



                try {
                    JSONArray res = response.getJSONArray("data");


                    for (int i=20;i<res.length();i++){


                        JSONObject jsonObject = res.getJSONObject(i);

                        id = jsonObject.getString("id");
                        name = jsonObject.getString("name");
                        image = jsonObject.getString("logo");
                        cat_name = jsonObject.getString("category_name");
                        URL = jsonObject.getString("url");


                        CategoryModel model = new CategoryModel();

                        model.setImg(image);
                        model.setName(name);
                        model.setId(id);
                        model.setCat_name(cat_name);
                        model.setUrl(URL);

                        cat.add(model);



                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                GridLayoutManager layoutManager=new GridLayoutManager(MoreActivity.this,4){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };



                    recyclerView.setLayoutManager(layoutManager);
                    CAtAdapter =  new CategoryAdapter_more(MoreActivity.this,cat);
                    recyclerView.setAdapter(CAtAdapter);
                    CAtAdapter.setOnItemClickListener(MoreActivity.this);





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

                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(MoreActivity.this));


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MoreActivity.this);
        requestQueue.add(jsonObjectRequest);


    }


    @Override
    public void onItemClick(int position) {

        CategoryModel model = cat.get(position);

        String url = model.getUrl();

        Intent i = new Intent(MoreActivity.this,Shopping_Activity.class);
        i.putExtra("url",url);
        startActivity(i);

    }
}