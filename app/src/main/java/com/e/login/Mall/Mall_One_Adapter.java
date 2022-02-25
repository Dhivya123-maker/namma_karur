package com.e.login.Mall;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.R;

import java.util.List;

public class Mall_One_Adapter extends RecyclerView.Adapter<Mall_One_Adapter.ViewHolder> {



    List<Mall_One_Model> mall_one_modelList;
    private Context context;


    public Mall_One_Adapter(Context context, List<Mall_One_Model> mall_one_modelList) {
        this.context = context;
        this.mall_one_modelList=mall_one_modelList;
    }


    @NonNull
    @Override
    public Mall_One_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_twenty_one,parent,false);


        return new Mall_One_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(mall_one_modelList.get(position).getText());

        holder.img.setImageResource(R.drawable.food_img);
    }


    // total number of rows
    @Override
    public int getItemCount() {

        return 3;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.mall_one_img);

            textView = itemView.findViewById(R.id.malls_one_txt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),Mall_Home_Fragment.class);
                    view.getContext().startActivity(intent);
                }
            });

        }


    }



}


