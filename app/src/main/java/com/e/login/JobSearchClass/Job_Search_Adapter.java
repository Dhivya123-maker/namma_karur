package com.e.login.JobSearchClass;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;

import java.util.List;

public class Job_Search_Adapter extends RecyclerView.Adapter<Job_Search_Adapter.ViewHolder> {



    List<Job_Search_Model> jobSearchModelList;
    private Context context;


    public Job_Search_Adapter(Context context, List<Job_Search_Model> jobSearchModelList) {
        this.context = context;
        this.jobSearchModelList = jobSearchModelList;
    }

    @NonNull
    @Override
    public Job_Search_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_thirty_six,parent,false);


        return new Job_Search_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull Job_Search_Adapter.ViewHolder holder, int position) {
        holder.textView.setText(jobSearchModelList.get(position).getTxt());
        holder.textView1.setText(jobSearchModelList.get(position).getTxt1());

        holder.img.setImageResource(R.drawable.job_white);
    }




    // total number of rows
    @Override
    public int getItemCount() {

        return 6;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView, textView1;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.job_img);

            textView = itemView.findViewById(R.id.job_txt);
            textView1 = itemView.findViewById(R.id.job_txt_one);


        }


    }}


