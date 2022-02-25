package com.e.login.HotelClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.login.Articles;
import com.e.login.FunClass.FunModel;
import com.e.login.R;

import java.util.List;

public class Hotel_Adapter extends RecyclerView.Adapter<Hotel_Adapter.ViewHolder> {



    List<HotelModel> hotelModelList;
    private Context context;


    public Hotel_Adapter(Context context, List<HotelModel> hotelModelList) {
        this.context = context;
        this.hotelModelList = hotelModelList;
    }

    @NonNull
    @Override
    public Hotel_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_thirty_first,parent,false);


        return new Hotel_Adapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(hotelModelList.get(position).getText());
        holder.img.setImageResource(R.drawable.articles);
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
            img = itemView.findViewById(R.id.hotels_img);

            textView = itemView.findViewById(R.id.hotels_txt);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), Articles.class);
                    view.getContext().startActivity(intent);
                }
            });

        }



    }


}