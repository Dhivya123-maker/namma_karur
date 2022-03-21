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

import com.bumptech.glide.Glide;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.ProductsFragmentClass.ProductsAdapter;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_recycle,parent,false);

        
        return new Gallery_Adapter.ViewHolder(view);


    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context)
                .load(galleryModelList.get(position).getImg1())
                .into(holder.img1);
        Glide.with(context)
                .load(galleryModelList.get(position).getImd2())
                .into(holder.img2);
        Glide.with(context)
                .load(galleryModelList.get(position).getImd3())
                .into(holder.img3);
        Glide.with(context)
                .load(galleryModelList.get(position).getImd4())
                .into(holder.img4);

        holder.txt.setText(galleryModelList.get(position).getTxt());



    }




    // total number of rows
    @Override
    public int getItemCount() {

        return galleryModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img1,img2,img3,img4;
        TextView txt;



        ViewHolder(View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.mahal_one_image);
            img2 = itemView.findViewById(R.id.mahal_two_image);
            img3 = itemView.findViewById(R.id.mahal_three_image);
            img4 = itemView.findViewById(R.id.mahal_four_image);
            txt = itemView.findViewById(R.id.categoryy);







        }



    }



}
