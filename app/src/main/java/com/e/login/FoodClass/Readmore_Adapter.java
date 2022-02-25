package com.e.login.FoodClass;

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

public class Readmore_Adapter extends RecyclerView.Adapter<Readmore_Adapter.ViewHolder> {



    List<Readmore_Model> readmoreModelList;
    private Context context;


    public Readmore_Adapter(Context context, List<Readmore_Model> readmoreModelList) {
        this.context = context;
        this.readmoreModelList = readmoreModelList;
    }

    @NonNull
    @Override
    public Readmore_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_twenty_seven,parent,false);


        return new Readmore_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(readmoreModelList.get(position).getText());

        holder.img.setImageResource(R.drawable.ic_baseline_arrow_back_ios_24);
        holder.img1.setImageResource(R.drawable.food_banner);
        holder.img2.setImageResource(R.drawable.heartt);
        holder.img3.setImageResource(R.drawable.comments);
        holder.img4.setImageResource(R.drawable.sharee);
    }



    // total number of rows
    @Override
    public int getItemCount() {

        return readmoreModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2,img3,img4;
        TextView textView;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.back_img);
            img1 = itemView.findViewById(R.id.banner_img);
            img2 = itemView.findViewById(R.id.fav_read);
            img3 = itemView.findViewById(R.id.comments_read);
            img4 = itemView.findViewById(R.id.comments_share);


            textView = itemView.findViewById(R.id.read_txt);


        }



    }


}