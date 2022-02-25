package com.e.login.MarketListClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.BusTimeClass.BusTimeModel;
import com.e.login.R;

import java.util.List;

public class MarketListAdapter extends RecyclerView.Adapter<MarketListAdapter.ViewHolder> {



    List<MarketListModel> marketListModelList;
    private Context context;


    public MarketListAdapter(Context context, List<MarketListModel> marketListModelList) {
        this.context = context;
        this.marketListModelList= marketListModelList;
    }

    @NonNull
    @Override
    public com.e.login.MarketListClass.MarketListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_seventeen,parent,false);


        return new com.e.login.MarketListClass.MarketListAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(marketListModelList.get(position).getName());
        holder.textView1.setText(marketListModelList.get(position).getPrice());

        Glide.with(context)
                .load(marketListModelList.get(position).getImg())
                .into(holder.img);
    }







    // total number of rows
    @Override
    public int getItemCount() {

        return marketListModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView, textView1;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.brinjal_img);

           textView = itemView.findViewById(R.id.brinjal_txt);
            textView1 = itemView.findViewById(R.id.brinjal_txt_one);





        }



    }


}