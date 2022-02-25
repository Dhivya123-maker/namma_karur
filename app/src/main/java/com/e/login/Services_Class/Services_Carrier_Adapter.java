package com.e.login.Services_Class;

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

public class Services_Carrier_Adapter extends RecyclerView.Adapter<Services_Carrier_Adapter.ViewHolder> {



    List<Services_Carrier_model> servicesCarrierModelList;
    private Context context;


    public Services_Carrier_Adapter(Context context, List<Services_Carrier_model> servicesCarrierModelList) {
        this.context = context;
        this.servicesCarrierModelList = servicesCarrierModelList;
    }

    @NonNull
    @Override
    public Services_Carrier_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_seventy_six,parent,false);


        return new Services_Carrier_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(servicesCarrierModelList.get(position).getTxt());
        holder.textView1.setText(servicesCarrierModelList.get(position).getTxt1());
        holder.txt2.setText(servicesCarrierModelList.get(position).getTxt2());

//        holder.img.setImageResource(R.drawable.ac_img);

        Glide.with(context)
                .load(servicesCarrierModelList.get(position).getImg())
                .into(holder.img);

        Glide.with(context)
                .load(servicesCarrierModelList.get(position).getImg1())
                .into(holder.img1);
        Glide.with(context)
                .load(servicesCarrierModelList.get(position).getImg2())
                .into(holder.img2);

        Glide.with(context)
                .load(servicesCarrierModelList.get(position).getImg3())
                .into(holder.img3);

        Glide.with(context)
                .load(servicesCarrierModelList.get(position).getImg4())
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
            img1 = itemView.findViewById(R.id.shop_img21);
            img2 = itemView.findViewById(R.id.shop_img31);
            img3 = itemView.findViewById(R.id.shop_img41);
            img4 = itemView.findViewById(R.id.shop_img51);


            textView = itemView.findViewById(R.id.carrier_txt3);
            textView1 = itemView.findViewById(R.id.offer_txt3);
            txt2 = itemView.findViewById(R.id.services_desc);






        }



    }


}