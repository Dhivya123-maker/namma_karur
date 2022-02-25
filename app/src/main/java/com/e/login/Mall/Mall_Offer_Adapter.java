package com.e.login.Mall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.MoreInfoClass.More_Info_Model;
import com.e.login.R;

import java.util.List;

public class Mall_Offer_Adapter extends RecyclerView.Adapter<Mall_Offer_Adapter.ViewHolder> {



    List<Mall_Offer_Model> mallOfferModelList;
    private Context context;


    public Mall_Offer_Adapter(Context context, List<Mall_Offer_Model> mallOfferModelList) {
        this.context = context;
        this.mallOfferModelList = mallOfferModelList;
    }

    @NonNull
    @Override
    public Mall_Offer_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fourty_seven,parent,false);


        return new Mall_Offer_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(mallOfferModelList.get(position).getTxt());

        holder.img.setImageResource(R.drawable.sharee);
        holder.img1.setImageResource(R.drawable.banner);
    }




    // total number of rows
    @Override
    public int getItemCount() {

        return 4;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.ac_sharee);
            img1 = itemView.findViewById(R.id.banner_offerr);

            textView = itemView.findViewById(R.id.ac_offer_txtt);



        }


    }
}


