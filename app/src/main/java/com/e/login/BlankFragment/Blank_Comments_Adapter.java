package com.e.login.BlankFragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.Bank.Bank_Comments_Model;
import com.e.login.R;

import java.util.List;

public class Blank_Comments_Adapter extends RecyclerView.Adapter<Blank_Comments_Adapter.ViewHolder> {



    List<Blank_Comments_Model> blankCommentsModelList;
    private Context context;


    public Blank_Comments_Adapter(Context context, List<Blank_Comments_Model>blankCommentsModelList) {
        this.context = context;
        this.blankCommentsModelList = blankCommentsModelList;
    }

    @NonNull
    @Override
    public Blank_Comments_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_thirty_ninee,parent,false);


        return new Blank_Comments_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(blankCommentsModelList.get(position).getTxt());
        holder.textView1.setText(blankCommentsModelList.get(position).getTxt1());
        holder.txt2.setText(blankCommentsModelList.get(position).getTxt2());

        holder.img.setImageResource(R.drawable.girl_white);







            if(blankCommentsModelList.get(position).getTxt2().equals("1") || blankCommentsModelList.get(position).getTxt2().equals("1.5")  ){
                holder.img1.setImageResource(R.drawable.y_star1);

            }else  if(blankCommentsModelList.get(position).getTxt2().equals("2") || blankCommentsModelList.get(position).getTxt2().equals("2.5")){
                holder.img1.setImageResource(R.drawable.y_star1);
                holder.img2.setImageResource(R.drawable.y_star1);
            }else  if(blankCommentsModelList.get(position).getTxt2().equals("3") || blankCommentsModelList.get(position).getTxt2().equals("3.5") ){
                holder.img1.setImageResource(R.drawable.y_star1);
                holder.img2.setImageResource(R.drawable.y_star1);
                holder.img3.setImageResource(R.drawable.y_star1);
            }else  if(blankCommentsModelList.get(position).getTxt2().equals("4") || blankCommentsModelList.get(position).getTxt2().equals("4.5")){
                holder.img1.setImageResource(R.drawable.y_star1);
                holder.img2.setImageResource(R.drawable.y_star1);
                holder.img3.setImageResource(R.drawable.y_star1);
            holder.img4.setImageResource(R.drawable.y_star1);

        }else  if(blankCommentsModelList.get(position).getTxt2().equals("5")  ){
            holder.img1.setImageResource(R.drawable.y_star1);
            holder.img2.setImageResource(R.drawable.y_star1);
            holder.img3.setImageResource(R.drawable.y_star1);
            holder.img4.setImageResource(R.drawable.y_star1);
            holder.img5.setImageResource(R.drawable.y_star1);

        }
//        holder.img1.setImageResource(R.drawable.five_starr);
    }




    // total number of rows
    @Override
    public int getItemCount() {

        return blankCommentsModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2,img3,img4,img5;
        TextView textView, textView1,txt2;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.comment_img);
            img1 = itemView.findViewById(R.id.star_s1);
            img2 = itemView.findViewById(R.id.star_s2);
            img3 = itemView.findViewById(R.id.star_s3);
            img4 = itemView.findViewById(R.id.star_s4);
            img5 = itemView.findViewById(R.id.star_s5);


            textView = itemView.findViewById(R.id.u_txt);
            textView1 = itemView.findViewById(R.id.g_txt);
            txt2 = itemView.findViewById(R.id.r_ratee);





        }



    }


}
