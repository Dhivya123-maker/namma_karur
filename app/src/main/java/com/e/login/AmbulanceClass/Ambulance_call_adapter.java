package com.e.login.AmbulanceClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.R;

import java.util.List;

public class Ambulance_call_adapter extends RecyclerView.Adapter<com.e.login.AmbulanceClass.Ambulance_call_adapter.ViewHolder> {



    List<Ambulance_Call_Model> ambulanceCallModelList;
    private Context context;


    public Ambulance_call_adapter(Context context, List<Ambulance_Call_Model> ambulanceCallModelList) {
        this.context = context;
        this.ambulanceCallModelList = ambulanceCallModelList;
    }

    @NonNull
    @Override
    public com.e.login.AmbulanceClass.Ambulance_call_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ambulance_call_recycle,parent,false);


        return new com.e.login.AmbulanceClass.Ambulance_call_adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(ambulanceCallModelList.get(position).getPri_call());

        holder.img.setImageResource(R.drawable.green_call);


    }




    // total number of rows
    @Override
    public int getItemCount() {

        return ambulanceCallModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1;
        TextView textView, textView1;
        Button btn;
        LinearLayout lnr;

        ViewHolder(View itemView) {
            super(itemView);
//            img = itemView.findViewById(R.id.primary_contact_img);
//            lnr = itemView.findViewById(R.id.contact_linear);
//
//            textView = itemView.findViewById(R.id.primary_con_txt);





        }



    }


}
