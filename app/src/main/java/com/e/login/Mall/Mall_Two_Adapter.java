package com.e.login.Mall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.R;

import java.util.List;

public class Mall_Two_Adapter extends RecyclerView.Adapter<Mall_Two_Adapter.ViewHolder> {



    List<Mall_two_Model> mall_two_modelList;
    private Context context;


    public Mall_Two_Adapter(Context context, List<Mall_two_Model> mall_two_modelList) {
        this.context = context;
        this.mall_two_modelList=mall_two_modelList;
    }

    @NonNull
    @Override
    public Mall_Two_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_twenty_two,parent,false);


        return new Mall_Two_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.img.setImageResource(R.drawable.thi);
    }


    // total number of rows
    @Override
    public int getItemCount() {

        return 5;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.shops_scroll_mall);


        }


    }



}


