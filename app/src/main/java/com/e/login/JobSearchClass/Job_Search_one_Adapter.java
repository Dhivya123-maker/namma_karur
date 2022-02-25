package com.e.login.JobSearchClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.R;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.List;

public class Job_Search_one_Adapter extends RecyclerView.Adapter<Job_Search_one_Adapter.ViewHolder> {



    List<Job_Search_one_Model> jobSearchOneModelList;
    private Context context;


    public Job_Search_one_Adapter(Context context, List<Job_Search_one_Model> jobSearchOneModelList) {
        this.context = context;
        this.jobSearchOneModelList = jobSearchOneModelList;
    }

    @NonNull
    @Override
    public Job_Search_one_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_thirty_seven,parent,false);


        return new Job_Search_one_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(jobSearchOneModelList.get(position).getTxt());


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
        TextView textView;
        com.kyleduo.switchbutton.SwitchButton switchButton;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.job_img);

            textView = itemView.findViewById(R.id.job_txt);
//            switchButton = itemView.findViewById(R.id.switch_button);



        }


    }}


