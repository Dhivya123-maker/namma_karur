package com.e.login.Non_govt_Class;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.GovtClass.NGOModel;
import com.e.login.R;

import java.util.List;

public class NGO_two_Adapter extends RecyclerView.Adapter<NGO_two_Adapter.ViewHolder> {



    List<NGO_two_Model> ngoTwoModelList;
    private Context context;


    public NGO_two_Adapter(Context context, List<NGO_two_Model> ngoTwoModelList) {
        this.context = context;
        this.ngoTwoModelList=ngoTwoModelList;
    }


    @NonNull
    @Override
    public NGO_two_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fourty_six,parent,false);


        return new NGO_two_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(ngoTwoModelList.get(position).getText());
        holder.textView1.setText(ngoTwoModelList.get(position).getText_one());
        holder.img.setImageResource(R.drawable.ngo_one);
    }


    // total number of rows
    @Override
    public int getItemCount() {

        return 2;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView, textView1;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.arpe_imgg);

            textView = itemView.findViewById(R.id.arpe_txtt);
            textView1 = itemView.findViewById(R.id.arpe_txt_onee);




        }



    }


}