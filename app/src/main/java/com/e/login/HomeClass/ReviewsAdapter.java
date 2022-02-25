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

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {



    List<ReviewsModel> reviewsModelList;
    private Context context;


    public ReviewsAdapter(Context context, List<ReviewsModel> reviewsModelList) {
        this.context = context;
        this.reviewsModelList = reviewsModelList;
    }

    @NonNull
    @Override
    public ReviewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_thirty_four,parent,false);


        return new ReviewsAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(reviewsModelList.get(position).getTxt());
        holder.textView1.setText(reviewsModelList.get(position).getTxt1());
        holder.txt2.setText(reviewsModelList.get(position).getTxt2());
        holder.txt3.setText(reviewsModelList.get(position).getTxt3());

        holder.img.setImageResource(R.drawable.five_starr);
    }



    // total number of rows
    @Override
    public int getItemCount() {

        return 5;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView, textView1,txt2,txt3;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.starr_reviews);

            textView = itemView.findViewById(R.id.dd_txt);
            textView1 = itemView.findViewById(R.id.userr_txt);
            txt2 = itemView.findViewById(R.id.goodd_txt);
            txt3= itemView.findViewById(R.id.reviewss_ratee);





        }



    }



}
