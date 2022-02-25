package com.e.login.FoodClass;

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

import com.e.login.BusTimeClass.BusTimeModel;
import com.e.login.R;
import com.e.login.SmallBusClass.SmallBusActivity;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<com.e.login.FoodClass.FoodAdapter.ViewHolder> {



    List<FoodModel> foodModelList;
    private Context context;


    public FoodAdapter(Context context, List<FoodModel> foodModelList) {
        this.context = context;
        this.foodModelList= foodModelList;
    }

    @NonNull
    @Override
    public com.e.login.FoodClass.FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_twelve,parent,false);


        return new com.e.login.FoodClass.FoodAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(foodModelList.get(position).getText());
        holder.btn.setText(foodModelList.get(position).getButton());

        holder.img.setImageResource(R.drawable.food_one);
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
        Button btn;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.food_img);

            textView = itemView.findViewById(R.id.food_txt);
            btn = itemView.findViewById(R.id.food_btn);



        }



    }


}