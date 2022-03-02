package com.e.login.Blog_Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;

import java.util.List;

public class Blog_One_Adapter extends RecyclerView.Adapter<Blog_One_Adapter.ViewHolder> {



    List<Blog_One_Model> blogOneModelList;
    private Context context;


    public Blog_One_Adapter(Context context, List<Blog_One_Model> blogOneModelList) {
        this.context = context;
        this.blogOneModelList = blogOneModelList;
    }

    @NonNull
    @Override
    public Blog_One_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_sixty_three,parent,false);


        return new Blog_One_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(blogOneModelList.get(position).getTxt());
        holder.textView1.setText(blogOneModelList.get(position).getTxt1());


        Glide.with(context)
                .load(blogOneModelList.get(position).getImg())
                .into(holder.img);

    }






    // total number of rows
    @Override
    public int getItemCount() {

        return blogOneModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView, textView1;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.latest_img);

            textView = itemView.findViewById(R.id.latest_txt);
            textView1 = itemView.findViewById(R.id.latest_txt_one);





        }



    }


}