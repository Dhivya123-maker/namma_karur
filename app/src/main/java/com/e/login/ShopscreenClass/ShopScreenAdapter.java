package com.e.login.ShopscreenClass;




import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.Home_Fragment_Class;
import com.e.login.R;
import com.e.login.ShopClass.ShopClassAdapter;
import com.e.login.ShopClass.ShopModel;
import com.e.login.ShopscreenClass.ShopsScreenFragment;

import java.util.List;

public class ShopScreenAdapter extends RecyclerView.Adapter<com.e.login.ShopscreenClass.ShopScreenAdapter.ViewHolder> {



    List<ShopScreenModel> shopScreenModelList;
    private Context context;


    public static ShopScreenAdapter.OnItemClickListener mListener1;




    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ShopsScreenFragment listener){

        mListener1 = (OnItemClickListener) listener;

    }

    public ShopScreenAdapter(Context context, List<ShopScreenModel> shopScreenModelList) {
        this.context = context;
        this.shopScreenModelList=shopScreenModelList;
    }

    @NonNull
    @Override
    public ShopScreenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_four,parent,false);


        return new com.e.login.ShopscreenClass.ShopScreenAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(shopScreenModelList.get(position).getText());
        holder.textView1.setText(shopScreenModelList.get(position).getText_one());
        holder.textView2.setText(shopScreenModelList.get(position).getText_two());
        holder.textView3.setText(shopScreenModelList.get(position).getText_three());
        holder.textView4.setText(shopScreenModelList.get(position).getText_four());


//        holder.img.setImageResource(R.drawable.ac_one);

        Glide.with(context)
                .load(shopScreenModelList.get(position).getImage())
                .into(holder.img);


//        holder.img1.setImageResource(R.drawable.starr);
//        holder.img2.setImageResource(R.drawable.verified);
//        holder.img3.setImageResource(R.drawable.clock);

    }
    // inflates the row layout from xml when needed
//    @Override
//    public ShopClassAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = mInflater.inflate(R.layout.recycler_row_three, parent, false);
////        return new com.e.login.HomeClass.MyRecyclerViewAdapter.ViewHolder(view);
//
//       return new ViewHolder(view);
//    }

    // binds the data to the TextView in each row
//    @Override
//    public void onBindViewHolder(com.e.login.ShopClass.ShopClassAdapter.ViewHolder holder, int position) {
////        String animal = mData.get(position);
////         holder.textView.setText(animal);
//

//
//
//
//    }

    // total number of rows
    @Override
    public int getItemCount() {

        return shopScreenModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2,img3;
        TextView textView, textView1,textView2,textView3,textView4;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.sr_img);
            img1 = itemView.findViewById(R.id.star);
            img2 = itemView.findViewById(R.id.verify_img);
            img3 = itemView.findViewById(R.id.time_img);

            textView = itemView.findViewById(R.id.sr_txt_one);
            textView1 = itemView.findViewById(R.id.sr_txt_two);
            textView2 = itemView.findViewById(R.id.sr_txt_three);
            textView3 = itemView.findViewById(R.id.verified_txt);
            textView4 = itemView.findViewById(R.id.time_txt);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener1 != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener1.onItemClick(position);
                        }
                    }

                }
            });

        }



    }


}