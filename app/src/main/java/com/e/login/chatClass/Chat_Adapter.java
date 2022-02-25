package com.e.login.chatClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.R;

import java.util.List;

public class Chat_Adapter extends RecyclerView.Adapter<Chat_Adapter.ViewHolder> {



    List<Chat_Model> chatModelList;
    private Context context;


    public Chat_Adapter(Context context, List<Chat_Model> chatModelList) {
        this.context = context;
        this.chatModelList= chatModelList;
    }

    @NonNull
    @Override
    public Chat_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fourty_eight,parent,false);


        return new Chat_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(chatModelList.get(position).getTxt());
        holder.textView1.setText(chatModelList.get(position).getTxt1());
        holder.txt2.setText(chatModelList.get(position).getTxt2());


        holder.img.setImageResource(R.drawable.twitter);

    }






    // total number of rows
    @Override
    public int getItemCount() {

        return 3;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView, textView1,txt2;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.chat_img);

            textView = itemView.findViewById(R.id.chat_txt);
            textView1 = itemView.findViewById(R.id.chat_txt_one);
            txt2 = itemView.findViewById(R.id.chat_time);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),Chat_Inside.class);
                    view.getContext().startActivity(intent);
                }
            });


        }



    }


}