package com.e.login.Mall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.Bank.Bank_Comments_Model;
import com.e.login.R;

import java.util.List;

public class Mall_Comments_Adapter extends RecyclerView.Adapter<Mall_Comments_Adapter.ViewHolder> {



    List<Mall_Comments_Model> mallCommentsModelList;
    private Context context;


    public Mall_Comments_Adapter(Context context, List<Mall_Comments_Model>mallCommentsModelList) {
        this.context = context;
        this.mallCommentsModelList = mallCommentsModelList;
    }

    @NonNull
    @Override
    public Mall_Comments_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fourty_four,parent,false);


        return new Mall_Comments_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(mallCommentsModelList.get(position).getTxt());
        holder.textView1.setText(mallCommentsModelList.get(position).getTxt1());
        holder.txt2.setText(mallCommentsModelList.get(position).getTxt2());

        holder.img.setImageResource(R.drawable.girl_white);
        holder.img1.setImageResource(R.drawable.five_starr);
    }




    // total number of rows
    @Override
    public int getItemCount() {

        return 4;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1;
        TextView textView, textView1,txt2;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.comments_img_mall);
            img1= itemView.findViewById(R.id.mall_star);

            textView = itemView.findViewById(R.id.mall_user_txt);
            textView1 = itemView.findViewById(R.id.mall_good_txt);
            txt2 = itemView.findViewById(R.id.mall_reviews);






        }



    }



}
