package com.e.login.Bank;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.R;

import java.util.List;

class Bank_One_Adapter extends RecyclerView.Adapter<Bank_One_Adapter.ViewHolder> {


    List<Bank_One_Model> bank_one_modelList;
    private Context context;


    public Bank_One_Adapter(Context context, List<Bank_One_Model> bank_one_modelList) {
        this.context = context;
        this.bank_one_modelList = bank_one_modelList;
    }



    @NonNull
    @Override
    public Bank_One_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_nineteen, parent, false);


        return new Bank_One_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(bank_one_modelList.get(position).getText());
        holder.textview1.setText(bank_one_modelList.get(position).getText1());
        holder.txt2.setText(bank_one_modelList.get(position).getText2());
        holder.txt3.setText(bank_one_modelList.get(position).getText3());
        holder.txt4.setText(bank_one_modelList.get(position).getText4());

        holder.img.setImageResource(R.drawable.axis);
        holder.img1.setImageResource(R.drawable.clock);
        holder.img2.setImageResource(R.drawable.verified);
        holder.img3.setImageResource(R.drawable.starr);
    }



    // total number of rows
    @Override
    public int getItemCount() {

        return 3;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2,img3;
        TextView textView,textview1,txt2,txt3,txt4;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.bk_img);
            img1 = itemView.findViewById(R.id.time_img_nine);
            img2 = itemView.findViewById(R.id.verify_img_nine);
            img3 = itemView.findViewById(R.id.star_nine);
            textView = itemView.findViewById(R.id.bk_txt_one);
            textview1 = itemView.findViewById(R.id.bk_txt_two);
            txt2 = itemView.findViewById(R.id.time_txt_nine);
            txt3 = itemView.findViewById(R.id.verified_txt_nine);
            txt4 = itemView.findViewById(R.id.nine_txt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),Bank_Fragment_Class.class);
                    view.getContext().startActivity(intent);
                }
            });


        }


    }



}
