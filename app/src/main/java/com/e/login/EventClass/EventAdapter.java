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

import com.e.login.Mall.Mallmodel;
import com.e.login.Mall.Malls_One;
import com.e.login.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {



    List<EventModel> eventModelList;
    private Context context;


    public EventAdapter(Context context, List<EventModel> eventModelList) {
        this.context = context;
        this.eventModelList= eventModelList;
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_twenty_four,parent,false);


        return new EventAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(eventModelList.get(position).getText());

        holder.img.setImageResource(R.drawable.three_star);
    }



    // total number of rows
    @Override
    public int getItemCount() {

        return 3;

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.event_img);

            textView = itemView.findViewById(R.id.event_txt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Event_Hotel_Activity.class);
                    view.getContext().startActivity(intent);
                }
            });

        }


    }



}


