package com.e.login.HomeClass;

import android.content.Context;
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

class Top_rating_Adapter extends RecyclerView.Adapter<Top_rating_Adapter.ViewHolder> {



    List<Top_rating_model> topRatingModelList;
    private Context context;


    public Top_rating_Adapter(Context context, List<Top_rating_model> topRatingModelList) {
        this.context = context;
        this.topRatingModelList= topRatingModelList;
    }

    @NonNull
    @Override
    public Top_rating_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_thirty_three,parent,false);


        return new Top_rating_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.img.setImageResource(R.drawable.news_reader);
        holder.img1.setImageResource(R.drawable.eye_icon);
        holder.txt.setText(topRatingModelList.get(position).getText());
        holder.txt1.setText(topRatingModelList.get(position).getText1());
    }





    // total number of rows
    @Override
    public int getItemCount() {

        return 5;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1;
        TextView txt,txt1;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.topic_img_home);
            img1 = itemView.findViewById(R.id.eye_topic);
            txt = itemView.findViewById(R.id.topic_one_txt);
            txt1 = itemView.findViewById(R.id.topic_two_txt);


        }

    }

    }


