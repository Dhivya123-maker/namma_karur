package com.e.login.Bank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.BlankFragment.Blank_Model;
import com.e.login.R;

import java.util.List;

public class Bank_Comments_Adapter extends RecyclerView.Adapter<Bank_Comments_Adapter.ViewHolder> {



    List<Bank_Comments_Model> bankCommentsModelList;
    private Context context;


    public Bank_Comments_Adapter(Context context, List<Bank_Comments_Model>bankCommentsModelList) {
        this.context = context;
        this.bankCommentsModelList = bankCommentsModelList;
    }

    @NonNull
    @Override
    public Bank_Comments_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_thirty_nine,parent,false);


        return new Bank_Comments_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(bankCommentsModelList.get(position).getTxt());
        holder.textView1.setText(bankCommentsModelList.get(position).getTxt1());
        holder.txt2.setText(bankCommentsModelList.get(position).getTxt2());

        holder.img.setImageResource(R.drawable.girl_white);
        holder.img1.setImageResource(R.drawable.five_starr);
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
            img = itemView.findViewById(R.id.comments_img);
            img1= itemView.findViewById(R.id.star_star);

            textView = itemView.findViewById(R.id.userr_txt);
            textView1 = itemView.findViewById(R.id.goodd_txt);
            txt2 = itemView.findViewById(R.id.reviewss_ratee);






        }



    }



}
