package com.e.login.Blog_Class;

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
import com.e.login.R;

import java.util.List;

public class Quotes_Adapter extends RecyclerView.Adapter<Quotes_Adapter.ViewHolder> {



    List<Quotes_model> quotesModelList;
    private Context context;


    public Quotes_Adapter(Context context, List<Quotes_model> quotesModelList) {
        this.context = context;
        this.quotesModelList = quotesModelList;
    }

    @NonNull
    @Override
    public Quotes_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotes_recycle,parent,false);


        return new Quotes_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull Quotes_Adapter.ViewHolder holder, int position) {
        holder.textView.setText(quotesModelList.get(position).getTxt());
        holder.txt1.setText(quotesModelList.get(position).getTxt1());



//        holder.img.setImageResource(R.drawable.ambulance);
        Glide.with(context)
                .load(quotesModelList.get(position).getImg())
                .into(holder.img);
    }




    // total number of rows
    @Override
    public int getItemCount() {

        return quotesModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1;
        TextView textView,txt1;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.quotes_img);
            img1 = itemView.findViewById(R.id.eye_iconn);


            textView = itemView.findViewById(R.id.quotes_txt_one);
            txt1 = itemView.findViewById(R.id.quotes_txt_two);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),Quotes_Catalog.class);
                    view.getContext().startActivity(intent);
                }
            });





        }



    }}
