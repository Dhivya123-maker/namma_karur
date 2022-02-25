package com.e.login.SmallBusClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.BusTimeClass.BusTimeModel;
import com.e.login.R;
import com.e.login.SmallBusClass.SmallBusActivity;

import java.util.List;

public class SmallBusAdapter extends RecyclerView.Adapter<com.e.login.SmallBusClass.SmallBusAdapter.ViewHolder> {



    List<SmallBusModel> smallBusModelList;
    private Context context;


    public SmallBusAdapter(Context context, List<SmallBusModel> smallBusModelList) {
        this.context = context;
        this.smallBusModelList= smallBusModelList;
    }

    @NonNull
    @Override
    public com.e.login.SmallBusClass.SmallBusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_ten,parent,false);


        return new com.e.login.SmallBusClass.SmallBusAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(smallBusModelList.get(position).getText());


        Glide.with(context)
                .load(smallBusModelList.get(position).getImage())
                .into(holder.img);
    }





    // total number of rows
    @Override
    public int getItemCount() {

        return smallBusModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.green_bus);

           textView = itemView.findViewById(R.id.small_bus_txt);




        }



    }


}