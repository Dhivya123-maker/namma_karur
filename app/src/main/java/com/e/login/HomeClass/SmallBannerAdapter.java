package com.e.login.HomeClass;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.R;
import com.e.login.ShopscreenClass.Banner_model;

import java.util.List;

public class SmallBannerAdapter extends RecyclerView.Adapter<SmallBannerAdapter.ViewHolder> {



    List<SmallbannerModel> smallbannerModelList;
    private Context context;


    public SmallBannerAdapter(Context context, List<SmallbannerModel> smallbannerModelList) {
        this.context = context;
        this.smallbannerModelList = smallbannerModelList;
    }

    @NonNull
    @Override
    public SmallBannerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.small_banner,parent,false);


        return new SmallBannerAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context)
                .load(smallbannerModelList.get(position).getImg())
                .into(holder.img);
    }





    // total number of rows
    @Override
    public int getItemCount() {

        return smallbannerModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.ban);








        }



    }


}