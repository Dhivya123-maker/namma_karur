package com.e.login.NewsClass;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.BusTimeClass.BusTimeModel;
import com.e.login.R;
import com.e.login.SmallBusClass.SmallBusActivity;

import org.w3c.dom.Text;

import java.util.List;

public class Recycler_news_Adapter extends RecyclerView.Adapter<com.e.login.NewsClass.Recycler_news_Adapter.ViewHolder> {



    List<NewsModel> newsModelList;
    private Context context;

    public static Recycler_news_Adapter.OnItemClickListener mListener;




    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(FragmentActivity listener){

        mListener = (Recycler_news_Adapter.OnItemClickListener) listener;

    }



    public Recycler_news_Adapter(Context context, List<NewsModel> newsModelList) {
        this.context = context;
        this.newsModelList= newsModelList;
    }

    @NonNull
    @Override
    public com.e.login.NewsClass.Recycler_news_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_one,parent,false);


        return new com.e.login.NewsClass.Recycler_news_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.img.setImageResource(R.drawable.news_one);
        holder.txt.setText(newsModelList.get(position).getTxt());


        Glide.with(context)
                .load(newsModelList.get(position).getImage())
                .into(holder.img);
    }





    // total number of rows
    @Override
    public int getItemCount() {

        return newsModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2,img3;
        TextView txt;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.newsimg);
            txt = itemView.findViewById(R.id.news_t);



          itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    String id = newsModelList.get(position).getId();
                    Intent intent1 = new Intent(view.getContext(),All_news.class);
                    intent1.putExtra("id",id);
                    view.getContext().startActivity(intent1);
//                    Toast.makeText(context.getApplicationContext(), id, Toast.LENGTH_SHORT).show();




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