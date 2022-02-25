package com.e.login.info_Class;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.R;
import com.e.login.ShoppostClass.ShopPostModel;

import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {



    List<InfoModel> infoModelList;
    private Context context;


    public InformationAdapter(Context context, List<InfoModel> infoModelList) {
        this.context = context;
        this.infoModelList = infoModelList;
    }

    @NonNull
    @Override
    public InformationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_recycle,parent,false);


        return new InformationAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(infoModelList.get(position).getText());
        holder.textView1.setText(infoModelList.get(position).getText_one());
        holder.textView2.setText(infoModelList.get(position).getText_two());
        holder.textView3.setText(infoModelList.get(position).getText_three());
        holder.img.setImageResource(R.drawable.ac);
        holder.img2.setImageResource(R.drawable.sam);
        holder.img3.setImageResource(R.drawable.heartt);
        holder.img5.setImageResource(R.drawable.sharee);

    }



    // total number of rows
    @Override
    public int getItemCount() {

        return 4;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2,img3,img4,img5;
        TextView textView, textView1,textView2,textView3;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.sam_img);
            img2 = itemView.findViewById(R.id.sam_full_img);
            img3 = itemView.findViewById(R.id.like);
            img5 = itemView.findViewById(R.id.samm_share);


            textView = itemView.findViewById(R.id.sam_txt);
            textView1 = itemView.findViewById(R.id.sam_txt_one);
            textView2 = itemView.findViewById(R.id.sam_days);
            textView3 = itemView.findViewById(R.id.txtview);


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