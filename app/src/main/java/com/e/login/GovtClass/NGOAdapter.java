package com.e.login.GovtClass;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.e.login.R;

import java.util.List;

public class NGOAdapter extends RecyclerView.Adapter<NGOAdapter.ViewHolder> {



    List<NGOModel> ngoModelList;
    private Context context;


    public NGOAdapter(Context context, List<NGOModel> ngoModelList) {
        this.context = context;
        this.ngoModelList=ngoModelList;
    }


    @NonNull
    @Override
    public NGOAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_eight,parent,false);


        return new NGOAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(ngoModelList.get(position).getText());
        holder.textView1.setText(ngoModelList.get(position).getText_one());
        Glide.with(context).load(ngoModelList.get(position).getImage()).into(holder.img);
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
            img = itemView.findViewById(R.id.arpe_img);

            textView = itemView.findViewById(R.id.arpe_txt);
            textView1 = itemView.findViewById(R.id.arpe_txt_one);




        }



    }


}