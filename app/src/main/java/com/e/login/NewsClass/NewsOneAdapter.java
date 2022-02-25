package com.e.login.NewsClass;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.BusTimeClass.BusTimeModel;
import com.e.login.R;
import com.e.login.ShopClass.ShopClassAdapter;
import com.e.login.SmallBusClass.SmallBusActivity;

import java.util.List;

class NewsoneAdapter extends RecyclerView.Adapter<com.e.login.NewsClass.NewsoneAdapter.ViewHolder> {



    List<NewsOneModel> newsOneModelList;
    private Context context;


    public static OnItemClickListener mListener;




    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(FragmentActivity listener){

        mListener = (NewsoneAdapter.OnItemClickListener) listener;

    }

    public NewsoneAdapter(Context context, List<NewsOneModel> newsOneModelList) {
        this.context = context;
        this.newsOneModelList= newsOneModelList;
    }

    @NonNull
    @Override
    public com.e.login.NewsClass.NewsoneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_fifteen,parent,false);


        return new com.e.login.NewsClass.NewsoneAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(newsOneModelList.get(position).getText());
        holder.textView1.setText(newsOneModelList.get(position).getText_one());
//        holder.img.setImageResource(R.drawable.dental);
        Glide.with(context)
                .load(newsOneModelList.get(position).getImage())
                .into(holder.img);
        holder.img1.setImageResource(R.drawable.eye);
        holder.btn.setText(newsOneModelList.get(position).getButton());
    }



    // total number of rows
    @Override
    public int getItemCount() {

        return newsOneModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1;
        TextView textView,textView1;
        Button btn;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.dental_img);
            img1 = itemView.findViewById(R.id.eye_img);

           textView = itemView.findViewById(R.id.dental_para);
           textView1 = itemView.findViewById(R.id.number);
           btn = itemView.findViewById(R.id.view_btn);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    String id = newsOneModelList.get(position).getId();
                    Intent intent1 = new Intent(view.getContext(),All_news.class);
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