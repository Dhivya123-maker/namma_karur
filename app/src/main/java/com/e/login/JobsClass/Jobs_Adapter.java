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

public class Jobs_Adapter extends RecyclerView.Adapter<Jobs_Adapter.ViewHolder> {



    List<Jobs_Model> jobsModelList;
    private Context context;
    public static Jobs_Adapter.OnItemClickListener mListener;




    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(Jobs_Adapter.OnItemClickListener listener){

        mListener = listener;

    }


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


        Glide.with(context)
                .load(jobsModelList.get(position).getImg())
                .into(holder.img);

    }





    // total number of rows
    @Override
    public int getItemCount() {

        return jobsModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.india);


            textView = itemView.findViewById(R.id.india_txt);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });




       }



    }


}