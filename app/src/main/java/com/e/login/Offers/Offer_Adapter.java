package com.e.login.Offers;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;

import java.util.List;

public class Offer_Adapter extends RecyclerView.Adapter<Offer_Adapter.ViewHolder> {



    List<Offer_Model> offerModelList;
    private Context context;


    public Offer_Adapter(Context context, List<Offer_Model> offerModelList) {
        this.context = context;
        this.offerModelList= offerModelList;
    }

    @NonNull
    @Override
    public Offer_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_twenty_eight,parent,false);


        return new Offer_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        Glide.with(context)
                .load(offerModelList.get(position).getImg())
                .into(holder.img);
    }


    // total number of rows
    @Override
    public int getItemCount() {

        return offerModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.offers_one);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),Top_Offer_Activity.class);
                    view.getContext().startActivity(intent);
                }
            });



        }



    }


}