package com.e.login.HomeClass;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.R;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {



    List<Recycler_Model> myrecyclerModelList;
    private Context context;


    public MyRecyclerViewAdapter(Context context, List<Recycler_Model> recyclerModelList) {
        this.context = context;
        this.myrecyclerModelList =recyclerModelList;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row,parent,false);


        return new MyRecyclerViewAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(myrecyclerModelList.get(position).getTxt());
        holder.textView1.setText(myrecyclerModelList.get(position).getTxt1());

        holder.img.setImageResource(R.drawable.phone_one);
        holder.img1.setImageResource(R.drawable.five_starr);
    }



    // total number of rows
    @Override
    public int getItemCount() {

        return 5;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1;
        TextView textView, textView1;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.mobile_img);
            img1 = itemView.findViewById(R.id.five_rate);

            textView = itemView.findViewById(R.id.best_txt);
            textView1 = itemView.findViewById(R.id.best_txt_two);





        }



    }



}