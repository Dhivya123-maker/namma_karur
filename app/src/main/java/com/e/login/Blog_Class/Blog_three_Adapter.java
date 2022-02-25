package com.e.login.Blog_Class;

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

public class Blog_three_Adapter extends RecyclerView.Adapter<Blog_three_Adapter.ViewHolder> {



    List<Blog_three_Model> blog_three_modelList;
    private Context context;


    public Blog_three_Adapter(Context context, List<Blog_three_Model> blog_three_modelList) {
        this.context = context;
        this.blog_three_modelList = blog_three_modelList;
    }

    @NonNull
    @Override
    public Blog_three_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_sixty_five,parent,false);


        return new Blog_three_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(blog_three_modelList.get(position).getTxt());
        holder.textView1.setText(blog_three_modelList.get(position).getTxt1());


        holder.img.setImageResource(R.drawable.veg);
    }






    // total number of rows
    @Override
    public int getItemCount() {

        return 4;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView, textView1;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.ns_img);

            textView = itemView.findViewById(R.id.ns_txt);
            textView1 = itemView.findViewById(R.id.ns_txt_one);





        }



    }


}