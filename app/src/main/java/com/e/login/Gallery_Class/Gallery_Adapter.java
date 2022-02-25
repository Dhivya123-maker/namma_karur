package com.e.login.Gallery_Class;

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

import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;

import java.util.List;

public class Gallery_Adapter extends RecyclerView.Adapter<Gallery_Adapter.ViewHolder> {



    List<Gallery_Model> galleryModelList;
    private Context context;


    public Gallery_Adapter(Context context, List<Gallery_Model> galleryModelList) {
        this.context = context;
        this.galleryModelList = galleryModelList;
    }

    @NonNull
    @Override
    public Gallery_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fourty_one,parent,false);


        return new Gallery_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.img.setImageResource(R.drawable.mahal_one);
    }



    // total number of rows
    @Override
    public int getItemCount() {

        return 4;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.mahal_image);






        }



    }



}
