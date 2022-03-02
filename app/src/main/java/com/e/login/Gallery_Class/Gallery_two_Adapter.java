package com.e.login.Gallery_Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.Facility_Class.Facility_Adapter;
import com.e.login.R;

import java.util.List;

public class Gallery_two_Adapter extends RecyclerView.Adapter<Gallery_two_Adapter.ViewHolder> {



    List<Gallery_two_Model> galleryTwoModelList;
    private Context context;


    public Gallery_two_Adapter(Context context, List<Gallery_two_Model> galleryTwoModelList) {
        this.context = context;
        this.galleryTwoModelList = galleryTwoModelList;
    }

    @NonNull
    @Override
    public Gallery_two_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_recycle,parent,false);


        return new Gallery_two_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.img.setImageResource(R.drawable.mahal_one);
//        Glide.with(context)
//                .load(galleryTwoModelList.get(position).getImg())
//                .into(holder.img);

    }



    // total number of rows
    @Override
    public int getItemCount() {

        return galleryTwoModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;

        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;

        ViewHolder(View itemView) {
            super(itemView);

//            recyclerView = itemView.findViewById(R.id.gallery_recycle);
//            txt = itemView.findViewById(R.id.cat_text);
//            recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
//
//            adapter =  new Gallery_two_Adapter(context.getApplicationContext(),galleryTwoModelList);
//            recyclerView.setAdapter(adapter);
//
//





        }



    }



}
