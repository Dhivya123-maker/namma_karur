package com.e.login.Blog_Class;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;
import com.e.login.ShopClass.ShopClassAdapter;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Blog_One_Adapter extends RecyclerView.Adapter<Blog_One_Adapter.ViewHolder> {



    List<Blog_One_Model> blogOneModelList;
    private Context context;
    String Success;
    String msg;

    public static ShopClassAdapter.OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ShopClassAdapter.OnItemClickListener listener){

        mListener = listener;

    }

    public Blog_One_Adapter(Context context, List<Blog_One_Model> blogOneModelList) {
        this.context = context;
        this.blogOneModelList = blogOneModelList;
    }

    @NonNull
    @Override
    public Blog_One_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_sixty_three,parent,false);


        return new Blog_One_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(blogOneModelList.get(position).getTxt());
        holder.textView1.setText(blogOneModelList.get(position).getTxt1());


        Glide.with(context)
                .load(blogOneModelList.get(position).getImg())
                .into(holder.img);

    }






    // total number of rows
    @Override
    public int getItemCount() {

        return blogOneModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView, textView1;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.latest_img);

            textView = itemView.findViewById(R.id.latest_txt);
            textView1 = itemView.findViewById(R.id.latest_txt_one);

            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                      int position = getAdapterPosition();
                    String link = blogOneModelList.get(position).getLink();
                    String id = blogOneModelList.get(position).getId();

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    context.startActivity(browserIntent);

                   v_count();




                }
            });

//            textView1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (mListener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.onItemClick(position);
//                        }
//                    }
//
//
//                }
//            });





        }



    }
    public  void v_count(){
        String url = "http://nk.inevitabletech.email/public/api/blog-view-count-increment?blog_id=1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {
                Log.i("hgtviuy",response.toString());





                try {
                     Success = response.getString("success");
                     msg = response.getString("messgae");

                     JSONObject jsonObject = response.getJSONObject("data");

                    if(Success.equals("true")){

                            Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();



                    }else {
                        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
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
                params.put("Accept", "application/json");
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(context.getApplicationContext()));
                params.put("Authorization", "Bearer  " + PreferenceUtils.getToken1(context.getApplicationContext()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.add(jsonObjectRequest);





    }


}