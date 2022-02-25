package com.e.login.EventClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.Mall.Mall_Food_Model;
import com.e.login.Mall.Mall_Home_Fragment;
import com.e.login.R;

import java.util.List;

public class Event_Hotel_Adapter extends RecyclerView.Adapter<Event_Hotel_Adapter.ViewHolder> {



    List<Event_Hotel_Model>eventHotelModelList;
    private Context context;


    public Event_Hotel_Adapter(Context context, List<Event_Hotel_Model> eventHotelModelList) {
        this.context = context;
        this.eventHotelModelList=eventHotelModelList;
    }

    @NonNull
    @Override
    public Event_Hotel_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_twenty_five,parent,false);


        return new Event_Hotel_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(eventHotelModelList.get(position).getText());
        holder.textview1.setText(eventHotelModelList.get(position).getText1());
        holder.txt2.setText(eventHotelModelList.get(position).getText2());
        holder.txt3.setText(eventHotelModelList.get(position).getText3());
        holder.txt4.setText(eventHotelModelList.get(position).getText4());

        holder.img.setImageResource(R.drawable.axis);
        holder.img1.setImageResource(R.drawable.clock);
        holder.img2.setImageResource(R.drawable.verified);
        holder.img3.setImageResource(R.drawable.starr);
    }


    // total number of rows
    @Override
    public int getItemCount() {

        return 4;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,img1,img2,img3;
        TextView textView,textview1,txt2,txt3,txt4;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.event_hotel_img);
            img1 = itemView.findViewById(R.id.time_img_event);
            img2 = itemView.findViewById(R.id.verify_img_event);
            img3 = itemView.findViewById(R.id.star_event);
            textView = itemView.findViewById(R.id.event_hotel_txt_one);
            textview1 = itemView.findViewById(R.id.event_hotel_txt_two);
            txt2 = itemView.findViewById(R.id.time_txt_event);
            txt3 = itemView.findViewById(R.id.verified_txt_event);
            txt4 = itemView.findViewById(R.id.review_event);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), Event_Home_Fragment.class);
                    view.getContext().startActivity(intent);
                }
            });

        }



    }



}
