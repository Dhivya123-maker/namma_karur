package com.e.login.BusTimeClass;


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
import com.e.login.SmallBusClass.SmallBusActivity;

import java.util.List;

public class BusTimeAdapter extends RecyclerView.Adapter<com.e.login.BusTimeClass.BusTimeAdapter.ViewHolder> {



    List<BusTimeModel> busTimeModelList;
    private Context context;


    public BusTimeAdapter(Context context, List<BusTimeModel> busTimeModelList) {
        this.context = context;
        this.busTimeModelList= busTimeModelList;
    }

    @NonNull
    @Override
    public com.e.login.BusTimeClass.BusTimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reycler_row_nine,parent,false);


        return new com.e.login.BusTimeClass.BusTimeAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.textView.setText(busTimeModelList.get(position).getText());
        holder.textView1.setText(busTimeModelList.get(position).getText_one());
        holder.textView2.setText(busTimeModelList.get(position).getText_two());

        holder.img.setImageResource(R.drawable.bus_green);
    }




    // total number of rows
    @Override
    public int getItemCount() {

        return 2;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView, textView1,textView2;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.bus_img);

//            textView = itemView.findViewById(R.id.bus_txt_one);
            textView1 = itemView.findViewById(R.id.bus_txt_two);
            textView2 = itemView.findViewById(R.id.bus_txt_three);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), SmallBusActivity.class);
                    view.getContext().startActivity(intent);
                }
            });
//
        }



    }


}