package com.e.login.Offers;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class Offer_One_Adapter extends RecyclerView.Adapter<Offer_One_Adapter.ViewHolder> {



    List<Offer_One_Model> offerOneModelList;
    private Context context;


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



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),Top_Offer_Activity.class);
                    view.getContext().startActivity(intent);
                }
            });



            // Time is in millisecond so 50sec = 50000
            // countdown Interveal is 1sec = 1000
            new CountDownTimer(50000, 1000) {
                public void onTick(long millisUntilFinished) {
                    // Used for formatting digit to be in 2 digits only
                    NumberFormat f = new DecimalFormat("00");
                    long hour = (millisUntilFinished / 3600000) % 24;
                    long min = (millisUntilFinished / 60000) % 60;
                    long sec = (millisUntilFinished / 1000) % 60;
                    txt.setText(f.format(hour) + "  :  " +   f.format(min) + "  :  " +   f.format(sec));
                }

                // When the task is over it will print 00:00:00 there
                public void onFinish() {
                    txt.setText("00 : 00 : 00");
                }
            }.start();
        }


    }



    }


