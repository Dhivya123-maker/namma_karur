package com.e.login.ShopscreenClass;


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

public class Banner_Adapter extends RecyclerView.Adapter<Banner_Adapter.ViewHolder> {



    List<Banner_model> banner_modelList;
    private Context context;


    public Banner_Adapter(Context context, List<Banner_model> banner_modelList) {
        this.context = context;
        this.banner_modelList= banner_modelList;
    }

    @NonNull
    @Override
    public Banner_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner,parent,false);


        return new Banner_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context)
                .load(banner_modelList.get(position).getImage())
                .into(holder.img);
    }





    // total number of rows
    @Override
    public int getItemCount() {

        return banner_modelList.size();

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