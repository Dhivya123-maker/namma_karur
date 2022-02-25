package com.e.login.BloodClass;

import android.content.Context;
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

public class Blood_Adapter extends RecyclerView.Adapter<Blood_Adapter.ViewHolder> {



    List<Blood_Model> bloodModelList;
    private Context context;


    public Blood_Adapter(Context context, List<Blood_Model> bloodModelList) {
        this.context = context;
        this.bloodModelList = bloodModelList;
    }

    @NonNull
    @Override
    public Blood_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blood_recycle,parent,false);


        return new Blood_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(bloodModelList.get(position).getName());
        holder.txt1.setText(bloodModelList.get(position).getPosted());
        holder.txt2.setText(bloodModelList.get(position).getP_name());
        holder.txt3.setText(bloodModelList.get(position).getB_grp());
        holder.txt4.setText(bloodModelList.get(position).getProblem());
        holder.txt5.setText(bloodModelList.get(position).getNeed());
        holder.txt6.setText(bloodModelList.get(position).getUnits());
        holder.txt7.setText(bloodModelList.get(position).getHospital());
        holder.txt8.setText(bloodModelList.get(position).getC_num());
        holder.txt9.setText(bloodModelList.get(position).getA_num());
        holder.txt10.setText(bloodModelList.get(position).getAddress());




//        holder.img.setImageResource(R.drawable.ambulance);
        Glide.with(context)
                .load(bloodModelList.get(position).getImg())
                .into(holder.img);
    }





    // total number of rows
    @Override
    public int getItemCount() {

        return bloodModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView, txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_blood);

            textView = itemView.findViewById(R.id.profile_txt);
            txt1 = itemView.findViewById(R.id.posted_txt);
            txt2 = itemView.findViewById(R.id.patient_txt);
            txt3 = itemView.findViewById(R.id.blood_txt);
            txt4 = itemView.findViewById(R.id.problem_txt);
            txt5 = itemView.findViewById(R.id.needed_txt);
            txt6 = itemView.findViewById(R.id.units_txt);
            txt7 = itemView.findViewById(R.id.hospitals_txt);
            txt8 = itemView.findViewById(R.id.c_num_txt);
            txt9 = itemView.findViewById(R.id.a_num_txt);
            txt10 = itemView.findViewById(R.id.addres_txt);







        }



    }



}