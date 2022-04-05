package com.e.login.BlankFragment;


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

public class Blank_Adapter extends RecyclerView.Adapter<Blank_Adapter.ViewHolder> {



    List<Blank_Model> blankModelList;
    private Context context;


    public Blank_Adapter(Context context, List<Blank_Model>blankModelList) {
        this.context = context;
        this.blankModelList = blankModelList;
    }

    @NonNull
    @Override
    public Blank_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_thirty_five,parent,false);


        return new Blank_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(blankModelList.get(position).getTxt());
        holder.textView1.setText(blankModelList.get(position).getTxt1());
        holder.txt2.setText(blankModelList.get(position).getTxt2());

        holder.img.setImageResource(R.drawable.girl_white);

    }





    // total number of rows
    @Override
    public int getItemCount() {

        return 4;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1;
        TextView textView, textView1,txt2;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.dd_img);
            img1= itemView.findViewById(R.id.starr_reviews);

            textView = itemView.findViewById(R.id.user_txt);
            textView1 = itemView.findViewById(R.id.good_txt);
            txt2 = itemView.findViewById(R.id.reviews_ratee);






        }



    }



}
