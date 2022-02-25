package com.e.login.TransportClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.EducationClass.Education_Comments_Model;
import com.e.login.R;

import java.util.List;

public class transport_Comments_Adapter extends RecyclerView.Adapter<transport_Comments_Adapter.ViewHolder> {



    List<Transport_Comments_Model> transportCommentsModelList;
    private Context context;


    public transport_Comments_Adapter(Context context, List<Transport_Comments_Model> transportCommentsModelList) {
        this.context = context;
        this.transportCommentsModelList = transportCommentsModelList;
    }

    @NonNull
    @Override
    public transport_Comments_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_sixty_nine,parent,false);


        return new transport_Comments_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(transportCommentsModelList.get(position).getTxt());
        holder.textView1.setText(transportCommentsModelList.get(position).getTxt1());
        holder.txt2.setText(transportCommentsModelList.get(position).getTxt2());

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
            img = itemView.findViewById(R.id.comment_img2);
            img1= itemView.findViewById(R.id.star_s2);

            textView = itemView.findViewById(R.id.u_txt2);
            textView1 = itemView.findViewById(R.id.g_txt2);
            txt2 = itemView.findViewById(R.id.r_rate2);






        }



    }



}
