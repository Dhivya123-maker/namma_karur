package com.e.login.Offers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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

import com.e.login.R;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
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

        try {


            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
            Date firstDate = null;


            firstDate = sdf.parse(offerOneModelList.get(position).getEnd_date());



            Date date = new Date();



            long diff =  firstDate.getTime() - date.getTime();

//            TimeUnit time = TimeUnit.DAYS;
//            long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
//            System.out.println("The difference in days is : "+date);

//            int time = now.getSecond();

//            Log.i("fdf", String.valueOf(diff));

            new CountDownTimer(diff, 1000) {
                @SuppressLint("SetTextI18n")
                public void onTick(long millisUntilFinished) {

                    NumberFormat f = new DecimalFormat("00");
                    long hour = (millisUntilFinished / 3600000) % 24;
                    long min = (millisUntilFinished / 60000) % 60;
                    long sec = (millisUntilFinished / 1000) % 60;
                    holder.txt.setText(f.format(hour) + "  :  " +   f.format(min) + "  :  " +   f.format(sec));
//                                viewmodel.setTxt(c_end);
                }

                // When the task is over it will print 00:00:00 there
                public void onFinish() {
                    holder.txt.setText("00 : 00 : 00");
                }
            }.start();

        } catch (ParseException e) {
            e.printStackTrace();
        }
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

        @RequiresApi(api = Build.VERSION_CODES.O)
        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.close_offer_img);
            txt = itemView.findViewById(R.id.timer_txt);
            txt1 = itemView.findViewById(R.id.hrs_txt);


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









