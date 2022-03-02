package com.e.login.JobsClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.R;

import java.util.List;

public class View_Morejobs_Adapter extends RecyclerView.Adapter<View_Morejobs_Adapter.ViewHolder> {



    List<View_MoreJob_Model> view_moreJob_models;
    private Context context;


    public View_Morejobs_Adapter(Context context, List<View_MoreJob_Model> view_moreJob_models) {
        this.context = context;
        this.view_moreJob_models =view_moreJob_models;
    }

    @NonNull
    @Override
    public View_Morejobs_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jobs_view_more,parent,false);


        return new View_Morejobs_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(view_moreJob_models.get(position).getTxt());

//        holder.img.setImageResource(R.drawable.india);
        Glide.with(context)
                .load(view_moreJob_models.get(position).getImg())
                .into(holder.img);

    }





    // total number of rows
    @Override
    public int getItemCount() {

        return view_moreJob_models.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        LinearLayout lnr;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.view_more_india);
            lnr = itemView.findViewById(R.id.view_more_linear);

            textView = itemView.findViewById(R.id.view_more_india_txt);




        }



    }



}
