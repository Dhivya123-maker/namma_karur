package com.e.login.HospitalClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.R;
import com.e.login.TransportClass.Transport_Comments_Model;

import java.util.List;

public class Hospital_Comments_Adapter extends RecyclerView.Adapter<Hospital_Comments_Adapter.ViewHolder> {



    List<Hospital_Comments_Model> hospitalCommentsModelList;
    private Context context;


    public Hospital_Comments_Adapter(Context context, List<Hospital_Comments_Model> hospitalCommentsModelList) {
        this.context = context;
        this.hospitalCommentsModelList = hospitalCommentsModelList;
    }

    @NonNull
    @Override
    public Hospital_Comments_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_seventy_one,parent,false);


        return new Hospital_Comments_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(hospitalCommentsModelList.get(position).getTxt());
        holder.textView1.setText(hospitalCommentsModelList.get(position).getTxt1());
        holder.txt2.setText(hospitalCommentsModelList.get(position).getTxt2());

        holder.img.setImageResource(R.drawable.girl_white);
        holder.img1.setImageResource(R.drawable.five_starr);
    }




    // total number of rows
    @Override
    public int getItemCount() {

        return hospitalCommentsModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1;
        TextView textView, textView1,txt2;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.comment_img3);
            img1= itemView.findViewById(R.id.star_s3);

            textView = itemView.findViewById(R.id.u_txt3);
            textView1 = itemView.findViewById(R.id.g_txt3);
            txt2 = itemView.findViewById(R.id.r_rate3);






        }



    }



}
