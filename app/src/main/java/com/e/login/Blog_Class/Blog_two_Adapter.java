package com.e.login.Blog_Class;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.EducationClass.Education_one_Adapter;
import com.e.login.R;

import java.util.List;

public class Blog_two_Adapter extends RecyclerView.Adapter<Blog_two_Adapter.ViewHolder> {



    List<Blog_two_Model> blogTwoModelList;
    private Context context;



    public Blog_two_Adapter(Context context, List<Blog_two_Model> blogTwoModelList) {
        this.context = context;
        this.blogTwoModelList = blogTwoModelList;
    }

    @NonNull
    @Override
    public Blog_two_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_sixty_four,parent,false);


        return new Blog_two_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(blogTwoModelList.get(position).getTxt());
        holder.textView1.setText(blogTwoModelList.get(position).getTxt1());

        Glide.with(context)
                .load(blogTwoModelList.get(position).getImg())
                .into(holder.img);


    }






    // total number of rows
    @Override
    public int getItemCount() {

        return blogTwoModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView, textView1;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.popular_img);

            textView = itemView.findViewById(R.id.popular_txt);
            textView1 = itemView.findViewById(R.id.popular_txt_one);
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    String link = blogTwoModelList.get(position).getLink();

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+link));
                    context.startActivity(browserIntent);


                }
            });





        }



    }


}