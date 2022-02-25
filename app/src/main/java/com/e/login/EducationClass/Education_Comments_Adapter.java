package com.e.login.EducationClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.BlankFragment.Blank_Comments_Model;
import com.e.login.R;

import java.util.List;

public class Education_Comments_Adapter extends RecyclerView.Adapter<Education_Comments_Adapter.ViewHolder> {



    List<Education_Comments_Model> educationCommentsModelList;
    private Context context;


    public Education_Comments_Adapter(Context context, List<Education_Comments_Model> educationCommentsModelList) {
        this.context = context;
        this.educationCommentsModelList = educationCommentsModelList;
    }

    @NonNull
    @Override
    public Education_Comments_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_sixty_seven,parent,false);


        return new Education_Comments_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(educationCommentsModelList.get(position).getTxt());
        holder.textView1.setText(educationCommentsModelList.get(position).getTxt1());
        holder.txt2.setText(educationCommentsModelList.get(position).getTxt2());

        holder.img.setImageResource(R.drawable.girl_white);
        holder.img1.setImageResource(R.drawable.five_starr);
    }




    // total number of rows
    @Override
    public int getItemCount() {

        return educationCommentsModelList.size();

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1;
        TextView textView, textView1,txt2;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.comment_img1);
            img1= itemView.findViewById(R.id.star_s1);

            textView = itemView.findViewById(R.id.u_txt1);
            textView1 = itemView.findViewById(R.id.g_txt1);
            txt2 = itemView.findViewById(R.id.r_ratee1);






        }



    }



}
