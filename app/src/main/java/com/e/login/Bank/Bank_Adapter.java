package com.e.login.Bank;

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

import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.Articles;
import com.e.login.BusTimeClass.BusTimeModel;
import com.e.login.R;
import com.e.login.SmallBusClass.SmallBusActivity;

import java.util.List;

public class Bank_Adapter extends RecyclerView.Adapter<Bank_Adapter.ViewHolder> {


    List<Bank_Model> bank_modelList;
    private Context context;


    public Bank_Adapter(Context context, List<Bank_Model> bank_modelList) {
        this.context = context;
        this.bank_modelList = bank_modelList;
    }

    @NonNull
    @Override
    public Bank_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_eighten, parent, false);


        return new Bank_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(bank_modelList.get(position).getText());
        holder.img.setImageResource(R.drawable.axis);
    }



    // total number of rows
    @Override
    public int getItemCount() {

        return 5;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.bank_image);

            textView = itemView.findViewById(R.id.bank_txt);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), Bank_One.class);
                    view.getContext().startActivity(intent);
                }
            });
        }


    }


}