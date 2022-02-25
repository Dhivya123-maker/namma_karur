package com.e.login.MoreInfoClass;


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

public class More_Info_Adapter extends RecyclerView.Adapter<More_Info_Adapter.ViewHolder> {



    List<More_Info_Model> moreInfoModelList;
    private Context context;


    public More_Info_Adapter(Context context, List<More_Info_Model> moreInfoModelList) {
        this.context = context;
        this.moreInfoModelList = moreInfoModelList;
    }

    @NonNull
    @Override
    public More_Info_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_thirty_eight,parent,false);


        return new More_Info_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(moreInfoModelList.get(position).getTxt());

        holder.img.setImageResource(R.drawable.sharee);
        Glide.with(context)
                .load(moreInfoModelList.get(position).getImg1())
                .into(holder.img1);

    }





    // total number of rows
    @Override
    public int getItemCount() {

        return moreInfoModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.ac_share);
            img1 = itemView.findViewById(R.id.banner_offer);

            textView = itemView.findViewById(R.id.ac_offer_txt);



        }


    }
}


