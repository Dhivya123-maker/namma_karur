package com.e.login.GovtClass;

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
import com.e.login.R;

import java.util.List;

public class Government_Adapter extends RecyclerView.Adapter<Government_Adapter.ViewHolder> {



    List<Government_Model> governmentModelList;
    private Context context;


    public Government_Adapter(Context context, List<Government_Model> governmentModelList) {
        this.context = context;
        this.governmentModelList = governmentModelList;
    }

    @NonNull
    @Override
    public Government_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fourty_nine,parent,false);


        return new Government_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(governmentModelList.get(position).getTxt());

        holder.img.setImageResource(R.drawable.ambulance);
    }




    // total number of rows
    @Override
    public int getItemCount() {

        return 2;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.government_img);

            textView = itemView.findViewById(R.id.government_txt);

        }



    }


}