package com.e.login.Blog_Class;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.HospitalClass.HospitalModel;
import com.e.login.R;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {



    List<BlogModel> blogModelList;
    private Context context;




    public BlogAdapter(Context context, List<BlogModel> blogModelList) {
        this.context = context;
        this.blogModelList = blogModelList;



    }




    @NonNull
    @Override
    public BlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fifty_one,parent,false);


        return new ViewHolder(view);



    }


    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        String animal = mData.get(position);
//         holder.textView.setText(animal);

        holder.textView.setText(blogModelList.get(position).getText());
       holder.textView1.setText(blogModelList.get(position).getText_one());
       holder.imageView.setImageResource(R.drawable.veg);
      //  holder.imageView.setImageResource(shopModelList.get(position).getImage());


//        Glide.with(context)
//                .load(servicesModelList.get(position).getImage())
//                .into(holder.imageView);




    }

    // total number of rows
    @Override
    public int getItemCount() {

        return 2;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView, textView1;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.blog_one_img);

            textView = itemView.findViewById(R.id.blog_one_txt);
            textView1 = itemView.findViewById(R.id.blog_two_txt);




        }



    }


}