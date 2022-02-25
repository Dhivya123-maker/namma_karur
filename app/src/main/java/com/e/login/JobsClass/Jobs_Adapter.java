package com.e.login.JobsClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.GovtClass.GovtModel;
import com.e.login.GovtClass.NGOActivity;
import com.e.login.R;
import com.e.login.ViewActivity;

import java.util.List;

public class Jobs_Adapter extends RecyclerView.Adapter<Jobs_Adapter.ViewHolder> {



    List<Jobs_Model> jobsModelList;
    private Context context;


    public Jobs_Adapter(Context context, List<Jobs_Model> jobsModelList) {
        this.context = context;
        this.jobsModelList=jobsModelList;
    }

    @NonNull
    @Override
    public Jobs_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fifty_two,parent,false);


        return new Jobs_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(jobsModelList.get(position).getTxt());

        holder.img.setImageResource(R.drawable.india);
    }





    // total number of rows
    @Override
    public int getItemCount() {

        return 4;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.india);

            textView = itemView.findViewById(R.id.india_txt);



////
       }



    }


}