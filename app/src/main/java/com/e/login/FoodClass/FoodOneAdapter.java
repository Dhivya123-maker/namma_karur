package com.e.login.FoodClass;

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

import com.e.login.BusTimeClass.BusTimeModel;
import com.e.login.R;
import com.e.login.SmallBusClass.SmallBusActivity;

import java.util.List;

public class FoodOneAdapter extends RecyclerView.Adapter<com.e.login.FoodClass.FoodOneAdapter.ViewHolder> {



    List<FoodOneModel> foodOneModelList;
    private Context context;


    public FoodOneAdapter(Context context, List<FoodOneModel> foodOneModelList) {
        this.context = context;
        this.foodOneModelList= foodOneModelList;
    }

    @NonNull
    @Override
    public com.e.login.FoodClass.FoodOneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_thirteen,parent,false);


        return new com.e.login.FoodClass.FoodOneAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(foodOneModelList.get(position).getText());
        holder.textView1.setText(foodOneModelList.get(position).getText_one());


        holder.img.setImageResource(R.drawable.food_four);
    }



    // total number of rows
    @Override
    public int getItemCount() {

        return 2;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView,textView1;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.food_one_img);

            textView = itemView.findViewById(R.id.food_one_txt);
            textView1= itemView.findViewById(R.id.food_two_txt);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),Readmore_Clas.class);
                    view.getContext().startActivity(intent);
                }
            });


        }



    }


}