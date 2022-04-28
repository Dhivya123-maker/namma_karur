package com.e.login.JobsClass;

import android.content.Context;
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

public class Jobs_One_Adapter extends RecyclerView.Adapter<Jobs_One_Adapter.ViewHolder> {



    List<Jobs_One_Model> jobsOneModelList;
    private Context context;


    public Jobs_One_Adapter(Context context, List<Jobs_One_Model> jobsOneModelList) {
        this.context = context;
        this.jobsOneModelList=jobsOneModelList;
    }

    @NonNull
    @Override
    public Jobs_One_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fifty_three,parent,false);


        return new Jobs_One_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(jobsOneModelList.get(position).getTxt());
        holder.txt1.setText(jobsOneModelList.get(position).getTxt1());

        holder.txt2.setText(jobsOneModelList.get(position).getTxt2());

        holder.txt3.setText(jobsOneModelList.get(position).getTxt3());

        holder.txt4.setText(jobsOneModelList.get(position).getTxt4());


        Glide.with(context)
                .load(jobsOneModelList.get(position).getImg())
                .into(holder.img);

//        holder.img.setImageResource(R.drawable.ambulance);
    }





    // total number of rows
    @Override
    public int getItemCount() {

        return jobsOneModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2;
        TextView textView,txt1,txt2,txt3,txt4;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.jobs_main);
            img1 = itemView.findViewById(R.id.job_image);
            img2 = itemView.findViewById(R.id.job_image1);

            textView = itemView.findViewById(R.id.jobb_txt);
            txt1 = itemView.findViewById(R.id.jobb_txt_one);
            txt2 = itemView.findViewById(R.id.job_txt_three);
            txt3 = itemView.findViewById(R.id.job_txt_four);
            txt4 = itemView.findViewById(R.id.job_txt_five);

//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Intent intent = new Intent(view.getContext(), ViewActivity.class);
//                    view.getContext().startActivity(intent);
//                }
//            });
////
        }



    }


}