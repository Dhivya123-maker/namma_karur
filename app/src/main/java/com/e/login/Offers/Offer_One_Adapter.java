package com.e.login.Offers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.e.login.BaseApi.Api;
import com.e.login.EducationClass.EducationAdapter;
import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Offer_One_Adapter extends RecyclerView.Adapter<Offer_One_Adapter.ViewHolder> {



    List<Offer_One_Model> offerOneModelList;
    private Context context;
    String api;
    String c_id = null, c_name = null, c_image = null, c_des = null,c_start,c_end;

    Offer_One_Adapter adapter1;
    RecyclerView recyclerView1;

//
//    public static Offer_One_Adapter.OnItemClickListener mListener;
//
//
//
//
//    public interface OnItemClickListener{
//        void onItemClick(int position);
//    }
//
//    public void setOnItemClickListener(Offer_One_Adapter.OnItemClickListener listener){
//
//        mListener = listener;
//
//    }

    public Offer_One_Adapter(Context context, List<Offer_One_Model> offerOneModelList) {
        this.context = context;
        this.offerOneModelList= offerOneModelList;
    }

    @NonNull
    @Override
    public Offer_One_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_twenty_nine,parent,false);


        return new Offer_One_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(offerOneModelList.get(position).getImg())
                .into(holder.img);
        holder.txt.setText(offerOneModelList.get(position).getTxt());
        holder.txt1.setText(offerOneModelList.get(position).getTxt1());
    }


    // total number of rows
    @Override
    public int getItemCount() {

        return offerOneModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt,txt1;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.close_offer_img);
            txt = itemView.findViewById(R.id.timer_txt);
            txt1 = itemView.findViewById(R.id.hrs_txt);


//            Api a = new Api();
//            api = a.getBASE_URL();
//
//
//            offerOneModelList = new ArrayList<>();
//            recyclerView1 = itemView.findViewById(R.id.close_offer_screen);
//
//            String url = api + "offer-home-page";
//
//
//            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                @SuppressLint("CheckResult")
//                @Override
//                public void onResponse(JSONObject response) {
//
//
//
//                    try {
//                        JSONObject res = response.getJSONObject("data");
//
//                        JSONArray top,close,only;
//
//
//                        close =  res.getJSONArray("closing");
//
//
//
//
//                        for (int i = 0; i < close.length(); i++) {
//
//                            JSONObject jsonObject = close.getJSONObject(i);
//
//
//
//                            c_id = jsonObject.getString("id");
//                            c_name = jsonObject.getString("title");
//                            c_image = jsonObject.getString("image");
//                            c_des = jsonObject.getString("description");
//                            c_start = jsonObject.getString("start_date");
//                            c_end = jsonObject.getString("end_date");
//
//
//                            Offer_One_Model viewmodel = new Offer_One_Model();
//
//
//                            new CountDownTimer(50000, 1000) {
//                                @SuppressLint("SetTextI18n")
//                                public void onTick(long millisUntilFinished) {
//
//                                    NumberFormat f = new DecimalFormat("00");
//                                    long hour = (millisUntilFinished / 3600000) % 24;
//                                    long min = (millisUntilFinished / 60000) % 60;
//                                    long sec = (millisUntilFinished / 1000) % 60;
//                                    txt.setText(f.format(hour) + "  :  " +   f.format(min) + "  :  " +   f.format(sec));
////                                viewmodel.setTxt(c_end);
//                                }
//
//                                // When the task is over it will print 00:00:00 there
//                                public void onFinish() {
//                                    txt.setText("00 : 00 : 00");
//                                }
//                            }.start();
//
//
//
//                            viewmodel.setImg(c_image);
////                        viewmodel.setTxt(c_end);
//                            viewmodel.setTxt1(" Hrs     Min     sec");
//                            viewmodel.setId(c_id);
//
//                            offerOneModelList.add(viewmodel);
//
//                        }
//
//
//
//
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//
//
//                    recyclerView1.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
//
//                    adapter1 =  new Offer_One_Adapter(context.getApplicationContext(),offerOneModelList);
//                    recyclerView1.setAdapter(adapter1);
//                    recyclerView1.setLayoutManager(new LinearLayoutManager(context.getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//
//
//
//                }
//
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                }
//            }){
//                @Override
//                protected Map<String,String> getParams(){
//                    Map<String,String> params = new HashMap<String, String>();
//
//
//
//                    return params;
//                }
//
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String,String> params = new HashMap<String, String>();
//
//                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(context.getApplicationContext()));
//                    return params;
//                }
//            };
//
//            RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
//            requestQueue.add(jsonObjectRequest);
//





            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    int position = getAdapterPosition();
                    String id = offerOneModelList.get(position).getId();
                    String list = offerOneModelList.get(position).getCat();

                    Intent intent = new Intent(view.getContext(), Top_Offer_Activity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("list", list);
                    view.getContext().startActivity(intent);



                }
            });

        }
    }}









