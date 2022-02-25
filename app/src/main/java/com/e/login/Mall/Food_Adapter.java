package com.e.login.Mall;

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

import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;

import java.util.List;

public class Food_Adapter extends RecyclerView.Adapter<Food_Adapter.ViewHolder> {



    List<Food_Model> foodModelList;
    private Context context;


    public Food_Adapter(Context context, List<Food_Model> foodModelList) {
        this.context = context;
        this.foodModelList= foodModelList;
    }

    @NonNull
    @Override
    public Food_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_twenty_three,parent,false);


        return new Food_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(foodModelList.get(position).getTxt());
        holder.textView1.setText(foodModelList.get(position).getTxt1());
        holder.txt2.setText(foodModelList.get(position).getTxt2());
        holder.txt3.setText(foodModelList.get(position).getTxt3());
        holder.txt4.setText(foodModelList.get(position).getTxt4());

        holder.img.setImageResource(R.drawable.food_banner);
        holder.img1.setImageResource(R.drawable.five_starr);
        holder.img2.setImageResource(R.drawable.clock);
        holder.img3.setImageResource(R.drawable.verified);
    }






    // total number of rows
    @Override
    public int getItemCount() {

        return 3;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2,img3;
        TextView textView, textView1,txt2,txt3,txt4;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.images);
            img1 = itemView.findViewById(R.id.star_images);
            img2 = itemView.findViewById(R.id.clock_images);
            img3 = itemView.findViewById(R.id.verify_images);

            textView = itemView.findViewById(R.id.reviews_malll);
            textView1 = itemView.findViewById(R.id.malll_txt_one);
            txt2 = itemView.findViewById(R.id.malll_txt_two);
            txt3 = itemView.findViewById(R.id.time_malll);
            txt4 = itemView.findViewById(R.id.verified_txt_malll);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),Mall_Home_Fragment.class);
                    view.getContext().startActivity(intent);
                }
            });

        }



    }



}
