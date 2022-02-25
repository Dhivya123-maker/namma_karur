package com.e.login.Offers;

import android.content.Context;
import android.content.Intent;
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

public class Offer_two_Adapter extends RecyclerView.Adapter<Offer_two_Adapter.ViewHolder> {



    List<Offer_two_model> offerTwoModelList;
    private Context context;


    public Offer_two_Adapter(Context context, List<Offer_two_model> offerTwoModelList) {
        this.context = context;
        this.offerTwoModelList = offerTwoModelList;
    }

    @NonNull
    @Override
    public Offer_two_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_thirty,parent,false);


        return new Offer_two_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(offerTwoModelList.get(position).getImg())
                .into(holder.img);
        holder.txt.setText(offerTwoModelList.get(position).getTxt());
        holder.txt1.setText(offerTwoModelList.get(position).getTxt1());
    }


    // total number of rows
    @Override
    public int getItemCount() {

        return offerTwoModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt,txt1;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.only_offerr_img);
            txt = itemView.findViewById(R.id.only_txt);
            txt1 = itemView.findViewById(R.id.only_txt_one);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),Top_Offer_Activity.class);
                    view.getContext().startActivity(intent);
                }
            });



        }



    }


}