package com.e.login.MarketCatClass;

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
import com.e.login.MarketListClass.MarketActivity;
import com.e.login.R;

import java.util.List;

public class MarketClassAdapter extends RecyclerView.Adapter<com.e.login.MarketCatClass.MarketClassAdapter.ViewHolder> {



    List<MarketClassModel> marketClassModelList;
    private Context context;


    public MarketClassAdapter(Context context, List<MarketClassModel> marketClassModelList) {
        this.context = context;
        this.marketClassModelList= marketClassModelList;
    }

    @NonNull
    @Override
    public com.e.login.MarketCatClass.MarketClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_sixteen,parent,false);


        return new com.e.login.MarketCatClass.MarketClassAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(marketClassModelList.get(position).getName());
        holder.textView2.setText(marketClassModelList.get(position).getView_count());


        Glide.with(context)
                .load(marketClassModelList.get(position).getImg())
                .into(holder.img);
    }



    // total number of rows
    @Override
    public int getItemCount() {

        return marketClassModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1;
        TextView textView, textView2;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.veg_img);


           textView = itemView.findViewById(R.id.veg_txt);
            textView2 = itemView.findViewById(R.id.veg_txt_one);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();

                    String id = marketClassModelList.get(position).getId();

                    Intent intent = new Intent(view.getContext(), MarketActivity.class);
                    intent.putExtra("id",id);
                    view.getContext().startActivity(intent);
                }
            });
//
        }



    }


}