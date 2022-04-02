package com.e.login.HomeClass;


import android.content.Context;
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

public class Slider_Top_Adapter extends RecyclerView.Adapter<Slider_Top_Adapter.ViewHolder> {



    List<BannerModel> bannerModelList;
    private Context context;


    public Slider_Top_Adapter(Context context, List<BannerModel> bannerModelList) {
        this.context = context;
        this.bannerModelList= bannerModelList ;
    }

    @NonNull
    @Override
    public Slider_Top_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item,parent,false);


        return new Slider_Top_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(bannerModelList.get(position).getImg())
                .into(holder.img);
    }




    // total number of rows
    @Override
    public int getItemCount() {

        return bannerModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image_view);






        }



    }


}