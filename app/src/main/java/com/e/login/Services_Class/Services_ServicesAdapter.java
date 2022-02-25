package com.e.login.Services_Class;


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
import com.e.login.EducationClass.Education_Services_Model;
import com.e.login.R;

import java.util.List;

public class Services_ServicesAdapter extends RecyclerView.Adapter<Services_ServicesAdapter.ViewHolder> {



    List<Services_Services_Model> servicesServicesModels;
    private Context context;





    public static Services_ServicesAdapter.OnItemClickListener mListener;



    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(Services_ServicesAdapter.OnItemClickListener listener){

        mListener = listener;

    }

    public Services_ServicesAdapter(Context context, List<Services_Services_Model> servicesServicesModels) {
        this.context = context;
        this.servicesServicesModels = servicesServicesModels;
    }

    @NonNull
    @Override
    public Services_ServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_seventy_three,parent,false);


        return new Services_ServicesAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(servicesServicesModels.get(position).getText());
        holder.textView1.setText(servicesServicesModels.get(position).getText_one());
       holder.btn.setText(servicesServicesModels.get(position).getButton());
        //holder.img.setImageResource(R.drawable.ac);

        Glide.with(context)
                .load(servicesServicesModels.get(position).getImage())
                .into(holder.img);

    }



    // total number of rows
    @Override
    public int getItemCount() {

        return servicesServicesModels.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        Button btn;
        TextView textView, textView1;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.services_ser);
           btn = itemView.findViewById(R.id.services_button);

            textView = itemView.findViewById(R.id.services_txt_one);
            textView1 = itemView.findViewById(R.id.services_txt_two);



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
//                    Intent intent = new Intent(view.getContext(),Carrier_Activity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    view.getContext().startActivity(intent);


        }



    }


}