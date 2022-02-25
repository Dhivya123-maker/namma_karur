package com.e.login.ShoppostClass;



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

import com.e.login.Home_Fragment_Class;
import com.e.login.R;

import java.util.List;

public class ShopPostAdapter extends RecyclerView.Adapter<com.e.login.ShoppostClass.ShopPostAdapter.ViewHolder> {



    List<ShopPostModel> shopPostModelList;
    private Context context;


    public ShopPostAdapter(Context context, List<ShopPostModel> shopPostModelList) {
        this.context = context;
        this.shopPostModelList= shopPostModelList;
    }

    @NonNull
    @Override
    public com.e.login.ShoppostClass.ShopPostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_six,parent,false);


        return new com.e.login.ShoppostClass.ShopPostAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(shopPostModelList.get(position).getText());
//        holder.textView1.setText(shopPostModelList.get(position).getText_one());
        holder.textView2.setText(shopPostModelList.get(position).getText_two());
        holder.textView3.setText(shopPostModelList.get(position).getText_three());
        holder.img.setImageResource(R.drawable.ac);
        holder.img1.setImageResource(R.drawable.menuu);
        holder.img2.setImageResource(R.drawable.sam);

        holder.img3.setImageResource(R.drawable.heartt);
       // holder.img4.setImageResource(R.drawable.comments);
        holder.img4.setImageResource(R.drawable.sharee);

    }



    // total number of rows
    @Override
    public int getItemCount() {

        return 2;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2,img3,img4,img5;

        TextView textView, textView1,textView2,textView3;

        ViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.sam_imgg);
            img1 = itemView.findViewById(R.id.sam_menuu);
            img2 = itemView.findViewById(R.id.sam_full_imgg);
            img3 = itemView.findViewById(R.id.likee);
            img4 = itemView.findViewById(R.id.samm_sharee);

            textView = itemView.findViewById(R.id.sam_txtt);
            textView2 = itemView.findViewById(R.id.sam_dayss);


            textView3 = itemView.findViewById(R.id.txtvieww);



////
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Intent intent = new Intent(view.getContext(), Home_Fragment_Class.class);
//                    view.getContext().startActivity(intent);
//                }
//            });
//
        }



    }


}