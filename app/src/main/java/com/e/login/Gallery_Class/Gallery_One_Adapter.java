package com.e.login.Gallery_Class;

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
import com.e.login.R;

import java.util.List;

public class Gallery_One_Adapter extends RecyclerView.Adapter<Gallery_One_Adapter.ViewHolder> {



    List<Gallery_One_Model> galleryOneModelList;
    private Context context;


    public Gallery_One_Adapter(Context context, List<Gallery_One_Model> galleryOneModelList) {
        this.context = context;
        this.galleryOneModelList = galleryOneModelList;
    }

    @NonNull
    @Override
    public Gallery_One_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fourty_two,parent,false);


        return new Gallery_One_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.img.setImageResource(R.drawable.mahal_one);
        Glide.with(context)
                .load(galleryOneModelList.get(position).getImg())
                .into(holder.img);
    }




    // total number of rows
    @Override
    public int getItemCount() {

        return galleryOneModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt,txt1;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.mahal_two_image);
//            txt = itemView.findViewById(R.id.category_txt);
//            txt1 = itemView.findViewById(R.id.view_all_txt);
//
//



        }



    }



}
