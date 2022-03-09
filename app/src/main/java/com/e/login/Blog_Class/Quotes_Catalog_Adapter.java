package com.e.login.Blog_Class;

import android.content.Context;
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
import com.e.login.R;

import java.util.List;

public class Quotes_Catalog_Adapter extends RecyclerView.Adapter<Quotes_Catalog_Adapter.ViewHolder> {



    List<Quotes_Catalog_Model> quotesCatalogModelList;
    private Context context;


    public Quotes_Catalog_Adapter(Context context, List<Quotes_Catalog_Model> quotesCatalogModelList) {
        this.context = context;
        this.quotesCatalogModelList = quotesCatalogModelList;
    }

    @NonNull
    @Override
    public Quotes_Catalog_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotes_catalog_recycle,parent,false);


        return new Quotes_Catalog_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(quotesCatalogModelList.get(position).getImg())
                .into(holder.img);
    }





    // total number of rows
    @Override
    public int getItemCount() {

        return quotesCatalogModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.quotes_catalog_img);




        }



    }

}
