package com.e.login.AmbulanceClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.BusTimeClass.BusTimeModel;
import com.e.login.R;
import com.e.login.SmallBusClass.SmallBusActivity;

import java.util.List;

public class AmbulanceAdapter extends RecyclerView.Adapter<com.e.login.AmbulanceClass.AmbulanceAdapter.ViewHolder> {



    List<AmbulanceModel> ambulanceModelList;
    private Context context;


    public AmbulanceAdapter(Context context, List<AmbulanceModel> ambulanceModelList) {
        this.context = context;
        this.ambulanceModelList= ambulanceModelList;
    }

    @NonNull
    @Override
    public com.e.login.AmbulanceClass.AmbulanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_fourteen,parent,false);


        return new com.e.login.AmbulanceClass.AmbulanceAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(ambulanceModelList.get(position).getText());
        holder.textView1.setText(ambulanceModelList.get(position).getText_one());
        holder.btn.setText(ambulanceModelList.get(position).getBtn());
        holder.btn1.setText(ambulanceModelList.get(position).getBtn_one());

//        holder.img.setImageResource(R.drawable.ambulance);
        Glide.with(context)
                .load(ambulanceModelList.get(position).getImage())
                .into(holder.img);
    }




    // total number of rows
    @Override
    public int getItemCount() {

        return ambulanceModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView, textView1;
        Button btn,btn1;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.ambulance_img);

            textView = itemView.findViewById(R.id.ambulance_txt);
            textView1 = itemView.findViewById(R.id.ambulance_txt_one);
            btn = itemView.findViewById(R.id.tn_btn);
            btn1 = itemView.findViewById(R.id.call_btn);





        }



    }


}