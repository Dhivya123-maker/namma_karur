package com.e.login.NewsClass;



import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.BusTimeClass.BusTimeModel;
import com.e.login.R;
import com.e.login.SmallBusClass.SmallBusActivity;

import java.util.List;

public class MyRecyclerViewAdapter_two extends RecyclerView.Adapter<com.e.login.NewsClass.MyRecyclerViewAdapter_two.ViewHolder> {


    List<News_Breaking_Model> newsBreakingModelList;
    private Context context;


    public MyRecyclerViewAdapter_two(Context context, List<News_Breaking_Model> newsBreakingModelList) {
        this.context = context;
        this.newsBreakingModelList = newsBreakingModelList;
    }

    @NonNull
    @Override
    public com.e.login.NewsClass.MyRecyclerViewAdapter_two.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_two, parent, false);


        return new com.e.login.NewsClass.MyRecyclerViewAdapter_two.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.img.setImageResource(R.drawable.news_five);
        Glide.with(context)
                .load(newsBreakingModelList.get(position).getImage())
                .into(holder.img);
        holder.textView.setText(newsBreakingModelList.get(position).getTxt());
    }


    // total number of rows
    @Override
    public int getItemCount() {

        return newsBreakingModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
       LinearLayout btn;
       TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.tv1);
           textView = itemView.findViewById(R.id.newss_txt);

           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    String id = newsBreakingModelList.get(position).getId();
                    Intent intent1 = new Intent(view.getContext(),All_news.class);
//                    intent1.putExtra("cat1","All_news");
                    intent1.putExtra("id",id);
                    view.getContext().startActivity(intent1);
                    Toast.makeText(context.getApplicationContext(), id, Toast.LENGTH_SHORT).show();


//                    if (mListener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.onItemClick(position);
//                        }
//                    }
                }
            });

        }


    }


}