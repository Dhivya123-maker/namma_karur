package com.e.login.HospitalClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.EducationClass.education_Carrier_model;
import com.e.login.R;

import java.util.List;

public class Hospital_Carrier_Adapter extends RecyclerView.Adapter<Hospital_Carrier_Adapter.ViewHolder> {



    List<Hospital_Carrier_model> hospitalCarrierModelList;
    private Context context;


    public Hospital_Carrier_Adapter(Context context, List<Hospital_Carrier_model> hospitalCarrierModelList) {
        this.context = context;
        this.hospitalCarrierModelList = hospitalCarrierModelList;
    }

    @NonNull
    @Override
    public Hospital_Carrier_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_seventy_eight,parent,false);


        return new Hospital_Carrier_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(hospitalCarrierModelList.get(position).getTxt());
        holder.textView1.setText(hospitalCarrierModelList.get(position).getTxt1());
        holder.txt2.setText(hospitalCarrierModelList.get(position).getTxt2());

//        holder.img.setImageResource(R.drawable.ac_img);

        Glide.with(context)
                .load(hospitalCarrierModelList.get(position).getImg())
                .into(holder.img);

        Glide.with(context)
                .load(hospitalCarrierModelList.get(position).getImg1())
                .into(holder.img1);
        Glide.with(context)
                .load(hospitalCarrierModelList.get(position).getImg2())
                .into(holder.img2);

        Glide.with(context)
                .load(hospitalCarrierModelList.get(position).getImg3())
                .into(holder.img3);

        Glide.with(context)
                .load(hospitalCarrierModelList.get(position).getImg4())
                .into(holder.img4);




    }


    // total number of rows
    @Override
    public int getItemCount() {

        return 1;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2,img3,img4;
        TextView textView, textView1,txt2;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.big_ac_img3);
            img1 = itemView.findViewById(R.id.hos_img1);
            img2 = itemView.findViewById(R.id.hos_img2);
            img3 = itemView.findViewById(R.id.hos_img3);
            img4 = itemView.findViewById(R.id.hos_img4);


            textView = itemView.findViewById(R.id.carrier_txt3);
            textView1 = itemView.findViewById(R.id.offer_txt3);
            txt2 = itemView.findViewById(R.id.hospital_desc);






        }



    }


}