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

import com.e.login.R;
import com.e.login.ViewActivity;

import java.util.List;

public class Jobs_two_Adapter extends RecyclerView.Adapter<Jobs_two_Adapter.ViewHolder> {



    List<Jobs_two_Model> jobs_two_modelList;
    private Context context;


    public Jobs_two_Adapter(Context context, List<Jobs_two_Model> jobs_two_modelList) {
        this.context = context;
        this.jobs_two_modelList =jobs_two_modelList;
    }

    @NonNull
    @Override
    public Jobs_two_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fifty_four,parent,false);


        return new Jobs_two_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(jobs_two_modelList.get(position).getTxt());
        holder.txt1.setText(jobs_two_modelList.get(position).getTxt1());

        holder.txt2.setText(jobs_two_modelList.get(position).getTxt2());

        holder.txt3.setText(jobs_two_modelList.get(position).getTxt3());

        holder.txt4.setText(jobs_two_modelList.get(position).getTxt4());


        holder.img.setImageResource(R.drawable.ambulance);
    }





    // total number of rows
    @Override
    public int getItemCount() {

        return 4;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2;
        TextView textView,txt1,txt2,txt3,txt4;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.jobs_main1);
            img1 = itemView.findViewById(R.id.job_img1);
            img2 = itemView.findViewById(R.id.job_img2);

            textView = itemView.findViewById(R.id.jobb_txt1);
            txt1 = itemView.findViewById(R.id.jobb_txt2);
            txt2 = itemView.findViewById(R.id.job_txt3);
            txt3 = itemView.findViewById(R.id.job_txt4);
            txt4 = itemView.findViewById(R.id.job_txt5);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), ViewActivity.class);
                    view.getContext().startActivity(intent);
                }
            });
////
        }



    }


}