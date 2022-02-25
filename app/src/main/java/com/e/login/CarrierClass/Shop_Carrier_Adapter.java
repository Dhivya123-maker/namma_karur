package com.e.login.CarrierClass;

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
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;

import java.util.List;

public class Shop_Carrier_Adapter extends RecyclerView.Adapter<Shop_Carrier_Adapter.ViewHolder> {



    List<Shop_Carrier_model> shopCarrierModelList;
    private Context context;


    public Shop_Carrier_Adapter(Context context, List<Shop_Carrier_model> shopCarrierModelList) {
        this.context = context;
        this.shopCarrierModelList = shopCarrierModelList;
    }

    @NonNull
    @Override
    public Shop_Carrier_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_seventy_four,parent,false);


        return new Shop_Carrier_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(shopCarrierModelList.get(position).getTxt());
        holder.textView1.setText(shopCarrierModelList.get(position).getTxt1());
        holder.txt2.setText(shopCarrierModelList.get(position).getTxt2());

//        holder.img.setImageResource(R.drawable.ac_img);

        Glide.with(context)
                .load(shopCarrierModelList.get(position).getImg())
                .into(holder.img);

        Glide.with(context)
                .load(shopCarrierModelList.get(position).getImg1())
                .into(holder.img1);
        Glide.with(context)
                .load(shopCarrierModelList.get(position).getImg2())
                .into(holder.img2);

        Glide.with(context)
                .load(shopCarrierModelList.get(position).getImg3())
                .into(holder.img3);

        Glide.with(context)
                .load(shopCarrierModelList.get(position).getImg4())
                .into(holder.img4);




    }


    // total number of rows
    @Override
    public int getItemCount() {

        return 1;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2,img3,img4;
        TextView textView, textView1,txt2;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.big_ac_img1);
            img1 = itemView.findViewById(R.id.shop_img1);
            img2 = itemView.findViewById(R.id.shop_img2);
            img3 = itemView.findViewById(R.id.shop_img3);
            img4 = itemView.findViewById(R.id.shop_img4);


            textView = itemView.findViewById(R.id.carrier_txt1);
            textView1 = itemView.findViewById(R.id.offer_txt1);
            txt2 = itemView.findViewById(R.id.shop_desc);






        }



    }


}