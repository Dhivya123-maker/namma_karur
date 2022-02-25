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

public class News_recycler_Adapter extends RecyclerView.Adapter<News_recycler_Adapter.ViewHolder> {



    List<News_Model> newsModelList;
    private Context context;


    public News_recycler_Adapter(Context context, List<News_Model> newsModelList) {
        this.context = context;
        this.newsModelList = newsModelList;
    }

    @NonNull
    @Override
    public News_recycler_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_thirty_two,parent,false);


        return new News_recycler_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(newsModelList.get(position).getText());
        holder.textView1.setText(newsModelList.get(position).getText_one());
        holder.img.setImageResource(R.drawable.news_reader);
        holder.img1.setImageResource(R.drawable.eye_icon);
        holder.img2.setImageResource(R.drawable.dots);
    }





    // total number of rows
    @Override
    public int getItemCount() {

        return 4;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2;
        TextView textView, textView1;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.news_img_home);
            img1 = itemView.findViewById(R.id.eye_home);
            img2 = itemView.findViewById(R.id.menu_home);



            textView = itemView.findViewById(R.id.news_one_txt);
            textView1 = itemView.findViewById(R.id.news_two_txt);






        }



    }



}
