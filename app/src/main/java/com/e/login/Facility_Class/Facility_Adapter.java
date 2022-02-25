package com.e.login.Facility_Class;


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
import com.e.login.Gallery_Class.Gallery_Model;
import com.e.login.R;

import java.util.List;

public class Facility_Adapter extends RecyclerView.Adapter<Facility_Adapter.ViewHolder> {



    List<Facility_Model> facilityModelList;
    private Context context;


    public Facility_Adapter(Context context, List<Facility_Model> facilityModelList) {
        this.context = context;
        this.facilityModelList = facilityModelList;
    }

    @NonNull
    @Override
    public Facility_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fourty_three,parent,false);


        return new Facility_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.img.setImageResource(R.drawable.mahal_two);
        holder.txt.setText(facilityModelList.get(position).getTxt());
        holder.txt1.setText(facilityModelList.get(position).getTxt1());
    }




    // total number of rows
    @Override
    public int getItemCount() {

        return 4;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt,txt1;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.mahall_img);
            txt = itemView.findViewById(R.id.mahal_txt_one);
            txt1 = itemView.findViewById(R.id.mahal_txt_two);






        }



    }



}
