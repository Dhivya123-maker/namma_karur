package com.e.login.Profile_details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.Profile;
import com.e.login.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.ViewHolder> {



    List<Education_Model> educationModelList;
    private Context context;




    public EducationAdapter(Profile context, List<Education_Model> educationModelList) {
        this.context = context;
        this.educationModelList = educationModelList;
    }


    @NonNull
    @Override
    public EducationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popup,parent,false);


        return new EducationAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.ins.setText(educationModelList.get(position).getIns());
        holder.deg.setText(educationModelList.get(position).getDeg());
        holder.year.setText(educationModelList.get(position).getYear());

    }




    // total number of rows
    @Override
    public int getItemCount() {

        return educationModelList.size();

    }



    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ins,deg,year;

        ViewHolder(View itemView) {
            super(itemView);

            ins = itemView.findViewById(R.id.inss);
            deg = itemView.findViewById(R.id.degg);
            year = itemView.findViewById(R.id.yrr);








        }



    }


}